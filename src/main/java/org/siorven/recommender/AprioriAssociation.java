package org.siorven.recommender;

import org.siorven.model.*;
import org.siorven.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import weka.associations.*;
import weka.core.Instances;
import weka.core.converters.ArffLoader;

import javax.servlet.ServletContext;
import java.io.File;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * Created by joseb on 17/05/2017.
 */
@Component
public class AprioriAssociation {

    public static final double APRIORI_MIN_METRIC = 0.5;
    public static final int APRIORI_NUM_RULES = 8;
    public static final String TRUE = "t";
    public static final String FALSE = "f";

    @Autowired
    private ProductService productService;

    @Autowired
    private MachineService machineService;

    @Autowired
    private SuggestionService suggestionService;

    @Autowired
    private StatementService statementService;

    @Autowired
    private MachineProductService machineProductService;

    private Timestamp finishDate;

    @Autowired
    private ServletContext servletContext;

    private List<Machine> machineList;


    //@Scheduled(fixedRate = 10000,initialDelay = 10000)
    public void runApriori(Instances outData) { //Instances outData, Machine machine
        machineList = machineService.findAll();
        ArffLoader loader = new ArffLoader();
        try {
            loader.setSource(new File(servletContext.getRealPath("/WEB-INF/data/dataset1.arff")));

            // build associator and configure parameters
            Apriori apriori = prepareAprioriAssociator(outData);

            //Separate the result into rules
            finishDate = new Timestamp(new Date().getTime());
            getSuggestionsFromAprioriRules(apriori);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * Translate the results of the Apriori algorith into a list of suggestions.
     *
     * @param apriori
     * @return
     */
    private List<Suggestion> getSuggestionsFromAprioriRules(Apriori apriori) {
        List<Suggestion> suggestionList = new ArrayList<>();
        AssociationRules rules = apriori.getAssociationRules();
        List<AssociationRule> listaRules = rules.getRules();
        for (AssociationRule rule : listaRules) {
            getAndSaveSuggestionsFromRule(rule);
        }

        return suggestionList;
    }

    /**
     * Function that configures Apriori algorithm parameters
     *
     * @param data instances generated from arff file
     * @return
     * @throws Exception
     */
    private Apriori prepareAprioriAssociator(Instances data) throws Exception {
        Apriori apriori = new Apriori();
        apriori.setMinMetric(APRIORI_MIN_METRIC);
        apriori.setNumRules(APRIORI_NUM_RULES);
        apriori.setClassIndex(data.classIndex());
        apriori.buildAssociations(data);
        return apriori;
    }

    /**
     * Parse a determinate {@link AssociationRule} into a {@link Suggestion}
     *
     * @param rule
     * @return
     */
    private Suggestion getAndSaveSuggestionsFromRule(AssociationRule rule) {
        SuggestionAssociation suggestion = null;
        try {

            List<Statement> premises = parseStatements(rule.getPremise());
            List<Statement> consequences = parseStatements(rule.getConsequence());

            double weight = rule.getTotalSupport();

            for (Machine machine : machineList) {
                if (!premises.isEmpty() && !consequences.isEmpty()) {
                    if (checkIfMachineHasPremises(machine, premises)) {
                        for (Statement s : premises) {
                            statementService.save(s);
                        }
                        for (Statement s : consequences) {
                            statementService.save(s);
                        }
                        suggestion = new SuggestionAssociation(finishDate, machine, weight);
                        suggestion.setConsequenceList(consequences);
                        suggestion.setPremiseList(premises);
                        suggestionService.save(suggestion);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return suggestion;
    }

    private boolean checkIfMachineHasPremises(Machine m, List<Statement> premises) {
        boolean checker = false;
        List<MachineProduct> machineProducts = machineProductService.findByMachine(m);
        for (Statement s : premises) {
            for (MachineProduct mp : machineProducts) {
                if (mp.getProduct().getId() == s.getProduct().getId()) {
                    checker = true;
                    break;
                }
            }
        }
        return checker;
    }

    private boolean stringToBoolean(String s) throws Exception {
        if (TRUE.equalsIgnoreCase(s)) {
            return true;
        } else if (FALSE.equalsIgnoreCase(s)) {
            return false;
        }
        throw new ClassCastException();
    }

    private List<Statement> parseStatements(Collection<Item> rule) throws Exception {
        List<Statement> statementList = new ArrayList<>();
        for (Item i : rule) {
            NominalItem ni = (NominalItem) i;
            Product p = productService.findByName(ni.getAttribute().name());
            if (p != null) {
                Statement statement = new Statement(p, stringToBoolean(ni.getItemValueAsString()));
                statementList.add(statement);
            }
        }
        return statementList;
    }
}

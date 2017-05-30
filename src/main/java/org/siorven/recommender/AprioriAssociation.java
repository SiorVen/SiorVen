package org.siorven.recommender;

import org.siorven.model.*;
import org.siorven.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import weka.associations.*;
import weka.core.*;
import weka.core.converters.ArffLoader;
import weka.filters.unsupervised.attribute.AddID;
import weka.filters.unsupervised.attribute.NumericToNominal;

import javax.servlet.ServletContext;
import java.io.File;
import java.sql.Timestamp;
import java.util.*;

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

            Instances data = outData;//loader.getDataSet();

            // build associator and configure parameters
            Apriori apriori = prepareAprioriAssociator(data);

            // output associator
            System.out.println(apriori);

            //Separate the result into rules
            finishDate = new Timestamp(new Date().getTime());
            List<Suggestion> suggestions = getSuggestionsFromAprioriRules(apriori);
            if(suggestions.size() > 0 ) {
                for (Suggestion suggestion : suggestions) {
                    System.out.println(suggestion.toString(null, null, null));
                }
            }

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
        System.out.println("Resultados de uno en uno");
        for (AssociationRule rule : listaRules) {
            Suggestion sug = getAndSaveSuggestionsFromRule(rule);
            if(sug != null) {
                suggestionList.add(sug);
            }
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

            for(Machine machine : machineList) {
                suggestion = new SuggestionAssociation(finishDate, machine);
                suggestion.setConsequenceList(consequences);
                suggestion.setPremiseList(premises);
                suggestionService.save(suggestion);
            }
        } catch (Exception e) {
            System.out.println("Error parsing rule");
        }

        return suggestion;
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
            Statement statement = new Statement(productService.findByName(ni.getAttribute().name()),
                    stringToBoolean(ni.getItemValueAsString()),ni.getAttribute().weight());
            statementService.save(statement);
            statementList.add(statement);
        }
        return statementList;
    }
}

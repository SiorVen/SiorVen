package org.siorven.services;

import org.siorven.controller.handlers.errors.ResourceNotFoundException;
import org.siorven.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import weka.associations.*;
import weka.core.Instances;

import java.sql.Timestamp;
import java.util.*;

/**
 * Service that provides functions of artificial intelligence, generating MaxMin and Association suggestions
 */
@Service
public class IAService {

    public static final String TRUE = "t";
    public static final String FALSE = "f";

    @Autowired
    private ProductService productService;

    @Autowired
    private MachineProductService machineProductService;

    @Autowired
    private ConfigParamService conf;

    @Autowired
    private MachineService machineService;

    private List<Machine> machineList;

    private Timestamp finishDate;

    /**
     * Generate suggestions of maximum and minimum sold product.
     * Takes into account a ratio for maximum and another for minimum.
     * If a product is sold more than the media plus the ratio is considered a maximum, and the same happens for minimum.
     * @param machine machine that which sales are evaluated.
     * @param machineProductQuantity List of machine products and the quantity of the number of sales for each one.
     * @param now
     * @return
     */
    public List<Suggestion> generateMaxMinSuggestions(Machine machine, HashMap<Integer, Double> machineProductQuantity, Timestamp now) {
        int totalSales = 0;
        double mediaSales;
        ArrayList<Suggestion> maxMinSuggestions = new ArrayList<>();
        for (Map.Entry<Integer, Double> entry : machineProductQuantity.entrySet()) {
            totalSales += entry.getValue();
        }
        List<MachineProduct> products = machineProductService.findByMachine(machine);
        mediaSales = totalSales / ((double) products.size());
        for (MachineProduct mp : products){

            if(machineProductQuantity.containsKey(mp.getProduct().getId())){
                Double saleQnt = machineProductQuantity.get(mp.getProduct().getId());
                if (saleQnt.compareTo(mediaSales * conf.getDouble(ConfigParam.SUGG_MAXMIN_RATIOMAX)) > 0) {
                    Suggestion maxMinSug = new SuggestionStatistic(now, machine, mp.getProduct(),SuggestionStatistic.MAX, 10);
                    maxMinSuggestions.add(maxMinSug);
                    //suggestionService.save(maxMinSug);
                }
                if (saleQnt.compareTo(mediaSales * conf.getDouble(ConfigParam.SUGG_MAXMIN_RATIOMIN)) < 0) {
                    Suggestion maxMinSug = new SuggestionStatistic(now, machine, mp.getProduct(),SuggestionStatistic.MIN, 5);
                    maxMinSuggestions.add(maxMinSug);
                    //suggestionService.save(maxMinSug);
                }
            } else {
                Suggestion maxMinSug = new SuggestionStatistic(now, machine, mp.getProduct(),SuggestionStatistic.MIN, 10);
                maxMinSuggestions.add(maxMinSug);
                //suggestionService.save(maxMinSug);
            }
        }
        return maxMinSuggestions;
    }

    /**
     * Translate the results of the Apriori algorith into a list of suggestions.
     *
     * @param apriori
     * @return
     */
    public List<Suggestion> getSuggestionsFromAprioriRules(Apriori apriori, Timestamp finishDate) {
        this.finishDate = finishDate;
        machineList = machineService.findAll();
        List<Suggestion> suggestionList = new ArrayList<>();
        AssociationRules rules = apriori.getAssociationRules();
        List<AssociationRule> listaRules = rules.getRules();
        for (AssociationRule rule : listaRules) {
            List<Suggestion> ruleSugg = getSuggestionsFromRule(rule);
            suggestionList.addAll(ruleSugg);
        }
        return suggestionList;
    }

    /**
     * Parse a determinate {@link AssociationRule} into a list of {@link Suggestion}
     *
     * @param rule
     * @return
     */
    private List<Suggestion> getSuggestionsFromRule(AssociationRule rule) {
        SuggestionAssociation suggestion = null;
        List<Suggestion> suggestions = new ArrayList<>();
        try {
            for (Machine machine : machineList) {
                List<Statement> premises = parseStatements(rule.getPremise());
                List<Statement> consequences = parseStatements(rule.getConsequence());

                double weight = rule.getTotalSupport();

                if (!premises.isEmpty() && !consequences.isEmpty() && checkIfMachineHasPremises(machine, premises)) {
                    suggestion = new SuggestionAssociation(finishDate, machine, weight);
                    suggestion.setConsequenceList(consequences);
                    suggestion.setPremiseList(premises);
                    suggestions.add(suggestion);
                    //suggestionService.save(suggestion);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return suggestions;
    }

    /**
     * Check if the products of a premise are located in a specific machine
     * @param m The machine to check the products
     * @param premises
     * @return
     */
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

    /**
     * Function to parse a string (t or f) to boolean
     * @param s
     * @return
     * @throws ClassCastException
     */
    private boolean stringToBoolean(String s) throws ClassCastException {
        if (TRUE.equalsIgnoreCase(s)) {
            return true;
        } else if (FALSE.equalsIgnoreCase(s)) {
            return false;
        }
        throw new ClassCastException();
    }

    /**
     * Generate a list of statements from a determinated rule
     * @param rule
     * @return
     * @throws ResourceNotFoundException
     */
    private List<Statement> parseStatements(Collection<Item> rule) throws ResourceNotFoundException {
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

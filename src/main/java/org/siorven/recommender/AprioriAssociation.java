package org.siorven.recommender;

import org.siorven.logic.Suggestion;
import org.siorven.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import weka.associations.Apriori;
import weka.associations.AssociationRule;
import weka.associations.AssociationRules;
import weka.associations.Item;
import weka.core.Instances;
import weka.core.converters.ArffLoader;
import weka.filters.unsupervised.attribute.AddID;
import weka.filters.unsupervised.attribute.NumericToNominal;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by joseb on 17/05/2017.
 */
public class AprioriAssociation {

    public static final double APRIORI_MIN_METRIC = 0.5;
    public static final int APRIORI_NUM_RULES = 8;

    @Autowired
    private ProductService productService;

    public void runApriori(Instances outData) {
        ArffLoader loader = new ArffLoader();
        try {
            //loader.setSource(new File(file));

            Instances data = outData;//loader.getDataSet();
            System.out.println("\nHeader of dataset:\n");
            System.out.println(new Instances(data, 0));

            //preprocess data

            //1. Add ID as class value
            data = addIdFilter(data);


            //2. Change ID format from numeric to nominal
            data = changeIdNumericToNominal(data);

            //3. ensure that ID attribute is considered as class)
            data.setClassIndex(data.numAttributes() - 1);

            // build associator and configure parameters
            Apriori apriori = prepareAprioriAssociator(data);

            // output associator
            System.out.println(apriori);

            //Separate the result into rules
            getSuggestionsFromAprioriRules(apriori);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * Translate the results of the Apriori algorith into a list of suggestions.
     * TODO: Kontuz, Rule baten parseoan errorea badau puede cascar muy facilmente. Arreglau in behar da!!!!!
     * @param apriori
     * @return
     */
    private List<Suggestion> getSuggestionsFromAprioriRules(Apriori apriori) {
        List<Suggestion> suggestionList = new ArrayList<>();
        AssociationRules rules = apriori.getAssociationRules();
        List<AssociationRule> listaRules = rules.getRules();
        System.out.println("Resultados de uno en uno");
        for (AssociationRule rule : listaRules) {
            suggestionList.add(parseRule(rule));
            System.out.println("");
        }

        return suggestionList;
    }

    /**
     * Function that configures Apriori algorithm parameters
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
     * Change the format of the ID attribute from numeric to nominal
     * @param data instances generated from arff file
     * @return
     * @throws Exception
     */
    private Instances changeIdNumericToNominal(Instances data) throws Exception {
        NumericToNominal numToNomFilter = new NumericToNominal();
        numToNomFilter.setAttributeIndices("last");
        numToNomFilter.setInputFormat(data);
        data = numToNomFilter.useFilter(data, numToNomFilter);
        return data;
    }

    /**
     * Add ID attribute to de instances
     * @param data
     * @return
     * @throws Exception
     */
    private Instances addIdFilter(Instances data) throws Exception {
        AddID idFilter = new AddID();
        idFilter.setIDIndex("last");
        idFilter.setInputFormat(data);
        data = idFilter.useFilter(data, idFilter);
        return data;
    }

    /**
     * Parse a determinate {@link AssociationRule} into a {@link Suggestion}
     * @param rule
     * @return
     */
    private Suggestion parseRule(AssociationRule rule) {
        Suggestion suggestion = null;
        Collection<Item> premiseRule = rule.getPremise();
        Collection<Item> consequenceRule = rule.getConsequence();
        String[] premise = parsePremise(premiseRule);
        String[] consequence = parseConsequence(consequenceRule);
        try {
            suggestion = new Suggestion(productService.findByName(premise[0]), stringToBoolean(premise[1]),
                    productService.findByName(consequence[0]), stringToBoolean(consequence[1]));
        }catch (Exception e){
            System.out.println("Rule parsing error");
        }
        return suggestion;
    }

    private boolean stringToBoolean(String s) throws Exception{
        if(s.toLowerCase().equals("t")){
            return true;
        } else if (s.toLowerCase().equals("f")){
            return false;
        }
        throw new ClassCastException();
    }

    private String[] parsePremise(Collection<Item> premise) {
        String cons = premise.toString().substring(1, premise.toString().length() - 1);
        String[] separate = cons.split("=");
        if (separate[1].equals("t")) {
            System.out.println("Si hay " + separate[0]);
        } else if (separate[1].equals("f")) {
            System.out.println("Si NO hay " + separate[0]);
        }
        return separate;
    }

    private String[] parseConsequence(Collection<Item> consequence) {
        String cons = consequence.toString().substring(1, consequence.toString().length() - 1);
        String[] separate = cons.split("=");
        if (separate[1].equals("t")) {
            System.out.println("colocar " + separate[0]);
        } else if (separate[1].equals("f")) {
            System.out.println("NO colocar " + separate[0]);
        }
        return separate;
    }

}

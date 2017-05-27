package org.siorven.recommender;

import org.siorven.model.*;
import org.siorven.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
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

    private Machine machine;

    private Timestamp finishDate;

    @Autowired
    ServletContext servletContext;

    @Autowired
    private ResourceService resourceService;

    @Autowired
    private IngredientService ingredientService;

    private static List<Product> productList;

    @Scheduled(fixedRate = 10000,initialDelay = 10000)
    public void probe() {
        System.out.println("STARTING PROBE!!!!");
        productList = productService.findAll();

        FastVector atts = new FastVector(productList.size());
        Vector attVals = new Vector();


        attVals.addElement("t");
        for (Product p : productList) {
            atts.add(new Attribute(p.getName(), attVals));
        }

        Instances data = new Instances("prueba", atts, 0);
        Random randomGenerator = new Random();
        for (int numInstances = 0; numInstances <= 10; numInstances++){
            Instance iExample = new DenseInstance(productList.size());
            for (int i = 0; i< productList.size(); i++){
                if(randomGenerator.nextBoolean()) {
                    iExample.setValue((Attribute) atts.elementAt(i), "t");
                }
            }
            data.add(iExample);
        }

        System.out.println(data);

        runApriori(data);

    }


    /**
     * Create a {@link Product} with a unique {@link Ingredient} and a unique {@link Resource} and add them into the database
     *
     * @param name
     * @return The product created.
     */
    private Product createSolidProduct(String name) {
        Resource resource = new Resource(name, ResourceType.ITEM);
        resourceService.saveOrUpdate(resource);
        Ingredient ingredient = new Ingredient(resource, 1);
        ingredientService.saveOrUpdate(ingredient);
        List<Ingredient> list = new ArrayList<>();
        list.add(ingredient);
        Product product = new Product(name, list);
        productService.saveOrUpdate(product);
        return product;
    }


    //@Scheduled(fixedRate = 1000000)
    public void runApriori(Instances outData) { //Instances outData, Machine machine
        this.machine = (Machine) machineService.findAll().get(0);
        ArffLoader loader = new ArffLoader();
        try {
            loader.setSource(new File(servletContext.getRealPath("/WEB-INF/data/dataset1.arff")));

            Instances data = outData;//loader.getDataSet();
            //System.out.println("\nHeader of dataset:\n");
            //System.out.println(new Instances(data, 0));

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
            finishDate = new Timestamp(new Date().getTime());
            List<Suggestion> suggestions = getSuggestionsFromAprioriRules(apriori);

            for (Suggestion suggestion : suggestions) {
                System.out.println(suggestion.toString(null,null,null));
                suggestionService.save(suggestion);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * Translate the results of the Apriori algorith into a list of suggestions.
     * TODO: Kontuz, Rule baten parseoan errorea badau puede cascar muy facilmente. Arreglau in behar da!!!!!
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
            suggestionList.add(parseRule(rule));
            System.out.println("");
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
     * Change the format of the ID attribute from numeric to nominal
     *
     * @param data instances generated from arff file
     * @return
     * @throws Exception
     */
    private Instances changeIdNumericToNominal(Instances data) throws Exception {
        Instances filteredData;
        NumericToNominal numToNomFilter = new NumericToNominal();
        numToNomFilter.setAttributeIndices("last");
        numToNomFilter.setInputFormat(data);
        filteredData = numToNomFilter.useFilter(data, numToNomFilter);
        return filteredData;
    }

    /**
     * Add ID attribute to de instances
     *
     * @param data
     * @return
     * @throws Exception
     */
    private Instances addIdFilter(Instances data) throws Exception {
        Instances filteredData;
        AddID idFilter = new AddID();
        idFilter.setIDIndex("last");
        idFilter.setInputFormat(data);
        filteredData = idFilter.useFilter(data, idFilter);
        return filteredData;
    }

    /**
     * Parse a determinate {@link AssociationRule} into a {@link Suggestion}
     *
     * @param rule
     * @return
     */
    private Suggestion parseRule(AssociationRule rule) {
        SuggestionAssociation suggestion = null;
        try {
            suggestion = new SuggestionAssociation(finishDate, machine);

            List<Statement> premises = parseStatements(rule.getPremise());
            List<Statement> consequences = parseStatements(rule.getConsequence());

            suggestion.setConsequenceList(consequences);
            suggestion.setPremiseList(premises);
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
                    stringToBoolean(ni.getItemValueAsString()));
            statementService.save(statement);
            statementList.add(statement);
        }
        return statementList;
    }
}

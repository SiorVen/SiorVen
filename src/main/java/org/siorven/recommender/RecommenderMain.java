package org.siorven.recommender;

import org.siorven.model.*;
import org.siorven.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import weka.core.*;

import java.sql.Timestamp;
import java.util.*;


/**
 * Created by joseb on 17/05/2017.
 */
@Component
public class RecommenderMain {

    public static final int DAY_IN_MILIS = 24*60*60*1000;
    public static final int WEEK_IN_MILIS = 7*DAY_IN_MILIS;
    public static final int MONTH_IN_MILIS = 30*DAY_IN_MILIS;

    @Autowired
    private ProductService productService;

    @Autowired
    private SaleService saleService;

    @Autowired
    private MachineService machineService;

    @Autowired
    private MachineProductService machineProductService;

    @Autowired
    private AprioriAssociation apriori;

    @Autowired
    private SuggestionService suggestionService;

    private static List<Product> productList;

    private List<Sale> machineLastSales;

    private List<Sale> allSales;

    private List<Machine> machineList;

    private Timestamp now;


    @Scheduled(fixedRate = 10000,initialDelay = 10000 )
    public void probe() {
        System.out.println("STARTING PROBE!!!!");
        now = new Timestamp(new Date().getTime());
        machineList = machineService.findAll();
        productList = productService.findAll();

        createSales();
        allSales = saleService.getAllSales();


        for(Machine machine : machineList) {
            HashMap<Integer,Integer> machineProductQuantity = prepareDataForMaxMin(machine);
            generateMaxMinSuggestions(machine,machineProductQuantity);
        }

        Instances data = generateDataForApriori();
        apriori.runApriori(data);


    }

    private void generateMaxMinSuggestions(Machine machine, HashMap<Integer,Integer> machineProductQuantity) {
        Map.Entry<Integer, Integer> maxEntry = null;
        Map.Entry<Integer, Integer> minEntry = null;

        for (Map.Entry<Integer, Integer> entry : machineProductQuantity.entrySet())
        {
            if (maxEntry == null || entry.getValue().compareTo(maxEntry.getValue()) > 0)
            {
                maxEntry = entry;
            }
            if (minEntry == null || entry.getValue().compareTo(minEntry.getValue()) < 0)
            {
                minEntry = entry;
            }
        }
        Product maxProduct = productService.findById(maxEntry.getKey());
        Product minProduct = productService.findById(minEntry.getKey());
        Suggestion maxMinSug = new SuggestionStatistic(now,machine,maxProduct,minProduct);
        maxMinSug.toString(null,null,null);
        suggestionService.save(maxMinSug);
    }

    private HashMap<Integer,Integer> prepareDataForMaxMin(Machine machine) {
        Timestamp weekBefore = new Timestamp(now.getTime()-WEEK_IN_MILIS);
        HashMap<Integer,Integer> machineProductQuantity = new HashMap<>();
        machineLastSales = saleService.getSalesFromMachineFromWeek(weekBefore,now,machine);
        for (Sale sale : machineLastSales) {
            Product p = sale.getProduct().getProduct();
            if(machineProductQuantity.containsKey(p.getId())) {
                Integer sumQnty = machineProductQuantity.get(p.getId())+sale.getQuantity();
                machineProductQuantity.replace(p.getId(),sumQnty);
            } else {
                machineProductQuantity.put(p.getId(),sale.getQuantity());
            }
        }

        return machineProductQuantity;
    }

    private Instances generateDataForApriori() {
        FastVector atts = new FastVector(productList.size());
        Vector attVals = new Vector();


        attVals.addElement("t");
        for (Product p : productList) {
            atts.add(new Attribute(p.getName(), attVals));
        }

        Instances data = new Instances("prueba", atts, 0);

        getDataFromDatabase();

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
        return data;
    }

    private void getDataFromDatabase() {
        Timestamp monthBefore = new Timestamp(now.getTime()-MONTH_IN_MILIS);
        for(Machine machine : machineList){
            List<Sale> machineDaySale = saleService.getSalesFromMachineFromWeek(monthBefore,now,machine);
        }
    }

    private void createSales() {
        for(int i = 0; i<2; i++) {
            Sale sale = new Sale(new Timestamp(now.getTime()-10000), (MachineProduct) machineProductService.findAll().get(0), 1);
            saleService.save(sale);

            sale = new Sale(new Timestamp(now.getTime() - 10000), (MachineProduct) machineProductService.findAll().get(1), 5);
            saleService.save(sale);

            sale = new Sale(new Timestamp(now.getTime() - 10000), (MachineProduct) machineProductService.findAll().get(2), 20);
            saleService.save(sale);
        }


    }

}

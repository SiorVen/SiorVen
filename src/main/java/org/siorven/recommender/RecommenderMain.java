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

    public static final int DAY_IN_MILIS = 24 * 60 * 60 * 1000;
    public static final int WEEK_IN_MILIS = 7 * DAY_IN_MILIS;
    public static final int SUCCESS_RATE = 10;
    public static final int MIN_INSTANCES_FOR_GOOD_SUGGESTIONS = 30;
    public static final double MIN_RATE = 0.5;
    public static final double MAX_RATE = 1.5;

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


    @Scheduled(initialDelay = 10000, fixedDelay = DAY_IN_MILIS)
    public void probe() {
        System.out.println("STARTING PROBE!!!!");
        now = new Timestamp(new Date().getTime());
        machineList = machineService.findAll();
        productList = productService.findAll();

        createSales();
        allSales = saleService.getAllSales();

        Timestamp weekBefore = new Timestamp(now.getTime() - WEEK_IN_MILIS);
        for (Machine machine : machineList) {
            HashMap<Integer, Double> machineProductQuantity = countProductOfSales(saleService.getSalesFromMachineBetweenDates(weekBefore, now, machine));
            if (machineProductQuantity.size() > 0) {
                generateMaxMinSuggestions(machine, machineProductQuantity);
            }
        }

        Instances data = generateDataForApriori();
        if (data.size() > MIN_INSTANCES_FOR_GOOD_SUGGESTIONS) {
            apriori.runApriori(data);
        }


    }

    private void generateMaxMinSuggestions(Machine machine, HashMap<Integer, Double> machineProductQuantity) {
        int totalSales = 0;
        double mediaSales;
        for (Map.Entry<Integer, Double> entry : machineProductQuantity.entrySet()) {
            totalSales += entry.getValue();
        }
        List<MachineProduct> products = machineProductService.findByMachine(machine);
        mediaSales = totalSales / ((double) products.size());
        for (MachineProduct mp : products){

            if(machineProductQuantity.containsKey(mp.getProduct().getId())){
                Double saleQnt = machineProductQuantity.get(mp.getProduct().getId());
                if (saleQnt.compareTo(mediaSales * MAX_RATE) > 0) {
                    Suggestion maxMinSug = new SuggestionStatistic(now, machine, mp.getProduct(),SuggestionStatistic.MAX, 10);
                    suggestionService.save(maxMinSug);
                }
                if (saleQnt.compareTo(mediaSales * MIN_RATE) < 0) {
                    Suggestion maxMinSug = new SuggestionStatistic(now, machine, mp.getProduct(),SuggestionStatistic.MIN, 5);
                    suggestionService.save(maxMinSug);
                }
            } else {
                Suggestion maxMinSug = new SuggestionStatistic(now, machine, mp.getProduct(),SuggestionStatistic.MIN, 10);
                suggestionService.save(maxMinSug);
            }
        }
    }

    private HashMap<Integer, Double> countProductOfSales(List<Sale> saleList) {
        HashMap<Integer, Double> machineProductQuantity = new HashMap<>();
        for (Sale sale : saleList) {
            Product p = sale.getProduct().getProduct();
            if (machineProductQuantity.containsKey(p.getId())) {
                Double sumQnty = machineProductQuantity.get(p.getId()) + sale.getQuantity();
                machineProductQuantity.replace(p.getId(), sumQnty);
            } else {
                Double q = (double) sale.getQuantity();
                machineProductQuantity.put(p.getId(), q);
            }
        }

        return machineProductQuantity;
    }

    private Instances generateDataForApriori() {
        FastVector atts = new FastVector(productList.size());
        ArrayList attVals = new ArrayList();

        attVals.add("t");
        for (Product p : productList) {
            atts.add(new Attribute(p.getName(), attVals));
        }

        Instances data = getDataFromDatabase(atts);

        System.out.println(data);
        return data;
    }

    private Instances getDataFromDatabase(FastVector atts) {
        Instances data = new Instances("prueba", atts, 0);

        for (Machine machine : machineList) {
            for (int i = 0; i < 30; i++) {
                Timestamp from = new Timestamp(now.getTime() - (DAY_IN_MILIS * (i + 1)));
                Timestamp to = new Timestamp(now.getTime() - (DAY_IN_MILIS * i));
                List<Sale> machineDaySale = saleService.getSalesFromMachineBetweenDates(from, to, machine);
                if (!machineDaySale.isEmpty()) {
                    HashMap<Integer, Double> productSales = countProductOfSales(machineDaySale);
                    data.add(generateInstanceOfDay(atts, productSales));
                }
            }
        }
        return data;
    }

    private Instance generateInstanceOfDay(FastVector atts, HashMap<Integer, Double> productSales) {
        Instance iExample = new DenseInstance(productList.size());
        for (Map.Entry<Integer, Double> entry : productSales.entrySet()) {
            if (entry.getValue() > SUCCESS_RATE) {
                Product p = productService.findById(entry.getKey());
                for (int j = 0; j < atts.size(); j++) {
                    if (((Attribute) atts.get(j)).name().equalsIgnoreCase(p.getName())) {
                        iExample.setValue((Attribute) atts.elementAt(j), "t");
                        break;
                    }
                }
            }
        }
        return iExample;
    }

    private void createSales() {
        for (int j = 0; j < 50; j++) {
            for (int i = 0; i < 15; i++) {
                Sale sale = new Sale(new Timestamp(now.getTime() - (DAY_IN_MILIS * (j + 1))), (MachineProduct) machineProductService.findAll().get(0), 20);
                saleService.save(sale);

                sale = new Sale(new Timestamp(now.getTime() - (DAY_IN_MILIS * (j + 1))), (MachineProduct) machineProductService.findAll().get(1), 10);
                saleService.save(sale);

                sale = new Sale(new Timestamp(now.getTime() - (DAY_IN_MILIS * (j + 1))), (MachineProduct) machineProductService.findAll().get(2), 20);
                saleService.save(sale);
            }
        }
    }

}

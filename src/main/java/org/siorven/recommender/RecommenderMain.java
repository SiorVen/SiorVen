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
 * Class that is executed every day.
 * It generates suggestions.
 */
@Component
public class RecommenderMain {

    private static final int DAY_IN_MILIS = 24 * 60 * 60 * 1000;
    private static final int MIN_INSTANCES_FOR_GOOD_SUGGESTIONS = 10;

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

    @Autowired
    private ConfigParamService conf;

    @Autowired
    private IAService iaService;
    private List<Product> productList;

    private List<Machine> machineList;

    private Timestamp now;


    @Scheduled(initialDelay = 10000, fixedDelay = DAY_IN_MILIS)
    public void probe() {
        now = new Timestamp(new Date().getTime());
        machineList = machineService.findAll();
        productList = productService.findAll();

        createSales();
        try {
            Timestamp weekBefore = new Timestamp(now.getTime() - conf.getInt(ConfigParam.SUGG_MAXMIN_DAYPERIOD) * DAY_IN_MILIS);
            for (Machine machine : machineList) {
                HashMap<Integer, Double> machineProductQuantity = countProductOfSales(saleService.getSalesFromMachineBetweenDates(weekBefore, now, machine));
                if (machineProductQuantity.size() > 0) {
                    suggestionService.saveSuggestionList(iaService.generateMaxMinSuggestions(machine, machineProductQuantity, now));
                }
            }

            Instances data = generateDataForApriori();
            if (data.size() > MIN_INSTANCES_FOR_GOOD_SUGGESTIONS) {
                apriori.runApriori(data);
            }
        }catch (Exception e){
            e.printStackTrace();
        }


    }

    /**
     * Group the sales into product and count the quantity of sales for each product
     * @param saleList
     * @return
     */
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

    /**
     * Create data to run Apriori algorithm
     * @return
     */
    private Instances generateDataForApriori() {
        FastVector atts = new FastVector(productList.size());
        ArrayList attVals = new ArrayList();

        attVals.add("t");
        for (Product p : productList) {
            atts.add(new Attribute(p.getName(), attVals));
        }

        return getDataFromDatabase(atts);

    }

    /**
     * Get data from database and generate instances for Apriori
     * @param atts list of attributes that represent products
     * @return
     */
    private Instances getDataFromDatabase(FastVector atts) {
        Instances data = new Instances("prueba", atts, 0);

        for (Machine machine : machineList) {
            for (int i = 0; i < conf.getInt(ConfigParam.SUGG_APRIORI_DAYPERIOD); i++) {
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

    /**
     * Create an instace from the sales taking into account a confidentiality parameter
     * @param atts  list of attributes tha represent products
     * @param productSales list of product and the quantity of the sales per day for each of them
     * @return
     */
    private Instance generateInstanceOfDay(FastVector atts, HashMap<Integer, Double> productSales) {
        Instance iExample = new DenseInstance(productList.size());
        for (Map.Entry<Integer, Double> entry : productSales.entrySet()) {
            if (entry.getValue() > conf.getInt(ConfigParam.SUGG_APRIORI_SUCCESSALES)) {
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

    /**
     * Function to simulate some sales for a demo.
     * This function must be deleted when integrating in the real product
     */
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

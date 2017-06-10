package org.siorven.services;

import org.siorven.dao.SaleDao;
import org.siorven.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

/**
 * Access logic for the sales
 */
@Service
public class SaleService {

    @Autowired
    private SaleDao saleDao;

    @Autowired
    private MailService mailService;

    @Autowired
    private MachineResourceService machineResourceService;

    @Autowired
    private MachineIngredientService machineIngredientService;

    /**
     * Creates a sale
     *
     * @param sale           The sale
     * @param machineProduct The machine product that was sold
     */
    public void generateSale(Sale sale, MachineProduct machineProduct) {
        spendResources(machineProduct);
        saleDao.save(sale);
    }

    /**
     * Save sale into database
     *
     * @param sale The sale
     */
    public void save(Sale sale) {
        saleDao.save(sale);
    }

    /**
     * Update sale
     *
     * @param sale The sale
     */
    public void edit(Sale sale) {
        saleDao.edit(sale);
    }

    /**
     * Save an sale if it is new, or update it if it exists
     *
     * @param sale The sale
     */
    public void editOrSave(Sale sale) {
        saleDao.editOrSave(sale);
    }

    /**
     * Delete sale
     *
     * @param sale The sale
     */
    public void delete(Sale sale) {
        saleDao.delete(sale.getId());
    }

    /**
     * Get sale that has a given id
     *
     * @param id The sale id
     * @return null if the sale wasn't found
     */
    public Sale findById(int id) {
        return saleDao.findById(id);
    }

    /**
     * Gets all the sales
     *
     * @return The sale list
     */
    public List<Sale> getAllSales() {
        return saleDao.getAllSales();
    }

    /**
     * Gets the sales on a machine in a period
     *
     * @param fromDate The date the period begins - inclusive
     * @param toDate   The date the period ends - Exclusive
     * @param machine  The machine
     * @return The list of sales
     */
    public List<Sale> getSalesFromMachineBetweenDates(Timestamp fromDate, Timestamp toDate, Machine machine) {
        return saleDao.getSalesFromMachineBetweenDates(fromDate, toDate, machine);
    }

    /**
     * Logic executed to spend the resources after the sale of a product
     *
     * @param mp The spent machine product
     */
    private void spendResources(MachineProduct mp) {
        List<MachineIngredient> recipe = machineIngredientService.getRecipeFromMachineProduct(mp);
        for (MachineIngredient mi : recipe) {
            MachineResource mr = mi.getResource();
            int quantity = mr.getQuantity() - mi.getQty();
            if (quantity <= 0)
                mailService.notify(mr);
            mr.setQuantity(quantity);
            machineResourceService.edit(mr);
        }
    }

}

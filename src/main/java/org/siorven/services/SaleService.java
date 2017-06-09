package org.siorven.services;

import org.siorven.dao.SaleDao;
import org.siorven.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by Gorospe on 25/05/2017.
 */
@Service
public class SaleService {

    @Autowired
    private SaleDao saleDao;

    @Autowired
    private transient MailService mailService;

    @Autowired
    private transient MachineResourceService machineResourceService;

    @Autowired
    private transient MachineIngredientService machineIngredientService;

    public void generateSale(Sale sale, MachineProduct machineProduct) {
        spendResources(machineProduct);
        saleDao.save(sale);
    }

    public void save(Sale sale){
        saleDao.save(sale);
    }

    public void edit(Sale sale) {
        saleDao.edit(sale);
    }

    public void editOrSave(Sale sale) {
        saleDao.editOrSave(sale);
    }

    public void delete(Sale sale) {
        saleDao.delete(sale.getId());
    }

    public Sale findById(int id) {
        return saleDao.findById(id);
    }

    public List getAllSales() {
        return saleDao.getAllSales();
    }

    public List getSalesFromMachineBetweenDates(Timestamp fromDate, Timestamp toDate, Machine machine) {
        return saleDao.getSalesFromMachineBetweenDates(fromDate, toDate, machine);
    }

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

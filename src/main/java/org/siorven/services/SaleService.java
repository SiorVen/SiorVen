package org.siorven.services;

import org.siorven.dao.SaleDao;
import org.siorven.model.Machine;
import org.siorven.model.Sale;
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

    public void save(Sale sale) {
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

    public List getSalesFromMachineFromDay(Timestamp date, Machine machine) {
        return saleDao.getSalesFromMachineFromDay(date, machine);
    }

}

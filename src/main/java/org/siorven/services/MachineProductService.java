package org.siorven.services;

import org.siorven.dao.MachineProductDao;
import org.siorven.model.MachineProduct;
import org.siorven.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Gorospe on 24/05/2017.
 */
@Service
public class MachineProductService {

    @Autowired
    private MachineProductDao machineProductDao;

    public void save(MachineProduct machineProduct) {
        machineProductDao.saveMachineProduct(machineProduct);
    }

    public void edit(MachineProduct machineProduct) {
        machineProductDao.editMachineProduct(machineProduct);
    }

    public void saveOrUpdate(MachineProduct machineProduct) {
        machineProductDao.editOrSaveMachineProduct(machineProduct);
    }

    public void delete(MachineProduct machineProduct) {
        machineProductDao.deleteMachineProduct(machineProduct.getId());
    }

    public MachineProduct findById(int id) {
        return machineProductDao.findById(id);
    }

    public List findAll() {
        return machineProductDao.findAll();
    }

    public List<MachineProduct> findByProduct(Product p) {
        return machineProductDao.findByProduct(p);
    }
}

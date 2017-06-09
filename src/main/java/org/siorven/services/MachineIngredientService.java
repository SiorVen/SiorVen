package org.siorven.services;

import org.siorven.dao.MachineIngredientDao;
import org.siorven.model.MachineIngredient;
import org.siorven.model.MachineProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Gorospe on 24/05/2017.
 */
@Service
public class MachineIngredientService {

    @Autowired
    private MachineIngredientDao machineIngredientDao;

    public void save(MachineIngredient machineIngredient) {
        machineIngredientDao.saveMachineIngredient(machineIngredient);
    }

    public void edit(MachineIngredient machineIngredient) {
        machineIngredientDao.editMachineIngredient(machineIngredient);
    }

    public void saveOrUpdate(MachineIngredient machineIngredient) {
        machineIngredientDao.editOrSaveMachineIngredient(machineIngredient);
    }

    public void delete(MachineIngredient machineIngredient) {
        machineIngredientDao.deleteMachineIngredient(machineIngredient.getId());
    }

    public MachineIngredient findById(int id) {
        return machineIngredientDao.findById(id);
    }

    public List findAll() {
        return machineIngredientDao.findAll();
    }

    public List getRecipeFromMachineProduct(MachineProduct mp){
        return machineIngredientDao.getRecipeFromMachineProduct(mp);
    }
}

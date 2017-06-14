package org.siorven.services;

import org.siorven.dao.MachineIngredientDao;
import org.siorven.model.MachineIngredient;
import org.siorven.model.MachineProduct;
import org.siorven.model.MachineResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Access logic provider for Machine Ingredients
 */
@Service
public class MachineIngredientService {

    @Autowired
    private MachineIngredientDao machineIngredientDao;

    /**
     * Save machine ingredient into database
     *
     * @param machineIngredient The machine ingredient
     */
    public void save(MachineIngredient machineIngredient) {
        machineIngredientDao.saveMachineIngredient(machineIngredient);
    }

    /**
     * Update machine ingredient
     *
     * @param machineIngredient The machine ingredient
     */
    public void edit(MachineIngredient machineIngredient) {
        machineIngredientDao.editMachineIngredient(machineIngredient);
    }

    /**
     * Save a machine ingredient if it is new, or update it if it exists
     *
     * @param machineIngredient The machine ingredient
     */
    public void saveOrUpdate(MachineIngredient machineIngredient) {
        machineIngredientDao.editOrSaveMachineIngredient(machineIngredient);
    }

    /**
     * Delete machine ingredient
     *
     * @param machineIngredient The machine ingredient
     */
    public void delete(MachineIngredient machineIngredient) {
        machineIngredientDao.deleteMachineIngredient(machineIngredient.getId());
    }

    /**
     * Get machine ingredient that has a given id
     *
     * @param id The id of the machine ingredient
     * @return null if the machine ingredient wasn't found
     */
    public MachineIngredient findById(int id) {
        return machineIngredientDao.findById(id);
    }

    /**
     * Gets all the machine ingredients
     *
     * @return The machine ingredient list
     */
    public List<MachineIngredient> findAll() {
        return machineIngredientDao.findAll();
    }

    /**
     * Gets the recipe (list of ingredients) of a MachineProduct
     *
     * @param mp The {@link MachineProduct}
     * @return The list of Machine ingredients
     */
    public List<MachineIngredient> getRecipeFromMachineProduct(MachineProduct mp) {
        return machineIngredientDao.getRecipeFromMachineProduct(mp);
    }

    public MachineIngredient findByMachineResource(MachineResource resource) {
        return machineIngredientDao.findByMachineResource(resource);
    }
}

package org.siorven.dao;

import org.siorven.model.MachineIngredient;
import org.siorven.model.MachineProduct;
import org.siorven.model.MachineResource;
import org.siorven.model.validacion.PersistenceGroup;
import org.springframework.validation.annotation.Validated;

import java.util.List;

/**
 * Data access layer for the ingredients in machine
 *
 * @see MachineIngredient
 */
public interface MachineIngredientDao {

    /**
     * Stores a machine ingredient on the persistence provider
     *
     * @param machineIngredient The machine ingredient
     */
    void saveMachineIngredient(@Validated(PersistenceGroup.class) MachineIngredient machineIngredient);

    /**
     * Edits a machine ingredient on the persistence provider
     *
     * @param machineIngredient The machine ingredient
     */
    void editMachineIngredient(@Validated(PersistenceGroup.class) MachineIngredient machineIngredient);

    /**
     * Edits or saves the given machine ingredient on the persistence provider
     *
     * @param machineIngredient The machine ingredient
     */
    void editOrSaveMachineIngredient(@Validated(PersistenceGroup.class) MachineIngredient machineIngredient);

    /**
     * Deletes a machine ingredient from the persistence provider
     *
     * @param id The id of the machine ingredient
     */
    void deleteMachineIngredient(int id);

    /**
     * Finds the persisted machine Ingredient with the given ID
     *
     * @param id The id of the persisted machine
     * @return The machine ingredient or null if it wasn't found
     */
    MachineIngredient findById(int id);

    /**
     * Find all the persisted machine ingredients
     *
     * @return the machine ingredient list
     */
    List<MachineIngredient> findAll();

    List getRecipeFromMachineProduct(MachineProduct machineProduct);

    MachineIngredient findByMachineResource(MachineResource resource);
}

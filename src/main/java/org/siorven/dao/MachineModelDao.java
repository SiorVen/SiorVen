package org.siorven.dao;

import org.siorven.model.MachineModel;
import org.siorven.model.validacion.PersistenceGroup;
import org.springframework.validation.annotation.Validated;

import java.util.List;

/**
 * Data access layer for machine model
 *
 * @see MachineModel
 */
public interface MachineModelDao {

    /**
     * Persists the given machine model
     *
     * @param machineModel The machine model
     */
    void saveModel(@Validated(PersistenceGroup.class) MachineModel machineModel);

    /**
     * Edits a machine model on the persistence provider
     *
     * @param machineModel The machine model
     */
    void editModel(@Validated(PersistenceGroup.class) MachineModel machineModel);

    /**
     * Edits or saves the given machine model on the persistence provider
     *
     * @param machineModel The machine model
     */
    void editOrSaveModel(@Validated(PersistenceGroup.class) MachineModel machineModel);

    /**
     * Deletes a machine model from the persistence provider
     *
     * @param id The id of the machine model
     */
    void deleteModel(int id);

    /**
     * Finds the persisted machine model with the given ID
     *
     * @param id The id of the persisted model
     * @return The model or null if it wasn't found
     */
    MachineModel findById(int id);

    /**
     * Find all the persisted machine models
     *
     * @return the machine models list
     */
    List<MachineModel> findAll();
}

package org.siorven.dao;

import org.siorven.model.Machine;
import org.siorven.model.MachineIngredient;
import org.siorven.model.MachineResource;
import org.siorven.model.validacion.PersistenceGroup;
import org.springframework.validation.annotation.Validated;

import java.util.List;

/**
 * Data access layer for the resources in machine
 *
 * @see MachineIngredient
 */
public interface MachineResourceDao {

    /**
     * Persists a Resource in machine
     *
     * @param r The Resource in machine to be persisted
     */
    void save(@Validated(PersistenceGroup.class) MachineResource r);

    /**
     * Updates a persisted Resource in machine
     *
     * @param r The Resource in machine to be persisted
     */
    void edit(@Validated(PersistenceGroup.class) MachineResource r);

    /**
     * Updates or persists a Resource in machine
     *
     * @param r The Resource in machine to be persisted
     */
    void editOrSave(@Validated(PersistenceGroup.class) MachineResource r);

    /**
     * Deletes a persisted Resource in machine
     *
     * @param id The id of the Resource in machine to be deleted
     */
    void delete(int id);

    /**
     * Finds a persisted Resource in machine by its id
     *
     * @param id The id of the Resource in machine to be searched
     * @return The Resource in machine or null if it wasn't found
     */
    MachineResource findById(int id);

    /**
     * Returns all the persisted Resources in machine
     *
     * @return A {@link List} conta1ining all the Resources in machine
     */
    List<MachineResource> getAllResources();

    /**
     * Finds the machine resources on the given machine
     *
     * @param m The machine
     * @return The list of machine resources
     */
    List<MachineResource> findByMachine(Machine m);
}

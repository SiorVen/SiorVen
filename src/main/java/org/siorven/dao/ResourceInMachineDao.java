package org.siorven.dao;

import org.siorven.model.ResourceInMachine;
import org.siorven.model.validacion.PersistenceGroup;
import org.springframework.validation.annotation.Validated;

import java.util.List;

/**
 * Created by Andoni on 24/05/2017.
 */
public interface ResourceInMachineDao {

    /**
     * Persists a Resource in machine
     * @param r The Resource in machine to be persisted
     */
    void save(@Validated(PersistenceGroup.class) ResourceInMachine r);

    /**
     * Updates a persisted Resource in machine
     * @param r The Resource in machine to be persisted
     */
    void edit(@Validated(PersistenceGroup.class) ResourceInMachine r);

    /**
     * Updates or persists a Resource in machine
     * @param r The Resource in machine to be persisted
     */
    void editOrSave(@Validated(PersistenceGroup.class) ResourceInMachine r);

    /**
     * Deletes a persisted Resource in machine
     * @param id The id of the Resource in machine to be deleted
     */
    void delete(String id);

    /**
     * Finds a persisted Resource in machine by its id
     * @param id The id of the Resource in machine to be searched
     * @return The Resource in machine or null if it wasn't found
     */
    ResourceInMachine findById(String id);

    /**
     * Returns all the persisted Resources in machine
     * @return A {@link List} conta1ining all the Resources in machine
     */
    List getAllResources();

}

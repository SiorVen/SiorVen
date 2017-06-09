package org.siorven.dao;

import org.siorven.model.Machine;
import org.siorven.model.validacion.PersistenceGroup;
import org.springframework.validation.annotation.Validated;

import java.util.List;

/**
 * Data acces layer for machines
 *
 * @see Machine
 */
public interface MachineDao {
    /**
     * Persists the given machine
     *
     * @param machine The machine to be persisted
     */
    void saveMachine(@Validated(PersistenceGroup.class) Machine machine);

    /**
     * Edits the given machine on the persistence provider
     *
     * @param machine The modified machine
     */
    void editMachine(@Validated(PersistenceGroup.class) Machine machine);

    /**
     * Edits or saves the given machine may it be already persisted or not
     *
     * @param machine The machine
     */
    void editOrSaveMachine(@Validated(PersistenceGroup.class) Machine machine);

    /**
     * Deletes the given machine from the persistence provider
     *
     * @param id
     */
    void deleteMachine(int id);

    /**
     * Find a persisted machine with the given id
     *
     * @param id The id of the machine
     * @return The machine or null if it was not found
     */
    Machine findById(int id);

    /**
     * Gets all the persisted machines on the persistence provider
     *
     * @return The list of machines
     */
    List<Machine> findAll();
}

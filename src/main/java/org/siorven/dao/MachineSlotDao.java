package org.siorven.dao;

import org.siorven.model.Machine;
import org.siorven.model.MachineSlot;
import org.siorven.model.validacion.PersistenceGroup;
import org.springframework.validation.annotation.Validated;

import java.util.List;

/**
 * Data access layer for the slots in machine
 *
 * @see MachineSlot
 */
public interface MachineSlotDao {

    /**
     * Stores a machine slot on the persistence provider
     *
     * @param machineSlot The machine slot
     */
    void saveMachineSlot(@Validated(PersistenceGroup.class) MachineSlot machineSlot);

    /**
     * Edits a machine slot on the persistence provider
     *
     * @param machineSlot The machine slot
     */
    void editMachineSlot(@Validated(PersistenceGroup.class) MachineSlot machineSlot);

    /**
     * Edits or saves the given machine slot on the persistence provider
     *
     * @param machineSlot The machine slot
     */
    void editOrSaveMachineSlot(@Validated(PersistenceGroup.class) MachineSlot machineSlot);

    /**
     * Deletes a machine slot from the persistence provider
     *
     * @param id The id of the machine slot
     */
    void deleteMachineSlot(int id);

    /**
     * Finds the persisted machine Slot with the given ID
     *
     * @param id The id of the persisted machine
     * @return The machine slot or null if it wasn't found
     */
    MachineSlot findById(int id);

    /**
     * Finds the slots on the given machine
     *
     * @param m The machine
     * @return The list of machine slots
     */
    List<MachineSlot> findByMachine(Machine m);

    /**
     * Find all the persisted machine slots
     *
     * @return the machine slot list
     */
    List<MachineSlot> findAll();


}

package org.siorven.services;

import org.siorven.dao.MachineSlotDao;
import org.siorven.model.Machine;
import org.siorven.model.MachineSlot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Access logic provider for Machine Slots
 */
@Service
public class MachineSlotService {

    @Autowired
    private MachineSlotDao machineSlotDao;

    @Autowired
    private MachineService machineService;

    /**
     * Save machine slot into database
     *
     * @param machineSlot The machine slot
     */
    public void save(MachineSlot machineSlot) {
        machineSlotDao.saveMachineSlot(machineSlot);
    }

    /**
     * Update machine slot
     *
     * @param machineSlot The machine slot
     */
    public void edit(MachineSlot machineSlot) {
        machineSlotDao.editMachineSlot(machineSlot);
    }

    /**
     * Save a machine slot if it is new, or update it if it exists
     *
     * @param machineSlot The machine slot
     */
    public void saveOrUpdate(MachineSlot machineSlot) {
        machineSlotDao.editOrSaveMachineSlot(machineSlot);
    }

    /**
     * Delete machine slot
     *
     * @param machineSlot The machine slot
     */
    public void delete(MachineSlot machineSlot) {
        machineSlotDao.deleteMachineSlot(machineSlot.getId());
    }

    /**
     * Get machine slot that has a given id
     *
     * @param id The id of the machine slot
     * @return null if the machine slot wasn't found
     */
    public MachineSlot findById(int id) {
        return machineSlotDao.findById(id);
    }

    /**
     * Gets the machine slots on the machine with the given id
     *
     * @param id The id of the machine
     * @return The list of machine Slots
     */
    public List<MachineSlot> findByMachineId(int id) {
        Machine m = machineService.findById(id);
        return machineSlotDao.findByMachine(m);
    }

    /**
     * Gets all the machine slots
     *
     * @return The machine slot list
     */
    public List<MachineSlot> findAll() {
        return machineSlotDao.findAll();
    }
}

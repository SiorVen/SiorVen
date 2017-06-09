package org.siorven.services;

import org.siorven.dao.MachineDao;
import org.siorven.model.Machine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Machine data access logic
 */
@Service
public class MachineService {

    @Autowired
    private MachineDao machineDao;

    /**
     * Save machine
     *
     * @param machine The machine
     */
    public void save(Machine machine) {
        machineDao.saveMachine(machine);
    }

    /**
     * Edit machine
     *
     * @param machine The machine
     */
    public void edit(Machine machine) {
        machineDao.editMachine(machine);
    }

    /**
     * Save a machine if it is new or update it if it exists
     *
     * @param machine The machine
     */
    public void saveOrUpdate(Machine machine) {
        machineDao.editOrSaveMachine(machine);
    }

    /**
     * Delete machine
     *
     * @param machine The machine
     */
    public void delete(Machine machine) {
        machineDao.deleteMachine(machine.getId());
    }

    /**
     * Get machine that has a given id
     *
     * @param id The id of the machine
     * @return null if the machine wasn't found
     */
    public Machine findById(int id) {
        return machineDao.findById(id);
    }

    /**
     * Finds all the machines
     *
     * @return The list containing all the machines
     */
    public List<Machine> findAll() {
        return machineDao.findAll();
    }
}

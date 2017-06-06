package org.siorven.services;

import org.siorven.dao.MachineResourceDao;
import org.siorven.model.Machine;
import org.siorven.model.MachineResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service to provide access logic to the user data
 */
@Service
public class MachineResourceService {

    /**
     * Data acces object for the user table on the database
     */
    @Autowired
    private MachineResourceDao machineResourceDao;

    /**
     * Data acces object for the user table on the database
     */
    @Autowired
    private MachineService machineService;

    /**
     * Saves a resource in machine to the database.
     *
     * @param r The resource in machine to be saved
     */
    public void save(MachineResource r) {
        machineResourceDao.save(r);
    }

    /**
     * Saves or updates a MachineResource on the database
     *
     * @param s MachineResource to be saved or updated
     */
    public void saveOrUpdate(MachineResource s) {
        machineResourceDao.editOrSave(s);
    }

    /**
     * Updates a MachineResource on the database
     *
     * @param s MachineResource to be updated
     */
    public void edit(MachineResource s) {
        machineResourceDao.edit(s);
    }

    /**
     * Deletes a MachineResource from the database
     *
     * @param id The id of the MachineResource to be deleted from the database
     */
    public void delete(int id) {
        machineResourceDao.delete(id);
    }

    /**
     * Searches for the MachineResource with the given id
     *
     * @param id The id of the requested MachineResource
     * @return The MachineResource or null if it wasn't found
     */
    public MachineResource findById(int id) {
        return machineResourceDao.findById(id);
    }

    /**
     * Returns all the MachineResource on the database
     *
     * @return The list of MachineResource
     */
    public List findAll() {
        return machineResourceDao.getAllResources();
    }

    public List findByMachine(int id) {
        Machine m = machineService.findById(id);
        return machineResourceDao.findByMachine(m);
    }
}

package org.siorven.services;

import org.siorven.dao.ResourceInMachineDao;
import org.siorven.model.ResourceInMachine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service to provide access logic to the user data
 */
@Service
public class ResourceInMachineService {

    /**
     * Data acces object for the user table on the database
     */
    @Autowired
    private ResourceInMachineDao resourceInMachineDao;

    /**
     * Saves a resource in machine to the database.
     *
     * @param r The resource in machine to be saved
     */
    public void save(ResourceInMachine r){
        resourceInMachineDao.save(r);
    }

    /**
     * Saves or updates a ResourceInMachine on the database
     *
     * @param s ResourceInMachine to be saved or updated
     */
    public void saveOrUpdate(ResourceInMachine s){
        resourceInMachineDao.editOrSave(s);
    }

    /**
     * Updates a ResourceInMachine on the database
     *
     * @param s ResourceInMachine to be updated
     */
    public void edit(ResourceInMachine s) {
        resourceInMachineDao.edit(s);
    }

    /**
     * Deletes a ResourceInMachine from the database
     * @param id The id of the ResourceInMachine to be deleted from the database
     */
    public void delete(String id){
        resourceInMachineDao.delete(id);
    }

    /**
     * Searches for the ResourceInMachine with the given id
     * @param id The id of the requested ResourceInMachine
     * @return The ResourceInMachine or null if it wasn't found
     */
    public ResourceInMachine findById(String id) {
        return resourceInMachineDao.findById(id);
    }

    /**
     * Returns all the ResourceInMachine on the database
     * @return The list of ResourceInMachine
     */
    public List findAll() {
        return resourceInMachineDao.getAllResources();
    }
}

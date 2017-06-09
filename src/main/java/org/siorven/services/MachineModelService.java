package org.siorven.services;

import org.siorven.dao.MachineModelDao;
import org.siorven.model.MachineModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Data access logic for Machine models
 */
@Service
public class MachineModelService {

    @Autowired
    private MachineModelDao machineModelDao;

    /**
     * Save machine model into database
     *
     * @param machineModel The machine model
     */
    public void save(MachineModel machineModel) {
        machineModelDao.saveModel(machineModel);
    }

    /**
     * Update machine model
     *
     * @param machineModel The machine model
     */
    public void edit(MachineModel machineModel) {
        machineModelDao.editModel(machineModel);
    }

    /**
     * Save a machine model if it is new, or update it if it exists
     *
     * @param machineModel The machine model
     */
    public void saveOrUpdate(MachineModel machineModel) {
        machineModelDao.editOrSaveModel(machineModel);
    }

    /**
     * Delete machine model
     *
     * @param machineModel The machine model
     */
    public void delete(MachineModel machineModel) {
        machineModelDao.deleteModel(machineModel.getId());
    }

    /**
     * Get machine model that has a given id
     *
     * @param id The id of the machine model
     * @return null if the machine model wasn't found
     */
    public MachineModel findById(int id) {
        return machineModelDao.findById(id);
    }

    /**
     * Gets all the machine models
     *
     * @return The machine model list
     */
    public List<MachineModel> findAll() {
        return machineModelDao.findAll();
    }
}

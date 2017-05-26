package org.siorven.services;

import org.siorven.dao.MachineModelDao;
import org.siorven.model.MachineModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Gorospe on 24/05/2017.
 */
@Service
public class MachineModelService {

    @Autowired
    private MachineModelDao machineModelDao;

    public void save(MachineModel machineModel) {
        machineModelDao.saveModel(machineModel);
    }

    public void edit(MachineModel machineModel) {
        machineModelDao.editModel(machineModel);
    }

    public void savaOrUpdate(MachineModel machineModel) {
        machineModelDao.editOrSaveModel(machineModel);
    }

    public void delete(MachineModel machineModel) {
        machineModelDao.deleteModel(machineModel.getId());
    }

    public MachineModel findById(int id) {
        return machineModelDao.findById(id);
    }

    public List findAll() {
        return machineModelDao.findAll();
    }
}

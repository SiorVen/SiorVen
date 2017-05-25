package org.siorven.services;

import org.siorven.dao.MachineDao;
import org.siorven.model.Machine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Gorospe on 24/05/2017.
 */
@Service
public class MachineService {

    @Autowired
    private MachineDao machineDao;

    public void save(Machine machine) {
        machineDao.saveMachine(machine);
    }

    public void edit(Machine machine) {
        machineDao.editMachine(machine);
    }

    public void saveOrUpdate(Machine machine) {
        machineDao.editOrSaveMachine(machine);
    }

    public void delete(Machine machine) {
        machineDao.deleteMachine(machine.getId());
    }

    public Machine findById(int id) {
        return machineDao.findById(id);
    }

    public List findAll() {
        return machineDao.findAll();
    }
}

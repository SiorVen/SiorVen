package org.siorven.services;

import org.siorven.dao.MachineSlotDao;
import org.siorven.model.Machine;
import org.siorven.model.MachineSlot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Gorospe on 29/05/2017.
 */
@Service
public class MachineSlotService {

    @Autowired
    private MachineSlotDao machineSlotDao;

    @Autowired MachineService machineService;

    public void save(MachineSlot machineSlot) {
        machineSlotDao.saveMachineSlot(machineSlot);
    }

    public void edit(MachineSlot machineSlot) {
        machineSlotDao.editMachineSlot(machineSlot);
    }

    public void saveOrUpdate(MachineSlot machineSlot) {
        machineSlotDao.editOrSaveMachineSlot(machineSlot);
    }

    public void delete(MachineSlot machineSlot) {
        machineSlotDao.deleteMachineSlot(machineSlot.getId());
    }

    public MachineSlot findById(int id) {
        return machineSlotDao.findById(id);
    }

    public  List findByMachineId(int id){
        Machine m = machineService.findById(id);
        return machineSlotDao.findByMachineId(m);
    }

    public List findAll() {
        return machineSlotDao.findAll();
    }
}

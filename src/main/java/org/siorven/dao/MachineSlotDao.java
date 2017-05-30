package org.siorven.dao;

import org.siorven.model.MachineSlot;
import org.siorven.model.validacion.PersistenceGroup;
import org.springframework.validation.annotation.Validated;

import java.util.List;

/**
 * Created by Gorospe on 29/05/2017.
 */
public interface MachineSlotDao {


    void saveMachineSlot(@Validated(PersistenceGroup.class) MachineSlot machineSlot);

    void editMachineSlot(@Validated(PersistenceGroup.class) MachineSlot machineSlot);

    void editOrSaveMachineSlot(@Validated(PersistenceGroup.class) MachineSlot machineSlot);

    void deleteMachineSlot(int id);

    MachineSlot findById(int id);

    List findAll();


}

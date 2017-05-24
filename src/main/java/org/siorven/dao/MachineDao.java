package org.siorven.dao;

import org.siorven.model.Machine;
import org.siorven.model.validacion.PersistenceGroup;
import org.springframework.validation.annotation.Validated;

import java.util.List;

/**
 * Created by joseb on 24/05/2017.
 */
public interface MachineDao {

    void saveMachine(@Validated(PersistenceGroup.class) Machine machine);

    void editMachine(@Validated(PersistenceGroup.class) Machine machine);

    void editOrSaveMachine(@Validated(PersistenceGroup.class) Machine machine);

    void deleteMachine(int id);

    Machine findById(int id);

    List findAll();
}

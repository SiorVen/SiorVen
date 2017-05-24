package org.siorven.dao;

import org.siorven.model.MachineIngredient;
import org.siorven.model.validacion.PersistenceGroup;
import org.springframework.validation.annotation.Validated;

import java.util.List;

/**
 * Created by Gorospe on 24/05/2017.
 */
public interface MachineIngredientDao {

    void saveMachineIngredient(@Validated(PersistenceGroup.class) MachineIngredient machineIngredient);

    void editMachineIngredient(@Validated(PersistenceGroup.class) MachineIngredient machineIngredient);

    void editOrSaveMachineIngredient(@Validated(PersistenceGroup.class) MachineIngredient machineIngredient);

    void deleteMachineIngredient(int id);

    MachineIngredient findById(int id);

    List findAll();
}

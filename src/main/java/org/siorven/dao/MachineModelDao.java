package org.siorven.dao;

import org.siorven.model.MachineModel;
import org.siorven.model.validacion.PersistenceGroup;
import org.springframework.validation.annotation.Validated;

import java.util.List;

/**
 * Created by Gorospe on 24/05/2017.
 */
public interface MachineModelDao {

    void saveModel(@Validated(PersistenceGroup.class) MachineModel machineModel);

    void editModel(@Validated(PersistenceGroup.class) MachineModel machineModel);

    void editOrSaveModel(@Validated(PersistenceGroup.class) MachineModel machineModel);

    void deleteModel(int id);

    MachineModel findById(int id);

    List findAll();
}

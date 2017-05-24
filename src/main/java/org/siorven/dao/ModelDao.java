package org.siorven.dao;

import org.siorven.model.Model;
import org.siorven.model.validacion.PersistenceGroup;
import org.springframework.validation.annotation.Validated;

import java.util.List;

/**
 * Created by Gorospe on 24/05/2017.
 */
public interface ModelDao {

    void saveModel(@Validated(PersistenceGroup.class) Model model);

    void editModel(@Validated(PersistenceGroup.class) Model model);

    void editOrSaveModel(@Validated(PersistenceGroup.class) Model model);

    void deleteModel(int id);

    Model findById(int id);

    List findAll();
}

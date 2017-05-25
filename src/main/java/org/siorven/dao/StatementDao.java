package org.siorven.dao;

import org.siorven.model.Statement;
import org.siorven.model.validacion.PersistenceGroup;
import org.springframework.validation.annotation.Validated;

import java.util.List;

/**
 * Created by Gorospe on 25/05/2017.
 */
public interface StatementDao {

    void saveStatement(@Validated(PersistenceGroup.class) Statement statement);

    void editStatement(@Validated(PersistenceGroup.class) Statement statement);

    void editOrSaveStatement(@Validated(PersistenceGroup.class) Statement statement);

    void deleteStatement(int id);

    Statement findById(int id);

    List findAll();
}

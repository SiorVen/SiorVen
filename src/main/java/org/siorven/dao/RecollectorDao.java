package org.siorven.dao;

import org.siorven.model.Recollector;
import org.siorven.model.validacion.PersistenceGroup;
import org.springframework.validation.annotation.Validated;

import java.util.List;

/**
 * Created by Gorospe on 31/05/2017.
 */
public interface RecollectorDao {
    void saveRecollector(@Validated(PersistenceGroup.class) Recollector recollector);

    void editRecollector(@Validated(PersistenceGroup.class) Recollector recollector);

    void editOrSaveRecollector(@Validated(PersistenceGroup.class) Recollector recollector);

    void deleteRecollector(String alias);

    Recollector findByAlias(String alias);

    List<Recollector> findAll();

}

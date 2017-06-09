package org.siorven.dao;

import org.siorven.model.Recollector;
import org.siorven.model.validacion.PersistenceGroup;
import org.springframework.validation.annotation.Validated;

import java.util.List;

/**
 * Data access layer for recollectors
 *
 * @see Recollector
 */
public interface RecollectorDao {
    /**
     * Persists a Recollector
     *
     * @param recollector The Recollector to be persisted
     */
    void saveRecollector(@Validated(PersistenceGroup.class) Recollector recollector);

    /**
     * Updates a persisted Recollector
     *
     * @param recollector The Recollector to be persisted
     */
    void editRecollector(@Validated(PersistenceGroup.class) Recollector recollector);

    /**
     * Updates or persists a Recollector
     *
     * @param recollector The Recollector to be persisted
     */
    void editOrSaveRecollector(@Validated(PersistenceGroup.class) Recollector recollector);

    /**
     * Deletes a persisted Recollector
     *
     * @param alias The alias of the Recollector to be deleted
     */
    void deleteRecollector(String alias);

    /**
     * Finds a persisted Recollector by its alias
     *
     * @param alias The alias of the Recollector to be searched
     * @return The Recollector or null if it wasn't found
     */
    Recollector findByAlias(String alias);

    /**
     * Returns all the persisted Recollectors
     *
     * @return A {@link List} containing all the Recollectors
     */
    List<Recollector> findAll();

}

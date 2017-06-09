package org.siorven.dao;

import org.siorven.model.Statement;
import org.siorven.model.validacion.PersistenceGroup;
import org.springframework.validation.annotation.Validated;

import java.util.List;

/**
 * Data access layer for statements
 *
 * @see Statement
 */
public interface StatementDao {

    /**
     * Persists a Statement
     *
     * @param statement The Statement to be persisted
     */
    void saveStatement(@Validated(PersistenceGroup.class) Statement statement);

    /**
     * Updates a persisted Statement
     *
     * @param statement The Statement to be persisted
     */
    void editStatement(@Validated(PersistenceGroup.class) Statement statement);

    /**
     * Updates or persists a Statement
     *
     * @param statement The Statement to be persisted
     */
    void editOrSaveStatement(@Validated(PersistenceGroup.class) Statement statement);

    /**
     * Deletes a persisted Statement
     *
     * @param id The id of the Statement to be deleted
     */
    void deleteStatement(int id);

    /**
     * Finds a persisted Statement by its id
     *
     * @param id The id of the Statement to be searched
     * @return The Statement or null if it wasn't found
     */
    Statement findById(int id);

    /**
     * Returns all the persisted Statements
     *
     * @return A {@link List} containing all the Statements
     */
    List<Statement> findAll();
}

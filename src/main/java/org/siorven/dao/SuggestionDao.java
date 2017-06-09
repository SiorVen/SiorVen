package org.siorven.dao;

import org.siorven.model.Machine;
import org.siorven.model.Suggestion;
import org.siorven.model.validacion.PersistenceGroup;
import org.springframework.validation.annotation.Validated;

import java.util.List;

/**
 * Data access layer for suggestions
 *
 * @see Suggestion
 */
public interface SuggestionDao {

    /**
     * Persists a Suggestion
     *
     * @param suggestion The Suggestion to be persisted
     */
    void saveSuggestion(@Validated(PersistenceGroup.class) Suggestion suggestion);

    /**
     * Updates a persisted Suggestion
     *
     * @param suggestion The Suggestion to be persisted
     */
    void editSuggestion(@Validated(PersistenceGroup.class) Suggestion suggestion);

    /**
     * Updates or persists a Suggestion
     *
     * @param suggestion The Suggestion to be persisted
     */
    void editOrSaveSuggestion(@Validated(PersistenceGroup.class) Suggestion suggestion);

    /**
     * Deletes a persisted Suggestion
     *
     * @param id The id of the Suggestion to be deleted
     */
    void deleteSuggestion(int id);

    /**
     * Finds a persisted Suggestion by its id
     *
     * @param id The id of the Suggestion to be searched
     * @return The Suggestion or null if it wasn't found
     */
    Suggestion findById(int id);

    /**
     * Returns all the persisted Suggestions
     *
     * @return A {@link List} containing all the Suggestions
     */
    List findAll();

    /**
     * Finds a persisted Suggestions by their machine
     *
     * @param m The machine searched by
     * @return The Suggestion or null if it wasn't found
     */
    List<Suggestion> findByMachine(Machine m);

}

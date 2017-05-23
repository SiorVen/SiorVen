package org.siorven.dao;

import org.siorven.model.CompartimentDistribution;
import org.siorven.model.validacion.PersistenceGroup;
import org.springframework.validation.annotation.Validated;

import java.util.List;

/**
 * Created by Andoni on 17/05/2017.
 */

/**
 * Data access interface for the distribution of compartiments
 * @see CompartimentDistribution
 */
public interface CompartimentDistributionDao {

    /**
     * Persists a compartiment distribution
     * @param compartimentDistribution
     */
    void saveCompartimentDistribution(@Validated(PersistenceGroup.class)CompartimentDistribution compartimentDistribution);

    /**
     * Updates a persisted compartiment distribution
     * @param compartimentDistribution The compartiment distribution to be persisted
     */
    void editCompartimentDistribution(@Validated(PersistenceGroup.class) CompartimentDistribution compartimentDistribution);

    /**
     * Updates or persists a compartiment distribution
     * @param compartimentDistribution The compartiment distribution to be persisted
     */
    void editOrSaveCompartimentDistribution(@Validated(PersistenceGroup.class) CompartimentDistribution compartimentDistribution);

    /**
     * Deletes a persisted compartiment distribution
     * @param id The id of the compartiment distribution to be deleted
     */
    void deleteCompartimentDistribution(int id);

    /**
     * Finds a persisted compartiment distribution by its id
     * @param id The id of the compartiment distribution to be searched
     * @return The user or null if it wasn't found
     */
    CompartimentDistribution findCompartimentDistributionById(int id);
}

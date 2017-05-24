package org.siorven.dao;

import org.siorven.model.CompartimentDistribution;
import org.siorven.model.Distribution;
import org.siorven.model.validacion.PersistenceGroup;
import org.springframework.validation.annotation.Validated;

import java.util.List;

/**
 * Created by Andoni on 17/05/2017.
 */

/**
 * Data access interface for the distribution
 *
 * @see
 */
public interface DistributionDao {

    /**
     * Persists a  distribution
     *
     * @param distribution
     */
    void saveDistribution(@Validated(PersistenceGroup.class) Distribution distribution);

    /**
     * Updates a persisted distribution
     *
     * @param distribution The distribution to be persisted
     */
    void editDistribution(@Validated(PersistenceGroup.class) Distribution distribution);

    /**
     * Updates or persists a distribution
     *
     * @param distribution The compartiment distribution to be persisted
     */
    void editOrSaveDistribution(@Validated(PersistenceGroup.class) Distribution distribution);

    /**
     * Deletes a persisted distribution
     *
     * @param id The id of the distribution to be deleted
     */
    void deleteDistribution(int id);

    /**
     * Finds a persisted distribution by its id
     *
     * @param id The id of the distribution to be searched
     * @return The user or null if it wasn't found
     */
    Distribution findDistributionById(int id);

    List getAllDistributions();
}

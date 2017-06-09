package org.siorven.dao;

import org.siorven.model.ConfigParam;

import java.util.List;

/**
 * Data access interface for the ConfigParam
 *
 * @see ConfigParam
 */
public interface ConfigParamDao {

    /**
     * Persists a  ConfigParam
     *
     * @param configParam
     */
    void saveConfigParam(ConfigParam configParam);

    /**
     * Updates a persisted ConfigParam
     *
     * @param configParam The ConfigParam to be persisted
     */
    void editConfigParam(ConfigParam configParam);

    /**
     * Updates or persists a ConfigParam
     *
     * @param configParam The compartiment ConfigParam to be persisted
     */
    void editOrSaveConfigParam(ConfigParam configParam);

    /**
     * Deletes a persisted ConfigParam
     *
     * @param configParam The ConfigParam to be deleted
     */
    void deleteConfigParam(ConfigParam configParam);

    /**
     * Finds a persisted ConfigParam by its key
     *
     * @param key The key of the ConfigParam to be searched
     * @return The ConfigParam or null if it wasn't found
     */
    ConfigParam findConfigParamBykey(String key);

    /**
     * Gets all the configuration parameters
     *
     * @return The list of configuration parameters in the persistence holder
     */
    List<ConfigParam> getAllConfigParams();
}

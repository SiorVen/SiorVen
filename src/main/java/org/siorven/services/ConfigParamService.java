package org.siorven.services;

import org.siorven.dao.ConfigParamDao;
import org.siorven.model.ConfigParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Contains the access logic to configuration parameters
 */
@Service
public class ConfigParamService {

    @Autowired
    ConfigParamDao configParamDao;

    /**
     * Saves a configuration parameter overriding if present
     *
     * @param key   The parameter name
     * @param value The parameter value
     */
    public void save(String key, String value) {
        if (configParamDao.findConfigParamBykey(key) == null)
            configParamDao.saveConfigParam(new ConfigParam(key, value));
        else
            configParamDao.editConfigParam(new ConfigParam(key, value));
    }

    /**
     * Saves a configuration parameter overriding if present
     *
     * @param key   The parameter name
     * @param value The parameter value
     */
    public void save(String key, int value) {
        if (configParamDao.findConfigParamBykey(key) == null)
            configParamDao.saveConfigParam(new ConfigParam(key, Integer.toString(value)));
        else
            configParamDao.editConfigParam(new ConfigParam(key, Integer.toString(value)));
    }

    /**
     * Saves a configuration parameter overriding if present
     *
     * @param key   The parameter name
     * @param value The parameter value
     */
    public void save(String key, double value) {
        if (configParamDao.findConfigParamBykey(key) == null)
            configParamDao.saveConfigParam(new ConfigParam(key, Double.toString(value)));
        else
            configParamDao.editConfigParam(new ConfigParam(key, Double.toString(value)));
    }

    /**
     * Gets the value of a configuration parameter
     *
     * @param key The name of the configuration parameter
     * @return The value of the configuration parameter
     */
    public String get(String key) {
        ConfigParam param = configParamDao.findConfigParamBykey(key);
        return param == null ? null : param.getValue();
    }

    /**
     * Gets the value of a configuration parameter
     *
     * @param key The name of the configuration parameter
     * @return The value of the configuration parameter
     */
    public Integer getInt(String key) {
        ConfigParam param = configParamDao.findConfigParamBykey(key);
        return param == null ? null : Integer.parseInt(param.getValue());
    }

    /**
     * Gets the value of a configuration parameter
     *
     * @param key The name of the configuration parameter
     * @return The value of the configuration parameter
     */
    public Double getDouble(String key) {
        ConfigParam param = configParamDao.findConfigParamBykey(key);
        return param == null ? null : Double.parseDouble(param.getValue());
    }

}

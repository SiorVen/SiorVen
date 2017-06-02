package org.siorven.services;

import org.siorven.dao.ConfigParamDao;
import org.siorven.model.ConfigParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by ander on 01/06/2017.
 */
@Service
public class ConfigParamService {

    @Autowired
    ConfigParamDao configParamDao;

    public void save(String key, String value){
        if(configParamDao.findConfigParamBykey(key) == null)
            configParamDao.saveConfigParam(new ConfigParam(key, value));
        else
            configParamDao.editConfigParam(new ConfigParam(key, value));
    }

    public void save(String key, int value){
        if(configParamDao.findConfigParamBykey(key) == null)
            configParamDao.saveConfigParam(new ConfigParam(key, Integer.toString(value)));
        else
            configParamDao.editConfigParam(new ConfigParam(key, Integer.toString(value)));
    }

    public void save(String key, double value){
        if(configParamDao.findConfigParamBykey(key) == null)
            configParamDao.saveConfigParam(new ConfigParam(key, Double.toString(value)));
        else
            configParamDao.editConfigParam(new ConfigParam(key, Double.toString(value)));
    }

    public String get(String key){
        ConfigParam param = configParamDao.findConfigParamBykey(key);
        return param == null ? null : param.getValue();
    }

    public Integer getInt(String key){
        ConfigParam param = configParamDao.findConfigParamBykey(key);
        return param == null ? null : Integer.parseInt(param.getValue());
    }

    public Double getDouble(String key){
        ConfigParam param = configParamDao.findConfigParamBykey(key);
        return param == null ? null : Double.parseDouble(param.getValue());
    }

}

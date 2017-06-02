package org.siorven.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.siorven.model.ConfigParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by ander on 01/06/2017.
 */
@Transactional
@Repository
public class ConfigParamDaoImpl implements ConfigParamDao {

    /**
     * Session factory for the jdbc connection bean
     */
    @Autowired
    private SessionFactory sessionFactory;

    /**
     * Returns the current session of the {@link #sessionFactory}
     *
     * @return The current {@link Session}
     */
    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public void saveConfigParam(ConfigParam configParam) {
        getSession().save(configParam);
    }

    @Override
    public void editConfigParam(ConfigParam configParam) {
        getSession().update(configParam);
    }

    @Override
    public void editOrSaveConfigParam(ConfigParam configParam) {
        getSession().saveOrUpdate(configParam);
    }

    @Override
    public void deleteConfigParam(ConfigParam configParam) {
        getSession().delete(configParam);
    }

    @Override
    public ConfigParam findConfigParamBykey(String key) {
        Criteria criteria = getSession().createCriteria(ConfigParam.class).add(Restrictions.eq("key", key));
        return (ConfigParam) criteria.uniqueResult();
    }

    @Override
    public List getAllConfigParams() {
        return getSession().createCriteria(ConfigParam.class).list();
    }
}

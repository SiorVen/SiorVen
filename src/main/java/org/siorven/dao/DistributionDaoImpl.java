package org.siorven.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.siorven.model.Distribution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by joseb on 23/05/2017.
 */
@Transactional
@Repository
public class DistributionDaoImpl implements DistributionDao {

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
    public void saveDistribution(Distribution distribution) {
        getSession().save(distribution);
    }

    @Override
    public void editDistribution(Distribution distribution) {
        getSession().update(distribution);
    }

    @Override
    public void editOrSaveDistribution(Distribution distribution) {
        getSession().saveOrUpdate(distribution);
    }

    @Override
    public void deleteDistribution(int id) {
        getSession().delete(findDistributionById(id));
    }

    @Override
    public Distribution findDistributionById(int id) {
        Criteria criteria = getSession().createCriteria(Distribution.class).add(Restrictions.eq("id", id));
        return (Distribution) criteria.uniqueResult();
    }

    @Override
    public List getAllDistributions() {
        return getSession().createCriteria(Distribution.class).list();
    }
}

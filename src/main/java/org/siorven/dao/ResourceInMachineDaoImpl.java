package org.siorven.dao;


import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.siorven.model.ResourceInMachine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Implementation of the {@link ResourceInMachineDao} interface with hibernate persistence onto a jdbc database
 */
@Transactional
@Repository
public class ResourceInMachineDaoImpl implements ResourceInMachineDao{

    /**
     * Session factory for the jdbc connection bean
     */
    @Autowired
    private SessionFactory sessionFactory;

    /**
     * Returns the current session of the {@link #sessionFactory}
     * @return The current {@link Session}
     */
    private Session getSession(){return sessionFactory.getCurrentSession();}

    @Override
    public void save(ResourceInMachine r) {
        getSession().save(r);
    }

    @Override
    public void edit(ResourceInMachine r) {
        getSession().update(r);
    }

    @Override
    public void editOrSave(ResourceInMachine r) {
        getSession().saveOrUpdate(r);
    }

    @Override
    public void delete(String id) {
        getSession().delete(findById(id));
    }

    @Override
    public ResourceInMachine findById(String id) {
        Criteria crit = getSession().createCriteria(ResourceInMachine.class).add(Restrictions.eq("id", id));
        return (ResourceInMachine) crit.uniqueResult();
    }

    @Override
    public List getAllResources() {
        return getSession().createCriteria(ResourceInMachine.class).list();
    }
}

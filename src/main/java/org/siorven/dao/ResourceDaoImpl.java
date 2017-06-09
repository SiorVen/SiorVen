package org.siorven.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.siorven.model.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Implementation of the {@link ResourceDao} interface with hibernate persistence onto a jdbc database
 */
@Transactional
@Repository
public class ResourceDaoImpl implements ResourceDao {


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
    public void save(Resource r) {
        getSession().save(r);
    }

    @Override
    public void edit(Resource r) {
        getSession().update(r);
    }

    @Override
    public void editOrSave(Resource r) {
        getSession().saveOrUpdate(r);
    }

    @Override
    public void delete(String id) {
        getSession().delete(findById(id));
    }

    @Override
    public Resource findById(String id) {
        Criteria crit = getSession().createCriteria(Resource.class).add(Restrictions.eq("id", id));
        return (Resource) crit.uniqueResult();
    }

    @Override
    public Resource findByName(String name) {
        Criteria crit = getSession().createCriteria(Resource.class).add(Restrictions.eq("name", name));
        return (Resource) crit.uniqueResult();

    }

    @Override
    public List<Resource> getAllResources() {
        return getSession().createCriteria(Resource.class).list();
    }

    @Override
    public List<Resource> findByResourceType(String resourceType) {
        Criteria crit = getSession().createCriteria(Resource.class).add(Restrictions.eq("resourceType", resourceType));
        return crit.list();
    }

    @Override
    public List<Resource> findByLikeName(String resourceName) {
        Criteria crit = getSession().createCriteria(Resource.class).add(Restrictions.like("name", "%" + resourceName + "%"));
        return crit.list();
    }
}

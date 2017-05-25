package org.siorven.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.siorven.model.ProductType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Implementation of the {@link ProductTypeDao} interface with hibernate persistence onto a jdbc database
 */
@Transactional
@Repository
public class ProductTypeDaoImpl implements ProductTypeDao{

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
    public void save(ProductType p) {
        getSession().save(p);
    }

    @Override
    public void edit(ProductType p) {
        getSession().update(p);
    }

    @Override
    public void editOrSave(ProductType p) {
        getSession().saveOrUpdate(p);
    }

    @Override
    public void delete(String id) {
        getSession().delete(findById(id));
    }

    @Override
    public ProductType findById(String id) {
        Criteria crit = getSession().createCriteria(ProductType.class).add(Restrictions.eq("id", id));
        return (ProductType) crit.uniqueResult();
    }

    @Override
    public ProductType findByType(String type) {
        Criteria crit = getSession().createCriteria(ProductType.class).add(Restrictions.eq("type", type));
        return (ProductType) crit.uniqueResult();
    }

    @Override
    public List getAllProductTypes() {
        return getSession().createCriteria(ProductType.class).list();
    }
}

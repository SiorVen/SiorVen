package org.siorven.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.siorven.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Implementation of the {@link ProductDao} interface with hibernate persistence onto a jdbc database
 */
@Transactional
@Repository
public class ProductDaoImpl implements ProductDao {

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
    public void save(Product p) {
        getSession().save(p);
    }

    @Override
    public void edit(Product p) {
        getSession().update(p);
    }

    @Override
    public void editOrSave(Product p) {
        getSession().saveOrUpdate(p);
    }

    @Override
    public void delete(int id) {
        getSession().delete(findById(id));
    }

    @Override
    public Product findById(int id) {
        Criteria crit = getSession().createCriteria(Product.class).add(Restrictions.eq("id", id));
        return (Product) crit.uniqueResult();
    }

    @Override
    public Product findByName(String name) {
        Criteria crit = getSession().createCriteria(Product.class).add(Restrictions.eq("name", name));
        return (Product) crit.uniqueResult();
    }

    @Override
    public List<Product> getAllProducts() {
        return getSession().createCriteria(Product.class).list();
    }


}

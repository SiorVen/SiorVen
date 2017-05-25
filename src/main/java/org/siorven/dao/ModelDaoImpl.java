package org.siorven.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.siorven.model.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Gorospe on 24/05/2017.
 */
@Transactional
@Repository
public class ModelDaoImpl implements ModelDao {

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
    public void saveModel(Model model) {
        getSession().save(model);
    }

    @Override
    public void editModel(Model model) {
        getSession().update(model);
    }

    @Override
    public void editOrSaveModel(Model model) {
        getSession().saveOrUpdate(model);
    }

    @Override
    public void deleteModel(int id) {
        getSession().delete(findById(id));
    }

    @Override
    public Model findById(int id) {
        Criteria criteria = getSession().createCriteria(Model.class).add(Restrictions.eq("id", id));
        return (Model) criteria.uniqueResult();
    }

    @Override
    public List findAll() {
        return getSession().createCriteria(Model.class).list();
    }
}

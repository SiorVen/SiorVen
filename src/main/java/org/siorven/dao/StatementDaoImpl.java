package org.siorven.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.siorven.model.Statement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Gorospe on 25/05/2017.
 */
@Transactional
@Repository
public class StatementDaoImpl implements StatementDao {

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
    public void saveStatement(Statement statement) {
        getSession().save(statement);
    }

    @Override
    public void editStatement(Statement statement) {
        getSession().update(statement);
    }

    @Override
    public void editOrSaveStatement(Statement statement) {
        getSession().saveOrUpdate(statement);
    }

    @Override
    public void deleteStatement(int id) {
        getSession().delete(findById(id));
    }

    @Override
    public Statement findById(int id) {
        Criteria crit = getSession().createCriteria(Statement.class).add(Restrictions.eq("id", id));
        return (Statement) crit.uniqueResult();
    }

    @Override
    public List findAll() {
        return getSession().createCriteria(Statement.class).list();
    }
}

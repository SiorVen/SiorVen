package org.siorven.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.siorven.model.Recollector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Gorospe on 31/05/2017.
 */
@Transactional
@Repository
public class RecollectorDaoImpl implements RecollectorDao {

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
    public void saveRecollector(Recollector recollector) {
        getSession().save(recollector);
    }

    @Override
    public void editRecollector(Recollector recollector) {
        getSession().update(recollector);
    }

    @Override
    public void editOrSaveRecollector(Recollector recollector) {
        getSession().saveOrUpdate(recollector);
    }

    @Override
    public void deleteRecollector(String alias) {
        getSession().delete(findByAlias(alias));
    }

    @Override
    public Recollector findByAlias(String alias) {
        Criteria crit = getSession().createCriteria(Recollector.class).add(Restrictions.eq("alias", alias));
        return (Recollector) crit.uniqueResult();
    }

    @Override
    public List<Recollector> findAll() {
        return getSession().createCriteria(Recollector.class).list();
    }
}

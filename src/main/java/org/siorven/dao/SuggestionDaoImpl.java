package org.siorven.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.siorven.model.Machine;
import org.siorven.model.Suggestion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Gorospe on 25/05/2017.
 */
@Transactional
@Repository
public class SuggestionDaoImpl implements SuggestionDao {

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
    public void saveSuggestion(Suggestion suggestion) {
        getSession().save(suggestion);
    }

    @Override
    public void editSuggestion(Suggestion suggestion) {
        getSession().update(suggestion);
    }

    @Override
    public void editOrSaveSuggestion(Suggestion suggestion) {
        getSession().saveOrUpdate(suggestion);
    }

    @Override
    public void deleteSuggestion(int id) {
        getSession().delete(findById(id));
    }

    @Override
    public Suggestion findById(int id) {
        Criteria crit = getSession().createCriteria(Suggestion.class).add(Restrictions.eq("id", id));
        return (Suggestion) crit.uniqueResult();
    }

    @Override
    public List findAll() {
        return getSession().createCriteria(Suggestion.class).addOrder(Order.desc("weight")).list();
    }

    @Override
    public List<Suggestion> findByMachine(Machine m) {
        Criteria crit = getSession().createCriteria(Suggestion.class).add(Restrictions.eq("machine", m));
        return crit.addOrder(Order.desc("weight")).list();
    }
}

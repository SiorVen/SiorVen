package org.siorven.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.siorven.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Implementation of the {@link UserDao} interface with hibernate persistence onto a jdbc database
 */
@Transactional
@Repository
public class UserDaoImpl implements UserDao {

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
    public void saveUser(User u) {
        getSession().save(u);
    }

    @Override
    public void editUser(User u) {
        getSession().update(u);
    }

    @Override
    public void editOrSave(User u) {
        getSession().saveOrUpdate(u);
    }

    @Override
    public void deleteUser(int id) {
        getSession().delete(findById(id));
    }

    @Override
    public User findById(int id) {
        Criteria crit = getSession().createCriteria(User.class).add(Restrictions.eq("id", id));
        return (User) crit.uniqueResult();
    }

    @Override
    public User findByUsername(String username) {
        Criteria crit = getSession().createCriteria(User.class).add(Restrictions.eq("username", username));
        return (User) crit.uniqueResult();
    }

    @Override
    public User findByEmail(String email) {
        Criteria crit = getSession().createCriteria(User.class).add(Restrictions.eq("email", email));
        return (User) crit.uniqueResult();
    }

    @Override
    public List<User> getAllUsers() {
        return getSession().createCriteria(User.class).list();
    }
}

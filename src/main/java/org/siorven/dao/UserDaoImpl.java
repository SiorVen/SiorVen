package org.siorven.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.siorven.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by ander on 18/05/2017.
 */
@Transactional
@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    private Session getSession(){return sessionFactory.getCurrentSession();}

    @Override
    public void saveUser(User u) {
        getSession().save(u);
    }

    @Override
    public void editUser(User u) {

    }

    @Override
    public void deleteUser(int id) {

    }

    @Override
    public User findById(int id) {
        return null;
    }

    @Override
    public User findByUsername(String username) {
        return null;
    }

    @Override
    public List<User> getAllUsers() {
        return null;
    }
}

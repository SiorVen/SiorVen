package org.siorven.dao;

import org.siorven.model.User;

import java.util.List;

/**
 * Created by Andoni on 17/05/2017.
 */
public interface UserDao {

    void saveUser(User u);
    void editUser(User u);
    void deleteUser(int id);
    User findById(int id);
    User findByUsername(String username);
    List<User> getAllUsers();
}

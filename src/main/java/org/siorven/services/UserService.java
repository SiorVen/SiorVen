package org.siorven.services;

import org.siorven.dao.UserDao;
import org.siorven.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service to provide access logic to the user data
 */
@Service
public class UserService {

    /**
     * Data acces object for the user table on the database
     */
    @Autowired
    private UserDao userDao;

    /**
     * Password encoder used to store the passwords on the database
     */
    @Autowired
    private PasswordEncoder passwordEncoder;


    /**
     * Saves a user to the database after encoding the password with the SCRYPT PKDF.
     *
     * @param u The user to be saved
     */
    public void save(User u) {
        String claveUsr = u.getPassword();
        u.setPassword(passwordEncoder.encode(claveUsr));
        userDao.saveUser(u);
    }

    /**
     * Saves or updates a user on the database, if the user is a new one the password wont get encoded use {@link #save(User) save} method
     *
     * @param user User to be saved or updated
     */
    public void saveOrUpdate(User user) {
        userDao.editOrSave(user);
    }

    /**
     * Deletes a user from the database
      * @param u
     */
    public void delete(User u) {
        userDao.deleteUser(u.getId());
    }

    /**
     * Searches for a user with the given user or email
     * @param string The username or the email of the user
     * @return Returns the user or null if it wasn't found
     */
    public User findByEmailOrUsername(String string) {
        User user = findByUsername(string);
        if (user != null) return user;
        return findByEmail(string);
    }

    /**
     * Returns all the users on the database
     * @return The list of users
     */
    public List<User> findAll() {
        return userDao.getAllUsers();
    }

    /**
     * Searches for the user with the given email
     * @param email The email of the requested user
     * @return The user or null if it wasn't found
     */
    public User findByEmail(String email) {
        return userDao.findByEmail(email);
    }

    /**
     * Searches for a user with the given username
     * @param username The username of the requested user
     * @return The user or null if the user wasn't found
     */
    public User findByUsername(String username) {
        return userDao.findByUsername(username);
    }

}

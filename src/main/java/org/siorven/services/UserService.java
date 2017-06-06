package org.siorven.services;

import org.siorven.dao.UserDao;
import org.siorven.exceptions.EmailInUseException;
import org.siorven.exceptions.UsernameInUseException;
import org.siorven.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

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
    public void save(User u) throws UsernameInUseException, EmailInUseException {
        checkifInUse(u);
        String claveUsr = u.getPassword();
        u.setPassword(passwordEncoder.encode(claveUsr));
        userDao.saveUser(u);
    }


    /**
     * Saves or updates a user on the database, if the user is a new one the password wont get encoded use {@link #save(User) saveProductType} method
     *
     * @param user User to be saved or updated
     */
    public void saveOrUpdate(User user) throws UsernameInUseException, EmailInUseException {
        checkifInUse(user);
        User oldUser = userDao.findById(user.getId());

        if (Objects.equals(oldUser.getPermission(), User.ROLE_ADMIN) && !Objects.equals(user.getPermission(), User.ROLE_ADMIN)) {
            if (userDao.findByRole(User.ROLE_ADMIN).size() <= 1) {
                throw new DataIntegrityViolationException("error.lastAdmin");
            }
        }

        userDao.editOrSave(user);
    }

    /**
     * Deletes a user from the database
     *
     * @param u The user to be deleted from the database
     */
    public void delete(User u) throws DataIntegrityViolationException {
        if (u.getPermission().equals(User.ROLE_ADMIN) && userDao.findByRole(User.ROLE_ADMIN).size() <= 1)
            throw new DataIntegrityViolationException("error.lastAdmin");
        userDao.deleteUser(u.getId());
    }

    /**
     * Searches for a user with the given user or email
     *
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
     *
     * @return The list of users
     */
    public List findAll() {
        return userDao.getAllUsers();
    }

    /**
     * Returns all the users on the database with the role
     *
     * @param role Role of the users
     * @return The list of users
     */
    public List findbyRole(String role) {
        return userDao.findByRole(role);
    }

    /**
     * Searches for the user with the given email
     *
     * @param email The email of the requested user
     * @return The user or null if it wasn't found
     */
    public User findByEmail(String email) {
        return userDao.findByEmail(email);
    }

    /**
     * Searches for a user with the given username
     *
     * @param username The username of the requested user
     * @return The user or null if the user wasn't found
     */
    public User findByUsername(String username) {
        return userDao.findByUsername(username);
    }

    /**
     * Searches for a user with the given id
     *
     * @param id The id of the requested user
     * @return The user or null if the user wasn't found
     */
    public User findById(int id) {
        return userDao.findById(id);
    }

    private void checkifInUse(User usuario) throws EmailInUseException, UsernameInUseException {
        boolean ret = false;
        User u = findByEmail(usuario.getEmail());
        if (u != null && u.getId() != usuario.getId()) {
            throw new EmailInUseException();
        }
        u = findByUsername(usuario.getUsername());
        if (u != null && u.getId() != usuario.getId()) {
            throw new UsernameInUseException();
        }
    }


}

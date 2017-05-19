package org.siorven.dao;

import org.siorven.model.User;
import org.siorven.model.validacion.PersistenceGroup;
import org.springframework.validation.annotation.Validated;

import java.util.List;

/**
 * Created by Andoni on 17/05/2017.
 */

/**
 * Data access interface for the users
 * @see User
 */
public interface UserDao {

    /**
     * Persists a user
     * @param u The user to be persisted
     */
    void saveUser(@Validated(PersistenceGroup.class)User u);

    /**
     * Updates a persisted user
     * @param u The user to be persisted
     */
    void editUser(@Validated(PersistenceGroup.class) User u);

    /**
     * Updates or persists a user
     * @param u The user to be persisted
     */
    void editOrSave(@Validated(PersistenceGroup.class) User u);

    /**
     * Deletes a persisted user
     * @param id The id of the user to be deleted
     */
    void deleteUser(int id);

    /**
     * Finds a persisted user by its id
     * @param id The id of the user to be searched
     * @return The user or null if it wasn't found
     */
    User findById(int id);

    /**
     * Finds a persisted user by its username
     * @param username The username of the searched user
     * @return The user or null if it wasn't found
     */
    User findByUsername(String username);

    /**
     * Finds a persisted user by its email
     * @param email The email of the requested user
     * @return The user or null if it was not found
     */
    User findByEmail(String email);

    /**
     * Returns all the persisted users
     * @return A {@link List} containing all the users
     */
    List getAllUsers();
}

package org.siorven.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.List;

/**
 * Created by Andoni on 17/05/2017.
 */

@Entity
@Table(name="user")
public class User implements UserService{

    private String username;

    private String password;

    @Override
    public int addUser(User u) {
        return 0;
    }

    @Override
    public int editUser(User u) {
        return 0;
    }

    @Override
    public int deleteUser(int id) {
        return 0;
    }

    @Override
    public User getUser(int id) {
        return null;
    }

    @Override
    public List<User> getAllUsers() {
        return null;
    }
}

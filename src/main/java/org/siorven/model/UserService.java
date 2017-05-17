package org.siorven.model;

import java.util.List;

/**
 * Created by Andoni on 17/05/2017.
 */
public interface UserService {

    public int addUser(User u);
    public int editUser(User u);
    public int deleteUser(int id);
    public User getUser(int id);
    public List<User> getAllUsers();
}

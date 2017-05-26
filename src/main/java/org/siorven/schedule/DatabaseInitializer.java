package org.siorven.schedule;

import org.siorven.model.User;
import org.siorven.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by ander on 26/05/2017.
 */
@Component
public class DatabaseInitializer {

    @Autowired
    UserService userService;

    @Scheduled(fixedRate = Long.MAX_VALUE) //Runs once
    public void initDb(){
        assertThereIsAnAdmin();

    }

    public void assertThereIsAnAdmin(){
        List<User> admins = userService.findbyRole(User.ROLE_ADMIN);
        if(admins.size() < 1){
            userService.save(new User("admin", "admin", "admin@siorven.eus", User.ROLE_ADMIN));
        }
    }

}

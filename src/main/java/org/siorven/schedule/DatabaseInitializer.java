package org.siorven.schedule;

import org.siorven.exceptions.EmailInUseException;
import org.siorven.exceptions.UsernameInUseException;
import org.siorven.logic.Initializer;
import org.siorven.model.ConfigParam;
import org.siorven.model.User;
import org.siorven.services.ConfigParamService;
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

    @Autowired
    Initializer initializer;

    @Autowired
    ConfigParamService configParamService;

    @Scheduled(fixedRate = Long.MAX_VALUE) //Runs once
    public void initDb() {
        initSuggestionConf();
        initializer.initExample();
        assertThereIsAnAdmin();
    }

    private void initSuggestionConf() {
        setIfAbsent(ConfigParam.SUGG_APRIORI_DAYPERIOD, 30);
        setIfAbsent(ConfigParam.SUGG_APRIORI_SUCCESSALES, 12);
        setIfAbsent(ConfigParam.SUGG_MAXMIN_DAYPERIOD, 7);
        setIfAbsent(ConfigParam.SUGG_MAXMIN_RATIOMAX, 1.5);
        setIfAbsent(ConfigParam.SUGG_MAXMIN_RATIOMIN, 0.5);
    }

    private void setIfAbsent(String key, int value){
        if (configParamService.get(key) == null)
            configParamService.save(key, value);
    }

    private void setIfAbsent(String key, double value){
        if (configParamService.get(key) == null)
            configParamService.save(key, value);
    }

    private void assertThereIsAnAdmin() {
        List<User> admins = userService.findbyRole(User.ROLE_ADMIN);
        if (admins.isEmpty()) {
            try {
                userService.save(new User("admin", "admin", "admin@siorven.eus", User.ROLE_ADMIN));
            } catch (UsernameInUseException | EmailInUseException e) {
                e.printStackTrace();
            }
        }
    }



}

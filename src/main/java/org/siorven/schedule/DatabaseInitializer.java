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
 * Contains the task to initialize the database on startup
 */
@Component
public class DatabaseInitializer {

    @Autowired
    UserService userService;

    @Autowired
    Initializer initializer;

    @Autowired
    ConfigParamService configParamService;

    /**
     * Runs once to initialize the database
     */
    @Scheduled(fixedRate = Long.MAX_VALUE)
    public void initDb() {
        initSuggestionConf();
        initializer.initExample();
        assertThereIsAnAdmin();
    }

    /**
     * Initializes the suggestion configuration if absent
     */
    private void initSuggestionConf() {
        setIfAbsent(ConfigParam.SUGG_APRIORI_DAYPERIOD, 30);
        setIfAbsent(ConfigParam.SUGG_APRIORI_SUCCESSALES, 12);
        setIfAbsent(ConfigParam.SUGG_MAXMIN_DAYPERIOD, 7);
        setIfAbsent(ConfigParam.SUGG_MAXMIN_RATIOMAX, 1.5);
        setIfAbsent(ConfigParam.SUGG_MAXMIN_RATIOMIN, 0.5);
    }

    /**
     * Sets a config param if it doesn't Exist
     *
     * @param key   The configuration parameter name
     * @param value The configuration parameter value
     */
    private void setIfAbsent(String key, int value) {
        if (configParamService.get(key) == null)
            configParamService.save(key, value);
    }

    /**
     * Sets a config param if it doesn't Exist
     *
     * @param key   The configuration parameter name
     * @param value The configuration parameter value
     */
    private void setIfAbsent(String key, double value) {
        if (configParamService.get(key) == null)
            configParamService.save(key, value);
    }

    /**
     * Checks if an admin exists and creates a default one if not with:
     * <p>
     * Username: admin
     * <br/>
     * Password: admin
     * </p>
     */
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

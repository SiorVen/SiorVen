package org.siorven.controller.api;

import org.siorven.model.User;
import org.siorven.services.UserService;
import org.siorven.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.LocaleResolver;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * Controller of the method on the user Rest Api for the users
 *
 * @see User
 */
@RestController
public class UserRestController {

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private LocaleResolver localeResolver;

    @Autowired
    private HttpServletRequest request;


    /**
     * Service with the user data access logic
     */
    @Autowired
    private UserService userService;

    /**
     * Gets the json representation of all the users
     *
     * @return The users
     */
    @GetMapping(value = "/api/user/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public Collection<User> getAll() {
        return userService.findAll();
    }

    /**
     * Gets all the users for presentation on a Datatables js api table
     *
     * @return json object
     */
    @PostMapping("/api/user/datatable")
    private Map<String, List<Map<String, String>>> giveUserListDataForTable() {
        List<User> users = userService.findAll();

        Map<String, List<Map<String, String>>> datatables = new HashMap<>();
        List<Map<String, String>> data = new ArrayList<>();
        datatables.put("data", data);
        for (User u : users) {
            Map<String, String> entry = new HashMap<>();
            entry.put("id", u.getId() + "");
            entry.put("type", UserUtils.permissionCodeToHuman(u.getPermission(), messageSource, localeResolver, request));
            entry.put("username", u.getUsername());
            entry.put("email", u.getEmail());
            data.add(entry);
        }
        return datatables;
    }

    /**
     * Gets the json representation of the user with the given id
     *
     * @param id the id of the requested user
     * @return NOT_FOUND (404) if there is no user with such id or the user
     */
    @GetMapping(value = "/api/user/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Object get(@PathVariable("id") int id) {
        User u = userService.findById(id);
        if (u == null) return new ResponseEntity(HttpStatus.NOT_FOUND);
        return u;
    }

    /**
     * Deletes a user by its id
     *
     * @param id Id os the user
     * @return returns CONFLICT (409) if the user being deleted is the last admin return OK (200) if else
     */
    @DeleteMapping(value = "/api/user/{id}")
    public ResponseEntity delete(@PathVariable("id") int id) throws ServletException {
        try {
            User deletedUser = userService.findById(id);
            if (deletedUser == null) return new ResponseEntity(HttpStatus.NOT_FOUND);
            userService.delete(deletedUser);
            User currentUser = UserUtils.getCurrentUser();

            logoutIfSame(deletedUser, currentUser);
        } catch (DataIntegrityViolationException dive) {
            return new ResponseEntity(HttpStatus.FORBIDDEN);
        }

        return new ResponseEntity(HttpStatus.OK);
    }

    private void logoutIfSame(User deleted, User user) throws ServletException {
        if (user.getId() == deleted.getId()) {
            try {
                request.logout();
            } catch (ServletException e) {
                throw e;
            }
        }
    }


}

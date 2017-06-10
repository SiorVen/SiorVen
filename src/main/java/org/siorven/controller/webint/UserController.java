package org.siorven.controller.webint;

import org.siorven.controller.handlers.errors.ResourceNotFoundException;
import org.siorven.exceptions.EmailInUseException;
import org.siorven.exceptions.UsernameInUseException;
import org.siorven.model.User;
import org.siorven.model.validacion.SpringFormEditGroup;
import org.siorven.model.validacion.SpringFormGroup;
import org.siorven.services.UserService;
import org.siorven.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.util.LinkedHashMap;
import java.util.Locale;

/**
 * Web controller for the newUser actions on the interface
 */
@Controller
public class UserController {

    private static final String REGISTER_VIEW = "register";
    private static final String USER = "user";
    private static final String REDIRECT_USER_MANAGER = "redirect:/user/manager";
    private static final String MESSAGE = "message";
    /**
     * Data access logic for the access to the newUser data on the DB
     */
    @Autowired
    private UserService userService;

    /**
     * Message source for internationalization of the content
     */
    @Autowired
    private MessageSource messageSource;

    /**
     * Locale resolver for the sent cookies or session attributes
     */
    @Autowired
    private LocaleResolver locale;

    /**
     * Currently handled HTTP Request
     */
    @Autowired
    private HttpServletRequest request;

    /**
     * Shortcut method to get the current requests locale
     *
     * @return The request locale
     */
    private Locale locale() {
        return locale.resolveLocale(request);
    }

    /**
     * GET method of the newUser register action, returns the register file
     *
     * @param model MachineModel of the response scope
     * @return Key for the {@link org.springframework.web.servlet.ViewResolver ViewResolver} bean
     */
    @GetMapping("/user/register")
    public String showRegister(Model model) {
        model.addAttribute(USER, new User());
        addUserTypes(model);
        return REGISTER_VIEW;
    }

    /**
     * POST method of the newUser register action, checks the form data and rejects it with the according error message(s)
     *
     * @param usuario       The newUser with the data collected from the web form
     * @param bindingResult The error wrapper of the validation errors
     * @param model         MachineModel of the response scope
     * @return Key for the {@link org.springframework.web.servlet.ViewResolver ViewResolver} bean
     */
    @PostMapping("/user/register")
    public String performRegister(@ModelAttribute(USER) @Validated(SpringFormGroup.class) User usuario,
                                  BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {
        addUserTypes(model);
        if (bindingResult.hasErrors()) {
            return REGISTER_VIEW;
        }

        try {
            userService.save(usuario);
        } catch (UsernameInUseException e) {
            String msg = messageSource.getMessage("error.user.emailTaken", null, locale());
            bindingResult.addError(new FieldError(USER, "email", usuario.getEmail(), true, null, null, msg));
            return REGISTER_VIEW;
        } catch (EmailInUseException e) {
            String msg = messageSource.getMessage("error.user.usernameTaken", null, locale());
            bindingResult.addError(new FieldError(USER, "username", usuario.getUsername(), true, null, null, msg));
            return REGISTER_VIEW;
        }
        String msg = messageSource.getMessage("msg.userRegisteredSuccesfully",
                new String[]{usuario.getUsername()}, locale());
        redirectAttributes.addFlashAttribute(MESSAGE, msg);

        return REDIRECT_USER_MANAGER;
    }

    /**
     * Displays the newUser manager
     *
     * @return Key for the {@link org.springframework.web.servlet.ViewResolver ViewResolver} bean
     */
    @GetMapping("/user/manager")
    public String showUserManager() {
        return "userManager";
    }


    /**
     * Deletes a newUser
     *
     * @param id                 id of the newUser
     * @param redirectAttributes Redirected attributes to the manager
     * @return Key for the {@link org.springframework.web.servlet.ViewResolver ViewResolver} bean
     */
    @GetMapping("/user/delete/{id}")
    public String deleteUser(@PathVariable("id") int id, RedirectAttributes redirectAttributes) throws ServletException {
        User u = getUserOrThrow(id);
        String msg;
        msg = handleDeleteUser(u);
        User user = UserUtils.getCurrentUser();

        if (user.getId() == u.getId())
            try {
                msg = messageSource.getMessage("msg.selfDelete", null, locale());
                redirectAttributes.addFlashAttribute(MESSAGE, msg);
                request.logout();
                return "redirect:/";
            } catch (ServletException e) {
                throw e;
            }

        redirectAttributes.addFlashAttribute(MESSAGE, msg);
        return REDIRECT_USER_MANAGER;
    }

    /**
     * Handles the deletion of a user
     *
     * @param u The user to be deleted
     * @return The message result of the deletion or attempt of deletion of the user
     */
    private String handleDeleteUser(User u) {
        String msg;
        try {
            userService.delete(u);
            msg = messageSource.getMessage("msg.userDeleted", new String[]{u.getUsername()}, locale());
        } catch (DataIntegrityViolationException dive) {
            msg = messageSource.getMessage(dive.getMessage(), null, locale());
        }
        return msg;
    }

    /**
     * Shows the edit newUser page
     *
     * @param id id of the newUser
     * @return Key for the {@link org.springframework.web.servlet.ViewResolver ViewResolver} bean
     */
    @GetMapping("/user/edit/{id}")
    public String showEditUser(@PathVariable("id") int id, Model model) {
        addUserTypes(model);
        User u = getUserOrThrow(id);
        model.addAttribute("user", u);
        return "userEdit";
    }

    /**
     * Edits a newUser
     *
     * @param newUser            newUser to update
     * @param redirectAttributes Redirected attributes to the manager
     * @return Key for the {@link org.springframework.web.servlet.ViewResolver ViewResolver} bean
     */
    @PostMapping("/user/edit")
    public String editUser(@ModelAttribute("user") @Validated(SpringFormEditGroup.class) User newUser, RedirectAttributes redirectAttributes, BindingResult bindingResult, Model model) throws ServletException {
        addUserTypes(model);
        if (bindingResult.hasErrors()) {
            return REGISTER_VIEW;
        }
        User oldUser = mergeWithOldUser(newUser);
        try {
            userService.saveOrUpdate(oldUser);
        } catch (DataIntegrityViolationException dive) {
            String msg = messageSource.getMessage(dive.getMessage(), null, locale());
            redirectAttributes.addFlashAttribute(MESSAGE, msg);
            return REDIRECT_USER_MANAGER;
        } catch (UsernameInUseException e) {
            String msg = messageSource.getMessage("error.user.emailTaken", null, locale());
            bindingResult.addError(new FieldError(USER, "email", oldUser.getEmail(), true, null, null, msg));
            return REGISTER_VIEW;
        } catch (EmailInUseException e) {
            String msg = messageSource.getMessage("error.user.usernameTaken", null, locale());
            bindingResult.addError(new FieldError(USER, "username", oldUser.getUsername(), true, null, null, msg));
            return REGISTER_VIEW;
        }

        User user = UserUtils.getCurrentUser();

        if (user.getId() == oldUser.getId())
            try {
                String msg = messageSource.getMessage("msg.selfEditLogin", null, locale());
                redirectAttributes.addFlashAttribute(MESSAGE, msg);
                request.logout();
                return "redirect:/";
            } catch (ServletException e) {
                throw e;
            }

        String msg = messageSource.getMessage("msg.userEdited", new String[]{oldUser.getUsername()}, locale());
        redirectAttributes.addFlashAttribute(MESSAGE, msg);

        return REDIRECT_USER_MANAGER;
    }

    /**
     * Combines the fields of a modified user with the unmodified ones
     *
     * @param newUser The modified user
     * @return The merged user
     */
    private User mergeWithOldUser(@ModelAttribute("user") @Validated(SpringFormEditGroup.class) User newUser) {
        User oldUser = userService.findById(newUser.getId());
        oldUser.setPermission(newUser.getPermission());
        oldUser.setEmail(newUser.getEmail());
        oldUser.setUsername(newUser.getUsername());
        return oldUser;
    }

    /**
     * Shows a users page
     *
     * @param id    id of the newUser
     * @param model Response model
     * @return Key for the {@link org.springframework.web.servlet.ViewResolver ViewResolver} bean
     */
    @GetMapping("/user/{id}")
    public String showUser(@PathVariable("id") int id, Model model) {

        User u = getUserOrThrow(id);
        u.setPermission(UserUtils.permissionCodeToHuman(u.getPermission(), messageSource, locale, request));
        model.addAttribute("user", u);

        return "userView";
    }

    /**
     * Adds a {@link LinkedHashMap LinkedHashMap<String, String>} to the response model
     * with the different newUser types and their internationalized representation
     *
     * @param model MachineModel of the response scope
     */
    private void addUserTypes(Model model) {
        LinkedHashMap<String, String> roles = new LinkedHashMap<>();
        roles.put(User.ROLE_ADMIN,
                messageSource.getMessage("role.admin", null, locale()));
        roles.put(User.ROLE_REPONEDOR,
                messageSource.getMessage("role.reponedor", null, locale()));
        model.addAttribute("userTypes", roles);
    }

    /**
     * Gets the user with the given ID or throws a {@link ResourceNotFoundException}
     *
     * @param id The id of the user
     * @return The user if found
     */
    private User getUserOrThrow(int id) {
        User u = userService.findById(id);
        if (u == null) {
            String reason = messageSource.getMessage("error.userNotExist", null, locale());
            throw new ResourceNotFoundException(reason);
        }
        return u;
    }

}

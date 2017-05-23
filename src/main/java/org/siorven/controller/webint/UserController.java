package org.siorven.controller.webint;

import org.siorven.controller.handlers.errors.ResourceNotFoundException;
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

/**
 * Web controller for the newUser actions on the interface
 */
@Controller
public class UserController {

    public static final String REGISTER_VIEW = "register";
    public static final String USER = "user";
    public static final String REDIRECT_USER_MANAGER = "redirect:/user/manager";
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
     * GET method of the newUser register action, returns the register file
     * @param model Model of the response scope
     * @return Key for the {@link org.springframework.web.servlet.ViewResolver ViewResolver} bean
     */
    @GetMapping("/user/register")
    public String showRegister(Model model){
        model.addAttribute(USER, new User());
        añadirTiposUsuario(model);
        return REGISTER_VIEW;
    }

    /**
     * POST method of the newUser register action, checks the form data and rejects it with the according error message(s)
     *
     * @param usuario The newUser with the data collected from the web form
     * @param bindingResult The error wrapper of the validation errors
     * @param model Model of the response scope
     * @return Key for the {@link org.springframework.web.servlet.ViewResolver ViewResolver} bean
     */
    @PostMapping("/user/register")
    public String performRegister(@ModelAttribute(USER) @Validated(SpringFormGroup.class) User usuario,
                                  BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {
        añadirTiposUsuario(model);
        if (bindingResult.hasErrors()) {
            return REGISTER_VIEW;
        }

        if (checkifInUse(usuario, bindingResult)) {
            return REGISTER_VIEW;
        }

        userService.save(usuario);
        String msg = messageSource.getMessage("msg.userRegisteredSuccesfully",
                new String[]{usuario.getUsername()}, locale.resolveLocale(request));
        redirectAttributes.addFlashAttribute("message", msg);

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

        if (user.getId() == u.getId()) {
            try {
                msg = messageSource.getMessage("msg.selfDelete", null, locale.resolveLocale(request));
                redirectAttributes.addFlashAttribute("message", msg);
                request.logout();
                return "redirect:/";
            } catch (ServletException e) {
                throw e;
            }
        }

        redirectAttributes.addFlashAttribute("message", msg);
        return REDIRECT_USER_MANAGER;
    }

    private String handleDeleteUser(User u) {
        String msg;
        try {
            userService.delete(u);
            msg = messageSource.getMessage("msg.userDeleted", new String[]{u.getUsername()}, locale.resolveLocale(request));
        } catch (DataIntegrityViolationException dive) {
            msg = messageSource.getMessage(dive.getMessage(), null, locale.resolveLocale(request));
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
        añadirTiposUsuario(model);
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
        añadirTiposUsuario(model);
        if (bindingResult.hasErrors()) {
            return REGISTER_VIEW;
        }
        if (checkifInUse(newUser, bindingResult)) {
            return REGISTER_VIEW;
        }
        User oldUser = mergeWithOldUser(newUser);
        try {
            userService.saveOrUpdate(oldUser);
        } catch (DataIntegrityViolationException dive) {
            String msg = messageSource.getMessage(dive.getMessage(), null, locale.resolveLocale(request));
            redirectAttributes.addFlashAttribute("message", msg);
            return REDIRECT_USER_MANAGER;
        }

        User user = UserUtils.getCurrentUser();

        if (user.getId() == oldUser.getId()) {
            try {
                String msg = messageSource.getMessage("msg.selfEditLogin", null, locale.resolveLocale(request));
                redirectAttributes.addFlashAttribute("message", msg);
                request.logout();
                return "redirect:/";
            } catch (ServletException e) {
                throw e;
            }
        }

        String msg = messageSource.getMessage("msg.userEdited", new String[]{oldUser.getUsername()}, locale.resolveLocale(request));
        redirectAttributes.addFlashAttribute("message", msg);

        return REDIRECT_USER_MANAGER;
    }

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
     * Checs if a newUser's username or/and email are in use and adds the according message to the {@link BindingResult bindingResult}
     * @param usuario User that is going to be checked
     * @param bindingResult The error wrapper of the validation errors
     * @return Whether an error was found or not
     */
    private boolean checkifInUse(User usuario, BindingResult bindingResult) {
        boolean ret = false;
        User u = userService.findByEmail(usuario.getEmail());
        if (u != null && u.getId() != usuario.getId()) {
            String msg = messageSource.getMessage("error.user.emailTaken", null, locale.resolveLocale(request));
            bindingResult.addError(new FieldError(USER, "email", usuario.getEmail(), true, null, null, msg));
            ret = true;
        }
        u = userService.findByUsername(usuario.getUsername());
        if (u != null && u.getId() != usuario.getId()) {
            String msg = messageSource.getMessage("error.user.usernameTaken", null, locale.resolveLocale(request));
            bindingResult.addError(new FieldError(USER, "username", usuario.getUsername(), true, null, null, msg));
            ret = true;
        }
        return ret;
    }

    /**
     * Adds a {@link LinkedHashMap LinkedHashMap<String, String>} to the response model
     * with the different newUser types and their internationalized representation
     * @param model Model of the response scope
     */
    private void añadirTiposUsuario(Model model) {
        LinkedHashMap<String, String> roles = new LinkedHashMap<>();
        roles.put(User.ROLE_ADMIN,
                messageSource.getMessage("role.admin", null, locale.resolveLocale(request)));
        roles.put(User.ROLE_REPONEDOR,
                messageSource.getMessage("role.reponedor", null, locale.resolveLocale(request)));
        model.addAttribute("userTypes", roles);
    }

    private User getUserOrThrow(int id) {
        User u = userService.findById(id);
        if (u == null) {
            String reason = messageSource.getMessage("error.userNotExist", null, locale.resolveLocale(request));
            throw new ResourceNotFoundException(reason);
        }
        return u;
    }

}

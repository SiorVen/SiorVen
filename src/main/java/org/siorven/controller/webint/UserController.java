package org.siorven.controller.webint;

import org.siorven.model.User;
import org.siorven.model.validacion.SpringFormGroup;
import org.siorven.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.LocaleResolver;

import javax.servlet.http.HttpServletRequest;
import java.util.LinkedHashMap;

/**
 * Web controller for the user actions on the interface
 */
@Controller
public class UserController {

    /**
     * Data access logic for the access to the user data on the DB
     */
    @Autowired
    private UserService usuarioService;

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
     * GET method of the user register action, returns the register file
     * @param model Model of the response scope
     * @return Key for the {@link org.springframework.web.servlet.ViewResolver ViewResolver} bean
     */
    @GetMapping("/user/register")
    public String showRegister(Model model){
        model.addAttribute("usuario", new User());
        añadirTiposUsuario(model);
        return "register";
    }

    /**
     * POST method of the user register action, checks the form data and rejects it with the according error message(s)
     *
     * @param usuario The user with the data collected from the web form
     * @param bindingResult The error wrapper of the validation errors
     * @param model Model of the response scope
     * @return Key for the {@link org.springframework.web.servlet.ViewResolver ViewResolver} bean
     */
    @PostMapping("/user/register")
    public String performRegister(@ModelAttribute("usuario") @Validated(SpringFormGroup.class) User usuario,
                                  BindingResult bindingResult, Model model){
        añadirTiposUsuario(model);
        if (bindingResult.hasErrors()) {
            return"register";
        }

        if (checkifInUse(usuario, bindingResult)) {
            return "register";
        }

        usuarioService.save(usuario);

        return "redirect:/";
        //TODO: Redirigir a pagína de gestion de usuarios
    }

    /**
     * Checs if a user's username or/and email are in use and adds the according message to the {@link BindingResult bindingResult}
     * @param usuario User that is going to be checked
     * @param bindingResult The error wrapper of the validation errors
     * @return Whether an error was found or not
     */
    private boolean checkifInUse(User usuario, BindingResult bindingResult) {
        boolean ret = false;
        if (usuarioService.findByEmail(usuario.getEmail()) != null) {
            String msg = messageSource.getMessage("error.user.emailTaken", null, locale.resolveLocale(request));
            bindingResult.addError(new FieldError("usuario", "email", usuario.getEmail(), true, null, null, msg));
            ret = true;
        }
        if (usuarioService.findByUsername(usuario.getUsername()) != null) {
            String msg = messageSource.getMessage("error.user.usernameTaken", null, locale.resolveLocale(request));
            bindingResult.addError(new FieldError("usuario", "username", usuario.getUsername(), true, null, null, "Este usuario esta en uso"));
            ret = true;
        }
        return ret;
    }

    /**
     * Adds a {@link LinkedHashMap LinkedHashMap<String, String>} to the response model with the different user types and their internationalized representation
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

}

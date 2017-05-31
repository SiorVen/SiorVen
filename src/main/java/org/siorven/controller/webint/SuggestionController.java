package org.siorven.controller.webint;

import org.siorven.services.SuggestionService;
import org.siorven.services.XmlValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.LocaleResolver;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Andoni on 30/05/2017.
 */
@Controller
public class SuggestionController {

    public static final String MACHINE_MODEL = "machineModel";
    public static final String MODEL_REGISTER_VIEW = "machineMachineRegister";
    public static final String REDIRECT_MODEL_REGISTER = "redirect:/model/register";

    /**
     * Data access logic for the access to the Suggestion data on the DB
     */

    @Autowired
    private SuggestionService suggestionService;

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

    @Autowired
    private XmlValidationService validator;

    /**
     * Displays the suggestion manager
     *
     * @return Key for the {@link org.springframework.web.servlet.ViewResolver ViewResolver} bean
     */
    @GetMapping("/suggestion/manager")
    public String showSuggestionManager() {
        return "suggestionManager";
    }
}

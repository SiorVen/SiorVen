package org.siorven.controller.webint;

import org.siorven.services.SuggestionService;
import org.siorven.services.XmlValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.LocaleResolver;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Andoni on 30/05/2017.
 */
@Controller
public class SuggestionController {

    /**
     * Displays the suggestion manager
     *
     * @return Key for the {@link org.springframework.web.servlet.ViewResolver ViewResolver} bean
     */
    @GetMapping("/suggestion/manager/{id}")
    public String showSuggestionManager(@PathVariable int id, Model model) {
        model.addAttribute("machineId", id);
        return "suggestionManager";
    }
}

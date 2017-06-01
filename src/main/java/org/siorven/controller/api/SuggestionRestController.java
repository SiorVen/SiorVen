package org.siorven.controller.api;

import org.siorven.model.Suggestion;
import org.siorven.services.SuggestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.LocaleResolver;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * Created by Andoni on 31/05/2017.
 */
@RestController
public class SuggestionRestController {

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private LocaleResolver localeResolver;

    @Autowired
    private HttpServletRequest request;

    /**
     * Service with the suggestion data access logic
     */
    @Autowired
    private SuggestionService suggestionService;

    /**
     * Gets the json representation of all the suggestions
     *
     * @return The suggestions
     */
    @GetMapping(value = "/api/suggestion/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public Collection<Suggestion> getAll() {
        return suggestionService.findAll();
    }

    /**
     * Gets all the users for presentation on a Datatables js api table
     *
     * @return json object
     */
    @PostMapping("/api/suggestion/datatable/{id}")
    public Map<String, List<Map<String, String>>> giveSuggestionListDataForTable(@PathVariable int id) {
        List<Suggestion> suggestions = suggestionService.findByMachine(id);

        Map<String, List<Map<String, String>>> datatables = new HashMap<>();
        List<Map<String, String>> data = new ArrayList<>();
        datatables.put("data", data);
        for (Suggestion m : suggestions) {
            Map<String, String> entry = new HashMap<>();
            entry.put("generateDate", m.getGenerateDate() + "");
            entry.put("weight", String.valueOf(m.getWeight()));
            entry.put("reason", messageSource.getMessage(m.geyClassKey(), null, localeResolver.resolveLocale(request)));
            entry.put("consequence", m.toString(messageSource, localeResolver, request));
            data.add(entry);
        }
        return datatables;
    }

}

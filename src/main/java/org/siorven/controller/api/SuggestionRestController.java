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
 * Rest API with the suggestions
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
        for (Suggestion s : suggestions) {
            Map<String, String> entry = new HashMap<>();
            entry.put("suggestion", s.printSuggestion(messageSource, localeResolver.resolveLocale(request)));
            entry.put("generateDate", s.getGenerateDate() + "");
            entry.put("weight", String.valueOf(s.getWeight()));
            entry.put("method", messageSource.getMessage(s.geyClassKey(), null, localeResolver.resolveLocale(request)));
            entry.put("reason", s.printReason(messageSource, localeResolver.resolveLocale(request)));
            data.add(entry);
        }
        return datatables;
    }


}

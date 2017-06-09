package org.siorven.controller.api;

import org.siorven.model.Machine;
import org.siorven.model.Recollector;
import org.siorven.services.RecollectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.LocaleResolver;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Rest controller for the recollector related actions
 */
@RestController
public class RecollectorRestController {


    @Autowired
    private RecollectorService recollectorService;

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private LocaleResolver localeResolver;

    @Autowired
    private HttpServletRequest request;

    /**
     * Gets all the users for presentation on a Datatables js api table
     *
     * @return json object
     */
    @PostMapping("/api/recollector/datatable/")
    public Map<String, List<Map<String, String>>> giveSuggestionListDataForTable() {
        List<Recollector> recollectors = recollectorService.findAll();

        Map<String, List<Map<String, String>>> datatables = new HashMap<>();
        List<Map<String, String>> data = new ArrayList<>();
        datatables.put("data", data);
        for (Recollector recollector : recollectors) {
            Machine machine = recollector.getMachine();
            String machineName;
            if (machine == null) {
                machineName = messageSource.getMessage("recollector.notLinked", null, localeResolver.resolveLocale(request));
            } else {
                machineName = machine.getAlias();
            }
            Map<String, String> entry = new HashMap<>();
            entry.put("alias", recollector.getAlias());
            entry.put("connection", String.valueOf(recollector.isConnection()));
            entry.put("machineName", machineName);
            data.add(entry);
        }
        return datatables;
    }
}

package org.siorven.controller.api;

import org.siorven.model.Machine;
import org.siorven.services.MachineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.LocaleResolver;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * Controller of the method on the user Rest Api for the machines
 *
 * @see Machine
 */

@RestController
public class MachineRestController {

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private LocaleResolver localeResolver;

    @Autowired
    private HttpServletRequest request;

    /**
     * Service with the machine data access logic
     */
    @Autowired
    private MachineService machineService;

    /**
     * Gets the json representation of all the machines
     *
     * @return The machines
     */
    @GetMapping(value = "/api/machine/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public Collection<Machine> getAll() {
        return machineService.findAll();
    }

    /**
     * Gets all the users for presentation on a Datatables js api table
     *
     * @return json object
     */
    @PostMapping("/api/machine/datatable")
    private Map<String, List<Map<String, String>>> giveMachineListDataForTable() {
        List<Machine> machines = machineService.findAll();

        Map<String, List<Map<String, String>>> datatables = new HashMap<>();
        List<Map<String, String>> data = new ArrayList<>();
        datatables.put("data", data);
        for (Machine m : machines) {
            Map<String, String> entry = new HashMap<>();
            entry.put("id", Integer.toString(m.getId()));
            entry.put("alias", m.getAlias());
            entry.put("modelRef", m.getMachineModel().getReference());
            entry.put("manufacturer", m.getMachineModel().getManufacturer());
            data.add(entry);
        }
        return datatables;
    }

}

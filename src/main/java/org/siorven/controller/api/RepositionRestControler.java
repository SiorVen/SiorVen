package org.siorven.controller.api;

import org.siorven.model.MachineResource;
import org.siorven.model.Resource;
import org.siorven.services.MachineResourceService;
import org.siorven.services.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * Rest controller for the reposition related actions
 */
@RestController
public class RepositionRestControler {

    /**
     * Service with the suggestion data access logic
     */
    @Autowired
    private MachineResourceService machineResourceService;

    /**
     * Service with the machine data access logic
     */
    @Autowired
    private ResourceService resourceService;

    /**
     * Gets the json representation of all the suggestions
     *
     * @return The suggestions
     */
    @GetMapping(value = "/api/reposition/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public Collection<MachineResource> getAll() {
        return machineResourceService.findAll();
    }

    /**
     * Gets all the users for presentation on a Datatables js api table
     *
     * @return json object
     */
    @PostMapping("/api/reposition/datatable/{id}")
    public Map<String, List<Map<String, String>>> giveSuggestionListDataForTable(@PathVariable int id) {
        List<MachineResource> suggestions = machineResourceService.findByMachine(id);

        Map<String, List<Map<String, String>>> datatables = new HashMap<>();
        List<Map<String, String>> data = new ArrayList<>();
        datatables.put("data", data);
        for (MachineResource m : suggestions) {
            Map<String, String> entry = new HashMap<>();
            entry.put("name", m.getResource().getName() + "");
            entry.put("quantity", String.valueOf(m.getQuantity()));
            entry.put("id", String.valueOf(m.getId()));
            data.add(entry);
        }
        return datatables;
    }

    /**
     * Searches the resources by their name with a like %name% sql query
     *
     * @param resourceName The string to search by
     * @return A json structure with the results
     */
    @RequestMapping(value = "api/reposition/search", produces = "application/json", params = {"term"})
    @ResponseBody
    public Map<String, Object> findAll(@RequestParam("term") String resourceName) {
        Map<String, Object> map = new HashMap<>();
        List<Resource> admins = resourceService.findBylikeName(resourceName);

        for (Resource resource : admins) {
            map.put(Integer.toString(resource.getId()), resource.getName());
        }
        return map;
    }

}

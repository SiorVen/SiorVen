package org.siorven.controller.api;

import org.siorven.model.Resource;
import org.siorven.services.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Rest controller for the resources
 */
@RestController
public class ResourceRestController {

    @Autowired
    ResourceService resourceService;

    /**
     * Searches the resources by their name with a like %name% sql query
     *
     * @param resourceName The string to search by
     * @return A json structure with the results
     */
    @RequestMapping(value = "api/resource/search", produces = "application/json", params = {"term"})
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

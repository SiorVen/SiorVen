package org.siorven.controller.api;

import org.siorven.model.Resource;
import org.siorven.model.ResourceType;
import org.siorven.services.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.LocaleResolver;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ander on 30/05/2017.
 */
@RestController
public class ResourceRestController {

    @Autowired
    ResourceService resourceService;

    @Autowired
    LocaleResolver localeResolver;
    @Autowired
    HttpServletRequest request;
    @Autowired
    MessageSource messageSource;


    @RequestMapping(value = "api/resource/search", produces = "application/json", params = {"term"})
    @ResponseBody
    public Map<String, Object> findAll(@RequestParam("term") String nombreResource) {
        Map<String, Object> map = new HashMap<>();
        List<Resource> admins = resourceService.findBylikeName(nombreResource);

        for (int i = 0; i < admins.size(); i++) {
            Resource resource = admins.get(i);
            map.put(resource.getId() + "", resource.getName() + "");
        }
        return map;
    }

}

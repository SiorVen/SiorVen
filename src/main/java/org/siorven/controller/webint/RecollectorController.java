package org.siorven.controller.webint;

import org.siorven.model.Machine;
import org.siorven.model.Recollector;
import org.siorven.services.MachineService;
import org.siorven.services.RecollectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by Gorospe on 31/05/2017.
 */
@Controller
public class RecollectorController {

    public static final String REDIRECT_RECOLLECTOR_MANAGER = "redirect:/recollector/manager/";
    @Autowired
    private RecollectorService recollectorService;

    @Autowired
    private MachineService machineService;

    @GetMapping("/recollector/manager/{id}")
    public String showSuggestionManager(@PathVariable int id, Model model) {
        model.addAttribute("machineId", id);
        return "recollectorManager";
    }

    @PostMapping(value = "/recollector/link", params = {"machineId", "recollectorAlias"})
    public String linkRecollectorToMachine(@RequestParam("machineId") Integer machineId, @RequestParam("recollectorAlias") String recAlias, Model model) {
        Recollector r = recollectorService.findByAlias(recAlias);
        boolean linked = false;
        if (r != null && r.getMachine() == null) {
            Machine m = machineService.findById(machineId);
            if (m != null) {
                r.setMachine(m);
                recollectorService.edit(r);
                linked = true;
            }
        }
        return REDIRECT_RECOLLECTOR_MANAGER + machineId.intValue();
    }
}

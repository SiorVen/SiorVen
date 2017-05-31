package org.siorven.controller.webint;

import org.siorven.exceptions.ResourceAlreadyRegistered;
import org.siorven.model.Machine;
import org.siorven.model.Recollector;
import org.siorven.model.validacion.SpringFormGroup;
import org.siorven.services.MachineService;
import org.siorven.services.RecollectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Created by Gorospe on 31/05/2017.
 */
@Controller
public class RecollectorController {

    @Autowired
    private RecollectorService recollectorService;

    @Autowired
    private MachineService machineService;

    @GetMapping("/recollector/manager/{id}")
    public String showSuggestionManager(@PathVariable int id, Model model) {
        model.addAttribute("machineId", id);
        return "recollectorManager";
    }

    //TODO Gestionar excepciones
    @PostMapping(value = "/recollector/link", params = {"machineId", "recollectorAlias"})
    public String performRegister(@RequestParam("machineId") Integer machineId, @RequestParam("recollectorAlias") String recAlias, Model model) {
        Recollector r = recollectorService.findByAlias(recAlias);

        if(r != null && r.getMachine() == null) {
            Machine m = machineService.findById(machineId);
            if(m != null) {
                r.setMachine(m);
                recollectorService.edit(r);
            }
        }

        return "redirect:/recollector/manager/"+machineId.intValue();
    }
}

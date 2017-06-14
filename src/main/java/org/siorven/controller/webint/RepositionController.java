package org.siorven.controller.webint;

import org.siorven.controller.webint.forms.MachineResourceForm;
import org.siorven.model.*;
import org.siorven.model.validacion.SpringFormEditGroup;
import org.siorven.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Contains the mappings for the reposition related actions
 */
@Controller
public class RepositionController {


    private static final String REDIRECT_REPOSITION_MANAGER = "redirect:/reposition/manager/";
    private static final String REPOSITION_MANAGER = "repositionManager";
    /**
     * Service with the suggestion data access logic
     */
    @Autowired
    private MachineResourceService machineResourceService;

    /**
     * Service with the suggestion data access logic
     */
    @Autowired
    private ResourceService resourceService;

    /**
     * Service with the suggestion data access logic
     */
    @Autowired
    private SlotService slotService;


    /**
     * Service with the suggestion data access logic
     */
    @Autowired
    private MachineSlotService machineSlotService;

    /**
     * Service with the suggestion data access logic
     */
    @Autowired
    private MachineService machineService;

    /**
     * Service with the suggestion data access logic
     */
    @Autowired
    private MachineIngredientService machineIngredientService;

    /**
     * Displays the suggestion manager
     *
     * @return Key for the {@link org.springframework.web.servlet.ViewResolver ViewResolver} bean
     */
    @GetMapping("/reposition/manager/{id}")
    public String showRepositionManager(@PathVariable int id, Model model) {
        model.addAttribute("machineId", id);
        return REPOSITION_MANAGER;
    }

    /**
     * Shows the edit reposition page
     *
     * @param id id of the reposition
     * @return Key for the {@link org.springframework.web.servlet.ViewResolver ViewResolver} bean
     */
    @GetMapping("/reposition/edit/{id}")
    public String showEditReposition(@PathVariable("id") int id, Model model) {

        MachineResource resource = machineResourceService.findById(id);
        anadirSlots(model, resource.getMachineSlot().getMachine().getId());
        anadirSlotAdd(model, resource.getMachineSlot().getMachine().getId());
        LinkedHashMap<Integer, String> modeHM = ((LinkedHashMap<Integer,String>)model.asMap().get("slots"));
        LinkedHashMap<Integer, String> currentSlot = new LinkedHashMap<>();
        currentSlot.put(resource.getMachineSlot().getSlot().getId(), resource.getMachineSlot().getSlot().getName());

        currentSlot.putAll(modeHM);
        model.addAttribute("slots", currentSlot);
        MachineResourceForm m = new MachineResourceForm();
        m.setProduct(resource.getResource().getName());
        m.setQuantity(resource.getQuantity());
        m.setId(id);
        m.setMachineId(resource.getMachineSlot().getMachine().getId());
        model.addAttribute("machineResource", m);
        return "repositionEdit";
    }

    /**
     * GET method of the reposition register action, returns the register file
     *
     * @param model Model of the response scope
     * @return Key for the {@link org.springframework.web.servlet.ViewResolver ViewResolver} bean
     */
    @GetMapping("/reposition/add/{id}")
    public String showRepositionRegisterInterface(@PathVariable("id") int id, Model model) {
        anadirSlotAdd(model, id);
        MachineResourceForm m = new MachineResourceForm();
        m.setId(id);
        model.addAttribute("machineResourceAdd", m);
        return "repositionAdd";
    }

    /**
     * POST method of the new machine register action, checks the form data and rejects it with the according error message(s)
     *
     * @param machineResourceForm The machine with the data collected from the web form
     * @param bindingResult       The error wrapper of the validation errors
     * @return Key for the {@link org.springframework.web.servlet.ViewResolver ViewResolver} bean
     */
    @PostMapping("/reposition/register")
    public String performMachineRegister(@ModelAttribute("machineResourceAdd") @Validated MachineResourceForm machineResourceForm, BindingResult bindingResult) throws IOException {

        if (bindingResult.hasErrors()) {
            return REPOSITION_MANAGER;
        }
        MachineResource m = new MachineResource();
        MachineSlot slot = new MachineSlot();
        slot.setSlot(slotService.findById(machineResourceForm.getMachineSlotId()));
        slot.setMachine(machineService.findById(machineResourceForm.getId()));
        machineSlotService.save(slot);
        m.setResource(resourceService.findByName(machineResourceForm.getProduct()));
        m.setMachineSlot(slot);
        m.setQuantity(machineResourceForm.getQuantity());
        machineResourceService.save(m);

        return REDIRECT_REPOSITION_MANAGER + machineResourceForm.getId();
    }

    /**
     * Edits a reposition
     *
     * @param machineResourceForm reposition to update
     * @return Key for the {@link org.springframework.web.servlet.ViewResolver ViewResolver} bean
     */
    @PostMapping("/reposition/edit")
    public String editReposition(@ModelAttribute("machineResource") @Validated MachineResourceForm machineResourceForm, BindingResult bindingResult, Model model) throws ServletException {
        MachineResource resource = machineResourceService.findById(machineResourceForm.getId());
        if (bindingResult.hasErrors()) {
            return REPOSITION_MANAGER;
        }
        MachineIngredient mi = machineIngredientService.findByMachineResource(resource);
        machineIngredientService.delete(mi);
        machineResourceService.delete(resource.getId());
        machineSlotService.delete(resource.getMachineSlot());
        MachineSlot ms = new MachineSlot();
        ms.setSlot(slotService.findById(machineResourceForm.getMachineSlotId()));
        ms.setMachine(machineService.findById(machineResourceForm.getMachineId()));
        machineSlotService.save(ms);
        resource.setQuantity(machineResourceForm.getQuantity());
        resource.setMachineSlot(ms);
        resource.getMachineSlot().setMachine(machineService.findById(machineResourceForm.getMachineId()));
        machineResourceService.save(resource);
        mi.setResource(resource);
        machineIngredientService.save(mi);
        return REDIRECT_REPOSITION_MANAGER + machineResourceForm.getMachineId();
    }

    /**
     * Fills a reposition
     *
     * @param machineResourceForm reposition to update
     * @return Key for the {@link org.springframework.web.servlet.ViewResolver ViewResolver} bean
     */
    @PostMapping(value = "/reposition/fill", params = {"id"})
    public String fillReposition(@RequestParam("id") Integer id, @ModelAttribute("machineResource") @Validated(SpringFormEditGroup.class) MachineResourceForm machineResourceForm, BindingResult bindingResult) throws ServletException {

        MachineResource m = machineResourceService.findById(id);
        m.setQuantity(m.getMachineSlot().getSlot().getCapacity());
        machineResourceService.edit(m);
        return REDIRECT_REPOSITION_MANAGER + machineResourceService.findById(machineResourceForm.getId()).getMachineSlot().getMachine().getId();
    }

    /**
     * Adds the free slots to a map and puts that map in the model
     *
     * @param model The request/response model
     */
    private void anadirSlotAdd(Model model, int id) {
        List<Slot> m = slotService.findFree(id);
        LinkedHashMap<Integer, String> roles = new LinkedHashMap<>();
        for (int i = 0; i < m.size(); i++) {
            roles.put(m.get(i).getId(), m.get(i).getName());
        }
        model.addAttribute("slots", roles);
    }

    /**
     * Puts the slots of a machine in a map in the request/response model
     *
     * @param model     The request/response model
     * @param machineId The id of the machine
     */
    private void anadirSlots(Model model, int machineId) {
        List<MachineSlot> m = machineSlotService.findByMachineId(machineId);
        LinkedHashMap<Integer, String> roles = new LinkedHashMap<>();
        for (int i = 0; i < m.size(); i++) {
            roles.put(m.get(i).getId(), m.get(i).getSlot().getName());
        }
        model.addAttribute("slots", roles);

    }


}

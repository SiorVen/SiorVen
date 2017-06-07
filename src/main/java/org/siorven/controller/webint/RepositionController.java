package org.siorven.controller.webint;

import org.siorven.controller.webint.forms.MachineResourceForm;
import org.siorven.model.MachineResource;
import org.siorven.model.MachineSlot;
import org.siorven.model.Slot;
import org.siorven.model.validacion.SpringFormEditGroup;
import org.siorven.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Created by Andoni on 01/06/2017.
 */
@Controller
public class RepositionController {


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
     * Message source for internationalization of the content
     */
    @Autowired
    private MessageSource messageSource;

    /**
     * Locale resolver for the sent cookies or session attributes
     */
    @Autowired
    private LocaleResolver locale;

    /**
     * Currently handled HTTP Request
     */
    @Autowired
    private HttpServletRequest request;

    @Autowired
    private XmlValidationService validator;

    /**
     * Displays the suggestion manager
     *
     * @return Key for the {@link org.springframework.web.servlet.ViewResolver ViewResolver} bean
     */
    @GetMapping("/reposition/manager/{id}")
    public String showRepositionManager(@PathVariable int id, Model model) {
        model.addAttribute("machineId", id);
        return "repositionManager";
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
        MachineResourceForm m = new MachineResourceForm();
        m.setProduct(resource.getResource().getName());
        m.setQuantity(resource.getQuantity());
        m.setId(id);
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
     * @param machineResourceForm   The machine with the data collected from the web form
     * @param bindingResult The error wrapper of the validation errors
     * @return Key for the {@link org.springframework.web.servlet.ViewResolver ViewResolver} bean
     */
    @PostMapping("/reposition/register")
    public String performMachineRegister(@ModelAttribute("machineResourceAdd") @Validated MachineResourceForm machineResourceForm, BindingResult bindingResult,
                                         RedirectAttributes redirectAttributes) throws IOException {

        if (bindingResult.hasErrors()) {
            return "repositionManager";
        }
        MachineResource m  = new MachineResource();
        MachineSlot slot = new MachineSlot();
        slot.setSlot(slotService.findById(machineResourceForm.getMachineSlotId()));
        slot.setMachine(machineService.findById(machineResourceForm.getId()));
        machineSlotService.save(slot);
        m.setResource(resourceService.findByName(machineResourceForm.getProduct()));
        m.setMachineSlot(slot);
        m.setQuantity(machineResourceForm.getQuantity());
        machineResourceService.save(m);

        return "redirect:/reposition/manager/"+machineResourceForm.getId();
    }
    /**
     * Edits a reposition
     *
     * @param machineResourceForm        reposition to update
     * @param redirectAttributes Redirected attributes to the manager
     * @return Key for the {@link org.springframework.web.servlet.ViewResolver ViewResolver} bean
     */
    @PostMapping("/reposition/edit")
    public String editReposition(@ModelAttribute("machineResource") @Validated(SpringFormEditGroup.class) MachineResourceForm machineResourceForm, RedirectAttributes redirectAttributes, BindingResult bindingResult, Model model) throws ServletException {
        MachineResource resource = machineResourceService.findById(machineResourceForm.getId());
        if (bindingResult.hasErrors()) {
            return "repositionManager";
        }
        anadirSlots(model, resource.getMachineSlot().getMachine().getId());
        resource.setQuantity(machineResourceForm.getQuantity());
        resource.setMachineSlot(machineSlotService.findById(machineResourceForm.getMachineSlotId()));
        machineResourceService.edit(resource);
        return "redirect:/reposition/manager/"+resource.getMachineSlot().getMachine().getId();
    }

    /**
     * Fills a reposition
     *
     * @param machineResourceForm        reposition to update
     * @param redirectAttributes Redirected attributes to the manager
     * @return Key for the {@link org.springframework.web.servlet.ViewResolver ViewResolver} bean
     */
    @PostMapping(value = "/reposition/fill", params = {"id"})
    public String fillReposition(@RequestParam("id") Integer id, @ModelAttribute("machineResource") @Validated(SpringFormEditGroup.class) MachineResourceForm machineResourceForm, RedirectAttributes redirectAttributes, BindingResult bindingResult, Model model) throws ServletException {

        MachineResource m = machineResourceService.findById(id);
        m.setQuantity(m.getMachineSlot().getSlot().getCapacity());
        machineResourceService.edit(m);
        return "redirect:/reposition/manager/"+machineResourceService.findById(machineResourceForm.getId()).getMachineSlot().getMachine().getId();
    }

    private  void anadirSlotAdd(Model model, int machineId) {
        List<Slot> m = slotService.findFree();
        LinkedHashMap<Integer, String> roles = new LinkedHashMap<>();
        for (int i = 0; i < m.size(); i++) {
            roles.put(m.get(i).getId(), m.get(i).getName());
        }
        model.addAttribute("slots", roles);
    }
    private void anadirSlots(Model model, int machineId) {
        List<MachineSlot> m = machineSlotService.findByMachineId(machineId);
        LinkedHashMap<Integer, String> roles = new LinkedHashMap<>();
        for (int i = 0; i < m.size(); i++) {
            roles.put(m.get(i).getId(), m.get(i).getSlot().getName());
        }
        model.addAttribute("slots", roles);

    }


}

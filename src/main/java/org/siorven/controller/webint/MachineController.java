package org.siorven.controller.webint;

import org.siorven.controller.handlers.errors.ResourceNotFoundException;
import org.siorven.controller.webint.forms.MachineEditForm;
import org.siorven.controller.webint.forms.MachineViewForm;
import org.siorven.model.Machine;
import org.siorven.model.MachineModel;
import org.siorven.model.validacion.SpringFormEditGroup;
import org.siorven.services.MachineModelService;
import org.siorven.services.MachineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Web controller for the new Machine actions on the interface
 */
@Controller
public class MachineController {
    private static final String MACHINE_MODEL = "machineModel";
    private static final String MODEL_REGISTER_VIEW = "machineMachineRegister";
    private static final String REDIRECT_MODEL_REGISTER = "redirect:/model/register";
    private static final String MACHINE_MANAGER_VIEW = "machineManager";


    /**
     * Data access logic for the access to the newUser data on the DB
     */
    @Autowired
    private MachineService machineService;

    @Autowired
    private MachineModelService machineModelService;

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

    /**
     * Displays the machine manager
     *
     * @return Key for the {@link org.springframework.web.servlet.ViewResolver ViewResolver} bean
     */
    @GetMapping("/machine/manager")
    public String showMachineManager() {
        return "machineManager";
    }

    /**
     * Shows the edit machine page
     *
     * @param id id of the machine
     * @return Key for the {@link org.springframework.web.servlet.ViewResolver ViewResolver} bean
     */
    @GetMapping("/machine/edit/{id}")
    public String showEditMachine(@PathVariable("id") int id, Model model) {
        addModels(model);
        Machine u = getMachineOrThrow(id);
        MachineEditForm m = new MachineEditForm();
        m.setAlias(u.getAlias());
        m.setMachineModelId(u.getMachineModel().getId());
        m.setId(id);
        model.addAttribute("machine", m);
        return "machineEdit";
    }

    /**
     * GET method of the machine register action, returns the register file
     *
     * @param model Model of the response scope
     * @return Key for the {@link org.springframework.web.servlet.ViewResolver ViewResolver} bean
     */
    @GetMapping("/machine/register")
    public String showMachineRegisterInterface(Model model) {
        addModels(model);
        model.addAttribute("machineModelRegister", new MachineEditForm());
        return MODEL_REGISTER_VIEW;
    }

    /**
     * POST method of the new machine register action, checks the form data and rejects it with the according error message(s)
     *
     * @param machineForm   The machine with the data collected from the web form
     * @param bindingResult The error wrapper of the validation errors
     * @return Key for the {@link org.springframework.web.servlet.ViewResolver ViewResolver} bean
     */
    @PostMapping("/machine/register")
    public String performMachineRegister(@ModelAttribute(MACHINE_MODEL) @Validated MachineEditForm machineForm, BindingResult bindingResult,
                                         RedirectAttributes redirectAttributes) throws IOException {

        if (bindingResult.hasErrors()) {
            return REDIRECT_MODEL_REGISTER;
        }
        Machine m = new Machine();
        m.setAlias(machineForm.getAlias());
        m.setMachineModel(machineModelService.findById(machineForm.getMachineModelId()));
        machineService.save(m);

        return MACHINE_MANAGER_VIEW;
    }


    /**
     * Edits a machine
     *
     * @param machineForm        newUser to update
     * @param redirectAttributes Redirected attributes to the manager
     * @return Key for the {@link org.springframework.web.servlet.ViewResolver ViewResolver} bean
     */
    @PostMapping("/machine/edit")
    public String editMachine(@ModelAttribute("machine") @Validated(SpringFormEditGroup.class) MachineEditForm machineForm, RedirectAttributes redirectAttributes, BindingResult bindingResult, Model model) throws ServletException {
        addModels(model);
        if (bindingResult.hasErrors()) {
            return MACHINE_MANAGER_VIEW;
        }
        Machine m = getMachineOrThrow(machineForm.getId());
        m.setAlias(machineForm.getAlias());
        m.setMachineModel(machineModelService.findById(machineForm.getMachineModelId()));
        machineService.edit(m);
        return MACHINE_MANAGER_VIEW;
    }

    /**
     * Shows a users page
     *
     * @param id    id of the newUser
     * @param model Response model
     * @return Key for the {@link org.springframework.web.servlet.ViewResolver ViewResolver} bean
     */
    @GetMapping("/machine/{id}")
    public String showMachine(@PathVariable("id") int id, Model model) {

        Machine u = getMachineOrThrow(id);
        MachineViewForm m = new MachineViewForm();
        m.setAlias(u.getAlias());
        m.setId(id);
        model.addAttribute("machineView", m);
        model.addAttribute("reference", u.getMachineModel().getReference());
        model.addAttribute("manufacturer", u.getMachineModel().getManufacturer());

        return "machineView";
    }

    /**
     * Adds the machine models to the response model
     *
     * @param model The response/request model
     */
    private void addModels(Model model) {
        List<MachineModel> m = machineModelService.findAll();
        LinkedHashMap<Integer, String> roles = new LinkedHashMap<>();
        for (MachineModel aM : m) {
            roles.put(aM.getId(), aM.getReference());
        }
        model.addAttribute("models", roles);
    }

    /**
     * Gets a machine from the database or throws a {@link ResourceNotFoundException}
     *
     * @param id the machine ID
     * @return The machine if found
     */
    private Machine getMachineOrThrow(int id) {
        Machine u = machineService.findById(id);
        if (u == null) {
            String reason = messageSource.getMessage("error.machineNotExist", null, locale.resolveLocale(request));
            throw new ResourceNotFoundException(reason);
        }
        return u;
    }
}

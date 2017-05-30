package org.siorven.controller.webint;

import org.apache.commons.io.FilenameUtils;
import org.siorven.controller.handlers.errors.ResourceNotFoundException;
import org.siorven.controller.webint.forms.MachineEditForm;
import org.siorven.controller.webint.forms.MachineModelForm;
import org.siorven.model.Machine;
import org.siorven.model.MachineModel;
import org.siorven.services.MachineModelService;
import org.siorven.services.MachineService;
import org.siorven.services.XmlValidationService;
import org.siorven.utils.machineXmlParse.ModelXmlToModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.LocaleResolver;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.xml.transform.stream.StreamSource;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Web controller for the new Machine actions on the interface
 */
@Controller
public class ModelController {

    public static final String MACHINE_MODEL = "machineModel";
    public static final String MODEL_REGISTER_VIEW = "machineMachineRegister";
    public static final String REDIRECT_MODEL_REGISTER = "redirect:/model/register";

    /**
     * Data access logic for the access to the newUser data on the DB
     */

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

    @Autowired
    private XmlValidationService validator;

    /**
     * GET method of the machine register action, returns the register file
     * @param model Model of the response scope
     * @return Key for the {@link org.springframework.web.servlet.ViewResolver ViewResolver} bean
     */
    @GetMapping("/model/register")
    public String showMachineRegister(Model model){
        model.addAttribute("machineModel", new MachineModelForm());
        return MODEL_REGISTER_VIEW;
    }

    /**
     * POST method of the new machine register action, checks the form data and rejects it with the according error message(s)
     *
     * @param machine The machine with the data collected from the web form
     * @param bindingResult The error wrapper of the validation errors
     * @return Key for the {@link org.springframework.web.servlet.ViewResolver ViewResolver} bean
     */
    @PostMapping("/model/register")
    public String performRegister(@ModelAttribute(MACHINE_MODEL) @Validated MachineModelForm machine, BindingResult bindingResult,
                                  RedirectAttributes redirectAttributes) throws IOException {

        if (bindingResult.hasErrors()) {
            return REDIRECT_MODEL_REGISTER;
        }

        if (machine.getFile().isEmpty()) {
            String msg = messageSource.getMessage("error.model.notNull", null, locale.resolveLocale(request));
            bindingResult.addError(new FieldError(MACHINE_MODEL, "file", machine.getFile(), true, null, null, msg));
            return REDIRECT_MODEL_REGISTER;
        }
        String ext = FilenameUtils.getExtension(machine.getFile().getOriginalFilename());
        if (isSuported(ext)) {
            try {
                validator.ValidateMachine(new StreamSource(machine.getFile().getInputStream()));
            } catch (IllegalArgumentException iae) {
                String msg = messageSource.getMessage("error.model.validation", null, locale.resolveLocale(request));
                bindingResult.addError(new FieldError(MACHINE_MODEL, "file", machine.getFile(), true, null, null, msg));
                return REDIRECT_MODEL_REGISTER;
            }
            MachineModel machineModel = ModelXmlToModel.Xml2Model(machine.getFile());
            machineModelService.save(machineModel);
            String msg = messageSource.getMessage("message.model.success",
                            new String[]{machineModel.getManufacturer() + " " + machineModel.getReference()}, locale.resolveLocale(request));
            redirectAttributes.addFlashAttribute("message", msg);
        } else {
            String msg = messageSource.getMessage("error.model.notXml", null, locale.resolveLocale(request));
            bindingResult.addError(new FieldError(MACHINE_MODEL, "file", machine.getFile(), true, null, null, msg));
            return REDIRECT_MODEL_REGISTER;
        }

        return REDIRECT_MODEL_REGISTER; //TODO: Should return a redirect to the machine manager

    }

    private boolean isSuported(String ext) {
        String normalizedExtension = ext.toLowerCase();
        boolean flag = false;
            if (normalizedExtension.compareTo("xml") == 0) flag = true;
        return flag;
    }

}

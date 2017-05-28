package org.siorven.controller.webint;

import org.apache.commons.io.FilenameUtils;
import org.siorven.model.Machine;
import org.siorven.model.validacion.SpringFormGroup;
import org.siorven.services.MachineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 * Web controller for the new Machine actions on the interface
 */
@Controller
public class MachineController {

    public static final String MACHINE = "machine";
    public static final String MACHINE_REGISTER_VIEW = "machineRegister";
    private static final String UPLOADED_FOLDER = "rsc/xml/";

    /**
     * Data access logic for the access to the newUser data on the DB
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

    /**
     * GET method of the machine register action, returns the register file
     * @param model Model of the response scope
     * @return Key for the {@link org.springframework.web.servlet.ViewResolver ViewResolver} bean
     */
    @GetMapping("/machine/register")
    public String showMachineRegister(Model model){
        model.addAttribute(MACHINE, new Machine());
        return MACHINE_REGISTER_VIEW;
    }

    /**
     * POST method of the new machine register action, checks the form data and rejects it with the according error message(s)
     *
     * @param machine The machine with the data collected from the web form
     * @param bindingResult The error wrapper of the validation errors
     * @param model Model of the response scope
     * @return Key for the {@link org.springframework.web.servlet.ViewResolver ViewResolver} bean
     */
    @PostMapping("/machine/register")
    public String performRegister(@ModelAttribute(MACHINE) @Validated(SpringFormGroup.class) Machine machine,
                                  BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            return MACHINE_REGISTER_VIEW;
        }
        if (checkifInUse(machine, bindingResult)) {
            return MACHINE_REGISTER_VIEW;
        }

        try {
            byte[] bytes = machine.getModel().getBytes();
            String ext = FilenameUtils.getExtension(machine.getModel().getOriginalFilename());
            if(isSuported(ext)){
                String realFolder = request.getServletContext().getRealPath(UPLOADED_FOLDER);
                Path path = Paths.get(realFolder + machine.getModel().getOriginalFilename());
                System.out.println(path.toAbsolutePath().toString());
                Files.write(path, bytes);
            }
            machine.setMachineProductList(new ArrayList<>());
            machine.setMachineResourceList(new ArrayList<>());
            machineService.save(machine);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();
        }




        return MACHINE_REGISTER_VIEW;

    }

    /**
     * Checs if a newMachine's username or/and email are in use and adds the according message to the {@link BindingResult bindingResult}
     * @param machine machine that is going to be checked
     * @param bindingResult The error wrapper of the validation errors
     * @return Whether an error was found or not
     */
    private boolean checkifInUse(Machine machine, BindingResult bindingResult) {
        boolean ret = false;

        Machine m = machineService.findById(machine.getId());
        if(m != null && m.getId() != machine.getId()) {
            String msg = messageSource.getMessage("error.machine.registeredMachine", null, locale.resolveLocale(request));
            bindingResult.addError(new FieldError(MACHINE, "id", machine.getId(), true, null, null, msg));
            ret = true;
        }
        return ret;
    }

    private boolean isSuported(String ext) {
        String normalizedExtension = ext.toLowerCase();
        boolean flag = false;
            if (normalizedExtension.compareTo("xml") == 0) flag = true;
        return flag;
    }

}

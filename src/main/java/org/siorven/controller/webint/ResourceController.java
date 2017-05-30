package org.siorven.controller.webint;

import org.siorven.exceptions.ResourceAlreadyRegistered;
import org.siorven.model.Resource;
import org.siorven.model.ResourceType;
import org.siorven.model.validacion.SpringFormGroup;
import org.siorven.services.ResourceService;
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
import java.util.LinkedHashMap;

/**
 * Created by Gorospe on 29/05/2017.
 */
@Controller
public class ResourceController {
    public static final String RESOURCE_REGISTER_VIEW = "resourceRegister";
    public static final String RESOURCE = "resource";
    public static final String REDIRECT_PRODUCT_REGISTER = "redirect:/product/register";


    @Autowired
    private ResourceService resourceService;

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
     * GET method of the newResource register action, returns the register file
     * @param model MachineModel of the response scope
     * @return Key for the {@link org.springframework.web.servlet.ViewResolver ViewResolver} bean
     */
    @GetMapping("/resource/register")
    public String showRegister(Model model){
        model.addAttribute(RESOURCE, new Resource());
        addResourceTypes(model);
        return RESOURCE_REGISTER_VIEW;
    }

    /**
     * POST method of the newUser register action, checks the form data and rejects it with the according error message(s)
     *
     * @param resource The newResource with the data collected from the web form
     * @param bindingResult The error wrapper of the validation errors
     * @param model MachineModel of the response scope
     * @return Key for the {@link org.springframework.web.servlet.ViewResolver ViewResolver} bean
     */
    @PostMapping("/resource/register")
    public String performRegister(@ModelAttribute(RESOURCE) @Validated(SpringFormGroup.class) Resource resource,
                                  BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {
        addResourceTypes(model);
        if (bindingResult.hasErrors()) {
            return RESOURCE_REGISTER_VIEW;
        }
        try {
            resourceService.save(resource);
        }catch (ResourceAlreadyRegistered e){
            String msg = messageSource.getMessage(e.getMessage(),
                    new String[]{resource.getName()}, locale.resolveLocale(request));
            bindingResult.addError(new FieldError(RESOURCE, "name", resource.getName(), true, null, null, msg));

            return RESOURCE_REGISTER_VIEW;
        }
        String msg = messageSource.getMessage("msg.resourceRegisteredSuccesfully",
                new String[]{resource.getName()}, locale.resolveLocale(request));
        redirectAttributes.addFlashAttribute("message", msg);

        return REDIRECT_PRODUCT_REGISTER;
    }

    /**
     * Adds a {@link LinkedHashMap LinkedHashMap<String, String>} to the response model
     * with the different resource types and their internationalized representation
     * @param model MachineModel of the response scope
     */
    private void addResourceTypes(Model model) {
        LinkedHashMap<ResourceType, String> resourceTypes = new LinkedHashMap<>();
        resourceTypes.put(ResourceType.COLD_ITEM, messageSource.getMessage(ResourceType.COLD_ITEM.toString(), null, locale.resolveLocale(request)));
        resourceTypes.put(ResourceType.COLD_LIQUID, messageSource.getMessage(ResourceType.COLD_LIQUID.toString(), null, locale.resolveLocale(request)));
        resourceTypes.put(ResourceType.HOT_ITEM, messageSource.getMessage(ResourceType.HOT_ITEM.toString(), null, locale.resolveLocale(request)));
        resourceTypes.put(ResourceType.HOT_LIQUID, messageSource.getMessage(ResourceType.HOT_LIQUID.toString(), null, locale.resolveLocale(request)));
        resourceTypes.put(ResourceType.ITEM, messageSource.getMessage(ResourceType.ITEM.toString(), null, locale.resolveLocale(request)));
        resourceTypes.put(ResourceType.LIQUID, messageSource.getMessage(ResourceType.LIQUID.toString(), null, locale.resolveLocale(request)));
        resourceTypes.put(ResourceType.POWDER, messageSource.getMessage(ResourceType.POWDER.toString(), null, locale.resolveLocale(request)));

        model.addAttribute("resourceType", resourceTypes);
    }
}

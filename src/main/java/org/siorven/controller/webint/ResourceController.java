package org.siorven.controller.webint;

import org.siorven.exceptions.ResourceAlreadyRegisteredException;
import org.siorven.model.Resource;
import org.siorven.model.ResourceType;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.EnumMap;
import java.util.LinkedHashMap;

/**
 * Created by Gorospe on 29/05/2017.
 */
@Controller
public class ResourceController {
    private static final String RESOURCE_REGISTER_VIEW = "resourceRegister";
    private static final String RESOURCE = "resource";
    private static final String REDIRECT_PRODUCT_REGISTER = "redirect:/product/";
    private static final String REDIRECT_PRODUCT_MANAGER = "redirect:/product/manager";


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
     *
     * @param model MachineModel of the response scope
     * @return Key for the {@link org.springframework.web.servlet.ViewResolver ViewResolver} bean
     */
    @GetMapping("/resource/register")
    public String showRegister(Model model, @RequestParam(required = false) Integer prodid) {
        model.addAttribute(RESOURCE, new Resource());
        int id;
        if (prodid == null) {
            id = 0;
        } else {
            id = prodid;
        }
        model.addAttribute("prodid", id);
        addResourceTypes(model);
        return RESOURCE_REGISTER_VIEW;
    }

    /**
     * POST method of the newUser register action, checks the form data and rejects it with the according error message(s)
     *
     * @param resource      The newResource with the data collected from the web form
     * @param bindingResult The error wrapper of the validation errors
     * @param model         MachineModel of the response scope
     * @return Key for the {@link org.springframework.web.servlet.ViewResolver ViewResolver} bean
     */
    @PostMapping("/resource/register")
    public String performRegister(@ModelAttribute(RESOURCE) @Validated Resource resource, BindingResult bindingResult, @ModelAttribute("prodid") int prodid,
                                  Model model, RedirectAttributes redirectAttributes) {

        addResourceTypes(model);
        if (bindingResult.hasErrors()) {
            return RESOURCE_REGISTER_VIEW;
        }
        try {
            resourceService.save(resource);
        } catch (ResourceAlreadyRegisteredException e) {
            String msg = messageSource.getMessage(e.getMessage(),
                    new String[]{resource.getName()}, locale.resolveLocale(request));
            bindingResult.addError(new FieldError(RESOURCE, "name", resource.getName(), true, null, null, msg));

            return RESOURCE_REGISTER_VIEW;
        }
        String msg = messageSource.getMessage("msg.resourceRegisteredSuccesfully",
                new String[]{resource.getName()}, locale.resolveLocale(request));
        redirectAttributes.addFlashAttribute("message", msg);

        if (prodid > 0) {
            return REDIRECT_PRODUCT_REGISTER + prodid;
        } else {
            return REDIRECT_PRODUCT_MANAGER;
        }
    }

    /**
     * Adds a {@link LinkedHashMap LinkedHashMap<String, String>} to the response model
     * with the different resource types and their internationalized representation
     *
     * @param model MachineModel of the response scope
     */
    private void addResourceTypes(Model model) {
        EnumMap<ResourceType, String> resourceTypes = new EnumMap<>(ResourceType.class);
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

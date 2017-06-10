package org.siorven.controller.webint;

import org.siorven.controller.handlers.errors.ResourceNotFoundException;
import org.siorven.controller.webint.forms.IngredientForm;
import org.siorven.model.Ingredient;
import org.siorven.model.MachineProduct;
import org.siorven.model.Product;
import org.siorven.model.Resource;
import org.siorven.services.IngredientService;
import org.siorven.services.MachineProductService;
import org.siorven.services.ProductService;
import org.siorven.services.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Contains the mapping for the product related actions
 */
@Controller
public class ProductController {

    private static final String MESSAGE = "message";

    @Autowired
    private ProductService productService;
    @Autowired
    private MachineProductService machineProductService;
    @Autowired
    private ResourceService resourceService;
    @Autowired
    private IngredientService ingredientService;
    @Autowired
    private MessageSource messageSource;
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private LocaleResolver resolver;

    /**
     * Shows the product manager
     *
     * @return The view key for the {@link ViewResolver}
     */
    @GetMapping("/product/manager")
    public String showProductManager() {
        return "productManager";
    }

    /**
     * Processes the request to create a new product
     *
     * @param name               The name of the new product
     * @param redirectAttributes The Redirect Attributes
     * @return The view key for the {@link ViewResolver}
     */
    @PostMapping("/product/new")
    public String newProduct(@RequestParam("name") String name, RedirectAttributes redirectAttributes) {
        Product p = new Product();
        p.setName(name);
        p.setRecipe(new ArrayList<>());
        productService.save(p);
        String msg = messageSource.getMessage("msg.product.added",
                new String[]{p.getName()}, resolver.resolveLocale(request));
        redirectAttributes.addFlashAttribute(MESSAGE, msg);
        return "redirect:/product/manager";
    }

    /**
     * Shows the view of a product
     *
     * @param id    The product's id
     * @param model The response/request model
     * @return The view key for the {@link ViewResolver}
     */
    @GetMapping("/product/{id}")
    public String showProduct(@PathVariable("id") int id, Model model) {
        Product p = getProductOrThrow(id);
        model.addAttribute("product", p);
        model.addAttribute("ingredientForm", new IngredientForm());
        return "viewProduct";
    }


    /**
     * Deletes the product with the given id id
     *
     * @param id                 The id of the product
     * @param redirectAttributes The Redirect Attributes
     * @return The view key for the {@link ViewResolver}
     */
    @PostMapping("/product/delete/{id}")
    public String deleteProduct(@PathVariable("id") int id, RedirectAttributes redirectAttributes) {
        Product p = getProductOrThrow(id);
        List<MachineProduct> mps = machineProductService.findByProduct(p);
        for (MachineProduct mp : mps)
            machineProductService.delete(mp);
        productService.delete(p.getId());
        String msg = messageSource.getMessage("msg.product.deleted", new String[]{p.getName()}, resolver.resolveLocale(request));
        redirectAttributes.addAttribute(MESSAGE, msg);
        return "redirect:/product/manager";
    }

    /**
     * Deletes the ingredient with the given id
     *
     * @param id                 The id of the ingredient
     * @param redirectAttributes The Redirect Attributes
     * @return The view key for the {@link ViewResolver}
     */
    @PostMapping("/ingredient/delete/{id}")
    public String deleteIngredient(@PathVariable("id") int id, RedirectAttributes redirectAttributes) {
        Ingredient i = getIngredientOrThrow(id);
        int productId = i.getProduct().getId();
        removeIngredientFromProduct(i);
        i.setProduct(null);
        ingredientService.edit(i);
        ingredientService.delete(i);
        String msg = messageSource.getMessage("msg.ingredient.deleted", new String[]{i.getResource().getName()}, resolver.resolveLocale(request));
        redirectAttributes.addFlashAttribute(MESSAGE, msg);
        return "redirect:/product/" + productId;
    }

    /**
     * Removes the ingredient from its containing product
     *
     * @param ingredient The ingredient to be deleted
     */
    private void removeIngredientFromProduct(Ingredient ingredient) {
        Product p = ingredient.getProduct();
        for (int i = 0; i < p.getRecipe().size(); i++) {
            Ingredient in = p.getRecipe().get(i);
            if (in.getId() == ingredient.getId()) {
                p.getRecipe().remove(i);
            }
        }
        productService.edit(p);
    }

    /**
     * Gets the ingredient from the database or throws a {@link ResourceNotFoundException}
     *
     * @param id The id of the ingredient
     * @return The ingredient if found
     */
    private Ingredient getIngredientOrThrow(int id) {
        Ingredient i = ingredientService.findById(id);
        if (i == null) {
            String reason = messageSource.getMessage("ingredient.notFound", null, resolver.resolveLocale(request));
            throw new ResourceNotFoundException(reason);
        }
        return i;
    }

    /**
     * Adds a ingredient to the selected product
     *
     * @param id                 The id of the product
     * @param ingredientForm     The ingredient form
     * @param redirectAttributes The Redirect Attributes
     * @param bindingResult      The binding Result
     * @param model              The request/response model
     * @return The view key for the {@link ViewResolver}
     */
    @PostMapping("/product/{id}/ingredients/add")
    public String addIngredient(@PathVariable("id") int id, @ModelAttribute("ingredientForm") @Validated IngredientForm ingredientForm,
                                BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model) {
        if (bindingResult.hasErrors()) {
            Product p = getProductOrThrow(id);
            model.addAttribute("product", p);
            return "viewProduct";
        }

        Product p = getProductOrThrow(id);
        Resource r;
        try {
            r = getResourceOrThrow(ingredientForm.getName());
        } catch (ResourceNotFoundException rnfe) {
            String msg = messageSource.getMessage("resource.notExist", new String[]{rnfe.getMessage()}, resolver.resolveLocale(request));
            bindingResult.addError(new FieldError("ingredientForm", "name", ingredientForm.getName(), true, null, null, msg));
            model.addAttribute("product", p);
            return "viewProduct";
        }
        Ingredient i = new Ingredient();
        i.setResource(r);
        i.setQuantity(ingredientForm.getQty());
        i.setProduct(p);
        p.getRecipe().add(i);
        ingredientService.save(i);
        productService.saveOrUpdate(p);
        String msg = messageSource.getMessage("msg.ingredient.added", new String[]{r.getName()}, resolver.resolveLocale(request));
        redirectAttributes.addFlashAttribute(MESSAGE, msg);
        return "redirect:/product/" + id;
    }

    /**
     * Gets the resource with the given name or throws {@link ResourceNotFoundException}
     *
     * @param name The name of the resource
     * @return The resource
     */
    private Resource getResourceOrThrow(String name) {
        Resource r = resourceService.findByName(name);
        if (r == null) {
            throw new ResourceNotFoundException(name);
        }
        return r;
    }

    /**
     * Gets a product or throws a {@link ResourceNotFoundException}
     *
     * @param id The id of the product
     * @return The product
     */
    private Product getProductOrThrow(int id) {
        Product p = productService.findById(id);
        if (p == null) {
            String reason = messageSource.getMessage("product.notFound", null, resolver.resolveLocale(request));
            throw new ResourceNotFoundException(reason);
        }
        return p;
    }
}

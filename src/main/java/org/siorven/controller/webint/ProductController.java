package org.siorven.controller.webint;

import org.siorven.controller.handlers.errors.ResourceNotFoundException;
import org.siorven.controller.webint.forms.Ingredientform;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ander on 29/05/2017.
 */
@Controller
public class ProductController {

    @Autowired
    ProductService productService;

    @Autowired
    MachineProductService machineProductService;
    @Autowired
    ResourceService resourceService;
    @Autowired
    IngredientService ingredientService;
    @Autowired
    MessageSource messageSource;
    @Autowired
    HttpServletRequest request;
    @Autowired
    LocaleResolver resolver;

    @GetMapping("/product/manager")
    public String showProductManager() {
        return "productManager";
    }

    @PostMapping("/product/new")
    public String newProduct(@RequestParam("name") String name, RedirectAttributes redirectAttributes) {
        Product p = new Product();
        p.setName(name);
        p.setRecipe(new ArrayList<>());
        productService.save(p);
        String msg = messageSource.getMessage("msg.product.added",
                new String[]{p.getName()}, resolver.resolveLocale(request));
        redirectAttributes.addFlashAttribute("message", msg);
        return "redirect:/product/manager";
    }

    @GetMapping("/product/{id}")
    public String showProduct(@PathVariable("id") int id, Model model) {
        Product p = getProductOrThrow(id);
        model.addAttribute("product", p);
        model.addAttribute("ingredientForm", new Ingredientform());
        return "viewProduct";
    }

    @GetMapping("/product/delete/{id}")
    public String deleteProduct(@PathVariable("id") int id, RedirectAttributes redirectAttributes) {
        Product p = getProductOrThrow(id);
        List<MachineProduct> mps = machineProductService.findByProduct(p);
        for (MachineProduct mp : mps)
            machineProductService.delete(mp);
        productService.delete(p.getId());
        String msg = messageSource.getMessage("msg.product.deleted", new String[]{p.getName()}, resolver.resolveLocale(request));
        redirectAttributes.addAttribute("message", msg);
        return "redirect:/product/manager";
    }

    @GetMapping("/ingredient/delete/{id}")
    public String deleteIngredient(@PathVariable("id") int id, RedirectAttributes redirectAttributes) {
        Ingredient i = getIngredientOrThrow(id);
        int productId = i.getProduct().getId();
        removeIngredientFromProduct(i);
        i.setProduct(null);
        ingredientService.edit(i);
        ingredientService.delete(i);
        String msg = messageSource.getMessage("msg.ingredient.deleted", new String[]{i.getResource().getName()}, resolver.resolveLocale(request));
        redirectAttributes.addFlashAttribute("message", msg);
        return "redirect:/product/" + productId;
    }

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

    private Ingredient getIngredientOrThrow(int id) {
        Ingredient i = ingredientService.findById(id);
        if (i == null) {
            String reason = messageSource.getMessage("ingredient.notFound", null, resolver.resolveLocale(request));
            throw new ResourceNotFoundException(reason);
        }
        return i;
    }

    @PostMapping("/product/{id}/ingredients/add")
    public String addIngredient(@PathVariable("id") int id, @ModelAttribute("ingredientForm") @Validated Ingredientform ingredientform,
                                RedirectAttributes redirectAttributes, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            Product p = getProductOrThrow(id);
            model.addAttribute("product", p);
            return showProduct(id, model);
        }

        Product p = getProductOrThrow(id);
        Resource r = null;
        try {
            r = getResourceOrThrow(ingredientform.getName());
        } catch (ResourceNotFoundException rnfe) {
            String msg = messageSource.getMessage("resource.notExist", new String[]{rnfe.getMessage()}, resolver.resolveLocale(request));
            bindingResult.addError(new FieldError("ingredientForm", "name", ingredientform.getName(), true, null, null, msg));
            return showProduct(id, model);
        }
        Ingredient i = new Ingredient();
        i.setResource(r);
        i.setQuantity(ingredientform.getQty());
        i.setProduct(p);
        p.getRecipe().add(i);
        ingredientService.save(i);
        productService.saveOrUpdate(p);
        String msg = messageSource.getMessage("msg.ingredient.added", new String[]{r.getName()}, resolver.resolveLocale(request));
        redirectAttributes.addFlashAttribute("message", msg);
        return "redirect:/product/" + id;
    }

    private Resource getResourceOrThrow(String name) {
        Resource r = resourceService.findByName(name);
        if (r == null) {
            throw new ResourceNotFoundException(name);
        }
        return r;
    }

    private Product getProductOrThrow(int id) {
        Product p = productService.findById(id);
        if (p == null) {
            String reason = messageSource.getMessage("product.notFound", null, resolver.resolveLocale(request));
            throw new ResourceNotFoundException(reason);
        }
        return p;
    }
}

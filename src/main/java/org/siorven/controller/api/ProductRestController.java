package org.siorven.controller.api;

import org.siorven.model.Ingredient;
import org.siorven.model.Product;
import org.siorven.model.ResourceType;
import org.siorven.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.LocaleResolver;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Rest controller for the product related methods
 */
@RestController
public class ProductRestController {

    @Autowired
    ProductService productService;

    @Autowired
    HttpServletRequest request;

    @Autowired
    MessageSource messageSource;

    @Autowired
    LocaleResolver resolver;

    /**
     * Gets the product data formatted as a json for the js Datatables API
     *
     * @return the json data
     */
    @PostMapping(value = "/api/product/datatable", produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, List<Map<String, String>>> datatableAll() {
        List<Product> users = productService.findAll();

        Map<String, List<Map<String, String>>> datatables = new HashMap<>();
        List<Map<String, String>> data = new ArrayList<>();
        datatables.put("data", data);
        for (Product p : users) {
            Map<String, String> entry = new HashMap<>();
            entry.put("id", Integer.toString(p.getId()));
            entry.put("name", p.getName());
            data.add(entry);
        }
        return datatables;
    }

    /**
     * Gets a products recipe in a format prepared for datatables as json
     *
     * @param id The id of the product
     * @return The Json data
     */
    @PostMapping(value = "/api/product/{id}/ingredients/datatable", produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, List<Map<String, String>>> datatableIngredients(@PathVariable("id") int id) {
        Product product = productService.findById(id);

        Map<String, List<Map<String, String>>> datatables = new HashMap<>();
        List<Map<String, String>> data = new ArrayList<>();
        datatables.put("data", data);
        for (Ingredient i : product.getRecipe()) {
            Map<String, String> entry = new HashMap<>();
            entry.put("id", Integer.toString(i.getId()));
            entry.put("name", i.getResource().getName());
            entry.put("qty", Integer.toString(i.getQuantity()));
            String unit = messageSource.getMessage(ResourceType.unit(i.getResource().getResourceType()).toString(), null, resolver.resolveLocale(request));
            entry.put("unit", unit);
            data.add(entry);
        }
        return datatables;
    }


}

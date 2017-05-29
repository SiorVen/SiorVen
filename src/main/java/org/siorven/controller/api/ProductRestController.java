package org.siorven.controller.api;

import org.siorven.model.Product;
import org.siorven.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ander on 29/05/2017.
 */
@RestController
public class ProductRestController {

    @Autowired
    ProductService productService;

    @PostMapping(value = "/api/product/datatable", produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, List<Map<String, String>>> datatableAll(){
        List<Product> users = productService.findAll();

        Map<String, List<Map<String, String>>> datatables = new HashMap<>();
        List<Map<String, String>> data = new ArrayList<>();
        datatables.put("data", data);
        for (Product p : users) {
            Map<String, String> entry = new HashMap<>();
            entry.put("id", p.getId() + "");
            entry.put("name", p.getName());
            data.add(entry);
        }
        return datatables;
    }



}

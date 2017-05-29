package org.siorven.controller.webint;

import org.siorven.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by ander on 29/05/2017.
 */
@Controller
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping("/product/manager")
    public String showProductManager(){
        return "productManager";
    }

}

package org.siorven.controller.webint;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Web interface controller for the method of general use and root content
 */
@Controller
public class IndexController {

    /**
     * Returns the index view
     * @return Key for the {@link org.springframework.web.servlet.ViewResolver ViewResolver} bean
     */
    @GetMapping("/")
    public String showIndex(){
        return "index";
    }

    /**
     * Returns the login view
     * @return Key for the {@link org.springframework.web.servlet.ViewResolver ViewResolver} bean
     */
    @GetMapping("/login")
    public String showLogin(){
        return "login";
    }

}

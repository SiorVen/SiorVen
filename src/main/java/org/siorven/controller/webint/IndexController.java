package org.siorven.controller.webint;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by ander on 16/05/2017.
 */
@Controller
public class IndexController {

    @GetMapping("/")
    public String showIndex(){
        return "index";
    }

    @GetMapping("/login")
    public String showLogin(){
        return "login";
    }

}

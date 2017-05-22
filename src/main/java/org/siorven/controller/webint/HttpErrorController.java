package org.siorven.controller.webint;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by ander on 21/05/2017.
 */
@Controller
public class HttpErrorController {

    @GetMapping("/404")
    public String show404() {
        return "404";
    }

    @GetMapping("/500")
    public String show500() {
        return "500";
    }

    @GetMapping("/403")
    public String show403() {
        return "403";
    }

    @GetMapping("/401")
    public String show401() {
        return "401";
    }

}

package org.siorven.controller.webint;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Defines the mappings for the custom error pages
 */
@Controller
public class HttpErrorController {

    /**
     * 404 error page mapping
     *
     * @return the error page
     */
    @GetMapping("/404")
    public String show404() {
        return "404";
    }

    /**
     * 500 error page mapping
     *
     * @return the error page
     */
    @GetMapping("/500")
    public String show500() {
        return "500";
    }

    /**
     * 403 error page mapping
     *
     * @return the error page
     */
    @GetMapping("/403")
    public String show403() {
        return "403";
    }

    /**
     * 401 error page mapping
     *
     * @return the error page
     */
    @GetMapping("/401")
    public String show401() {
        return "401";
    }

}

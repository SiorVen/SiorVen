package org.siorven.controller.handlers;

import org.siorven.controller.handlers.errors.ForbiddenActionException;
import org.siorven.controller.handlers.errors.ResourceNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by ander on 21/05/2017.
 */
@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(ForbiddenActionException.class) //403
    public ModelAndView handleForbiddenActionException(ForbiddenActionException ex){
        ModelAndView modelAndView = new ModelAndView("403");
        modelAndView.addObject("reason", ex.getMessage());
        return modelAndView;
    }

    @ExceptionHandler(ResourceNotFoundException.class) //403
    public ModelAndView handleResourceNotFoundException(ResourceNotFoundException ex){
        ModelAndView modelAndView = new ModelAndView("404");
        modelAndView.addObject("reason", ex.getMessage());
        return modelAndView;
    }

}

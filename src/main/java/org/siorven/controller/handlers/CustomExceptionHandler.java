package org.siorven.controller.handlers;

import org.siorven.controller.handlers.errors.ForbiddenActionException;
import org.siorven.controller.handlers.errors.ResourceNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

/**
 * Exception handler for the mapping exceptions
 */
@ControllerAdvice
public class CustomExceptionHandler {

    /**
     * Handler for access forbidden exceptions
     *
     * @param ex The exception
     * @return The page to show in case of error
     */
    @ExceptionHandler(ForbiddenActionException.class) //403
    public ModelAndView handleForbiddenActionException(ForbiddenActionException ex) {
        ModelAndView modelAndView = new ModelAndView("403");
        modelAndView.addObject("reason", ex.getMessage());
        return modelAndView;
    }

    /**
     * Handler for resource not found exceptions
     *
     * @param ex The exception
     * @return The page to show in case of error
     */
    @ExceptionHandler(ResourceNotFoundException.class) //404
    public ModelAndView handleResourceNotFoundException(ResourceNotFoundException ex) {
        ModelAndView modelAndView = new ModelAndView("404");
        modelAndView.addObject("reason", ex.getMessage());
        return modelAndView;
    }

}

package org.siorven.controller.handlers.errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by ander on 21/05/2017.
 */
@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class ForbiddenActionException extends RuntimeException {
    public ForbiddenActionException(String message) {
        super(message);
    }
}

package org.siorven.controller.handlers.errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Unauthorized error prepared to map to 401 error
 */
@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class ForbiddenActionException extends RuntimeException {
    public ForbiddenActionException(String message) {
        super(message);
    }
}

package org.siorven.controller.handlers.errors;

/**
 * Exception to be thrown in case that a user is blocked and attempt access
 */
public class UserBlockedException extends RuntimeException {

    public UserBlockedException(String message) {
        super(message);
    }
}

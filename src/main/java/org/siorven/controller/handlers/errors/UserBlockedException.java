package org.siorven.controller.handlers.errors;

/**
 * Created by ander on 23/05/2017.
 */
public class UserBlockedException extends RuntimeException {

    public UserBlockedException(String message) {
        super(message);
    }
}

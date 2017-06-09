package org.siorven.exceptions;

/**
 * Created by Gorospe on 30/05/2017.
 */
public class ResourceAlreadyRegisteredException extends Exception {
    public ResourceAlreadyRegisteredException() {
        super();
    }

    public ResourceAlreadyRegisteredException(String message) {
        super(message);
    }

    public ResourceAlreadyRegisteredException(String message, Throwable cause) {
        super(message, cause);
    }

    public ResourceAlreadyRegisteredException(Throwable cause) {
        super(cause);
    }
}

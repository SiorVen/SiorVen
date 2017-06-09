package org.siorven.exceptions;

/**
 * Indicated that a resource is being persisted and shouldn't as its content is
 * duplicating some existing one
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

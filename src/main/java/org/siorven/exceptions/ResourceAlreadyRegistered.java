package org.siorven.exceptions;

/**
 * Created by Gorospe on 30/05/2017.
 */
public class ResourceAlreadyRegistered extends Exception {
    public ResourceAlreadyRegistered() {
        super();
    }

    public ResourceAlreadyRegistered(String message) {
        super(message);
    }

    public ResourceAlreadyRegistered(String message, Throwable cause) {
        super(message, cause);
    }

    public ResourceAlreadyRegistered(Throwable cause) {
        super(cause);
    }
}

package org.siorven.exceptions;

/**
 * Exception to be used when a user is trying to get persisted while a user with
 * the same email is already persisted
 */
public class EmailInUseException extends ResourceAlreadyRegisteredException {
}

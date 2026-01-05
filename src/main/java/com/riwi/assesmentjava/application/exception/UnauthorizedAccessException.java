package com.riwi.assesmentjava.application.exception;

/**
 * Excepción lanzada cuando un usuario intenta acceder a un recurso que no le
 * pertenece.
 */
public class UnauthorizedAccessException extends RuntimeException {

    public UnauthorizedAccessException(String message) {
        super(message);
    }

    public UnauthorizedAccessException() {
        super("You don't have permission to access this resource");
    }
}

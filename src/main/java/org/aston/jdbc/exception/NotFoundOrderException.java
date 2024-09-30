package org.aston.jdbc.exception;

public class NotFoundOrderException extends RuntimeException {
    public NotFoundOrderException(String message) {
        super(message);
    }
}

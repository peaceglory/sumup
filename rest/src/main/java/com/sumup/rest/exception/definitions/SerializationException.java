package com.sumup.rest.exception.definitions;

public class SerializationException extends RuntimeException {
    public SerializationException(String message, Throwable originalException) {
        super(message, originalException);
    }
}

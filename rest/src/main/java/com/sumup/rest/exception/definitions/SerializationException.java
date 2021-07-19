package com.sumup.rest.exception.definitions;

public class SerializationException extends RuntimeException {
    public SerializationException(String message, Exception originalException) {
        super(message, originalException);
    }
}

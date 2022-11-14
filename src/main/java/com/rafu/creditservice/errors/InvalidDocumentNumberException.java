package com.rafu.creditservice.errors;

public class InvalidDocumentNumberException extends RuntimeException {
    public InvalidDocumentNumberException(final String message) {
        super(message);
    }

}

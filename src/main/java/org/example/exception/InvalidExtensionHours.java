package org.example.exception;

public class InvalidExtensionHours extends RuntimeException {
    public InvalidExtensionHours(String message) {
        super(message);
    }
}

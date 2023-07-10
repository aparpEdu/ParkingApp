package org.example.exception;

public class InvalidSumException extends RuntimeException{
    public InvalidSumException(String message, double parkingFee) {
        super(message + ' ' + parkingFee);
    }
}

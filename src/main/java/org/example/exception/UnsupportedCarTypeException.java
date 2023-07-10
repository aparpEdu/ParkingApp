package org.example.exception;

public class UnsupportedCarTypeException extends RuntimeException{
    public UnsupportedCarTypeException(String message, String carType) {
        super(message+ " "+ carType);
    }
}

package org.example.exception;

public class UnsupportedCarBrandException extends RuntimeException{
    public UnsupportedCarBrandException(String message, String carBrand) {
        super(message + " " + carBrand);
    }
}

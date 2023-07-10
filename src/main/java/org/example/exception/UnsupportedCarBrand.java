package org.example.exception;

public class UnsupportedCarBrand extends RuntimeException{
    public UnsupportedCarBrand(String message, String carBrand) {
        super(message + " " + carBrand);
    }
}

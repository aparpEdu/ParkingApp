package org.example.exception;

public class WeightOverMaximumException extends RuntimeException {

    public WeightOverMaximumException(String message, double maxWeight) {
        super(message+ " "+ maxWeight);
    }
}

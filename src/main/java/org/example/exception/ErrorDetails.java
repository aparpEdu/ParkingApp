package org.example.exception;

public enum ErrorDetails {
    UNSUPPORTED_CAR_TYPE("Unsupported car type:"),
    UNSUPPORTED_CAR_BRAND("Unsupported car brand:"),
    INVALID_SUM("Sum you provided is less than the parking fee:"),
    UNSUPPORTED_HOURS("Hours you provided are not supported"),
    UNSUPPORTED_WEIGHT("Weight is over the current maximum limit of:");

    private final String name;

    ErrorDetails(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

package org.example.exception;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ResourceNotFoundException extends RuntimeException {
    private String resourceName;
    private String fieldName;
    private String fieldValueString;
    public ResourceNotFoundException(String resourceName, String fieldName, String fieldValueString ) {
        super(String.format("%s not found with %s : '%s'", resourceName, fieldName, fieldValueString));
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.fieldValueString = fieldValueString;
    }
}

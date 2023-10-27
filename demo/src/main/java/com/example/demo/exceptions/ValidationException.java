package com.example.demo.exceptions;

import lombok.Data;
import org.springframework.validation.ObjectError;

import java.util.ArrayList;
import java.util.List;

@Data
public class ValidationException extends RuntimeException {
    private List<ObjectError> objectErrors;

    public ValidationException(String message, List<ObjectError> objectErrors) {
        super(message);
        this.objectErrors = objectErrors;
    }

    public ValidationException(String message) {
        super("Validation Error");
    }

    public ValidationException(List<ObjectError> objectErrors) {
        super("Validation Error");
        this.objectErrors = objectErrors;
    }

    public ValidationException() {
        super("Validation Error");
        this.objectErrors = new ArrayList<ObjectError>();
    }
}

package com.example.validator.model;

import java.util.ArrayList;
import java.util.List;

public class ValidationResult {

    private boolean valid;
    private List<String> errors;

    public ValidationResult() {
        this.valid = true;
        this.errors = new ArrayList<>();
    }

    public boolean isValid() {
        return valid;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void addError(String message) {
        this.valid = false;
        this.errors.add(message);
    }
}

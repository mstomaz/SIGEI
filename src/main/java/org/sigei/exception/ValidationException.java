package org.sigei.exception;

import java.util.Map;

public class ValidationException extends Exception {
    private final Map<String, String> errors;

    public ValidationException(Map<String, String> errors) {
        super();
        this.errors = errors;
    }

    public ValidationException(String key, String value) {
        super();
        errors = Map.of(key, value);
    }

    public Map<String, String> getMessages() {
        return errors;
    }
}

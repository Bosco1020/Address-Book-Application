package com.digitalfutures.app;

public class Validator {

    public static void validateString(String input) {
        if(input == null || input.trim().isEmpty()) {
            throw new IllegalArgumentException("Property cannot be null or empty");
        }
    }
}

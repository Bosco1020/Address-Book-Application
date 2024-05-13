package com.digitalfutures.app;

public class Validator {

    public static void validateString(String input) {
        if(input == null || input.trim().isEmpty()) {
            throw new IllegalArgumentException("Property cannot be null or empty");
        }
    }

    public static void validatePhoneNumber(String input) {
        validateString(input);
        if(!input.matches("^[0-9]*[0-9]*[0-9]*[0-9]*[0-9]*[0-9]*[0-9]*[0-9]*[0-9]*[0-9]*[0-9]*")){
            throw new IllegalArgumentException("Property cannot be null or empty");
        }
        if(input.length() != 11) {throw new IllegalArgumentException("Property cannot be null or empty");}
    }
}

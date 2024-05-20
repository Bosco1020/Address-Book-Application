package com.digitalfutures.app;

public class Validator {

    public static void validateString(String input) {
        if(input == null || input.trim().isEmpty()) {
            throw new IllegalArgumentException("Property cannot be null or empty");
        }
    }

    public static void validatePhoneNumber(String input) {
        validateString(input);
        if(!input.matches("^[+][0-9]{11,13}$|[0-9]{11}$")){
            throw new IllegalArgumentException("Property cannot be null or empty");
        }
    }

    public static void validateEmail(String input){
        validateString(input);
        if(!input.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")){
            throw new IllegalArgumentException("Property cannot be null or empty");
        }
    }
}

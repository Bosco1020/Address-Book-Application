package com.digitalfutures.app;

public class Validator {

    // Validate Methods are last line of check within Constructors. In theory should never trigger but act as failsafe
    public static void validateString(String input) {
        if(input == null || input.trim().isEmpty()) {
            throw new IllegalArgumentException("Property cannot be null or empty");
        }
    }

    public static void validateName(String input){
        validateString(input);
        if(!input.matches("([a-zA-Z0-9_\\s]+)")) {
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

    // Check methods also validate inputs but rather than throwing an exception, return a boolean.
    // These are used in the logic to request a re-input before attempting to add the results into an object and causing an error.
    public static boolean checkName(String input) {
        if(input == null || !input.matches("([a-zA-Z0-9_\\s]+)") || input.trim().isEmpty()) {
            return false;
        }
        return true;
    }

    public static boolean checkPhoneNumber(String input) {
        if(input == null || !input.matches("^[+][0-9]{11,13}$|[0-9]{11}$")){
            return false;
        }
        return true;
    }

    public static boolean checkEmail(String input){
        if(input == null || !input.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")){
            return false;
        }
        return true;
    }

    public static boolean checkInteger(String input) {
        if(input == null || !input.matches("\\b([0-9]|[1-9][0-9])\\b")){
            return false;
        }
        return true;
    }
}

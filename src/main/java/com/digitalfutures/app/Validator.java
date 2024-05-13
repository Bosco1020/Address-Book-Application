package com.digitalfutures.app;

public class Validator {

    public static void validateString(String input) {
        if(input == null || input.trim().isEmpty()) {
            throw new IllegalArgumentException("Property cannot be null or empty");
        }
    }

    public static void validatePhoneNumber(String input) {
        validateString(input);
        //if(input.matches("^[+][0-9]{11,13}$")) {System.out.println("YEE HAWWW");}
        if(!input.matches("^[+][0-9]{11,13}$|[0-9]{11}$")){ //043, 2b
            throw new IllegalArgumentException("Property cannot be null or empty");
        }
    }
}

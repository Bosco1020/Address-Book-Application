package com.digitalfutures.app;

public class Validator {

    public static void validateString(String toValidate) {
        if(toValidate == null) {
            throw new IllegalArgumentException("Property cannot be null");
        }
    }
}

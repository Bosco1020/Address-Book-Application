package com.digitalfutures.app;

public class Contact {

    private String name;
    private String phoneNumber;
    private String email;

    public Contact(String newName, String newPhone, String newEmail) {
        Validator.validateString(newName);
        Validator.validatePhoneNumber(newPhone);
        Validator.validateEmail(newEmail);
        this.name = newName;
        this.phoneNumber = newPhone;
        this.email = newEmail;
    }

    public String getName() {
        return this.name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

}

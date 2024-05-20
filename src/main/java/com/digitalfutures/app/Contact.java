package com.digitalfutures.app;

public class Contact {

    private String name;



    private String phoneNumber;
    private String email;

    public Contact(String newName, String newPhone, String newEmail) {
        checkValidation(newName, newPhone, newEmail);
        this.name = newName;
        this.phoneNumber = newPhone;
        this.email = newEmail;
    }

    private void checkValidation(String newName, String newPhone, String newEmail) {
        Validator.validateName(newName);
        Validator.validatePhoneNumber(newPhone);
        Validator.validateEmail(newEmail);
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

    public void setName(String name) {
        Validator.validateName(name);
        this.name = name;
    }

    public void setNumber(String phoneNumber) {
        Validator.validatePhoneNumber(phoneNumber);
        this.phoneNumber = phoneNumber;
    }

    public void setEmail(String email) {
        Validator.validateEmail(email);
        this.email = email;
    }

    public boolean checkName(String input) {
        if(input == this.name) return true;
        return false;
    }

}

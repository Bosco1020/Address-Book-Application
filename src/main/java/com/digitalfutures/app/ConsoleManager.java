package com.digitalfutures.app;

import java.util.ArrayList;
import java.util.Scanner;

public class ConsoleManager {

    Scanner scanner = new Scanner(System.in);

    public String getInput() {
        return scanner.nextLine();
    }

    public Object readInput(Scanner s) {
        switch(s.nextLine()) {
            case "View Contacts":
                //view all contacts break;
            case "New Contact":
                // create new contact
                Object newContact = new Contact(collectName(s), collectNumber(s), collectEmail(s));
                return newContact;
            case "Search -x-":
                // Searching address book for contact(s) break;
            default:
                return null;
        }
    }

    // Collect methods read in inputs sor a contacts information and check they're valid.
    //If not they continue to ask for new inputs until a valid input is added.
    public String collectName(Scanner sc) {
        System.out.println("Please Enter the Contacts Name");
        String response = sc.nextLine();
        while(!Validator.checkName(response)) {
            System.out.println("The Name is invalid. Please try again");
            response = sc.nextLine(); }
        return response;
    }

    public String collectNumber(Scanner sc) {
        System.out.println("Please Enter the Contacts Phone Number");
        String response = sc.nextLine();
        while(!Validator.checkPhoneNumber(response)) {
            System.out.println("The Phone Number is invalid. Please try again");
            response = sc.nextLine(); }
        return response;
    }

    public String collectEmail(Scanner sc) {
        System.out.println("Please Enter the Contacts Email Address");
        String response = sc.nextLine();
        while(!Validator.checkEmail(response)) {
            System.out.println("The Email Address is invalid. Please try again");
            response = sc.nextLine(); }
        return response;
    }

    public void printOutput(ArrayList<Object> inputs) {
        for(int i=0; i<inputs.size(); i++){
            printOutput(((Contact)inputs.get(i)).getName(), "Name:", "");
            printOutput(((Contact)inputs.get(i)).getPhoneNumber(), "Phone Number:", "");
            printOutput(((Contact)inputs.get(i)).getEmail(), "Email Address:", "");
        }
    }

    public void printOutput(String input) {
        System.out.println(input);
    }

    public void printOutput(String input, String pre, String post) {
        System.out.println(pre + " " + input + " " + post);
    }
}

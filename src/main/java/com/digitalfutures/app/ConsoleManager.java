package com.digitalfutures.app;

import java.util.ArrayList;
import java.util.Scanner;

public class ConsoleManager {

    public String getInput(Scanner s) {
        return s.nextLine();
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

    public int collectInteger(Scanner s) {
        String input = getInput(s);
        while(!Validator.checkInteger(input)) {
            printOutput("Please enter a valid integer for the target Contact (e.g. 1, 2 etc");
            input = getInput(s);
        }
        return Integer.parseInt(input);
    }

    public void printOutput(ArrayList<Object> inputs) {
        for(int i=0; i<inputs.size(); i++){

            printOutput(
                    "Phone Number: " + ((Contact)inputs.get(i)).getPhoneNumber() + ",",
                    "Name: " + ((Contact)inputs.get(i)).getName() + ",",
                    "Email Address: " + ((Contact)inputs.get(i)).getEmail() + ","
            );
        }
    }

    public void printOutput(String input) {
        System.out.println(input);
    }

    public void printOutput(String centre, String pre, String post) {
        System.out.println(pre + " " + centre + " " + post);
    }
}

package com.digitalfutures.app;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    private static Scanner scanner = new Scanner(System.in);
    private static ConsoleManager console = new ConsoleManager();
    private static AddressBook addressBook = new AddressBook();

    private static ArrayList<Object> currentContacts;

    public static void main(String[] args) {
        // Print Start info

        // wait for input & act
        readInput();

    }

    public static void readInput() {
        String read = scanner.nextLine();
        if(read.equalsIgnoreCase("View Contact")) {
            //view all contacts
             }
        if(read.equalsIgnoreCase("New Contact")) {
            // make new contact & add to book
            newContact(); }
        if(read.contains("Search ") || read.contains("search")) {
            // Search & display contact(s)
            searchContacts(read.substring(7)); }
        if(read.equalsIgnoreCase("Edit Contact")) {
            // edit current contact information
            editContact();
             }
        // default

    }

    private static void newContact() {
        Contact newContact = new Contact(console.collectName(scanner), console.collectNumber(scanner), console.collectEmail(scanner));
        addressBook.addContact(newContact);
    }

    private static void searchContacts(String name) {
        if(!Validator.checkName(name)) { console.printOutput("Sorry that name is invalid to search for."); name = console.collectName(scanner); }
        ArrayList<Object> result = Main.addressBook.searchContacts(name);
        if(result == null || result.isEmpty()) {
            // No match, report
            console.printOutput("Sorry there was no match for that name."); }
        else {
            Main.console.printOutput(result);
            currentContacts = result; }
    }

    private static void editContact() {
        Contact toEdit = getTarget();
        if(toEdit == null) return;
        toEdit.setName(console.collectName(scanner, toEdit.getName()));
        toEdit.setNumber(console.collectNumber(scanner, toEdit.getPhoneNumber()));
        toEdit.setEmail(console.collectEmail(scanner, toEdit.getEmail()));
    }

    private static Contact getTarget() {
        switch(currentContacts.size()) {
            case 0:
                console.printOutput("Please use the Search command to find the Contact to Edit");
                return null;
            case 1:
                return ((Contact)currentContacts.get(0));
            default: {
                console.printOutput("Please designate which of these contacts you wish to edit by typing in number 1 to " + currentContacts.size() + ".");
                return ((Contact)currentContacts.get(console.collectInteger(scanner)-1)); }
        }
    }

    public static Scanner getScanner() {
        return scanner;
    }

    public static void setScanner(Scanner scanner) {
        Main.scanner = scanner;
    }

    public static ConsoleManager getConsole() {
        return console;
    }

    public static void setConsole(ConsoleManager newConsole) {
        Main.console = newConsole;
    }

    public static AddressBook getAddressBook() {
        return addressBook;
    }

    public static void setAddressBook(AddressBook addressBook) {
        Main.addressBook = addressBook;
    }

}

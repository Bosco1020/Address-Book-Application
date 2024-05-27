package com.digitalfutures.app;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    private static Scanner scanner = new Scanner(System.in);
    private static ConsoleManager console = new ConsoleManager();
    private static AddressBook addressBook = new AddressBook();

    private static ArrayList<Object> currentContacts; // Record of most recent search for Edit & Delete

    public static void main(String[] args) {
        // Print Start info
        console.printHelp();
        // wait for input & act
        while(true) {
        readInput(); }
    }

    // Interprets User Input and calls appropriate methods.
    public static void readInput() {
        String read = scanner.nextLine();
        if(read.equalsIgnoreCase("View Contacts")) {
            //view all contacts
            viewContacts(); currentContacts = null; return; }
        if(read.equalsIgnoreCase("New Contact")) {
            // make new contact & add to book
            newContact(); currentContacts = null; return; }
        if(read.contains("Search ") || read.contains("search")) {
            // Search & display contact(s)
            searchContacts(read.substring(7)); return; }
        if(read.equalsIgnoreCase("Edit Contact")) {
            // edit current contact information
            editContact(); return; }
        if(read.equalsIgnoreCase("Delete Contact")) {
            // Delete current contact
             deleteContact(); return; }
        if(read.equalsIgnoreCase("Help")) {
            // Delete current contact
            console.printHelp(); currentContacts = null; return; }
        // default
        console.printOutput("Invalid Input, type 'Help' to see a list of commands.");
    }

    private static void newContact() {
        Contact newContact = new Contact(console.collectName(scanner), console.collectNumber(scanner), console.collectEmail(scanner));
        addressBook.addContact(newContact);
    }

    private static void searchContacts(String name) {
        if(!Validator.checkName(name)) { console.printOutput("Sorry, please input a valid name to search for."); name = console.collectName(scanner); }
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
        Main.console.printOutput(addressBook.searchContacts(toEdit.getName()));
    }

    private static void deleteContact() {
        Contact toEdit = getTarget();
        if(toEdit == null) return;
        if(addressBook.deleteContact(toEdit))
        console.printOutput("Contact " + toEdit.getName() + " has been removed from the Address Book.");
        else console.printOutput("Contact not deleted. Please check you haven't already deleted the contact, or try to search for the target contact again.");
    }

    private static void viewContacts() {
        if(currentContacts.size() <= 0){
            console.printOutput("There are currently no Contacts in the Address Book."); return; }
        console.printOutput(addressBook.getContacts());
    }

    // If multiple search results, method interprets User Input into Integer for identify target index to Edit/Delete
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

    // Setters mainly to facilitate testing
    public static void setScanner(Scanner scanner) {
        Main.scanner = scanner;
    }

    public static void setConsole(ConsoleManager newConsole) {
        Main.console = newConsole;
    }

    public static void setAddressBook(AddressBook addressBook) {
        Main.addressBook = addressBook;
    }
}

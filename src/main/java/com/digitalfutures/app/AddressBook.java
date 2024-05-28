package com.digitalfutures.app;

import java.util.ArrayList;

public class AddressBook {

    ArrayList<Object> contacts = new ArrayList<Object>(); // Using Object lists to try and reduce dependency

    public void addContact(Object contact) {
        boolean duplicate = false;
        for(Object c : this.contacts){
            if(!duplicate) duplicate = checkDuplicate(contact, c); } // Don't want to accidentally set from true to false
        if(!duplicate) { contacts.add(contact);
        System.out.println("Contact " + ((Contact)contact).getName() + " successfully added."); }
    }

    // Compares if the 1st contact is Null (which Mock objects return null),
    // And compares emails & phone numbers, if either match then true
    private boolean checkDuplicate(Object contact1, Object contact2) {
        if(((Contact)contact1).getEmail() != null && (((Contact)contact2).getEmail().equals(((Contact)contact1).getEmail()) ||
                ((Contact)contact2).getPhoneNumber().equals(((Contact)contact1).getPhoneNumber()))) {
            System.out.println("A contact with these details already exists, please try again or check your details."); return true; }
        return false;
    }

    public ArrayList<Object> getContacts() {
        return contacts;
    }

    // Loop through all contacts looking for matching name.
    // If no match then output is empty so set null for easy identify, then return either way.
    public ArrayList<Object> searchContacts(String target) {
        ArrayList<Object> output = new ArrayList<Object>();
        for(Object contact : contacts) {
            if(((Contact)contact).getName().toLowerCase().contains(target.toLowerCase())) output.add(contact); }
        if(output.size() <= 0) output = null;
        return output;
    }

    public boolean deleteContact(Object target) {
        return this.contacts.remove(target);
    }
}

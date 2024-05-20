package com.digitalfutures.app;

import java.util.ArrayList;

public class AddressBook {

    ArrayList<Object> contacts = new ArrayList<Object>();

    public void addContact(Object contact) {
        contacts.add(contact);
    }

    public ArrayList<Object> getContacts() {
        return contacts;
    }

    public ArrayList<Object> searchContacts(String target) {
        ArrayList<Object> output = new ArrayList<Object>();
        for(Object contact : contacts) {
            if(((Contact)contact).getName().contains(target)) output.add(contact); }
        if(output.size() > 0) return output; output = null; return output;
    }

}

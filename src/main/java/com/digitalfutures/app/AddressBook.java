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

}

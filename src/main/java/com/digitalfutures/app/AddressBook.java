package com.digitalfutures.app;

import java.util.ArrayList;

public class AddressBook {

    ArrayList<Object> contacts = new ArrayList<Object>();

    public void addContact(Object contact) {
        boolean duplicate = false;
        for(Object c : this.contacts){ // Check if null for tests as Mocked objects default response is null
            if(((Contact)contact).getEmail() != null && ((Contact)c).getEmail().equals(((Contact)contact).getEmail())) {
                duplicate = true; }
        }
        if(!duplicate) contacts.add(contact);
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

    public boolean deleteContact(Object target) {
        if(this.contacts.remove(target)) return true;
        return false;
    }

}

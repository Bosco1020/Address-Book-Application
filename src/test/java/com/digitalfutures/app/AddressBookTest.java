package com.digitalfutures.app;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AddressBookTest {

    @Nested
    @DisplayName("Address Book Input Tests")
    class BookInputTests {

        @Test
        @DisplayName("A new Contact can be added to the array in the Address Book")
        public void NewContactCanBeAddedToAddressBookArray() {
            // Arrange
            AddressBook testBook = new AddressBook();
            // Act
            Contact mockContact = mock(Contact.class);
            testBook.addContact(mockContact);
            //Assert
            assertEquals(testBook.getContacts().get(0), mockContact);
        }

        @Test
        @DisplayName("getContacts returns an array of every Contact stored in the Address Book")
        public void AddressBookRetunsArrayOfAllContacts() {
            // Arrange
            AddressBook testBook = new AddressBook();
            // Act
            Contact mockContact1 = mock(Contact.class);
            Contact mockContact2 = mock(Contact.class);
            Contact mockContact3 = mock(Contact.class);
            testBook.addContact(mockContact1);
            testBook.addContact(mockContact2);
            testBook.addContact(mockContact3);
            //Assert
            assertAll("Constructor set values to inputs",
                    () -> assertEquals(testBook.getContacts().get(0), mockContact1),
                    () -> assertEquals(testBook.getContacts().get(1), mockContact2),
                    () -> assertEquals(testBook.getContacts().get(2), mockContact3));
        }
    }

    @Nested
    @DisplayName("Address Book Search Tests")
    class BookSearchTests {
        @Test
        @DisplayName("searchContacts() takes a contacts name and returns all matching contacts if any")
        public void searchContactsReturnsAllContactsWithAMatchingNameIfAny() {
            // Arrange
            AddressBook testBook = new AddressBook();
            // Act
            Contact mockContact1 = mock(Contact.class);
            when(mockContact1.getName()).thenReturn("Juliet");

            Contact mockContact2 = mock(Contact.class);
            when(mockContact2.getName()).thenReturn("Joe");

            Contact mockContact3 = mock(Contact.class);
            when(mockContact3.getName()).thenReturn("Joeseph");

            testBook.addContact(mockContact1);
            testBook.addContact(mockContact2);
            testBook.addContact(mockContact3);

            ArrayList<Object> expected = new ArrayList<Object>();
            expected.add(mockContact2);
            expected.add(mockContact3);
            //Assert
            assertEquals(testBook.searchContacts("Joe"), expected);
        }
    }
}

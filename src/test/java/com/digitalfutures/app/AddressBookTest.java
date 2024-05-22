package com.digitalfutures.app;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

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

        @Test
        @DisplayName("if searchContacts() doesn't find a matching contact, it returns null")
        public void searchContactsReturnsNullIfNoMatchingNamesFound() {
            // Arrange
            AddressBook testBook = new AddressBook();
            // Act
            Contact mockContact1 = mock(Contact.class);
            when(mockContact1.getName()).thenReturn("Juliet");

            testBook.addContact(mockContact1);
            //Assert
            assertEquals(testBook.searchContacts("Joe"), null);
        }
    }
    @Nested
    @DisplayName("Address Book Edit Tests")
    class BookEditTests {
        @Test
        @DisplayName("Can change the information of an existing contact in the address book")
        public void canChangeInformationOfAnExistingContactInAddressBook() {
            // Arrange
            AddressBook testBook = new AddressBook();
            String newName = "Dan";
            // Act
                // using a spy object rather than mocking so the setName method cna still be used
            Contact spyContact = spy(new Contact("Juliet", "01234567891", "JB@Gmail.com"));
            testBook.addContact(spyContact);
                // returns all Contacts matching "Juliet" from book, gets the 1st and sets the name to newName
            ((Contact)(testBook.searchContacts("Juliet").get(0))).setName(newName);
            //Assert
            assertEquals(((Contact)(testBook.searchContacts(newName).get(0))).getName(), newName);
        }

        @Test
        @DisplayName("deleteContact() removes the inputted contact from the address book")
        public void deleteContactRemovesTargetFromAddressBook() {
            // Arrange
            AddressBook testBook = new AddressBook();
            // Act
            Contact mockContact1 = mock(Contact.class);
            testBook.addContact(mockContact1);

            testBook.deleteContact(mockContact1);
            //Assert
            assertEquals(testBook.getContacts().size(), 0);
        }
    }
}

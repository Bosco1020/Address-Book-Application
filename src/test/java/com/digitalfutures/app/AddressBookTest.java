package com.digitalfutures.app;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

public class AddressBookTest {

    @Nested
    @DisplayName("Content Tests")
    class ContentTests {

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
    }
}

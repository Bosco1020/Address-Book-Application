package com.digitalfutures.app;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UserInputIntegrationTest {

    @Nested
    @DisplayName("New Contact Tests")
    class NewContactTests {

        @Test
        @DisplayName("Console input of \"New Contact\"  prompts the user to input a new Contacts info and returns the created Contact")
        public void InputOfNewContactPromptsUserToInputContactDetailsAndReturnsContact() {
            // Arrange
            Main spyMain = spy(Main.class);
            AddressBook spyBook = spy(AddressBook.class);
            Scanner mockScanner = mock(Scanner.class);
            // Act
            when(mockScanner.nextLine()).thenReturn("New Contact", "Jean", "01234567890", "J@Yahoo.co.uk");
            spyMain.setScanner(mockScanner);
            spyMain.setAddressBook(spyBook);

            spyMain.readInput();

            ArrayList<Object> result = spyBook.searchContacts("Jean");
            //Assert
            assertAll("Constructor set values to inputs",
                    () -> assertEquals(((Contact)result.get(0)).getName(), "Jean"),
                    () -> assertEquals(((Contact)result.get(0)).getPhoneNumber(), "01234567890"),
                    () -> assertEquals(((Contact)result.get(0)).getEmail(), "J@Yahoo.co.uk"));
        }
    }
    @Nested
    @DisplayName("Search Tests")
    class SearchTests {

        @Test
        @DisplayName("Console input of \"Search -name-\"  prints all matches in the address book")
        public void InputOfSearchNamePrintsAllMatchingContacts() {
            // Arrange
            Main spyMain = spy(Main.class);
            AddressBook spyBook = spy(AddressBook.class);
            Scanner mockScanner = mock(Scanner.class);
            ConsoleManager spyConsole = spy(ConsoleManager.class);
            // Act
            Contact spyContact1 = spy(new Contact("Juliet", "01234567891", "JB@Gmail.com"));
            spyBook.addContact(spyContact1);
            Contact spyContact2 = spy(new Contact("David", "22222222222", "Dav-Id@mail.co.uk"));
            spyBook.addContact(spyContact2);
            Contact spyContact3 = spy(new Contact("Juliet", "98765432109", "J-B@Gmail.com"));
            spyBook.addContact(spyContact3);

            when(mockScanner.nextLine()).thenReturn("Search Juliet");
            spyMain.setScanner(mockScanner);
            spyMain.setAddressBook(spyBook);
            spyMain.setConsole(spyConsole);

            spyMain.readInput();

            //Assert
            Mockito.verify(spyConsole, times(2)).printOutput(any(), any(), any());
        }
        @Test
        @DisplayName("Console input of \"Search -name-\" requests new input until name is valid")
        public void InputOfSearchNameRequestsNewInputUntilValid() {
            // Arrange
            Main spyMain = spy(Main.class);
            AddressBook spyBook = spy(AddressBook.class);
            Scanner mockScanner = mock(Scanner.class);
            ConsoleManager spyConsole = spy(ConsoleManager.class);
            // Act
            Contact spyContact1 = spy(new Contact("Juliet", "01234567891", "JB@Gmail.com"));
            spyBook.addContact(spyContact1);

            when(mockScanner.nextLine()).thenReturn("Search 5!", "Juliet");
            spyMain.setScanner(mockScanner);
            spyMain.setAddressBook(spyBook);
            spyMain.setConsole(spyConsole);

            spyMain.readInput();

            //Assert
            Mockito.verify(spyConsole, times(1)).printOutput("Sorry that name is invalid to search for.");
        }
        @Test
        @DisplayName("Console input of \"Search -name-\" informs the user if there is no match")
        public void InputOfSearchNameInformsUserIfNoMatch() {
            // Arrange
            Main spyMain = spy(Main.class);
            AddressBook spyBook = spy(AddressBook.class);
            Scanner mockScanner = mock(Scanner.class);
            ConsoleManager spyConsole = spy(ConsoleManager.class);
            // Act
            Contact spyContact1 = spy(new Contact("Juliet", "01234567891", "JB@Gmail.com"));
            spyBook.addContact(spyContact1);

            when(mockScanner.nextLine()).thenReturn("Search Daniel");
            spyMain.setScanner(mockScanner);
            spyMain.setAddressBook(spyBook);
            spyMain.setConsole(spyConsole);

            spyMain.readInput();

            //Assert
            Mockito.verify(spyConsole, times(1)).printOutput("Sorry there was no match for that name.");
        }
    }
}

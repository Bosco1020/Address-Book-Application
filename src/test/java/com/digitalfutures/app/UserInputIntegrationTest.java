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
    @Nested
    @DisplayName("Edit Tests")
    class EditTests {
        @Test
        @DisplayName("Console input of \"Edit Contact\"  prompts the user to update each field of the current contact from \"Search\"")
        public void InputOfEditContactPromptsForUpdatingEachFieldOfCurrentContact() {
            // Arrange
            Main spyMain = spy(Main.class);
            AddressBook spyBook = spy(AddressBook.class);
            Scanner mockScanner = mock(Scanner.class);
            ConsoleManager spyConsole = spy(ConsoleManager.class);

            String newName = "Dave";
            String newNumber = "12345678901";
            String newEmail = "David@Gamil.com";
            // Act
            Contact spyContact1 = spy(new Contact("David", "22222222222", "Dav-Id@mail.co.uk"));
            spyBook.addContact(spyContact1);

            when(mockScanner.nextLine()).thenReturn("Search David", "Edit Contact", newName, newNumber, newEmail);
            spyMain.setScanner(mockScanner);
            spyMain.setAddressBook(spyBook);
            spyMain.setConsole(spyConsole);

            spyMain.readInput();
            spyMain.readInput();

            //Assert
            ArrayList<Object> result = spyBook.searchContacts(newName);

            assertAll("Constructor set values to inputs",
                    () -> assertEquals(((Contact)result.get(0)).getName(), newName),
                    () -> assertEquals(((Contact)result.get(0)).getPhoneNumber(), newNumber),
                    () -> assertEquals(((Contact)result.get(0)).getEmail(), newEmail));
        }
        @Test
        @DisplayName("If there's multiple contacts viewed, user must designate which to edit")
        public void InputOfEditContactPromptsForSelectionIfMultipleContactsViewed() {
            // Arrange
            Main spyMain = spy(Main.class);
            AddressBook spyBook = spy(AddressBook.class);
            Scanner mockScanner = mock(Scanner.class);
            ConsoleManager spyConsole = spy(ConsoleManager.class);

            String newName = "Dave";
            String newNumber = "12345678901";
            String newEmail = "David@Gamil.com";
            // Act
            Contact spyContact1 = spy(new Contact("David", "22222222222", "Dav-Id@mail.co.uk"));
            spyBook.addContact(spyContact1);

            Contact spyContact2 = spy(new Contact("David", "01234567891", "Dav@Yahoo.com"));
            spyBook.addContact(spyContact2);

            when(mockScanner.nextLine()).thenReturn("Search David", "Edit Contact", "2", newName, newNumber, newEmail);
            spyMain.setScanner(mockScanner);
            spyMain.setAddressBook(spyBook);
            spyMain.setConsole(spyConsole);

            spyMain.readInput();
            spyMain.readInput();

            //Assert
            ArrayList<Object> result = spyBook.getContacts();

            assertAll("Constructor set values to inputs",
                    () -> assertEquals(((Contact)result.get(1)).getName(), newName),
                    () -> assertEquals(((Contact)result.get(1)).getPhoneNumber(), newNumber),
                    () -> assertEquals(((Contact)result.get(1)).getEmail(), newEmail),
                    () -> assertEquals(((Contact)result.get(0)).getName(), "David"),
                    () -> assertEquals(((Contact)result.get(0)).getPhoneNumber(), "22222222222"),
                    () -> assertEquals(((Contact)result.get(0)).getEmail(), "Dav-Id@mail.co.uk"));
        }
        @Test
        @DisplayName("Leaving a field blank when editing a contact doesn't change that property of the contact")
        public void InputOfEditContactInterpretsBlankInputsAsMaintainingCurrentProperty() {
            // Arrange
            Main spyMain = spy(Main.class);
            AddressBook spyBook = spy(AddressBook.class);
            Scanner mockScanner = mock(Scanner.class);
            ConsoleManager spyConsole = spy(ConsoleManager.class);

            String newName = "Dave";
            String newNumber = " ";
            String newEmail = "David@Gamil.com";
            // Act
            Contact spyContact1 = spy(new Contact("David", "22222222222", "Dav-Id@mail.co.uk"));
            spyBook.addContact(spyContact1);

            when(mockScanner.nextLine()).thenReturn("Search David", "Edit Contact", newName, newNumber, newEmail);
            spyMain.setScanner(mockScanner);
            spyMain.setAddressBook(spyBook);
            spyMain.setConsole(spyConsole);

            spyMain.readInput();
            spyMain.readInput();

            //Assert
            ArrayList<Object> result = spyBook.searchContacts(newName);

            assertAll("Constructor set values to inputs",
                    () -> assertEquals(((Contact)result.get(0)).getName(), newName),
                    () -> assertEquals(((Contact)result.get(0)).getPhoneNumber(), "22222222222"),
                    () -> assertEquals(((Contact)result.get(0)).getEmail(), newEmail));
        }
        @Test
        @DisplayName("After editing a contact, its new information is printed to the console")
        public void InputOfEditContactPrintsNewInformationToConsoleAfter() {
            // Arrange
            Main spyMain = spy(Main.class);
            AddressBook spyBook = spy(AddressBook.class);
            Scanner mockScanner = mock(Scanner.class);
            ConsoleManager spyConsole = spy(ConsoleManager.class);

            String newName = "Sam";
            String newNumber = " ";
            String newEmail = "SamE@Gamil.com";
            // Act
            Contact spyContact1 = spy(new Contact("Samuel", "22222222222", "Sammy@mail.co.uk"));
            spyBook.addContact(spyContact1);

            when(mockScanner.nextLine()).thenReturn("Search Samuel", "Edit Contact", newName, newNumber, newEmail);
            spyMain.setScanner(mockScanner);
            spyMain.setAddressBook(spyBook);
            spyMain.setConsole(spyConsole);

            spyMain.readInput();
            spyMain.readInput();

            //Assert
            Mockito.verify(spyConsole, times(2)).printOutput(any(), any(), any());
        }
    }
    @Nested
    @DisplayName("View Tests")
    class ViewTests {
        @Test
        @DisplayName("Console input of \"View Contacts\"  displays a list of each Contact in the Address Book")
        public void InputOfViewContactsPrintsAllContactsToTheConsole() {
            // Arrange
            Main spyMain = spy(Main.class);
            AddressBook spyBook = spy(AddressBook.class);
            Scanner mockScanner = mock(Scanner.class);
            ConsoleManager spyConsole = spy(ConsoleManager.class);
            // Act
            Contact spyContact1 = spy(new Contact("David", "22222222222", "Dav-Id@mail.co.uk"));
            spyBook.addContact(spyContact1);

            Contact spyContact2 = spy(new Contact("Samuel", "12345678901", "Sammy@mail.co.uk"));
            spyBook.addContact(spyContact2);

            Contact spyContact3 = spy(new Contact("Arri", "01133557799", "Arr-I@Aol.fr"));
            spyBook.addContact(spyContact3);

            when(mockScanner.nextLine()).thenReturn("View Contacts");
            spyMain.setScanner(mockScanner);
            spyMain.setAddressBook(spyBook);
            spyMain.setConsole(spyConsole);

            spyMain.readInput();

            //Assert
            Mockito.verify(spyConsole, times(3)).printOutput(any(), any(), any());
        }
    }
    @Nested
    @DisplayName("Delete Tests")
    class DeleteTests {
        @Test
        @DisplayName("Console input of \"Delete Contact\" deletes the current contact")
        public void InputOfDeleteContactDeletesCurrentContactFromAddressBook() {
            // Arrange
            Main spyMain = spy(Main.class);
            AddressBook spyBook = spy(AddressBook.class);
            Scanner mockScanner = mock(Scanner.class);
            ConsoleManager spyConsole = spy(ConsoleManager.class);
            // Act
            Contact spyContact1 = spy(new Contact("David", "22222222222", "Dav-Id@mail.co.uk"));
            spyBook.addContact(spyContact1);

            Contact spyContact2 = spy(new Contact("Samuel", "12345678901", "Sammy@mail.co.uk"));
            spyBook.addContact(spyContact2);

            Contact spyContact3 = spy(new Contact("Arri", "01133557799", "Arr-I@Aol.fr"));
            spyBook.addContact(spyContact3);

            when(mockScanner.nextLine()).thenReturn("Search Samuel", "Delete Contact");
            spyMain.setScanner(mockScanner);
            spyMain.setAddressBook(spyBook);
            spyMain.setConsole(spyConsole);

            spyMain.readInput();
            spyMain.readInput();

            //Assert
            assertEquals(spyBook.getContacts().size(), 2);
        }
    }
}

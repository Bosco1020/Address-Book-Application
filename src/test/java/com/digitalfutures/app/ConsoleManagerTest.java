package com.digitalfutures.app;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ConsoleManagerTest {
    @Nested
    @DisplayName("User Input Tests")
    class UserInputTests {

        @Test
        @DisplayName("The user should be able to input all details of a new contact through the console")
        public void UserCanUseConsoleToInputNewContactInformationThroughConsole() {
            // Arrange
            ConsoleManager testConsole = new ConsoleManager();
            Scanner mockScanner = mock(Scanner.class);
            // Act
            when(mockScanner.nextLine()).thenReturn("Jean");
            String newName = testConsole.collectName(mockScanner);

            when(mockScanner.nextLine()).thenReturn("01234567890");
            String newNumber = testConsole.collectName(mockScanner);

            when(mockScanner.nextLine()).thenReturn("J@Yahoo.co.uk");
            String newEmail = testConsole.collectName(mockScanner);
            //Assert
            assertAll("Constructor set values to inputs",
                    () -> assertEquals(newName, "Jean"),
                    () -> assertEquals(newNumber, "01234567890"),
                    () -> assertEquals(newEmail, "J@Yahoo.co.uk"));
        }

        @Test
        @DisplayName("Console Inputs for a new Contact should only be accepted if valid")
        public void ConsoleInputsForNewContactMustBeValid() {
            // Arrange
            ConsoleManager testConsole = new ConsoleManager();
            Scanner mockScanner = mock(Scanner.class);
            // Act
            when(mockScanner.nextLine()).thenReturn("  ", null, "Connor");
            String newName = testConsole.collectName(mockScanner);

            when(mockScanner.nextLine()).thenReturn("myNumber", null, "98765432109");
            String newNumber = testConsole.collectNumber(mockScanner);

            when(mockScanner.nextLine()).thenReturn("J.co.pl@Yahoo", null, "Coco@gmail.com");
            String newEmail = testConsole.collectEmail(mockScanner);
            //Assert
            assertAll("Constructor set values to inputs",
                    () -> assertEquals(newName, "Connor"),
                    () -> assertEquals(newNumber, "98765432109"),
                    () -> assertEquals(newEmail, "Coco@gmail.com"));
        }
    }
    //The ConsoleManager should print all Contacts in the Address Book into the console
    @Nested
    @DisplayName("Console Output Tests")
    class ConsoleOutputTests {

        @Test
        @DisplayName("The ConsoleManager prints all Contacts in the Address Book into the console")
        public void ConsoleManagerPintsDetailsOfAllContactsInAddressBook() {
            // Arrange
            ConsoleManager testConsole = spy(new ConsoleManager());
            ArrayList<Object> testContacts = new ArrayList<Object>();
            // Act
            Contact mockContact1 = mock(Contact.class);
            when(mockContact1.getName()).thenReturn("Florance");
            when(mockContact1.getPhoneNumber()).thenReturn("11111111111");
            when(mockContact1.getEmail()).thenReturn("FloFlo@Aol.co.uk");
            Contact mockContact2 = mock(Contact.class);
            when(mockContact2.getName()).thenReturn("Ricky");
            when(mockContact2.getPhoneNumber()).thenReturn("09876543210");
            when(mockContact2.getEmail()).thenReturn("Rick-Roll@Yahoo.com");
            Contact mockContact3 = mock(Contact.class);
            when(mockContact3.getName()).thenReturn("Leo");
            when(mockContact3.getPhoneNumber()).thenReturn("+445555555555");
            when(mockContact3.getEmail()).thenReturn("LeoOragano26@Gmail.com");

            testContacts.add(mockContact1);
            testContacts.add(mockContact2);
            testContacts.add(mockContact3);

            testConsole.printOutput(testContacts);
            //Assert
            Mockito.verify(testConsole, times(9)).printOutput(any(), any(), any());
        }
    }
}

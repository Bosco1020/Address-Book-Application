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

public class UserInputIntegrationTest {

    @Nested
    @DisplayName("New Contact Tests")
    class NewContactTests {

        @Test
        @DisplayName("Console input of \"New Contact\"  prompts the user to input a new Contacts info and returns the created Contact")
        public void InputOfNewContactPromptsUserToInputCOntactDetailsAndReturnsContact() {
            // Arrange
            ConsoleManager testConsole = new ConsoleManager();
            Scanner mockScanner = mock(Scanner.class);
            // Act
            when(mockScanner.nextLine()).thenReturn("New Contact", "Jean", "01234567890", "J@Yahoo.co.uk");
            Object createdContact = testConsole.readInput(mockScanner);
            //Assert
            assertAll("Constructor set values to inputs",
                    () -> assertEquals(((Contact)createdContact).getName(), "Jean"),
                    () -> assertEquals(((Contact)createdContact).getPhoneNumber(), "01234567890"),
                    () -> assertEquals(((Contact)createdContact).getEmail(), "J@Yahoo.co.uk"));
        }
    }
}

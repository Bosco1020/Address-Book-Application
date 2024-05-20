package com.digitalfutures.app;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ConsoleManagerTest {
    @Nested
    @DisplayName("User Input Tests")
    class UserInputTests {

        @Test
        @DisplayName("The user should be able to input all details of a new contact through the console")
        public void USerCanUseConsoleToInputNewContactInformationThroughConsole() {
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
    }

}

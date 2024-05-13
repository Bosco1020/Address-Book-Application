package com.digitalfutures.app;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ContactTest {

    @Nested
    @DisplayName("Constructor tests")
    class ConstructorTests {

        @Test
        @DisplayName("Constructor takes arguments for a contacts name, phone number & email and set those value")
        public void constructorTakesNameNumberEmailAndSetsValues() {
            // Arrange
            String name = "Sam";
            String phoneNumber = "07123456789";
            String email = "sam@aol.com";
            // Act
            Contact b = new Contact(name, phoneNumber, email);
            //Assert
            assertAll("Constructor set values to inputs",
                    () -> assertEquals(name, b.getName()),
                    () -> assertEquals(phoneNumber, b.getPhoneNumber()),
                    () -> assertEquals(email, b.getEmail()));
        }
        // Phone Number should only accept an 11 digit number input or a 11-13 digit number that starts with a +
    }
    @Nested
    @DisplayName("Constructor Name tests")
    class ConstructorNameTests {
        @Test
        @DisplayName("constructor throws IllegalArgumentException when name is null")
        public void constructorThrowsExceptionIfNameIsNull() {
            // Arrange
            String name = null;
            String phoneNumber = "07123456789";
            String email = "sam@aol.com";
            // Act
            //Assert
            assertThrows(IllegalArgumentException.class, () -> {
                new Contact(name, phoneNumber, email);
            });
        }
        @Test
        @DisplayName("constructor throws IllegalArgumentException when name is an empty string")
        public void constructorThrowsExceptionIfNameEmptyString() {
            // Arrange
            String name = "";
            String phoneNumber = "07123456789";
            String email = "sam@aol.com";
            // Act
            //Assert
            assertThrows(IllegalArgumentException.class, () -> {
                new Contact(name, phoneNumber, email);
            });
        }
        @Test
        @DisplayName("constructor throws IllegalArgumentException when name is only whitespace")
        public void constructorThrowsExceptionIfNameIsWhitespace() {
            // Arrange
            String name = "   ";
            String phoneNumber = "07123456789";
            String email = "sam@aol.com";
            // Act
            //Assert
            assertThrows(IllegalArgumentException.class, () -> {
                new Contact(name, phoneNumber, email);
            });
        }
    }
    @Nested
    @DisplayName("Constructor Phone Number tests")
    class ConstructorPhoneNumberTests {
        @Test
        @DisplayName("constructor throws IllegalArgumentException when phone number is null")
        public void constructorThrowsExceptionIfPhoneNumberIsNull() {
            // Arrange
            String name = "Sam";
            String phoneNumber = null;
            String email = "sam@aol.com";
            // Act
            //Assert
            assertThrows(IllegalArgumentException.class, () -> {
                new Contact(name, phoneNumber, email);
            });
        }
        @Test
        @DisplayName("constructor throws IllegalArgumentException when phone number is an empty string")
        public void constructorThrowsExceptionIfPhoneNumberEmptyString() {
            // Arrange
            String name = "Sam";
            String phoneNumber = "";
            String email = "sam@aol.com";
            // Act
            //Assert
            assertThrows(IllegalArgumentException.class, () -> {
                new Contact(name, phoneNumber, email);
            });
        }
    }

}

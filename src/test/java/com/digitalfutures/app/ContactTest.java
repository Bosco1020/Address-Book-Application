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
        @Test
        @DisplayName("constructor throws IllegalArgumentException when phone number is more or less than 11 characters excluding whitespace")
        public void constructorThrowsExceptionIfPhoneNumberIsWhitespace() {
            // Arrange
            String name = "Sam";
            String phoneNumber1 = "0712345";
            String phoneNumber2 = "07123456789101112";
            String email = "sam@aol.com";
            // Act
            //Assert
            assertAll("Constructor set values to inputs",
                    () -> assertThrows(IllegalArgumentException.class, () -> {new Contact(name, phoneNumber1, email);}),
                    () -> assertThrows(IllegalArgumentException.class, () -> {new Contact(name, phoneNumber2, email);}));
        }
        @Test
        @DisplayName("constructor throws IllegalArgumentException when phone number contains characters that aren't numbers 0-9")
        public void constructorThrowsExceptionIfPhoneNumberContainsNonNumbers() {
            // Arrange
            String name = "Sam";
            String phoneNumber = "NineHundred";
            String email = "sam@aol.com";
            // Act
            //Assert
            assertThrows(IllegalArgumentException.class, () -> {
                new Contact(name, phoneNumber, email);
            });
        }
        // When adding a region code, it can be 1 to 3 digits followed by the 10 digit number
        @Test
        @DisplayName("constructor doesn't throw IllegalArgumentException when phone number is 11 - 13 digits but starts with a +")
        public void constructorDoesntThrowsExceptionIfPhoneNumberIS11To13CharactersButStartsWithPlus() {
            // Arrange
            String name = "Sam";
            String phoneNumber = "+4412345678910";
            String email = "sam@aol.com";
            // Act
            //Assert
            assertDoesNotThrow(() -> new Contact(name, phoneNumber, email));
        }
    }

    @Nested
    @DisplayName("Constructor Email tests")
    class ConstructorEmailTests {
        @Test
        @DisplayName("constructor throws IllegalArgumentException when Email is null")
        public void constructorThrowsExceptionIfEmailIsNull() {
            // Arrange
            String name = "Sam";
            String phoneNumber = "07123456789";
            String email = null;
            // Act
            //Assert
            assertThrows(IllegalArgumentException.class, () -> {
                new Contact(name, phoneNumber, email);
            });
        }

        @Test
        @DisplayName("constructor throws IllegalArgumentException when email is empty string")
        public void constructorThrowsExceptionIfEmailIsEmptyString() {
            // Arrange
            String name = "Sam";
            String phoneNumber = "07123456789";
            String email = "";
            // Act
            //Assert
            assertThrows(IllegalArgumentException.class, () -> {
                new Contact(name, phoneNumber, email);
            });
        }

        @Test
        @DisplayName("constructor throws IllegalArgumentException when email is whitespace")
        public void constructorThrowsExceptionIfEmailIsWhitespace() {
            // Arrange
            String name = "Sam";
            String phoneNumber = "07123456789";
            String email = "   ";
            // Act
            //Assert
            assertThrows(IllegalArgumentException.class, () -> {
                new Contact(name, phoneNumber, email);
            });
        }

        @Test
        @DisplayName("constructor throws IllegalArgumentException when email doesn't conform to regular email format")
        public void constructorThrowsExceptionIfEmailDoesNotMatchEmailFormat() {
            // Arrange
            String name = "Sam";
            String phoneNumber = "07123456789";
            String email1 = "testEmail.com";
            String email2 = "testEmail@gmail";
            String email3 = "testEmail@hotmail.com";
            // Act
            //Assert
            assertAll("Constructor set values to inputs",
                    () -> assertThrows(IllegalArgumentException.class, () -> {new Contact(name, phoneNumber, email1);}),
                    () -> assertThrows(IllegalArgumentException.class, () -> {new Contact(name, phoneNumber, email2);}),
                    () -> assertDoesNotThrow(() -> new Contact(name, phoneNumber, email3)));
        }
    }

}

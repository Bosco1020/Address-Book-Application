package com.digitalfutures.app;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ContactTest {

    @Nested
    @DisplayName("Constructor tests")
    class ConstructorTests {

        // US1: Test 1
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

}

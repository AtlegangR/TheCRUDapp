/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */



import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import poe.part1.Registration;

class RegistrationTest {
    private Registration registration;

    @BeforeEach
    void setUp() {
        // Create a new Registration object with initial values
        registration = new Registration("user_name", "Password1!", "John", "Doe");
    }

    @Test
    void testGetFirstName() {
        registration.userDetails(); // Simulate user input
        assertNotNull(registration.getFirstName(), "First name should not be null");
    }

    @Test
    void testGetUserName() {
        registration.userDetails(); // Simulate user input
        assertNotNull(registration.getUserName(), "Username should not be null");
    }

    @Test
    void testGetSurname() {
        registration.userDetails(); // Simulate user input
        assertNotNull(registration.getSurname(), "Surname should not be null");
    }

    @Test
    void testGetPassword() {
        registration.userDetails(); // Simulate user input
        assertNotNull(registration.getPassword(), "Password should not be null");
    }

    @Test
    void testConstructorInitialization() {
        assertEquals("user_name", registration.getUserName(), "Username should match the constructor value");
        assertEquals("Password1!", registration.getPassword(), "Password should match the constructor value");
    }
}
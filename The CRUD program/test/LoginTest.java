/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import poe.part1.Login;
import poe.part1.Registration;

class LoginTest {
    private Registration registration;
    private Login login;

    @BeforeEach
    void setUp() {
        // Create a new Registration object with valid credentials
        registration = new Registration("user_name", "Password1!", "John", "Doe");
        
        // Set up a Login object with the Registration
        login = new Login(registration);
    }

    @Test
    void testCheckUserName_Valid() {
        // Directly check the user name validity
        assertTrue(login.checkUserName(), "Username should be valid");
    }

    @Test
    void testCheckUserName_Invalid() {
        // Change the username to an invalid format
        registration = new Registration("username", "Password1!", "John", "Doe");
        login = new Login(registration);
        assertFalse(login.checkUserName(), "Username should be invalid without underscore or length > 5");
    }

    @Test
    void testCheckPasswordComplexity_Valid() {
        // Directly check the password complexity
        assertTrue(login.checkPasswordComplexity(), "Password should meet complexity requirements");
    }

    @Test
    void testCheckPasswordComplexity_Invalid() {
        // Change the password to an invalid format
        registration = new Registration("user_name", "pass", "John", "Doe");
        login = new Login(registration);
        assertFalse(login.checkPasswordComplexity(), "Password should be invalid due to length");
        
        registration = new Registration("user_name", "Password", "John", "Doe");
        login = new Login(registration);
        assertFalse(login.checkPasswordComplexity(), "Password should be invalid due to missing number and special character");
        
        registration = new Registration("user_name", "Password1", "John", "Doe");
        login = new Login(registration);
        assertFalse(login.checkPasswordComplexity(), "Password should be invalid due to missing special character");
        
        registration = new Registration("user_name", "Password!", "John", "Doe");
        login = new Login(registration);
        assertFalse(login.checkPasswordComplexity(), "Password should be invalid due to missing number");
    }

    @Test
    void testRegisterUser_Valid() {
        // Register user and check output
        // Capture console output if needed (not shown here)
        login.registerUser();
        // Verify output here as needed
    }

    @Test
    void testLoginUser_Valid() {
        // Simulate user input for login
        System.setIn(new java.io.ByteArrayInputStream("user_name\nPassword1!".getBytes()));
        
        assertTrue(login.loginUser(), "Login should succeed with correct credentials");
    }

    @Test
    void testLoginUser_Invalid() {
        // Simulate user input for incorrect login
        System.setIn(new java.io.ByteArrayInputStream("wrong_user\nWrongPassword!".getBytes()));
        
        assertFalse(login.loginUser(), "Login should fail with incorrect credentials");
    }

    @Test
    void testReturnLoginStatus_Valid() {
        // Simulate user input for login
        System.setIn(new java.io.ByteArrayInputStream("user_name\nPassword1!".getBytes()));

        String status = login.returnLoginStatus();
        assertTrue(status.contains("Welcome"), "Login status should welcome the user");
    }

    @Test
    void testReturnLoginStatus_Invalid() {
        // Simulate user input for incorrect login
        System.setIn(new java.io.ByteArrayInputStream("wrong_user\nWrongPassword!".getBytes()));

        String status = login.returnLoginStatus();
        assertTrue(status.contains("incorrect"), "Login status should indicate incorrect credentials");
    }
}
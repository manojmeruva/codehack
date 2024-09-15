package com.example.demo;




import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import com.example.demo.controller.UserController;
import com.example.demo.models.User;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserControllerTest {

    @Autowired
    private UserController userController;

    @Test
    public void testRegisterUser() {
        User user = new User("1", "John");
        ResponseEntity<String> response = userController.registerUser(user);
        assertEquals("User registered successfully.", response.getBody());

        List<User> users = userController.getAllUsers();
        assertEquals(1, users.size());
    }

    @Test
    public void testUpdateScore() {
        User user = new User("2", "Alice");
        userController.registerUser(user);
        user.setScore(50);
        userController.updateUserScore("2", user);

        User updatedUser = userController.getUserById("2").getBody();
        assertNotNull(updatedUser);
        assertEquals(50, updatedUser.getScore());
        assertTrue(updatedUser.getBadges().contains("Code Champ"));
    }

    @Test
    public void testDeleteUser() {
        User user = new User("3", "Bob");
        userController.registerUser(user);
        userController.deleteUser("3");

        Exception exception = assertThrows(RuntimeException.class, () -> userController.getUserById("3"));
        assertEquals("User not found with ID: 3", exception.getMessage());
    }
}

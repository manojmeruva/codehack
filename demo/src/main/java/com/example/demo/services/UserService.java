package com.example.demo.services;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Exceptions.ResourceNotFoundException;
import com.example.demo.models.User;
import com.example.demo.repository.UserRepository;

import java.util.List;


@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAllByOrderByScoreDesc();
    }

    public User getUserById(String userId) {
        return (User)userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with ID: " + userId));
    }

    public void registerUser(String userId, String username) {
        if (userRepository.existsById(userId)) {
            throw new IllegalArgumentException("User ID already exists.");
        }
        User user = new User(userId, username);
        userRepository.save(user);
    }

    public void updateUserScore(String userId, int score) {
        User user = getUserById(userId);
        user.setScore(score);
        user.validateBadges();
        userRepository.save(user);  
    }

    public void deleteUser(String userId) {
        if (!userRepository.existsById(userId)) {
            throw new ResourceNotFoundException("User not found with ID: " + userId);
        }
        userRepository.deleteById(userId);
    }
}


package com.example.demo.models;



import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "users")
public class User {
    @Id
    private String userId;
    private String username;
    private int score;
    private Set<String> badges;

    public User(String userId, String username) {
        this.userId = userId;
        this.username = username;
        this.score = 0;
        this.badges = new HashSet<>();
    }

  
    public void setScore(int score) {
        if (score < 0 || score > 100) {
            throw new IllegalArgumentException("Invalid score. Score must be between 0 and 100.");
        }
        this.score = score;
        assignBadges();
        validateBadges();
    }

    private void assignBadges() {
        if(badges==null) badges = new HashSet<>();
        badges.clear();
        if (score >= 1 && score < 30) {
            badges.add("Code Ninja");
        } else if (score >= 30 && score < 60) {
            badges.add("Code Champ");
        } else if (score >= 60) {
            badges.add("Code Master");
        }
    }

    public void validateBadges() {
        if (badges.size() > 3) {
            throw new IllegalArgumentException("Invalid badges: A user cannot have more than 3 unique badges.");
        }
    }
}


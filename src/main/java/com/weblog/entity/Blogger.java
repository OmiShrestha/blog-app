/*
 * Author: Omi Shrestha
 * Blogger.java: Entity Class Representing a Blogger
 * Description: This class represents a blogger with personal details such as email, password, name, address, and
 * interests. It includes getter and setter methods for these attributes, with validation checks for email, password,
 * name, address, and interests. The interests are stored as a list of strings, representing various topics of interest
 * to the blogger.
 */

package com.weblog.entity;

import java.util.List;

public class Blogger {
    private String email;
    private String password;
    private String name;
    private String address;
    private List<String> interests;

    // Default constructor
    public Blogger() {
    }
    public Blogger(String email, String password, String name, String address, List<String> interests) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.address = address;
        this.interests = interests; // List of interests
    }

    // Getters
    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
    public String getName() {
        return name;
    }
    public String getAddress() {
        return address;
    }
    public List<String> getInterests() { // Return List<String> instead of String
        return interests;
    }

    // Setters
    public void setEmail(String email) {
        if (email != null && email.contains("@")) {
            this.email = email;
        } else {
            throw new IllegalArgumentException("Invalid email format");
        }
    }

    public void setPassword(String password) {
        if (password != null && password.length() >= 6) { // Simple password length check
            this.password = password;
        } else {
            throw new IllegalArgumentException("Password must be at least 6 characters long");
        }
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty.");
        }
        this.name = name;
    }

    public void setAddress(String address) {
        if (address == null || address.trim().isEmpty()) {
            throw new IllegalArgumentException("Address cannot be null or empty.");
        }
        this.address = address;
    }

    public void setInterests(List<String> interests) { // Accept List<String> as input
        if (interests == null || interests.isEmpty()) {
            throw new IllegalArgumentException("Interests cannot be null or empty.");
        }
        this.interests = interests;
    }
}

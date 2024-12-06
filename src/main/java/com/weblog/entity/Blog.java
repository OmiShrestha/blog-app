/*
 Author: Omi Shrestha
 Blog.java: Class for representing a Blog Post
 Description: This class defines a Blog Object that holds information about a blog post
 including its title, body content, privacy status, and the email of the blogger. It provides
 constructors for initializing a blog and getter methods to retrieve the values of its fields.
 */

package com.weblog.entity;

public class Blog {
    private String title;
    private String body;
    private boolean isPrivate;
    private String email;
    public Blog() {
    }
    public Blog(String title, String body, boolean isPrivate, String email) {
        this.title = title;
        this.body = body;
        this.isPrivate = isPrivate;
        this.email = email;
    }

    // Getters for title, body, privacy status and email.
    public String getTitle() {
        return title;
    }
    public String getBody() {
        return body;
    }

    public boolean isPrivate() {
        return isPrivate;
    }

    public void setPrivate(boolean isPrivate) {
        this.isPrivate = isPrivate;
    }

    public String getEmail() {
        return email;
    }

    // Setters for email, title, body, and privacy status.

    /**
     * Sets the email for the blog post.
     *
     * @param email The email of the blogger (cannot be null or empty).
     * @throws IllegalArgumentException if the email is null or empty.
     */
    public void setEmail(String email) {
        if (email == null || email.trim().isEmpty()) {
            throw new IllegalArgumentException("Email cannot be empty or null.");
        }
        this.email = email;
    }

    /**
     * Sets the title of the blog post.
     *
     * @param title The title of the blog post (cannot be null or empty).
     * @throws IllegalArgumentException if the title is null or empty.
     */
    public void setTitle(String title) {
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("Title cannot be empty or null.");
        }
        this.title = title;
    }

    /**
     * Sets the body content of the blog post.
     *
     * @param body The body content of the blog post (cannot be null or empty).
     * @throws IllegalArgumentException if the body is null or empty.
     */
    public void setBody(String body) {
        if (body == null || body.trim().isEmpty()) {
            throw new IllegalArgumentException("Body cannot be empty or null.");
        }
        this.body = body;
    }

    /**
     * Sets the privacy status of the blog post.
     *
     * @param privacyInput The privacy input ("Y" for private, "N" for public).
     * @throws IllegalArgumentException if the privacy input is invalid or null.
     */
    public void setPrivate(String privacyInput) {
        if (privacyInput == null || privacyInput.trim().isEmpty()) {
            throw new IllegalArgumentException("Privacy input cannot be empty or null.");
        }

        // Converts 'Y'/'N' to boolean
        if (privacyInput.equalsIgnoreCase("Y")) {
            this.isPrivate = true;
        } else if (privacyInput.equalsIgnoreCase("N")) {
            this.isPrivate = false;
        } else {
            throw new IllegalArgumentException("Invalid input for privacy. Please enter 'Y' or 'N'.");
        }
    }
}

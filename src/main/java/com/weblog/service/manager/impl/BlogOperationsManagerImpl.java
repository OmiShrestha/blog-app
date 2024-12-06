/*
 * Author: Omi Shrestha
 * BlogOperationsManager.java: Handles Blog-related operations for the user.
 * Description: This class manages operations such as logging in, adding readers to a blogger,
 * and viewing blogs written by specific bloggers.
 *
 * Methods:
 * - handleLogin: Handles the login functionality by verifying the user's credentials.
 * - addReaders: Allows the logged-in blogger to add readers (followers) to their blog.
 * - readBloggersBlogs: Allows the logged-in reader to view blogs of a specific blogger.
 */

package com.weblog.service.manager.impl;

import com.weblog.service.WeBlogService;
import com.weblog.service.manager.BlogOperationsManager;

import java.sql.SQLException;
import java.util.Scanner;

public class BlogOperationsManagerImpl implements BlogOperationsManager {
    private final WeBlogService blogService;
    private String loggedInEmail = "";

    public BlogOperationsManagerImpl(WeBlogService blogService) {
        this.blogService = blogService;
    }

    public void handleLogin(Scanner scanner) {
        boolean isAuthenticated = false;

        while (!isAuthenticated) {
            System.out.print("Enter your email: ");
            String email = scanner.nextLine();
            System.out.print("Enter your password: ");
            String password = scanner.nextLine();

            isAuthenticated = blogService.authenticate(email, password);
            if (isAuthenticated) {
                loggedInEmail = email;  // Store the email after successful login
                System.out.println("Login successful! \n");
            } else {
                System.out.println("Invalid email or password. Please try again. \n");
            }
        }
    }

    public void addReaders(Scanner scanner) {
        try {
            // List all bloggers
            System.out.println("List of all bloggers:");
            blogService.listBloggers(); // Assuming this method lists all bloggers and their emails

            // Use the logged-in blogger's email
            String bloggerEmail = loggedInEmail;

            // Prompt for reader emails
            System.out.print("Enter the email addresses of your readers (comma-separated): ");
            String readerEmails = scanner.nextLine();
            String[] emailArray = readerEmails.split(",");

            // Add each reader to the blogger
            for (String readerEmail : emailArray) {
                readerEmail = readerEmail.trim();
                if (!readerEmail.isEmpty()) {
                    blogService.addReader(bloggerEmail, readerEmail);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error adding readers: " + e.getMessage());
        }
    }

    public void readBloggersBlogs(Scanner scanner) {
        // List all bloggers
        System.out.println("List of all bloggers:");
        blogService.listBloggers(); // Assuming this method lists all bloggers and their emails

        // Prompt for the blogger's email
        System.out.print("Enter the blogger's email: ");
        String bloggerEmail = scanner.nextLine().trim();

        // Display blogs for the reader
        blogService.listBlogsForReader(bloggerEmail, loggedInEmail);
    }
}
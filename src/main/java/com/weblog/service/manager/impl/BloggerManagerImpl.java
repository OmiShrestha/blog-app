/*
 * Author: Omi Shrestha
 * BloggerManagerImpl.java: Service Layer for Blogger Operations
 * Description: This class provides the service layer for managing blogger-related operations. It interacts with
 * the BloggerDAO class to perform database operations such as registering a new blogger and listing all bloggers.
 * The class uses a Scanner object to read user input for registering a new blogger. The main functionality includes
 * registering a new blogger, listing all bloggers, and authenticating a blogger.
 *
 * Methods:
 * - registerBlogger: Reads blogger details from the console (email, password, name, address, and interests),
 *   creates a Blogger object, and registers the blogger using BloggerDAO.
 * - listBloggers: Retrieves and lists all registered bloggers by invoking the corresponding method in BloggerDAO.
 * - authenticate: Verifies blogger credentials (email and password) by calling the authenticate method in BloggerDAO.
 */

package com.weblog.service.manager.impl;

import com.weblog.db.BloggerDAO;
import com.weblog.entity.Blogger;
import com.weblog.db.impl.BloggerDAOImpl;
import com.weblog.service.manager.BloggerManager;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class BloggerManagerImpl implements BloggerManager {
    private final BloggerDAO bloggerDAO = new BloggerDAOImpl(); // DAO for blogger operations
    @Override
    // Method to register a new blogger
    public void registerBlogger(Scanner scanner) {
        // Creates a new blogger object with input details
        Blogger blogger = new Blogger();

        System.out.print("Enter a new email: ");
        String email = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();
        System.out.print("Name: ");
        String name = scanner.nextLine();
        System.out.print("Address: ");
        String address = scanner.nextLine();
        System.out.print("Interests (comma-separated): ");
        String interestsInput = scanner.nextLine();

        List<String> interests = Arrays.asList(interestsInput.split("\\s*,\\s*"));

        blogger.setEmail(email);
        blogger.setPassword(password);
        blogger.setName(name);
        blogger.setAddress(address);
        blogger.setInterests(interests);

        try {
            bloggerDAO.registerBlogger(blogger);
            System.out.println("Blogger registered successfully.");
            System.out.println();
        } catch (SQLException e) {
            System.err.println("Error registering blogger: " + e.getMessage());
        }
    }
    @Override
    public void listBloggers() {
        try {
            bloggerDAO.listBloggers();
        } catch (SQLException e) {
            System.err.println("Error listing bloggers: " + e.getMessage());
        }
    }
    @Override
    public boolean authenticate(String email, String password) {
        return bloggerDAO.authenticate(email, password);
    }
}

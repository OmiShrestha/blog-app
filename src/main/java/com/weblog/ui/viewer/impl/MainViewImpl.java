/*
 * Author: Omi Shrestha
 * MainViewImpl.java: Implementation of the MainView interface for user interaction.
 * Description: This class provides the user interface for the WeBlog application, allowing users to
 * interact with the system by logging in, registering bloggers, posting blogs, adding readers, and viewing blogs.
 * It delegates the actual operations to the service layer and displays results to the user.
 *
 * Methods:
 * - showLoginRegisterMenu: Displays the login/register menu with options for the user.
 * - showMenu: Displays the main menu with options for blog operations like listing bloggers, posting blogs, etc.
 * - showMessage: Displays a message to the user.
 * - showError: Displays an error message.
 * - getUserChoice: Reads the user's choice input.
 * - getBloggerDetails: Registers a new blogger by invoking the relevant service method.
 * - getBlogDetails: Invokes the service method to post a new blog.
 * - listBloggers: Displays a list of all registered bloggers.
 * - handleLogin: Handles the login functionality by invoking the relevant operation.
 * - addReaders: Adds readers to a blogger's blog.
 * - readBloggersBlogs: Allows a reader to view blogs of a specific blogger.
 */

package com.weblog.ui.viewer.impl;

import com.weblog.service.WeBlogService;
import com.weblog.service.manager.impl.BlogOperationsManagerImpl;
import com.weblog.ui.viewer.MainView;

import java.util.Scanner;

public class MainViewImpl implements MainView {
    private final WeBlogService blogService;
    private final BlogOperationsManagerImpl blogOperations;

    public MainViewImpl(WeBlogService blogService) {
        this.blogService = blogService;
        this.blogOperations = new BlogOperationsManagerImpl(blogService);
    }

    @Override
    public void showLoginRegisterMenu() {
        System.out.println("WELCOME TO THE WEBLOG APPLICATION");
        System.out.println("1. Login");
        System.out.println("2. Register");
        System.out.println("3. Exit");
        System.out.print("Enter your choice: ");
    }

    @Override
    public void showMenu() {
        System.out.println("Menu:");
        System.out.println("1. List all registered bloggers");
        System.out.println("2. Post a blog");
        System.out.println("3. Add readers");
        System.out.println("4. List all blogs");
        System.out.println("5. Logout");
        System.out.print("Enter your choice: ");
    }

    @Override
    public void showMessage(String message) {
        System.out.println(message);
        System.out.println();
    }

    @Override
    public void showError(String message) {
        System.out.println("Error: " + message);
        System.out.println();
    }

    @Override
    public int getUserChoice() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }

    @Override
    public void getBloggerDetails(Scanner scanner) {
        blogService.registerBlogger(scanner);
    }

    @Override
    public void getBlogDetails(Scanner scanner) {
        blogService.postBlog(scanner);
    }

    @Override
    public void listBloggers() {
        blogService.listBloggers();
    }

    @Override
    public void handleLogin(Scanner scanner) {
        blogOperations.handleLogin(scanner);
    }

    @Override
    public void addReaders(Scanner scanner) {
        blogOperations.addReaders(scanner);
    }

    @Override
    public void readBloggersBlogs(Scanner scanner) {
        blogOperations.readBloggersBlogs(scanner);
    }
}
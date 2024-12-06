/*
 Author: Omi Shrestha
 MainView.java: Interface for WeBlog Application View
 Description: Defines methods for displaying menus, showing messages, and handling user input
 in the WeBlog application. It ensures consistency across different view implementations.
 */

package com.weblog.ui.viewer;

import java.sql.SQLException;
import java.util.Scanner;

public interface MainView {
    void showLoginRegisterMenu();
    void showMenu();
    void showMessage(String message);
    void showError(String message);
    int getUserChoice();
    void getBloggerDetails(Scanner scanner);
    void getBlogDetails(Scanner scanner);
    void listBloggers();
    void handleLogin(Scanner scanner);
    void addReaders(Scanner scanner) throws SQLException;
    void readBloggersBlogs(Scanner scanner);
}

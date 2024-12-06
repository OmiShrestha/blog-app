/*
 * Author: Omi Shrestha
 * MainPresenter.java: Presenter for handling the main menu and user interaction.
 * Description: This class manages the flow of the application, handling user login, blogger registration,
 * and presenting the main menu for blog-related operations. It interacts with the view layer to display
 * options to the user and calls appropriate methods based on user input.
 *
 * Methods:
 * - start: Initiates the login/register process and leads to the main menu.
 * - mainMenu: Displays the main menu and handles blog operations like listing bloggers, adding readers,
 *   reading blogs, etc.
 */

package com.weblog.ui.presenter;

import com.weblog.ui.viewer.MainView;

import java.sql.SQLException;
import java.util.Scanner;

public class MainPresenter {
    private final MainView view;

    public MainPresenter(MainView view) {
        this.view = view;
    }

    public void start(Scanner scanner) {
        while (true) {
            view.showLoginRegisterMenu();
            int choice = view.getUserChoice();

            boolean validChoice = false;
            switch (choice) {
                case 1:
                    view.handleLogin(scanner);
                    validChoice = true;
                    break;
                case 2:
                    view.getBloggerDetails(scanner);
                    view.showMessage("Please log in to continue.");
                    break;
                case 3:
                    view.showMessage("Exiting the application...");
                    return;
                default:
                    view.showError("Invalid choice. Please try again.");
            }
            if (validChoice) {
                mainMenu(scanner);
            }
        }
    }

    private void mainMenu(Scanner scanner) {
        boolean running = true;

        while (running) {
            view.showMenu();
            int choice = view.getUserChoice();

            switch (choice) {
                case 1:
                    view.listBloggers();
                    break;
                case 2:
                    view.getBlogDetails(scanner);
                    break;
                case 3:
                    try {
                        view.addReaders(scanner);
                    } catch (SQLException e) {
                        System.err.println("Error: " + e.getMessage());
                    }
                    break;
                case 4:
                    view.readBloggersBlogs(scanner);
                    break;
                case 5:
                    view.showMessage("Logging out...");
                    running = false;
                    break;
                default:
                    view.showError("Invalid choice. Please try again.");
            }
        }
    }
}

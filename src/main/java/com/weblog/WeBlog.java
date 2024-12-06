/*
 Author: Omi Shrestha
 WeBlog.java: Main Application Class for WeBlog
 Description: This is the entry point of the WeBlog application. It initializes the necessary services,
 creates the viewer and presenter, and starts the application by passing user input via Scanner to the presenter.
 */

package com.weblog;

import com.weblog.service.WeBlogService;
import com.weblog.ui.presenter.MainPresenter;
import com.weblog.ui.viewer.impl.MainViewImpl;

import java.util.Scanner;

public class WeBlog {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        WeBlogService blogService = new WeBlogService();

        // Creates the view
        MainViewImpl view = new MainViewImpl(blogService);

        // Creates the presenter
        MainPresenter presenter = new MainPresenter(view);

        // Starts the application, passing the scanner to the presenter
        presenter.start(scanner);

        // Closes scanner at the end of the program
        scanner.close();
    }
}

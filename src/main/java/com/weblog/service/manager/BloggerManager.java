/*
 * Author: Omi Shrestha
 * BloggerManager.java: Service Interface for Blogger Operations
 */


package com.weblog.service.manager;

import java.util.Scanner;

public interface BloggerManager {
    void registerBlogger(Scanner scanner);
    void listBloggers();
    boolean authenticate(String email, String password);
}

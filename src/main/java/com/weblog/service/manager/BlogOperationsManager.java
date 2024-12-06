/*
 * Author: Omi Shrestha
 * BlogOperationsManager.java: Interface for Blog-related operations.
 */

package com.weblog.service.manager;

import java.sql.SQLException;
import java.util.Scanner;

public interface BlogOperationsManager {
    void handleLogin(Scanner scanner) throws SQLException;
    void addReaders(Scanner scanner) throws SQLException;
    void readBloggersBlogs(Scanner scanner) throws SQLException;
}
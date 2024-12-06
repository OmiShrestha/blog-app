/*
 Author: Omi Shrestha
 DBConfig.java: Database Configuration for Blog Application
 Description: This class defines the database configuration constants used for connecting
 to the PostgreSQL database. It includes the database URL, user, and password. These constants
 are used across the application for establishing database connections.
 */
package com.weblog.config;

public class DBConfig {
    // URL, Username and Password to connect to the PostgreSQL database
    public static final String DB_URL = "jdbc:postgresql://localhost:5432/mydb";
    public static final String USER = "omi";
    public static final String PASS = "shrestha";
}

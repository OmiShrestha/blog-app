/*
 * Author: Omi Shrestha
 * BloggerDAO.java: Data Access Object for Blogger Operations
 * Description: This class implements the BloggerDAO interface, providing methods for
 * interacting with the blogger-related data in the database. It includes functionality
 * for registering a new blogger, listing all bloggers, retrieving blogger details by email,
 * and authenticating a blogger's login credentials.
 *
 * The blogger's password is encrypted using BCrypt before being stored in the database.
 * The class uses JDBC for database connectivity and Spring Security's BCryptPasswordEncoder
 * for password encryption.
 *
 * Methods:
 * - registerBlogger: Registers a new blogger by inserting their details into the 'blogger'
 *   and 'blogger_interest' tables in the database, with the password encrypted.
 * - listBloggers: Retrieves and displays all bloggers' email, name, and address from the
 *   'blogger' table.
 * - getBloggerByEmail: Fetches the details of a blogger using their email address.
 * - authenticate: Verifies the blogger's credentials by checking the provided password
 *   against the stored encrypted password.
 */

package com.weblog.db.impl;

import java.sql.*;

import com.weblog.config.DBConfig;
import com.weblog.entity.Blogger;
import com.weblog.db.BloggerDAO;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class BloggerDAOImpl implements BloggerDAO {
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(14);
    @Override
    public void registerBlogger(Blogger blogger) throws SQLException {
        String encodedPassword = encoder.encode(blogger.getPassword()); // Encrypts the blogger's password
        String sql1 = "INSERT INTO blogger (email, password, name, address) VALUES (?, ?, ?, ?)";
        String sql2 = "INSERT INTO blogger_interest (email, interest) VALUES (?, ?)";

        try (Connection conn = DriverManager.getConnection(DBConfig.DB_URL, DBConfig.USER, DBConfig.PASS);
                PreparedStatement ps = conn.prepareStatement(sql1)) {
            ps.setString(1, blogger.getEmail());
            ps.setString(2, encodedPassword);
            ps.setString(3, blogger.getName());
            ps.setString(4, blogger.getAddress());
            ps.executeUpdate();
        }

        try (Connection conn = DriverManager.getConnection(DBConfig.DB_URL, DBConfig.USER, DBConfig.PASS);
             PreparedStatement ps2 = conn.prepareStatement(sql2)) {
            for (String interest : blogger.getInterests()) {
                ps2.setString(1, blogger.getEmail());
                ps2.setString(2, interest);
                ps2.executeUpdate();
            }
        }
    }

    @Override
    public void listBloggers() throws SQLException {
        String sql = "SELECT email, name, address FROM blogger";
        try (Connection conn = DriverManager.getConnection(DBConfig.DB_URL, DBConfig.USER, DBConfig.PASS);
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                System.out.println("Email: " + rs.getString("email") +
                        ", Name: " + rs.getString("name") +
                        ", Address: " + rs.getString("address"));
            }
            System.out.println();
        }
    }

    @Override
    public Blogger getBloggerByEmail(String email) throws SQLException {
        String sql = "SELECT * FROM blogger WHERE email = ?";
        try (Connection conn = DriverManager.getConnection(DBConfig.DB_URL, DBConfig.USER, DBConfig.PASS);
                PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, email);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Blogger blogger = new Blogger();
                    blogger.setEmail(rs.getString("email"));
                    blogger.setPassword(rs.getString("password"));
                    blogger.setName(rs.getString("name"));
                    blogger.setAddress(rs.getString("address"));
                    return blogger;
                }
            }
        }
        return null;
    }

    @Override
    public boolean authenticate(String email, String password) {
        try {
            Blogger blogger = getBloggerByEmail(email);
            if (blogger != null && BCrypt.checkpw(password, blogger.getPassword())) {
                return true;
            }
        } catch (SQLException e) {
            System.err.println("Error during authentication: " + e.getMessage());
        }
        return false;
    }
}

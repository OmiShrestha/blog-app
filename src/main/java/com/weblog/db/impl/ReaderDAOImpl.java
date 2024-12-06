/*
 * Author: Omi Shrestha
 * ReaderDAOImpl.java: Implementation of ReaderDAO for Managing Reader-Blogger Relationships
 * Description: This class implements the ReaderDAO interface, providing methods for managing
 * the relationship between bloggers and their followers in the database. The class interacts with
 * the 'reader' table to verify reader authorization and add new followers for bloggers.
 *
 * Methods:
 * - isReaderAuthorized: Checks if a specific follower is authorized to access a blogger's private blogs
 *   by verifying the existence of the relationship in the 'reader' table.
 * - addReader: Adds a new reader-follower relationship into the 'reader' table, allowing a user to follow a blogger.
 */

package com.weblog.db.impl;

import com.weblog.config.DBConfig;
import com.weblog.db.ReaderDAO;

import java.sql.*;
public class ReaderDAOImpl implements ReaderDAO {
    @Override
    public boolean isReaderAuthorized(String bloggerEmail, String followerEmail) throws SQLException {
        String sql = "SELECT COUNT(*) FROM reader WHERE blogger_email = ? AND follower_email = ?";

        try (Connection conn = DriverManager.getConnection(DBConfig.DB_URL, DBConfig.USER, DBConfig.PASS);
             PreparedStatement st = conn.prepareStatement(sql)) {
            st.setString(1, bloggerEmail);
            st.setString(2, followerEmail);

            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            System.out.println("Error checking authorization: " + e.getMessage());
        }

        return false;
    }

    @Override
    public void addReader(String bloggerEmail, String followerEmail) throws SQLException {
        String sql = "INSERT INTO reader (blogger_email, follower_email) VALUES (?, ?)";
        try (Connection conn = DriverManager.getConnection(DBConfig.DB_URL, DBConfig.USER, DBConfig.PASS);
             PreparedStatement st = conn.prepareStatement(sql)) {
            st.setString(1, bloggerEmail);
            st.setString(2, followerEmail);
            st.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error adding readers: " + e.getMessage());
            throw e;
        }
    }
}

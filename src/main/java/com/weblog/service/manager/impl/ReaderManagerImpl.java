/*
 * Author: Omi Shrestha
 * ReaderManagerImpl.java: Service Layer for Reader Operations
 * Description: This class provides the service layer for managing reader-related operations. It interacts with the
 * ReaderDAO class to perform database operations such as adding a new reader to a blogger's follower list and checking
 * the authorization status of a reader to access a blogger's private blogs.
 *
 * Methods:
 * - addReader: Adds a new reader (follower) to a blogger's list of readers using the ReaderDAO.
 *   It takes the blogger's email and the follower's email as inputs.
 * - isReaderAuthorized: Checks if a specific reader is authorized to access the private blogs of a given blogger.
 *   It interacts with ReaderDAO to validate the authorization.
 */

package com.weblog.service.manager.impl;

import com.weblog.db.ReaderDAO;
import com.weblog.db.impl.ReaderDAOImpl;
import com.weblog.service.manager.ReaderManager;

import java.sql.SQLException;
public class ReaderManagerImpl implements ReaderManager {
    private final ReaderDAO readerDAO = new ReaderDAOImpl();
    @Override
    // Method to add a new reader to a blogger
    public void addReader(String bloggerEmail, String followerEmail) throws SQLException {
        // Add the follower to the blogger's reader list
        readerDAO.addReader(bloggerEmail, followerEmail);
        System.out.println("Reader " + "'" + followerEmail + "'" + " had been added successfully.");
        System.out.println();
    }
    @Override
    public boolean isReaderAuthorized(String bloggerEmail, String followerEmail) throws SQLException {
        return readerDAO.isReaderAuthorized(bloggerEmail, followerEmail);
    }
}

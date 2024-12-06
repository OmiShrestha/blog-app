/*
 * Author: Omi Shrestha
 * ReaderManager.java: Service Interface for Reader Operations
 */

package com.weblog.service.manager;

import java.sql.SQLException;

public interface ReaderManager {
    void addReader(String bloggerEmail, String followerEmail) throws SQLException;
    boolean isReaderAuthorized(String bloggerEmail, String followerEmail) throws SQLException;
}

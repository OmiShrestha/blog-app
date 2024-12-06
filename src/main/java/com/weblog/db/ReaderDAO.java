/*
 * Author: Omi Shrestha
 * ReaderDAO.java: Interface for Reader-Blogger Relationship Operations
 */

package com.weblog.db;

import java.sql.SQLException;
public interface ReaderDAO {
    boolean isReaderAuthorized(String bloggerEmail, String followerEmail) throws SQLException;
    void addReader(String bloggerEmail, String followerEmail) throws SQLException;
}

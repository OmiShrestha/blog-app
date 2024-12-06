/*
 * Author: Omi Shrestha
 * BloggerDAO.java: Interface for Blogger Database Operations
 */

package com.weblog.db;

import com.weblog.entity.Blogger;
import java.sql.SQLException;

public interface BloggerDAO {
    void registerBlogger(Blogger blogger) throws SQLException;
    void listBloggers() throws SQLException;
    Blogger getBloggerByEmail(String email) throws SQLException;
    boolean authenticate(String email, String password);
}
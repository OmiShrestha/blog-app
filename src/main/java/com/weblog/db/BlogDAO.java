/*
 * Author: Omi Shrestha
 * BlogDAO.java: Interface for Blog Database Operations
 */

package com.weblog.db;

import com.weblog.entity.Blog;

import java.sql.SQLException;
import java.util.List;

public interface BlogDAO {

    void postBlog(Blog blog) throws SQLException;
    List<Blog> listPublicBlogsByBlogger(String bloggerEmail) throws SQLException;
    List<Blog> listPrivateBlogsByBlogger(String bloggerEmail, String followerEmail) throws SQLException;
}
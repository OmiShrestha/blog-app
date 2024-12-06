/*
 * Author: Omi Shrestha
 * BlogDAOImpl.java: Implementation of BlogDAO for Database Interaction
 * Description: This class implements the BlogDAO interface, providing methods for posting new blogs and listing both
 * public and private blogs from the database.
 * The methods interact with the database to insert new blog entries and retrieve blog data based on privacy settings
 * and blogger information.
 */

package com.weblog.db.impl;

import com.weblog.config.DBConfig;
import com.weblog.entity.Blog;
import com.weblog.db.BlogDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BlogDAOImpl implements BlogDAO {
    @Override
    public void postBlog(Blog blog) throws SQLException {

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());

        String sql = "INSERT INTO blog (title, body, is_private, date_time, email) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(DBConfig.DB_URL, DBConfig.USER, DBConfig.PASS);
                PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, blog.getTitle());
            ps.setString(2, blog.getBody());
            ps.setBoolean(3, blog.isPrivate());
            ps.setTimestamp(4, timestamp);
            ps.setString(5, blog.getEmail());
            ps.executeUpdate();
        }
        System.out.println();
    }

    @Override
    public List<Blog> listPublicBlogsByBlogger(String bloggerEmail) throws SQLException {
        String sql = "SELECT * FROM blog WHERE is_private = false AND email = ?";
        List<Blog> blogs = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(DBConfig.DB_URL, DBConfig.USER, DBConfig.PASS);
                PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, bloggerEmail);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Blog blog = new Blog();
                    blog.setTitle(resultSet.getString("title"));
                    blog.setBody(resultSet.getString("body"));
                    blog.setPrivate(resultSet.getBoolean("is_private"));
                    blog.setEmail(resultSet.getString("email"));
                    blogs.add(blog);
                }
            }
        }
        return blogs;
    }

    @Override
    public List<Blog> listPrivateBlogsByBlogger(String bloggerEmail, String followerEmail) throws SQLException {
        String sql = "SELECT b.title, b.body, b.is_private, b.email " +
                "FROM blog b " +
                "JOIN reader r ON b.email = r.blogger_email " +
                "WHERE b.is_private = true AND b.email = ? AND r.follower_email = ?";
        List<Blog> blogs = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(DBConfig.DB_URL, DBConfig.USER, DBConfig.PASS);
                PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, bloggerEmail);
            statement.setString(2, followerEmail);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Blog blog = new Blog();
                    blog.setTitle(resultSet.getString("title"));
                    blog.setBody(resultSet.getString("body"));
                    blog.setPrivate(resultSet.getBoolean("is_private"));
                    blog.setEmail(resultSet.getString("email"));
                    blogs.add(blog);
                }
            }
        }
        return blogs;
    }
}

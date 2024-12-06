/*
 * Author: Omi Shrestha
 * WeBlogService.java: Service class for handling blogger, reader, and blog operations.
 * Description: This class implements methods for managing bloggers, readers, and blog posts.
 * It handles user authentication, blogger registration, adding readers, posting blogs,
 * and checking reader authorization.
 */

package com.weblog.service;

import com.weblog.service.manager.BlogManager;
import com.weblog.service.manager.BloggerManager;
import com.weblog.service.manager.ReaderManager;
import com.weblog.service.manager.impl.BlogManagerImpl;
import com.weblog.service.manager.impl.BloggerManagerImpl;
import com.weblog.service.manager.impl.ReaderManagerImpl;

import java.sql.SQLException;
import java.util.Scanner;

public class WeBlogService implements ReaderManager, BlogManager, BloggerManager {
    private final ReaderManager readerManager = new ReaderManagerImpl();
    private final BlogManagerImpl blogManagerImpl = new BlogManagerImpl(this);
    private final BlogManager blogManager = blogManagerImpl;
    private final BloggerManager bloggerManager = new BloggerManagerImpl();
    private String loggedInEmail;

    // BloggerManager methods
    @Override
    public void registerBlogger(Scanner scanner) {
        bloggerManager.registerBlogger(scanner);
    }

    @Override
    public void listBloggers() {
        bloggerManager.listBloggers();
    }
    @Override
    public boolean authenticate(String email, String password) {
        boolean isAuthenticated = bloggerManager.authenticate(email, password);
        if (isAuthenticated) {
            loggedInEmail = email;
        }
        return isAuthenticated;
    }

    // BlogManager methods
    @Override
    public void postBlog(Scanner scanner) {
        blogManager.postBlog(scanner);
    }

    // ReaderManager methods
    @Override
    public void addReader(String bloggerEmail, String followerEmail) throws SQLException {
        readerManager.addReader(bloggerEmail, followerEmail);
    }
    public void listBlogsForReader(String bloggerEmail, String readerEmail) {
        blogManagerImpl.listBlogsForReader(bloggerEmail, readerEmail);
    }
    @Override
    public boolean isReaderAuthorized(String bloggerEmail, String followerEmail) throws SQLException {
        return readerManager.isReaderAuthorized(bloggerEmail, followerEmail);
    }
    public String getLoggedInEmail() {
        return loggedInEmail;
    }
}

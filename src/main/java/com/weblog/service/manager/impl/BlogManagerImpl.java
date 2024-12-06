/*
 * Author: Omi Shrestha
 * BlogManagerImpl.java: Service Layer for Blog Operations
 * Description: This class provides the service layer for managing blog-related operations. It interacts with the
 * BlogDAO class to perform database operations such as posting a new blog and listing blogs for a reader. The class
 * also utilizes the WeBlogService to manage user authentication and authorization.
 *
 * Methods:
 * - postBlog: Prompts the user to enter blog details (title, body, privacy status), creates a Blog object,
 *   and posts it using BlogDAO. The blog is associated with the currently logged-in blogger.
 * - listBlogsForReader: Lists public blogs of a specified blogger and, if the reader is authorized, lists the
 *   private blogs as well. It checks for authorization using WeBlogService's isReaderAuthorized method before
 *   displaying private blogs.
 */

package com.weblog.service.manager.impl;

import com.weblog.db.BlogDAO;
import com.weblog.entity.Blog;
import com.weblog.db.impl.BlogDAOImpl;
import com.weblog.service.WeBlogService;
import com.weblog.service.manager.BlogManager;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class BlogManagerImpl implements BlogManager {
    private final BlogDAO blogDAO = new BlogDAOImpl(); // DAO for Blog Operations
    private final WeBlogService blogService;

    public BlogManagerImpl(WeBlogService blogService) {
        this.blogService = blogService;
    }

    @Override
    // Posts blog
    public void postBlog(Scanner scanner) {
        Blog blog = new Blog();

        String email = blogService.getLoggedInEmail();
        System.out.print("Title: ");
        String title = scanner.nextLine();
        System.out.print("Body: ");
        String body = scanner.nextLine();
        System.out.print("Private (Y/N): ");
        String privacyInput = scanner.nextLine();

        blog.setEmail(email);
        blog.setTitle(title);
        blog.setBody(body);
        blog.setPrivate(privacyInput);

        try {
            blogDAO.postBlog(blog);
            System.out.println("Blog posted successfully.");
            System.out.println();
        } catch (SQLException e) {
            System.err.println("Error posting blog: " + e.getMessage());
        }
    }
    public void listBlogsForReader(String bloggerEmail, String readerEmail) {
        try {
            // List public blogs of the specific blogger
            List<Blog> publicBlogs = blogDAO.listPublicBlogsByBlogger(bloggerEmail);
            System.out.println("--------------------------");
            System.out.println("Public Blogs: ");
            System.out.println("--------------------------");

            for (Blog blog : publicBlogs) {
                System.out.println("Title: " + blog.getTitle());
                System.out.println("Body: " + blog.getBody());
                System.out.println("Private: " + blog.isPrivate());
                System.out.println("Email: " + blog.getEmail());
                System.out.println();
            }
            // List private blogs of the specific blogger if the reader is authorized
            if (blogService.isReaderAuthorized(bloggerEmail, readerEmail)) {
                List<Blog> privateBlogs = blogDAO.listPrivateBlogsByBlogger(bloggerEmail, readerEmail);
                System.out.println("--------------------------");
                System.out.println("Private Blogs: ");
                System.out.println("--------------------------");
                for (Blog blog : privateBlogs) {
                    System.out.println("Title: " + blog.getTitle());
                    System.out.println("Body: " + blog.getBody());
                    System.out.println("Private: " + blog.isPrivate());
                    System.out.println("Email: " + blog.getEmail());
                    System.out.println();
                }
            } else {
                System.out.println("You do not have access to any private blogs. \n");
            }
        } catch (SQLException e) {
            System.err.println("Error listing blogs: " + e.getMessage());
        }
    }
}
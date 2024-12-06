/*
 Author: Omi Shrestha
 File name: table.sql
 Description: This SQL script is used for the creation and management of the "blogger", "blogger_interest",
              "blog", "comment", and "reader" tables in a PostgreSQL database for a weblog management system.
              It creates tables for storing information about bloggers, their interests, blog posts, comments on
              blogs, and readers. The script defines the structure and relationships between these tables
              with appropriate data types, primary keys, and foreign key constraints to ensure data integrity.
*/

-- Blogger table to store blogger details
CREATE TABLE blogger (
    email VARCHAR(255) PRIMARY KEY,
    password VARCHAR(255),
    name VARCHAR (255),
    address VARCHAR(255)
);

-- Blogger interest table to store each blogger's interests
CREATE TABLE blogger_interest (
    email VARCHAR(255),
    interest VARCHAR(255),
    PRIMARY KEY (email, interest),
    FOREIGN KEY (email) REFERENCES blogger(email)
);

-- Blog table to store individual blog posts
CREATE TABLE blog (
    id SERIAL PRIMARY KEY,
    title VARCHAR(255),
    body TEXT,
    is_private BOOLEAN,
    date_time TIMESTAMP,
    email VARCHAR(255),
    FOREIGN KEY (email) REFERENCES blogger(email)
);

-- Comment table to store comments on blog posts
CREATE TABLE comment (
    blog_id INT,
    seq_num INT,
    body TEXT,
    date_time TIMESTAMP,
    email VARCHAR(255),
    PRIMARY KEY (blog_id, seq_num),
    FOREIGN KEY (blog_id) REFERENCES blog(id),
    FOREIGN KEY (email) REFERENCES blogger(email)
);

-- Reader table to store readers and followings between bloggers
CREATE TABLE reader (
    blogger_email VARCHAR(255),
    follower_email VARCHAR(255),
    PRIMARY KEY (blogger_email, follower_email),
    FOREIGN KEY (blogger_email) REFERENCES blogger(email),
    FOREIGN KEY (follower_email) REFERENCES blogger(email)
);



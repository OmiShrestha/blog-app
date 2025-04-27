# WeBlog Application

## Overview
WeBlog is a Java-based web application that allows users to share their blogs. Users, known as bloggers, can post both public and private blogs. Public blogs are accessible to all users, while private blogs can only be viewed and commented on by followers of the blogger.

## Technologies Used
- **Java**: Core programming language used for the application logic.
- **PostgreSQL**: Database used to store blogger and blog data.
- **JDBC**: Java Database Connectivity for interacting with the PostgreSQL database.
- **Spring Security**: For password encryption.
- **Docker Containers**: For containerizing the application to ensure consistency across different environments.
  
![Java](https://img.shields.io/badge/Java-007396?style=for-the-badge&logo=java&logoColor=white)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-4169E1?style=for-the-badge&logo=postgresql&logoColor=white)
![JDBC](https://img.shields.io/badge/JDBC-007396?style=for-the-badge&logo=java&logoColor=white)
![Spring Security](https://img.shields.io/badge/Spring%20Security-6DB33F?style=for-the-badge&logo=springsecurity&logoColor=white)
![Docker](https://img.shields.io/badge/Docker-2496ED?style=for-the-badge&logo=docker&logoColor=white)

## Project Structure
The project follows a modular structure organized into several packages:

### Config
Contains configuration classes for database connectivity.
- `DBConfig.java`: Configuration class for setting up the database connection.

### DAO
Contains Data Access Object (DAO) interfaces and their implementations for database operations.
- `BlogDAO.java`: Interface for blog-related database operations.
- `BloggerDAO.java`: Interface for blogger-related database operations.
- `ReaderDAO.java`: Interface for reader-related database operations.

### DAO.impl
Contains implementations of the DAO interfaces.
- `BlogDAOImpl.java`: Implementation of BlogDAO.
- `BloggerDAOImpl.java`: Implementation of BloggerDAO.
- `ReaderDAOImpl.java`: Implementation of ReaderDAO.

### Entity
Contains the entity classes that map to the database tables.
- `Blog.java`: Represents a blog entity.
- `Blogger.java`: Represents a blogger entity.

### Service
Contains service interfaces and their implementations.
#### Manager
- `BlogManager.java`: Interface for blog-related services.
- `BloggerManager.java`: Interface for blogger-related services.
- `BlogOperationsManager.java`: Interface for blog operations-related services.
- `ReaderManager.java`: Interface for reader-related services.

#### Manager.impl
Contains implementations of the service interfaces.
- `BlogManagerImpl.java`: Implementation of BlogManager.
- `BloggerManagerImpl.java`: Implementation of BloggerManager.
- `BlogOperationsManagerImpl.java`: Implementation of BlogOperationsManager.
- `ReaderManagerImpl.java`: Implementation of ReaderManager.

### UI
Contains classes related to the user interface.
#### Presenter
- `MainPresenter.java`: Presenter class that handles user interactions.

#### Viewer
- `MainView.java`: Interface for the main view of the application.

#### Viewer.impl
- `MainViewImpl.java`: Implementation of the MainView interface.

`WeBlog.java`: The main class that starts the program and launches the user interface.

## Functional Requirements

### 1. Login Use Case
- The App displays the menu with the Login option, and the user selects the option.
- The App prompts the user for email and password, and the user enters them.
- The App verifies the email and password. If the authentication fails, the App displays an error message and asks the user to enter a new email and/or a new password.
- After successfully logging in, the App saves the Blogger (maybe in the MainPresenter) as the Session data for the blogger.

### 2. Add Readers/Followers
- The current Blogger (after either successful registration or login), the App displays the menu with an option for adding readers/followers, and the user selects the option.
- The App displays all bloggers and asks the Blogger to select readers/followers by entering their email addresses. You may determine what prompts to use and how to terminate this process (e.g., comma-separated list, or one email per line).

### 3. Read Blogger’s Blogs
- The current Blogger (after either successful registration or login), the App displays the menu with an option for Read Blogger’s Blogs, and the user selects the option.
- The App displays all bloggers.
- The Blogger enters the email of the blogger whose blogs the current Blogger wants to read. If the current Blogger is a reader or a follower, the App would display both public and private blogs. Otherwise, only public blogs would be displayed.

## Features
The application provides the following functionalities:
- **Register as a Blogger**: Users can register as a blogger by entering their email, password (which is encoded), name, address, and a list of interests.
- **List All Registered Bloggers**: Display a list of all bloggers, showing their email, name, and address.
- **Post a Blog**: Bloggers can create a blog by entering their email, blog title, body, and privacy setting (public/private). The system automatically records the current date and time as the blog’s posting time.
- **Add Readers/Followers**: Bloggers can add readers to their blog by entering the email addresses of the readers.
- **Read Blogger’s Blogs**: Bloggers can read blogs of other bloggers by entering the email of the blogger. If the current blogger is a reader or follower, both public and private blogs will be displayed. Otherwise, only public blogs will be displayed.
- **Exit**: Exits the application.

## Prerequisites
- **Java (JDK 11 or higher)**
- **PostgreSQL** (Ensure you have PostgreSQL running locally or in a container)
- **JDBC driver for PostgreSQL** (ensure it's included in the project)

## Setup Instructions
### Database Setup
1. Create a PostgreSQL database with a name of your choice.
2. Create username and password for your database.
3. Run the SQL script `table.sql` to create the `book` table and insert sample data.

### Development Environment Setup
1. **Install Java Development Kit (JDK)**:
    - Ensure that JDK is installed on your system.
2. **Install PostgreSQL**:
    - Download and install PostgreSQL.
3. **Download PostgreSQL JDBC Driver**:
    - Download the JDBC driver from [PostgreSQL JDBC Download Page](https://jdbc.postgresql.org/download/.).
    - Make sure to place the downloaded JAR file in the same directory as your `MyStore.java` file or note its path for
      the classpath.
4. **Set Environment Variables** (optional):
    - If you want to run Java commands from any directory, ensure that the JDK `bin` directory is added to your
      system's PATH environment variable.
5. **Verify Installation**:
    - Check if Java and PostgreSQL are correctly installed by running the following commands in your terminal:
      ```bash
      java -version
      ```
      ```bash
      psql --version
      ```

### Steps to Install and Run
**Clone the repository**:
   ```bash
   git clone <repository_url>
   cd weblog

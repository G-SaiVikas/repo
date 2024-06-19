# Recruitment Portal: Job Search and Application System


# Introduction 
The Recruitment Portal project is designed to simplify the job search and application process for job seekers. It offers a user-friendly interface for browsing available jobs, applying to them, and saving them for future applications. Built using Spring Boot, MySQL, and React, this application aims to streamline the recruitment process by providing essential functionalities like user authentication, job search filters, and job application tracking.

# Architecture

Our application follows a multi-tier architecture:

![Screenshot 2024-05-28 233331 1](https://github.com/G-SaiVikas/repo/assets/171183561/fd515e1c-8db6-48ec-be8f-616d5bebfcec)


  Frontend: Built with ReactJS, providing a responsive and interactive user interface.
  Backend: Developed using Spring Boot, handling business logic and API endpoints.
  Database: MySQL for data storage, ensuring reliable and efficient data management.



# Components Used  
  1. Spring Boot: An open-source Java framework for building production-ready applications.  
  2. MySQL: A popular relational database management system.  
  3. ReactJS: A JavaScript library for building user interfaces.  
  4. Swagger UI: Tools for API documentation and testing.  

# Features
  1. User Registration and Login: Secure authentication system for users to register and log in.  
  2. Job Search: Advanced search functionality to find jobs based on various filters like location, skills required, and job title.  
  3. Apply for Jobs: Users can apply for jobs directly through the portal.  
  4. Save Jobs: Users can save jobs to review and apply later.  
  5. User Profile: Manage personal information and view applied and saved jobs.  

# Running the Application Locally 

**Method 1: Using Docker Compose**
  1. Download the Docker Compose YAML file: Click to Download Docker Compose YAML  
  
  2. Run the command in the terminal:  
  ```bash
  docker-compose up
  ```

**Method 2: Cloning the Repository**
  1. Clone this repository and navigate to the project directory.
  
  2. Build and run the backend:
  
  ```bash
  
  cd recruitment-portal-backend
  ./gradlew build
  ./gradlew bootRun
  ```
  3. Build and run the frontend:
  
  ```bash
  cd ../recruitment-portal-frontend
  npm install
  npm start
  ```

# Miscellaneous and Useful Commands   
  -> To start the Spring Boot server:  
  
  ```bash
  cd recruitment-portal-backend  
  ./gradlew bootRun
  ```
  -> To start the frontend:  
  
  ```bash
  cd recruitment-portal-frontend  
  npm start
  ```
  -> To run all applications at once with Docker Compose:  
  
  ```bash
  docker-compose up
  ```
# Limitations of the Application    
  1. The current implementation does not include the admin module.  
  2. The application does not support advanced analytics or machine learning for job recommendations.  

# Future Enhancements   
  1. **Admin Module:** Adding an admin panel to manage job postings and user accounts.  
  2. **Recommendation System:** Implementing a machine learning algorithm to suggest jobs to users based on their profile and activity.  
  3. **Mobile App:** Developing a mobile application for better accessibility.  

# Zoom Meeting Link: 
Video demo zoom recording link  


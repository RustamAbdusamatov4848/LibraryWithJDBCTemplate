# Web-Library

## Brief description of the project
This is my first full-fledged project using the Spring framework.
The essence of this project, based on the name, is to provide a simple functionality for an online library, where a conditional librarian has 2 tables (Books and Peoples). 

## Install project

## Installation

To install and run the Web-Library project, follow these steps:

1. Clone the repository on your local machine:

    ```bash
    git clone https://github.com/RustamAbdusamatov4848/LibraryWithJDBCTemplate.git
    ```

2. Navigate to the project directory:

    ```bash
    cd LibraryWithJDBCTemplate
    ```

3. Install dependencies using Maven:

    ```bash
    mvn install
    ```

4. Run the application:
   
    To run the application you will need Tomcat (I'm using version 10)

5. Open your browser and go to the following URL:

    ```
    http://localhost:8080/books
    ```

You are now ready to start using Web-Library!

## Note
The UI uses Russian language

## Technologies and Libraries

The Web-Library project utilizes the following technologies and libraries:

- **JUnit** (version 3.8.1)
- **Spring Framework**:
  - **spring-core**
  - **spring-context**
  - **spring-web**
  - **spring-webmvc**
  - **spring-jdbc**
- **Thymeleaf**
- **Jakarta Servlet API**
- **Hibernate Validator**
- **MySQL Connector/J**
- **Project Lombok**

## Usage
The librarian has the ability to:
1. Add/Edit and remove people from the database;
2. Add / Edit and delete books from the database;
3. Give out books to a certain person, or after the person returned the book back to the library, mark it in the database.

## Project status:

completed

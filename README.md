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

---


# Web-Library

## Краткое описание проекта
Это мой первый полноценный проект с использованием фреймворка Spring.
Суть этого проекта, исходя из названия, заключается в предоставлении простой функциональности для онлайн-библиотеки, где условный библиотекарь имеет две таблицы (Books и Peoples).

## Установка проекта

## Установка

Для установки и запуска проекта Web-Library выполните следующие шаги:

1. Клонируйте репозиторий на ваш локальный компьютер:

    ```bash
    git clone https://github.com/RustamAbdusamatov4848/LibraryWithJDBCTemplate.git
    ```

2. Перейдите в директорию проекта:

    ```bash
    cd LibraryWithJDBCTemplate
    ```

3. Установите зависимости с помощью Maven:

    ```bash
    mvn install
    ```

4. Запустите приложение:
   
    Для запуска приложения вам понадобится Tomcat (я использую версию 10)

5. Откройте браузер и перейдите по следующему адресу:

    ```
    http://localhost:8080/books
    ```

Теперь вы готовы начать использовать Web-Library!

## Примечание
Интерфейс использует русский язык.

## Технологии и библиотеки

Проект Web-Library использует следующие технологии и библиотеки:

- **JUnit** (версия 3.8.1)
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

## Использование
Библиотекарь имеет возможность:
1. Добавлять, редактировать и удалять людей из базы данных;
2. Добавлять, редактировать и удалять книги из базы данных;
3. Выдавать книги определенному человеку или, после того как человек вернул книгу в библиотеку, отмечать это в базе данных.

## Статус проекта:

завершен

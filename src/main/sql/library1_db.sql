CREATE TABLE Person (
                        id int auto_increment PRIMARY KEY,
                        full_name varchar(100) NOT NULL UNIQUE,
                        year_of_birth int NOT NULL
);

CREATE TABLE Book (
                      id int auto_increment PRIMARY KEY,
                      title varchar(100) NOT NULL,
                      author varchar(100) NOT NULL,
                      year int NOT NULL,
                      person_id int REFERENCES Person(id) ON DELETE SET NULL
);

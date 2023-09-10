CREATE TABLE Person(
     id int GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
     full_name varchar(80) NOT NULL UNIQUE,
     date_born int NOT NULL
);

CREATE TABLE Book (
    id int GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    title varchar(100) NOT NULL,
    author varchar(100) NOT NULL,
    year int NOT NULL,
    person_id int REFERENCES Person(id) ON DELETE SET NULL
);
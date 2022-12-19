CREATE TABLE users(
    id NOT NULL AUTO_INCREMENT,
    name VARCHAR(100),
    username VARCHAR(100) UNIQUE,
    email VARCHAR(200) UNIQUE,
    password VARCHAR(200),
    roles FOREIGN KEY(roles) REFERENCES roles(id) ON UPDATE CASCADE
    PRIMARY KEY(id)
);

CREATE TABLE roles(
    id NOT NULL AUTO_INCREMENT,
    name VARCHAR(100),
    PRIMARY KEY(id)
);

CREATE TABLE posts(
    id NOT NULL AUTO_INCREMENT,
    title VARCHAR(100) UNIQUE,
    description VARCHAR(100),
    content VARCHAR(100),
    comments VARCHAR(100) FOREIGN KEY(comments) REFERENCES comments ON UPDATE CASCADE,
    PRIMARY KEY(id)
);

CREATE TABLE comments(
    id NOT NULL AUTO_INCREMENT,
    name VARCHAR(100) UNIQUE,
    email VARCHAR(100),
    body VARCHAR(100),
    post VARCHAR(100) FOREIGN KEY(posts) REFERENCES posts,
    PRIMARY KEY(id)
)

INSERT INTO roles(name) VALUES('ADMIN');
INSERT INTO roles(name) VALUES('ROLE_ADMIN');
INSERT INTO roles(name) VALUES('USER');

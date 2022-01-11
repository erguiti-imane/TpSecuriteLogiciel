DROP TABLE IF EXISTS authorities;
DROP TABLE IF EXISTS users;
CREATE TABLE users
(
    username VARCHAR(150) not null,
    password VARCHAR(150) not null,
    enabled  TINYINT      NOT NULL DEFAULT 1,
    PRIMARY KEY (username)
);

CREATE TABLE authorities
(
    username  VARCHAR(50) not null,
    authority VARCHAR(50) not null,
    FOREIGN KEY (username) REFERENCES users (username)
);
CREATE
UNIQUE INDEX ix_auth_username on authorities (username,authority);
INSERT INTO users (username, password, enabled)
VALUES ('User1', '$2a$04$aaxjt5fibvGTQSC/yGj0t.uTBJdu54KkjdEWMiJlTRsvRievp6D2m', true);
INSERT INTO users (username, password, enabled)
VALUES ('Admin', '$2a$04$TYqCTJWOJAssd/smmYfPT.829bHgHRv5R2b1o1gd7AtHcqaiYj4ym', true);
INSERT INTO authorities (username, authority)
VALUES ('Admin', 'ROLE_ADMIN');
INSERT INTO authorities (username, authority)
VALUES ('User1', 'ROLE_USER')
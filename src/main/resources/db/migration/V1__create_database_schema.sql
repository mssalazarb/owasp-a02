CREATE TABLE users
(
    id            SERIAL PRIMARY KEY,
    username      VARCHAR(20) NOT NULL,
    pawssword     VARCHAR(20) NOT NULL
);
CREATE TABLE users (
    ID bigint SERIAL PRIMARY KEY,
    EMAIL varchar(100) NOT NULL,
    FIRSTNAME varchar(50),
    LASTNAME varchar(50)
);

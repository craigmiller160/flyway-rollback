CREATE EXTENSION pgcrypto;

CREATE TABLE people (
    id UUID NOT NULL,
    name VARCHAR(255),
    PRIMARY KEY (id)
);
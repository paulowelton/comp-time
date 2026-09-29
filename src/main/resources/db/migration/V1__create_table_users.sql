CREATE TABLE users (
    id SERIAL PRIMARY KEY NOT NULL ,
    name TEXT NOT NULL,
    email TEXT NOT NULL,
    password TEXT NOT NULL,
    role TEXT NOT NULL
);
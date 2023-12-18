DROP TABLE IF EXISTS users CASCADE;
DROP TABLE IF EXISTS roles CASCADE;
DROP TABLE IF EXISTS addresses CASCADE;
DROP TABLE IF EXISTS recipes CASCADE;
DROP TABLE IF EXISTS ingridients CASCADE;
DROP TABLE IF EXISTS recipes_ingridients CASCADE;

CREATE TABLE IF NOT EXISTS addresses(
    address_id INTEGER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    city VARCHAR(30) NOT NULL,
    street VARCHAR(100) NOT NULL,
    house INTEGER
);

CREATE TABLE roles(
    role_id INTEGER GENERATED BY DEFAULT AS IDENTITY  PRIMARY KEY,
    role_name VARCHAR(10) NOT NULL
);

CREATE TABLE IF NOT EXISTS users(
    user_id INTEGER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    role INTEGER REFERENCES roles(role_id) ON DELETE SET NULL,
    username VARCHAR(30) UNIQUE NOT NULL,
    firstname VARCHAR(30),
    lastname VARCHAR(30),
    email VARCHAR(30) UNIQUE NOT NULL,
    address_id INTEGER REFERENCES addresses(address_id) ON DELETE SET NULL,
    password VARCHAR(255) NOT NULL,
    description VARCHAR(255),
    rating REAL,
    registration_date DATE NOT NULL,
    status INTEGER NOT NULL,
    photo VARCHAR(30)
);

CREATE TABLE IF NOT EXISTS recipes(
    recipe_id INTEGER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    title VARCHAR(100) NOT NULL,
    description VARCHAR NOT NULL,
    cooking_time TIME,
    portions INTEGER NOT NULL DEFAULT 1,
    rating REAL,
    author INTEGER REFERENCES users(user_id) ON DELETE SET NULL,
    post_date DATE NOT NULL,
    photo VARCHAR(100) NOT NULL,
    status INTEGER NOT NULL
);

CREATE TABLE IF NOT EXISTS ingridients(
    ingridient_id INTEGER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    title VARCHAR(50) UNIQUE NOT NULL,
    price REAL,
    photo VARCHAR(50) NOT NULL
);

CREATE TABLE IF NOT EXISTS recipes_ingridients(
    recipe_id INTEGER REFERENCES recipes(recipe_id) NOT NULL,
    ingridient_id INTEGER REFERENCES ingridients(ingridient_id) NOT NULL
);
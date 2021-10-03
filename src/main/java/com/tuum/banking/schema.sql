DROP TABLE IF EXISTS customers ;
CREATE TABLE customers (
                           id SERIAL PRIMARY KEY,
                           name VARCHAR ( 50 ) NOT NULL,
                           surname VARCHAR ( 50 ) NOT NULL,
                           email VARCHAR ( 255 ) UNIQUE NOT NULL,
                           phone NUMERIC NOT NULL,
                           created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL
);

INSERT INTO customers(name, surname, email, phone) VALUES ('baran', 'ozoglu', 'abaranozoglu@gmail.com', 5459475193);
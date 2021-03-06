DROP TABLE IF EXISTS balances ;
DROP TABLE IF EXISTS transactions;
DROP TABLE IF EXISTS accounts;
DROP TABLE IF EXISTS customers;

CREATE TABLE customers (
                           id SERIAL PRIMARY KEY,
                           name VARCHAR ( 50 ) NOT NULL,
                           surname VARCHAR ( 50 ) NOT NULL,
                           email VARCHAR ( 255 ) UNIQUE NOT NULL,
                           phone NUMERIC NOT NULL,
                           updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
                           created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL
);

CREATE TABLE accounts (
                          id SERIAL PRIMARY KEY,
                          customer_id int NOT NULL REFERENCES customers,
                          country VARCHAR ( 50 ) NOT NULL,
                          updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
                          created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL
);

CREATE TABLE balances (
                          id SERIAL PRIMARY KEY,
                          account_id int NOT NULL REFERENCES accounts,
                          available_amount NUMERIC (50, 2) NOT NULL,
                          currency VARCHAR ( 5 ) NOT NULL,
                          updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
                          created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL
);

CREATE TABLE transactions (
                          id SERIAL PRIMARY KEY,
                          account_id int NOT NULL REFERENCES accounts,
                          amount NUMERIC (50, 2) NOT NULL,
                          direction VARCHAR (5) NOT NULL,
                          description VARCHAR ( 250 ) NOT NULL,
                          currency VARCHAR ( 5 ) NOT NULL,
                          balance_after_transaction NUMERIC (50, 2) NOT NULL,
                          updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
                          created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL
);

INSERT INTO customers(name, surname, email, phone) VALUES ('baran', 'ozoglu', 'abaranozoglu@gmail.com', 5459475193);
INSERT INTO accounts(customer_id, country) VALUES (1, 'ankara');
INSERT INTO balances(account_id, available_amount, currency) VALUES (1, 56.991, 'EUR');
INSERT INTO balances(account_id, available_amount, currency) VALUES (1, 70.821, 'GBP');

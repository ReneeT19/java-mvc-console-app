create database dollarsbank;
use dollarsbank;

CREATE TABLE account (
	account_id INT AUTO_INCREMENT PRIMARY KEY,
	deposit INT NOT NULL,
    withdral INT NOT NULL,
    funds_transfer INT NOT NULL,
    balance INT NOT NULL
);

CREATE TABLE customer (
	user_id INT AUTO_INCREMENT PRIMARY KEY,
    customer_name VARCHAR(50) NOT NULL,
    customer_address VARCHAR(50) NOT NULL,
    customer_number INT NOT NULL,
    account_password varchar(50) NOT NULL,
    acco_id INT NOT NULL,
    FOREIGN KEY (acco_id) REFERENCES account (account_id)
);


-- CREATE TABLE savingsaccount (
-- 	department_id INT AUTO_INCREMENT PRIMARY KEY,
--     department_name VARCHAR(50) NOT NULL
-- );

INSERT INTO account VALUES (1, 5000, 200, 50, 4750); 
INSERT INTO account VALUES (2, 3000, 100, 200, 2700); 

-- INSERT INTO savingsaccount VALUES (1001, 'IT');
-- INSERT INTO department VALUES (1002, 'Engineering');

INSERT INTO customer VALUES (1001, 'Renee Thomsen', 'US', 1234567890, 111, 2);
INSERT INTO customer VALUES (1002, 'Erik Thomsen', 'US', 0987654321, 222, 1);

desc account;
select * from account;
desc customer;
select * from customer;

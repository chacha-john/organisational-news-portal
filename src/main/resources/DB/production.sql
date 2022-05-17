 CREATE DATABASE rinews;

\c rinews;

 CREATE TABLE IF NOT EXISTS users (id SERIAL PRIMARY KEY, name VARCHAR, phone VARCHAR, address VARCHAR, email VARCHAR, position VARCHAR, role VARCHAR, departmentId int);

CREATE TABLE IF NOT EXISTS departments(id SERIAL PRIMARY KEY, name VARCHAR, description VARCHAR);

CREATE TABLE IF NOT EXISTS news(id SERIAL PRIMARY KEY, content VARCHAR, employeeid int, datecreated TIMESTAMP DEFAULT CURRENT_TIMESTAMP, published varchar, departmentid VARCHAR);

CREATE DATABASE rinews_test WITH TEMPLATE rinews;
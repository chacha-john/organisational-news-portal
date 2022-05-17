SET MODE postgreSQL;

CREATE TABLE IF NOT EXISTS users (
id int PRIMARY KEY auto_increment,
name VARCHAR,
phone VARCHAR,
address VARCHAR,
email VARCHAR,
position VARCHAR,
role VARCHAR,
departmentId int
);

CREATE TABLE IF NOT EXISTS departments(
id int PRIMARY KEY AUTO_INCREMENT,
name VARCHAR,
description VARCHAR
);

CREATE TABLE IF NOT EXISTS news(
id int PRIMARY KEY auto_increment,
content VARCHAR,
employeeid int,
datecreated Date,
published varchar,
departmentid VARCHAR
);
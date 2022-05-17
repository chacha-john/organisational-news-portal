# news

## By Chacha John 

# Table of Content

+ [Description](#description)
+ [Screenshots](#screenshots)
+ [Technology Used](#technology-used)
+ [Set up Instructions](#setup-instructions)
+ [Licence](#licence)
+ [Authors Info](#authors-Info)

# Description
<p>An application where users can create quotes and have those quotes voted on whether they are terrible or are inspirational.</p>

[Go Back to the top](#news)

<!-- # Screenshots
![Landing page screenshot](/src/main/resources/public/images/luku.png "Landing page")
![Add sighting page screenshot](/src/main/resources/public/images/luku2.png "Add sighting page")
 -->
# Technology Used
* Java Spark - backend logic and routing

* Postgresql - Data store for application data

[How to set up](#setup-instructions)
#### In PSQL:
* CREATE DATABASE rinews; 

* CREATE TABLE IF NOT EXISTS users (id SERIAL PRIMARY KEY, name VARCHAR, phone VARCHAR, address VARCHAR, email VARCHAR, position VARCHAR, role VARCHAR, departmentId int);

* CREATE TABLE IF NOT EXISTS departments(id SERIAL PRIMARY KEY, name VARCHAR, description VARCHAR);

* CREATE TABLE IF NOT EXISTS news(id SERIAL PRIMARY KEY, content VARCHAR, employeeid int, datecreated TIMESTAMP DEFAULT CURRENT_TIMESTAMP, published varchar, departmentid VARCHAR);


[Go Back to the top](#news)

# Licence

[Licence](LICENSE)

[Go Back to the top](#news)

# Authors Info

Linkedin - [Chacha John](https://www.linkedin.com/in/chachaup/)

Twitter - [Chacha](https://www.twitter.com/_chachaup)

Email - [Chacha John](mailto:chachaerickjo@gmail.com)

[Go Back to the top](#news)

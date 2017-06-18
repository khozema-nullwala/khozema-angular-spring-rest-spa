CREATE DATABASE kzncollaboration;

USE DATABASE kzncollaboration;

CREATE TABLE user_detail (
	id INT AUTO_INCREMENT,
	first_name VARCHAR(50),
	last_name VARCHAR(50),
	username VARCHAR(50) UNIQUE,
	password VARCHAR(50),
	email VARCHAR(50) UNIQUE,
	contact_number VARCHAR(10),
	CONSTRAINT pk_user_detail_id PRIMARY KEY (id)	
);

INSERT INTO user_detail (first_name, last_name, username, password, email, contact_number)
	VALUES ('KHOZEMA', 'NULLWALA', 'kozi2017', 'Niit@2017', 'kozi4987@gmail.com', '9819000000');
	
	COMMIT;
-- Insert into user table
use hello;

-- Create user table
CREATE TABLE user (
                      id INT PRIMARY KEY,
                      username VARCHAR(255) NOT NULL,
                      email VARCHAR(255) NOT NULL
);

-- Create category table
CREATE TABLE category (
                          id INT PRIMARY KEY,
                          name VARCHAR(255) NOT NULL
);

-- Create expense table
CREATE TABLE expense (
                         id INT PRIMARY KEY,
                         description VARCHAR(255) NOT NULL,
                         date_time DATETIME NOT NULL,
                         location VARCHAR(255) NOT NULL,
                         user_id INT,
                         category_id INT,
                         FOREIGN KEY (user_id) REFERENCES user(id),
                         FOREIGN KEY (category_id) REFERENCES category(id)
);


insert into user values (1, 'Siamak', 'Codeengine11@gmail.com');
insert into user values (2, 'John', 'John@john.com');
insert into user values (3, 'Adam', 'adam@adam.com');

-- Insert into category table
insert into category values (1, 'Travel');
insert into category values (2, 'Auto Loan');
insert into category values (3, 'Travel'); -- Note: This statement seems to be a duplicate; adjust if needed

-- Insert into expense table
insert into expense values (100, 'New York Business Trip', '2019-06-16 17:00:00', 'New York', 1, 1);
insert into expense values (101, 'Ford Mustang Payment', '2019-06-15 15:00:00', 'Los Angeles', 2, 2);
insert into expense values (102, 'Grand Canyon Trip With Family', '2019-06-15 15:00:00', 'Arizona', 3, 1);

-- Drop table if it exists
DROP TABLE IF EXISTS tb_person;

-- Create table
CREATE TABLE tb_person (
    id INT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(80) NOT NULL,
    last_name VARCHAR(80) NOT NULL,
    address VARCHAR(100) NOT NULL,
    gender VARCHAR(6) NOT NULL
);
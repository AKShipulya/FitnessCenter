CREATE DATABASE fitness_center;

USE fitness_center;

CREATE TABLE IF NOT EXISTS customer
(
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    sure_name VARCHAR(80) NOT NULL,
    middle_name VARCHAR(80) NOT NULL,
    customer_type ENUM('DEFAULT', 'REGULAR', 'CORPORATE') NOT NULL,
    discount TINYINT,
    program VARCHAR(80)
);

CREATE TABLE IF NOT EXISTS order_program
(
    order_id BIGINT PRIMARY KEY AUTO_INCREMENT,
    customer_id BIGINT NOT NULL,
    program VARCHAR(80) NOT NULL,
    price DECIMAL(5,2) NOT NULL,
    FOREIGN KEY (customer_id) REFERENCES customer (id)
);

CREATE TABLE IF NOT EXISTS coach
(
    coach_id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(80) NOT NULL,
    sure_name VARCHAR(80) NOT NULL,
    qualification ENUM('EXPERT','ADVANCED','BASE') NOT NULL,
    gender ENUM('MALE','FEMALE') NOT NULL
);

CREATE TABLE IF NOT EXISTS program
(
    program_id BIGINT PRIMARY KEY AUTO_INCREMENT,
    coach_id BIGINT,
    customer_id BIGINT,
    name VARCHAR(80) NOT NULL,
    description VARCHAR(1000),
    gender ENUM('MALE','FEMALE') NOT NULL,
    has_meal_plan BOOLEAN,
    program_type ENUM('EXPERT','AMATEUR','BEGINNER','START'),
    FOREIGN KEY (coach_id) REFERENCES coach (coach_id),
    FOREIGN KEY (customer_id) REFERENCES customer (id)
);

CREATE TABLE IF NOT EXISTS feedback
(
    feedback_id BIGINT PRIMARY KEY AUTO_INCREMENT,
    customer_id BIGINT NOT NULL,
    program_id BIGINT NOT NULL,
    coach_id BIGINT NOT NULL,
    text MEDIUMTEXT NOT NULL,
    grade TINYINT NOT NULL,
    FOREIGN KEY (customer_id) REFERENCES customer (id),
    FOREIGN KEY (coach_id) REFERENCES coach (coach_id),
    FOREIGN KEY (program_id) REFERENCES program (program_id)
    );


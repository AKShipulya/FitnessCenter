DROP DATABASE IF EXISTS fitness_center;
CREATE DATABASE fitness_center;
USE fitness_center;

--
-- Table structure for table user
--

DROP TABLE IF EXISTS user;

CREATE TABLE user
(
    id       int                                          NOT NULL,
    login    varchar(45)                                  NOT NULL,
    email    varchar(45)                                  NOT NULL,
    password varchar(45)                                  NOT NULL,
    name     varchar(50)                                  NOT NULL,
    surname  varchar(80)                                  NOT NULL,
    role     enum ('GUEST','USER','ADMIN', 'VIP')         NOT NULL,
    status   enum ('ACTIVATED','BLOCKED','NOT_CONFIRMED') NOT NULL,
    payment  decimal DEFAULT NULL,
    PRIMARY KEY (id)
);

--
-- Table structure for table purpose
--

DROP TABLE IF EXISTS purpose;

CREATE TABLE purpose
(
    id         int        NOT NULL AUTO_INCREMENT,
    name varchar(45) DEFAULT NULL,
    price decimal NOT NULL,
    PRIMARY KEY (id)
);

--
-- Table structure for table program
--

DROP TABLE IF EXISTS program;

CREATE TABLE program
(
    id            int      NOT NULL AUTO_INCREMENT,
    program_order      varchar(45)     DEFAULT NULL,
    start_date     DATE     NOT NULL,
    end_date      date DEFAULT NULL,
    name          varchar(80) NOT NULL,
    status enum('IN_PROGRESS', 'DONE', 'CANCELED') NOT NULL,
    summary_price decimal NOT NULL,
    user_id int NOT NULL,
    purpose_id int NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (user_id) REFERENCES user (id),
    FOREIGN KEY (purpose_id) REFERENCES purpose (id)
);

--
-- Table structure for table feedback
--
DROP TABLE IF EXISTS feedback;

CREATE TABLE feedback
(
    id         int                                      NOT NULL AUTO_INCREMENT,
    user_id    int                                      NOT NULL,
    program_id int DEFAULT NULL,
    score      double                                   NOT NULL,
    type       enum ('WORK_QUALITY', 'PROGRAM_QUALITY') NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (program_id) REFERENCES program (id),
    FOREIGN KEY (user_id) REFERENCES user (id)
);
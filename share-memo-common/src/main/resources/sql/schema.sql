DROP TABLE IF EXISTS member;

CREATE TABLE member
(
    id               int(6) AUTO_INCREMENT PRIMARY KEY,
    name             varchar(30) NOT NULL,
    email            varchar(50),
    create_timestamp varchar(30),
    create_user      varchar(20),
    update_timestamp varchar(30),
    update_user      varchar(20)
);
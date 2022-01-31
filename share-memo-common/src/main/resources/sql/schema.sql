USE `share-memo`;

DROP TABLE IF EXISTS member_notification;
DROP TABLE IF EXISTS member;
DROP TABLE IF EXISTS notification;
DROP TABLE IF EXISTS quartz_notification;

CREATE TABLE member
(
    id               int(6) AUTO_INCREMENT PRIMARY KEY,
    name             varchar(30) NOT NULL,
    line_id          varchar(50),
    email            varchar(50),
    create_timestamp varchar(30),
    create_user      varchar(20),
    update_timestamp varchar(30),
    update_user      varchar(20)
);

CREATE TABLE notification
(
    id                int(6) AUTO_INCREMENT PRIMARY KEY,
    subject           varchar(100)  NOT NULL,
    content           varchar(4000) NOT NULL,
    notification_date varchar(10)   NOT NULL,
    create_timestamp  varchar(30),
    create_user       varchar(20),
    update_timestamp  varchar(30),
    update_user       varchar(20)
);

CREATE TABLE member_notification
(
    member_id        int(6) NOT NULL,
    notification_id  int(6) NOT NULL,
    isSend           varchar(1) NOT NULL,
    create_timestamp varchar(30),
    create_user      varchar(20),
    update_timestamp varchar(30),
    update_user      varchar(20),
    PRIMARY KEY (member_id, notification_id),
    FOREIGN KEY (member_id) REFERENCES member (id),
    FOREIGN KEY (notification_id) REFERENCES notification (id)
);

CREATE TABLE quartz_notification
(
    id               int(6) AUTO_INCREMENT PRIMARY KEY,
    job_name         varchar(50)   NOT NULL,
    job_group        varchar(50)   NOT NULL,
    subject          varchar(100)  NOT NULL,
    content          varchar(4000) NOT NULL,
    cron             varchar(50)   NOT NULL,
    create_timestamp varchar(30),
    create_user      varchar(20),
    update_timestamp varchar(30),
    update_user      varchar(20)
);
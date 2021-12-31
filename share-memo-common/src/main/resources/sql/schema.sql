DROP TABLE IF EXISTS member_notification;
DROP TABLE IF EXISTS member;
DROP TABLE IF EXISTS notification;

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

CREATE TABLE notification
(
    id               int(6) AUTO_INCREMENT PRIMARY KEY,
    subject          varchar(100)  NOT NULL,
    content          varchar(4000) NOT NULL,
    create_timestamp varchar(30),
    create_user      varchar(20),
    update_timestamp varchar(30),
    update_user      varchar(20)
);

CREATE TABLE member_notification
(
    member_id        int(6)        NOT NULL,
    notification_id  int(6)        NOT NULL,
    subject          varchar(100)  NOT NULL,
    content          varchar(4000) NOT NULL,
    isSend           varchar(1)       NOT NULL,
    create_timestamp varchar(30),
    create_user      varchar(20),
    update_timestamp varchar(30),
    update_user      varchar(20),
    PRIMARY KEY (member_id, notification_id),
    FOREIGN KEY (member_id) REFERENCES member (id),
    FOREIGN KEY (notification_id) REFERENCES notification (id)
);
CREATE TABLE system_config
(
    id           BIGINT      NOT NULL AUTO_INCREMENT,
    config_key   VARCHAR(32) NOT NULL,
    config_value VARCHAR(64) NOT NULL,
    version      INT         NOT NULL DEFAULT 0,
    is_deleted   TINYINT     NOT NULL DEFAULT 0,
    create_id    BIGINT      NOT NULL DEFAULT 0,
    create_time  TIMESTAMP   NOT NULL DEFAULT CURRENT_TIMESTAMP,
    update_id    BIGINT      NOT NULL DEFAULT 0,
    update_time  TIMESTAMP   NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (id)
);


INSERT INTO system_config (config_key, config_value)
VALUES ('system_name', '内容管理系统'),
       ('system_desc', '内容管理系统（Content Management System）'),
       ('system_copyright', '© 2020 东京易冷');


CREATE TABLE `user`
(
    id          BIGINT       NOT NULL AUTO_INCREMENT,
    username    VARCHAR(32)  NOT NULL,
    password    VARCHAR(512) NOT NULL,
    true_name   VARCHAR(32)  NOT NULL DEFAULT '',
    email       VARCHAR(64)  NOT NULL DEFAULT '',
    phone       VARCHAR(32)  NOT NULL DEFAULT '',
    enabled     TINYINT      NOT NULL DEFAULT 1,
    version     INT          NOT NULL DEFAULT 0,
    is_deleted  TINYINT      NOT NULL DEFAULT 0,
    create_id   BIGINT       NOT NULL DEFAULT 0,
    create_time TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    update_id   BIGINT       NOT NULL DEFAULT 0,
    update_time TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (id)
);

INSERT INTO `user` (id, username, password)
VALUES (1, 'admin',
        'ba3253876aed6bc22d4a6ff53d8406c6ad864195ed144ab5c87621b6c233b548baeae6956df346ec8c17f5ea10f35ee3cbc514797ed7ddd3145464e2a0bab413');


CREATE TABLE `system_menu`
(
    id          BIGINT      NOT NULL AUTO_INCREMENT,
    name        VARCHAR(32) NOT NULL,
    url         VARCHAR(64) NOT NULL,
    icon        VARCHAR(64) NOT NULL,
    parent_id   BIGINT      NOT NULL DEFAULT 0,
    version     INT         NOT NULL DEFAULT 0,
    is_deleted  TINYINT     NOT NULL DEFAULT 0,
    create_id   BIGINT      NOT NULL DEFAULT 0,
    create_time TIMESTAMP   NOT NULL DEFAULT CURRENT_TIMESTAMP,
    update_id   BIGINT      NOT NULL DEFAULT 0,
    update_time TIMESTAMP   NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (id)
);

INSERT INTO `system_menu` (id, name, url, icon, parent_id)
VALUES (1, '系统管理', '', 'layui-icon layui-icon-set', 0);

INSERT INTO `system_menu` (name, url, icon, parent_id)
VALUES ('用户管理', '/user/list', '', 1);
CREATE TABLE system_config
(
    id           BIGINT UNSIGNED  NOT NULL AUTO_INCREMENT,
    config_key   VARCHAR(32)      NOT NULL,
    config_value VARCHAR(64)      NOT NULL,
    version      INT UNSIGNED     NOT NULL DEFAULT 0,
    is_deleted   TINYINT UNSIGNED NOT NULL DEFAULT 0,
    create_id    BIGINT UNSIGNED  NOT NULL DEFAULT 0,
    create_time  TIMESTAMP        NOT NULL DEFAULT CURRENT_TIMESTAMP,
    update_id    BIGINT UNSIGNED  NOT NULL DEFAULT 0,
    update_time  TIMESTAMP        NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (id),
    INDEX (config_key)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;


INSERT INTO system_config (config_key, config_value)
VALUES ('system_name', '内容管理系统'),
       ('system_desc', '内容管理系统（Content Management System）'),
       ('system_copyright', '© 2020 东京易冷');


CREATE TABLE `user`
(
    id          BIGINT UNSIGNED  NOT NULL AUTO_INCREMENT,
    username    VARCHAR(32)      NOT NULL COMMENT '用户名',
    password    VARCHAR(512)     NOT NULL COMMENT '密码',
    true_name   VARCHAR(32)      NOT NULL DEFAULT '' COMMENT '真实姓名',
    email       VARCHAR(64)      NOT NULL DEFAULT '' COMMENT '邮箱',
    phone       VARCHAR(32)      NOT NULL DEFAULT '' COMMENT '手机号',
    enabled     TINYINT UNSIGNED NOT NULL DEFAULT 1 COMMENT '是否启用',
    version     INT UNSIGNED     NOT NULL DEFAULT 0,
    is_deleted  TINYINT UNSIGNED NOT NULL DEFAULT 0,
    create_id   BIGINT UNSIGNED  NOT NULL DEFAULT 0,
    create_time TIMESTAMP        NOT NULL DEFAULT CURRENT_TIMESTAMP,
    update_id   BIGINT UNSIGNED  NOT NULL DEFAULT 0,
    update_time TIMESTAMP        NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (id),
    UNIQUE INDEX (username)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;

INSERT INTO `user` (id, username, password)
VALUES (1, 'admin',
        'ba3253876aed6bc22d4a6ff53d8406c6ad864195ed144ab5c87621b6c233b548baeae6956df346ec8c17f5ea10f35ee3cbc514797ed7ddd3145464e2a0bab413');


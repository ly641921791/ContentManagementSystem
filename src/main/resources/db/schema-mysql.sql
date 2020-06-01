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


CREATE TABLE `system_user`
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

INSERT INTO `system_user` (id, username, password)
VALUES (1, 'admin',
        'ba3253876aed6bc22d4a6ff53d8406c6ad864195ed144ab5c87621b6c233b548baeae6956df346ec8c17f5ea10f35ee3cbc514797ed7ddd3145464e2a0bab413');


CREATE TABLE `system_role`
(
    id          BIGINT UNSIGNED  NOT NULL AUTO_INCREMENT,
    name        VARCHAR(32)      NOT NULL,
    version     INT UNSIGNED     NOT NULL DEFAULT 0,
    is_deleted  TINYINT UNSIGNED NOT NULL DEFAULT 0,
    create_id   BIGINT UNSIGNED  NOT NULL DEFAULT 0,
    create_time TIMESTAMP        NOT NULL DEFAULT CURRENT_TIMESTAMP,
    update_id   BIGINT UNSIGNED  NOT NULL DEFAULT 0,
    update_time TIMESTAMP        NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;


INSERT INTO `system_role` (id, name)
VALUES (1, '超级管理员');


CREATE TABLE `system_user_role`
(
    id          BIGINT UNSIGNED  NOT NULL AUTO_INCREMENT,
    user_id     BIGINT UNSIGNED  NOT NULL,
    role_id     BIGINT UNSIGNED  NOT NULL,
    version     INT UNSIGNED     NOT NULL DEFAULT 0,
    is_deleted  TINYINT UNSIGNED NOT NULL DEFAULT 0,
    create_id   BIGINT UNSIGNED  NOT NULL DEFAULT 0,
    create_time TIMESTAMP        NOT NULL DEFAULT CURRENT_TIMESTAMP,
    update_id   BIGINT UNSIGNED  NOT NULL DEFAULT 0,
    update_time TIMESTAMP        NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (id),
    KEY (user_id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;


INSERT INTO `system_user_role` (user_id, role_id)
VALUES (1, 1);


CREATE TABLE `system_permission`
(
    id          BIGINT UNSIGNED  NOT NULL AUTO_INCREMENT,
    type        TINYINT UNSIGNED NOT NULL COMMENT '权限类型：1 页面 2 接口',
    name        VARCHAR(32)      NOT NULL,
    url         VARCHAR(64)      NOT NULL,
    icon        VARCHAR(64)      NOT NULL,
    parent_id   BIGINT UNSIGNED  NOT NULL DEFAULT 0,
    version     INT UNSIGNED     NOT NULL DEFAULT 0,
    is_deleted  TINYINT UNSIGNED NOT NULL DEFAULT 0,
    create_id   BIGINT UNSIGNED  NOT NULL DEFAULT 0,
    create_time TIMESTAMP        NOT NULL DEFAULT CURRENT_TIMESTAMP,
    update_id   BIGINT UNSIGNED  NOT NULL DEFAULT 0,
    update_time TIMESTAMP        NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;

INSERT INTO `system_permission` (id, type, name, url, icon, parent_id)
VALUES (1, 1, '系统管理', '', 'layui-icon layui-icon-set', 0),
       (2, 1, '图书管理', '', 'layui-icon layui-icon-read', 0),
       (3, 1, '资料管理', '', 'layui-icon layui-icon-file', 0);

INSERT INTO `system_permission` (type, name, url, icon, parent_id)
VALUES (1, '用户列表', '/user/list', '', 1),
       (1, '角色列表', '/system/role', '', 1),
       (1, '图书列表', '/book', '', 2),
       (1, '图书类型', '/book/type', '', 2),
       (1, '借阅管理', '/book/lend', '', 2),
       (1, '资料列表', '/documentation', '', 3);

CREATE TABLE `system_role_permission`
(
    id            BIGINT UNSIGNED  NOT NULL AUTO_INCREMENT,
    role_id       BIGINT UNSIGNED  NOT NULL,
    permission_id BIGINT UNSIGNED  NOT NULL,
    version       INT UNSIGNED     NOT NULL DEFAULT 0,
    is_deleted    TINYINT UNSIGNED NOT NULL DEFAULT 0,
    create_id     BIGINT UNSIGNED  NOT NULL DEFAULT 0,
    create_time   TIMESTAMP        NOT NULL DEFAULT CURRENT_TIMESTAMP,
    update_id     BIGINT UNSIGNED  NOT NULL DEFAULT 0,
    update_time   TIMESTAMP        NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (id),
    KEY (role_id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;

INSERT INTO `system_role_permission` (role_id, permission_id)
VALUES (1, 1);

CREATE TABLE book_info
(
    id           BIGINT UNSIGNED  NOT NULL AUTO_INCREMENT,
    isbn         VARCHAR(32)      NOT NULL DEFAULT '',
    name         VARCHAR(128)     NOT NULL,
    author       VARCHAR(64)      NOT NULL DEFAULT '',
    publish      BIGINT UNSIGNED  NOT NULL DEFAULT 0,
    type         BIGINT UNSIGNED  NOT NULL DEFAULT 0,
    introduction TEXT             NOT NULL DEFAULT '',
    shelf        VARCHAR(32)      NOT NULL DEFAULT '',
    total        INT UNSIGNED     NOT NULL DEFAULT 0,
    remaining    INT UNSIGNED     NOT NULL DEFAULT 0,
    version      INT UNSIGNED     NOT NULL DEFAULT 0,
    is_deleted   TINYINT UNSIGNED NOT NULL DEFAULT 0,
    create_id    BIGINT UNSIGNED  NOT NULL DEFAULT 0,
    create_time  TIMESTAMP        NOT NULL DEFAULT CURRENT_TIMESTAMP,
    update_id    BIGINT UNSIGNED  NOT NULL DEFAULT 0,
    update_time  TIMESTAMP        NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;


INSERT INTO book_info (isbn, name, author, publish, type, introduction, shelf, total, remaining)
VALUES ('', '资治通鉴', '司马光', 0, 0, '《资治通鉴》的内容以政治、军事和民族关系为主，兼及经济、文化和历史人物评价，目的是通过对事关国家盛衰、民族兴亡的统治阶级政策的描述警示后人。', '一号书架', 1,
        1);


CREATE TABLE book_type
(
    id          BIGINT UNSIGNED  NOT NULL AUTO_INCREMENT,
    name        VARCHAR(32)      NOT NULL,
    node        VARCHAR(32)      NOT NULL DEFAULT '',
    parent_id   BIGINT UNSIGNED  NOT NULL DEFAULT 0,
    version     INT UNSIGNED     NOT NULL DEFAULT 0,
    is_deleted  TINYINT UNSIGNED NOT NULL DEFAULT 0,
    create_id   BIGINT UNSIGNED  NOT NULL DEFAULT 0,
    create_time TIMESTAMP        NOT NULL DEFAULT CURRENT_TIMESTAMP,
    update_id   BIGINT UNSIGNED  NOT NULL DEFAULT 0,
    update_time TIMESTAMP        NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;


INSERT INTO book_type (id, name, parent_id)
VALUES (1, '所有分类', 0),
       (2, '电脑相关', 1);


CREATE TABLE book_lend
(
    id          BIGINT UNSIGNED  NOT NULL AUTO_INCREMENT,
    user_id     BIGINT UNSIGNED  NOT NULL,
    book_id     BIGINT UNSIGNED  NOT NULL,
    lend_time   DATETIME         NOT NULL DEFAULT CURRENT_TIMESTAMP,
    return_time DATETIME                  DEFAULT NULL,
    state       TINYINT UNSIGNED NOT NULL DEFAULT 0,
    version     INT UNSIGNED     NOT NULL DEFAULT 0,
    is_deleted  TINYINT UNSIGNED NOT NULL DEFAULT 0,
    create_id   BIGINT UNSIGNED  NOT NULL DEFAULT 0,
    create_time TIMESTAMP        NOT NULL DEFAULT CURRENT_TIMESTAMP,
    update_id   BIGINT UNSIGNED  NOT NULL DEFAULT 0,
    update_time TIMESTAMP        NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;


INSERT INTO book_lend (user_id, book_id, lend_time, return_time, state)
VALUES (1, 1, '2019-03-05 01:53:56', '2020-03-05 01:53:56', 0),
       (1, 1, '2019-03-05 01:53:56', NULL, 1),
       (1, 1, '2019-03-05 01:53:56', NULL, 2);


CREATE TABLE documentation
(
    id           BIGINT UNSIGNED  NOT NULL AUTO_INCREMENT,
    isbn         VARCHAR(32)      NOT NULL DEFAULT '',
    name         VARCHAR(128)     NOT NULL,
    author       VARCHAR(64)      NOT NULL DEFAULT '',
    publish      BIGINT UNSIGNED  NOT NULL DEFAULT 0,
    type         BIGINT UNSIGNED  NOT NULL DEFAULT 0,
    introduction TEXT             NOT NULL DEFAULT '',
    shelf        VARCHAR(32)      NOT NULL DEFAULT '',
    total        INT UNSIGNED     NOT NULL DEFAULT 0,
    remaining    INT UNSIGNED     NOT NULL DEFAULT 0,
    version      INT UNSIGNED     NOT NULL DEFAULT 0,
    is_deleted   TINYINT UNSIGNED NOT NULL DEFAULT 0,
    create_id    BIGINT UNSIGNED  NOT NULL DEFAULT 0,
    create_time  TIMESTAMP        NOT NULL DEFAULT CURRENT_TIMESTAMP,
    update_id    BIGINT UNSIGNED  NOT NULL DEFAULT 0,
    update_time  TIMESTAMP        NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;

INSERT INTO documentation (isbn, name, author, publish, type, introduction, shelf, total, remaining)
VALUES ('', '史记', '司马迁', 0, 0,
        '《史记》，二十四史之一，最初称为《太史公书》或《太史公记》、《太史记》，是西汉史学家司马迁撰写的纪传体史书，是中国历史上第一部纪传体通史，记载了上至上古传说中的黄帝时代，下至汉武帝太初四年间共3000多年的历史。',
        '一号书架', 1,
        1);



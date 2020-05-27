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
VALUES (1, '系统管理', '', 'layui-icon layui-icon-set', 0),
       (2, '图书管理', '', 'layui-icon layui-icon-read', 0),
       (3, '资料管理', '', 'layui-icon layui-icon-file', 0);

INSERT INTO `system_menu` (name, url, icon, parent_id)
VALUES ('用户列表', '/user/list', '', 1),
       ('图书列表', '/book', '', 2),
       ('图书类型', '/book/type', '', 2),
       ('资料列表', '/documentation', '', 3);


CREATE TABLE book_info
(
    id           BIGINT       NOT NULL AUTO_INCREMENT,
    isbn         VARCHAR(32)  NOT NULL DEFAULT '',
    name         VARCHAR(128) NOT NULL,
    author       VARCHAR(64)  NOT NULL DEFAULT '',
    publish      BIGINT       NOT NULL DEFAULT 0,
    type         BIGINT       NOT NULL DEFAULT 0,
    introduction TEXT         NOT NULL DEFAULT '',
    shelf        VARCHAR(32)  NOT NULL DEFAULT '',
    total        INT          NOT NULL DEFAULT 0,
    remaining    INT          NOT NULL DEFAULT 0,
    version      INT          NOT NULL DEFAULT 0,
    is_deleted   TINYINT      NOT NULL DEFAULT 0,
    create_id    BIGINT       NOT NULL DEFAULT 0,
    create_time  TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    update_id    BIGINT       NOT NULL DEFAULT 0,
    update_time  TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (id)
);


INSERT INTO book_info (isbn, name, author, publish, type, introduction, shelf, total, remaining)
VALUES ('', '资治通鉴', '司马光', 0, 0, '《资治通鉴》的内容以政治、军事和民族关系为主，兼及经济、文化和历史人物评价，目的是通过对事关国家盛衰、民族兴亡的统治阶级政策的描述警示后人。', '一号书架', 1,
        1);


CREATE TABLE book_type
(
    id          BIGINT      NOT NULL AUTO_INCREMENT,
    name        VARCHAR(32) NOT NULL,
    node        VARCHAR(32) NOT NULL DEFAULT '',
    parent_id   BIGINT      NOT NULL DEFAULT 0,
    version     INT         NOT NULL DEFAULT 0,
    is_deleted  TINYINT     NOT NULL DEFAULT 0,
    create_id   BIGINT      NOT NULL DEFAULT 0,
    create_time TIMESTAMP   NOT NULL DEFAULT CURRENT_TIMESTAMP,
    update_id   BIGINT      NOT NULL DEFAULT 0,
    update_time TIMESTAMP   NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (id)
);


INSERT INTO book_type (id, name, parent_id)
VALUES (1, '所有分类', 0),
       (2, '电脑相关', 1);


CREATE TABLE documentation
(
    id           BIGINT       NOT NULL AUTO_INCREMENT,
    isbn         VARCHAR(32)  NOT NULL DEFAULT '',
    name         VARCHAR(128) NOT NULL,
    author       VARCHAR(64)  NOT NULL DEFAULT '',
    publish      BIGINT       NOT NULL DEFAULT 0,
    type         BIGINT       NOT NULL DEFAULT 0,
    introduction TEXT         NOT NULL DEFAULT '',
    shelf        VARCHAR(32)  NOT NULL DEFAULT '',
    total        INT          NOT NULL DEFAULT 0,
    remaining    INT          NOT NULL DEFAULT 0,
    version      INT          NOT NULL DEFAULT 0,
    is_deleted   TINYINT      NOT NULL DEFAULT 0,
    create_id    BIGINT       NOT NULL DEFAULT 0,
    create_time  TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    update_id    BIGINT       NOT NULL DEFAULT 0,
    update_time  TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (id)
);

INSERT INTO documentation (isbn, name, author, publish, type, introduction, shelf, total, remaining)
VALUES ('', '史记', '司马迁', 0, 0,
        '《史记》，二十四史之一，最初称为《太史公书》或《太史公记》、《太史记》，是西汉史学家司马迁撰写的纪传体史书，是中国历史上第一部纪传体通史，记载了上至上古传说中的黄帝时代，下至汉武帝太初四年间共3000多年的历史。',
        '一号书架', 1,
        1);


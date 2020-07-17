CREATE TABLE system_config
(
    id           BIGINT       NOT NULL AUTO_INCREMENT,
    config_key   VARCHAR(32)  NOT NULL,
    config_value VARCHAR(128) NOT NULL,
    config_desc  VARCHAR(64)  NOT NULL DEFAULT '',
    version      INT          NOT NULL DEFAULT 0,
    is_deleted   TINYINT      NOT NULL DEFAULT 0,
    create_id    BIGINT       NOT NULL DEFAULT 0,
    create_time  TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    update_id    BIGINT       NOT NULL DEFAULT 0,
    update_time  TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (id)
);


INSERT INTO system_config (config_key, config_value, config_desc)
VALUES ('system_name', '内容管理系统', ''),
       ('system_desc', '内容管理系统（Content Management System）', ''),
       ('system_copyright', '© 2020 东京易冷', ''),
       ('cms_sys_home_url', '/home', ''),
       ('cms_visitor_status', '1', '启用 1 禁用 0');


CREATE TABLE `system_user`
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
    PRIMARY KEY (id),
    UNIQUE (username)
);

INSERT INTO `system_user` (id, username, true_name, email, phone, password)
VALUES (0, 'visitor', '游客', '', '', ''),
       (1, 'admin', '超级管理员', '641921791@qq.com', '18720968614',
        'ba3253876aed6bc22d4a6ff53d8406c6ad864195ed144ab5c87621b6c233b548baeae6956df346ec8c17f5ea10f35ee3cbc514797ed7ddd3145464e2a0bab413');


CREATE TABLE `system_role`
(
    id              BIGINT      NOT NULL AUTO_INCREMENT,
    name            VARCHAR(32) NOT NULL,
    is_default_role TINYINT     NOT NULL DEFAULT 0 COMMENT '是否默认角色。指在创建用户时，默认赋予用户的角色。',
    version         INT         NOT NULL DEFAULT 0,
    is_deleted      TINYINT     NOT NULL DEFAULT 0,
    create_id       BIGINT      NOT NULL DEFAULT 0,
    create_time     TIMESTAMP   NOT NULL DEFAULT CURRENT_TIMESTAMP,
    update_id       BIGINT      NOT NULL DEFAULT 0,
    update_time     TIMESTAMP   NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (id)
);


INSERT INTO `system_role` (id, name, is_default_role)
VALUES (0, '游客', 0),
       (1, '超级管理员', 0),
       (2, '普通角色', 1);


CREATE TABLE `system_user_role`
(
    id          BIGINT    NOT NULL AUTO_INCREMENT,
    user_id     BIGINT    NOT NULL,
    role_id     BIGINT    NOT NULL,
    version     INT       NOT NULL DEFAULT 0,
    is_deleted  TINYINT   NOT NULL DEFAULT 0,
    create_id   BIGINT    NOT NULL DEFAULT 0,
    create_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    update_id   BIGINT    NOT NULL DEFAULT 0,
    update_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (id)
);


INSERT INTO `system_user_role` (user_id, role_id)
VALUES (0, 0),
       (1, 1);


CREATE TABLE `system_permission`
(
    id          BIGINT      NOT NULL AUTO_INCREMENT,
    type        TINYINT     NOT NULL COMMENT '权限类型：1 页面 2 接口',
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

INSERT INTO `system_permission` (id, type, name, url, icon, parent_id)
VALUES (1, 1, '系统管理', '', 'layui-icon layui-icon-set', 0),
       (2, 1, '图书管理', '', 'layui-icon layui-icon-read', 0),
       (3, 1, '资料管理', '', 'layui-icon layui-icon-file', 0);

INSERT INTO `system_permission` (id, type, name, url, icon, parent_id)
VALUES (101, 1, '用户列表', '/user/list', '', 1),
       (102, 1, '角色列表', '/system/role', '', 1),
       (103, 1, '图书列表', '/book', '', 2),
       (104, 1, '图书类型', '/book/type', '', 2),
       (105, 1, '借阅管理', '/book/lend', '', 2),
       (106, 1, '资料列表', '/documentation', '', 3),
       (107, 1, '资料类型', '/documentation/type', '', 3),
       (108, 1, '借阅管理', '/documentation/lend', '', 3);

INSERT INTO `system_permission` (id, type, name, url, icon, parent_id)
VALUES (10101, 2, '查看用户列表', '{GET /api/v1/system/user/list}', '', 101),
       (10102, 2, '新增用户', '{POST /api/v1/system/user}', '', 101),
       (10103, 2, '修改用户', '{PUT /api/v1/system/user}', '', 101),
       (10104, 2, '删除用户', '{DELETE /api/v1/system/user}', '', 101),
       (10105, 2, '分配角色', '{POST /api/v1/system/user/{id}/role}', '', 101),
       (10201, 2, '查看角色列表', '{GET /api/v1/system/role/list}', '', 102),
       (10202, 2, '新增角色', '{POST /api/v1/system/role}', '', 102),
       (10203, 2, '修改角色', '{PUT /api/v1/system/role}', '', 102),
       (10204, 2, '删除角色', '{DELETE /api/v1/system/role}', '', 102),
       (10205, 2, '分配权限', '{POST /api/v1/system/role/{id}/permission}', '', 102),
       (10301, 2, '查看图书列表', '{GET /api/v1/book/list}', '', 103),
       (10302, 2, '新增图书', '{POST /api/v1/book}', '', 103),
       (10303, 2, '修改图书', '{PUT /api/v1/book}', '', 103),
       (10304, 2, '删除图书', '{DELETE /api/v1/book}', '', 103),
       (10305, 2, '借阅图书', '{POST /api/v1/book/lend}', '', 103),
       (10401, 2, '查看图书分类', '{GET /api/v1/book/type/list}', '', 104),
       (10402, 2, '新增图书分类', '{POST /api/v1/book/type}', '', 104),
       (10403, 2, '修改图书分类', '{PUT /api/v1/book/type}', '', 104),
       (10404, 2, '删除图书列表', '{DELETE /api/v1/book/type}', '', 104),
       (10501, 2, '查看借阅列表', '{GET /api/v1/book/lend/list}', '', 105),
       (10502, 2, '借还操作', '{PUT /api/v1/book/lend}', '', 105),
       (10601, 2, '查看资料列表', '{GET /api/v1/documentation/list}', '', 106),
       (10602, 2, '新增资料', '{POST /api/v1/documentation}', '', 106),
       (10603, 2, '修改资料', '{PUT /api/v1/documentation}', '', 106),
       (10604, 2, '删除资料', '{DELETE /api/v1/documentation}', '', 106),
       (10605, 2, '借阅资料', '{POST /api/v1/documentation/lend}', '', 106),
       (10701, 2, '查看资料分类', '{GET /api/v1/documentation/type/list}', '', 107),
       (10702, 2, '新增资料分类', '{POST /api/v1/documentation/type}', '', 107),
       (10703, 2, '修改资料分类', '{PUT /api/v1/documentation/type}', '', 107),
       (10704, 2, '删除资料列表', '{DELETE /api/v1/documentation/type}', '', 107),
       (10801, 2, '查看借阅列表', '{GET /api/v1/documentation/lend/list}', '', 108),
       (10802, 2, '借还操作', '{PUT /api/v1/documentation/lend}', '', 108);

CREATE TABLE `system_role_permission`
(
    id            BIGINT    NOT NULL AUTO_INCREMENT,
    role_id       BIGINT    NOT NULL,
    permission_id BIGINT    NOT NULL,
    version       INT       NOT NULL DEFAULT 0,
    is_deleted    TINYINT   NOT NULL DEFAULT 0,
    create_id     BIGINT    NOT NULL DEFAULT 0,
    create_time   TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    update_id     BIGINT    NOT NULL DEFAULT 0,
    update_time   TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (id)
);

INSERT INTO `system_role_permission` (role_id, permission_id)
VALUES (1, 1),
       (1, 2),
       (1, 3),
       (1, 101),
       (1, 102),
       (1, 103),
       (1, 104),
       (1, 105),
       (1, 106),
       (1, 107),
       (1, 108),
       (1, 10101),
       (1, 10102),
       (1, 10103),
       (1, 10104),
       (1, 10105),
       (1, 10201),
       (1, 10202),
       (1, 10203),
       (1, 10204),
       (1, 10205),
       (1, 10301),
       (1, 10302),
       (1, 10303),
       (1, 10304),
       (1, 10305),
       (1, 10401),
       (1, 10402),
       (1, 10403),
       (1, 10404),
       (1, 10501),
       (1, 10502),
       (1, 10601),
       (1, 10602),
       (1, 10603),
       (1, 10604),
       (1, 10605),
       (1, 10701),
       (1, 10702),
       (1, 10703),
       (1, 10704),
       (1, 10801),
       (1, 10802);

CREATE TABLE book_info
(
    id           BIGINT       NOT NULL AUTO_INCREMENT,
    isbn         VARCHAR(32)  NOT NULL DEFAULT '',
    name         VARCHAR(128) NOT NULL,
    author       VARCHAR(64)  NOT NULL DEFAULT '',
    publisher    VARCHAR(64)  NOT NULL DEFAULT 0,
    type         BIGINT       NOT NULL DEFAULT 0,
    introduction TEXT         NOT NULL,
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


INSERT INTO book_info (isbn, name, author, publisher, type, introduction, shelf, total, remaining)
VALUES ('978-7-1010-6716-3', '资治通鉴', '（宋）司马光', '中华书局', 1,
        '《资治通鉴》的内容以政治、军事和民族关系为主，兼及经济、文化和历史人物评价，目的是通过对事关国家盛衰、民族兴亡的统治阶级政策的描述警示后人。', '一号书架',
        3, 1),
       ('978-7-5354-9723-9', '钢铁是怎样炼成的', '【前苏联】尼古拉·奥斯特洛夫斯基', '长江文艺出版社', 2, '', '二号书架', 1, 1);


CREATE TABLE book_type
(
    id          BIGINT      NOT NULL AUTO_INCREMENT,
    name        VARCHAR(32) NOT NULL,
    note        VARCHAR(32) NOT NULL DEFAULT '' COMMENT '描述',
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
       (2, '长篇小说', 1);


CREATE TABLE book_lend
(
    id          BIGINT    NOT NULL AUTO_INCREMENT,
    user_id     BIGINT    NOT NULL,
    book_id     BIGINT    NOT NULL,
    lend_time   DATETIME  NOT NULL DEFAULT CURRENT_TIMESTAMP,
    return_time DATETIME           DEFAULT NULL,
    state       TINYINT   NOT NULL DEFAULT 0,
    version     INT       NOT NULL DEFAULT 0,
    is_deleted  TINYINT   NOT NULL DEFAULT 0,
    create_id   BIGINT    NOT NULL DEFAULT 0,
    create_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    update_id   BIGINT    NOT NULL DEFAULT 0,
    update_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (id)
);


INSERT INTO book_lend (user_id, book_id, lend_time, return_time, state)
VALUES (1, 1, '2019-03-05 01:53:56', '2020-03-05 01:53:56', 0),
       (1, 1, '2019-03-05 01:53:56', NULL, 1),
       (1, 1, '2019-03-05 01:53:56', NULL, 2);


CREATE TABLE documentation
(
    id           BIGINT       NOT NULL AUTO_INCREMENT,
    isbn         VARCHAR(32)  NOT NULL DEFAULT '',
    name         VARCHAR(128) NOT NULL,
    author       VARCHAR(64)  NOT NULL DEFAULT '',
    publisher    VARCHAR(64)  NOT NULL DEFAULT 0,
    type         BIGINT       NOT NULL DEFAULT 0,
    introduction TEXT         NOT NULL,
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

INSERT INTO documentation (isbn, name, author, publisher, type, introduction, shelf, total, remaining)
VALUES ('', '史记', '司马迁', '出版社', 0,
        '《史记》，二十四史之一，最初称为《太史公书》或《太史公记》、《太史记》，是西汉史学家司马迁撰写的纪传体史书，是中国历史上第一部纪传体通史，记载了上至上古传说中的黄帝时代，下至汉武帝太初四年间共3000多年的历史。',
        '一号书架', 3, 1);


CREATE TABLE documentation_type
(
    id          BIGINT      NOT NULL AUTO_INCREMENT,
    name        VARCHAR(32) NOT NULL,
    note        VARCHAR(32) NOT NULL DEFAULT '' COMMENT '描述',
    parent_id   BIGINT      NOT NULL DEFAULT 0,
    version     INT         NOT NULL DEFAULT 0,
    is_deleted  TINYINT     NOT NULL DEFAULT 0,
    create_id   BIGINT      NOT NULL DEFAULT 0,
    create_time TIMESTAMP   NOT NULL DEFAULT CURRENT_TIMESTAMP,
    update_id   BIGINT      NOT NULL DEFAULT 0,
    update_time TIMESTAMP   NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (id)
);


INSERT INTO documentation_type (id, name, parent_id)
VALUES (1, '所有分类', 0),
       (2, '历史相关', 1);


CREATE TABLE documentation_lend
(
    id               BIGINT    NOT NULL AUTO_INCREMENT,
    user_id          BIGINT    NOT NULL,
    documentation_id BIGINT    NOT NULL,
    lend_time        DATETIME  NOT NULL DEFAULT CURRENT_TIMESTAMP,
    return_time      DATETIME           DEFAULT NULL,
    state            TINYINT   NOT NULL DEFAULT 0,
    version          INT       NOT NULL DEFAULT 0,
    is_deleted       TINYINT   NOT NULL DEFAULT 0,
    create_id        BIGINT    NOT NULL DEFAULT 0,
    create_time      TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    update_id        BIGINT    NOT NULL DEFAULT 0,
    update_time      TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (id)
);


INSERT INTO documentation_lend (user_id, documentation_id, lend_time, return_time, state)
VALUES (1, 1, '2019-03-05 01:53:56', '2020-03-05 01:53:56', 0),
       (1, 1, '2019-03-05 01:53:56', NULL, 1),
       (1, 1, '2019-03-05 01:53:56', NULL, 2);


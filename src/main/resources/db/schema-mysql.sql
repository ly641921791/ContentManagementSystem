

CREATE TABLE system_config (
    id              BIGINT UNSIGNED     NOT NULL AUTO_INCREMENT,
    config_key      VARCHAR(32)         NOT NULL ,
    config_value    VARCHAR(32)         NOT NULL ,
    version         INT UNSIGNED        NOT NULL DEFAULT 0 ,
    is_deleted      TINYINT UNSIGNED    NOT NULL DEFAULT 0 ,
    create_id       BIGINT UNSIGNED     NOT NULL DEFAULT 0 ,
    create_time     TIMESTAMP           NOT NULL DEFAULT CURRENT_TIMESTAMP ,
    update_id       BIGINT UNSIGNED     NOT NULL DEFAULT 0 ,
    update_time     TIMESTAMP           NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP ,
    PRIMARY KEY (id) ,
    INDEX (config_key)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 ;


INSERT INTO system_config (config_key,config_value) VALUES
('system_name','内容管理系统') ,
('system_desc','内容管理系统') ;
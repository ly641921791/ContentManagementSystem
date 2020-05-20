

CREATE TABLE system_config (
   id              BIGINT              NOT NULL AUTO_INCREMENT,
   config_key      VARCHAR(32)         NOT NULL ,
   config_value    VARCHAR(32)         NOT NULL ,
   version         INT                 NOT NULL DEFAULT 0 ,
   is_deleted      TINYINT             NOT NULL DEFAULT 0 ,
   create_id       BIGINT              NOT NULL DEFAULT 0 ,
   create_time     TIMESTAMP           NOT NULL DEFAULT CURRENT_TIMESTAMP ,
   update_id       BIGINT              NOT NULL DEFAULT 0 ,
   update_time     TIMESTAMP           NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP ,
   PRIMARY KEY (id) 
) ;


INSERT INTO system_config (config_key,config_value) VALUES
('system_name','内容管理系统') ,
('system_desc','内容管理系统') ;
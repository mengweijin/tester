SET FOREIGN_KEY_CHECKS=0;

DROP TABLE IF EXISTS cms_user;
CREATE TABLE cms_user (
  username varchar(64) NOT NULL COMMENT '登录账号',
  password varchar(128) NOT NULL COMMENT '密码',
  nickname varchar(64) NOT NULL COMMENT '用户昵称',
  email varchar(64) DEFAULT NULL COMMENT '用户邮箱',
  mobile_phone varchar(15) DEFAULT NULL COMMENT '手机号码',
  sex char(1) DEFAULT NULL COMMENT '性别（M:男(male)， F:女(female)）',
  deleted char(1) DEFAULT 0 COMMENT '逻辑删除标记,（0未删除 1已删除）',
  enabled char(1) DEFAULT 1 COMMENT '启用标记,（0停用 1启用）',
  version int(11) DEFAULT 1 COMMENT '乐观锁',
  create_by varchar(64) NOT NULL COMMENT '创建者',
  create_time datetime NOT NULL COMMENT '创建时间',
  update_by varchar(64) NOT NULL COMMENT '更新者',
  update_time datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (username)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户信息表';

INSERT INTO cms_user VALUES('admin', '123', '系统管理员', 'mengweijin.work@foxmail.com', null, 'M', '0', '1', 1, 'system', '2020-01-01 00:00:00', 'system', '2020-01-01 00:00:00');
INSERT INTO cms_user VALUES('mengweijin', '123', '孟伟进', 'mengweijin.work@foxmail.com', null, 'M', '0', '1', 1, 'system', '2020-01-01 00:00:00', 'system', '2020-01-01 00:00:00');


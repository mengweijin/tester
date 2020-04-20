SET FOREIGN_KEY_CHECKS=0;

DROP TABLE IF EXISTS AT_DATA_SOURCE_INFO;
CREATE TABLE AT_DATA_SOURCE_INFO (
  id bigint NOT NULL COMMENT 'primary key id',
  name varchar(20) NOT NULL UNIQUE COMMENT 'Unique identification of data source name',
  url varchar(200) NOT NULL COMMENT 'jdbc url',
  username varchar(60) NOT NULL COMMENT 'Database username',
  password varchar(60) DEFAULT NULL COMMENT 'Database password',
  show_sql varchar(5) NOT NULL DEFAULT 'true' COMMENT 'true/false',
  format_sql varchar(5) NOT NULL DEFAULT 'false' COMMENT 'true/false',
  show_params varchar(5) NOT NULL DEFAULT 'true' COMMENT 'true/false',
  sql_level varchar(10) NOT NULL DEFAULT 'debug' COMMENT 'debug/info/error/warn',
  deleted int(4) DEFAULT 0 COMMENT 'Logic delete,（0 no delete; 1 deleted）',
  version bigint(11) DEFAULT 1 COMMENT 'Optimistic Lock',
  create_by varchar(64) NULL COMMENT 'Creator',
  create_time datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'creation time',
  update_by varchar(64) NULL COMMENT 'Revisor',
  update_time datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'Revisor time',
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='Data source info';

INSERT INTO AT_DATA_SOURCE_INFO VALUES(1, 'h2', 'jdbc:h2:file:./h2/test;AUTO_SERVER=TRUE;DB_CLOSE_ON_EXIT=FALSE;MODE=MYSQL', 'sa', null, 'true', 'false', 'true', 'debug', 0, 1, 'system', '2020-01-01 00:00:00', 'system', '2020-01-01 00:00:00');



DROP TABLE IF EXISTS AT_TEST_PROJECT;
CREATE TABLE AT_TEST_PROJECT (
  id bigint NOT NULL COMMENT 'primary key id',
  name varchar(100) NOT NULL COMMENT 'test project name',
  description varchar(500) NULL COMMENT 'test project description',
  deleted int(4) DEFAULT 0 COMMENT 'Logic delete,（0 no delete; 1 deleted）',
  version bigint(11) DEFAULT 1 COMMENT 'Optimistic Lock',
  create_by varchar(64) NULL COMMENT 'Creator',
  create_time datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'creation time',
  update_by varchar(64) NULL COMMENT 'Revisor',
  update_time datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'Revisor time',
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='test case';



DROP TABLE IF EXISTS AT_TEST_API;
CREATE TABLE AT_TEST_API (
  id bigint NOT NULL COMMENT 'primary key id',
  url varchar(500) NULL COMMENT 'test api url',
  http_method varchar(10) NOT NULL COMMENT 'HTTP method: GET/POST/PUT/DELETE',
  datasource_id bigint NULL COMMENT 'AT_DATASOURCE_INFO id',
  project_id bigint NOT NULL COMMENT 'AT_TEST_PROJECT id',
  transaction_rollback int(4) DEFAULT 1 COMMENT 'Enable database transaction rollback, （0 disable, auto commit; 1 enable, auto rollback）',
  deleted int(4) DEFAULT 0 COMMENT 'Logic delete,（0 no delete; 1 deleted）',
  version bigint(11) DEFAULT 1 COMMENT 'Optimistic Lock',
  create_by varchar(64) NULL COMMENT 'Creator',
  create_time datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'creation time',
  update_by varchar(64) NULL COMMENT 'Revisor',
  update_time datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'Revisor time',
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='test case';

INSERT INTO AT_TEST_API VALUES(1, 'http://localhost:8081/video-downloader/task/{id}', 'GET', 1, 1, 1, 0, 1, 'system', '2020-01-01 00:00:00', 'system', '2020-01-01 00:00:00');



DROP TABLE IF EXISTS AT_TEST_CASE;
CREATE TABLE AT_TEST_CASE (
  id bigint NOT NULL COMMENT 'primary key id',
  code varchar(20) NULL COMMENT 'Test case code, Associate TEST STEP ASSERT when excel import',
  name varchar(100) NOT NULL COMMENT 'test case name',
  description varchar(500) NOT NULL COMMENT 'test case description',
  api_id bigint NOT NULL COMMENT 'AT_TEST_API id',
  prepare_data_sql text NULL COMMENT 'prepare data SQLs',
  request_url varchar(500) NOT NULL COMMENT 'request url',
  request_method varchar(10) NOT NULL COMMENT 'HTTP request method: GET/POST/PUT/DELETE',
  request_params text NULL COMMENT 'request parameters JSON string',
  status varchar(20) NOT NULL DEFAULT 'WAITING' COMMENT 'ECaseStatus enum.',
  failed_message text NULL COMMENT 'test case failed message.',
  deleted int(4) DEFAULT 0 COMMENT 'Logic delete,（0 no delete; 1 deleted）',
  version bigint(11) DEFAULT 1 COMMENT 'Optimistic Lock',
  create_by varchar(64) NULL COMMENT 'Creator',
  create_time datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'creation time',
  update_by varchar(64) NULL COMMENT 'Revisor',
  update_time datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'Revisor time',
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='test case';

INSERT INTO AT_TEST_CASE VALUES(1, 'CODE 001', 'Happy Path', 'Happy Path Description', 1, null, 'http://localhost:8081/video-downloader/task/{id}', 'GET', '{"id": 20200414225648970}', 'WAITING', 0, 1, 'system', '2020-01-01 00:00:00', 'system', '2020-01-01 00:00:00');
INSERT INTO AT_TEST_CASE VALUES(2, 'CODE 002', 'Empty response', 'Empty response Description', 1, null, 'http://localhost:8081/video-downloader/task/{id}', 'GET', '{"id": 10000000000000000}', 'WAITING', 0, 1, 'system', '2020-01-01 00:00:00', 'system', '2020-01-01 00:00:00');



DROP TABLE IF EXISTS AT_TEST_STEP;
CREATE TABLE AT_TEST_STEP (
  id bigint NOT NULL COMMENT 'primary key id',
  case_id bigint NOT NULL COMMENT 'AT_TEST_CASE id',
  case_code varchar(20) NULL COMMENT 'Test case code, Associate TEST STEP ASSERT when excel import',
  step varchar(30) NOT NULL COMMENT 'EStep enum. ',
  index int(4) NOT NULL COMMENT 'The execution order of steps, generated by the program.',
  assert_key varchar(500) NULL COMMENT 'GIVEN_TOKEN:                    keep empty.
                                        GIVEN_HTTP_HEADER:              keep empty.
                                        WHEN_CALL_API:                  keep empty.
                                        ASSERT_HTTP_CODE:               keep empty.
                                        THEN_ASSERT_RESPONSE_JSON_PATH: JSON path in response body.
                                        ASSERT_RESPONSE:                keep empty.
                                        ASSERT_DB_DATA:                 write assert query SQL. If include a date type, please format it in SQL.',
  expect_value varchar(500) NULL COMMENT 'GIVEN_TOKEN:                    keep empty.
                                            GIVEN_HTTP_HEADER:              keep empty.
                                            WHEN_CALL_API:                  keep empty.
                                            ASSERT_HTTP_CODE:               200;400;404;等
                                            THEN_ASSERT_RESPONSE_JSON_PATH: JSON path的JSON vaule.
                                            ASSERT_RESPONSE:                完整的response body.
                                            ASSERT_DB_DATA:                 []括起来的sql查询结果',
  deleted int(4) DEFAULT 0 COMMENT 'Logic delete,（0 no delete; 1 deleted）',
  version bigint(11) DEFAULT 1 COMMENT 'Optimistic Lock',
  create_by varchar(64) NULL COMMENT 'Creator',
  create_time datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'creation time',
  update_by varchar(64) NULL COMMENT 'Revisor',
  update_time datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'Revisor time',
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='test step';

INSERT INTO AT_TEST_STEP VALUES(1, 1, 'CODE 001', 'WHEN_CALL_API', 1, null, null, 0, 1, 'system', '2020-01-01 00:00:00', 'system', '2020-01-01 00:00:00');
INSERT INTO AT_TEST_STEP VALUES(2, 1, 'CODE 001', 'THEN_ASSERT_HTTP_CODE', 2, 'httpCode', '200', 0, 1, 'system', '2020-01-01 00:00:00', 'system', '2020-01-01 00:00:00');
INSERT INTO AT_TEST_STEP VALUES(3, 1, 'CODE 001', 'THEN_ASSERT_RESPONSE', 3, 'response',
    '{
                "id": "20200414225423341",
                "createTime": "2020-04-14T22:54:23.341",
                "updateTime": "2020-04-14T23:04:44.542",
                "createBy": "admin",
                "updateBy": "admin",
                "deleted": 0,
                "name": "",
                "url": "http://www.gcwdp.com/zxgcw/142225.html",
                "status": "FAILED",
                "attachmentPath": null,
                "attachmentName": null,
                "errorMessage": "不支持当前视频源，请尝试打开原网页在视频上方点击右键另存为下载视频。"
    }', 0, 1, 'system', '2020-01-01 00:00:00', 'system', '2020-01-01 00:00:00');

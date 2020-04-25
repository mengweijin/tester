SET FOREIGN_KEY_CHECKS=0;

drop table IF EXISTS AT_DATA_SOURCE_INFO;
create TABLE AT_DATA_SOURCE_INFO (
  id bigint NOT NULL COMMENT 'primary key id',
  name varchar(20) NOT NULL UNIQUE COMMENT 'Unique identification of data source name',
  url varchar(200) NOT NULL COMMENT 'jdbc url',
  username varchar(60) NOT NULL COMMENT 'Database username',
  password varchar(60) DEFAULT NULL COMMENT 'Database password',
  version bigint(11) DEFAULT 1 COMMENT 'Optimistic Lock',
  deleted int(4) DEFAULT 0 COMMENT 'Logic delete,（0 no delete; 1 deleted）',
  create_by varchar(64) NULL COMMENT 'Creator',
  create_time datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'creation time',
  update_by varchar(64) NULL COMMENT 'Revisor',
  update_time datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON update CURRENT_TIMESTAMP COMMENT 'Revisor time',
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='Data source info';

insert into AT_DATA_SOURCE_INFO values(1, 'mysql', 'jdbc:mysql://192.168.233.155:3306/tester?useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=false', 'root', 'root', 1, 0, 'system', '2020-01-01 00:00:00', 'system', '2020-01-01 00:00:00');



drop table IF EXISTS AT_TEST_PROJECT;
create TABLE AT_TEST_PROJECT (
  id bigint NOT NULL COMMENT 'primary key id',
  name varchar(100) NOT NULL COMMENT 'test project name',
  description varchar(500) NULL COMMENT 'test project description',
  deleted int(4) DEFAULT 0 COMMENT 'Logic delete,（0 no delete; 1 deleted）',
  create_by varchar(64) NULL COMMENT 'Creator',
  create_time datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'creation time',
  update_by varchar(64) NULL COMMENT 'Revisor',
  update_time datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON update CURRENT_TIMESTAMP COMMENT 'Revisor time',
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='test case';

insert into AT_TEST_PROJECT values(1, 'video-downloader', 'video-downloader description', 0, 'system', '2020-01-01 00:00:00', 'system', '2020-01-01 00:00:00');



drop table IF EXISTS AT_TEST_API;
create TABLE AT_TEST_API (
  id bigint NOT NULL COMMENT 'primary key id',
  url varchar(500) NULL COMMENT 'test api url',
  http_method varchar(10) NOT NULL COMMENT 'HTTP method: GET/POST/PUT/DELETE',
  data_source_id bigint NULL COMMENT 'AT_DATASOURCE_INFO id',
  project_id bigint NOT NULL COMMENT 'AT_TEST_PROJECT id',
  deleted int(4) DEFAULT 0 COMMENT 'Logic delete,（0 no delete; 1 deleted）',
  create_by varchar(64) NULL COMMENT 'Creator',
  create_time datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'creation time',
  update_by varchar(64) NULL COMMENT 'Revisor',
  update_time datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON update CURRENT_TIMESTAMP COMMENT 'Revisor time',
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='test api';

insert into AT_TEST_API values(1, 'http://localhost:8080/system/testApi/{apiId}', 'GET', 1, 1, 0, 'system', '2020-01-01 00:00:00', 'system', '2020-01-01 00:00:00');



drop table IF EXISTS AT_TEST_CASE;
create TABLE AT_TEST_CASE (
  id bigint NOT NULL COMMENT 'primary key id',
  code varchar(20) NULL COMMENT 'Test case code, Associate TEST STEP ASSERT when excel import',
  name varchar(100) NOT NULL COMMENT 'test case name',
  description varchar(500) NOT NULL COMMENT 'test case description',
  api_id bigint NOT NULL COMMENT 'AT_TEST_API id',
  prepared_data_sql text NULL COMMENT 'prepared data SQLs',
  request_url varchar(500) NOT NULL COMMENT 'request url',
  http_method varchar(10) NOT NULL COMMENT 'HTTP request method: GET/POST/PUT/DELETE',
  url_params text NULL COMMENT 'url parameters JSON string',
  request_params text NULL COMMENT 'request parameters JSON string',
  status varchar(20) NOT NULL DEFAULT 'WAITING' COMMENT 'ECaseStatus enum.',
  feature text NULL COMMENT 'feature file',
  failed_message text NULL COMMENT 'test case failed message.',
  deleted int(4) DEFAULT 0 COMMENT 'Logic delete,（0 no delete; 1 deleted）',
  create_by varchar(64) NULL COMMENT 'Creator',
  create_time datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'creation time',
  update_by varchar(64) NULL COMMENT 'Revisor',
  update_time datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON update CURRENT_TIMESTAMP COMMENT 'Revisor time',
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='test case';

insert into AT_TEST_CASE values(1, 'CODE 001', 'Happy Path', 'Happy Path Description', 1, null, 'http://localhost:8080/system/testApi/{apiId}', 'GET', '{"apiId": 1}', '{"id": 1}', 'WAITING', null, null, 0, 'system', '2020-01-01 00:00:00', 'system', '2020-01-01 00:00:00');
insert into AT_TEST_CASE values(2, 'CODE 002', 'Empty response', 'Empty response Description', 1, null, 'http://localhost:8080/system/testApi/{apiId}', 'GET', '{"apiId": 1}', '{"id": 10000000000000000}', 'WAITING', null, null, 0, 'system', '2020-01-01 00:00:00', 'system', '2020-01-01 00:00:00');



drop table IF EXISTS AT_TEST_STEP;
create TABLE AT_TEST_STEP (
  id bigint NOT NULL COMMENT 'primary key id',
  case_id bigint NOT NULL COMMENT 'AT_TEST_CASE id',
  case_code varchar(20) NULL COMMENT 'Test case code, Associate TEST STEP ASSERT when excel import',
  step varchar(30) NOT NULL COMMENT 'EStep enum. ',
  default_index int(4) NOT NULL COMMENT 'The execution order of steps, generated by the program.',
  assert_key varchar(500) NULL COMMENT 'GIVEN_TOKEN:                    keep empty.
                                        GIVEN_HTTP_HEADER:              keep empty.
                                        WHEN_CALL_API:                  keep empty.
                                        ASSERT_HTTP_CODE:               keep empty.
                                        THEN_ASSERT_RESPONSE_JSON_PATH: JSON path in response body.
                                        ASSERT_RESPONSE:                keep empty.
                                        ASSERT_DB_DATA:                 write assert query SQL. If include a date type, please format it in SQL.',
  expect_value text NULL COMMENT 'GIVEN_TOKEN:                    keep empty.
                                 GIVEN_HTTP_HEADER:              keep empty.
                                 WHEN_CALL_API:                  keep empty.
                                 ASSERT_HTTP_CODE:               200;400;404;等
                                 THEN_ASSERT_RESPONSE_JSON_PATH: JSON path的JSON vaule.
                                 ASSERT_RESPONSE:                完整的response body.
                                 ASSERT_DB_DATA:                 []括起来的sql查询结果',
  deleted int(4) DEFAULT 0 COMMENT 'Logic delete,（0 no delete; 1 deleted）',
  create_by varchar(64) NULL COMMENT 'Creator',
  create_time datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'creation time',
  update_by varchar(64) NULL COMMENT 'Revisor',
  update_time datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON update CURRENT_TIMESTAMP COMMENT 'Revisor time',
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='test step';

insert into AT_TEST_STEP values(1, 1, 'CODE 001', 'GIVEN_TOKEN', 1, null, null, 0, 'system', '2020-01-01 00:00:00', 'system', '2020-01-01 00:00:00');
insert into AT_TEST_STEP values(2, 1, 'CODE 001', 'GIVEN_HTTP_HEADER', 2, null, null, 0, 'system', '2020-01-01 00:00:00', 'system', '2020-01-01 00:00:00');
insert into AT_TEST_STEP values(3, 1, 'CODE 001', 'WHEN_CALL_API', 3, null, null, 0, 'system', '2020-01-01 00:00:00', 'system', '2020-01-01 00:00:00');
insert into AT_TEST_STEP values(4, 1, 'CODE 001', 'THEN_ASSERT_HTTP_CODE', 4, null, '200', 0, 'system', '2020-01-01 00:00:00', 'system', '2020-01-01 00:00:00');
insert into AT_TEST_STEP values(5, 1, 'CODE 001', 'THEN_ASSERT_RESPONSE', 5, null,
    '{
        "createTime": "2020-01-01T00:00:00",
        "createBy": "system",
        "updateTime": "2020-01-01T00:00:00",
        "updateBy": "system",
        "deleted": 0,
        "id": "1",
        "url": "http://localhost:8080/system/testApi/{apiId}",
        "httpMethod": "GET",
        "dataSourceId": 1,
        "projectId": 1
    }', 0, 'system', '2020-01-01 00:00:00', 'system', '2020-01-01 00:00:00');
insert into AT_TEST_STEP values(6, 1, 'CODE 001', 'THEN_ASSERT_RESPONSE_JSON_PATH', 6, '$.id;$.url;',
    '{
        "$.id": "1",
        "$.url": "http://localhost:8080/system/testApi/{apiId}"
    }', 0, 'system', '2020-01-01 00:00:00', 'system', '2020-01-01 00:00:00');
insert into AT_TEST_STEP values(7, 1, 'CODE 001', 'THEN_ASSERT_DB_DATA', 7, 'select id, url from AT_TEST_API where id=1;',
    '[{
        "id": "1",
        "url": "http://localhost:8080/system/testApi/{apiId}"
    }]', 0, 'system', '2020-01-01 00:00:00', 'system', '2020-01-01 00:00:00');

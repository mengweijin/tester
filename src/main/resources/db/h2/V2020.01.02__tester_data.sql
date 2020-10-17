insert into AT_DATA_SOURCE_INFO values(1256875293757841408, 'mwj-tester-mysql-192.168.233.158:8080', 'jdbc:mysql://192.168.233.158:3306/tester?useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=false', 'root', 'root', 1, 0, 'system', '2020-01-01 00:00:00', 'system', '2020-01-01 00:00:00');
insert into AT_DATA_SOURCE_INFO values(1256875293757841409, 'video-downloader-h2-localhost:8080', 'jdbc:h2:file:D:/Source/Gitee/mwjwork/mwjwork-app/target/h2/test;AUTO_SERVER=TRUE;DB_CLOSE_ON_EXIT=FALSE;MODE=MYSQL', 'sa', null, 1, 0, 'system', '2020-01-01 00:00:00', 'system', '2020-01-01 00:00:00');


insert into AT_TEST_PROJECT values(1, 'video-downloader', 'video-downloader description', 1256875293757841409, 0, 'system', '2020-01-01 00:00:00', 'system', '2020-01-01 00:00:00');
insert into AT_TEST_PROJECT values(2, 'mwj-cms', 'mwj-cms description', 1256875293757841408, 0, 'system', '2020-01-01 00:00:00', 'system', '2020-01-01 00:00:00');


insert into AT_TEST_API values(1, 'http://localhost:8080/system/testApi/{apiId}', 'GET', 1, 0, 'system', '2020-01-01 00:00:00', 'system', '2020-01-01 00:00:00');


DROP DATABASE IF EXISTS `JSPAM`;
CREATE DATABASE `JSPAM`;
USE `JSPAM`;

# `article` 테이블 생성

CREATE TABLE article(
id INT(11) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
regDate DATETIME NOT NULL,
title VARCHAR(300) NOT NULL,
`body` TEXT NOT NULL,
memberId INT(11) UNSIGNED NOT NULL
);

# `member` 테이블 생성

CREATE TABLE `member`(
id INT(11) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
regDate DATETIME NOT NULL,
loginId VARCHAR(100) NOT NULL,
loginPw VARCHAR(200) NOT NULL,
`name` VARCHAR(100) NOT NULL
);

ALTER TABLE article CONVERT TO CHARSET UTF8;
ALTER TABLE `member` CONVERT TO CHARSET UTF8;
# `article` 테스트 데이터 생성 구문

INSERT INTO article
SET regDate = NOW(),
title = '제목1',
`body` ='내용1',
memberId = 1;

INSERT INTO article
SET regDate = NOW(),
title = '제목2',
`body` ='내용2',
memberId = 2;

INSERT INTO article
SET regDate = NOW(),
title = '제목3',
`body` ='내용3',
memberId = 3;

INSERT INTO article
SET regDate = NOW(),
title = '제목4',
`body` ='내용4',
memberId = 3;

# `member` 테스트 데이터 생성 구문

INSERT INTO `member`
SET regDate = NOW(),
loginId = 'test1',
loginPw ='test1',
`name` = '이름1';

INSERT INTO `member`
SET regDate = NOW(),
loginId = 'test2',
loginPw ='test2',
`name` = '이름2';

INSERT INTO `member`
SET regDate = NOW(),
loginId = 'test3',
loginPw ='test3',
`name` = '이름3';

# 조인 셀렉트
SELECT a.id, a.regDate, a.title, a.body, a.memberId, m.name
FROM article a
INNER JOIN `member` m
ON a.memberId = m.id
ORDER BY a.id DESC LIMIT 0, 10; 

SELECT * FROM article ORDER BY id DESC;

SELECT * FROM `member` ORDER BY id DESC;

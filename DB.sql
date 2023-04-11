DROP DATABASE IF EXISTS `JSPAM`;
CREATE DATABASE `JSPAM`;
USE `JSPAM`;

# `article` 테이블 생성

CREATE TABLE article(
id INT(11) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
regDate DATETIME NOT NULL,
title VARCHAR(100) NOT NULL,
`body` TEXT NOT NULL
);

# `member` 테이블 생성

CREATE TABLE `member`(
id INT(11) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
regDate DATETIME NOT NULL,
loginId VARCHAR(100) NOT NULL,
loginPw VARCHAR(200) NOT NULL,
`name` VARCHAR(100) NOT NULL
);

# `article` 테스트 데이터 생성 구문

INSERT INTO article
SET regDate = NOW(),
title = '제목1',
`body` ='내용1';

INSERT INTO article
SET regDate = NOW(),
title = '제목2',
`body` ='내용2';

INSERT INTO article
SET regDate = NOW(),
title = '제목3',
`body` ='내용3';

INSERT INTO article
SET regDate = NOW(),
title = '제목4',
`body` ='내용4';

# `member` 테스트 데이터 생성 구문

INSERT INTO `member`
SET regDate = NOW(),
loginId = 'test1',
loginPw ='test1',
`name` = '김아무개';

SELECT * FROM article ORDER BY id DESC;

SELECT * FROM `member` ORDER BY id DESC;

UPDATE article SET title = '제목11' WHERE id = 1;
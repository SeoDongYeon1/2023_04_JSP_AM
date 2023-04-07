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

SELECT * FROM article;

UPDATE article SET title = '제목11' WHERE id = 1;


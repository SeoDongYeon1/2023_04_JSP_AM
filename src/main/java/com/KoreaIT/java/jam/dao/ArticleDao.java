package com.KoreaIT.java.jam.dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.KoreaIT.java.jam.dto.Article;
import com.KoreaIT.java.jam.util.DBUtil;
import com.KoreaIT.java.jam.util.SecSql;

public class ArticleDao {
	Connection conn;
	public ArticleDao(Connection conn) {
		this.conn = conn;
	}
	public int getTotalCnt() {
		SecSql sql = SecSql.from("SELECT COUNT(*) AS cnt");
		sql.append("FROM article");
		
		return DBUtil.selectRowIntValue(conn, sql);
	}
	public List<Article> getArticles(int itemsInAPage, int limitFrom) {
		SecSql sql = new SecSql();
		sql.append("SELECT a.*, m.name AS extra__writer");
		sql.append("FROM article a");
		sql.append("INNER JOIN `member` m");
		sql.append("ON a.memberId = m.id");
		sql.append("ORDER BY a.id DESC LIMIT ?, ?;", limitFrom, itemsInAPage);
		
		List<Map<String, Object>> articleRows = DBUtil.selectRows(conn, sql);
		
		List<Article> articles = new ArrayList<>();
		for(Map<String, Object> articleRow : articleRows) {
			articles.add(new Article(articleRow));
		}
		
		return articles;
	}

}

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
	public Article getArticleById(int id) {
		SecSql sql = SecSql.from("SELECT a.*, m.name AS extra__writer");
		sql.append("FROM article a");
		sql.append("INNER JOIN `member` m");
		sql.append("ON a.memberId = m.id");
		sql.append("WHERE a.id = ?", id);
		
		Map<String, Object> articleRow = DBUtil.selectRow(conn, sql);
		Article article = new Article(articleRow);
		
		return article;
	}
	public void doDelete(int id) {
		SecSql sql = new SecSql();
		sql.append("DELETE FROM article");
		sql.append("WHERE id = ?;", id);
		
		DBUtil.delete(conn, sql);
	}
	public void doModify(String title, String body, int id) {
		SecSql sql = new SecSql();
		sql.append("UPDATE article");
		sql.append("SET title = ?", title);
		sql.append(", `body` = ?", body);
		sql.append("WHERE id = ?;", id);

		DBUtil.update(conn, sql);
	}
	public int Dowrite(String title, String body, int memberId) {
		SecSql sql = new SecSql();
		sql.append("INSERT INTO article");
		sql.append("SET regDate = NOW(),");
		sql.append("title = ?,", title);
		sql.append("`body` = ?,", body);
		sql.append("memberId = ?;", memberId);
		
		int id = DBUtil.insert(conn, sql);
		return id;
	}

}

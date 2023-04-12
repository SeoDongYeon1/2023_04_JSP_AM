package com.KoreaIT.java.jam.service;

import java.sql.Connection;
import java.util.List;

import com.KoreaIT.java.jam.dao.ArticleDao;
import com.KoreaIT.java.jam.dto.Article;

public class ArticleService {
	private Connection conn;
	private ArticleDao articleDao;
	
	public ArticleService(Connection conn) {
		this.conn = conn;
		this.articleDao = new ArticleDao(conn);
	}

	public int getItemsInAPage() {
		return 10;
	}

	public int getTotalPage() {
		int itemsInAPage = getItemsInAPage();

		int totalCnt = articleDao.getTotalCnt();
		int totalPage = (int) Math.ceil((double) totalCnt / itemsInAPage);
		
//		int totalPage = 0;
//
//		if(totalCnt%10==0) {
//			totalPage= totalCnt/10;
//		}
//		else if(totalCnt<=10) {
//			totalPage = 1;
//		}
//		else {
//			totalPage = totalCnt/10+1;
//		}

		
		return totalPage;
	}

	public List<Article> getArticles(int page) {
		int itemsInAPage = getItemsInAPage();
		int limitFrom = (page - 1) * itemsInAPage;
		
		List<Article> getArticles = articleDao.getArticles(itemsInAPage, limitFrom);
		
		return getArticles;
	}

	public Article getArticleById(int id) {
		Article article = articleDao.getArticleById(id);
		
		return article;
	}

	public void doDelete(int id) {
		articleDao.doDelete(id);
	}

	public void doModify(String title, String body, int id) {
		articleDao.doModify(title, body, id);
	}

	public int DoWrite(String title, String body, int memberId) {
		int id = articleDao.Dowrite(title, body, memberId);
		return id;
	}
	
}

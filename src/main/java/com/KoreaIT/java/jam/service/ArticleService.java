package com.KoreaIT.java.jam.service;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import com.KoreaIT.java.jam.dao.ArticleDao;
import com.KoreaIT.java.jam.util.DBUtil;
import com.KoreaIT.java.jam.util.SecSql;

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

	public List<Map<String, Object>> getForPrintArticleRows(int page) {
		int itemsInAPage = getItemsInAPage();
		int limitFrom = (page - 1) * itemsInAPage;
		
		List<Map<String, Object>> articleRows = articleDao.getARticleRows(itemsInAPage, limitFrom);
		
		return articleRows;
	}
	
}

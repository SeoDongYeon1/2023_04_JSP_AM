package com.KoreaIT.java.jam.controller;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.KoreaIT.java.jam.util.DBUtil;
import com.KoreaIT.java.jam.util.SecSql;

public class ArticleController {
	
	private HttpServletRequest request;
	private HttpServletResponse response;
	private Connection conn;

	public ArticleController(HttpServletRequest request, HttpServletResponse response, Connection conn) {
		this.request = request;
		this.response = response;
		this.conn = conn;
	}

	public void showList() throws ServletException, IOException {
		String inputedPage = request.getParameter("page");
		
		if(inputedPage==null) {
			inputedPage = "1";
		}
		
		int page = Integer.parseInt(inputedPage);
		
		int itemsInAPage = 10;
		int limitFrom = (page - 1) * itemsInAPage;
		
		SecSql sql = new SecSql();
		sql.append("SELECT COUNT(*) FROM article");
		int totalCnt = DBUtil.selectRowIntValue(conn, sql);
		
		sql = new SecSql();
		sql.append("SELECT a.*, m.name");
		sql.append("FROM article a");
		sql.append("INNER JOIN `member` m");
		sql.append("ON a.memberId = m.id");
		sql.append("ORDER BY a.id DESC LIMIT ?, ?;", limitFrom, itemsInAPage);
		
		List<Map<String, Object>> articleRows = DBUtil.selectRows(conn, sql);
		
//		 서블릿에서 jsp에 뭔가를 알려줘야할때
		request.setAttribute("articleRows", articleRows);
		request.setAttribute("totalCnt", totalCnt);
		request.setAttribute("page", page);

		request.getRequestDispatcher("/jsp/article/list.jsp").forward(request, response);
	}

}

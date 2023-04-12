package com.KoreaIT.java.jam.controller;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.KoreaIT.java.jam.dto.Article;
import com.KoreaIT.java.jam.service.ArticleService;
import com.KoreaIT.java.jam.util.DBUtil;
import com.KoreaIT.java.jam.util.SecSql;

public class ArticleController {
	
	private HttpServletRequest request;
	private HttpServletResponse response;
	private Connection conn;
	private ArticleService articleService;

	public ArticleController(HttpServletRequest request, HttpServletResponse response, Connection conn) {
		this.request = request;
		this.response = response;
		this.conn = conn;
		this.articleService = new ArticleService(conn);
	}

	public void showList() throws ServletException, IOException {
		int page = 1;

		if (request.getParameter("page") != null && request.getParameter("page").length() != 0) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		
		int itemsInAPage = articleService.getItemsInAPage();
		
		int totalPage = articleService.getTotalPage();
		
		List<Article> articles = articleService.getArticles(page);
		
//		 서블릿에서 jsp에 뭔가를 알려줘야할때
		request.setAttribute("page", page);
		request.setAttribute("totalPage", totalPage);
		request.setAttribute("articles", articles);

		request.getRequestDispatcher("/jsp/article/list.jsp").forward(request, response);
	}

	public void showDetail() throws ServletException, IOException {
		String inputedid = request.getParameter("id");
		
		if(inputedid==null) {
			inputedid = "1";
		}
		
		int id = Integer.parseInt(inputedid);
		
		SecSql sql = SecSql.from("SELECT a.id, a.regDate, a.title, a.body, a.memberId, m.name");
		sql.append("FROM article a");
		sql.append("INNER JOIN `member` m");
		sql.append("ON a.memberId = m.id");
		sql.append("WHERE a.id = ?", id);
		
		Map<String, Object> articleRow = DBUtil.selectRow(conn, sql);
		
		request.setAttribute("articleRow", articleRow);
		// 서블릿에서 jsp에 뭔가를 알려줘야할때
		
		request.getRequestDispatcher("/jsp/article/detail.jsp").forward(request, response);
	}

	public void doWrite() throws IOException {
		response.getWriter().append(String.format("<script>alert('hu'); location.replace('../home/main')</script>"));
	}

}

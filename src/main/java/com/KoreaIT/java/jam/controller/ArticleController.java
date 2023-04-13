package com.KoreaIT.java.jam.controller;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.KoreaIT.java.jam.dto.Article;
import com.KoreaIT.java.jam.service.ArticleService;

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
		
		int totalPage = articleService.getTotalPage();
		
		List<Article> articles = articleService.getArticles(page);
		
//		 서블릿에서 jsp에 뭔가를 알려줘야할때
		request.setAttribute("page", page);
		request.setAttribute("totalPage", totalPage);
		request.setAttribute("articles", articles);

		request.getRequestDispatcher("/jsp/article/list.jsp").forward(request, response);
	}
	
	public void doWrite(String actionMethodName) throws IOException, ServletException {
		HttpSession session = request.getSession();	
		
		int loginedMemberId = -1;
		
		if(session.getAttribute("loginedMemberId")!=null) {
			loginedMemberId = (int) session.getAttribute("loginedMemberId");
		}
		
		if(session.getAttribute("loginedMemberId")==null) {
			response.getWriter().append(String.format("<script>alert('로그인 후 이용해주세요.'); location.replace('../home/main')</script>"));
			return;
		}
		
		if(actionMethodName.equals("write")) {
			request.setAttribute("loginedMemberId", loginedMemberId);
			request.getRequestDispatcher("/jsp/article/write.jsp").forward(request, response);
		}
		else if(actionMethodName.equals("DoWrite")) {
			String title = request.getParameter("title");
			String body = request.getParameter("body");
			int memberId = Integer.parseInt(request.getParameter("loginedMemberId"));
			request.setCharacterEncoding("UTF-8");
			
			if(title==null) {
				title = "제목";
			}
			if(body==null) {
				body = "내용";
			}

			int id = articleService.DoWrite(title, body, memberId);
			
			response.getWriter().append(String.format("<script>alert('%d번 글이 생성되었습니다.'); location.replace('list')</script>", id));
		}
	}
	
	public void showDetail() throws ServletException, IOException {
		String inputedid = request.getParameter("id");
		
		if(inputedid==null) {
			inputedid = "1";
		}
		
		int id = Integer.parseInt(inputedid);
		
		Article article = articleService.getArticleById(id);
		
		request.setAttribute("article", article);
		// 서블릿에서 jsp에 뭔가를 알려줘야할때
		
		request.getRequestDispatcher("/jsp/article/detail.jsp").forward(request, response);
	}

	public void doDelete() throws IOException {
		HttpSession session = request.getSession();	
		
		if(session.getAttribute("loginedMemberId")==null) {
			response.getWriter().append(String.format("<script>alert('로그인 후 이용해주세요.'); location.replace('../article/list')</script>"));
			return;
		}
		
		int id = 1;

		if (request.getParameter("id") != null && request.getParameter("id").length() != 0) {
			id = Integer.parseInt(request.getParameter("id"));
		}

		Article article = articleService.getArticleById(id);

		if((int)session.getAttribute("loginedMemberId")!=article.memberId) {
			response.getWriter().append(String.format("<script>alert('이 게시글에 권한이 없습니다.'); location.replace('../s/article/list')</script>"));
			return;
		}
		
		articleService.doDelete(id);
		
		response.getWriter().append(String.format("<script>alert('%d번 글이 삭제되었습니다.'); location.replace('list')</script>", id));
	}

	public void doModify(String actionMethodName) throws IOException, ServletException {
		response.setContentType("text/html; charset=UTF-8");
		
		HttpSession session = request.getSession();	
		
		if(session.getAttribute("loginedMemberId")==null) {
			response.getWriter().append(String.format("<script>alert('로그인 후 이용해주세요.'); location.replace('../article/list')</script>"));
			return;
		}
		
		if(actionMethodName.equals("modify")) {
			int id = Integer.parseInt(request.getParameter("id"));
			
			Article article = articleService.getArticleById(id);
			
			if((int)session.getAttribute("loginedMemberId")!=article.memberId) {
				response.getWriter().append(String.format("<script>alert('이 게시글에 권한이 없습니다.'); location.replace('../article/list')</script>"));
				return;
			}
			
			request.setAttribute("article", article);
			request.getRequestDispatcher("/jsp/article/modify.jsp").forward(request, response);
		}
		else if(actionMethodName.equals("DoModify")) {
			String title = request.getParameter("title");
			String body = request.getParameter("body");
			int id = Integer.parseInt(request.getParameter("id"));
			
			if(title==null) {
				title = "제목";
			}
			if(body==null) {
				body = "내용";
			}

			articleService.doModify(title, body, id);
			
			response.getWriter().append(String.format("<script>alert('%d번 글이 수정되었습니다.'); location.replace('detail?id=%d')</script>", id, id));
		}
	}
		
	public Article getArticleById(int id) {
		Article article = articleService.getArticleById(id);
		return article;
	}


}

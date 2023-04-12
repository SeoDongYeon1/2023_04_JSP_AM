package com.KoreaIT.java.jam;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.KoreaIT.java.jam.config.Config;
import com.KoreaIT.java.jam.controller.ArticleController;
import com.KoreaIT.java.jam.dto.Article;
import com.KoreaIT.java.jam.exception.SQLErrorException;

@WebServlet("/s/article/modify")
public class ArticleModifyServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html; charset=UTF-8");
		
		HttpSession session = request.getSession();	
		
		if(session.getAttribute("loginedMemberId")==null) {
			response.getWriter().append(String.format("<script>alert('로그인 후 이용해주세요.'); location.replace('../article/list')</script>"));
			return;
		}
		
		// DB 연결
		Connection conn = null;
		try {
			Class.forName(Config.getDBDriverClassName());
		} catch (ClassNotFoundException e) {
			System.out.println("예외: 클래스가 없습니다.");
			System.out.println("프로그램을 종료합니다.");
			return;
		}
		
		try {
			conn = DriverManager.getConnection(Config.getDBUrl(), Config.getDBUser(),Config.getDBPassword());

			int id = Integer.parseInt(request.getParameter("id"));
			
			ArticleController articleController = new ArticleController(request, response, conn);

			Article article = articleController.getArticleById(id);

			if((int)session.getAttribute("loginedMemberId")!=article.memberId) {
				response.getWriter().append(String.format("<script>alert('이 게시글에 권한이 없습니다.'); location.replace('../article/list')</script>"));
				return;
			}
			
			request.setAttribute("article", article);
			request.getRequestDispatcher("/jsp/article/modify.jsp").forward(request, response);

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (SQLErrorException e) {
			e.getOrigin().printStackTrace();
		} finally {
			try {
				if (conn != null && !conn.isClosed()) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}

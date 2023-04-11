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

import com.KoreaIT.java.jam.config.Config;
import com.KoreaIT.java.jam.util.DBUtil;
import com.KoreaIT.java.jam.util.SecSql;

@WebServlet("/article/doWrite")
public class ArticleDoWriteServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
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
			response.getWriter().append("Success!!");
			
			
			String title = request.getParameter("title");
			String body = request.getParameter("body");
			request.setCharacterEncoding("UTF-8");
			
			if(title==null) {
				title = "제목";
			}
			if(body==null) {
				body = "내용";
			}

			SecSql sql = new SecSql();
			sql.append("INSERT INTO article");
			sql.append("SET regDate = NOW(),");
			sql.append("title = ?,", title);
			sql.append("`body` = ?;", body);
			
			int id = DBUtil.insert(conn, sql);
			
			response.getWriter().append(String.format("<script>alert('%d번 글이 생성되었습니다.'); location.replace('list')</script>", id));
			
			//request.getRequestDispatcher("/jsp/article/write.jsp").forward(request, response);
			
		} catch (SQLException e) {
			e.printStackTrace();
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}

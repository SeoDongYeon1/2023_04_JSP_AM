package com.KoreaIT.java.jam;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.KoreaIT.java.jam.config.Config;
import com.KoreaIT.java.jam.exception.SQLErrorException;
import com.KoreaIT.java.jam.util.DBUtil;
import com.KoreaIT.java.jam.util.SecSql;

@WebServlet("/article/detail")
public class ArticleDetailServlet extends HttpServlet {
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		HttpSession session = request.getSession();
		
		int loginedMemberId = -1;
		
		if(session.getAttribute("loginedMemberId")!=null) {
			loginedMemberId = (int) session.getAttribute("loginedMemberId");
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

			response.getWriter().append("Success!!");
			
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
			
			response.getWriter().append(articleRow.toString());
			
			request.setAttribute("loginedMemberId", loginedMemberId);
			request.setAttribute("articleRow", articleRow);
			// 서블릿에서 jsp에 뭔가를 알려줘야할때
			
			request.getRequestDispatcher("/jsp/article/detail.jsp").forward(request, response);
			
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

}

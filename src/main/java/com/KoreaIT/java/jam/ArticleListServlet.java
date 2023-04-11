package com.KoreaIT.java.jam;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.KoreaIT.java.jam.config.Config;
import com.KoreaIT.java.jam.util.DBUtil;
import com.KoreaIT.java.jam.util.SecSql;

@WebServlet("/article/list")
public class ArticleListServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		response.setContentType("text/html;charset=UTF-8");
		
		HttpSession session = request.getSession();
		
		String loginedMembername = null;
		
		if(session.getAttribute("loginedMemberId")!=null) {
			loginedMembername = (String) session.getAttribute("loginedMembername");
		}
		
		request.setAttribute("loginedMembername", loginedMembername);
		
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
			sql.append("SELECT a.id, a.regDate, a.title, a.body, a.memberId, m.name");
			sql.append("FROM article a");
			sql.append("INNER JOIN `member` m");
			sql.append("ON a.memberId = m.id");
			sql.append("ORDER BY a.id DESC LIMIT ?, ?;", limitFrom, itemsInAPage);
			
			List<Map<String, Object>> articleRows = DBUtil.selectRows(conn, sql);
			
			response.getWriter().append(articleRows.toString());
			
			request.setAttribute("articleRows", articleRows);
			request.setAttribute("totalCnt", totalCnt);
			request.setAttribute("page", page);
//			 서블릿에서 jsp에 뭔가를 알려줘야할때
			
			
			request.getRequestDispatcher("/jsp/article/list.jsp").forward(request, response);
			
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
}

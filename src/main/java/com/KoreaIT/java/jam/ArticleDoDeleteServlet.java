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
import com.KoreaIT.java.jam.util.DBUtil;
import com.KoreaIT.java.jam.util.SecSql;

@WebServlet("/article/doDelete")
public class ArticleDoDeleteServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		
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

			String inputedid = request.getParameter("id");
			
			if(inputedid==null) {
				inputedid = "1";
			}
			
			int id = Integer.parseInt(inputedid);
			
			SecSql sql = SecSql.from("SELECT *");
			sql.append("FROM article");
			sql.append("WHERE id = ? ;", id);

			Map<String, Object> articleRow = DBUtil.selectRow(conn, sql);

			if(session.getAttribute("loginedMemberId")!=articleRow.get("memberId")) {
				response.getWriter().append(String.format("<script>alert('이 게시글에 권한이 없습니다.'); location.replace('../article/list')</script>"));
				return;
			}
			
			sql = new SecSql();
			sql.append("DELETE FROM article");
			sql.append("WHERE id = ?;", id);
			
			DBUtil.delete(conn, sql);
						
			response.getWriter().append(String.format("<script>alert('%d번 글이 삭제되었습니다.'); location.replace('list')</script>", id));
			
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

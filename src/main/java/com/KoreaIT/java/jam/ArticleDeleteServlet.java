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

import com.KoreaIT.java.jam.util.DBUtil;
import com.KoreaIT.java.jam.util.SecSql;

@WebServlet("/article/delete")
public class ArticleDeleteServlet extends HttpServlet {
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		// DB 연결
		String url = "jdbc:mysql://127.0.0.1:3306/JSPAM?useUnicode=true&characterEncoding=utf8&autoReconnect=true&serverTimezone=Asia/Seoul&useOldAliasMetadataBehavior=true&zeroDateTimeNehavior=convertToNull";
		String user = "root";
		String password = "";
		Connection conn = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("예외: 클래스가 없습니다.");
			System.out.println("프로그램을 종료합니다.");
			return;
		}
		
		try {
			conn = DriverManager.getConnection(url, user, password);

			response.getWriter().append("Success!!");
			
			String inputedid = request.getParameter("id");
			
			if(inputedid==null) {
				inputedid = "1";
			}
			
			int id = Integer.parseInt(inputedid);
			
			SecSql sql = new SecSql();
			sql.append("DELETE FROM article");
			sql.append("WHERE id = ?", id);
			
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

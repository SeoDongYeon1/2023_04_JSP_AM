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

import com.KoreaIT.java.jam.config.Config;
import com.KoreaIT.java.jam.util.DBUtil;
import com.KoreaIT.java.jam.util.SecSql;

@WebServlet("/member/doJoin")
public class MemberDoJoinServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
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
			
			String loginId = request.getParameter("loginId").trim();
			String loginPw = request.getParameter("loginPw").trim();
			String name = request.getParameter("name").trim();
			
			request.setCharacterEncoding("UTF-8");
			

			SecSql sql = new SecSql();
			sql.append("SELECT COUNT(*) AS cnt ");
			sql.append("FROM `member`");
			sql.append("WHERE loginId = ?;", loginId);
			
			boolean isJoinAbleLoginId = DBUtil.selectRowIntValue(conn, sql)==0;
			
			if(isJoinAbleLoginId==false) {
				response.getWriter().append(String.format("<script>alert('이미 사용중인 아이디입니다.'); history.back()</script>"));
				return;
			}
			
			sql = SecSql.from("INSERT INTO `member`");
			sql.append("SET regDate = NOW(),");
			sql.append("loginId = ?,", loginId);
			sql.append("loginPw = ?,", loginPw);
			sql.append("`name` = ?;", name);
			
			DBUtil.insert(conn, sql);
			
			response.getWriter().append(String.format("<script>alert('%s님 회원가입 되었습니다.'); location.replace('../home/main')</script>",name));
			
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

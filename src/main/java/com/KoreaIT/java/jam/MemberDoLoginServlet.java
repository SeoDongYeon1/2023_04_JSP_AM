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

@WebServlet("/member/doLogin")
public class MemberDoLoginServlet extends HttpServlet {
	
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
			
			request.setCharacterEncoding("UTF-8");
			

			SecSql sql = new SecSql();
			sql.append("SELECT *");
			sql.append("FROM `member`");
			sql.append("WHERE loginId = ?;", loginId);
			
			Map<String,Object> memberRow = DBUtil.selectRow(conn, sql);
			
			if(memberRow.isEmpty()) {
				response.getWriter().append(String.format("<script>alert('아이디를 확인해주세요.'); location.replace('../member/login')</script>"));
				return;
			}
			
			if(memberRow.get("loginPw").equals(loginPw)==false) {
				response.getWriter().append(String.format("<script>alert('비밀번호를 확인해주세요.'); location.replace('../member/login')</script>"));
				return;
			}
			
			HttpSession session = request.getSession();
			session.setAttribute("loginedMemberId", memberRow.get("id"));
			session.setAttribute("loginedMemberLoginId", memberRow.get("loginId"));
			session.setAttribute("loginedMembername", memberRow.get("name"));
			// 세션에 속성 저장
			
			response.getWriter().append(String.format("<script>alert('%s님 환영합니다.'); location.replace('../home/main')</script>",memberRow.get("name")));
			
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

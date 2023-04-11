package com.KoreaIT.java.jam;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/home/main") // 요청사항에 대한 주소 정의
public class HomeMainServlet extends HttpServlet {
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		
		boolean isLogined = false;
		String loginedMemberLoginId = null;
		String loginedMembername = null;
		int loginedMemberId = -1;
		
		if(session.getAttribute("loginedMemberId")!=null) {
			isLogined = true;
			loginedMemberLoginId = (String) session.getAttribute("loginedMemberLoginId");
			loginedMembername = (String) session.getAttribute("loginedMembername");
			loginedMemberId = (int) session.getAttribute("loginedMemberId");
		}
		
		request.setAttribute("isLogined", isLogined);
		request.setAttribute("loginedMemberLoginId", loginedMemberLoginId);
		request.setAttribute("loginedMembername", loginedMembername);
		request.setAttribute("loginedMemberId", loginedMemberId);
		
		request.getRequestDispatcher("/jsp/home/main.jsp").forward(request, response);
		response.setContentType("text/html;charset=UTF-8");
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}

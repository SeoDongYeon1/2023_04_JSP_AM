package com.KoreaIT.java.jam;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/s/article/write")
public class ArticleWriteServlet extends HttpServlet {
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		HttpSession session = request.getSession();	
		
		int loginedMemberId = -1;
		
		if(session.getAttribute("loginedMemberId")!=null) {
			loginedMemberId = (int) session.getAttribute("loginedMemberId");
		}
		
		if(session.getAttribute("loginedMemberId")==null) {
			response.getWriter().append(String.format("<script>alert('로그인 후 이용해주세요.'); location.replace('../home/main')</script>"));
			return;
		}
		
		request.setAttribute("loginedMemberId", loginedMemberId);
		request.getRequestDispatcher("/jsp/article/write.jsp").forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}

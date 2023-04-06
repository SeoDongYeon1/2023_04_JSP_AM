package com.KoreaIT.java.jam;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/printDan") // 요청사항에 대한 주소 정의
public class HomeMainServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8"); // html문법을 써서 웹페이지 한글 깨짐 해결

		String inputedDan = request.getParameter("dan");
		String inputedLimit = request.getParameter("limit");
		
		if(inputedDan==null) {
			inputedDan = "1";
		}
		
		if(inputedLimit==null) {
			inputedLimit = "1";
		}
		
		int dan = Integer.parseInt(inputedDan);
		int limit = Integer.parseInt(inputedLimit);

		response.getWriter().append(String.format("==%d단==<br>", dan));

		for (int i = 1; i <= limit; i++) {
			response.getWriter().append(String.format("%d * %d = %d<br>", dan, i, dan*i));
		}
	}

}

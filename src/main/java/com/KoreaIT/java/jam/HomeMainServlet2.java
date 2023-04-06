package com.KoreaIT.java.jam;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/printDan") // 요청사항에 대한 주소 정의
public class HomeMainServlet2 extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8"); // html문법을 써서 웹페이지 한글 깨짐 해결

		String inputedDan = request.getParameter("dan");
		// /printDan? 뒤에 입력된 값(파라미터)를 받아온다
		// 파라미터를 여러개 받고싶으면 &로 엮어주면 된다. / 예시 : printDan?dan=9&limit=3
		String inputedLimit = request.getParameter("limit");
		String inputedColor = request.getParameter("color");
		
		if(inputedDan==null) {
			inputedDan = "1";
		}
		
		if(inputedLimit==null) {
			inputedLimit = "1";
		}
		
		if(inputedColor==null) {
			inputedColor = "black";
		}
		
		int dan = Integer.parseInt(inputedDan);
		int limit = Integer.parseInt(inputedLimit);
		String color = inputedColor;
		
		response.getWriter().append(String.format("<div style = \"color: %s;\">==%d단==</div>", color, dan));

		for (int i = 1; i <= limit; i++) {
			response.getWriter().append(String.format("<div style = \"color: %s;\">%d * %d = %d</div>", color, dan, i, dan*i));
		}
	}

}

package com.KoreaIT.java.jam;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/printDan/dan") // 요청사항에 대한 주소 정의
public class HomeMainServlet extends HttpServlet {
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8"); // html문법을 써서 웹페이지 한글 깨짐 해결

		response.getWriter().append("구구단<br>");
				
		for(int dan = 2; dan <= 9; dan++) {
			response.getWriter().append("=====" + dan + "단====="+"<br/>");
			for(int j = 1; j<=9; j++) {
				response.getWriter().append(dan + " * " + j + " = " + dan*j + "<br/>");
			}
		}
		
//		PrintWriter out = response.getWriter();
//		out.print("이름: " + "<br/>");
//		out.print("비밀번호: " + "<br/>");
	}
}

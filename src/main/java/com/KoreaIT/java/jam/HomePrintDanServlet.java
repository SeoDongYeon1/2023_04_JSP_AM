package com.KoreaIT.java.jam;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/home/printDan") // 요청사항에 대한 주소 정의
public class HomePrintDanServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/jsp/home/printDan.jsp").forward(request, response);
		// 아래 src 폴더에 있는 /jsp/home/printDan.jsp에 일을 요청한다.
	}

}

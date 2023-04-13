package com.KoreaIT.java.jam.controller;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.KoreaIT.java.jam.dto.Member;
import com.KoreaIT.java.jam.service.MemberService;

public class MemberController {
	
	private HttpServletRequest request;
	private HttpServletResponse response;
	private Connection conn;
	private MemberService memberService;

	public MemberController(HttpServletRequest request, HttpServletResponse response, Connection conn) {
		this.request = request;
		this.response = response;
		this.conn = conn;
		this.memberService = new MemberService(conn);
	}
	
	public void doLogin(String actionMethodName) throws IOException, ServletException {
		if(actionMethodName.equals("login")) {
			response.setContentType("text/html;charset=UTF-8"); 
			request.getRequestDispatcher("/jsp/member/login.jsp").forward(request, response);
		}
		else if(actionMethodName.equals("DoLogin")) {
			String loginId = request.getParameter("loginId").trim();
			String loginPw = request.getParameter("loginPw").trim();
			
			Member member = memberService.getMemberByLoginId(loginId);
			
			if(member==null) {
				response.getWriter().append(String.format("<script>alert('아이디를 확인해주세요.'); location.replace('../member/login')</script>"));
				return;
			}
			
			if(member.loginPw.equals(loginPw)==false) {
				response.getWriter().append(String.format("<script>alert('비밀번호를 확인해주세요.'); location.replace('../member/login')</script>"));
				return;
			}
			
			HttpSession session = request.getSession();
			session.setAttribute("loginedMemberId", member.id);
			session.setAttribute("loginedMemberLoginId", member.loginId);
			session.setAttribute("loginedMembername", member.name);
			// 세션에 속성 저장
			
			response.getWriter().append(String.format("<script>alert('%s님 환영합니다.'); location.replace('../home/main')</script>",member.name));
		}
	}

	public void doLogout() throws IOException {
		HttpSession session = request.getSession();
		session.removeAttribute("loginedMemberId");
		session.removeAttribute("loginedMemberLoginId");
		
		response.getWriter().append(String.format("<script>alert('로그아웃 되었습니다.'); location.replace('../home/main')</script>"));
	}

	public void doJoin(String actionMethodName) throws IOException, ServletException {
		if(actionMethodName.equals("join")) {
			response.setContentType("text/html;charset=UTF-8");
			request.getRequestDispatcher("/jsp/member/join.jsp").forward(request, response);
		}
		else if(actionMethodName.equals("DoJoin")) {
			String loginId = request.getParameter("loginId").trim();
			String loginPw = request.getParameter("loginPw").trim();
			String name = request.getParameter("name").trim();
			
			request.setCharacterEncoding("UTF-8");
			
			boolean isJoinAbleLoginId = memberService.isJoinAbleLoginId(loginId);
			
			if(isJoinAbleLoginId==false) {
				response.getWriter().append(String.format("<script>alert('이미 사용중인 아이디입니다.'); location.replace('../member/join')</script>"));
				return;
			}
			
			memberService.doJoin(loginId, loginPw, name);
			
			response.getWriter().append(String.format("<script>alert('%s님 회원가입 되었습니다.'); location.replace('../home/main')</script>",name));
		}
	}
}

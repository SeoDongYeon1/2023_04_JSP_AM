<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
boolean isLogined = (boolean) request.getAttribute("isLogined");
String loginedMemberLoginId = (String) request.getAttribute("loginedMemberLoginId");
%>
<!DOCTYPE html>

<html>

<head>

<meta charset="UTF-8">

<title>메인 페이지</title>

</head>

<body>
	<h1 style="text-align:center;">MAIN</h1>
	<hr />
	
	<div>
	<% if(!isLogined) { 
	%>
		<div>
			<a href="../member/login">로그인</a>
		</div>
		
	<%}
	if(isLogined) { 
	%>
		<div>
			<%=loginedMemberLoginId %>님 로그인 중
			<a href="../member/doLogout">로그아웃</a>
		</div>		
	<%}%>
	
	</div>
	
	<ul>
		<li><a href="../article/list">게시물 리스트</a></li>
		<li><a href="../member/join">회원가입</a></li>
	</ul>
	
</body>

</html>
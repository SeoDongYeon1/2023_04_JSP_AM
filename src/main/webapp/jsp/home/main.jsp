<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
boolean isLogined = (boolean) request.getAttribute("isLogined");
String loginedMemberLoginId = (String) request.getAttribute("loginedMemberLoginId");
String loginedMembername = (String) request.getAttribute("loginedMembername");
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
	
	<div style="text-align: right; padding: 0 20px; ">
	<% if(!isLogined) { 
	%>
		<div style="border: 2px solid black; display: inline-block;">
			<a href="../member/login">로그인</a>
		</div>
		
	<%}
	if(isLogined) { 
	%>
		<div style="text-align:right;">
			<%=loginedMembername %>님 로그인 중
		</div>
		<div style="border: 2px solid black; display: inline-block;">
			<a style="padding: 5px; display: block;" href="../member/doLogout">로그아웃</a>
		</div>		
	<%}%>
	
	</div>
	
	<ul>
		<li><a href="../article/list">게시물 리스트</a></li>
		<%if(isLogined==false){ %>
			<li><a href="../member/join">회원가입</a></li>
		<%}%>
		
	</ul>
	
</body>

</html>
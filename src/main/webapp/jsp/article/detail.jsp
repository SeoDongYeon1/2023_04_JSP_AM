<%@ page import="java.util.List"%>
<%@ page import="java.util.Map"%>
<%@ page import="com.KoreaIT.java.jam.dto.Article"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
Article article = (Article) request.getAttribute("article");
String inputedid = request.getParameter("id");

if(inputedid==null) {
	inputedid = "1";
}

int id = Integer.parseInt(inputedid);

int loginedMemberId = (int) request.getAttribute("loginedMemberId");

int memberId = article.memberId;

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시물 상세보기</title>
</head>
<body>
	<h1 style="text-align:center;"><%=article.id %>번 게시물 상세페이지</h1>
	<%@ include file="../part/topbar.jspf" %>
	<hr />
	<nav>
			<div>번호 : <%=article.id %></div>
			<div>날짜 : <%=article.regDate %></div>
			<div>제목 : <%=article.title %></div>
			<div>내용 : <%=article.body %></div>
			<div>작성자 : <%=article.extra__writer %></div>
			<%if(loginedMemberId==memberId){ %>
			<div><a style="text-decoration: underline;" href="modify?id=<%=article.id%>">수정</a></div>	
			<%}%>
			<%if(loginedMemberId==memberId){ %>
			<div><a style="text-decoration: underline;" href="delete?id=<%=article.id%>">삭제</a></div>
			<%}%>
	</nav>
	
	<style type="text/css">
		a {
			color: black;
			text-decoration: none;
		}
		a:hover {
			text-decoration: underline;
		}
</style>
</body>
</html>
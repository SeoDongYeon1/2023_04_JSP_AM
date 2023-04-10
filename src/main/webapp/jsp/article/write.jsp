<%@ page import="java.util.List"%>
<%@ page import="java.util.Map"%>
<%@ page import="java.util.Scanner;"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
Scanner sc = new Scanner(System.in);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시물 작성</title>
</head>
<body>
	<h1>게시물 작성</h1>
	<div>제목 : <%=sc.nextLine() %></div>
	<div>내용 : <%=sc.nextLine() %></div>
	<div><a style="color:green" href="list">리스트로 돌아가기</a></div>
</body>
</html>
<%@ page import="java.util.List"%>

<%@ page import="java.util.Map"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
List<Map<String, Object>> articleRows = (List<Map<String, Object>>) request.getAttribute("articleRows");
int searchpage = request.getAttribute("page");
%>

<!DOCTYPE html>

<html>

<head>

<meta charset="UTF-8">

<title>게시물 리스트</title>

</head>

<body>
	<div>
		<a href="../home/main">메인페이지로 이동</a>
	</div>
	
	<h1>게시물 리스트</h1>
	
	<table style="border-collapse: collapse; border-color: green" border="2px">
	
		<tr>
			<th style="width:100px;">번호</th>
			<th style="width:300px;">작성날짜</th>
			<th style="width:300px;">제목</th>
			<th style="width:100px;">삭제</th>
		</tr>
		
		<%for(Map<String, Object> articleRow: articleRows) {
		%>		
		<tr style ="text-align: center;">
			<td><%=articleRow.get("id") %></td>
			<td><%=articleRow.get("regDate") %></td>
			<td><a href="detail?id=<%=articleRow.get("id")%>"><%=articleRow.get("title")%></a></td>
			<td><a href="delete?id=<%=articleRow.get("id")%>">del</a></td>
		</tr>
		<%}
		%>
	</table>
	
	<div><a href="list?page=<%= %>"></a></div>

</body>

</html>
<%@ page import="java.util.List"%>

<%@ page import="java.util.Map"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
List<Map<String, Object>> articleRows = (List<Map<String, Object>>) request.getAttribute("articleRows");
%>

<!DOCTYPE html>

<html>

<head>

<meta charset="UTF-8">

<title>게시물 리스트</title>

</head>

<body>
	<div>
		<a href="https://www.naver.com" target="_blank">네이버</a>
		<a href="http://localhost:8081/2023_04_JSP_AM/article/list" target="_blank">버튼</a>
		<a href="/2023_04_JSP_AM/article/list" target="_blank">버튼</a>
		<a href="detail" target="_blank">디테일</a>
	</div>
	
	
	<h1>게시물 리스트 v1</h1>

	<ul>
		<li><%=articleRows.get(0).get("id")%>번, <%=articleRows.get(0).get("regDate")%>,
			<%=articleRows.get(0).get("title")%>, <%=articleRows.get(0).get("body")%></li>
		<li><%=articleRows.get(1).get("id")%>번, <%=articleRows.get(1).get("regDate")%>,
			<%=articleRows.get(1).get("title")%>, <%=articleRows.get(1).get("body")%></li>
		<li><%=articleRows.get(2).get("id")%>번, <%=articleRows.get(2).get("regDate")%>,
			<%=articleRows.get(2).get("title")%>, <%=articleRows.get(2).get("body")%></li>
		<li><%=articleRows.get(3).get("id")%>번, <%=articleRows.get(3).get("regDate")%>,
			<%=articleRows.get(3).get("title")%>, <%=articleRows.get(3).get("body")%></li>
	</ul>
	
	<h1>게시물 리스트 v2</h1>

	<ul>
		<%
		for (int i = 0; i < articleRows.size(); i++) {
		%>

		<li><%=articleRows.get(i).get("id")%>번, <%=articleRows.get(i).get("regDate")%>,
			<%=articleRows.get(i).get("title")%>, <%=articleRows.get(i).get("body")%></li>
		<%
		}
		%>
	</ul>
	
	<h1>게시물 리스트 v3</h1>

	<ul>
		<%
		for (Map<String,Object> articleRow : articleRows) {
		%>
		<li><a href="detail?id=<%=articleRow.get("id")%>"><%=articleRow.get("id")%>번, <%=articleRow.get("regDate")%>,
			<%=articleRow.get("title")%>, <%=articleRow.get("body")%></a></li>
		<%
		}
		%>
	</ul>
	

</body>

</html>
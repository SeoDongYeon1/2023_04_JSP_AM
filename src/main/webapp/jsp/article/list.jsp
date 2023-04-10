<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
List<Map<String, Object>> articleRows = (List<Map<String, Object>>) request.getAttribute("articleRows");
int pagenum = (int) request.getAttribute("pagenum");

int pages = 0;

if(pagenum%10==0) {
	pages= pagenum/10;
}
else if(pagenum<=10) {
	pages = 1;
}
else {
	pages = pagenum/10+1;
}

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시물 리스트</title>
</head>
<body>
	<h1>게시물 리스트</h1>
	
	<div><a href="../home/main">메인 페이지로 이동</a></div>

	<table style="border-collapse: collapse; border-color: green;" border="2px">
		<tr>
			<th>번호</th>
			<th>제목</th>
			<th>내용</th>
			<th>삭제</th>
		</tr>
		
		<%for(Map<String, Object> articleRow : articleRows) {
		%>		
		<tr style = "text-align: center;">
			<td><%=articleRow.get("id") %></td>
			<td><%=articleRow.get("regDate") %></td>
			<td><a href="detail?id=<%=articleRow.get("id")%>"><%=articleRow.get("title") %></a></td>
			<td><a href="delete?id=<%=articleRow.get("id")%>">del</a></td>
		</tr>
		<%} %>
	</table>
	
	<%for(int i = 1; i <= pages; i++) {
	%>
	<div style = "display: inline-block;"><a href="list?page=<%=i%>"><%=i %></a></div>
	<%} %>
</body>
</html>
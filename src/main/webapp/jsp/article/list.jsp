<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
List<Map<String, Object>> articleRows = (List<Map<String, Object>>) request.getAttribute("articleRows");
int totalCnt = (int) request.getAttribute("totalCnt");
int cur_Page = (int) request.getAttribute("page");

int totalPage = 0;

if(totalCnt%10==0) {
	totalPage= totalCnt/10;
}
else if(totalCnt<=10) {
	totalPage = 1;
}
else {
	totalPage = totalCnt/10+1;
}

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시물 리스트</title>
</head>
<body>
	<h1 style = "text-align:center;">게시물 리스트</h1>
	
	<div >
		<a href="../home/main">메인 페이지로 이동</a></div>
	<div>
	
	<div >
		<a href="write">글 작성하기</a></div>
	<div>
		<table style="border-collapse: collapse; border-color: green; width: 600px;" border = 2px >
			<tr>
				<th>번호</th>
				<th>제목</th>
				<th>작성날짜</th>
			</tr>
		
			<%for(Map<String, Object> articleRow : articleRows) {
			%>		
			<tr style = "text-align: center;">
				<td><%=articleRow.get("id") %></td>
				<td><a href="detail?id=<%=articleRow.get("id")%>"><%=articleRow.get("title") %></a></td>
				<td><%=articleRow.get("regDate") %></td>
			</tr>
			<%} %>
		</table>
	</div>
	
	<style type="text/css">
		.page{
			background-color: gold;
		}
		
		.page > a {
			color: black;
		}
		
		.page > a.red {
			color: red;
		}
	</style>

	<div class="page">
		<a class = "first_page" href="list?page=1">[처음 페이지]</a>
		<%for(int i = 1; i <= totalPage; i++) {
		%>
		<a class= "<%=cur_Page == i ? "red" : ""%>" href="list?page=<%=i%>"><%=i %></a>
		<%} %>
		<a class = "first_page" href="list?page=<%=totalPage %>">[마지막 페이지]</a>
	</div>
	
</body>
</html>
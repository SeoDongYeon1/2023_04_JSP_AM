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

int displayPage = 10;
int startPage = ((cur_Page-1)/displayPage)*displayPage+1;
int endPage = startPage+displayPage-1;

String loginedMembername = (String) request.getAttribute("loginedMembername");

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시물 리스트</title>
</head>
<body>
	<h1 style = "text-align:center;">게시물 리스트</h1>
	<hr />
	
	<div style="text-align:right;">
		<%=loginedMembername %>님 로그인 중
	</div>
	
	<nav class="menu_option">
		<div style= "border: 1px solid black; display: inline-block; width: 13%; text-align: center;">
			<a style = "display: block" href="../home/main">메인 페이지로 이동</a>
		</div>

		<div style= "border: 1px solid black; display: inline-block; width: 13%; text-align: center;">
			<a style = "display: block" href="write">글 작성하기</a>
		</div>
	</nav>
	<br />
	<br />
	<div>
		<table style="border-collapse: collapse; border-color: green; width: 700px; " border = 2px >
			<tr>
				<th>번호</th>
				<th>제목</th>
				<th>작성날짜</th>
				<th>작성자</th>
			</tr>
		
			<%for(Map<String, Object> articleRow : articleRows) {
			%>		
			<tr class= "list_item" style = "text-align: center;">
				<td><%=articleRow.get("id") %></td>
				<td><a href="detail?id=<%=articleRow.get("id")%>"><%=articleRow.get("title") %></a></td>
				<td><%=articleRow.get("regDate") %></td>
				<td><%=articleRow.get("name") %></td>
			</tr>
			<%} %>
		</table>
	</div>
	
	<style type="text/css">
		table {
			margin-left:auto; 
    		margin-right:auto;
		}
		a {
			color: black;
			text-decoration: none;
			
		}
		a:hover {
			text-decoration: underline;
		}
		.menu_option {
			text-align: center;
		}
		.list_item > td > a {
			color: black;
			text-decoration: none;
		}
		.list_item > td > a:hover {
			text-decoration: underline;
		}
		.page {
			text-align: center;
		}
		.page > a {
			color: black;
			text-decoration: none;
		}
		
		.page > a.red {
			color: red;
		}
		.page > a.underline {
			text-decoration: underline;
		}
	</style>
	<br />
	<div class="page">
		<%
		if(cur_Page >1) {
			%>
			<a class = "first_page" href="list?page=1">[<<]</a>	
			<%
		}
		if(endPage > totalPage)
		{
			endPage = totalPage;
		}
							
	    if(startPage > displayPage)
	    { 
		%>
			<a href="list?page=<%=startPage - 10%>">[이전]</a>
		<%
		}
		for(int i=startPage; i <= endPage; i++){
			if(i == cur_Page)
			{
			%>
				<a class= "<%=cur_Page == i ? "red underline" : "" %>" href="list?page=<%=i%>">[<%=i %>]</a>
			<%									
			} 
			else
			{
			%>
				<a class= "<%=cur_Page == i ? "red underline" : "" %>" href="list?page=<%=i%>">[<%=i %>]</a>
			<%
			}
		}
		if(endPage < totalPage)
		{
		%>
			<a href="list?page=<%=startPage + 10 %>">[다음]</a>
		<%
		}
		if(cur_Page < totalPage) {
			%>
			<a class = "last_page" href="list?page=<%=totalPage%>">[>>]</a>	
			<%
		}
		%>
	</div>
	
</body>
</html>
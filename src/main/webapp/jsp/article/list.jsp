<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page import="com.KoreaIT.java.jam.dto.Article"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
List<Article> articles = (List<Article>) request.getAttribute("articles");
int totalPage = (int) request.getAttribute("totalPage");
int cur_Page = (int) request.getAttribute("page");


int displayPage = 10;
int startPage = ((cur_Page-1)/displayPage)*displayPage+1;
int endPage = startPage+displayPage-1;

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시물 리스트</title>
</head>
<body>
	<h1 style = "text-align:center;">게시물 리스트</h1>
	<%@ include file="../part/topbar.jspf" %>
	<hr />
	<div>
		<table style="border-collapse: collapse; border-color: green; width: 700px; " border = 2px >
			<tr>
				<th>번호</th>
				<th>제목</th>
				<th>작성날짜</th>
				<th>작성자</th>
			</tr>
		
			<%for(Article article : articles) {
			%>		
			<tr class= "list_item" style = "text-align: center;">
				<td><%=article.id %></td>
				<td><a href="detail?id=<%=article.id%>"><%=article.title %></a></td>
				<td><%=article.regDate %></td>
				<td><%=article.extra__writer %></td>
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
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.Map" %>
<%@ page import="com.KoreaIT.java.jam.dto.Article"%>
<%
Article article = (Article) request.getAttribute("article");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시물 수정</title>
</head>
<body>
	<h1 style="text-align:center;"><%=article.id%>번 게시물 수정</h1>
	<div><a style="color:green" href="list">리스트로 돌아가기</a></div>
	
	<form method="post" action="DoModify">
		<input value= "<%=article.id%>" type="hidden" name="id"/>
		<div>
			제목 : <input value="<%=article.title%>" type="text" name="title"
				placeholder="제목을 입력해주세요" />
		</div>
		<div>
			내용 :
			<textarea type="text" name="body" placeholder="내용을 입력해주세요"><%=article.body%></textarea>
		</div>
		<button type="submit">수정하기</button>
	</form>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.Map" %>

<%
Map<String, Object> articleRow = (Map<String, Object>) request.getAttribute("articleRow");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시물 수정</title>
</head>
<body>
	<h1 style="text-align:center;"><%=articleRow.get("id") %>번 게시물 수정</h1>
	<div><a style="color:green" href="list">리스트로 돌아가기</a></div>
	
	<form method="post" action="doModify">
		<input value= "<%=articleRow.get("id") %>" type="hidden" name="id"/>
		<div>
			제목 : <input value="<%=articleRow.get("title")%>" type="text" name="title"
				placeholder="제목을 입력해주세요" />
		</div>
		<div>
			내용 :
			<textarea type="text" name="body" placeholder="내용을 입력해주세요"><%=articleRow.get("body")%></textarea>
		</div>
		<button type="submit">수정하기</button>
	</form>
</body>
</html>
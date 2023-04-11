<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
int loginedMemberId = (int) request.getAttribute("loginedMemberId");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시물 작성</title>
</head>
<body>
	<div><a style="color:green" href="list">리스트로 돌아가기</a></div>
	<h1 style="text-align:center;">게시물 작성</h1>
	
	<form method="post" action="doWrite">
		<input value= "<%=loginedMemberId %>" type="hidden" name="loginedMemberId"/>
		<div>
			제목 : <input type="text" placeholder="제목을 입력해주세요." name="title" autocomplete="on"/>
		</div>
		<div>
			내용 : <textarea type="text" placeholder="내용을 입력해주세요." name="body"></textarea>
		</div>
		<div></div>
		<button type="submit">글쓰기</button>
	</form>
</body>
</html>
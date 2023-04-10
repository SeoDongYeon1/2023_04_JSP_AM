<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
</head>
<body>
	<div><a style="color:green" href="../home/main">메인으로 돌아가기</a></div>
	<h1 style="text-align:center;">회원가입</h1>
	
	<form style="text-align:center;" method="post" action="doJoin">
		<div>
			아이디 : <input type="text" placeholder="아이디" name="loginId" autocomplete="on"/>
		</div>
		<br />
		<div>
			비밀번호 : <input type="password" placeholder="비밀번호" name="loginPw"/>
		</div>
		<br />
		<div>
			이름 : <input type="text" placeholder="이름" name="name"/>
		</div>
		<br />
		<button style="text-align:center;" type="submit">회원가입</button>
	</form>
</body>
</html>
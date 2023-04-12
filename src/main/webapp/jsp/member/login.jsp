<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page import="java.util.Map" %>

<%
Map<String, Object> memberRow = (Map<String, Object>) request.getAttribute("memberRow");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인 페이지</title>

<script type="text/javascript">
		var LoginForm__submitDone = false;
		
		function LoginForm__Submit(form) {
			if(JoinForm__submitDone) {
				alert('처리중 입니다.');
				return;
			}
			var loginId = form.loginId.value.trim();		
			var loginPw = form.loginPw.value.trim();
			var loginPwConfirm = form.loginPwConfirm.value.trim();
			var name = form.name.value.trim();
			
			if(loginId.length==0) {
				alert('아이디를 입력해주세요.');
				form.loginId.focus(); //하이라이팅
				return false;
			}
			if(loginPw.length==0) {
				alert('비밀번호를 입력해주세요.');
				form.loginPw.focus();	
				return false;
			}
			
			JoinForm__submitDone = true;
			form.submit();
		}	
</script>
</head>
<body>
	
	<h1 style="text-align:center;">로그인</h1>
	<hr />
	<div><a style="color:green" href="../home/main">메인으로 돌아가기</a></div>
	<form style="text-align:center;" method="post" action="doLogin" onsubmit = "return LoginForm__Submit(this); return false;">
	
		<div>
			아이디<br /><input type="text" placeholder="아이디" name="loginId" autocomplete="on" required/>
		</div>
		<br />
		<div>
			비밀번호<br /><input type="password" placeholder="비밀번호" name="loginPw" required/>
		</div>

		<br />
		<button type="submit">로그인</button>
	</form>
	
</body>
</html>
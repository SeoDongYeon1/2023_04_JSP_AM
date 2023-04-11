<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
	<script type="text/javascript">
		var JoinForm__submitDone = false;
		
		function JoinForm__Submit(form) {
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
			if(loginPwConfirm.length==0) {
				alert('비밀번호 확인을 입력해주세요.');
				form.loginPwConfirm.focus();	
				return false;
			}
			
			if(loginPw != loginPwConfirm) {
				alert( '비밀번호가 일치하지 않습니다.' );
				form.loginPw.select();
				form.loginPw.value="";
				form.loginPwConfirm.value="";
			    return false;
			}
			
			if(name.length==0) {
				alert('이름을 입력해주세요.');
				form.name.focus();
				return false;
			}
			
			JoinForm__submitDone = true;
			form.submit();
		}
	</script>
</head>
<body>
	<div><a style="color:green" href="../home/main">메인으로 돌아가기</a></div>
	<h1 style="text-align:center;">회원가입</h1>
	<hr />
	
	<form style="text-align:center;" method="post" action="doJoin" onsubmit = "return JoinForm__Submit(this); return false;">
		<div>
			아이디<br /><input type="text" placeholder="아이디" name="loginId" autocomplete="on"/>
		</div>
		<br />
		<div>
			비밀번호<br /><input type="password" placeholder="비밀번호" name="loginPw"/>
		</div>
		<br />
		<div>
			비밀번호 확인<br /><input type="password" placeholder="비밀번호 확인" name="loginPwConfirm" required id="pw2"/>
		</div>
		<br />
		<div>
			이름<br /><input type="text" placeholder="이름" name="name"/>
		</div>
		<br />
		<button type="submit">회원가입</button>
	</form>
</body>
</html>
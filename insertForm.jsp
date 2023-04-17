<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
	<script type="text/javascript">
		function checkData(){
			//ID check
			if(document.fr.id.value == ""){
				alert("아이디를 입력하세요.");
				document.fr.id.focus();
				return false;
			}// if end
		}// function end
	
	</script>
</head>
<body>
	<h1>insertForm(MVC).jsp</h1>
	<!-- itwill_member 테이블에 저장될 정보를 입력 -->
	<fieldset>
	<legend>Itwill Sign in</legend>
		<form action="./MemberJoinAction.me" method="post" name="fr" onsubmit="return checkData();">
						<!-- Action > 모델1의 Pro와 같은 역할! -->
		<!-- action 주소가 없는경우, 자신의 페이지를 호출한다. -->
			ID: <input type="text" name="id"><br>
			PW: <input type="password" name="pw"><br>
			Name: <input type="text" name="name"><br>
			Age: <input type="text" name="age"><br>
			E-mail: <input type="text" name="email"><br>
			Gender: 
				Male<input type="radio" name="gender" value="남">
				Female<input type="radio" name="gender" value="여"><br>
			
			
		<input type="submit" value="send">
		</form>
	</fieldset>

</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>memberDelete.jsp</h1>

	<!-- el표현식을 사용한 세션정보 제어 -->
	<c:if test="${empty id }">
		<c:redirect url="/MemberLogin.me"/>
	</c:if>
	
	<fieldset>
		<form action="MemberDeleteAction.me" method="post">
			<input type="hidden" name= "id" value="${id }"> <!-- 세션제어를 위한 히든태그 -->
			<input type="password" name="pw" placeholder="비밀번호를 입력하세요.">
			<input type="submit" value="탈퇴">
		 </form>
	</fieldset>


</body>
</html>
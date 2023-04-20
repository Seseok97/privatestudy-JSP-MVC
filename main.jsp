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
<h1>main.jsp(MVC)</h1>
<br>

로그인 아이디: ${id }<br>

<!-- 로그인 제어 -->
	<c:if test="${empty id }">
		<c:redirect url="./MemberLogin.me"/>
	</c:if>
	<br>

<!-- 로그아웃 버튼 -->
	<input type="button" value="Log Out" onclick="location.href='./MemberLogOutAction.me';">
	<hr>

<!-- 회원정보와 관련된 버튼 -->
	<h3><a href="./MemberInfo.me">내 정보 조회</a></h3>
	<h3><a href="./MemberUpdate.me">내 정보 수정</a></h3>
	<h3><a href="./MemberDelete.me">회원탈퇴</a></h3>

<!-- 관리자메뉴 -->
	<c:if test="${id eq 'admin' }">
		<hr>
		<h3><a href="#">회원정보 목록</a></h3>
	</c:if>




</body>
</html>
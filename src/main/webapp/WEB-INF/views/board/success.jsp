<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
		<h1>/board/success.jsp</h1>
		
		<!-- 컨트롤러에서 전달된 result 값에 따른 정보 출력 -->
		<!-- createOK 값이 전달된 경우에만 " 글쓰기 성공! "출력 -->
		
		
		<c:if test="${result.equals('createOK')}">
			<h2> 글쓰기 성공! </h2>		  
		</c:if>
		
		
		
		
		
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1> common_err.jsp </h1>
	
	<hr>
	${e }
	<hr>
	${e.getMessage() }
	
	
	<%
	
	// response.addHeader("Refresh", "3; url='/board/listAll'");
	
	%>
	
	
	
</body>
</html>
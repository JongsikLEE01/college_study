<%@page language="java" contentType="text/html;charset=utf-8"%>
<html>
<head>
	<title></title>
</head>
<body>
	<p> 잘못된 데이터 입력
	<p> <%="숫자1 : " + request.getParameter("num1") %>
	<p> <%="숫자2 : " + request.getParameter("num2") %>
</body>
</html>
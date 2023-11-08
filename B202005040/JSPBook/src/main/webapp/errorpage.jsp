<%@page language="java" contentType="text/html;charset=utf-8"%>
<%@page errorPage="errorpage_error.jsp" %>
<html>
<head>
	<title></title>
</head>
<body>
	name 파라미터 : <%= request.getParameter("name").toUpperCase() %>
</body>
</html>
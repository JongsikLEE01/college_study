<%@page language="java" contentType="text/html;charset=utf-8"%>
<%@page isErrorPage="true"%>
<html>
<head>
	<title></title>
</head>
<body>
	<p>error
	<p>예외 : <%=exception.getClass().getName() %>
	<p>유형 : <%=exception.getMessage() %>
</body>
</html>
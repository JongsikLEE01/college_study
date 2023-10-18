<%@page language="java" contentType="text/html;charset=utf-8"%>
<%@page isErrorPage="true" %>
<html>
<head>
	<title></title>
</head>
<body>
		<p>Error
		<p>예외 : <%=exception %> 
		<p> toString() : <%=exception.toString() %>
		<p> getClass().getName() : <%=exception.getClass().getName() %>
		<p> getMassage() : <%=exception.getMessage() %>
</body>
</html>
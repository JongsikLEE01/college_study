<%@page language="java" contentType="text/html;charset=utf-8"%>
<html>
<head>
	<title></title>
</head>
<body>
	<%
		String name= request.getParameter("name");
	%>
	<p> 입력된 name값 : <%=name %>
</body>
</html>
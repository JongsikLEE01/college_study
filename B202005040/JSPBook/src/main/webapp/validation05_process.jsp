<%@page language="java" contentType="text/html;charset=utf-8"%>
<html>
<head>
	<title>Validation</title>
</head>
<body>
	<%
		request.setCharacterEncoding("UTF-8");
	%>
	<p> 아 이 디 : <%=request.getParameter("id") %>
	<p> 비밀번호 : <%=request.getParameter("passwd") %>
	<p> 이   름 : <%=request.getParameter("name") %>
	<p> 연 락 처 : <%=request.getParameter("phone1") %> - <%=request.getParameter("phone2") %> - <%=request.getParameter("phone3") %>
	<p> 이 메 일 : <%=request.getParameter("email") %>
</body>
</html>
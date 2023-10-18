<%@page language="java" contentType="text/html;charset=utf-8"%>
<html>
<head>
	<title></title>
</head>
<body>
	<p> 사용자명: <%=request.getRemoteUser() %>
	<p> 인증방법: <%=request.getAuthType() %>
	<p>인증한 사용자명이 역할명 "tomcat"에 속하는 사용자? <%=request.isUserInRole("tomcat") %>
	<p>인증한 사용자명이 역할명 "role1"에 속하는 사용자? <%=request.isUserInRole("role1") %>
</body>
</html>
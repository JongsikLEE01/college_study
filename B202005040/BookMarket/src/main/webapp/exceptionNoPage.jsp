<%@page language="java" contentType="text/html;charset=utf-8" %>
<html>
<head>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
<title>페이지 오류</title>
</head>
<body>
	<%@include file="menu.jsp" %>
	<div class="jumbotron">
		<div class="container">
			<h2 class="alert alert-danger">요청하신 페이지를 찾을 수 없습니다.</h2>
		</div>
	</div>
	<div class="container">
		<p><%=request.getRequestURL() %></p>
		<p><a href="books.jsp" class="btn btn-secondary">도서 목록 &raquo;</a>
	</div>
</body>
</html>
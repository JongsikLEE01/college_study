<%@page language="java" contentType="text/html;charset=utf-8"%>
<%@page import="java.net.URLDecoder" %>
<html>
<head>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
<title>주문 취소</title>
</head>
<body>
	<%@include file="menu.jsp" %>
	<div class="jumbotron">
		<div class="container">
			<h1 class="display-3">주문 취소</h1>
		</div>
	</div>
	<div class="container">
		<h2 class="alert alert-danger">주문이 취소되었습니다.</h2>
	</div>
	<div class="container">
		<a href="./products.jsp" class="btn btn.secondary"> &laquo; 상품 목록</a>
	</div>
</body>
</html>
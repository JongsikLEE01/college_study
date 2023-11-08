<%@page language="java" contentType="text/html;charset=utf-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="dto.Product"%>
<%@page import="dao.ProductRepository" %>

<html>
<head>
<link rel="stylesheet" href="./resources/css/bootstrap.min.css" >
<title>Welcome</title>
</head>
<body>
	<%@include file="menu.jsp" %>
	<div class="jumbotron">
		<div class="container">
			<h1 class="display-3">상품 목록</h1>
		</div>
	</div>
	<%
		ProductRepository dao=ProductRepository.getInstance();
		ArrayList<Product> listOfProducts=dao.getAllProducts();
	%>
	<div class="container">
		<div class="row" align="center">
			<%
				for(int i=0;i<listOfProducts.size();i++){
					Product product=listOfProducts.get(i);
				
			%>
			<div class="col-md-4">
				<img src="./resources/images/<%=product.getFilename() %>" style="width:100%"/>
				<h3><%=product.getPname() %></h3>
				<p><%=product.getDescription() %>
				<p><%=product.getUnitPrice() %>원
				<p><a href="./product.jsp?id=<%=product.getProductId() %>" class="btn btn-secondary"
				role="button">상세 정보 &raquo;</a>
			</div>
			<%} %>
		</div>
	</div>
		<hr>
	<%@include file="footer.jsp" %>
</body>
</html>
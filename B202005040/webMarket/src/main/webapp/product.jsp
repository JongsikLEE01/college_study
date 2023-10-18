<%@page language="java" contentType="text/html;charset=utf-8"%>
<%@page import="dto.Product"%>
<%@page import="dao.ProductRepository" %>
<%@page errorPage="exceptionNoProductid.jsp" %>
<html>
<head>
<link rel="stylesheet" href="./resources/css/bootstrap.min.css">
<title>상품 상세 정보</title>
</head>
<body>
	<%@include file="menu.jsp" %>
	<div class="jumbotron">
		<div class="container">
			<h1 class="display-3">상품 상세 정보</h1>
		</div>
	</div>
	<%
		String id=request.getParameter("id");
		ProductRepository dao=ProductRepository.getInstance();
		Product product=dao.getProductById(id);
	%>
	<div class="container">
		<div class="row">
			<div class="col-md-6">
				<div class="col-md-5">
					<img src="./resources/images/<%=product.getFilename() %>" style="width:100%"/>
				</div>
				<h3><%=product.getPname() %></h3>
				<p><%=product.getDescription() %>
				<p><b>상품 코드 : </b><span class="badge badge-danger"><%=product.getProductId() %></span>
				<p><b>제조사</b> : <%=product.getManufacturer() %>
				<p><b>분류</b> : <%=product.getCategory() %>
				<p><b>재고 수</b> : <%=product.getUnitsInStock() %>
				<h4><%=product.getUnitPrice() %>원</h4>
				<p><a href="#" class="btn btn-info">상품 주문 &raquo;</a>
				<a href="./products.jsp" class="btn btn-secondary">상품 목록 &raquo;</a>
			</div>
		
		</div>
		</div>
		<hr>
	<%@include file="footer.jsp" %>
</body>
</html>
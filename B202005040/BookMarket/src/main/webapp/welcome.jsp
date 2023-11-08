<%@page language="java" contentType="text/html;charset=utf-8" %>
<html>
<head><title>BookMarket</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css">
</head>
<body>
	<%@include file="menu.jsp" %>
	
	<%!String greeting="도서 웹 쇼핑몰";
		String tagline="Welcome to Book Market";
	%>
	
	<div class="jumbotron">
		<div class="container">
			<h1 class="display-3">
				<%=greeting %>
			</h1>
		</div>
	</div>
	
	<div class="container">
		<div class="text-center">
			<h3><%=tagline %></h3>
		</div>
	</div>
	<hr>
	<%@include file="footer.jsp" %>
	
</body>
</html>
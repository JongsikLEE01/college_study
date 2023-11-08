<%@page language="java" contentType="text/html;charset=utf-8"%>
<%@page import="dto.Book"%>
<%@page import="java.util.ArrayList" %>
<%@page import="dao.BookRepository" %>
<html>
<head>
<link rel="stylesheet" href="./resources/css/bootstrap.min.css">
<title>도서 목록</title>
</head>
<body>
	<%@include file="menu.jsp" %>
	<div class="jumbotron">
		<div class="container">
			<h1 class="display-3">도서 목록</h1>
		</div>
	</div>
	<%
	BookRepository dao = BookRepository.getInstance();
	ArrayList<Book> listOfBooks = dao.getAllBooks();
	%>
	<div class="container">
		<div class="row" align="left">
			<%
			for (int i = 0; i < listOfBooks.size(); i++) {
				Book book = listOfBooks.get(i);
			%>
			<div class="col-md-3" align="center">
				<img src="./resources/images/<%=book.getFilename() %>" width="60%">
			</div>
			<div class="col-md-7">
				<p><h5><b>[<%=book.getCategory()%>]<%=book.getName() %></b></h5>
				<p style="padding-top:20px"><%=book.getDescription() %>...
				<p><%=book.getAuthor()%>|<%=book.getPublisher()%>|<%=book.getUnitPrice() %>원
				<p><a href="./book.jsp?id=<%=book.getBookId() %>" class="btn btn-secondary" role="button">상세 정보</a>
				
			</div>
			<%} %>
		</div>
	</div>
		<hr>
	<%@include file="footer.jsp" %>
</body>
</html>
<%@page language="java" contentType="text/html;charset=utf-8"%>
<%@page import="java.util.ArrayList" %>
<%@page import="dto.Book"%>
<%@page import="dao.BookRepository" %>
<%
	String id = request.getParameter("id");
	if(id == null || id.trim().equals("")){
		response.sendRedirect("books.jsp");
		return;
	}
	
	BookRepository dao = BookRepository.getInstance();
	
	Book product = dao.getBookById(id);
	if(product == null){
		response.sendRedirect("exeptionNoBookId.jsp");
	}
	
	ArrayList<Book> cartList = (ArrayList<Book>) session.getAttribute("cartlist");
	Book goodsQnt = new Book();
	for(int i =0; i < cartList.size(); i++){
		goodsQnt = cartList.get(i);
		if(goodsQnt.getBookId().equals(id)){
			cartList.remove(goodsQnt);
		}
	}
	
	response.sendRedirect("cart.jsp");
%>
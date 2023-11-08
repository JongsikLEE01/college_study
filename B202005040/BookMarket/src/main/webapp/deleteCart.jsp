<%@page language="java" contentType="text/html;charset=utf-8"%>
<%@page import="dto.Book"%>
<%@page import="dao.BookRepository" %>
<%
	String id = request.getParameter("id");
	if(id == null || id.trim().equals("")){
		response.sendRedirect("books.jsp");
		return;
	}
	
	session.invalidate();
	
	response.sendRedirect("cart.jsp");
%>
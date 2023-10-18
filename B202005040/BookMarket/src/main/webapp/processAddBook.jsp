<%@page language="java" contentType="text/html;charset=utf-8"%>
<%@page import="com.oreilly.servlet.*" %>
<%@page import="com.oreilly.servlet.multipart.*" %>
<%@page import="java.util.*" %>
<%@page import="dto.Book"%>
<%@page import="dao.BookRepository"%>
<%
	request.setCharacterEncoding("utf-8");

	String filename="";
	String realFolder="c:\\upload";
	int maxSize=5*1024*1024;
	String encType="utf-8";
	
	MultipartRequest multi=new MultipartRequest(
			request,
			realFolder,
			maxSize,
			encType,
			new DefaultFileRenamePolicy());

	String bookId=multi.getParameter("bookId");
	String name=multi.getParameter("name");
	String unitPrice=multi.getParameter("unitPrice");
	String author=multi.getParameter("author");
	String publisher=multi.getParameter("publisher");
	String releaseDate=multi.getParameter("releaseDate");
	String totalPages=multi.getParameter("totalPages");
	String description=multi.getParameter("description");
	String category=multi.getParameter("category");
	String unitsInStock=multi.getParameter("unitsInStock");
	String condition=multi.getParameter("condition");
	String bookImage=multi.getParameter("bookImage");
			
	
	Integer price;
	if(unitPrice.isEmpty()){
		price=0;
	}else{
		price=Integer.valueOf(unitPrice);
	}
	
	long pages;
	if(totalPages.isEmpty()){
		pages=0;
	}else{
		pages=Long.valueOf(totalPages);
	}
	
	long stock;
	if(unitsInStock.isEmpty()){
		stock=0;
	}else{
		stock=Long.valueOf(unitsInStock);
	}
	
	Enumeration files=multi.getFileNames();
	String fname=(String)files.nextElement();
	String fileName=multi.getFilesystemName(fname);
	
	BookRepository dao = BookRepository.getInstance();
	
	Book newBook = new Book();
	newBook.setBookId(bookId);
	newBook.setName(name);
	newBook.setUnitPrice(price);
	newBook.setAuthor(author);
	newBook.setPublisher(publisher);
	newBook.setReleaseDate(releaseDate);
	newBook.setTotalPages(pages);
	newBook.setDescription(description);
	newBook.setCategory(category);
	newBook.setUnitsInStock(stock);
	newBook.setCondition(condition);
	newBook.setFilename(bookImage);
	
	dao.addBook(newBook);
	
	response.sendRedirect("books.jsp");
%>
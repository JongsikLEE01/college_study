<%@page language="java" contentType="text/html;charset=utf-8"%>
<%@page import="com.oreilly.servlet.*" %>
<%@page import="com.oreilly.servlet.multipart.*" %>
<%@page import="java.util.*" %>
<%@page import="java.sql.*" %>
<%@include file="dbconn.jsp" %>

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

	String productId=multi.getParameter("productId");
	String name=multi.getParameter("name");
	String unitPrice=multi.getParameter("unitPrice");
	String description=multi.getParameter("description");
	String manufacturer=multi.getParameter("manufacturer");
	String category=multi.getParameter("category");
	String unitsInStock=multi.getParameter("unitsInStock");
	String condition=multi.getParameter("condition");
	
	Integer price;
	if(unitPrice.isEmpty()){
		price=0;
	}else{
		price=Integer.valueOf(unitPrice);
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
	
	PreparedStatement pstmt=null;
	
	String sql="insert into product values(?,?,?,?,?,?,?,?,?)";
	pstmt=conn.prepareStatement(sql);
	pstmt.setString(1,productId);
	pstmt.setString(2,name);
	pstmt.setString(3,unitPrice);
	pstmt.setString(4,description);
	pstmt.setString(5,category);
	pstmt.setString(6,manufacturer);
	pstmt.setString(7,unitsInStock);
	pstmt.setString(8,condition);
	pstmt.setString(9,fileName);
	pstmt.executeUpdate();
	
	if(pstmt!=null)
		pstmt.close();
	if(conn!=null)
		conn.close();
		
	response.sendRedirect("products.jsp");
%>
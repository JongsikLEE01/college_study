<%@page language="java" contentType="text/html;charset=utf-8"%>
<%@page import="com.oreilly.servlet.*"%>
<%@page import="com.oreilly.servlet.multipart.*"%>
<%@page import="java.util.*"%>
<%@page import="java.sql.*"%>
<%@include file="dbconn.jsp"%>
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
	ResultSet rs=null;
	
	String sql="select * from product where p_id=?";
	pstmt=conn.prepareStatement(sql);
	pstmt.setString(1,productId);
	rs=pstmt.executeQuery();
	
	if(rs.next()){
		if(fileName!=null){
			sql="update product set p_name=?,p_unitPrice=?,p_description=?,p_manufacturer=?,p_category=?,p_unitsInStock=?,p_condition=?,p_fileName=? where p_id=?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1,name);
			pstmt.setString(2,unitPrice);
			pstmt.setString(3,description);
			pstmt.setString(4,manufacturer);
			pstmt.setString(5,category);
			pstmt.setString(6,unitsInStock);
			pstmt.setString(7,condition);
			pstmt.setString(8,fileName);
			pstmt.setString(9,productId);
			pstmt.executeUpdate();
		}else{
			sql="update product set p_name=?,p_unitPrice=?,p_description=?,p_manufacturer=?,p_category=?,p_unitsInStock=?,p_condition=? where p_id=?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1,name);
			pstmt.setString(2,unitPrice);
			pstmt.setString(3,description);
			pstmt.setString(4,manufacturer);
			pstmt.setString(5,category);
			pstmt.setString(6,unitsInStock);
			pstmt.setString(7,condition);
			pstmt.setString(8,productId);
			pstmt.executeUpdate();
		}
	}
	if(rs!=null)
		rs.close();
	if(pstmt!=null)
		pstmt.close();
	if(conn!=null)
		conn.close();
	
	response.sendRedirect("editProduct.jsp?edit=update");
%>
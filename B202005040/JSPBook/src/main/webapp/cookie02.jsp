<%@page language="java" contentType="text/html;charset=utf-8"%>
<html>
<head>
	<title></title>
</head>
<body>
	<%
	try{
		Cookie[] cookies = request.getCookies();
		out.println("현재 성정된 쿠키 수" + cookies.length + "<br>");
		out.println("==========================<br>");
		for(int i=0; i<cookies.length ; i++){
			out.println("설정된 쿠키의 속성 이름" + cookies[i].getName() +"<br>");	
			out.println("설정된 쿠키의 속성 값" + cookies[i].getValue() +"<br>");	
			out.println("---------------------------<br>");	
		}
	}catch(NullPointerException e){
		
	}
	%>
</body>
</html>
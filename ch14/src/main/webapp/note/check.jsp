<%@ page import = "com.sanqing.servlet.*"%>
<jsp:useBean id="servlet" scope="page" class="com.sanqing.servlet.DOServlet"/>
<%
	String user=(String)session.getAttribute("sessionuser");
	if(user==null)
	servlet.responseUrl(response,"../error/noteerror.jsp");
%>
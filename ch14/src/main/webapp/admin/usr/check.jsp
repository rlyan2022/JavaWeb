<%@ page import = "com.sanqing.servlet.*"%>
<jsp:useBean id="sessionManager" class="com.sanqing.servlet.SessionManager"/>
<jsp:useBean id="servlet" scope="page" class="com.sanqing.servlet.DOServlet"/>

<%
	String adminName=sessionManager.getSession(session,"user");
	if(adminName==null)
	servlet.responseUrl(response,"../../error/error.jsp");
%>
<%@ page import = "com.sanqing.servlet.*"%>
<jsp:useBean id="SessionManager" class="com.sanqing.servlet.SessionManager"/>
<jsp:useBean id="servlet" scope="page" class="com.sanqing.servlet.DOServlet"/>
<%
	String user=SessionManager.getSession(session,"sessionuser");
	if(user==null)
	servlet.responseUrl(response,"../error/personalerror.jsp");
%>
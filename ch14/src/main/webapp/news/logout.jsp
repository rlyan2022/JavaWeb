<%@ page import = "com.sanqing.servlet.*" %>
<%@ include file="check.jsp" %>

<%
	SessionManager.removeSession(session,"user");
	DOServlet.responseUrl(response,"index.jsp");
%>
<%@ page import = "com.sanqing.servlet.*" %>
<%@ include file="check.jsp" %>

<%
	SessionManager.removeSession(session,"adminName");
	DOServlet.responseUrl(response,"index.jsp");
%>
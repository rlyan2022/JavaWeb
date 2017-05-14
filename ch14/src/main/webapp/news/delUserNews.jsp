<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="java.sql.*" %>
<%@ include file="check.jsp" %>


<jsp:useBean id="userN" scope="page" class="com.sanqing.news.UserNews"/>

<%
	int newsId=servlet.requestInt(request,"newsId");
	userN.delUserNews(servlet.requestInt(request,"newsId"));
	servlet.responseUrl(response,"personal.jsp");
%>
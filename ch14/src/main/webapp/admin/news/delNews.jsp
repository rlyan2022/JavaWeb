<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="java.sql.*" %>
<%@ include file="check.jsp" %>


<jsp:useBean id="news" scope="page" class="com.sanqing.news.manage.News"/>

<%
	int newsId=servlet.requestInt(request,"newsId");
	news.delNews(servlet.requestInt(request,"newsId"));
	servlet.responseUrl(response,"newslist.jsp");
%>
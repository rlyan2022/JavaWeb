<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="java.sql.*,com.sanqing.admin.column.*"%>
<%@ include file="check.jsp" %>

<jsp:useBean id="Kind" scope="page" class="com.sanqing.news.manage.Kind"/>

<body bgcolor="#799ae1">
<%
	int kindId = servlet.requestInt(request,"kindId");
	//执行删除
	Kind.delKind(kindId);
	servlet.responseUrl(response,"kind.jsp");
%>
</body>
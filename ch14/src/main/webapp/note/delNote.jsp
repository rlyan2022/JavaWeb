<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="check.jsp"%>


<jsp:useBean id="manager" scope="page" class="com.sanqing.news.note.Manager"/>
	
<%	
	int noteId=servlet.requestInt(request,"noteId");
	manager.delNote(noteId);
	DOServlet.responseUrl(response,"default.jsp");
%>
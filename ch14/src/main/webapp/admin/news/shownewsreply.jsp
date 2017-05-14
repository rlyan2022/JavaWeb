<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="java.sql.*"%>
<%@ page import="java.util.*" %>
<%@ page import="com.sanqing.persistence.*"%>
<%@ include file="check.jsp"%>

<jsp:useBean id="news" scope="page" class="com.sanqing.news.manage.News"/>

<link rel="stylesheet" href="../../css/admin.css" type="text/css">
<body bgcolor="#799ae1">
<table width="90%" height="73" border="1" align="center" cellpadding="0" cellspacing="0" bordercolor="#FFFFFF">
  <tr align="center"> 
    <td height="25" colspan="3"><font color="#FFFFFF"><strong>用户情况</strong></font></td>
  </tr>
  <tr align="center">
    <td width="69" height="24">用户名</td>
    <td width="335">内容</td>
    <td width="46">删除</td>
  </tr>
  <%
		Iterator rs = news.listReply(servlet.requestInt(request,"newsId"));
		while(rs.hasNext()){
			NEWSReply tableReply = (NEWSReply)rs.next();
  %>
  <tr align="center"> 
    <td height="24"><%=tableReply.getUser()%></td>
    <td height="24"><%=tableReply.getContent()%></td>
    <td><a href="delnewsreply.jsp?replyId=<%=tableReply.getReplyId()%>"><img src="../../images/admin/del.gif" border="0"></a></td>
  </tr>
  <%
	}
 %>
</table>
</body>
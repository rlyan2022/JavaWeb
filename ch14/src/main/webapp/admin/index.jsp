<%@ page contentType="text/html;charset=UTF-8"%>
<jsp:useBean id="DOServlet" class="com.sanqing.servlet.DOServlet"/>
<table width="75%" height="461" border="0" align="center">
  <tr> 
    <td align="center"><img src="news/images/go.gif" width="150" height="13"></td>
  </tr>
</table>
<%
	DOServlet.responseUrl(response,"admin_login.jsp");
%>
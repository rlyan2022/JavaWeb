<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="java.sql.*"%>
<%@ page import="java.util.*" %>
<%@ page import="com.sanqing.persistence.*"%>
<%@ include file="check.jsp"%>

<jsp:useBean id="userShow" class="com.sanqing.news.UserManage"/>
<jsp:useBean id="usr" class="com.sanqing.news.manage.Usr"/>
<%
	if(usr.adminPurview(adminName)>1)
		servlet.responseUrl(response,"../../error/error.jsp");
%>

<link rel="stylesheet" href="../../css/admin.css" type="text/css">
<body bgcolor="#799ae1">
<table width="400" height="204" border="0" align="center" cellpadding="0" cellspacing="0">
  <%
  		userShow.setUser(servlet.requestStr(request,"userName"));
		Iterator rs = userShow.listPersonal();
		while(rs.hasNext()){
			NEWSUsr userTable = (NEWSUsr)rs.next();
  %>
  <tr align="center"> 
    <td height="25" colspan="2"><font color="#FFFFFF">用户情况</font></td>
  </tr>
  <tr align="center">
    <td width="195" height="22">用户名</td>
    <td width="205"><%=userTable.getUserName()%></td>
  </tr>
  <tr align="center">
    <td height="19">性别</td>
    <td><%int sex=userTable.getSex();if(sex==1){out.print("男");}else{out.print("女");}%></td>
  </tr>
  <tr align="center">
    <td height="19">密码</td>
    <td><%=userTable.getPassWd()%></td>
  </tr>
  <tr align="center">
    <td height="21">密码提示问题</td>
    <td><%=userTable.getQuestion()%></td>
  </tr>
  <tr align="center">
    <td height="18">答案</td>
    <td><%=userTable.getAnswer()%></td>
  </tr>
  <tr align="center">
    <td height="18">E-mail</td>
    <td><%=userTable.getEmailAddr()%></td>
  </tr>
  <tr align="center">
    <td height="18">QQ</td>
    <td><%=userTable.getQq()%></td>
  </tr>
  <tr align="center">
    <td height="18">个人主页</td>
    <td><%=userTable.getHttp()%></td>
  </tr>
  <tr align="right">
    <td colspan="2"><a href="javascript:window.close()">关闭窗口</a>&nbsp;</td>
  </tr>
  <%
	}
 %>
</table>
</body>
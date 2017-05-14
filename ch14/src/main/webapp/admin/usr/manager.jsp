<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="java.sql.*" %>
<%@ page import="java.util.*" %>
<%@ page import="com.sanqing.persistence.*" %>
<%@ include file="check.jsp" %>

<jsp:useBean id="usr" scope="page" class="com.sanqing.news.manage.Usr"/>
<%
	if(usr.adminPurview(adminName)>0)
		servlet.responseUrl(response,"../../error/error.jsp");
%>
<jsp:useBean id="newsA" scope="page" class="com.sanqing.persistence.NEWSAdmin"/>
<jsp:setProperty name="newsA" property="*"/>
<%
	/**
	 *增加用户
	 */
	//取得action的值
	String action=servlet.requestStr(request,"submit");
	//判断插入
	if(action.equals("true")){
		//判断是否有重名的用户
		if(!usr.isAdminName(newsA.getUserName())){
			usr.insAdmin(newsA.getUserName(),newsA.getPurview());
			servlet.responseUrl(response,"manager.jsp");
		}
		else{
			out.println("<Script language=JavaScript>alert('此用户已经存在，请另选一个!');JavaScript:history.back();</Script>");
		}
	}
%>
<html>
<head>
<title>类别管理</title>
<link rel="stylesheet" href="../../css/admin.css" type="text/css">
<script language="JavaScript">
	function check(){
		if(manager.userName.value == ""){
			alert("用户名不为空！");
			return false;
		}
		if(manager.userName.value.length>20){
			alert("用户名不超过20位");
			return false;
		}
		return true;
	}
</script>
</head>

<body>
<table width="80%" height="2%" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td height="26" align="center"><font color="#FFFFFF"><strong>管理员信息管理</strong></font></td>
  </tr>
</table>
<table width="96%" border="1" align="center" cellpadding="0" cellspacing="0" bordercolor="#FFFFFF">
  <tr valign="middle" bgcolor="#799ae1">
    <td width="289" height="29" align="center"><font color="#FFFFFF">用户名</font></td>
    <td width="324" align="center"><font color="#FFFFFF">用户等级</font></td>
    <td width="127" align="center"><font color="#FFFFFF">删除</font></td>
  </tr>
  <%
  		if(usr.isAdminUser()){
  			Iterator rs = usr.listAdminUser();
			while(rs.hasNext()){
				NEWSAdmin tableAdmin = (NEWSAdmin)rs.next();
  %>
  <tr>
    <td height="30" align="center">&nbsp;<%=tableAdmin.getUserName()%></td>
    <td align="center">&nbsp;
      <a><%=usr.strPurview(tableAdmin.getPurview())%></a>
      </td>
    <td align="center"><a href="deladmin.jsp?userName=<%=tableAdmin.getUserName()%>" ><img src="../../images/admin/del.gif" alt="删除用户" width="16" height="16" border="0"></a></td>
  </tr>
  <%
			}
		}else{
%>
  <tr align="center">
    <td height="35" colspan="3" align="center" class="title">Sorry! 还没有任何记录？ 请先添加!</td>
  </tr>
  <%
	}
%>
</table>
<br>
<br>
<table width="96%" border="1" align="center" cellpadding="0" cellspacing="0" bordercolor="#FFFFFF">
  <tr>
    <td height="36" bordercolor="#FFFFFF">
    <form action="manager.jsp?submit=true" method="post" name="manager" onSubmit="return check();">
      <table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>
          <td width="15%" height="24" align="right"><strong><font color="#FFFFFF">添加管理员:</font></strong></td>
          <td width="76%" align="center">请输入用户名:&nbsp;&nbsp;
            <input type="text" size="16" name="userName">
            &nbsp;&nbsp;用户等级：
            <select name="purview" size="1" id="purview" value="" checked>
                <option value="1">管理员</option>
                <option value="2">录入员</option>
              </select></td>
          <td width="9%"><input type="submit" value="添加">&nbsp;</td>
        </tr>
		
      </table>
      </form></td>
  </tr>
</table>
<p>&nbsp; </p>
</body>
</html>

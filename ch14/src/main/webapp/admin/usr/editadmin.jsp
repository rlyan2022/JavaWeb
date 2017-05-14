<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="java.sql.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.sanqing.persistence.*"%>
<%@ include file="check.jsp"%>

<jsp:useBean id="usr" scope="page" class="com.sanqing.news.manage.Usr"/>
<jsp:useBean id="newsA" scope="page" class="com.sanqing.persistence.NEWSAdmin">
<jsp:setProperty name="newsA" property="*"/>
</jsp:useBean>

<%
	//更新管理员信息
	String action=servlet.requestStr(request,"submit");
	if(action.equals("true")){
		Iterator rs=usr.listAdmin(newsA.getUserName());
		while(rs.hasNext()){
		NEWSAdmin tableAdmin = (NEWSAdmin)rs.next();
			//判断插入
			if((tableAdmin.getPassWd()).equals(servlet.requestStr(request,"ypasswd"))){//原始密码判断
				usr.upAdmin(newsA.getPassWd(),newsA.getUserName());
				servlet.responseUrl(response,"../news/news.jsp");
			}
			else{
				out.println("<Script language=JavaScript>alert('您输入的原始密码有问题!');JavaScript:history.back();</Script>");
			}
		}
	}
%>
<html>
<head>
<title>类别管理</title>
<link rel="stylesheet" href="../../css/admin.css" type="text/css">
<script language="javascript">
	function check(){
		if(edit.ypasswd.value==""){
			alert("原始密码不为空！");
		}
		if(edit.passWd.value==""){
			alert("密码不位空！");
			return false;
		}
		if(edit.passWd.value.length>20){
			alert("密码不超过20位");
			return false;
		}
		if(edit.passWd.value.length<6){
			alert("密码不少于6位");
			return false;
		}
		if(edit.pwdAgain.value==""){
			alert(" 重复的密码不为空！");
			return false;
		}
		if(edit.pwdAgain.value.length>20){
			alert("密码不超过20位");
			return false;
		}
		if(edit.pwdAgain.value.length<6){
			alert("密码不少于6位");
			return false;
		}
		return true;
	}
</script>

</head>
<body>
<br>
<table width="80%" height="2%" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td height="26" align="center"><strong><font color="#ffffff">修改管理员信息</font></strong></td>
  </tr>
</table>
<br>
<form  method="post" action="editadmin.jsp?submit=true" name="edit" onsubmit="return check();">
<table width="96%" border="1" align="center" cellpadding="0" cellspacing="0" bordercolor="#FFFFFF">
  <tr align="center">
      <td width="48%" height="28">用 户 名：</td>
    <td width="52%"><font color="red"><%=adminName%></font></td>
  </tr>
  <tr align="center">
    <td height="29">
      <p>原始密码：</p></td>
    <td><input name="ypasswd" type="password" id="ypasswd" size="16"></td>
  </tr>
  <tr align="center">
      <td height="28">新 密 码：</td>
    <td><input name="passWd" type="password" id="passWd" size="16"></td>
  </tr>
  <tr align="center">
    <td height="26">重复一遍：</td>
    <td><input name="pwdAgain" type="password" id="pwdAgain" size="16"></td>
  </tr>
  <tr align="center">
    <td height="25">用户等级：</td>
    <td><%=usr.strPurview(usr.adminPurview(adminName))%>
 	</td>
  </tr>
  <tr align="center">
    <td height="29" colspan="2">
	<input type="hidden" value="<%=adminName%>" name="userName">
	<input type="submit" value="更新资料"></td>
  </tr>
</table>
</form>
<p>&nbsp; </p>
</body>
</html>

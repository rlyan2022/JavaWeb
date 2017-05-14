<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="java.sql.*" %>
<%@ page import="java.util.*" %>

<jsp:useBean id="noteA" scope="page" class="com.sanqing.persistence.NOTEAdmin"/>
<jsp:setProperty name="noteA" property="*"/>
<jsp:useBean id="servlet" scope="page" class="com.sanqing.servlet.DOServlet"/>
<jsp:useBean id="manager" class="com.sanqing.news.note.Manager"/>

<%
	String action=servlet.requestStr(request,"submit");
	if(action.equals("true")){
		if(manager.isAdminOk(noteA.getAdminName())){
			if(manager.isPasswdOk(noteA.getAdminPasswd())){
				session.setAttribute("sessionuser",noteA.getAdminName());
				servlet.responseUrl(response,"default.jsp");
			}
			else{
				out.println("<Script language=JavaScript>alert('请输入正确的密码!');JavaScript:history.back();</Script>");
			}
		}
		else{
			out.println("<Script language=JavaScript>alert('请输入正确的用户名!');JavaScript:history.back();</Script>");
		}
	}
%>
<html>
<head>
<title>新闻发布系统</title>
<link rel="stylesheet" href="text.css" type="text/css">
<script language="javascript">
	function check(){
		if(login.adminName.value==""){
			alert("请输入姓名");
			return false;
		}
		if(login.adminName.value.length>20){
			alert("姓名不超过20位");
			return false;
		}
		if(login.adminPasswd.value==""){
			alert("请输入密码");
			return false;
		}
		if(login.adminPasswd.value.length>20){
			alert("密码不超过20位");
			return false;
		}
		if(login.adminPasswd.value.length<6){
			alert("密码不少于6位");
			return false;
		}
		return true;
	}
</script>
</head>
<body leftMargin=0  topMargin=0 marginheight="0" marginwidth="0">
<br>
<br>
<br>
<br>
<div align="center">
  <center>
  <table align=center border=0 cellPadding=0 cellSpacing=0 width=500>
    <tr>
      <td width=134 height=37><IMG height=37 src="images/top_left.gif" width=134></td>
      <td width="100%" background=images/top_middle.gif height=37 valign="middle">
      <p align="center">　</td>
      <td width=49 height=37><IMG height=37 src="images/top_right.gif" width=49></td>
    </tr>
  </table>
  </center>
</div>
<div align=center>
  <center>

  <table border=0 cellPadding=0 cellSpacing=0 width=500 height=113 bordercolor="#111111">
    <tr>
      <td width=11 background="images/middle_left.gif" height=113>　</td>
      <td width=478 align="center" height=21>
    <br>
    <table border="0" cellpadding="0" cellspacing="0" bordercolor="#111111" width=280 height=107>
      <tr>
        <td width="100%" height=107 align="center" valign="top">
        <table border="1" cellpadding="0" cellspacing="0" bordercolor="#99BB99" width="100%" height="30" style="border-collapse: collapse">
          <tr>
            <td width="100%" height=30 background="images/note_bg.gif">
            <p align="center">sanqing留言本</td>
          </tr>
        </table>
       <table border="1" cellpadding="0" cellspacing="0"bordercolor="#99BB99" width="100%" height="92" style=" border-collapse: collapse">
          <form method=post action="login.jsp?submit=true" name="login" onsubmit="return check();">
          <tr>
            <td width=100 height=30>
            <p align="center">用户名：</td>
            <td width=180 height=30 align="center">
            <input type="text" name="adminName" size=16 class="text9"></td>
          </tr>
          <tr>
            <td width=100 height=30>
            <p align="center">密　码：</td>
            <td width=180 height=30 align="center">
            <input type="password" name="adminPasswd" size=16 class="text9"></td>
          </tr>
          <tr>
            <td width="100%" height=32 colspan=2>　　　　　　　　<input type="submit" name="Submit" value="登录">　　　　<input type="reset" name="reset" value="重置"></a></td>
          </tr>
          </form>
        </table>


        </td>
      </tr>
    </table>
    <br>
      </td>
      <td width=11 background="images/middle_left.gif" height=113>　</td>
    </tr>
    </table>
    </center>
</div>
<div align="center">
  <center>
  <table border="0" cellpadding="0" cellspacing="0" bordercolor="#111111" width=500>
    <tr>
    <td width="133" height="72"><IMG height="72" src="images/bottom_left.gif" width="133"></td>
    <TD width="100%" background=images/bottom_middle.gif height=72>
    <font color="#FFFFFF">All Rights Reserved.Copyleft &copy; 2001-2003 </font>
    <a href="http://www.sanqing.com"><font color="#FFFFFF">www.sanqing.com </font>
    </a> </TD>
    <td width="22" height="72"><IMG height="72" src="images/bottom_right.gif" width="22"></td>
    </tr>
  </table>
  </center>
</div>
</body>
</html>
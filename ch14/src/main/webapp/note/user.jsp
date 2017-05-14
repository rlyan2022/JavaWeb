<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="java.sql.*" %>
<%@ page import="java.util.*" %>
<%@ page import="com.sanqing.persistence.*" %>
<%@ include file="check.jsp" %>

<jsp:useBean id="manager" class="com.sanqing.news.note.Manager"/>

<%	
	String action=servlet.requestStr(request,"submit");
	if(action.equals("true")){
		//原始密码判断
		String adminPasswd=servlet.requestStr(request,"adminPasswd");
		String passWd=servlet.requestStr(request,"passWd");
		if(manager.isOldPwd(passWd,user)){
			manager.upPasswd(adminPasswd,user);
			servlet.responseUrl(response,"default.jsp");
		}
		else{
			out.println("<Script language=JavaScript>alert('您输入的原始密码有问题!');JavaScript:history.back();</Script>");
		}
	}
%>
<html>
<head>
<title>新闻发布系统</title>
<link rel="stylesheet" href="text.css" type="text/css">
<script language="javascript">
	function check(){
		if(user.passWd.value==""){
			alert("请输入旧密码");
			return false;
		}
		if(user.passWd.value.length>20){
			alert("旧密码不超过20位");
			return false;
		}
		if(user.passWd.value.length<6){
			alert("旧密码不少于6位");
			return false;
		}
		if(user.adminPasswd.value==""){
			alert("请输入新密码");
			return false;
		}
		if(user.adminPasswd.value.length>20){
			alert("新密码不超过20位");
			return false;
		}
		if(user.adminPasswd.value.length<6){
			alert("新密码不少于6位");
			return false;
		}
		return true;
	}
</script>
</head>

<body leftMargin=0  topMargin=0 marginheight="0" marginwidth="0" bgcolor="#ffffff">
<br>
<div align="center">
  <center>
  <table align=center border=0 cellPadding=0 cellSpacing=0 width=500>
    <tr>
      <td width=134 height=37><IMG height=37 src="images/top_left.gif" width=134></td>
      <td width="100%" background=images/top_middle.gif height=37 valign="middle">
      <p align="center"><font color="#FFFFFF">欢迎来到新闻发布系统!!!</font></td>
      <td width=49 height=37><IMG height=37 src="images/top_right.gif" width=49></td>
    </tr>
  </table>
  </center>
</div>
<div align=center>
  <center>
    <table align=center border=0 cellPadding=0 cellSpacing=0 width=500 height=228>
      	<form action="user.jsp?submit=true" method="post" name="user" onsubmit="return check();">
	  <tr>
        <td width=11 background="images/middle_left.gif" height=228>　</td>
        <td width=478 align="center" height=228> <br>
  <table border="1" cellpadding="0" cellspacing="0" style="border-collapse: collapse" bordercolor="#009933" width="77%" id="AutoNumber1" height="168">

	<tr>
                <td width="100%" height="166">
                  <table border="1" cellpadding="0" cellspacing="0" style="border-collapse: collapse" bordercolor="#009933" width="100%" id="AutoNumber2" height="164">
                    <tr>
          				<td width="100%" height="26" background="images/note_bg.gif"><p align="center"><font color="#FF0000">修改用户和密码</font></td>
          			</tr>
					<tr>
                      <td width="100%" height="113"> <table width="75%" height="84" border="0" align="center">
                          <tr>
                          <td align="center">用户名：&nbsp;<font color="red"><%=user%></font></td>
                        </tr>
                        <tr>
                            <td height="25" align="center">源密码：
							  <input name="passWd" type="password" id="passWd" size="20"></td>
                        </tr>

                        <tr>
                            <td height="31" align="center">新密码：
								<input name="adminPasswd" type="password" id="adminPasswd" size="20"></td>
                        </tr>

                      </table>

                      </td>
       			 </tr>
        <tr>
          <td width="100%" height="25">
          <p align="right">　
                          <input type="submit" value="修改">
                          &nbsp; </td>

        </tr>

		</table>
      </td>
    </tr>
    </table>
   <br>

  </td>
        <td width=11 background="images/middle_left.gif" height=228>　</td>
    </tr>
	</form>
    </table>
  </center>
</div>
<div align=center>
  <center>
  <table border=0 cellpadding=0 cellspacing=0 width=500>
    <tr>
    <td width=133 height=72><IMG height=72 src="images/bottom_left.gif" width=133></td>
    <TD width="100%" background=images/bottom_middle.gif height=72>
    <font color="#FFFFFF">All Rights Reserved.Copyleft &copy; 2001-2003 
    <a href="http://www.sanqing.com"><font color="#FFFFFF">www.sanqing.com</font></a></font><a href="http://www.sanqing.com"><font color="#FFFFFF">
    </font></a> </TD>
    <td width=22 height=72><IMG height=72 src="images/bottom_right.gif" width=22></td>
    </tr>
  </table>
  </center>
</div>
</body>
</html>
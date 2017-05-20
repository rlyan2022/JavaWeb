<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"><style type="text/css">
<!--
body {
	background-image: url(inmage/12d.JPG);
}
.style1 {
	font-size: 36px;
	color: #FF0000;
}
-->
</style><body>
<p align="center" class="style1">欢迎您<%=session.getAttribute("username")%>进入本系统</p>
<hr>
<p>&nbsp;</p>
<table width="644" height="26" border="2" align="center" cellpadding="1" cellspacing="0" bordercolor="#FFFFFF" bgcolor="#CDE9FE">
  <tr>
    <td><div align="center"><a href="index.html">首页</a></div></td>
    <td><div align="center"><a href="ModifySerlvet?param=0">修改个人信息</a></div></td>
    <td><div align="center"><a href="passwordamend.jsp">密码修改</a></div></td>
    <td><div align="center"><a href="DestineServlet?param=1">预订机票</a></div></td>
    <td><div align="center"><a href="ExamineSerlvet">查看购物车</a></div></td>
    <td><div align="center"><a href="index.html">退出</a></div></td>
  </tr>
</table>
<p>&nbsp;</p>

</body>
</html>

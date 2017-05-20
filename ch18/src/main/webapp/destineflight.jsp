<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
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
<p align="center" class="style1">订票信息</p>
<hr>
<p>&nbsp;</p>
<table width="644" height="26" border="2" align="center" cellpadding="1" cellspacing="0" bordercolor="#FFFFFF" bgcolor="#CDE9FE">
  <tr>
    <td><div align="center"><a href="index.html">首页</a></div></td>
    <td><div align="center"><a href="ModifySerlvet?param=0">修改个人信息</a></div></td>
    <td><div align="center"><a href="passwordamend.jsp">密码修改</a></div></td>
    <td><div align="center">预订机票</div></td>
    <td><div align="center"><a href="ExamineSerlvet">查看购物车</a></div></td>
    <td><div align="center"><a href="index.html">退出</a></div></td>
  </tr>
</table>
<p align="center">&nbsp;</p>
<p align="center">&nbsp;</p>
<table width="593" border="1" align="center" cellpadding="1" cellspacing="1">
  <tr>
    <td width="70" height="32"><div align="center">航班号</div></td>
    <td width="82"><div align="center">出发地点</div></td>
    <td width="100"><div align="center">目的地</div></td>
    <td width="130"><div align="center">航班日期</div></td>
    <td width="83"><div align="center">票价</div></td>
    <td width="70"><div align="center">总票数</div></td>
    <td width="91"><div align="center">预定</div></td>
  </tr>
  <c:forEach  var="sch" items="${sched}" >
     <tr>
    <td height="32" align="center">&nbsp;${sch.hao}</td>
    <td align="center">&nbsp;${sch.qifei}</td>
    <td align="center">&nbsp;${sch.mudi}</td>
    <td align="center">&nbsp;${sch.rqi}</td>
    <td align="center">&nbsp;${sch.jiage}</td>
    <td align="center">&nbsp;${sch.piaosu}</td>

    <td align="center">&nbsp;<a href="AffirmServlet?hao=${sch.hao}">订票</td>
  </tr>  
  </c:forEach>
 
  
</table>
<p align="center">&nbsp;</p>
<p align="center">&nbsp;</p>
</body>
</html>


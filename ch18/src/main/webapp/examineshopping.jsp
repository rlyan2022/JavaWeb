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
    <td><div align="center"><a href="DestineServlet?param=1">预订机票</a></div></td>
    <td><div align="center">查看购物车</div></td>
    <td><div align="center"><a href="index.html">退出</a></div></td>
  </tr>
</table>
<p align="center">&nbsp;</p>
<p align="center">&nbsp;</p>
<table width="593" border="1" align="center" cellpadding="1" cellspacing="1">
  <tr>
    <td width="70" height="32"><div align="center">航班号</div></td>
    <td width="100"><div align="center">出发地点</div></td>
    <td width="100"><div align="center">目的地</div></td>
    <td width="150"><div align="center">航班日期</div></td>
    <td width="83"><div align="center">票价</div></td>
     <td width="83"><div align="center">订票数</div></td>
    <td width="91"><div align="center">退订</div></td>
  </tr>
  <c:forEach var="ary" items="${ary}">
      <tr>
          <td height="32" align="center">&nbsp;${ary.hao}</td>
          <td align="center">&nbsp;${ary.qifei}</td>
          <td align="center">&nbsp;${ary.mudi}</td>
          <td align="center">&nbsp;${ary.rqi}</td>
          <td align="center">&nbsp;${ary.jiage}</td>
          <td align="center">&nbsp;${ary.piaosu}</td>
          <td align="center">&nbsp;<a href=RecedServlet?hao=${ary.hao}>退票</td>
      </tr>
  </c:forEach>
</table>
<p align="center">&nbsp;*如须退票,请用户在至少在规定航班日期前一天退票.</p>
<p align="center">&nbsp;超过规定期限,本系统不予票.</p>
</body>
</html>


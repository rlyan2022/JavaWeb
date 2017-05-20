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
<p align="center" class="style1">查看航班</p>
<hr>
<p>&nbsp;</p>
<table width="644" height="26" border="2" align="center" cellpadding="1" cellspacing="0" bordercolor="#FFFFFF" bgcolor="#CDE9FE">
  <tr>
    <td><div align="center"></div>
      <div align="center"><a href="estaflight.jsp">制定航班</a></div></td>
     <td> <div align="center"><a href="HavingServlet">安排航班</a></div></td>
    <td><div align="center"><a href="DestineServlet?param=2">查看航班</a></div></td>
    <td><div align="center"></div>      <div align="center"><a href="index.html">退出</a></div></td>
  </tr>
</table>
<p align="center">&nbsp;</p>
<table width="593" border="1" align="center" cellpadding="1" cellspacing="1">
  <tr>
    <td width="70" height="32"><div align="center">航班号</div></td>
    <td width="120" ><div align="center">航班日期</div></td>
    <td width="126" ><div align="center">出发地点</div></td>
    <td width="108"><div align="center">目的地</div></td>
    <td width="83"><div align="center">票价</div></td>
    <td width="91"><div align="center">票数</div></td>
    <td width="130"><div align="center">安排航班</a></div></td>
  </tr>
  <c:forEach var="sch" items="${sched}">
    <tr>
    <td height="32" align="center">&nbsp;${sch.hao}</td>
    <td align="center">&nbsp;${sch.rqi}</td>
    <td align="center">&nbsp;${sch.qifei}</td>
    <td align="center">&nbsp;${sch.mudi}</td>
    <td align="center">&nbsp;${sch.jiage}</td>
    <td align="center">&nbsp;${sch.piaosu}</td>
    <td align="center"><a href="InterServlet?param=${sch.hao}">安排${sch.hao}航班</a></td>
  </tr>
  </c:forEach>
</table>

</body>
</html>



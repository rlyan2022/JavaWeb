<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script language="javascript">
function add()
{
    var f=document.form1;
     if(f.Qifei.value==f.Mudi.value)
     {
        alert("起飞地点和目的地不能相同");

        return false;
     }
}
</script>

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
<p align="center" class="style1">制定航班</p>
<hr>
<p>&nbsp;</p>
<table width="644" height="26" border="2" align="center" cellpadding="1" cellspacing="0" bordercolor="#FFFFFF" bgcolor="#CDE9FE">
  <tr>
    <td><div align="center"></div>
      <div align="center"><a href="estaflight.jsp">制定航班</a></div></td>
      <td><div align="center"><a href="HavingServlet">安排航班</a></div></td>
    <td><div align="center"><a href="DestineServlet?param=2">查看航班</a></div></td>
    <td><div align="center"></div>      <div align="center"><a href="index.html">退出</a></div></td>
  </tr>
</table>
<p align="center">&nbsp;</p>
<form name="form1" method="post" action="SchedServlet"  onsubmit="return add()">
<table width="400" border="1" align="center" cellpadding="1" cellspacing="1">
  <tr>
    <td width="150" height="30" align="center">航班号</td>
    <td width="250"><div align="center">
                <select name="Hao">
                    <c:forEach var="flight" items="${a}">
                        <option value="${flight}">${flight}</option>
                </c:forEach>

            </select>
    </div></td>
  </tr>

  <tr>
    <td height="30" align="center">起飞地点</td>
    <td><div align="center"><select name="Qifei" >
                <option value="武汉">武汉</option>
                <option value="北京">北京</option>
                <option value="上海">上海</option>
                <option value="长沙">长沙</option>
                <option value="深圳">深圳</option>
                <option value="南京">南京</option>
                <option value="福州">福州</option>
                <option value="大连">大连</option>
                <option value="天津">天津</option>
                <option value="广州">广州</option>
    </select></div></td>
  </tr>
  <tr>
    <td height="30" align="center">目的地点</td>
    <td><div align="center">
            <select name="Mudi">
                <option value="北京">北京</option>
                <option value="上海">上海</option>
                <option value="长沙">长沙</option>
                <option value="深圳">深圳</option>
                <option value="南京">南京</option>
                <option value="福州">福州</option>
                <option value="大连">大连</option>
                <option value="天津">天津</option>
                <option value="广州">广州</option>

        </select></div>
    </td>
  </tr>
  <tr>
    <td height="30" align="center">旅程(小时)</td>
    <td><div align="center">

        <select name="Lchen">
                    <c:forEach var="h" items="${b}">
                        <option value="${h}">${h}</option>
                </c:forEach>
    </div></td>
  </tr>
  <tr>
    <td height="30" align="center">价格(￥)</td>
    <td><div align="center">
        <select name="Jiage">
                    <c:forEach var="mon" items="${c}">
                        <option value="${mon}">${mon}</option>
                </c:forEach>

    </div></td>
  </tr>
  <tr>
    <td height="30" align="center">票数(张)</td>
    <td><div align="center">
        <select name="Piaosu">
                    <c:forEach var="piao" items="${d}">
                        <option value="${piao}">${piao}</option>
                </c:forEach>

    </div></td>
  </tr>
</table>
<p align="center">
  <input name="tijiao" type="submit" id="tijiao" value="提交">
  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<input name="chongzhi" type="reset" id="chongzhi" value="重置">
</p>
<p align="center">&nbsp;</p>
</form>
</body>
</html>



<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.sanqing.dao.MessageDAO"%>
<%@page import="com.sanqing.factory.MessageDAOFactory"%>
<%@page import="com.sanqing.util.Page"%>
<%@page import="com.sanqing.util.PageUtil"%>
<%@page import="com.sanqing.bean.Message"%>
<%@ taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>企业日常事务管理系统-首页</title>
<link href="css.css" type="text/css" rel="stylesheet" media="all" />
<script src="menu.js" type="text/javascript"></script>
<style type="text/css">
<!--
.STYLE1 {font-size: 16px}
.STYLE2 {
	color: #CCCCCC;
	font-size: 14px;
}
.STYLE3 {font-size: 14px}
-->
</style>
</head>

<body>
<div id="topexplain">企业日常事务管理系统，为企业内部通信提供最简便的服务！</div>
<div id="topmenu"><img src="images/banner.jpg" width="970" height="147" /></div>
<div id="bookmunu">
<div class="jsmenu" id="jsmenu">
<ul>
  <li class="active"><a href="index.jsp" urn="#default_Info" rel="conmenu">首页</a></li>
  <li class="normal"><a urn="jsmenu1" rel="conmenu" href="GetMessageList">消息列表</a></li>
  <li class="normal"><a urn="jsmenu2" rel="conmenu" href="publishNewMsg.jsp">发布新消息</a></li>
  <li class="normal"><a urn="jsmenu3" rel="conmenu" href="statusRecognise.jsp">身份识别</a></li>
  </ul>
</div>
<div id="conmenu"></div>

</div>
<div id="indexfirst">
<div id="thenew">
<div class="tit">
  <h1>最新消息</h1>
</div>
<div class="STYLE1" id="therecom">
	<%
		MessageDAO messageDAO = MessageDAOFactory.getMessageAOInstance();
		Page pageX = PageUtil.createPage(6, messageDAO.findAllCount(), 1);
		List<Message> messages = messageDAO.findAllMessagee(pageX);
		for(Message message : messages) {
	%>
		<p><a href="GetMessage?messageID=<%=message.getMessageID()%>"><%=message.getMessageTitle()%></a>
		<span class="STYLE2"><%=message.getPublishTime() %></span></p>
 		<p>&nbsp;</p>
	<%
		}
	 %>
</div>
</div>
<div id="menunav">
<div class="tit">
  <h1>员工信息</h1>
</div>
<div id="employee">
	<c:choose>
		<c:when test="${empty sessionScope.employee}">
			没有进行身份识别，请先进行身份识别!
		</c:when>
		<c:otherwise>
			<ul>
			  <li>员工编号：${employee.employeeID}</li>
			  <li>员工姓名：${employee.employeeName}</li>
			  <li>员工性别：${employee.employeeSex ? "男" : "女"}</li>
			  <li>出生日期：${employee.employeeBirth }</li>
			  <li>办公室电话：${employee.employeePhone }</li>
			  <li>住址：${employee.employeePlace }</li>
			  <li>管理层领导：${employee.lead ? '是' : '否'}</li>
			</ul>
		</c:otherwise>
	</c:choose>
</div>
</div>
</div>
<div id="indexsec"></div>
<div class="copyright"><ul><li></li>
<li>企业日常事务管理系统 &nbsp;&copy;2009-2010</li>
</ul>
</div>
<div class="end"></div>
<script type=text/javascript>
startajaxtabs("jsmenu");
var iTab=GetCookie("nets_jsmenu");
	iTab = iTab ? parseInt(iTab):parseInt(Math.random()*5);
	if(iTab!=0) getElement("jsmenu").getElementsByTagName("h1")[iTab].LoadTab();
	iTab++;
	if(iTab>4) iTab=0;
	SetCookie("nets_jsmenu",iTab,365);
function getElement(aID)
{
  return (document.getElementById) ? document.getElementById(aID)
                                   : document.all[aID];
}
</script>
</body>
</html>


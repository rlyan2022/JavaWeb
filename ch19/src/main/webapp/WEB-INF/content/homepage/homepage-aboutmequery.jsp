<%@ page language="java" pageEncoding="UTF-8"%> 
<%@ include file="/common/taglibs.jsp"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
	<title><%=ConstantUtils.restaurantName %>-关于餐店</title>
	<%@ include file="/common/meta.jsp"%>
	<script src="${ctx}/js/form.js" type="text/javascript"></script>
	<link href="${ctx}/css/default_hp.css" type="text/css" rel="stylesheet" />
	<script src="${ctx}/js/jquery.js" type="text/javascript"></script>
	<script src="${ctx}/js/effects/jquery.messager.js"></script>
	<link href="${ctx}/css/sp_lee.css" rel="stylesheet" type="text/css" />
	<link href="${ctx}/css/main_hp.css" rel="stylesheet" type="text/css" />
</head>
<body> 
<%@ include file="/common/hander_item.jsp"%>
<div class="main_container">
<div class="main_left_container">
<div class="left_column"><div class="ico_jpg" style="background-image:url(../images/jpg_active.jpg); width:45px; height:33px;  "></div>
<li>餐店简介</li>
</div>
<div class="left_content">

	<div style="WIDTH: 210px;  font-size:12px; color:#000000;">
		<form id="mainForm" action="homepage!aboutmequery.action" method="post"> 
		  	<table width="100%">
				<%int rowid = 0;%>
				<s:iterator id="am" value="aboutmelist">
					<% if (rowid == 0) {//默认显示第一条记录%> 
						<c:set var="aboutmeId" value="${am.id}" scope="request"/>
					<%}%>
	  				<tr>
	  					<td style="border-bottom:1px #cccccc dashed;"><a href="homepage!aboutmeview.action?aboutmeId=${am.id }" target="m_iframe">${am.title }</a></td>
	  					<!--<td align="right" style="border-bottom:1px #cccccc dashed;"><fmt:formatDate value="${am.createTime}" pattern="MM-dd"/></td>-->
	  				</tr>
					<%rowid=1;%>
	  			</s:iterator>
			</table>
		  </form>
	</div>
</div>
<div class="left_foot"></div>

<!-- 热门菜单 -->
<div class="left_column">
<div class="ico_jpg" style="background-image:url(images/jpg_caishi.jpg); width:45px; height:44px; margin-bottom:-5px; margin-right:-9px;"></div>
<li>热门菜单TOP 10</li></div>
<div class="left_content">
 <table width="100%">
	<tr>
		<td bgcolor="#cccccc"><b>菜单名称</b></td>
		<td bgcolor="#cccccc"><b>菜单名称</b></td> 
	</tr>
	<%int count=0; %>
	<s:iterator id="pmenu" value="submenulist">
		<%if (count%2==0){ %>
		<tr>
		<%}%>
			<td style="border-bottom:1px #cccccc dashed;" title="价格：${pmenu.price }元"> 
				<font color="#DD5800"><%=count+1 %>.</font>
				<a href="${ctx }/homepage/homepage!menuview.action?submenuId=${pmenu.id }" target="m_iframe">${pmenu.name }</a>
			</td> 
		<%if (count%2==1){ %>
		</tr>
		<%} count++;%>		
	</s:iterator>
	 
	<%
		//菜单为奇数时补全html
		if (count%2 > 0) { %>
			<td style="border-bottom:1px #cccccc dashed;" colspan="2"> 
		</tr>
	<%} %>
</table>  
</div>
<div class="left_foot"></div>
<!-- 热门菜单 结束-->

</div>

<div class="main_right_container">
	<br/>
	<iframe width="100%" name="m_iframe" height="450" src="homepage!aboutmeview.action?aboutmeId=${aboutmeId }" 
		allowtransparency="true" frameborder="0" scrolling="yes"></iframe>
	 
</div><!-- main_right_container -->
</div>
<div style="clear:both;"></div>

<%@ include file="/common/footer_item.jsp"%>

</div>
<!--wai_container-->
</body>
</html>


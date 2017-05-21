<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title></title>
	<%@ include file="/common/meta.jsp"%>
	<link href="${ctx}/admin/css/css.css" rel="stylesheet" type="text/css" />

	<style type="text/css">
	<!--
		body {
			margin: 0px;
			background-color:#e3ecf1;
		}
		.admin_top_table{ border-collapse:collapse;}
		.admin_top_table td{border:1px #ffffff solid;}
	-->
	</style>
</head>

<body>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
 	<td width="29%"> <br/>
 		<a href="http://www.tienting.com" target="_blank">
    		<img src="${ctx}/admin/images/biglogo.jpg" width="321" height="75" border="0" />
    	</a>
    </td>
	<td width="71%" valign="bottom">
	    <table class="admin_top_table" width="475" height="57" border="0" cellpadding="3" cellspacing="0">
	       <tr>
	        <td align="center" bordercolor="#CCCCCC"><a href="${ctx}/notice/notice.action" target="mainFrame" class="left-font03">公告管理</a></td>
	        <td align="center" bordercolor="#CCCCCC"><a href="${ctx}/menu/menutype.action" target="mainFrame" class="left-font03">菜单类型</a></td>
	        <td align="center" bordercolor="#CCCCCC"><a href="${ctx}/orderform/orderformnote.action" target="mainFrame" class="left-font03">今日订单</a></td>
	        <td align="center" bordercolor="#CCCCCC"><a href="${ctx}/security/user.action" target="mainFrame" class="left-font03">帐号管理</a></td>
	      </tr>
	      <tr>
	      	<td align="center" bordercolor="#CCCCCC"><a href="${ctx}/restaurant/aboutme.action" target="mainFrame" class="left-font03">餐店简介</a></td>
	      	<td align="center" bordercolor="#CCCCCC"><a href="${ctx}/menu/submenu.action" target="mainFrame" class="left-font03">菜单管理</a></td>
	      	<td align="center" bordercolor="#CCCCCC"><a href="${ctx}/restaurant/assignman.action" target="mainFrame" class="left-font03">送 餐 员</a></td>	        
	      	<td align="center" bordercolor="#CCCCCC"><a href="${ctx}/security/role.action" target="mainFrame" class="left-font03">权限管理</a></td>
	      </tr>
	      <tr>
	      	<td align="center" bordercolor="#CCCCCC"><a href="${ctx}/eatguide/eatguide.action" target="mainFrame" class="left-font03">订餐指南</a></td>
	      	<td align="center" bordercolor="#CCCCCC"><a href="${ctx}/healthdrink/healthdrink.action" target="mainFrame" class="left-font03">饮食文章</a></td>
	        <td align="center" bordercolor="#CCCCCC"><a href="${ctx}/member/member_operate.action" target="mainFrame" class="left-font03">会员管理</a></td> 
	        <td align="center" bordercolor="#CCCCCC"><a href="${ctx}/statistics/salestatistics.action" target="mainFrame" class="left-font03">销售统计</a></td>
	      </tr>
	    </table>
    </td>
  </tr>
</table>
</body>
</html>

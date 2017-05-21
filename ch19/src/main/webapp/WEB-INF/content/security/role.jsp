<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="cn.com.tienting.modules.security.springsecurity.SpringSecurityUtils" %>
<%@ include file="/common/taglibs.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>权限管理</title>
	<%@ include file="/common/meta.jsp"%>
	<link href="${ctx}/css/default.css" type="text/css" rel="stylesheet" />
	<script src="${ctx}/js/jquery.js" type="text/javascript"></script>
	<script  src="${ctx}/js/form.js" type="text/javascript"></script>
	<script type="text/javascript">
	var modifyAction = 'role!input.action';
	var deleteAction = 'role!delete.action';
	</script>
</head>

<body>
<fieldset>
<legend>角色（权限）管理</legend> 
<div id="message"><s:actionmessage theme="mytheme"/></div>
<div id="filter">你好,<%=SpringSecurityUtils.getCurrentUserName()%>.</div>
<form id="mainForm" action="role.action" method="post">
<input type="hidden" name="pageSize" id="pageSize" value="${page.pageSize}"/>
<input type="hidden" name="page.pageNo" id="pageNo" value="${page.pageNo}"/>
<input type="hidden" name="page.orderBy" id="orderBy" value="${page.orderBy}"/>
<input type="hidden" name="page.order" id="order" value="${page.order}" />
<input type="hidden" name="id" id="id" />
<div id="listContent">
<table class="listView">
	<tr>
		<th><a href="javascript:sorting('name','asc')"><b>名称</b></a></th>
		<th><a href="javascript:sorting('cnname','asc')"><b>中文名称</b></a></th>
		<th><b>授权</b></th>
		<th><b>操作</b></th>
	</tr>
	<s:iterator value="page.result">
		<tr title="${remark}">
			<td>${name}</td>
			<td>${cnname}</td>
			<td>${authNames}</td>
			<td style="width:60px;"> 
				<security:authorize ifAnyGranted="ROLE_MANAGE">
					<a href="javascript:modifyItem(${id})">修改</a>&nbsp;
					<a href="javascript:if(confirm('确定要删除【${name}】吗?'))deleteItem(${id})">删除</a>
				</security:authorize>
			</td>
		</tr>
	</s:iterator>
</table>
</div>
<%@ include file="/common/pagingbanner.jsp"%>
</form>
<div>
	<security:authorize ifAnyGranted="ROLE_MANAGE">
		<a href="role!input.action">增加新角色</a>
		<br/>
	</security:authorize>
</div>
<div>
<br/>
	<font color="#006676">
	提示：<br/>
	１、您可以添加多个角色，每个角色都可以授予不同的功能权限；<br/>
	２、系统用户可以属于多个角色，即可以拥护这些角色的所有功能权限；<br/>
	３、功能权限指的是订餐系统的后台管理功能，如：菜单管理、订单管理等。
	
	</font>
</div>
</fieldset>
</body>
</html>
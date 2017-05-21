<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="cn.com.tienting.modules.security.springsecurity.SpringSecurityUtils" %>
<%@ include file="/common/taglibs.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>帐号管理</title>
	<%@ include file="/common/meta.jsp"%>
	<link href="${ctx}/css/default.css" type="text/css" rel="stylesheet" />
	<script src="${ctx}/js/jquery.js" type="text/javascript"></script>
	<script src="${ctx}/js/form.js" type="text/javascript"></script>	
	<script type="text/javascript">	
	var modifyAction = 'user!input.action';
	var deleteAction = 'user!delete.action';    
	</script>
</head>

<body>
<fieldset>
<legend>帐号（后台登录用户）管理</legend> 
<div id="message"><s:actionmessage theme="mytheme"/></div>
<div id="filter">
	<form action="user!search.action" method="post">
		你好,<%=SpringSecurityUtils.getCurrentUserName()%>.&nbsp;&nbsp;
		登录名: <input type="text" name="filter_EQ_loginName" value="${param['filter_EQ_loginName']}"  size="8"/>
		姓名或Email: <input type="text" name="filter_LIKE_name__email" value="${param['filter_LIKE_name__email']}" size="8"/>
		<input type="submit" value="搜索" />
	</form>
</div> 

<div id="listContent">
	<form id="mainForm" action="user.action" method="post">
	<input type="hidden" name="pageSize" id="pageSize" value="${page.pageSize}"/>
	<input type="hidden" name="page.pageNo" id="pageNo" value="${page.pageNo}"/>
	<input type="hidden" name="page.orderBy" id="orderBy" value="${page.orderBy}"/>
	<input type="hidden" name="page.order" id="order" value="${page.order}" />
	<input type="hidden" name="id" id="id" />
	<table class="listView">
		<tr>
			<th><a href="javascript:sorting('loginName','asc')"><b>登录名</b></a></th>
			<th><a href="javascript:sorting('name','asc')"><b>姓名</b></a></th>
			<th><a href="javascript:sorting('email','asc')"><b>邮箱地址</b></a></th>
			<th><a href="javascript:sorting('enabled','asc')"><b>状态</b></a></th>
			<th><b>角色</b></th>
			<th><b>登录次数</b></th>
			<th><b>最后登录时间</b></th>
			<security:authorize ifAnyGranted="USER_MANAGE">
				<th><b>操作</b></th>
			</security:authorize>
		</tr>
	
		<s:iterator value="page.result">
		<tr>
			<td>${loginName}&nbsp;</td>
			<td>${name}&nbsp;</td>
			<td>${email}&nbsp;</td>		
			<td><s:if test="enabled==1">启用</s:if><s:elseif test="enabled==0">停用</s:elseif>&nbsp;</td>
			<td>${roleCnNames}&nbsp;</td>
			<td>${logincount}&nbsp;</td>
			<td><fmt:formatDate value="${finallytime}" pattern="yyyy年MM月dd日   HH:mm"/>  </td>
			<security:authorize ifAnyGranted="USER_MANAGE">
				<td><s:if test="id != 1">		
						<a href="javascript:modifyItem(${id})">修改</a>、			
						<a href="javascript:if(confirm('确定要删除该帐号吗?'))deleteItem(${id})">删除</a>		
					</s:if>		
				</td>
			</security:authorize>
		</tr>
		</s:iterator>
	</table>
	</form>
</div>
<%@ include file="/common/pagingbanner.jsp" %>
<div>
	<security:authorize ifAnyGranted="USER_MANAGE">
		<br/>
		<a href="user!input.action">增加新用户</a>
	</security:authorize>
</div>
<br/>
<div>
	<font color="#006676">
		提示：<br/>
		１、网上订餐系统分前台和后台功能模块，后台功能模块必须是系统用户（帐号）才能访问；<br/>
		２、超级管理员帐号不能删除，如果您是第一次使用网上订餐系统，请尽快通过右上角的'修改密码'功能修改该帐号的密码；<br/>
		３、您可以添加多个管理员或者用户（角色）等，同时可通过系统管理的'角色管理'功能指定管理员、用户的功能权限。
	</font>

</div>
</fieldset>
</body>
</html>
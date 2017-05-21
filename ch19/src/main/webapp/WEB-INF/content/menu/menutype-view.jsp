<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>录入菜单类型信息</title>
	<%@ include file="/js/widgets/calendar/calendar.jsp"%>
	<link href="${ctx}/css/default.css" type="text/css" rel="stylesheet" />
	<link href="${ctx}/js/validate/jquery.validate.css" type="text/css" rel="stylesheet" />		
	<script src="${ctx}/js/jquery.js" type="text/javascript"></script>
	<script src="${ctx}/js/validate/jquery.validate.js" type="text/javascript"></script>
	<script src="${ctx}/js/validate/messages_cn.js" type="text/javascript"></script>

</head>

<body>
<fieldset>
<legend>查看菜单类型明细 - ${name}</legend>
<div id="message"><s:actionmessage theme="mytheme"/></div>

<div id="inputContent">
	<form id="inputForm" action="menutype.action" method="post">
	<input type="hidden" id="id" name="id" value="${id}" />
	<table class="inputView">
		<tr>
			<td align="right">类型编号：</td>
			<td>${code}</td>
		</tr>
		<tr>
			<td align="right">类型名称：</td>
			<td>${name}</td>
		</tr>
		<tr>
			<td nowrap="nowrap" align="right">类型排序：</td>
			<td>${serial}</font></td>
		</tr>
	
		<tr>
			<td nowrap="nowrap" align="right">使用状态：</td>
			<td>
				<c:if test="${status=='1'}">正常使用</c:if>
				<c:if test="${status=='2'}">暂停使用</c:if>
			</td>
		</tr>
		<tr>
			<td align="right">编辑人：</td>
			<td>${editor.name}</td>
		</tr>
		<tr>
			<td align="right">编辑时间：</td>
			<td>${edittime}</td>
		</tr>  
		<tr>
			<td nowrap="nowrap" align="right">类型描述：</td>
			<td>${description}</textarea></td>
		</tr>
		<tr>
			<td colspan="2" align="center" height="50">　
				<input type="button" value=" 返回  " onclick="history.back()" />
			</td>
		</tr>
	</table>
	</form>
</div>
</fieldset>
</body>

</html>

<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>送餐员信息管理</title>
	<link href="${ctx}/css/default.css" type="text/css" rel="stylesheet" />
	<link href="${ctx}/js/validate/jquery.validate.css" type="text/css" rel="stylesheet" />		
</head>
<body>
<div> 
	<fieldset style="width:500">
		<legend>查看送餐员信息明细－${name}</legend>
    <div id="inputContent">
    <form id="inputForm" action="assignman.action" method="post" >
	<input type="hidden" name="id" value="${id}" />
	<table class="inputView">
		<tr>
			<td nowrap="nowrap" align="right">姓名：</td>
			<td>${name}</td>
		</tr>
		<tr>
			<td nowrap="nowrap" align="right">编号：</td>
			<td>${code}</td>
		</tr>
		<tr>
			<td align="right">性别：</td>
			<td>
				<c:if test="${sex eq 1}">男</c:if>
				<c:if test="${sex eq 2}">女</c:if>
			</td>
		</tr>
		
		<tr> 
			<td align="right">照片：</td>
			<td>
				<s:if test="photo != null">
					<img src="${ctx}/upload/assignman/${photo}"/>	
				</s:if>
				<s:else>
					未上传照片
				</s:else>			
			</td>
		</tr>
		<tr>
			<td nowrap="nowrap" align="right">联系电话：</td>
			<td>${phone}</td>
		</tr>
		<tr>
			<td nowrap="nowrap" align="right">邮箱地址：</td>
			<td>${email}</td>
		</tr>	
		<tr>
			<td nowrap="nowrap" align="right">编辑人：</td>
			<td>${editor.name}</td>
		</tr>
		<tr>
			<td nowrap="nowrap" align="right">编辑时间：</td>
			<td>${edittime}</td>
		</tr>
		<tr>
			<td nowrap="nowrap" align="right">备注：</td>
			<td>${remark}</td>
		</tr>	
		<tr>
			<td colspan="2" align="center" height="50">　
				<input type="button" value=" 返回 " onclick="history.back()" />
			</td>
		</tr>
	</table>
	</form>
	</div>
	</fieldset>
</div>
</body>
</html>

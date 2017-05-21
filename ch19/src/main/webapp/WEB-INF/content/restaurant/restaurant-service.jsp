<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>餐店基本信息管理</title>
	<link href="${ctx}/css/default.css" type="text/css" rel="stylesheet" />
	<link href="${ctx}/js/validate/jquery.validate.css" type="text/css" rel="stylesheet" />		
	<script src="${ctx}/js/jquery.js" type="text/javascript"></script>
	<script src="${ctx}/js/validate/jquery.validate.js" type="text/javascript"></script>
	<script src="${ctx}/js/validate/messages_cn.js" type="text/javascript"></script>
	<script src="${ctx}/js/widgets/kindeditor/kindeditor.js" type="text/javascript" charset="utf-8" ></script>
	
	<script type="text/javascript">
		KE.show({
		id:'service',
		skinType:'tinymce',
		items : ['undo', 'redo', 'fontname', 'fontsize', 'textcolor', 'bgcolor', 'bold', 'italic', 'underline',
	        'removeformat', 'justifyleft', 'justifycenter', 'justifyright', 'insertorderedlist',
	        'insertunorderedlist'] 
		});
		
		$(document).ready(function(){
			$("#service").focus();
			$("#inputForm").validate({
				 rules: {
					service: { 
	        			required: true,
	        			maxlength: 200
	    			}     		
				}
			});
		});
	</script>
</head>
<body>
<div id="listContent"> 
	<fieldset>
		<legend><s:if test="service == null">新增餐店温馨提示信息</s:if><s:else>修改餐店温馨提示信息－${name}</s:else></legend>
    <div id="inputContent">
    <form id="inputForm" action="restaurant!saveservice.action" method="post" enctype="multipart/form-data">
	<input type="hidden" name="id" value="${id}" />
	<table class="inputView" style="margin-left:20px;">
		<tr height="30">
			<td nowrap="nowrap" align="right">店名：</td>
			<td>${name}</td>
		</tr>
		<tr height="30">
			<td nowrap="nowrap" align="right">地址：</td>
			<td>${address}</td>
		</tr>
		 
		<tr>
			<td nowrap="nowrap" align="right">温馨提示：</td>
			<td><textarea id="service" name="service" style="width:600px;height:250px;visibility:hidden;" class="required">${service}</textarea></td>
		</tr>	
		<tr>
			<td colspan="2" align="center" height="50">
				<input type="submit" value=" 提交 " />&nbsp;&nbsp;
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

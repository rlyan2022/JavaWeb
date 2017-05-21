<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>录入菜单类型信息</title>
	<%//@ include file="/js/widgets/calendar/calendar.jsp"%>
	<link href="${ctx}/css/default.css" type="text/css" rel="stylesheet" />
	<link href="${ctx}/js/validate/jquery.validate.css" type="text/css" rel="stylesheet" />		
	<script src="${ctx}/js/jquery.js" type="text/javascript"></script>
	<script src="${ctx}/js/validate/jquery.validate.js" type="text/javascript"></script>
	<script src="${ctx}/js/validate/messages_cn.js" type="text/javascript"></script>

	<script type="text/javascript">
	$(document).ready(function(){
		$("#name").focus();
		$("#inputForm").validate(
		{
			rules:{
				code: { 
       				required: true,
       				remote:"menutype!checkMenuTypeCodeUnique.action?oldCode="+encodeURIComponent('${code}')+"&t="+Math.random()
   				},
				name: { 
       				required: true,
       				remote:"menutype!checkMenuTypeNameUnique.action?oldName="+encodeURIComponent('${name}')+"&t="+Math.random()
   				},
   				sequence:{
   					required:true,
   					digits:true,
   					min:1,
   					max:255
   				},
	    		description: {
	    			required: true,
	    			maxlength:400
	    		}
			},
			messages:{
				code: { 
       				required: "请输入菜单类型编号!",
       				remote:"该菜单类型编号已被使用,请重新输入!"
   				},
				name: { 
       				required: "请输入菜单类型名称!",
       				remote:"该菜单类型名称已被使用,请重新输入!"
   				},
   				sequence:{
   					required:"请输入菜单排序!",
   					digits:"请输入合法数字(如：1-255)!"
   				},
   				description:{
	    			maxlength: "摘要请控制在400个字符(200个汉字)内"
	    		} 
			}
		}
		);
	});
	</script>
</head>

<body>
<fieldset>
<legend><s:if test="id == null">新增菜单类型</s:if><s:else>修改菜单类型 - ${name}</s:else></legend>
<div id="message"><s:actionmessage theme="mytheme"/></div>

<div id="listContent">
	<form id="inputForm" action="menutype!save.action" method="post">
	<input type="hidden" id="id" name="id" value="${id}" />
	<table class="inputView">
		<tr>
			<td align="right">类型编号：</td>
			<td><input type="text" id="code" name="code" size="15" maxlength="20" value="${code}"/><font color="red">*</font></td>
		</tr>
		<tr>
			<td align="right">类型名称：</td>
			<td><input type="text" id="name" name="name" size="15" maxlength="40" value="${name}"/><font color="red">*</font></td>
		</tr>
		<tr>
			<td nowrap="nowrap" align="right">类型排序：</td>
			<td><input type="text" name="sequence" size="5" maxlength="3" value="${sequence}" />
				<font color="red">*</font><font color="#006676">请输入0-255之间的整数，数字越小排序越前</font></td>
		</tr>
	
		<tr>
			<td nowrap="nowrap" align="right">使用状态：</td>
			<td>
				<select name="status">
					<option value="1" <c:if test="${status=='1'}">selected</c:if>>正常可用</option>
					<option value="2" <c:if test="${status=='2'}">selected</c:if>>暂停停用</option>
				</select>
				<font color="#006676">状态为‘暂停使用’的菜单不在前台菜单栏显示</font>
			</td>
		</tr>
		<s:if test="id != null">
			<tr>
				<td align="right">编辑人：</td>
				<td>${editor.name}</td>
			</tr>
			<tr>
				<td align="right">编辑时间：</td>
				<td>${edittime}</td>
			</tr>
		</s:if>

		<tr>
			<td nowrap="nowrap" align="right">类型描述：</td>
			<td><textarea id="content" name="description" title="类型描述请控制在200字以内" style="width:500px;height:100px;" >${description}</textarea></td>
		</tr>
		<tr>
			<td colspan="2" align="center" height="50">
				<input type="submit" value=" 提 交  " />&nbsp; &nbsp; 
				<input type="button" value=" 取 消  " onclick="history.back()" />
			</td>
		</tr>
	</table>
	</form>
</div>
</fieldset>
</body>

</html>

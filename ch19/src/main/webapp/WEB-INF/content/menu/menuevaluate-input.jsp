<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>菜单评价信息修改</title>
	<%//@ include file="/js/widgets/calendar/calendar.jsp"%>
	<link href="${ctx}/css/default.css" type="text/css" rel="stylesheet" />
	<link href="${ctx}/js/validate/jquery.validate.css" type="text/css" rel="stylesheet" />		
	<script src="${ctx}/js/jquery.js" type="text/javascript"></script>
	<script src="${ctx}/js/validate/jquery.validate.js" type="text/javascript"></script>
	<script src="${ctx}/js/validate/messages_cn.js" type="text/javascript"></script>

	<script type="text/javascript">
	$(document).ready(function(){
		$("#content").focus();
		$("#inputForm").validate(
		{
			rules:{　
	    		content: {
	    			required: true,
	    			maxlength:400
	    		}
			},
			messages:{
   				content:{
	    			maxlength: "摘要请控制在200个字符串以内。"
	    		} 
			}
		}
		);
	});
	</script>
</head>

<body>
<fieldset>
<legend>菜单评价信息修改</legend>
<div id="message"><s:actionmessage theme="mytheme"/></div>

<div id="listContent">
	<form id="inputForm" action="menuevaluate!save.action" method="post">
	<input type="hidden" id="id" name="id" value="${id}" />
	<table class="inputView">
		<tr>
			<td align="right">菜单名称：</td>
			<td>${submenu.name }</td>
		</tr>
		<tr>
			<td align="right">会员帐号：</td>
			<td>${member.loginCode }</td>
		</tr>
		
		<tr>
			<td nowrap="nowrap" align="right">是否显示：</td>
			<td>
				<select name="status">
					<option value="1" <c:if test="${status=='1'}">selected</c:if>>显示</option>
					<option value="2" <c:if test="${status=='2'}">selected</c:if>>不显示</option>
				</select>
				<font color="#006676">状态为‘暂停使用’的菜单不在前台菜单栏显示</font>
			</td>
		</tr>
		<tr>
			<td align="right">评价时间：</td>
			<td>${evaluateTime}</td>
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
			<td nowrap="nowrap" align="right">评价内容：</td>
			<td><textarea id="content" name="content" title="评价内容请控制在200字以内" style="width:500px;height:100px;" >${content}</textarea></td>
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

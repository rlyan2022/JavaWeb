<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>角色管理</title>	
	<%@ include file="/common/meta.jsp"%>
	<link href="${ctx}/css/default.css" type="text/css" rel="stylesheet" />
	<link href="${ctx}/js/validate/jquery.validate.css" type="text/css" rel="stylesheet" />
	<script src="${ctx}/js/jquery.js" type="text/javascript"></script>
	<script src="${ctx}/js/validate/jquery.validate.js" type="text/javascript"></script>
	<script src="${ctx}/js/validate/messages_cn.js" type="text/javascript"></script>

	<script>	
	$(document).ready(function(){
	
		<s:if test="id == null">//进入添加页面后默认选上的项
			var authorityIds = "1,11,12,28,29";
			var str = authorityIds.split(",");
			for (var i=0; i < str.length; i++){
				$("input[type='checkbox']").each(function() {
					if( this.value==str[i]){
						this.checked=true;
					}
				});
			}
		</s:if>
		<s:else>//进入修改页面获取数据库的值。
			var authorityIds = "${authorityIds}";
			var str = authorityIds.split(",");
			for (var i=0; i < str.length; i++){
				$("input[type='checkbox']").each(function() {
					if( this.value==str[i]){
						this.checked=true;
					}
				});
			}
		</s:else>
		
		//全选/取消
		$("#selectall").click(function(){
			if($("input[type='checkbox']").attr("checked")==true){
				$("input[type='checkbox']").attr("checked",false);
			}else{
				$("input[type='checkbox']").attr("checked",true);
			}
		});
		
		//聚焦第一个输入框	
		$("#name").focus();
		//为inputForm注册validate函数
		$("#inputForm").validate({
			rules: { 
				name: { 
	    			required: true, 
	    			remote: "role!checkRoleName.action?orgRoleName="+encodeURIComponent('${name}')+"&a="+Math.random(),
	    			maxlength:40
				},
				cnname: {
					required: true,
					maxlength:20
				}       	
			},
			messages: {
				name: {
					remote: "此角色已存在"
				}
			},
			success: function(label) {
				label.html("&nbsp;").addClass("checked");
	        }			
		});
	});
	</script>
</head>
<body>
<br />
<fieldset>
<legend><s:if test="id == null">创建</s:if><s:else>修改</s:else>角色（权限）</legend>
<div id="message"><s:actionmessage theme="mytheme"/></div>

<div id="inputContent">
	<form id="inputForm" action="role!save.action" method="post">
	<input type="hidden" name="id" value="${id}" />
	<table class="inputView">
		<tr>
			<td  align="right">角色名：</td>
			<td><input type="text" name="name" size="30" value="${name}" class="required"/>
				<font color="#006676">例：manager、user</font>
			</td>
		</tr>
		<tr>
			<td  align="right">角色中文名：</td>
			<td><input type="text" name="cnname" size="30" value="${cnname}" class="required"/>
				<font color="#006676">例：管理员、用户</font>
			</td>
		</tr>
		<tr>
			<td  align="right">备注：</td>
			<td><input type="text" name="remark" size="30" value="${remark}" />
				<font color="#006676">描述该角色用户的功能权限</font>
			</td>
		</tr>
		<tr>
			<td valign="top" align="right">权限：</td>
			<td>
			<input type="button" class="inputBox" name="selectall" id="selectall" value="全选/取消全选"/><br />
			<s:iterator value="allAuthority">
				${addBlank}<input type="checkbox" name="checkedAuthIds" value="${id}"/>${displayName}<br />
			</s:iterator>
			</td>
		</tr>			
		<tr>
			<td colspan="2">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;			
				<input type="submit" value="提 交" />&nbsp;&nbsp; 
				<input type="button" value="取 消" onclick="history.back()"/>
			</td>
		</tr>
	</table>
	</form>
</div>
<div>
	<font color="#006676">
	提示：<br/>
	１、添加/修改角色，可以同时指定该角色的功能权限；<br/>
	２、功能权限可以通过菜单>系统管理>权限管理 功能添加。　
	
	</font>
</div>
</fieldset>
</body>
</html>
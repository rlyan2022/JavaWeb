<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
	<title>${title}</title>
	<%@ include file="/common/meta.jsp"%> 
	<link href="${ctx}/css/default.css" type="text/css" rel="stylesheet" />
	<link href="${ctx}/js/validate/jquery.validate.css" type="text/css" rel="stylesheet" />		
	<script src="${ctx}/js/jquery.js" type="text/javascript"></script>
	<script src="${ctx}/js/validate/jquery.validate.js" type="text/javascript"></script>
	<script src="${ctx}/js/validate/messages_cn.js" type="text/javascript"></script>
	<script src="${ctx}/js/widgets/kindeditor/kindeditor.js" type="text/javascript" charset="utf-8" ></script>
	
	<script type="text/javascript">
		KE.show({
			id:'content'
		});

		$(document).ready(function(){
			$("#title").focus();
			$("#inputForm").validate({
				 rules: { 
					title: { 
	        			required: true
	    			},
	    			summary: {
	    				maxlength:200
	    			}   		
				}
			});
		});
		function checkForm(){		
			
		}
	</script>
</head>
<body>
<fieldset>
	<legend>会员信息明细</legend>
<div id="message"><s:actionmessage theme="mytheme"/></div>
<div id="listContent">
	<form id="inputForm" name="inputForm" action="member_operate.action" method="post">
	<input type="hidden" id="id" name="id" value="${id}" />
	<table class="inputView">
		<tr height="20">
			<td nowrap="nowrap" align="right" >用户帐号：</td>
			<td>${loginCode}</td>
		</tr>	
		<tr height="20">
			<td nowrap="nowrap" align="right">姓名：</td>
			<td>${memberName}</td>
		</tr>
		<tr height="20">
			<td nowrap="nowrap" align="right">证件号码：</td>
			<td>${idcardNo}</td>
		</tr>
		<tr height="20">
			<td nowrap="nowrap" align="right">性别：</td>
			<td>
				<c:if test="${sex eq 1 }">女</c:if>
				<c:if test="${sex eq 2 }">男</c:if>	
			</td>
		</tr>
		<tr height="20">
			<td nowrap="nowrap" align="right">头像：</td>
			<td>
				<s:if test="photo==null">
					未上传头像
				</s:if> 
				<s:else>
					<img src="${ctx}/upload/member/${photo}"/>
				</s:else>
			</td>
		</tr>
		<tr height="20">
			<td nowrap="nowrap" align="right">使用状态：</td>
			<td>
				<c:if test="${status eq 1 }">正常使用</c:if>
				<c:if test="${status eq 2 }">停用</c:if>
			</td>
		</tr>
		<tr height="20">
			<td nowrap="nowrap" align="right">送餐地址：</td>
			<td>${assignAddress}</td>
		</tr>
		<tr height="20">
			<td nowrap="nowrap" align="right">送餐电话：</td>
			<td>${relationPhone}</td>
		</tr>
		<tr height="20">
			<td nowrap="nowrap" align="right">手机号码：</td>
			<td>${mobile}</td>
		</tr>	
		 	
			<tr height="20">
				<td nowrap="nowrap" align="right">餐币：</td>
				<td>${mealCurrency} 元</td>
			</tr>
			<tr height="20">
				<td nowrap="nowrap" align="right">可用积分：</td>
				<td>${integral} 个</td>
			</tr>
			<tr height="20">
				<td nowrap="nowrap" align="right">历史积分：</td>
				<td>${historyIntegral} 个</td>
			</tr> 
			<tr height="20">
				<td nowrap="nowrap" align="right">注册时间：</td>
				<td>${registerTime}</td>
			</tr>
			<tr height="20">
				<td nowrap="nowrap" align="right">注册IP：</td>
				<td>${loginIp}</td>
			</tr>
			<tr height="20">
				<td nowrap="nowrap" align="right">登录次数：</td>
				<td>${loginCount}</td>
			</tr> 
			<tr height="20">
				<td nowrap="nowrap" align="right">最后登录时间：</td>
				<td>${finallyTime }</td>
			</tr>
			<tr height="20">
				<td nowrap="nowrap" align="right">编辑人：</td>
				<td>${editor.name }</td>
			</tr>
			<tr height="20">
				<td nowrap="nowrap" align="right">编辑时间：</td>
				<td>${edittime }</td>
			</tr>

		<tr height="20">
			<td nowrap="nowrap" align="right">备注：</td>
			<td>${remark}</td>
		</tr>	
		<tr height="20">
			<td nowrap="nowrap" align="right">会员操作记录：</td>
			<td>${operateRecord}</td>
		</tr>	

		<tr height="50">
			<td colspan="2" align="center" > 
				<input type="button" value="返 回" onclick="history.back()" />
			</td>
		</tr>
	</table>
	</form>
</div> 
</fieldset>
</body>
</html>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
	<title>${title}</title>
	<%@ include file="/common/meta.jsp"%> 
	<link href="${ctx}/css/default.css" type="text/css" rel="stylesheet" />
	
	<script type="text/javascript">
		
	</script>
</head>
<body> 
	<br/>
	<div id="message" style="margin-left: 30px;"><s:actionmessage theme="mytheme"/></div>
	<div style="margin-left: 30px;"><b><font color="#006676">您的个人信息明细</font></b></div>
<div id="listContent" style="margin-left: 30px;">
	<form id="inputForm" name="inputForm" action="member.action" method="post">
	<input type="hidden" id="id" name="id" value="${id}" />
	<table >
		<tr height="20">
			<td nowrap="nowrap" align="right" ><b>用户帐号：</b></td>
			<td>&nbsp;${loginCode}</td>
		</tr>	
		<tr height="20">
			<td nowrap="nowrap" align="right"><b>姓名：</b></td>
			<td>&nbsp;${memberName}</td>
		</tr>
		<tr height="20">
			<td nowrap="nowrap" align="right"><b>证件号码：</b></td>
			<td>&nbsp;${idcardNo}</td>
		</tr>
		<tr height="20">
			<td nowrap="nowrap" align="right"><b>性别：</b></td>
			<td>
				<c:if test="${sex eq 1 }">女</c:if>
				<c:if test="${sex eq 2 }">男</c:if>	
			</td>
		</tr>
		<tr height="20">
			<td nowrap="nowrap" align="right"><b>头像：</b></td>
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
			<td nowrap="nowrap" align="right"><b>使用状态：</b></td>
			<td>&nbsp;
				<c:if test="${status eq 1 }">正常使用</c:if>
				<c:if test="${status eq 2 }">停用</c:if>
			</td>
		</tr>
		<tr height="20">
			<td nowrap="nowrap" align="right"><b>送餐地址：</b></td>
			<td>&nbsp;${assignAddress}</td>
		</tr>
		<tr height="20">
			<td nowrap="nowrap" align="right"><b>送餐电话：</b></td>
			<td>&nbsp;${relationPhone}</td>
		</tr>
		<tr height="20">
			<td nowrap="nowrap" align="right"><b>手机号码：</b></td>
			<td>&nbsp;${mobile}</td>
		</tr>	
		 	
			<tr height="20">
				<td nowrap="nowrap" align="right"><b>餐币：</b></td>
				<td>&nbsp;${mealCurrency} 元</td>
			</tr>
			<tr height="20">
				<td nowrap="nowrap" align="right"><b>可用积分：</b></td>
				<td>&nbsp;${integral} 个</td>
			</tr>
			<tr height="20">
				<td nowrap="nowrap" align="right"><b>历史积分：</b></td>
				<td>&nbsp;${historyIntegral} 个</td>
			</tr> 
			<tr height="20">
				<td nowrap="nowrap" align="right"><b>注册时间：</b></td>
				<td>&nbsp;${registerTime}</td>
			</tr>
			<tr height="20">
				<td nowrap="nowrap" align="right"><b>注册IP：</b></td>
				<td>&nbsp;${loginIp}</td>
			</tr>
			<tr height="20">
				<td nowrap="nowrap" align="right"><b>登录次数：</b></td>
				<td>&nbsp;${loginCount}</td>
			</tr> 
			<tr height="20">
				<td nowrap="nowrap" align="right"><b>上次登录时间：</b></td>
				<td>&nbsp;<font color="bule">${finallyTime }</font></td>
			</tr>
			<tr height="20">
				<td nowrap="nowrap" align="right"><b>上次登录IP：</b></td>
				<td>&nbsp;<font color="bule">${loginIp }</font></td>
			</tr>
			<tr height="20">
				<td nowrap="nowrap" align="right"><b>编辑人：</b></td>
				<td>&nbsp;${editor.name }</td>
			</tr>
			<tr height="20">
				<td nowrap="nowrap" align="right"><b>编辑时间：</b></td>
				<td>&nbsp;${edittime }</td>
			</tr>

		<tr height="20">
			<td nowrap="nowrap" align="right"><b>备注：</b></td>
			<td>&nbsp;${remark}</td>
		</tr>	
		<tr height="20">
			<td nowrap="nowrap" align="right"><b>您的操作记录：</b></td>
			<td>&nbsp;${operateRecord}</td>
		</tr>	
		<!-- 
		<tr height="50">
			<td colspan="2" align="center" > 
				<input type="button" value="返 回" onclick="history.back()" />
			</td>
		</tr> -->
	</table>
	</form>
</div>  
</body>
</html>
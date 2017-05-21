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
		$(document).ready(function(){
			$("#integral").focus();
			$("#inputForm").validate({
				 rules: { 
					integral: { 
	        			required: true,
						number: true
	    			},
	    			mealCurrency: {
	    				required: true,
						number: true
	    			}   		
				}
			});
		});
	</script>
</head>
<body>
<fieldset>
	<legend><s:if test="id == null">增加积分兑换餐币比率</s:if>	<s:else>修改积分兑换餐币比率</s:else></legend>
<div id="message"><s:actionmessage theme="mytheme"/></div>
<div id="listContent">
	<form id="inputForm" name="inputForm" action="integralset!save.action" method="post">
	<input type="hidden" id="id" name="id" value="${id}" />
	<table class="inputView">
		<tr>
			<td nowrap="nowrap" align="right">兑换积分：</td>
			<td><input type="text" name="integral" size="5" value="${integral}" maxlenght="7"/>(<font color="#006676">请输入整数，如：100</font>)</td>
		</tr>	
		<tr>
			<td nowrap="nowrap" align="right">生成餐币：</td>
			<td><input type="text" name="mealCurrency" size="5" value="${mealCurrency}" maxlenght="5"/>个(<font color="#006676">请输入整数，如：1</font>)</td>
		</tr>　
		<tr>
			<td nowrap="nowrap" align="right">是否使用：</td>
			<td>
				<select name="status">
					<option value="1" <c:if test="${status eq 1 }">selected</c:if>>使用</option>
					<option value="2" <c:if test="${status eq 2 }">selected</c:if>>不使用</option>
				</select>		
			</td>
		</tr>
		
		<tr>
			<td nowrap="nowrap" align="right">备注：</td>
			<td><textarea name="remark" style="width:400px;height:60px;" >${remark}</textarea></td>
		</tr>	
		<tr>
			<td colspan="2" align="center">
				<input type="submit" value=" 提 交 " />&nbsp; 
				<input type="button" value=" 取 消 " onclick="history.back()" />
			</td>
		</tr>
	</table>
	</form>
</div>
	<div>
		<font color="#006676">
			提示：<br/>
			１、会员积分的来源：会员成功订餐，则支付的现金值转换成会员的积分值(1元现金＝１个积分)；<br/>
			２、餐币的作业：会员的餐币可以代替现金订餐付款；<br/>
			３、积分与餐币的比率：如：500积分兑10个餐币、1000积分对21个餐币等。<br/>
			４、积分兑换餐币的可用记录同一时段只能为一条记录。	
		</font>
	</div>
</fieldset>
</body>
</html>
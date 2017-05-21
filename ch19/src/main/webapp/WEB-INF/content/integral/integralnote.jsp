<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net/el" prefix="display" %>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ include file="/common/meta.jsp"%>
<head>
	<title>积分兑换餐币</title>
	<link href="${ctx}/css/default.css" type="text/css" rel="stylesheet" />
	<script src="${ctx}/js/jquery.js" type="text/javascript"></script>
	<script src="${ctx}/js/effects/jquery.messager.js"></script>
	<script  src="${ctx}/js/form.js" type="text/javascript"></script>
	<link href="${ctx}/js/validate/jquery.validate.css" type="text/css"	rel="stylesheet" />
	<script type="text/javascript">	 
			
		function checkForm(){
			var i,myObj;
			myObj=document.getElementsByName("radioId");
			for(i=0;i<myObj.length;i++){
				if(myObj[i].checked)break;
			};
										 
			var inputValue = document.inputForm.elements["inputValue"].value;
			
			if (inputValue != "") {
				if (isNaN(inputValue)) {
					alert("请输入有效的数值（整数）！");
					return false;
				}
				if (inputValue < 1) {
					alert("请输入大于0的整数！");
					return false;
				}
			}
		
			if (i>=myObj.length && inputValue=="") {
				alert("请选择您要兑换餐币的选项\n或者\n输入需要兑换餐币的整数倍！");
				return false;
			}
			
			document.inputForm.action = "integralnote!save.action";
			document.inputForm.submit();
		}
	</script>
	<style type="text/css">
		<!--
		.STYLE1 {
			color: #DD5800;
			font-weight: bold;
		}
		//-->
    </style>
</head>
<body>
<fieldset style="width: 98%;">
	<legend>积分兑换餐币 &nbsp;&nbsp;&nbsp;<a href="integralnote!memberQuery.action">积分兑餐币记录查询</a></legend>
	<br/>
<div id="message" style="margin-left: 50px;"><s:actionmessage theme="mytheme"/></div>

<form id="inputForm" name="inputForm" action="integralnote.action" method="post">
<!-- 隐藏关于我们内容   ID -->
<input type="hidden" name="id" id="id" />  

<div style="margin-left: 50px;">
	<!-- 会员积分、餐币 -->
	<input type="hidden" name="memberIntegral" value="${memberIntegral }"/>
	<!--<input type="hidden" name="memberMealCurrency" value="${memberMealCurrency }"/>-->

	<table>
		<tr>
			<td>您的帐号：<span class="STYLE1">${memberCode }</span></td>
			<td></td>
		</tr>
		<tr>
			<td>您当前可兑换的积分：</td>
			<td><span class="STYLE1">${memberIntegral }</span></td>
		</tr>
		<tr>
			<td>您当前可用的餐币：</td>
			<td><span class="STYLE1">${memberMealCurrency }</span></td>
		</tr>
	</table>
	<br/>
	<c:if test="${empty integralsetlist}">
		抱歉！积分兑换餐币功能暂未开放，暂不能兑换餐币！
	</c:if>
 	<s:iterator id="row" value="integralsetlist">
 		<!-- 开通兑换的积分 --> 
 		<input type="hidden" name="integral" value="${row.integral }"/>
 		<input type="hidden" name="mealCurrency" value="${row.mealCurrency }"/>
		
		<!-- 生成4个积分选项 -->
		<table>
			<tr>
				<td colspan="2" height="30">
					尊敬的客户只要您的积分余额等于或超过<span class="STYLE1">${row.integral }</span>积分，即可以将积分兑换成餐币，
					每<span class="STYLE1">${row.integral }</span>积分可以兑换<span class="STYLE1">${row.mealCurrency }</span>个餐币
				</td>
			</tr>
			<c:forEach begin="1" end="4" step="1" var="count">
				<c:choose>
					<c:when test="${count%2==1}">
						<tr>
							<td>
								<input type="radio" name="radioId" value="${count}"/>
								兑换<span class="STYLE1">${count*row.mealCurrency }</span>个餐币（需要<span class="STYLE1">${count*row.integral }</span>积分）
							</td>
					</c:when>		
								
					<c:otherwise>
							<td>
								<input type="radio" id="radioId" name="radioId" value="${count}"/>
								兑换<span class="STYLE1">${count*row.mealCurrency }</span>个餐币（需要<span class="STYLE1">${count*row.integral }</span>积分）
							</td>
						</tr>
					</c:otherwise>
				</c:choose> 				
			</c:forEach>
			
			<c:choose>
				<c:when test="${memberIntegral/row.integral>1 }">
					<tr>
						<td colspan="2" height="30">
							兑换<input type="text" id="inputValue" name="inputValue" size="3" maxlength="3"/>个餐币（<span class="STYLE1">${row.mealCurrency }</span>的整倍数）,
							您当前积分余额最多可兑换<span class="STYLE1"> ${(memberIntegral/row.integral)*row.mealCurrency }</span>个餐币
						</td>
					</tr>
					<tr>
						<td colspan="2" height="30">
							<input type="button" value="兑 换" onclick="return checkForm();"/>
						</td>
					</tr>
				</c:when>
				<c:otherwise>
					<tr>
						<td colspan="2" height="30">
							兑换<input type="text" id="inputValue" name="inputValue" size="3" maxlength="3"/>元餐币（<span class="STYLE1">${row.mealCurrency }</span>的整倍数）,
							您当前积分余额最多可兑换<span class="STYLE1">0	</span>元餐币
						</td>
					</tr>
					<tr>
						<td colspan="2" height="30">
							<input type="button" value="兑 换" disabled="disabled"/>
						</td>
					</tr>
				</c:otherwise>
			</c:choose>
 		</table>
 	</s:iterator>
</div> 
</form> 
<br/> 
</fieldset>
</body>
</html>
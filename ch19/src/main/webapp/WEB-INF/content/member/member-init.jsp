<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="cn.com.tienting.modules.web.struts2.Struts2Utils" %>
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
		function checkForm1(){
			alert("请您先登录再选择积分兑换餐币操作！\n如果您还没有注册，请先注册（免费）！");
		}
		function checkForm2(){
			alert("请您选择左边的‘积分兑餐币’链接进行积分兑换餐币操作！");
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
	<legend>积分兑换餐币(演示)</legend>
	<br/> 
	<div style="margin-left: 50px; color:#FF0000;">
		注册成为会员，您订餐的价格（现金付款）会累加到您的积分帐号中，您可以使用积分兑换餐币，使用餐币代替现金订餐！
	</div>
<form id="inputForm" name="inputForm" action="member!initMember.action" method="post">
<div style="margin-left: 50px;">
	<!-- 会员积分、餐币 --> 
	<table>
		<tr>
			<td>您的帐号：<span class="STYLE1">tienting</span></td>
			<td></td>
		</tr>
		<tr>
			<td>您当前可兑换的积分：</td>
			<td><span class="STYLE1">1200</span></td>
		</tr>
		<tr>
			<td>您当前可用的餐币：</td>
			<td><span class="STYLE1">80</span></td>
		</tr>
	</table>
	<br/> 
 	<table>
		<tr>
			<td colspan="2" height="30">
				尊敬的客户：只要您的积分余额等于或超过<span class="STYLE1">100</span>积分，即可以将积分兑换成餐币，每<span class="STYLE1">100</span>积分可以兑换<span class="STYLE1">8</span>元餐币
			</td>
		</tr>
		
		<c:forEach begin="1" end="4" step="1" var="count">
			<c:choose>
				<c:when test="${count%2==1}">
					<tr> 
						<td>
							<input type="radio" id="radioId" name="radioId" value="${count}"/>
							兑换兑换<span class="STYLE1">${count*8 }</span>元餐币（需要兑换<span class="STYLE1">${count*100 }</span>积分）
						</td>
				</c:when>		
							
				<c:otherwise>
						<td>
							<input type="radio" id="radioId" name="radioId" value="${count}"/>
							兑换兑换<span class="STYLE1">${count*8 }</span>元餐币（需要兑换<span class="STYLE1">${count*100 }</span>积分）
						</td>
					</tr>
				</c:otherwise>
			</c:choose> 				
		</c:forEach>
			
		<tr>
			<td colspan="2" height="50">
						兑换<input type="text" id="inputValue" name="inputValue" size="3" maxlength="3" value="16"/>元餐币（<span class="STYLE1">8</span>的整倍数）,
						您当前积分余额最多可兑换<span class="STYLE1">96</span>元餐币
			</td>
		</tr>
		<tr>
			<td colspan="2" height="30">
				<%
					String sessionMemberId = Struts2Utils.getSession().getAttribute("memberId")==null?"":Struts2Utils.getSession().getAttribute("memberId").toString();
					//System.out.println("jsp sessionMemberId="+sessionMemberId);		
					if ("".equals(sessionMemberId)) {
				%>
					<input type="button" value="兑 换" onclick="return checkForm1();"/>		
				<%} else {%>
					<input type="button" value="兑 换" onclick="return checkForm2();"/>	
				<%}%>
			</td>
		</tr>
 	</table>
</div> 
</form> 
<br/> 
</fieldset>
</body>
</html>
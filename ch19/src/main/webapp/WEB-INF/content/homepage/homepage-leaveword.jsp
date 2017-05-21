<%@ page language="java" pageEncoding="UTF-8"%> 
<%@ include file="/common/taglibs.jsp"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
	<title><%=ConstantUtils.restaurantName %>-给我们建议</title>
	<%@ include file="/common/meta.jsp"%>
	<link href="${ctx}/css/default.css" type="text/css" rel="stylesheet" />
	<link href="${ctx}/css/sp_lee.css" rel="stylesheet" type="text/css" />
	<link href="${ctx}/css/main_hp.css" rel="stylesheet" type="text/css" />	
	
	<script src="${ctx}/js/form.js" type="text/javascript"></script>
	<link href="${ctx}/js/validate/jquery.validate.css" type="text/css" rel="stylesheet" />
	<script src="${ctx}/js/jquery.js" type="text/javascript"></script>
	<script src="${ctx}/js/validate/jquery.validate.js" type="text/javascript"></script>
	<script src="${ctx}/js/validate/messages_cn.js" type="text/javascript"></script>
	
	<script type="text/javascript">
		$(document).ready(function(){ 
		$("#inputForm").validate({
			 rules: {   
			 	userName: {
			 		required: true 	
			 	}, 
    			content: {
    				required: true, 
    				maxlength:200
    			},
    			email: "email"
			} 
		});
	});	
	
		function lwshow() {
			 document.getElementById("lw1").style.display="none";
			 document.getElementById("lw2").style.display=""; 
		}
		
		function lwclose() {
		 document.getElementById("lw1").style.display="";
		 document.getElementById("lw2").style.display="none"; 
		}
	</script>
	
	<style type="text/css"> 
		.lshow {
			cursor:pointer;
		}
	</style>
</head>
<body> 
<%@ include file="/common/hander_item.jsp"%>

<div class="main_container"> 
	<br/> 
	<div id="message" align="center"><s:actionmessage theme="mytheme"/></div>
	<fieldset>
	<legend><b>留言记录</b></legend>
	<div style="WIDTH: 100%;  font-size:12px; color:#000000;">
		<form id="mainForm" action="homepage!leaveword.action" method="post">
		  	<input type="hidden" name="pageSize" id="pageSize" value="${leavewordPage.pageSize}"/>
			<input type="hidden" name="leavewordPage.pageNo" id="pageNo" value="${leavewordPage.pageNo}"/>
			<input type="hidden" name="leavewordPage.orderBy" id="orderBy" value="${leavewordPage.orderBy}"/>
			<input type="hidden" name="leavewordPage.order" id="order" value="${leavewordPage.order}" />
				<%int count = 0; %>
				<s:iterator id="lwp" value="leavewordPage.result">
					<%if (count>0){%>
						<br/>
					<%}%>
					<%if (count%2 == 1) {%>	
					<table width="100%" bgcolor="#e3ecf1">				
					<%} else { %>
					<table width="100%">	
					<%} %>
					  	<tr>
						  	<td height="30" >客户：<b>${lwp.name }</b>
						  		&nbsp;&nbsp;留言于：<fmt:formatDate value="${lwp.leaveTime}" pattern="yyyy年MM月dd日 HH:mm"/>
						  		<c:if test="${not empty email}">
						  			&nbsp;&nbsp;&nbsp;&nbsp;E-mail：${email }
						  		</c:if>
						  	</td>
					  	</tr>
					  	<tr>
						  	<td><b>留言内容：</b>${lwp.content }</td>
					  	</tr>
					  	<c:if test="${not empty answer}">
					  		<tr>
							  	<td height="30" style="border-top:1px #DD5800 dashed;"><b>管理员：${lwp.answerMan }</b>
							  		&nbsp;&nbsp;回复于：<fmt:formatDate value="${lwp.edittime}" pattern="yyyy年MM月dd日 HH:mm"/>
							  	</td>
					  		</tr>
					  		<tr>
						  		<td><b>回复内容：</b>${lwp.answer }</td>
					  		</tr>
					  	</c:if>
				  	</table> 
			  	<%count++; %>
				</s:iterator>	
		 </form>
	</div>
	<%@ include file="/common/pagingbanner_leaveword.jsp"%> 
	</fieldset>
	<br/>
	<div id="lw1" style="display:">
		<img src="${ctx}/images/leaveword_ioc.jpg" /><label onclick="lwshow();" class="lshow"><font color="#0066CC"><b>我要提建议</b></font></label>
	</div>
	
	<fieldset id="lw2" style="display:none">
		<legend><b>给我们建议</b></legend>
		<form id="inputForm" name="inputForm" action="homepage!leavewordsave.action" method="post">
		<table summary="订餐系统" width="100%" style="margin-top:-2px;">
			<caption style="background-color:#e3ecf1">
				非常感谢您对网上订餐系统的支持和关注，请在这里留下您的意见/建议，我们也许无法一一回复，但我们会认真阅读，您的支持是我们最大的动力。
			</caption>
			<tr>
				<td align="right"><b>您的名字：</b></td>
				<td><input type="text" name="userName" size="30" maxlength="30" value="${userName }"/></td>
			</tr>
			<tr>
				<td align="right"><b>E-mail：</b></td>
				<td><input type="text" name="email" size="30" maxlength="30" value="${email }"/>(我们将通过email与您联系)</td>
			</tr>
			<tr>
				<td align="right"><b>联系电话：</b></td>
				<td><input type="text" name="phone" size="30" maxlength="30" value="${phone }"/>(或者我们将通过电话与您联系)</td>
			</tr>
			<tr>
				<td align="right"><b>留言类别：</b></td>
				<td>
					<select	name="type">
						<option value="1" <c:if test="${type eq 1 }">selected</c:if>>意见&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</option>
						<option value="2" <c:if test="${type eq 2 }">selected</c:if>>建议</option>
						<option value="3" <c:if test="${type eq 3 }">selected</c:if>>咨询</option>
					</select>
				</td>
			</tr>
			<tr>
				<td nowrap="nowrap" width="70"><b>留言内容：</b></td>
				<td>
					<textarea name="content" style="width:500px;height:80px;">${content }</textarea>
				</td>
			</tr>
			<tr>
				<td align="center" colspan="2">
					<input type="submit" value="提 交"/>&nbsp;&nbsp;&nbsp;&nbsp;
					<input type="button" value="取 消" onclick="lwclose();"/>
				</td>
			</tr>
		</table>
		</form>
	</fieldset>
<%@ include file="/common/footer_item.jsp"%>

</div>
<!--wai_container-->
</body>
</html>


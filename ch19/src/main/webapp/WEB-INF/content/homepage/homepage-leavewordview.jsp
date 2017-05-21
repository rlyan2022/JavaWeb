<%@ page language="java" pageEncoding="UTF-8"%> 
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
	<title></title>
	<%@ include file="/common/meta.jsp"%>
	<script src="${ctx}/js/form.js" type="text/javascript"></script>
	<link href="${ctx}/css/default_hp.css" type="text/css" rel="stylesheet" />
	<script src="${ctx}/js/jquery.js" type="text/javascript"></script>
	<script src="${ctx}/js/effects/jquery.messager.js"></script>
	<link href="${ctx}/css/sp_lee.css" rel="stylesheet" type="text/css" />
	<link href="${ctx}/css/main_hp.css" rel="stylesheet" type="text/css" />
	
    <style>
    	html,body{margin:0; padding:0}
    </style>
</head>
<body>

<div style="WIDTH: 100%;  font-size:12px; color:#000000;">
		<form id="mainForm" action="homepage!leavewordview.action" method="post">
		  	<input type="hidden" name="pageSize" id="pageSize" value="${leavewordPage.pageSize}"/>
			<input type="hidden" name="leavewordPage.pageNo" id="pageNo" value="${leavewordPage.pageNo}"/>
			<input type="hidden" name="leavewordPage.orderBy" id="orderBy" value="${leavewordPage.orderBy}"/>
			<input type="hidden" name="leavewordPage.order" id="order" value="${leavewordPage.order}" />
			<%int count = 0; %>
			<s:iterator id="lwp" value="leavewordPage.result">
				<br/>
				<fieldset style="margin-left: 10px;width:95%;">
					<%if (count%2 == 1) {%>	
					<table width="100%" bgcolor="#e3ecf1">				
					<%} else { %>
					<table width="100%">	
					<%} %>
					  	<tr>
						  	<td height="30" >客户：<b>${lwp.name }</b>
						  		&nbsp;&nbsp;留言于：<fmt:formatDate value="${lwp.leaveTime}" pattern="yyyy年MM月dd日 HH:mm"/>
						  		<c:if test="${not empty email}">
						  			&nbsp;&nbsp;&nbsp;&nbsp;邮箱地址：${email }
						  		</c:if>
						  	</td>
					  	</tr>
					  	<tr>
						  	<td><b>留言内容：</b>${lwp.content }</td>
					  	</tr>
					  	<c:if test="${not empty answer}">
					  		<tr>
							  	<td height="30" style="border-top:1px #DD5800 dashed;"><b>${lwp.answerMan }</b>
							  		&nbsp;&nbsp;回复于：<fmt:formatDate value="${lwp.edittime}" pattern="yyyy年MM月dd日 HH:mm"/>
							  	</td>
					  		</tr>
					  		<tr>
						  		<td><b>回复内容：</b>${lwp.answer }</td>
					  		</tr>
					  	</c:if>
				  	</table>
			  	</fieldset>
			  	<%count++; %>
			</s:iterator>	
		 </form>
	</div>
	<%@ include file="/common/pagingbanner_leaveword.jsp"%>
</body>
</html>
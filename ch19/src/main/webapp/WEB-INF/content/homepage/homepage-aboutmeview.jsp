<%@ page language="java" pageEncoding="UTF-8"%> 
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
	<title><%=ConstantUtils.restaurantName %></title>
	<%@ include file="/common/meta.jsp"%>
	<link href="${ctx}/css/default_hp.css" type="text/css" rel="stylesheet" />　
	<style>
    	html,body{margin:0; padding:0}
    </style>
</head>
<body>
<div id="message"><s:actionmessage theme="mytheme"/></div>
  <form id="inputForm" name="inputForm" action="homepage!aboutmeview.action?aboutmeId=${aboutmeId }" method="post" >
	<input type="hidden" name="aboutmeId" value="${aboutmeId}" />
	<table style="margin-top:3px;" width="700px" align="left" cellpadding="5" cellspacing="5">
  		<tr>
  			<td>
  				您所在的位置：<%=ConstantUtils.restaurantName %> >关于餐店 >正文
  			</td>
  		</tr>
		<tr>
			<td align="center"><h3><font color="#FF6600">${aboutme.title}</font></h3></td>
		</tr>
		<tr>
			<td>发布日期：<fmt:formatDate value="${aboutme.createTime}" pattern="yyyy年MM月dd日  HH:mm"/></td>
		</tr>
		<tr>
			<td style="border-top:1px #cccccc dashed;"> <br/>			
				${aboutme.content}
			</td>
		</tr>
	</table>
  </form>
</body>
</html>
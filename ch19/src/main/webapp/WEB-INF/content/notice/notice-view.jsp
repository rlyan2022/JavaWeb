<%@ page language="java" pageEncoding="UTF-8"%> 
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
	<title>${title}</title>
	<%@ include file="/common/meta.jsp"%>
	<link href="${ctx}/css/default.css" type="text/css" rel="stylesheet" />
	<link href="${ctx}/js/validate/jquery.validate.css" type="text/css" rel="stylesheet" />	
	 
</head>
<body>
<div id="message"><s:actionmessage theme="mytheme"/></div>
<form id="inputForm" name="inputForm" action="notice!view.action?id=${id }" method="post" >
	<input type="hidden" name="id" value="${id}" />
	<table align="center" width="95%">
  		<tr>
  			<td>
  				您所在的位置：慧鼎订餐 >餐店公告 >正文
  			</td>
  		</tr>
		<tr>
			<td align="center"><h2><font color="#FF6600">${title}</font></h2></td>
		</tr>
		<tr>
			<td><fmt:formatDate value="${recordTime}" pattern="yyyy年MM月  HH:mm"/></td>
		</tr>
		<tr>
			<td style="border-top:1px #cccccc dashed;">
				<br/>
				<font color="#006676">${summary}</font><br/>
				
				
				${content}
			</td>
		</tr>
		<s:if test="sign!=null"> 
		<tr>
			<td align="center" height="30">				
				<input type="button" value="返　回" onclick="history.back();" />					
			</td>
		</tr>
		</s:if>
	</table>		
</form>
</body>
</html>
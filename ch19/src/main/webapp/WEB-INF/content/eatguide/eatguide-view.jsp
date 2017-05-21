<%@ page language="java" pageEncoding="UTF-8"%> 
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
	<title>${title}</title>
	<%@ include file="/common/meta.jsp"%>
	<link href="${ctx}/css/default.css" type="text/css" rel="stylesheet" /> 
</head>
<body>
<div id="message"><s:actionmessage theme="mytheme"/></div>
<form id="inputForm" name="inputForm" action="eatguide!view.action?id=${id }" method="post" >
	<input type="hidden" name="id" value="${id}" />
	<table style="margin-top:7px;" width="98%" align="center" cellpadding="5" cellspacing="5">
  		<tr>
  			<td >
  				您所在的位置：慧鼎订餐 >饮食指南 >正文
  			</td>
  		</tr>
		<tr>
			<td align="center"><h2><font color="#FF6600">${title}</font></h2></td>
		</tr>
		<tr>
			<td><fmt:formatDate value="${recordTime}" pattern="yyyy年MM月  HH:mm"/>
			<s:if test="expert != null}">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;作者：${expert}</s:if>
			<s:if test="source != null">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;来源：${source}</s:if>
			</td>
		</tr> 
		<tr>
 
			<td style="border-top:1px #cccccc dashed;"><font color="#006676">&nbsp;&nbsp;${summary}</font><br/><br/>
 	
			&nbsp;&nbsp;${content}</td>
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
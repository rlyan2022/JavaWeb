<%@ page language="java" pageEncoding="UTF-8"%> 
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
	<title><%=ConstantUtils.restaurantName %> </title>
	<%@ include file="/common/meta.jsp"%>
	<link href="${ctx}/css/default_hp.css" type="text/css" rel="stylesheet" />
	<link href="${ctx}/js/validate/jquery.validate.css" type="text/css" rel="stylesheet" />	
	 
</head>
<body>
<div id="message"><s:actionmessage theme="mytheme"/></div>
<form id="inputForm" name="inputForm" action="homepage!noticeview.action?noticeId=${noticeId }" method="post" >
	<input type="hidden" name="noticeId" value="${noticeId}" />
	<table align="center" width="95%">
  		<tr>
  			<td>
  				您所在的位置：<%=ConstantUtils.restaurantName %> > 餐店公告 >正文
  			</td>
  		</tr>
		<tr>
			<td align="center"><h2><font color="#FF6600">${notice.title}</font></h2></td>
		</tr>
		<tr>
			<td><fmt:formatDate value="${notice.recordTime}" pattern="yyyy年MM月dd日 HH:mm"/></td>
		</tr>
		<tr>
			<td style="border-top:1px #cccccc dashed;">
				<br/>
				<font color="#006676">${notice.summary}</font><br/><br/>			
					&nbsp;&nbsp;${notice.content}
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
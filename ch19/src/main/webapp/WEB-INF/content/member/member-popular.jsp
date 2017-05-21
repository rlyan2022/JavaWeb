<%@ page contentType="text/html;charset=UTF-8"%>
<%//@ taglib uri="http://java.fckeditor.net" prefix="FCK"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
	<title>会员积分排行</title>
	<%@ include file="/common/meta.jsp"%>
	<%@ include file="/js/widgets/calendar/calendar.jsp"%>
	<link href="${ctx}/css/default.css" type="text/css" rel="stylesheet" />
	<script src="${ctx}/js/jquery.js" type="text/javascript"></script>
	<script  src="${ctx}/js/form.js" type="text/javascript"></script>
</head>

<body> 
<div style="margin-left: 20px;"><font color="#006676"><b>热门菜单 top 10</b></font></div> 
   <% int count = 0; %>
   	  <s:iterator value="submenulist">
   	  <% if (count%2==0) { %>
   	  	<table >
   	  		<tr><%} %>
   	  			<td width="50%"> 	
   	  				<div id="listContent">
   	  				<table width="100%" height="100"> 
			   			<tr>
			   				<td rowspan="4">
			   					<s:if test="photo==null">
			   						<img src="${ctx }/upload/submenu/defaultphoto.jpg" width="120px;" height="100px;"/>
			   					</s:if> 
			   					<s:else>
			   						<img src="${ctx }/upload/submenu/${photo }" width="120px;" height="80px;"/>
			   					</s:else>
			   				</td>
			   				<td width="120px;">&nbsp;&nbsp;${menutype.name }</td>
			   			</tr>  	
			   			<tr>
			   				<td>&nbsp;&nbsp;<b>${name }</b></td> 
			   			</tr>	
			   			<tr>
			   				<td>&nbsp;&nbsp;单价:<font color="orange">${price }</font>元</td> 
			   			</tr>	
			   			<tr>
			   				<td>&nbsp;&nbsp;已销售:<font color="orange">${sellNumber }</font>份</td> 
			   			</tr>
			   		</table>  
			   		</div>	
   	  			</td>
   	  	<%if (count%2==1) { %>
   	  		</tr>
   	  	</table> 
   		<%} count++;%>
   	  </s:iterator>
</body>
</html>

<%@ page contentType="text/html;charset=UTF-8"%>
<%//@ taglib uri="http://java.fckeditor.net" prefix="FCK"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
	<title>订单查看</title>
	<%@ include file="/common/meta.jsp"%>
	<%@ include file="/js/widgets/calendar/calendar.jsp"%>
	<link href="${ctx}/css/default.css" type="text/css" rel="stylesheet" />
	<script src="${ctx}/js/jquery.js" type="text/javascript"></script>
	<script  src="${ctx}/js/form.js" type="text/javascript"></script>
</head>

<body>
<div id="menu">历史订单查看</div>
   <div id="listContent">    
	   <table align="center" width="90%"> 
   			<tr>
   				<td align="right">订单号：</td><td>${orderFormNo}</td>
   				<td align="right">价格：</td><td><font color="red">${price}元</font></td>
   				<td align="right">状态：</td>
   				<td>
   					<font color="blue">
   					<c:choose>
   						<c:when test="${status==1}">等待处理</c:when>
   						<c:when test="${status==2}">正在配餐</c:when>
   						<c:when test="${status==3}">已送餐</c:when>
   						<c:when test="${status==4}">完成</c:when>
   						<c:otherwise>取消</c:otherwise>
   					</c:choose>
   					</font>
		       	</td>
   				<td>菜单名称、数量、单价</td>
   			</tr>
   			<tr>
   				<td align="right">订餐时间：</td><td>
					<font color="blue">
   						<fmt:formatDate value="${orderDate}" pattern="yyyy-MM-dd HH:mm"/>  				
   					</font>
				</td>	
   				<td align="right">送餐员：</td><td>${assignman.name}</td>
   				<td align="right">送餐员电话：</td><td>${assignman.phone}</td>
   				<td rowspan="5"> 
   					<s:iterator value="orderformlists" id="subMain">
			       		${posPrintCode}号&nbsp;${mainMenuName} &nbsp;${count}*${price}元=${count*price}元<br/>
		           	</s:iterator> 
   				</td>
   			</tr>
   			<tr>				
   				<td align="right">送到时间：</td><td><font color="blue">${fetchTime}</font></td>			
   				<td align="right">联系人：</td><td>${relationMan}</td>
   				<td align="right">联系电话：</td><td>${relationPhone}</td>
   			</tr>
   			<tr>
   				<td align="right">联系地址：</td><td>${assignAddress }</td>  			
   				<td align="right">备注：</td><td colspan="3">${remark }</td>  			
   			</tr>   			
   		</table> 
   </div>
   <br/>
   <br/>
   <div align="center"><input type="button" value=" 返 回 " onclick="window.history.back()"/></div>
</body>
</html>

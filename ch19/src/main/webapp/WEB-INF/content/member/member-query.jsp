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
<br/><br/>
<div id="message" align="center"><s:actionmessage theme="mytheme"/></div>
<form id="inputForm" name="inputForm" action="member!order_query.action" method="post">
<div style="margin-left: 20px;"><font color="#006676"><b>订单查询>请输入查询条件：</b></font></div>
<br />
<table width="98%">
	<tr>
		<td align="right">开始日期:</td>
		<td>
			<input id="startDate" type="text" name="startDate" size="10" value="${startDate}" readonly="readonly" />
				<script type="text/javascript">
						function catcalc(cal) {
					        var date = cal.date;
					        var time = date.getTime()
					
					        var field = $("#startDate");
					        var date2 = new Date(time);
					        field.value = date2.print("%Y-%m-%d");
					    }
						Calendar.setup({
							inputField     : "startDate",
							/*button	  	   : "edittimeBt",
							ifFormat       :    "%Y-%m-%d %H:%M",							
					        showsTime      :    true,
					        timeFormat     :    "24"*/
							ifFormat       :    "%Y-%m-%d",
							onUpdate       :    catcalc
						});
			   </script>	
		</td>
		<td align="right">结束日期:</td>
		<td>
			<input id="endDate" type="text" name="endDate" size="10" value="${endDate}" readonly="readonly" />
				<script type="text/javascript">
						function catcalc(cal) {
					        var date = cal.date;
					        var time = date.getTime()
					
					        var field = $("#endDate");
					        var date2 = new Date(time);
					        field.value = date2.print("%Y-%m-%d");
					    }
						Calendar.setup({
							inputField     : "endDate",
							/*button	  	   : "edittimeBt",
							ifFormat       :    "%Y-%m-%d %H:%M",							
					        showsTime      :    true,
					        timeFormat     :    "24"*/
							ifFormat       :    "%Y-%m-%d",
							onUpdate       :    catcalc
						});
			   </script>
		</td>
		<td align="right">送餐状态:</td>
		<td>
			<select name="orderStatus">
				<option value="">全部</option>
				<option value="1" <c:if test="${oldStatus==1}">selected</c:if>>等待处理</option>
				<option value="2" <c:if test="${oldStatus==2}">selected</c:if>>正在配餐</option>
				<option value="3" <c:if test="${oldStatus==3}">selected</c:if>>已送餐</option>
				<option value="4" <c:if test="${oldStatus==4}">selected</c:if>>完成</option>
				<option value="5" <c:if test="${oldStatus==5}">selected</c:if>>取消</option>
			</select>
		</td>
		<!--  <td align="right">付款方式:</td>
		<td>
			<select name="payMode">
				<option value="">全部</option>
				<option value="1" <c:if test="${payMode==1}">selected</c:if>>上门收费</option>
				<option value="2" <c:if test="${payMode==2}">selected</c:if>>餐币付费</option> 
			</select>
		</td>-->
		<td> 
			<input type="submit" value="查  询"/>
		</td>
	</tr>
	<tr>
		<td colspan="7" style="border-top: 1px #cccccc dashed;">&nbsp;</td>
	</tr>
</table>
<br/>
</form>
<br/>
<c:if test="${empty orderformnotelist}">
	<div style="margin-left: 200px;font-size: 14px;"><font color="#006676"><b>没有符合条件的订餐记录！</b></font></div>
</c:if>
<c:if test="${not empty orderformnotelist}">
	<div style="margin-left: 20px;font-size: 14px;"><font color="#006676"><b>您的订餐记录明细：</b></font></div>
</c:if>

<s:iterator value="orderformnotelist">
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
   				<td align="right">订餐时间：</td>
   				<td>
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
   				<td align="right">送到时间：</td><td>${fetchTime}</td>			
   				<td align="right">联系人：</td><td>${memberName}</td>
   				<td align="right">联系电话：</td><td>${relationPhone}</td>
   			</tr>
   			<tr>
   				<td align="right">联系地址：</td><td>${relationAddress }</td>  			
   				<td align="right">备注：</td><td colspan="3">${remark }</td>  			
   			</tr>   			
   		</table> 
   </div>
</s:iterator> 
</body>
</html>

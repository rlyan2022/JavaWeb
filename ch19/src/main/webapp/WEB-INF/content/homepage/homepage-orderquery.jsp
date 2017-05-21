<%@ page language="java" pageEncoding="UTF-8"%> 
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
	<title><%=ConstantUtils.restaurantName %>-订单查询</title>
	<%@ include file="/common/meta.jsp"%>
	<%@ include file="/js/widgets/calendar/calendar.jsp"%>
	<link href="${ctx}/css/default_hp.css" type="text/css" rel="stylesheet" />
	<link href="${ctx}/css/sp_lee.css" rel="stylesheet" type="text/css" />
	<link href="${ctx}/css/main_hp.css" rel="stylesheet" type="text/css" />
	
	<script src="${ctx}/js/jquery.js" type="text/javascript"></script>
	<script  src="${ctx}/js/form.js" type="text/javascript"></script>
	
	<script type="text/javascript">
		function checkForm(){		
			var orderno = document.inputForm.elements["orderno"].value;
			var orderman = document.inputForm.elements["orderman"].value;
		
			if (orderno=="" && orderman=="") {
				alert("请输入订单号 或者 订餐联系人 再点击查询！");
				return false;
			}
			
			document.inputForm.action = "homepage!orderquery.action";
			document.inputForm.submit();
		}
	</script>	 
</head>
<body> 
<%@ include file="/common/hander_item.jsp"%>
<div class="main_container">
<div id="message" align="center"><s:actionmessage theme="mytheme"/></div>
<!-- div class="main_right_container">-->
<form id="inputForm" name="inputForm" action="homepage!orderqueryinit.action" method="post">
<br/>
	<table align="center" width="95%">
  		<tr>
  			<td>
  				&nbsp;&nbsp;您所在的位置：<%=ConstantUtils.restaurantName %> >订单查询 >订单信息
  			</td>
  		</tr>
		<tr>
			<td style="border-top:1px #cccccc dashed;">&nbsp;</td>
		</tr>
	</table>		
	<table align="center" width="95%">
		<tr>
		<td align="right">开始日期:
			<input id="startDate" type="text" name="startDate" size="8" value="${startDate}" readonly="readonly" />
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
		　<td align="right">结束日期:
			<input id="endDate" type="text" name="endDate" size="8" value="${endDate}" readonly="readonly" />
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
			<td align="center">
				订单号：<input type="text" id="orderno" name="orderno" value="${orderno }" size="16" maxlength="16"/>&nbsp;&nbsp;
			</td>
			<td align="center">
				订餐联系人：<input type="text" id="orderman" name="orderman" value="${orderman }" size="16" maxlength="20"/>
			</td>
			<td align="center">
				<input type="button" onclick="return checkForm();" value="查   询"/>
			</td>
		</tr>
	</table>
	<br/><br/>
  <div id="listContent">
	<s:iterator value="orderformnotelist" id="main">
   		<table width="930" align="center" cellpadding="5"> 
<tr>
   				<td width="110" align="right">订单号：</td>
			<td width="150">${orderFormNo}</td>
			<td width="90" align="right">价格：</td>
			<td width="60"><font color="red">${price}元</font></td>
			<td width="110" align="right">状态：</td>
	  <td width="110">
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
			<td width="300">菜单名称、数量、单价</td>
		  </tr>
   			<tr>
   				<td width="110" align="right">订餐时间：</td>
		  <td width="150">
					<font color="blue">
					<fmt:formatDate value="${orderDate}" pattern="yyyy-MM-dd HH:mm"/>  				
   					</font>
				</td>	
			  <td width="90" align="right">送餐员：</td>
			  <td width="60">${assignman.name}</td>
			  <td width="110" align="right">送餐员电话：</td>
			  <td width="110">${assignman.phone}</td>
		  <td width="300" rowspan="5"> 
<s:iterator value="#main.orderformlists" id="subMain">
			       		${posPrintCode}号&nbsp;${mainMenuName} &nbsp;${count}*${price}元=${count*price}元<br/>
		           	</s:iterator> 
   				</td>
		  </tr>
   			<tr>				
   				<td width="110" align="right">送到时间：</td>
			  <td width="150">${fetchTime}</td>			
			  <td width="90" align="right">联系人：</td>
			  <td width="60">${relationMan}</td>
			  <td width="110" align="right">联系电话：</td>
			  <td width="110">${relationPhone}</td>
		  </tr>
   			<tr>
   				<td width="110" align="right">送餐地址：</td>
			  <td width="150">${assignAddress }</td>  			
			  <td width="90" align="right">备注：</td>
			  <td colspan="3">${remark }</td>  			
   			</tr>   			
   		</table> 
   		<br/>
   	</s:iterator>
   </div>
</form>
<!--</div>-->
<div style="clear:both;"></div>
<br/>
<%@ include file="/common/footer_item.jsp"%>
<!--wai_container-->
</div>
</body>
</html>


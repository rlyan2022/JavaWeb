<%@ page language="java" pageEncoding="UTF-8"%> 
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
	<title><%=ConstantUtils.restaurantName %>-订单查询</title>
	<link href="${ctx}/css/sp_lee.css" rel="stylesheet" type="text/css" />
	<link href="${ctx}/css/main_hp.css" rel="stylesheet" type="text/css" />
	
	<%@ include file="/common/meta.jsp"%>
	<link href="${ctx}/css/default.css" type="text/css" rel="stylesheet" />
	
	<script type="text/javascript">
		function checkForm(){		
			var orderno = document.inputForm.elements["orderno"].value;
			var orderman = document.inputForm.elements["orderman"].value;
		
			if (orderno=="" && orderman=="") {
				alert("请输入订单号 或者 订餐联系人 再点击查询！");
				return false;
			}
			
			document.inputForm.action = "orderformnote!order_query.action";
			document.inputForm.submit();
		}
	</script>	 
</head>
<body> 
<%@ include file="/common/hander.jsp"%>
<div class="main_container">
<!-- 
<div class="main_left_container">
<div class="left_column"><div class="ico_jpg" style="background-image:url(images/jpg_active.jpg); width:45px; height:33px;  "></div>
<li>会员操作</li>
</div>
<div class="left_content">

	<div style="WIDTH: 210px;  font-size:12px; color:#000000;">
		<form id="mainForm" action="eatguide!query.action" method="post">
		  	<input type="hidden" name="pageSize" id="pageSize" value="${page.pageSize}"/>
			<input type="hidden" name="page.pageNo" id="pageNo" value="${page.pageNo}"/>
			<input type="hidden" name="page.orderBy" id="orderBy" value="${page.orderBy}"/>
			<input type="hidden" name="page.order" id="order" value="${page.order}" />
		  	<table>
				<s:iterator id="notid" value="page.result">
	  				<tr>
	  					<td><a href="notice!query.action?id=${notid.id }">${notid.title }</a></td>
	  					<td><fmt:formatDate value="${notid.recordTime}" pattern="dd/MM"/></td>
	  				</tr>
	  			</s:iterator>
			</table>
		  </form>
	</div>
</div>
<div class="left_foot"></div>

</div>-->

<!-- div class="main_right_container">-->
<form id="inputForm" name="inputForm" action="orderformnote!order_query.action" method="post" onsubmit="checkForm();">
	<br/>
	<table align="center" width="90%">
  		<tr>
  			<td>
  				&nbsp;&nbsp;您所在的位置：<%=ConstantUtils.restaurantName %>- >订单查询 >订单信息
  			</td>
  		</tr>
		<tr>
			<td style="border-top:1px #cccccc dashed;">&nbsp;</td>
		</tr>
	</table>	
	
	<table align="center" width="90%">
		<tr>
			<td align="center">
				请输入订单号：<input type="text" id="orderno" name="orderno" value="${orderno }" size="16" maxlength="16"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				订餐联系人：<input type="text" id="orderman" name="orderman" value="${orderman }" size="16" maxlength="20"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="button" onclick="return checkForm();" value="查   询"/>
			</td>
		</tr>
	</table>
	<br/><br/>
  <div id="listContent">
	<s:iterator value="orderlist" id="main">
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
   					<s:iterator value="#main.orderformlists" id="subMain">
			       		${posPrintCode}号&nbsp;${mainMenuName} &nbsp;${count}*${price}元=${count*price}元<br/>
		           	</s:iterator> 
   				</td>
   			</tr>
   			<tr>				
   				<td align="right">送到时间：</td><td>${fetchTime}</td>			
   				<td align="right">联系人：</td><td>${relationMan}</td>
   				<td align="right">联系电话：</td><td>${relationPhone}</td>
   			</tr>
   			<tr>
   				<td align="right">联系地址：</td><td>${relationAddress }</td>  			
   				<td align="right">备注：</td><td colspan="3">${remark }</td>  			
   			</tr>   			
   		</table> 
   		<br/>
   	</s:iterator>
   </div>
</form>
<!--</div>-->
<div style="clear:both;"></div>

<%@ include file="/common/footer_item.jsp"%>
<!--wai_container-->
</div>
</body>
</html>


<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/common/taglibs.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
	<title></title>
	<%@ include file="/common/meta.jsp"%>
	<link href="${ctx}/css/default.css" type="text/css" rel="stylesheet" />
	<script src="${ctx}/js/form.js" type="text/javascript"></script>
	<script src="${ctx}/js/jquery.js" type="text/javascript"></script>
	<script src="${ctx}/js/effects/jquery.messager.js"></script>
	
	<style type="text/css" media=print>
		.noprint{display : none }
	</style>

	<script type="text/javascript">
		var batchPrintOrderPrintCountAction = 'orderformnote!batchPrintOrderPrintCount.action';
	
		function  printsetup(){
			//打印页面设置    
		    wb.execwb(8,1);           
		}     
		   
		function  printpreview(){             
		    //打印页面预览    
		    wb.execwb(7,1);    
		}     
		   
		function printit(){     
		    if(confirm('确定打印吗？(如原订单状态为等待处理，则修改为正在配餐！)')){
		    	wb.execwb(6,6); 
		    	 
		    	batchPrintOrderPrintCount();
		    }   
		}
		function PrintData(Id){
                 var mStr;
                 mStr = window.document.body.innerHTML ;
                 var mWindow = window;                
                 window.document.body.innerHTML =Id.innerHTML; 
                 mWindow.print();
                 window.document.body.innerHTML = mStr;
        }
        
        //批量修改订单状态、打印次数
		function batchPrintOrderPrintCount(){
			$.ajax({
				type: "POST",
				url: "orderformnote!batchPrintOrderPrintCount.action",
				data: "selectOrderIds=${selectOrderIds }",
				//执行回调函数...此处不使用服务器返回的msg
				success: function(msg){
					//alert(msg);
					$.messager.anim('fade', 1000);
					$.messager.lays(250, 100);
					$.messager.show("订单打印提示：", "<span style='color:green'>修改订单的打印标记、状态成功！</span>",2000);
				}	
			});	
		}
	</script>
  </head>
  
  <body>
  <div id="message"><s:actionmessage theme="mytheme"/></div>
   <div id="listContent">
   <form id="printForm" action="orderformnote!batchPrintOrder.ation" method="post">
	
   	<s:iterator value="printOrders" id="main">
   		<table width="300"> 
   			<tr>
   				<td align="right">订单号</td><td>${orderFormNo}</td>
   				<td align="right">价格</td><td>${price}元</td>
   			</tr>
			<tr>
   				<td align="right">订单时间</td><td><font color="blue"><fmt:formatDate value="${orderDate}" pattern="yyyy-MM-dd HH:mm" /></font></td>
   				<td align="right">送到时间</td><td><font color="blue">${fetchTime}</font></td>	
   			</tr>
   			<tr>
   				<td align="right">状态</td>
   				<td>
   					<c:if test="${status=='1'}">等待处理</c:if>
		       		<font color="red">
		       			<c:if test="${status=='2'}">正在配餐</c:if>
		       			<c:if test="${status=='3'}">已送餐</c:if>
		       			<c:if test="${status=='4'}">完成</c:if>
		       			<c:if test="${status=='5'}">取消</c:if> 
		       		</font>
		       	</td>
   				<td align="right">送餐员</td><td>${assignman.name}</td>			
   			</tr>
   			<tr>
   				<td align="right">联系人</td><td>${relationMan}</td>
   				<td align="right">联系电话</td><td>${relationPhone}</td>
   			</tr>
   			<tr>
   				<td align="right">联系地址</td><td colspan="3">${assignAddress }</td>  			
   			</tr>
   			<!--<tr>
   				<td align="center" colspan="4">菜单名称、数量、单价</td>  
   			</tr>-->
   			<tr>
   				<td colspan="4">
		       		<s:iterator value="#main.orderformlists" id="subMain">
			       		${posPrintCode}号&nbsp;${mainMenuName} &nbsp;${count}*${price}元=${count*price}元<br/>
		           	</s:iterator>  
		        </td>
   			</tr>
   		</table> 
   		<br/>
   	</s:iterator>
   	</form>
   
   	<p class="noprint">
	   	<object classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2" height=0 id=wb name=wb width=3></object>    
		<input type=button name=print value="确定打印" onclick="javascript:printit();" />     
		<input type=button name=priview value="打印预览" onclick="javascript:printpreview();" />
		<input type="button" value="返回" onclick="window.history.back()"/>
	</p>
	
	<!--  
		<input type="button" value="打    印" onclick="return PrintData(listContent)"> 
	-->  
   </div>
  </body>
</html>

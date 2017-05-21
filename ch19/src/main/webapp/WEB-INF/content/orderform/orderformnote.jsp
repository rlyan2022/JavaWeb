<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ page import="cn.com.tienting.modules.utils.DateUtils" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <title>今日订单操作台</title>
    <%@ include file="/js/widgets/calendar/calendar.jsp"%>
    <%@ include file="/common/meta.jsp"%>
	<script src="${ctx}/js/form.js" type="text/javascript"></script>
	<link href="${ctx}/css/default.css" type="text/css" rel="stylesheet" />
	<script src="${ctx}/js/jquery.js" type="text/javascript"></script>
	<script src="${ctx}/js/effects/jquery.messager.js"></script>
	
	<script type="text/javascript">	
		var defaultAction = 'orderformnote.action';
		var viewAction = 'orderformnote!view.action';
		//var modifyAction = 'orderformnote!input.action';
		var deleteAction = 'orderformnote!delete.action';
		//var checkoutAction = 'orderformnote!listOrderByRestaurant.action';
		var batchDeleteAction = 'orderformnote!batchDelete.action';
		var batchChangeStatusAction = 'orderformnote!batchChangeStatus.action';
		var batchChangeAssignmanAction = 'orderformnote!batchChangeAssignman.action';
		var batchPrintOrderAction = 'orderformnote!batchPrintOrder.action';
		var selectAction = 'orderformnote!select.action';
		
		//修改订单状态前获取订单原状态
		var oldStatusValue;
		//获取当前订单的状态选择下拉框select的ID属性的值
		var orderStatusId;
		
		function getOldStatus(statusId , statusValue){
			oldStatusValue = statusValue;
			orderStatusId = statusId;
			//alert(orderStatusId);
		}
		
		//复选框对订单的'全选'与'全不选'
		function checkall(form, prefix, checkall) {
			for(var i = 0; i < form.elements.length; i++) {
				var e = form.elements[i];
				if(e.name != checkall && (!prefix || (prefix && e.name.match(prefix)))) {
					e.checked = form.elements[checkall].checked;
				}
			}
		}
		
		//页面'全选'按钮
		function CheckedAll() {
			//alert("CheckedAll");
		    $("input:checkbox[name='selectOrder']").attr('checked', true);
		    $("input:checkbox[id='chkall']").attr('checked', true);
		}
		//页面'全不选'按钮
		function CancelAll() {
			//alert("CancelAll");
			$("input:checkbox[name='selectOrder']").attr('checked', false);
			$("input:checkbox[id='chkall']").attr('checked', false);
		}
		//通过指定状态查找订单记录
		function selectByStatus(selectStatus){          
			$("#mainForm").attr("action",selectAction+"?selectStatus="+selectStatus);
			$("#mainForm").submit();
		}
		
		//修改订单状态前检查修改是否合法
		function checkBeforeChangeStatus(newStatusValue){
			//订单状态不能从'正在配餐'修改为'取消'
			if(oldStatusValue=="2" && newStatusValue=="5"){
				return false;
			}
			//订单状态不能从'已送出'修改为'取消'
			else if(oldStatusValue=="3" && newStatusValue=="5"){
				return false;
			}
			//订单状态不能从'完成'修改为'取消'
			else if(oldStatusValue=="4" && newStatusValue=="5"){
				return false;
			}
			else{
				return true;
			}
		}
		
		//改变订单状态
		function changeStatus(id,orderformno,statusValue,newStatus){
			if(checkBeforeChangeStatus(statusValue)){
				$.ajax({
				type: "POST",
				url: "orderformnote!changeStatus.action",
				data: "id=" + id + "&orderFormNo=" + orderformno + "&status=" + statusValue,
				//执行回调函数...此处不使用服务器返回的msg
				success: function(msg){
					//alert(msg);
					$.messager.anim('fade', 1000);
					$.messager.lays(250, 100);
					$.messager.show("订单状态修改提示", "<span style='color:green'>今日订单：<strong>"+orderformno+"</strong><br>状态修改成:<font color='red'><strong>"+newStatus+"</strong></font></span>",2000);
				}	
			});	
			}
			else{
				$.messager.anim('fade', 1000);
				$.messager.lays(200, 70);
				$.messager.show("订单状态修改提示", "<font color='red'><strong>非法修改订单状态!</strong></font></span>",2000);
				//修改订单状态非法时下拉列表恢复到以前的状态
				$("#" + orderStatusId).attr("value",oldStatusValue);
			}
		}
		
		/*/获取指定分店的订单
		function checkoutOrderByRestaurant(){
			var item = $('#restaurantId').val();
			if(item == "nochoice"){
				window.location.href(defaultAction);
			}
			else{
				//$("#mainForm").attr("action",checkoutAction + "?restaurantId=" + item);
				$("#mainForm").attr("action",checkoutAction);
				$("#mainForm").submit();
			}
		}*/
		
		//修改配送员
		function changeAssignman(id,orderformno,newAssignman){
			alert(id + " " + orderformno + " " + newAssignman);
		}
		
		//批量删除订单
		function deleteAll(){
			var all = document.getElementsByName("selectOrder"); 
			if(all.length == 0){
				alert("很抱歉,当前页面未发现有订单记录!");
				return;
			}
			var allChecked = new Array();
			for(var i = 0; i < all.length; i ++){ 
				if(all[i].checked){ 
					allChecked.push(all[i]);
				}
			}
			if(allChecked.length == 0){
				alert("请选择需要删除的订单记录!");
				return;
			}
			else{
				//alert(allChecked.length);
				//window.location.href(batchDeleteAction);
				$("#mainForm").attr("action",batchDeleteAction);
				$("#mainForm").submit();
			}
		} 
		
		//批量修改订单状态
		function batchChangeStatus(newStatus){
			var all = document.getElementsByName("selectOrder"); 
			if(all.length == 0){
				alert("很抱歉,当前页面未发现有订单记录!");
				$("#batchOrderStatus").attr("value","0");
				return;
			}
			var allChecked = new Array();
			for(var i = 0; i < all.length; i ++){ 
				if(all[i].checked){ 
					allChecked.push(all[i]);
				}
			}
			if(allChecked.length == 0){
				alert("请选择需要修改状态的订单记录!");
				$("#batchOrderStatus").attr("value","0");
				return;
			}
			else{
				$("#mainForm").attr("action" , batchChangeStatusAction + "?newStatus=" + newStatus);
				$("#mainForm").submit();
			}
		}
		
		//批量修改订单的配送员
		function batchChangeAssignman(newAssignman){
			var all = document.getElementsByName("selectOrder"); 
			if(all.length == 0){
				alert("很抱歉,当前页面未发现有订单记录!");
				$("#batchOrderAssignman").attr("value","0");
				return;
			}
			var allChecked = new Array();
			for(var i = 0; i < all.length; i ++){ 
				if(all[i].checked){ 
					allChecked.push(all[i]);
				}
			}
			if(allChecked.length == 0){
				alert("请选择需要设置配送员的订单记录!");
				$("#batchOrderAssignman").attr("value","0");
				return;
			}
			else{
				//alert(newAssignman);
				$("#mainForm").attr("action" , batchChangeAssignmanAction + "?newAssignman=" + newAssignman);
				$("#mainForm").submit();
			}
		}
		
		//打印订单
		function printOrder(){
			var all = document.getElementsByName("selectOrder"); 
			if(all.length == 0){
				alert("很抱歉,当前页面未发现有订单记录!");
				return;
			}
			var allChecked = new Array();
			for(var i = 0; i < all.length; i ++){ 
				if(all[i].checked){ 
					allChecked.push(all[i]);
				}
			}
			if(allChecked.length == 0){
				alert("请选择需要打印的订单记录!");
				return;
			}
			else{
				if (confirm('您现在打印的订单的订餐日期为：${q_orderDate}\n'+"系统当前日期是：<%=DateUtils.getCurrentDateStr("yyyy-MM-dd")%>"+'\n\n您确定要打印吗?')) {
					$("#mainForm").attr("action" , batchPrintOrderAction);
					$("#mainForm").submit();
				}
			}
		}
	</script>
  </head>
  
  <body onload="setInterval('t1.innerText=new Date().toLocaleString()',100)">
	<fieldset style="width:98%">
	<legend style="font-size:14px"><b>今日<font style="color:red;">(<%=DateUtils.getCurrentDateStr("yyyy年MM月dd日")%>)</font>订单操作台，
	&nbsp;&nbsp;<font style="color:#DD5800;">您的计算机当前时间：<span id="t1"></span></font></b>&nbsp;</legend> 	 
	<form id="searchForm" action="orderformnote!search.action" method="post">
		<table align="center">
			<tr>	 
				<td>
					<font style="color:#DD5800; font-weight:bold;">订单查询</font> &nbsp;&nbsp;订单日期: <input id="q_orderDate" type="text" name="q_orderDate" size="10" value="${q_orderDate}" readonly="readonly" />
					<!-- <input id="orderDate" type="text" name="orderDate" size="10" value="<fmt:formatDate value="${orderDate}" pattern="yyyy-MM-dd"/>" readonly="readonly"/> -->
					<script type="text/javascript">
						function catcalc(cal) {
					        var date = cal.date;
					        var time = date.getTime()
					
					        var field = $("#q_orderDate");
					        var date2 = new Date(time);
					        field.value = date2.print("%Y-%m-%d");
					    }
						Calendar.setup({
							inputField     : "q_orderDate",
							/*button	  	   : "edittimeBt",
							ifFormat       :    "%Y-%m-%d %H:%M",							
					        showsTime      :    true,
					        timeFormat     :    "24"*/
							ifFormat       :    "%Y-%m-%d",
							onUpdate       :    catcalc
						});
					</script>	
				</td>
				<td>
					&nbsp;订单状态:
					<select id="q_status" name="q_status">
						<option value="">&nbsp;&nbsp;全部</option>
						<option value="1" <c:if test="${q_status=='1'}">selected</c:if>>等待处理</option>
						<option value="2" <c:if test="${q_status=='2'}">selected</c:if>>正在配餐</option>
						<option value="3" <c:if test="${q_status=='3'}">selected</c:if>>已送餐</option>
						<option value="4" <c:if test="${q_status=='4'}">selected</c:if>>完成</option>
						<option value="5" <c:if test="${q_status=='5'}">selected</c:if>>取消</option>
					</select>
				</td> 
				<td>&nbsp;<input id="search" type="submit" value="查  询" /></td>
			</tr>
		</table>
	</form>
	
	<form id="mainForm" action="orderformnote!search.action" method="post"> 
		<input type="hidden" name="pageSize" id="pageSize" value="${page.pageSize}"/>
		<input type="hidden" name="page.pageNo" id="pageNo" value="${page.pageNo}"/>
		<input type="hidden" name="page.orderBy" id="orderBy" value="${page.orderBy}"/>
		<input type="hidden" name="page.order" id="order" value="${page.order}" />
		<input type="hidden" name="id" id="id" />
		
		<input type="hidden" name="q_orderDate" id="q_orderDate" value="${q_orderDate}"/>
		<input type="hidden" name="q_status" id="q_status" value="${q_status}"/> 
		
		<div id="listContent">
		
		<table class="listView"  width="98%">
			<!--<tr>
				<td>	 
					  打印设置:
					<input type="checkbox" id="autoPrint" value="autoPrint">自动打印
					<input type="checkbox" id="autoPrint" value="autoPrint"/>手动打印
				</td>
			</tr>  -->
			<tr>
				<td >
					<font style="color:#DD5800; font-weight:bold;">批量操作</font>
					<!-- <input type="button" value="删除订单" id="delete" onclick="deleteAll()"/> -->
					<input type="button" value="打印订单" id="print" onclick="printOrder()"/>
					&nbsp;&nbsp;设置订单状态:
					<select id="batchOrderStatus" <s:if test="page.totalCount==0">disabled="disabled"</s:if>
							onchange="batchChangeStatus(this.options[this.options.selectedIndex].value)">
						<option value="0">选择状态</option>
						<option value="1" style="color:blue">等待处理</option>
						<option value="2" style="color:red">正在配餐</option>
						<option value="3" style="color:orange">已送餐</option>
						<option value="4" style="color:green">完成</option>
						<option value="5">取消</option>
					</select>
					&nbsp;&nbsp;设置配送员:
					<c:choose>
						<c:when test="${assignmans==null}">
							<select disabled="disabled">
								<option>指定送餐员</option>
							</select>
						</c:when>
						<c:when test="${not empty assignmans}">
							<s:select id="batchOrderAssignman" list="assignmans" listKey="id" 
									listValue="name" headerKey="0" headerValue="指定送餐员"
									onchange="batchChangeAssignman(this.options[this.options.selectedIndex].value)" 
										theme="simple"/>
						</c:when>
					</c:choose>
					
					&nbsp;&nbsp;<input id="reload" type="submit" value="刷　新" />
				</td>
			</tr>
		</table>
		<br/>
		<div id="message"><s:actionmessage theme="mytheme"/></div>		
		<table class="listView" width="98%">
			<tr>
				<th><input type="checkbox" id="chkall" name="chkall" onclick="checkall(this.form, 'selectOrder','chkall')"/></th>
				<th><a href="javascript:sorting('orderFormNo','asc')"><b>订单编号</b></a></th>
				<th><a href="javascript:sorting('orderDate','asc')"><b>订餐时间</b></a></th>
				<th><a href="javascript:sorting('fetchTime','asc')"><b>送到时间</b></a></th>
				<th><a href="javascript:sorting('memberName','asc')"><b>会员</b></a></th>
				<th><a href="javascript:sorting('price','asc')"><b>价格</b></a></th>		
				<th><a href="javascript:sorting('payMode','asc')"><b>付款方式</b></a></th>				
				<th><a href="javascript:sorting('status','asc')"><b>订单状态</b></a></th>
				<th><a href="javascript:sorting('isPrint','asc')"><b>已打印</b></a></th>
				<th><a href="javascript:sorting('relationMan','asc')"><b>联系人</b></a></th>
				<th><b>联系电话</b></th>
				<th><a href="javascript:sorting('assignAddress','asc')"><b>送餐地址</b></a></th>
				<th><a href="javascript:sorting('assignName','asc')"><b>送餐员</b></a></th>
				<th>操作</th>
			</tr>
		
			<s:if test="page.totalCount==0"><tr><td colspan="10">未找到相关订单记录</td></tr></s:if>
			<s:elseif test="page.totalCount>0">
				<s:iterator value="page.result">
					<tr>
						<td><input type="checkbox" id="selectOrder" name="selectOrder" value="${id}"/></td>
						<td><a href="javascript:viewItem(${id})">${orderFormNo}</a>&nbsp;</td>
						<td align="center"><font color="red"><b><fmt:formatDate value="${orderDate}" pattern="MM-dd HH:mm"/></b></font>&nbsp;</td>
						<td align="center"><font color="red"><b>${fetchTime}</b></font></td>	
						<td><!--<c:if test="${null==member}">游客</c:if>
							<c:if test="${null!= member}">${member.relationMan}</c:if>-->
							${memberName }
						</td>		
						<td>￥${price}</td>
						<td>
							<c:if test="${1==payMode}">上门收费</c:if>
							<c:if test="${2==payMode}"><font color="blue">餐币付款</font></c:if>
						</td>
						
						<td>
							<select id="${orderFormNo}" style="font-weight:bold" onclick="getOldStatus('${orderFormNo}',this.options[this.options.selectedIndex].value)" 
								onchange="changeStatus('${id}','${orderFormNo}',this.options[this.options.selectedIndex].value,this.options[this.options.selectedIndex].text)">
								<option value="1" <c:if test="${status=='1'}">selected</c:if> style="color:blue">等待处理</option>
								<option value="2" <c:if test="${status=='2'}">selected</c:if> style="color:red">正在配餐</option>
								<option value="3" <c:if test="${status=='3'}">selected</c:if> style="color:orange">已送餐</option>
								<option value="4" <c:if test="${status=='4'}">selected</c:if> style="color:green">完成</option>
								<option value="5" <c:if test="${status=='5'}">selected</c:if>>取消</option>
							</select>
						</td>
						<td>
							<c:if test="${1==isPrint}">已打印</c:if>
							<c:if test="${2==isPrint}">未打印</c:if>
						</td>
						<td>${relationMan}</td>
						<td>${relationPhone}</td>
						<td>${assignAddress}</td>
						<td>
							${assignName}
						</td>
						 
						<td width="30px;">
							<!--<a href="javascript:viewItem(${id})">查看</a>
							 
							<a href="javascript:modifyItem(${id})">修改</a>-->
							<a href="javascript:if(confirm('订单【${orderFormNo}】删除后不能恢复,\n您确定要删除订单吗?'))deleteItem(${id})">删除</a>							
						</td>
					</tr>
				</s:iterator>
			</s:elseif>
		</table>
		</div>
		
		<br/>
		
		<div>
			<font style="color:#DD5800; font-weight:bold;">选择</font>&nbsp;&nbsp;
			<input type="button" id="selectall" value="全选" onclick="CheckedAll()"/>&nbsp;&nbsp;
			<input type="button" id="deselectall" value="全不选" onclick="CancelAll()"/>&nbsp;&nbsp;
			<font style="color:#DD5800; font-weight:bold;">快速查找</font>
			<input type="button" id="processing" value="等待处理" onclick="selectByStatus('1')"/>&nbsp;&nbsp;
			<input type="button" id="matching" value="正在配餐" onclick="selectByStatus('2')"/>&nbsp;&nbsp;
			<input type="button" id="sended" value="已送餐" onclick="selectByStatus('3')"/>&nbsp;&nbsp;
			<input type="button" id="complete" value="完成" onclick="selectByStatus('4')"/>&nbsp;&nbsp;
			<input type="button" id="complete" value="取消" onclick="selectByStatus('5')"/>
		</div>
		<%@ include file="/common/pagingbanner.jsp"%>
	</form>
	<!--  
	<div><a href="orderformnote!input.action">增加订单</a></div>	
	-->
	<div><font color="#006676">
		提示：<br/>
		1、'取消'订单请慎重操作，'取消'的订单不建议再修改为其他状态<br/>
		2、若订单使用餐币付款，<br/>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;'取消'订单则价格将回退到该会员的餐币账号中，<br/>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;反之，则重新在该会员餐币账号中扣款，若餐币值不够，则付款方式修改为'上门收费'<br/>
		</font>
	</div>
	</fieldset>
  </body>
</html>

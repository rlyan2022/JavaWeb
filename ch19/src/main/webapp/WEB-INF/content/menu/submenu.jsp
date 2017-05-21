<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net/el" prefix="display" %>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>餐店菜单列表</title>
	<%--<%@ include file="/common/meta.jsp"%>--%>
	<%@ include file="/common/meta.jsp"%>
	<%@ include file="/js/widgets/calendar/calendar.jsp"%>
	<link href="${ctx}/css/default.css" type="text/css" rel="stylesheet" />
	<link href="${ctx}/js/validate/jquery.validate.css" type="text/css" rel="stylesheet" />
	<script src="${ctx}/js/jquery.js" type="text/javascript"></script>
	<script src="${ctx}/js/validate/jquery.validate.js" type="text/javascript"></script>
	<script src="${ctx}/js/validate/messages_cn.js" type="text/javascript"></script>
	<script  src="${ctx}/js/form.js" type="text/javascript"></script>
	<script src="${ctx}/js/effects/jquery.messager.js"></script>
	
	<script type="text/javascript">	
	var viewAction = 'submenu!view.action';
	var modifyAction = 'submenu!input.action';
	var deleteAction = 'submenu!delete.action';

	function search(){
		$('#mainForm').submit();
	}
	/*$(document).ready(function(){
		$("#mainForm").validate(
		{
			rules: { 
				menuStocks: { 
       				number: true
   				}
			},
			messages: {
				menuStocks: { 
       				number: "请输入合法数字"
   				}
			}
		}
		);
	});*/	
	
	//修改菜单为推荐菜单或普通菜单
	function changeSubmenuRecommendatory(id,menuName,value,text){
		$.ajax({
			type: "POST",
			url: "submenu!changeSubmenuType.action",
			data: "recommended="+value+"&id="+id,
			success: function(msg){
				$.messager.anim('fade', 1000);
				$.messager.lays(200, 70);
				
				if(msg=='true'){
					$.messager.show(0, "<span style='color:green'>菜单：<strong>"+menuName+"</strong><br>已被改为<font color='red'><strong>"+text+"</strong></font>！</span>",2000);
				}else{
					$.messager.show(0, "<span style='color:green'>菜单：<strong>"+menuName+"</strong><br>未能改为<font color='red'><strong>"+text+"</strong></font>！</span>",2000);
				}
				
				
			}	
		});		
	}
	//改变菜单数量
	function changeMenuStock(id,menuName){
		//alert(id+"---"+$('#m_'+id).val());		
		var stock  = $('#stock_'+id).val();	
		$.messager.anim('fade', 1000);
		$.messager.lays(200, 70);		
		
		if(''!=stock){
			var b = /^\d+$/.test(stock);
			if(b){//输入库存为合法数字
				if(parseInt(stock)<=100){//库存量需小于等于100
					$.ajax({
					type:"POST",
					url:"submenu!changeSubmenuStock.action",
					data:"stock="+stock+"&id="+id,
					success: function(msg){
						if(msg=='true'){
							$.messager.show(0, "<span style='color:green'>菜单：<strong>"+menuName+"</strong><br>,库存量已被改为<font color='red'><strong>"+stock+"</strong></font>(份)！</span>",2000);
						}else{
							$.messager.show(0, "<span style='color:green'>菜单：<strong>"+menuName+"</strong><br>,库存量更改失败！</span>",2000);
						}
					}
					});			
				}else{
					alert('库存量不能大于100!');
				}		
			}else{//不是合法数字
				alert('请输入不小于0的数字!');			
			}		
		}else{//数量为空
			alert('菜单库存量不能为空!');		
		}
	}
	
	//改变菜单价格
	function changeMenuPrice(id,menuName){		
		var mprice  = $('#price_'+id).val();
		if (''==mprice) {
			alert("价格不能为空，请输入一个有效的数值(如有小数，保留一位小数)！");　
			return false;
		}	 
		if (isNaN(mprice)) {
			alert("请输入一个有效的数值(如有小数，保留一位小数)！"); 
			return false;
		}
		
		//获得估计成本值
		var mcost = $('#cost_'+id).val();
		if ((''==mcost) || isNaN(mcost)) {
			mcost = 0;
		}
		//获得估计利润值
		var mprofit = $('#profit_'+id).val();
		if ((''==mprofit) || isNaN(mprofit)) {
			mprofit = 0;
		}
		
		//价格应大于等于估计成本值+估计利润值
		if (parseFloat(mprice)<(parseFloat(mprofit)+parseFloat(mcost))) {
			alert("价格应大于或等于(估计成本+估计利润)的值！");　
			return false;
		}
	
		$.ajax({
			type:"POST",
			url:"submenu!changeSubmenuPrice.action",
			data:"price="+mprice+"&id="+id,
			success: function(msg){
				$.messager.anim('fade', 1000);
				$.messager.lays(300, 70);	
				if(msg=='true'){
					$.messager.show(0, "<span style='color:green'>菜单：<strong>"+menuName+"</strong><br>,价格被改为<font color='red'><strong>"+mprice+"</strong></font>(元)！</span>",3000);
				}else{
					$.messager.show(0, "<span style='color:green'>菜单：<strong>"+menuName+"</strong><br>,价格更改失败！</span>",3000);
				}
			}
		});
	}
	
	//改变菜单估计成本
	function changeMenuCost(id,menuName){		
		var mcost  = $('#cost_'+id).val();
		if (''==mcost) {
			alert("估计成本不能为空，请输入一个有效的数值(如有小数，保留一位小数)！"); 
			return false;
		}	 
		if (isNaN(mcost)) {
			alert("请输入一个有效的数值(如有小数，保留一位小数)！");　
			return false;
		}
		
		//获得价格
		var mprice = $('#price_'+id).val();
		if ((''==mprice) || isNaN(mprice)) {
			mcost = 0;
		}
		//获得估计利润值
		var mprofit = $('#profit_'+id).val();
		if ((''==mprofit) || isNaN(mprofit)) {
			mprofit = 0;
		}
		
		//估计成本值应小于等于价格-估计利润值
		if (parseFloat(mcost)>(parseFloat(mprice)-parseFloat(mprofit))) {
			alert("估计成本值应小于等于价格－估计利润值！"); 
			return false;
		}
	
		$.ajax({
			type:"POST",
			url:"submenu!changeSubmenuCost.action",
			data:"cost="+mcost+"&id="+id,
			success: function(msg){
				$.messager.anim('fade', 1000);
				$.messager.lays(300, 70);	
				if(msg=='true'){
					$.messager.show(0, "<span style='color:green'>菜单：<strong>"+menuName+"</strong><br>,估计成本被改为<font color='red'><strong>"+mcost+"</strong></font>(元)！</span>",3000);
				}else{
					$.messager.show(0, "<span style='color:green'>菜单：<strong>"+menuName+"</strong><br>,估计成本更改失败！</span>",3000);
				}
			}
		});
	}
	
	//改变菜单估计利润
	function changeMenuProfit(id,menuName){	
		var mprofit = $('#profit_'+id).val();
		if (''==mprofit) {
			alert("估计利润不能为空，请输入一个有效的数值(如有小数，保留一位小数)！"); 
			return false;
		}	 
		if (isNaN(mprofit)) {
			alert("请输入一个有效的数值(如有小数，保留一位小数)！"); 
			return false;
		}
		
		//获得价格
		var mprice = $('#price_'+id).val();
		if ((''==mprice) || isNaN(mprice)) {
			mcost = 0;
		}
		//获得估计成本
		var mcost = $('#cost_'+id).val();
		if ((''==mcost) || isNaN(mcost)) {
			mcost = 0;
		}
		
		//估计利润值应小于等于价格-估计成本值
		if (parseFloat(mprofit)>(parseFloat(mprice)-parseFloat(mcost))) {
			alert("估计利润值应小于等于价格－估计成本值！"); 
			return false;
		}
	
		$.ajax({
			type:"POST",
			url:"submenu!changeSubmenuProfit.action",
			data:"profit="+mprofit+"&id="+id,
			success: function(msg){
				$.messager.anim('fade', 1000);
				$.messager.lays(300, 70);	
				if(msg=='true'){
					$.messager.show(0, "<span style='color:green'>菜单：<strong>"+menuName+"</strong><br>,估计利润被改为<font color='red'><strong>"+mprofit+"</strong></font>(元)！</span>",3000);
				}else{
					$.messager.show(0, "<span style='color:green'>菜单：<strong>"+menuName+"</strong><br>,估计利润更改失败！</span>",3000);
				}
			}
		});
	}
	
	function selectedMenuTypeChanged(mType){
		$('#searchForm').submit();
	}
	
	　　//修改是否显示
		function changeIsDisplay(id,title,isDisplayValue,isDisplayName){
			$.ajax({
				type: "POST",
				url: "submenu!changeIsDisplay.action",
				data: "isDisplay="+isDisplayValue+"&id="+id,
				success: function(msg){
					$.messager.anim('fade', 1000);
					$.messager.lays(300, 70);
					$.messager.show(0, "<span style='color:green'>菜单：<strong>"+title+"</strong><br>使用状态 ：修改为<font color='red'><strong>"+isDisplayName+"</strong></font>！</span>",3000);
				}	
			});	
		}
		
		//修改显示顺序
		function changeSequence(id,title,sequenceValue){
			$.messager.anim('fade', 1000);
			$.messager.lays(300, 70);
			if(isNaN(sequenceValue) || sequenceValue>255 || sequenceValue<=0) {
				$.messager.show(0, "<span style='color:red'>显示顺序请输入0-255之间的整数！</span>",3000);
				return false;
			}
			$.ajax({
				type: "POST",
				url: "submenu!changeSequence.action",
				data: "sequence="+sequenceValue+"&id="+id,
				success: function(msg){
					$.messager.show(0, "<span style='color:green'>菜单：<strong>"+title+"</strong><br>排序： 修改为<font color='red'><strong>"+sequenceValue+"</strong></font>！</span>",3000);
				}	
			});	
		}
		
		//修改菜单类型
		function changeType(id,title,isTypeValue,isTypeName) {
			$.ajax({
				type: "POST",
				url: "submenu!changeType.action",
				data: "menutypeId="+isTypeValue+"&id="+id,
				success: function(msg){
					$.messager.anim('fade', 1000);
					$.messager.lays(300, 70);
					$.messager.show(0, "<span style='color:green'>菜单：<strong>"+title+"</strong><br>所属菜单类型 ：修改为<font color='red'><strong>"+isTypeName+"</strong></font>！</span>",3000);
				}	
			});	
		}
	</script>
</head>

<body>
<fieldset>
<legend>餐店菜单管理</legend>
<!-- 
<form id="searchForm" action="submenu!search.action" method="post">
<div id="search">
菜单类型:
<select name="selectedMenuType">
<option value="0">全部</option>
<s:iterator value="allMenuType">
<option value="${id}">${name }</option>
</s:iterator>
</select>
<br/>
菜单状态:
<select name="selectedStatus">
<option value="1">正常使用</option>
<option value="2">暂停使用</option>
</select>
<br/>
店长推荐:
<select name="isRecommended">
<option value="2">普通菜单</option>
<option value="1">店长推荐</option>
</select>
<br/>
菜单名称:
<input type="text" id="menuName" name="menuName"/>
<br/>
菜单编号:
<input type="text" id="menuCode" name="menuCode"/>
<br/>
编辑时间:
<input type="text" id="filter_GREATEREQ_edittime___Date" name="filter_GREATEREQ_edittime___Date" value="${param['filter_GREATEREQ_edittime___Date']}" readonly="readonly" size="20"/>之后
			<script type="text/javascript">
				function catcalc(cal) {
			        var date = cal.date;
			        var time = date.getTime();			
			        var field = $("#filter_GREATEREQ_issueTime___Date");
			        var date2 = new Date(time);
			        field.value = date2.print("%Y-%m-%d");
			    }
				Calendar.setup({
					inputField     : "filter_GREATEREQ_edittime___Date",
					ifFormat       :    "%Y-%m-%d",
					onUpdate       :    catcalc
				});
			</script>		

<br/>
<input type="submit" value="搜索"/>


</div>
</form>
<hr/>
<button type="button">库存不足菜单</button>
<hr/>-->
<br/>
<form id="mainForm" action="submenu.action" method="post">
<input type="hidden" name="pageSize" id="pageSize" value="${pageSize}"/>
<input type="hidden" name="id" id="id" />
<div id="message"><s:actionmessage theme="mytheme"/></div>

<div id="listContent">
	<display:table id="row" name="submenulist" requestURI="submenu.action" pagesize="${pageSize}" class="listView" >
		<display:column title="菜单类型" >
			<c:if test="${row.menutypeStatus eq 1}">	
				<select name="menutypeId" onchange="changeType('${row.id}','${row.name}',this.options[this.options.selectedIndex].value,this.options[this.options.selectedIndex].text)" >
					<s:iterator id="ty" value="allMenuType">
						<option value="${ty.id}" <c:if test="${ty.id == row.menutypeId}">selected</c:if>>${ty.name}</option>
					</s:iterator>
				</select>	
			</c:if>
			<c:if test="${row.menutypeStatus eq 2}">
				${row.menutypeName}
			</c:if>
		</display:column>
		<display:column title="菜单编号" property="code" sortable="true"/>	
		<display:column title="菜单名称" sortable="true">
			<a href="submenu!view.action?id=${row.id}">${row.name }</a>
		</display:column>		
		<display:column title="价格" sortable="true" >
			<input id="price_${row.id}" type="text" value="${row.price}" style="width: 30px;" onchange="changeMenuPrice('${row.id }','${row.name}')"/>
		</display:column>
		<display:column title="估计成本" sortable="true">
			<input id="cost_${row.id}" type="text" value="${row.cost}" style="width: 30px;" onchange="changeMenuCost('${row.id }','${row.name}')"/>
		</display:column>
		<display:column title="估计利润" sortable="true">
			<input id="profit_${row.id}" type="text" value="${row.profit}" style="width: 30px;" onchange="changeMenuProfit('${row.id }','${row.name}')"/>
		</display:column>
		<display:column title="库存量" sortable="true">
			<input id="stock_${row.id}" type="text" value="${row.stock}" style="width: 30px;" onchange="changeMenuStock('${row.id }','${row.name}')"/>
		</display:column>
		<display:column property="sellNumber" title="销售量" sortable="true"  />		
		<display:column  title="使用状态" sortable="true">
			<select name="status" onchange="changeIsDisplay('${row.id}','${row.name}',this.options[this.options.selectedIndex].value,this.options[this.options.selectedIndex].text)">
				<option value="1" <c:if test="${row.status=='1'}">selected</c:if>>正常使用</option>
				<option value="2" style="color:gray" <c:if test="${row.status=='2'}">selected</c:if>>暂停使用</option>
			</select>
		</display:column>
		<display:column title="排序" sortable="true"  >
			<input type="text" name="serial" value="${row.serial}" onchange="changeSequence('${row.id}','${row.name}',this.value)" style="width: 30px;" maxlength="3"/>
		</display:column>
		<display:column title="是否推荐">
			<select id="recommended" onchange="changeSubmenuRecommendatory('${row.id}','${row.name}',this.options[this.options.selectedIndex].value,this.options[this.options.selectedIndex].text)">
				<option value="1" style="color:orange" <c:if test="${row.isRecommend=='1'}">selected</c:if>>推荐菜单</option>
				<option value="2" <c:if test="${row.isRecommend=='2'}">selected</c:if>>普通菜单</option>
			</select>
		</display:column>
		<display:column  title="操作" style="width:60px;">	
			<c:if test="${row.menutypeStatus eq 1}">
				<a href="submenu!input.action?id=${row.id }&updatemenutypeid=${row.menutypeId }">修改</a>
			</c:if> 
			<!-- <a href="submenu!input.action?id=${row.id }&menutypeId=${row.menutype.id }">修改</a> -->
			<a href="#" onclick="if(confirm('确定要删除‘${row.name}’吗?'))deleteItem(${row.id})">删除</a>
		</display:column> 
		<%@ include file="/common/pagingbanner_displayTag.jsp"%>
	</display:table>	
</div>
</form>
<div><a href="submenu!input.action">增加菜单</a></div>	
<br/>
<div>
	<font color="#006676">
	提示：<br/>
	１、使用状态为‘正常使用’且‘库存量’大于１的菜单才能在前台菜单栏显示（订餐）；<br/>
	２、是否推荐：‘推荐菜单’在前台推荐菜单栏显示，‘普通菜单’在前台普通菜单栏按类型显示；<br/>	
	</font>
</div>
</fieldset>
</body>
</html>




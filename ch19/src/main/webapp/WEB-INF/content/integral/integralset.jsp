<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net/el" prefix="display" %>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ include file="/common/meta.jsp"%>
<head>
	<title>积分兑换餐币设置</title>
	<link href="${ctx}/css/default.css" type="text/css" rel="stylesheet" />
	<script src="${ctx}/js/jquery.js" type="text/javascript"></script>
	<script src="${ctx}/js/effects/jquery.messager.js"></script>
	<script  src="${ctx}/js/form.js" type="text/javascript"></script>
	<script type="text/javascript">	 		
		var deleteAction = 'integralset!delete.action';
		
		/*//修改显示
		function changeIsDisplay(id,title,isDisplayValue,isDisplayName){
			$.ajax({
				type: "POST",
				url: "integralset!changeIsDisplay.action",
				data: "isDisplay="+isDisplayValue+"&id="+id,
				success: function(msg){
					$.messager.anim('fade', 1000);
					$.messager.lays(300, 70);
					if (msg == "true") {
						$.messager.show(0, "<span style='color:green'>积分兑换餐币设置：<strong>"+title+"</strong><br>是否可用 ：修改为<font color='red'><strong>"+isDisplayName+"</strong></font>！</span>",3000);
					} else {
						$.messager.show(0, "抱歉！数据有误，积分兑换餐币设置 是否可用修改不成功！",3000);
					}
				}	
			});	
		}*/
	</script>
</head>
<body>
<fieldset style="width: 98%;">
	<legend>积分兑换餐币设置</legend>
<div id="message"><s:actionmessage theme="mytheme"/></div>

<form id="mainForm" action="integralset.action" method="post">
<!-- 隐藏关于我们内容   ID -->
<input type="hidden" name="id" id="id" /> 
<input type="hidden" name="pageSize" id="pageSize" value="${page.pageSize}"/>
<input type="hidden" name="page.pageNo" id="pageNo" value="${page.pageNo}"/>
<input type="hidden" name="page.orderBy" id="orderBy" value="${page.orderBy}"/>
<input type="hidden" name="page.order" id="order" value="${page.order}" />

<div id="listContent" align="center">

<display:table id="row" name="integralsetlist" requestURI="integralset.action" pagesize="${pageSize}" class="listView" defaultsort="4" defaultorder="ascending" style="listView">
		<display:column property="integral" title="兑换积分" sortable="true" />
		<display:column property="mealCurrency" title="生成餐币（个）" sortable="true" /> 	
		<display:column title="是否使用"  >
			<c:if test="${row.status == '1'}">使用</c:if>
			<c:if test="${row.status == '2'}"><font style="color:gray">不使用</font></c:if>
		</display:column> 
		<display:column property="remark" title="备注" sortable="true" /> 
		<display:column property="edittime" title="编辑时间" sortable="true" format="{0,date,yyyy-MM-dd}"/>	
		<display:column title="操作" style="width:60px;">	 
			<a href="integralset!input.action?id=${row.id }">修改</a> 
			<a href="javascript:if(confirm('确定要删除积分设置吗?'))deleteItem(${row.id})">删除</a>
		</display:column>
		<%@ include file="/common/pagingbanner_displayTag.jsp"%>
	</display:table>
</div>
<%//@ include file="/common/pagingbanner.jsp"%>
</form>
<div><a href="integralset!input.action">增加新积分兑换设置</a></div>	
<br/>
<div>
	<font color="#006676">
	提示：<br/>
	1、积分与餐币的关系：如500积分兑换10元餐币；<br/> 
	2、餐币的作用：餐币可以代替现金订餐；<br/> 
	3、会员积分的来源：会员成功订餐，则支付的现金值转换成会员的积分值(1元现金＝１个积分)，<br/> 
	4、是否使用：不适用的积分兑换餐币的记录在会员积分兑换界面不显示。<br/> 
	5、积分兑换餐币的可用记录请控制为1条。<br/> 
	</font>
</div>
</fieldset>
</body>
</html>

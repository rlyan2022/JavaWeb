<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net/el" prefix="display" %>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>销售统计</title>
	<%@ include file="/common/meta.jsp"%>
	<%@ include file="/js/widgets/calendar/calendar.jsp"%>
	<link href="${ctx}/css/default.css" type="text/css" rel="stylesheet" />
	<script src="${ctx}/js/jquery.js" type="text/javascript"></script>
	<script src="${ctx}/js/effects/jquery.messager.js"></script>
	<script  src="${ctx}/js/form.js" type="text/javascript"></script>
	<script type="text/javascript">					
		$(document).ready(function(){
			$("#inputForm").validate({
				 rules: { 
					remark: { 
	    				maxlength: 200
	    			}   		
				},
				messages: {
					remark:{
	    				maxlength: "备注请控制在200个字符内"
	    			} 
				}
			});
		});
		
		var deleteAction = 'salestatistics!delete.action';
							 
		function countSave(){					
			document.inputForm.action = "salestatistics!save.action";
			document.inputForm.submit(); 
		} 
		
		function countInput(){					
			document.inputForm.action = "salestatistics!input.action";
			document.inputForm.submit(); 
		}
	</script>
</head>
<body>
<fieldset>
	<legend>销售统计</legend>
<div id="message"><s:actionmessage theme="mytheme"/></div>


<div id="listContent">
<form id="inputForm" name="inputForm" action="salestatistics!search.action" method="post">
<input type="hidden" name="save_values" value="${save_values }"/>

<div><font color="#006676"><b>请输入销售统计条件：</b></font></div>
<table width="98%">
	<tr>
		<td align="right">开始日期:</td>
		<td>
			<input id="c_startDate" type="text" name="c_startDate" size="10" value="${c_startDate}" readonly="readonly" />
				<script type="text/javascript">
						function catcalc(cal) {
					        var date = cal.date;
					        var time = date.getTime()
					
					        var field = $("#c_startDate");
					        var date2 = new Date(time);
					        field.value = date2.print("%Y-%m-%d");
					    }
						Calendar.setup({
							inputField     : "c_startDate",
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
			<input id="c_endDate" type="text" name="c_endDate" size="10" value="${c_endDate}" readonly="readonly" />
				<script type="text/javascript">
						function catcalc(cal) {
					        var date = cal.date;
					        var time = date.getTime()
					
					        var field = $("#c_endDate");
					        var date2 = new Date(time);
					        field.value = date2.print("%Y-%m-%d");
					    }
						Calendar.setup({
							inputField     : "c_endDate",
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
			<select name="oldStatus">
				<option value="">全部</option>
				<option value="1" <c:if test="${oldStatus==1}">selected</c:if>>等待处理</option>
				<option value="2" <c:if test="${oldStatus==2}">selected</c:if>>正在配餐</option>
				<option value="3" <c:if test="${oldStatus==3}">selected</c:if>>已送餐</option>
				<option value="4" <c:if test="${oldStatus==4}">selected</c:if>>完成</option>
				<option value="5" <c:if test="${oldStatus==5}">selected</c:if>>取消</option>
			</select>
		</td>
		<td align="right">付款方式:</td>
		<td>
			<select name="payMode">
				<option value="">全部</option>
				<option value="1" <c:if test="${payMode==1}">selected</c:if>>上门收费</option>
				<option value="2" <c:if test="${payMode==2}">selected</c:if>>餐币付费</option> 
			</select>
		</td>
		<td>
			<input type="button" onclick="return countInput();" value="统  计"/><br/>
			<input type="submit" value="搜  索"/>
		</td>
	</tr>
</table>
<br/>
<s:if test="sign != null">
	<div><font color="#006676"><b>销售统计结果：</b></font></div>
	<table width="98%">
	<tr>
		<td align="center"><b>统计时间段</b></td>
		<td align="center"><b>送餐状态</b></td>
		<td align="center"><b>付款方式</b></td>
		<td align="center"><b>订单数量</b> </td>
		<td align="center"><b>菜单数量</b></td>
		<td align="center"><b>销售总价</b></td>
		<td align="center"><b>估计成本</b></td>
		<td align="center"><b>估计利润</b></td>
	</tr>
	<tr>
		<td><fmt:formatDate value="${startDate}" pattern="yyyy年MM月dd日"/>-<fmt:formatDate value="${endDate}" pattern="yyyy年MM月dd日"/></td>
		<td align="center">
			<c:choose>
				<c:when test="${oldStatus==1}">等待处理</c:when>
				<c:when test="${oldStatus==2}">正在配餐</c:when>
				<c:when test="${oldStatus==3}">已送餐</c:when>
				<c:when test="${oldStatus==4}">完成</c:when>
				<c:when test="${oldStatus==5}">取消</c:when>
				<c:otherwise>全部</c:otherwise>
			</c:choose> 
		</td>
		<td align="center">
			<c:choose>
				<c:when test="${payMode==1}">上门收费</c:when>
				<c:when test="${payMode==2}">餐币付款</c:when> 
				<c:otherwise>全部</c:otherwise>
			</c:choose>  
		</td> 
		<td align="center">${bookingCount}</td>
		<td align="center">${menuCount}</td>
		<td align="center"><font style="color:red; font-size: 16px;">${price }</font></td>
		<td align="center">${cost}</td>
		<td align="center"><font style="color:orange; font-size: 16px;">${profit}</font></td>
	</tr>
	<tr>
		<td align="right"><b>备注：</b></td> 
		<td colspan="6"><textarea id="remark" name="remark" style="width:400px;height:40px;" class="required">${remark}</textarea></td>
		<td><input type="button" onclick="countSave();" value="保  存"/></td>
	</tr>
</table>
<br/>
</s:if>
</form>
<br/>
<form id="mainForm" action="salestatistics.action" method="post">
<!-- 隐藏关于我们内容   ID -->
<input type="hidden" name="id" id="id" />  
<input type="hidden" name="pageSize" id="pageSize"/>
<input type="hidden" name="page.pageNo" id="pageNo" value="${page.pageNo}"/>
<input type="hidden" name="page.orderBy" id="orderBy" value="${page.orderBy}"/>
<input type="hidden" name="page.order" id="order" value="${page.order}" />

<div><font color="#006676"><b>销售统计保存的记录：</b></font></div>
<display:table id="row" name="salestatisticslist" requestURI="salestatistics.action" pagesize="${pageSize}" class="listView" defaultsort="4" defaultorder="ascending" style="listView">	
		<display:column property="startDate" title="开始日期" sortable="true" format="{0,date,yyyy-MM-dd}"/>
		<display:column property="endDate" title="结束日期" sortable="true" format="{0,date,yyyy-MM-dd}"/>
		<display:column title="送餐状态" sortable="true">
			<c:choose>
				<c:when test="${row.oldStatus==1}">等待处理</c:when>
				<c:when test="${row.oldStatus==2}">正在配餐</c:when>
				<c:when test="${row.oldStatus==3}">已送餐</c:when>
				<c:when test="${row.oldStatus==4}"><font style="color:red; font-size: 16px;">完成</font></c:when>
				<c:when test="${row.oldStatus==5}">取消</c:when>
				<c:otherwise>全部</c:otherwise>
			</c:choose> 
		</display:column>
		<display:column title="付款方式" sortable="true">
			<c:choose>
				<c:when test="${row.payMode==1}">上门收费</c:when>
				<c:when test="${row.payMode==2}"><font style="color:blue; font-size: 16px;">餐币付款</font></c:when> 
				<c:otherwise>全部</c:otherwise>
			</c:choose>
		</display:column>
		<display:column property="bookingCount" title="订单数量" sortable="true" />
		<display:column property="menuCount" title="菜单数量" sortable="true" />
		<display:column title="销售总价" sortable="true">
			<font style="color:red; font-size: 16px;">${row.price }</font>
		</display:column>
		<display:column property="cost" title="估计成本" sortable="true" />
		<display:column title="估计利润" sortable="true">
			<font style="color:orange; font-size: 16px;">${row.profit}</font>
		</display:column>
		<display:column property="remark" title="备注" />
		<display:column property="editor.name" title="统计人" sortable="true" />
		<display:column property="edittime" title="统计日期" sortable="true" format="{0,date,yyyy-MM-dd HH:mm}"/>	
		<display:column title="操作" >	 
			<a href="javascript:if(confirm('确定要删除该销售统计吗?'))deleteItem(${row.id})">删除</a>
		</display:column>
		<%@ include file="/common/pagingbanner_displayTag.jsp"%>
	</display:table>
 </form>
 
</div>
</fieldset>
</body>
</html>
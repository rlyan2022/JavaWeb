<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>默认页面</title>
	<%@ include file="/common/meta.jsp"%>
	<link href="${ctx}/css/default.css" type="text/css" rel="stylesheet" />	
	<style>
		body {margin:0px;}
		a#blackLink:link,#blackLink:visited {
			COLOR: #000000;
			font-size: 12px;
			TEXT-DECORATION: none;
			TEXT-TRANSFORM: none;
		}			
		a#blackLink:hover {
			COLOR: GREEN;
		} 	
		.STYLE2 {
					color: #DD5800;
					font-weight: bold;
				}
		-->
    </style>
</head>
<body>
	<div id="content">
	<table style="margin-left: 2px;width: 90%;">
		<tr>
			<td valign="top">
				 <!-- 今日菜单销售情况 start-->
				<fieldset>
				<table width="100%">
					<tr>
						<td width="100%" height="20" bgcolor="#e3ecf1" colspan="8" valign="baseline">
							<font color="#DD5800" size="3px;">今日菜单销售情况 top 20</font>
						</td>
					</tr>
					<tr>
						<th>菜单名称</th>
						<th>价格</th>
						<th>已销售</th>
						<th>库存量</th>
						<th>菜单名称</th>
						<th>价格</th>
						<th>已销售</th>
						<th>库存量</th>
					</tr>
					<%int sellCount=0; %>
					<s:iterator id="ofl" value="orderformlist">
						<%if (sellCount%2==0){ %>
						<tr>
							<td style="border-bottom:1px #cccccc dashed;"> 
								<%=sellCount+1 %>.&nbsp;${ofl.mainMenuName }
							</td>
							<td style="border-bottom:1px #cccccc dashed;" align="center">
								<font class="STYLE2">${ofl.price }</font>元	
							</td>
							<td style="border-bottom:1px #cccccc dashed;" align="center">
								<font class="STYLE2">${ofl.count }</font>份	
							</td>
							<td style="border-bottom:1px #cccccc dashed;" align="center">
								<font class="STYLE2">${ofl.stock }</font>份	
							</td>
						<%} else {%>
							<td style="border-bottom:1px #cccccc dashed;">
								<%=sellCount+1 %>.&nbsp;${ofl.mainMenuName }
							</td>
							<td style="border-bottom:1px #cccccc dashed;" align="center">
								<font class="STYLE2">${ofl.price }</font>元	
							</td>
							<td style="border-bottom:1px #cccccc dashed;" align="center">
								<font class="STYLE2">${ofl.count }</font>份
							</td>
							<td style="border-bottom:1px #cccccc dashed;" align="center">
								<font class="STYLE2">${ofl.stock }</font>份	
							</td>
						</tr>
						<%} sellCount++;%>		
					</s:iterator>
					<%
						//菜单为奇数时补全html
						if (sellCount%2 > 0) { %>
						<td style="border-bottom:1px #cccccc dashed;" colspan="2"> 
						</tr>
					<%} %>
				</table>
				</fieldset>
				 <!-- 今日菜单销售情况 end-->
				<br/><br/>
				<!--今日销售统计-->
				<fieldset style="margin-left: 3px;">
					<table width="100%">
						<tr>
						<td width="100%" height="20" bgcolor="#e3ecf1" colspan="6" valign="baseline">
							<font color="#DD5800" size="3px;">餐店统计</font>
						</td>
					</tr>
					<tr>
						<td>
							<p><b>今日订单：</b>共<font class="STYLE2">${orderCount }</font>份，总计<font class="STYLE2">${price }</font>元</p>
							<p><b>菜单评价：</b>共<font class="STYLE2">${evalCount }</font>条记录未处理</p>
							<p><b>客户留言：</b>共<font class="STYLE2">${leaveCount }</font>条记录未回复</p>
						</td>
					</tr>
					</table>
				</fieldset>
			</td>
						 
			<td style="border-right:2px GREEN dashed;"  width="1">&nbsp;</td>
			
			<!-- 热门菜单 top 20 开始-->
			<td valign="top">
				<fieldset style="margin-left: 3px;">
				<table width="100%">
					<tr>
						<td width="100%" height="20" bgcolor="#e3ecf1" colspan="6" valign="baseline">
							<font color="#DD5800" size="3px;">热门菜单 top 20</font>
						</td>
					</tr>
					<tr>
						<th>菜单名称</th>
						<th>价格</th>
						<th>已销售</th>	
						<th>菜单名称</th>
						<th>价格</th>
						<th>已销售</th>
					</tr>
					<%int count=0; %>
					<s:iterator id="pmenu" value="submenulist">
						<%if (count%2==0){ %>
						<tr>
							<td style="border-bottom:1px #cccccc dashed;"> 
								<%=count+1 %>.&nbsp;${pmenu.name }
							</td>
							<td style="border-bottom:1px #cccccc dashed;" align="center">
								<font class="STYLE2">${pmenu.price }</font>元	
							</td>
							<td style="border-bottom:1px #cccccc dashed;" align="center">
								<font class="STYLE2">${pmenu.sellNumber }</font>份	
							</td>
						<%} else {%>
							<td style="border-bottom:1px #cccccc dashed;">
								<%=count+1 %>.&nbsp;${pmenu.name }
							</td>
							<td style="border-bottom:1px #cccccc dashed;" align="center">
								<font class="STYLE2">${pmenu.price }</font>元	
							</td>
							<td style="border-bottom:1px #cccccc dashed;" align="center">
								<font class="STYLE2">${pmenu.sellNumber }</font>份
							</td>
						</tr>
						<%} count++;%>		
					</s:iterator>
					<%
						//菜单为奇数时补全html
						if (count%2 > 0) { %>
						<td style="border-bottom:1px #cccccc dashed;" colspan="3"> 
						</tr>
					<%} %>
				</table>
				</fieldset>
				<!-- 热门菜单 top 20 结束-->
				<br/>
			</td>
		</tr>
	</table>
	</div>	 	
</body>
</html>
<%@ page language="java" pageEncoding="UTF-8"%>
<%//@ taglib uri="http://java.fckeditor.net" prefix="FCK"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>菜单管理</title>
		<link href="${ctx}/css/default.css" type="text/css" rel="stylesheet" />
		<link href="${ctx}/js/validate/jquery.validate.css" type="text/css"	rel="stylesheet" />
		<script type='text/javascript' src='${ctx}/js/jquery.js'></script>
		<script src="${ctx}/js/form.js" type="text/javascript"></script>
		<!-- 表单验证js -->
		<script src="${ctx}/js/validate/jquery.validate.js"	type="text/javascript"></script>
		<script src="${ctx}/js/validate/messages_cn.js" type="text/javascript"></script>
		
	</head>
	<body>
		<div id="message">
			<s:actionmessage theme="mytheme" />
		</div>
		<fieldset>
		<legend>查看菜单信息明细</legend>
		<div id="listContent">
			<form id="inputForm" action="submenu.action" method="post">
				<input type="hidden" name="id" value="${id}" />
				<table class="inputView">
					<tr>
						<td nowrap="nowrap" align="right"> 菜单类型：</td>
						<td>
							${menutype.name}
						</td>
					</tr>
					<tr>
						<td nowrap="nowrap" align="right">菜单编号：</td>
						<td>
							${code}
						</td>
					</tr>			
					<tr>
						<td nowrap="nowrap" align="right">菜单名称：</td>
						<td>
							${name}
						</td>
					</tr>		
					<s:if test="photo != null">
						<tr>
							<td align="right">菜单图片：</td>
							<td>
								<img id="menu_img" src="${ctx}/upload/submenu/${photo}"/>
							</td>
						</tr>
					</s:if>		
					<tr>
						<td nowrap="nowrap" align="right">排序：</td>
						<td>
							${serial}
						</td>
					</tr>	
					<tr>
						<td nowrap="nowrap" align="right">单价：</td>
						<td>
							${price} 元
						</td>
					</tr>
					<tr>
						<td nowrap="nowrap"  align="right">估计成本：</td>
						<td>
							${cost} 元
						</td>
					</tr>
					<tr>
						<td nowrap="nowrap"  align="right">估计利润：</td>
						<td>
							${profit} 元
						</td>
					</tr>					
					<tr>
						<td nowrap="nowrap" align="right">库存量：</td>
						<td>${stock} 份</td>
					</tr>
					<tr> <td nowrap="nowrap" align="right">库存量：</td>
						<td>${sellNumber} 份</td>
					</tr>
					<tr>
						<td nowrap="nowrap" align="right">状态：</td>
						<td>
							<s:if test="status==\"1\"">正常使用</s:if>
							<s:if test="status==\"2\"">暂停使用</s:if>
						</td>
					</tr>		
					
					<tr>
						<td nowrap="nowrap">是否推荐：</td>
						<td>
							<c:if test="${isRecommend=='2'}">不推荐</c:if>
							<c:if test="${isRecommend=='1'}">推荐</c:if>
						</td>
					</tr>		
					<tr>
						<td nowrap="nowrap" align="right">编辑人：</td>
						<td>${editor.name}</td>
					</tr>
						
					<tr>
						<td nowrap="nowrap" align="right">编辑时间：</td>
						<td>${edittime}</td>
					</tr>		
					<tr>
						<td nowrap="nowrap">推荐理由：</td>
						<td>
							${recommendReason}
						</td>
					</tr>
					<tr>
						<td nowrap="nowrap">菜单描述:</td>
						<td>
							${description}
						</td>
					</tr>
					<tr>
						<td colspan="2" align="center" height="50">
							<input type="button" value="返   回 " onclick="history.back()" />
						</td>
					</tr>
				</table>
			</form>
		</div>
		</fieldset>
	</body>
</html>

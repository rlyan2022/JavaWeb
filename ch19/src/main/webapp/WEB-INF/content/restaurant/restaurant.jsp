<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>餐店基本信息</title>
	<link href="${ctx}/css/default.css" type="text/css" rel="stylesheet" />
	<link href="${ctx}/js/validate/jquery.validate.css" type="text/css" rel="stylesheet" />
	
</head>
<body>
<div id="listContent"> 
	<fieldset style="width:95%">
		<legend>${name}&nbsp;餐店基本信息管理</legend>
		<div id="message"><s:actionmessage theme="mytheme"/></div> 
			<br/>
			<s:iterator value="restaurants">
				<table cellpadding="0" cellspacing="0" style="margin-left:100px;" class="inputView">
					<tr>
						<td nowrap="nowrap" align="right" height="30px;"><b>店名：</b></td>
						<td>${name}</td>
					</tr>
					<tr>
						<td nowrap="nowrap" align="right" height="30px;"><b>前台LOGO：</b></td>
						<td>
							<s:if test="logo != null">
								<img src="${ctx}/upload/restaurant/${logo}" alt="${ctx}/upload/restaurant/${logo}" width="320px;" height="75px;"/><br/>
								&nbsp;&nbsp;<a href="restaurant!logo.action?id=${id }">重新上传</a>
							</s:if>
							<s:else>
								未上传餐店LOGO<br/>
								&nbsp;&nbsp;<a href="restaurant!logo.action?id=${id }">上传LOGO</a>
							</s:else>
						</td>
					</tr>
					<tr>
						<td nowrap="nowrap" align="right" height="30px;"><b>地址：</b></td>
						<td>${address}</td>
					</tr>
					<tr>
						<td nowrap="nowrap" align="right" height="30px;"><b>地图：</b></td>
						<td>
							<s:if test="map != null">
								<img src="${ctx}/upload/restaurant/${map}" alt="${ctx}/upload/restaurant/${map}"/>
							</s:if>
							<s:else>
								未上传餐店地图
							</s:else>
						</td>
					</tr>
					<tr>
						<td nowrap="nowrap" align="right" height="30px;"><b>餐店负责人：</b></td>
						<td>${principal}</td>
					</tr>
					<tr>
						<td nowrap="nowrap" align="right" height="30px;"><b>联系电话：</b></td>
						<td>${phone}</td>
					</tr>
					<tr>
						<td nowrap="nowrap" align="right" height="30px;"><b>手机：</b></td>
						<td>${mobile }</td>
					</tr>
					<tr>
						<td nowrap="nowrap" align="right" height="30px;"><b>邮箱地址：</b></td>
						<td>${email}</td>
					</tr>
					<tr>
						<td nowrap="nowrap" align="right" height="30px;"><b>温馨提示：</b></td>
						<td>
							${service}
						</td>
					</tr>
					<tr>
						<td nowrap="nowrap" align="right" height="30px;"><b>描述：</b></td>
						<td>${description}</td>
					</tr>
					
					<tr>
						<td colspan="2" align="center" height="50">											
							<a href="restaurant!input.action?id=${id }">修 改</a>						
						</td>
					</tr>
					<tr>
						<td nowrap="nowrap" align="right" height="30px;"><b>广告图片：</b></td>
						<td>
							<s:if test="ad != null">
								<img src="${ctx}/upload/restaurant/${ad}" alt="${ctx}/upload/restaurant/${ad}" width="650px;" height="80px;"/><br/>
								&nbsp;&nbsp;<a href="restaurant!ad.action?id=${id }">重新上传</a>
							</s:if>
							<s:else>
								未上传餐店广告图片<br/>
								&nbsp;&nbsp;<a href="restaurant!ad.action?id=${id }">上传广告图片</a>
							</s:else>
						</td>
					</tr>
				</table>
			</s:iterator>
	</fieldset>
</div>
</body>
</html>

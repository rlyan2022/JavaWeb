<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>餐店基本信息管理</title>
	<link href="${ctx}/css/default.css" type="text/css" rel="stylesheet" />
	<link href="${ctx}/js/validate/jquery.validate.css" type="text/css" rel="stylesheet" />		
	<script src="${ctx}/js/jquery.js" type="text/javascript"></script>
	<script src="${ctx}/js/validate/jquery.validate.js" type="text/javascript"></script>
	<script src="${ctx}/js/validate/messages_cn.js" type="text/javascript"></script> 
	
	<script type="text/javascript">
	<!--
		//检查插入是否为图片
		var img=null;
		var upstate = true;
		function chkimg(inp)
		{
			if(img)img.removeNode(true);
			img=document.createElement("img");
			img.attachEvent("onreadystatechange",isimg);
			img.attachEvent("onerror",notimg);		
			img.src=inp;
		}
		function notimg()
		{
			alert("您插入的不是图片，请重新选择插入");
			clearupload();
			//return false;
		}
		function isimg()
		{
			//show.insertAdjacentElement("BeforeEnd",img);
			if (img.fileSize/1024>100) {
				alert("您上传图片的大小是："+img.fileSize/1024+"K！\n\n请重新上传小于100K的图片！\n\n大于100K的图片系统将忽略保存！");
				clearupload();	 
			} else {
				//显示图像
				show.insertAdjacentElement("BeforeEnd",img);
			}
		//show1.innerHTML = "图片大小" + img.fileSize/1024 +"K<br />图片宽度"+ img.offsetWidth +"<br />图片高度"+ img.offsetHeight;
		} 		
		//清除图片框的内容
		function clearupload()
		{
			var obj = document.getElementById('upload');
			obj.outerHTML = obj.outerHTML;
			document.getElementById('upload').value="";			
		}	
	//-->
	</script>
</head>
<body>
<div id="listContent"> 
	<fieldset style="width:95%;">
		<legend><s:if test="id == null">上传餐店LOGO</s:if><s:else>修改餐店LOGO－${name}</s:else></legend>
    <div id="inputContent">
    <form id="inputForm" action="restaurant!savelogo.action" method="post" enctype="multipart/form-data">
	<input type="hidden" name="id" value="${id}" />
	<table class="inputView" style="margin-left:20px;">
		<tr height="30">
			<td nowrap="nowrap" align="right">店名：</td>
			<td>${name}</td>
		</tr>
		<tr height="30">
			<td nowrap="nowrap" align="right">地址：</td>
			<td>${address}</td>
		</tr>
		<tr height="30">
			<td nowrap="nowrap" align="right">LOGO：</td>
			<td><input type="file" name="upload" size="30" maxlength="100"/><font color="#006676">不上传图片请置空</font></td>
		</tr>
		<s:if test="logo != null">
			<tr height="30"> 
				<td>已上传的LOGO：</td>
				<td>
					<img src="${ctx}/upload/restaurant/${logo}" width="320" height="75"/>				
				</td>
			</tr>
		</s:if>
		 
		<tr height="30">
			<td colspan="2" align="center" height="50">
				<input type="submit" value=" 提交 " />&nbsp;&nbsp;
				<input type="button" value=" 返回 " onclick="history.back()" />
			</td>
		</tr>
	</table>
	</form>
	</div>
	</fieldset>
</div>
</body>
</html>

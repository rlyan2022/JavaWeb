<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
	<title>${title}</title>
	<%@ include file="/common/meta.jsp"%> 
	<link href="${ctx}/css/default.css" type="text/css" rel="stylesheet" />
	<link href="${ctx}/js/validate/jquery.validate.css" type="text/css" rel="stylesheet" />		
	<script src="${ctx}/js/jquery.js" type="text/javascript"></script>
	<script src="${ctx}/js/validate/jquery.validate.js" type="text/javascript"></script>
	<script src="${ctx}/js/validate/messages_cn.js" type="text/javascript"></script>
	<script src="${ctx}/js/widgets/kindeditor/kindeditor.js" type="text/javascript" charset="utf-8" ></script>
	
	<script type="text/javascript">

		$(document).ready(function(){
		//聚焦第一个输入框
		//$("#loginName").focus();
		//为inputForm注册validate函数
		$("#inputForm").validate({
			rules: { 
           		memberName: {
           			required: true
           		},       		
   				email:"email"
			}
		});
		});
		
	</script>
	<script language="javascript">
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
			if (img.fileSize/1024>20) {
				alert("您上传图片的大小是："+img.fileSize/1024+"K！\n\n请重新上传小于20K的图片！\n\n大于20K的图片系统将忽略保存！");
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
	
<div id="message"><s:actionmessage theme="mytheme"/></div>
<br/>
	<div style="margin-left: 30px;"><b>修改您的个人信息</b></div>
<div id="listContent" style="margin-left: 30px;">
	<form id="inputForm" name="inputForm" action="member!save.action" method="post" enctype="multipart/form-data">
	<input type="hidden" id="id" name="id" value="${id}" />
	<table class="inputView">
		<tr>
			<td nowrap="nowrap" align="right"><b>用户帐号：</b></td>
			<td><input type="text" name="loginCode" id="loginCode" size="20" value="${loginCode}" readonly="readonly"/></td>
		</tr>	
		<tr>
			<td nowrap="nowrap" align="right"><b>姓名：</b></td>
			<td><input type="text" name="memberName" size="20" value="${memberName}" maxlenght="20"/></td>
		</tr>
		<tr>
			<td nowrap="nowrap" align="right"><b>证件号码：</b></td>
			<td><input type="text" name="idcardNo" size="20" value="${idcardNo}" maxlenght="18"/></td>
		</tr>
		<tr>
			<td nowrap="nowrap" align="right"><b>性别：</b></td>
			<td>
				<select name="sex">
					<option value="1" <c:if test="${sex eq 1 }">selected</c:if>>女</option>
					<option value="2" <c:if test="${sex eq 2 }">selected</c:if>>男</option>
					<option value="">&nbsp;</option>
				</select>		
			</td>
		</tr>
		<tr>
			<td align="right"><b>头像：</b></td>
			<td>
				<input type="file" name="upload" size="30" onchange="chkimg(this.value)"/>&nbsp;
				<input name="btn" onclick="clearupload();" type="button" value="清空"/><br/>
				<font color="#006676">请上传大小在20K以下的图片</font> 			
				<div id="show" ></div> 
			</td>
		</tr>  
		<s:if test="photo!=null">
			<tr>
				<td align="right"><b>图片预览：</b></td>
				<td>
					<img id="menu_img" width="120" height="80"
										src="${ctx}/upload/member/${photo}"/>
				</td>
			</tr>
		</s:if>	
		<tr>
			<td nowrap="nowrap" align="right"><b>使用状态：</b></td>
			<td>
				<select name="status" disabled="disabled">
					<option value="1" <c:if test="${status eq 1 }">selected</c:if>>正常使用</option>
					<option value="2" <c:if test="${status eq 2 }">selected</c:if>>停用</option>
					<option value="">&nbsp;</option>
				</select>		
			</td>
		</tr>
		<tr>
			<td nowrap="nowrap" align="right"><b>邮箱地址：</b></td>
			<td><input type="text" name="email" size="30" value="${email}" maxlenght="50"/></td>
		</tr>	
		<tr>
			<td nowrap="nowrap" align="right"><b>送餐地址：</b></td>
			<td><input type="text" name="assignAddress" size="30" value="${assignAddress}" maxlenght="50"/></td>
		</tr>
		<tr>
			<td nowrap="nowrap" align="right"><b>送餐电话：</b></td>
			<td><input type="text" name="relationPhone" size="30" value="${relationPhone}" maxlenght="30"/></td>
		</tr>
		<tr>
			<td nowrap="nowrap" align="right"><b>手机号码：</b></td>
			<td><input type="text" name="mobile" size="30" value="${mobile}" maxlenght="30"/></td>
		</tr>	
		<tr>
			<td nowrap="nowrap" align="right"><b>备注：</b></td>
			<td><textarea title="备注请控制在100个汉字以内" name="remark" style="width:400px;height:60px;" >${remark}</textarea></td>
		</tr>	

		<tr>
			<td colspan="2" align="center" height="50">
				<input type="submit" value=" 提 交 " />&nbsp; 
				<input type="button" value=" 取 消 " onclick="history.back();" />
			</td>
		</tr>
	</table>
	</form>
</div>
</body>
</html>
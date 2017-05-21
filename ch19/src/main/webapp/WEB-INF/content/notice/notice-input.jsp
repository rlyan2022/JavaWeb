<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
	<title>${title}</title>
	<%@ include file="/common/meta.jsp"%>
	<%@ include file="/js/widgets/calendar/calendar.jsp"%>
	<link href="${ctx}/css/default.css" type="text/css" rel="stylesheet" />
	<link href="${ctx}/js/validate/jquery.validate.css" type="text/css" rel="stylesheet" />		
	<script src="${ctx}/js/jquery.js" type="text/javascript"></script>
	<script src="${ctx}/js/validate/jquery.validate.js" type="text/javascript"></script>
	<script src="${ctx}/js/validate/messages_cn.js" type="text/javascript"></script>
	<script src="${ctx}/js/widgets/kindeditor/kindeditor.js" type="text/javascript" charset="utf-8" ></script>
	
	<script type="text/javascript">
		KE.show({
			id:'content'
		});

		$(document).ready(function(){
			$("#title").focus();
			$("#inputForm").validate({
				 rules: { 
					title: { 
	        			required: true
	    			},
	    			summary: {
	    				required: true,
	    				maxlength: 200
	    			}   		
				},
				messages: {
					summary:{
	    				maxlength: "摘要请控制在200个字符(100个汉字)内"
	    			} 
				}
			});
		});
		function checkForm(){		
			
		}
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
<fieldset>
	<legend><s:if test="id == null">增加网站公告信息</s:if>	<s:else>修改网站公告记录 - ${title}</s:else></legend>
<div id="message"><s:actionmessage theme="mytheme"/></div>
<div id="listContent">
	<form id="inputForm" name="inputForm" action="notice!save.action" method="post" enctype="multipart/form-data" onsubmit="return checkSubmit()">
	<input type="hidden" id="id" name="id" value="${id}" />
	<table class="inputView">
		<tr>
			<td nowrap="nowrap" align="right">公告标题：</td>
			<td><input type="text" id="title" name="title" size="30" value="${title}" class="required"/></td>
		</tr>	
		<tr>
			<td nowrap="nowrap" align="right">标题图片：</td>
			<td>
				<input type="file" name="upload" size="30" onchange="chkimg(this.value)"/>&nbsp;
				<input name="btn" onclick="clearupload();" type="button" value="清空"/><br/>
				<font color="#006676">请上传大小在20K以下的图片</font> 			
				<div id="show" ></div> 
			</td>
		</tr>
		<s:if test="titleIco != null">
			<tr>
				<td nowrap="nowrap" align="right">已上传的标题图片：</td>
				<td><img src="${ctx}/upload/notice/${titleIco}" width="130" height="100" /></td>
			</tr>
		</s:if>
		<tr>
			<td nowrap="nowrap" align="right">是否显示：</td>
			<td>
				<select name="isDisplay">
					<option value="1" <c:if test="${isDisplay eq 1 }">selected</c:if>>是</option>
					<option value="2" <c:if test="${isDisplay eq 2 }">selected</c:if>>否</option>
				</select>		
				<font color="#006676">选择‘否’表示该公告不在前台公告栏显示</font>
			</td>
		</tr>
		<tr>
			<td nowrap="nowrap" align="right">是否置顶：</td>
			<td>
				<select name="isTop">
					<option value="2" <c:if test="${isTop eq 2 }">selected</c:if>>否</option>
					<option value="1" <c:if test="${isTop eq 1 }">selected</c:if>>是</option>
				</select>	
				<font color="#006676">选择‘是’则该公告在前台公告栏最顶端显示</font>	
			</td>
		</tr>
		<s:if test="id != null">
			<tr>
				<td nowrap="nowrap" align="right">发布人：</td>
				<td><input type="text" name="recordMan" size="20" value="${recordMan}" /></td>
			</tr>
			<tr>
				<td nowrap="nowrap" align="right">发布时间：</td>
				<td>${recordTime}</td>
			</tr>
			<tr>
				<td nowrap="nowrap" align="right">编辑人：</td>
				<td>${editor.name}</td>
			</tr>
			<tr>
				<td nowrap="nowrap" align="right">编辑时间：</td>
				<td>${edittime}</td>
			</tr>
		</s:if>
		<tr>
			<td nowrap="nowrap" align="right">公告摘要：</td>
			<td><textarea id="summary" name="summary" style="width:400px;height:60px;" class="required">${summary}</textarea>
				<font color="#006676">请输入100个汉字以内的公告摘要</font>
			</td>
		</tr>	
		<tr>
			<td nowrap="nowrap" align="right">公告内容：</td>
			<td><textarea id="content" name="content" style="width:600px;height:250px;visibility:hidden;" class="required">${content}</textarea>
			</td>
		</tr>
		<tr>
			<td colspan="2" align="center">
				<input type="submit" value=" 提 交 " />&nbsp; 
				<input type="button" value=" 取 消 " onclick="history.back()" />
			</td>
		</tr>
	</table>
	</form>
</div>
<div>
	<font color="#006676">
	提示：<br/>
	１、公告内容可以上传图片，图片大小请控制在100K以下；<br/>
	２、如果您上传的图片超过100K,请使用<a href="http://www.neoimaging.cn/" target="_blank">图片压缩软件</a>将图片压缩到100k以下再上传；<br/>
	３、如果您需要上传大于100K的图片，请<a href="http://www.tienting.com" target="_blank">联系我们</a>。	
	</font>
</div>
</fieldset>
</body>
</html>
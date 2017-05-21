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
		<!-- KE js -->
		<script src="${ctx}/js/widgets/kindeditor/kindeditor.js" type="text/javascript" charset="utf-8"></script>

		<script type="text/javascript">				
			KE.show({
				id:'description'
			});
		
		$(document).ready(function(){
		$("#menu").focus();
			$("#inputForm").validate(
			{
			rules: { 
				name: { 
       				required: true,
       				remote:"submenu!checkMenuNameUnique.action?oldName="+encodeURIComponent('${name}')+"&t="+Math.random()
   				},
   				code: { 
       				required: true,
       				remote:"submenu!checkMenuCodeUnique.action?oldCode="+encodeURIComponent('${code}')+"&t="+Math.random()
   				},
   				cost: { 
       				required: true,
       				number:true,
       				min:0.1,
       				max:9999,
       				lessThan:"#price"
   				},
   				price: { 
       				required: true,
       				number:true,
       				min:0.1,
       				max:9999
   				},
   				profit: { 
       				required: true,
       				number:true,
       				min:0.1,
       				max:9999,
       			    lessThan:"#price"
   				},
   				stock:{
   					required: true,
   					digits:true
   				},
           		sellNumber: {
   					required: true,
   					digits:true
   				},
   				serial:{
   					required:true,
   					digits:true
   				},
   				recommendReason:{
   					maxlength:200
   				}//,
   				//description:{
   					//maxlength:100
   				//}
			},
			messages: {
				name: { 
       				required: "请输入菜单名称",
       				remote:"改菜单名称已被使用!"
   				},
   				code: { 
       				required: "请输入菜单编号",
       				remote:"改菜单编号已被使用!"
   				},
   				cost: { 
       				required: "请输入估计成本",
       				number:"估计成本需是合法数字",
       				lessThan:"成本需小于单价"
   				},
   				price: { 
       				required: "请输入单价",
       				number:"单价需是合法数字"
   				},
   				profit: { 
       				required: "请输入估计利润",
       				number:"估计利润需是合法数字",
       				lessThan:"估计利润需小于单价"
   				},
   				stock:{
   					required: "请输入菜单库存量",
   					digits:"菜单库存量需为数字"
   				},
   				serial:{
   					required:"请输入菜单排序",
   					digits:"请输入合法数字"
   				},
   				recommendReason:{
   					maxlength:"推荐理由最多只能为200字符(100个汉字)"
   				}//,
   				//description:{
   					//maxlength:"菜单描述最都只能100字符"
   				//}
			}
		}
		);
		
		var gg = $('#menu_img');
		var ei = $('#large');
		
		$('#menu_img').mousemove(function(event){
		event = event || window.event;
		ei.css("display","block");
		ei.html('<img style="border:1px solid gray;" src="' + this.src + '" />');
		ei.css("top",event.clientY -10 + "px").css("left",event.clientX + "px");
		ei.show(); 
		});
		
		$('#menu_img').mouseout(function(){
		ei.html("");
		ei.css("display","none");
		});
		
		$("#upload").change(function(){
		$("#menu_img").attr("src","file:///"+$("#upload").val());
		});
		});
	</script>
	<script type="text/javascript">
	String.prototype.trim=function(){
   		return this.replace(/(^\s*)|(\s*$)/g, '');
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
			if (img.fileSize/1024>50) {
				alert("您上传图片的大小是："+img.fileSize/1024+"K！\n\n请重新上传小于50K的图片！\n\n大于50K的图片系统将忽略保存！");
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
		<div id="message">
			<s:actionmessage theme="mytheme" />
		</div>
		<fieldset>
		<legend><s:if test="id == null">添加菜单</s:if><s:else>修改菜单 - ${name}</s:else></legend>
		<div id="listContent">
			<form id="inputForm" name="inputForm" action="submenu!save.action" method="post" enctype="multipart/form-data">
				<input type="hidden" name="id" value="${id}" />
				<table class="inputView">
					<tr>
						<td nowrap="nowrap" align="right"> 菜单类型：</td>
						<td>
							<s:select name="updatemenutypeid" list="allMenuType" listKey="id" listValue="name" theme="simple" />
						</td>
					</tr>
					<tr>
						<td nowrap="nowrap" align="right">菜单编号：</td>
						<td>
							<input type="text" id="code" name="code" size="30" maxlenght="20" value="${code}" />
						</td>
					</tr>			
					<tr>
						<td nowrap="nowrap" align="right">菜单名称：</td>
						<td>
							<input type="text" id="name" name="name" size="30" maxlenght="100" value="${name}" />
						</td>
					</tr>		
					<tr>
						<td align="right">菜单图片：</td>
						<td>
							<input type="file" name="upload" size="30" onchange="chkimg(this.value)"/>&nbsp;
							<input name="btn" onclick="clearupload();" type="button" value="清空"/><br/>
							<font color="#006676">请上传大小在50K以下的图片</font> 			
							<div id="show"></div> 
						</td>
					</tr> 
					<s:if test="photo != null">
						<tr>
							<td align="right">图片预览：</td>
							<td>
								<img id="menu_img" width="120" height="80"
										src="${ctx}/upload/submenu/${photo}"/>
							</td>
						</tr>
					</s:if>			
					<tr>
						<td nowrap="nowrap" align="right">价格：</td>
						<td>
							<input type="text" id="price" name="price" size="5" maxlenght="4" value="${price}" class="required" />元
							<font color="#006676">请输入大于0的数值</font>
						</td>
					</tr>
					<tr>
						<td nowrap="nowrap"  align="right">估计成本：</td>
						<td>
							<input type="text" id="cost" name="cost" size="5" maxlenght="4" value="${cost}" class="required" />元
							<font color="#006676">请输入大于0且小于价格的数值</font>
						</td>
					</tr>
					<tr>
						<td nowrap="nowrap"  align="right">估计利润：</td>
						<td>
							<input type="text" id="profit" name="profit" size="5" maxlenght="4" value="${profit}" class="required" />元
							<font color="#006676">请输入大于0且小于价格的数值</font>
						</td>
					</tr>					
					<tr>
						<td nowrap="nowrap" align="right">库存量：</td>
						<td>
							<input type="text" id="stock" name="stock" size="5" maxlenght="4" value="${stock}" class="required" />份/个
						</td>
					</tr>
					<s:if test="id != null">
						<tr>
							<td nowrap="nowrap" align="right">销售量：</td>
							<td>
								${sellNumber} 份/个
							</td>
						</tr>
					</s:if>
					<tr>
						<td nowrap="nowrap" align="right">排序：</td>
						<td>
							<input type="text" id="serial" name="serial" size="5" maxlenght="3" value="${serial}" class="required" />
							<font color="#006676">数字(0-255)越小，排序越前</font>
						</td>
					</tr>
					<s:if test="id != null">
						<tr>
							<td nowrap="nowrap" align="right">状态：</td>
							<td>
								<select name="status">
									<option value="1" <s:if test="status==\"1\"">selected</s:if>>正常使用</option>
									<option value="2" <s:if test="status==\"2\"">selected</s:if>>暂停使用</option>
								</select>
							</td>
						</tr>		
					</s:if>		
					<tr>
						<td nowrap="nowrap">是否推荐：</td>
						<td>
							<select name="isRecommend">
								<option value="2" <c:if test="${isRecommend=='2'}">selected</c:if>>普通菜单</option>
								<option value="1" <c:if test="${isRecommend=='1'}">selected</c:if>>推荐菜单</option>
							</select>
						</td>
					</tr>		
					<s:if test="id != null">
						<tr>
							<td nowrap="nowrap" align="right">编辑人：</td>
							<td>
								${editor.name}
							</td>
						</tr>
						<tr>
							<td nowrap="nowrap" align="right">编辑时间：</td>
							<td>
								${edittime}
							</td>
						</tr>
					</s:if>			
					<tr>
						<td nowrap="nowrap">推荐理由：</td>
						<td>
							<textarea name="recommendReason" style="width:400px;height:50px;">${recommendReason}</textarea>
						</td>
					</tr>
					<tr>
						<td nowrap="nowrap">菜单描述:</td>
						<td>
							<textarea id="description" name="description" style="width:600px;height:250px;visibility:hidden;">${description}</textarea>
						</td>
					</tr>
					<tr>
						<td colspan="2" align="center" height="50">
							<input type="submit" value=" 提 交 " />&nbsp;&nbsp;
							<input type="button" value=" 取 消 " onclick="history.back()" />
						</td>
					</tr>
				</table>
			</form>
		</div>
		<div>
			<font color="#006676">
			提示：<br/>
			１、菜单描述可以上传图片，图片大小请控制在100K以下；<br/>
			２、如果您上传的图片超过100K,请使用<a href="http://www.neoimaging.cn/" target="_blank">图片压缩软件</a>将图片压缩到100k以下再上传；<br/>
			３、如果您需要上传大于100K的图片，请<a href="http://www.tienting.com" target="_blank">联系我们</a>。	
			</font>
		</div>
		</fieldset>
	</body>
</html>

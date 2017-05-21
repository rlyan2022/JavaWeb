<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title></title>
	<%@ include file="/common/meta.jsp"%>
	<link href="${ctx}/css/default.css" type="text/css" rel="stylesheet" />
	<link href="${ctx}/js/validate/jquery.validate.css" type="text/css" rel="stylesheet" />
	<script src="${ctx}/js/jquery.js" type="text/javascript"></script>
	<script src="${ctx}/js/validate/jquery.validate.js" type="text/javascript"></script>
	<script src="${ctx}/js/validate/messages_cn.js" type="text/javascript"></script>
	<script>
	$(document).ready(function(){
		$("#passwordOld").focus();
		$("#inputForm").validate({
			 rules: { 
            	passwordOld:{
            		required: true            		
            	},
            	newpassword: {
    				required: true,
    				minlength:3,
    				maxlength:32
    			}, 
    			passwordConfirm: {
    				required: true,
    				equalTo:"#newpassword"
    			}		
			},
			messages: {
			    passwordOld: {
			    	remote:"密码不正确"
			    },
				passwordConfirm: {
					equalTo: "输入与上面相同的密码"
				}
			},
			success: function(label) {
				label.html("&nbsp;").addClass("checked");
	        }
		});
		
		$("#newpassword").keyup(function(){
			var pwd = $("#newpassword").val();
	    	if(pwd.length < 6){
	    		$("#newPwd").html("");
	    	}else if(pwd.length < 8){
	    		$("#newPwd").html("密码安全等级弱");
	    	}else if(pwd.length > 8){
	    		var p1= (pwd.search(/[a-zA-Z]/)!=-1) ? 1 : 0;
	   			var p2= (pwd.search(/[0-9]/)!=-1) ? 1 : 0;
	    		var p3= (pwd.search(/[^A-Za-z0-9_]/)!=-1) ? 1 : 0;
	    		var pa=p1+p2+p3;
		    	if(pa==1){
		    		$("#newPwd").html("密码安全等级弱");
		    	}else if(pa==2){
		    		$("#newPwd").html("密码安全等级中");	
		    	}else if(pa==3){
		    		$("#newPwd").html("密码安全等级强");
		    	}
	    	}	
		});
	});			
	</script>
</head>
<body>
<br/><br/><br/><br/>
<div id="content" style="width:500px;">
	<form id="inputForm" action="member!changePassword.action" method="post">
	<input type="hidden" id="id" name="id" value="${id}" />　
	<table width="266" style="margin-left:100px;" class="listView">
<tr>
			<td height="50"><s:actionmessage theme="mytheme"/></td>
		</tr>
		<tr>
			<td height="30" ><b>修改密码</b></td>
		</tr>
		<tr>
			<td >当前密码：
				<input type="password" id="passwordOld" name="passwordOld" size="20" maxlength="20"/></td>
		</tr>
		<tr>
			<td >更新密码：
				<input type="password" id="newpassword" name="newpassword" size="20" maxlength="20"/><div id="newPwd"></div></td>
		</tr>
		<tr>
			<td >重复密码：
				<input type="password" id="passwordConfirm" name="passwordConfirm" size="20" maxlength="20"/></td>
		</tr>
		
		<tr>
			<td height="50">密码请勿过于简单！</td>
		</tr>
		<tr> 
			<td>
				<input class="default_button" type="submit" value="确认修改" />
			</td>
		</tr>
	</table>
  </form>
</div>
</body>
</html>
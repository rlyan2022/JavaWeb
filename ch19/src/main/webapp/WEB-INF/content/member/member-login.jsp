<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title></title>
<%@ include file="/common/meta.jsp"%>
<script src="${ctx}/js/effects/jquery.messager.js"></script>
<!--<script type="text/javascript">
	
		function loginSubmit(){	 
			var loginCode = document.loginForm.elements["loginCode"].value;
			if (loginCode=="") {
				alert("抱歉！帐号不能为空，请输入帐号！");
				return false;
			}
			
			var password = document.loginForm.elements["password"].value;	
			if (password=="") {
				alert("抱歉！密码不能为空，请输入密码！");
				return false;
			}
			
			document.loginForm.action = "member!login.action";
			document.loginForm.submit();
		}
	</script>-->
</head>
<body>
<span id="error_message">
<s:actionmessage theme="mytheme"/>
</span>
<c:if test="${empty memberlist}">
  <script>
        error_message_txt = $("#error_message").text();
		alert(error_message_txt);
  </script>
</c:if>
<s:iterator value="memberlist">
  <p id="loginCode_lee">${loginCode}</p>
  <p id="relationMan">${memberName}</p>
  <p id="relationPhone">${relationPhone}</p>
  <p id="assignAddress_lee">${assignAddress}</p>
  <p id="mealCurrency">${mealCurrency}</p>
	<script>
        $("#dialog").dialog("close");
		var loginCode_lee = $("#loginCode_lee").text();
        var relationMan_txt = $("#relationMan").text();
        var relationPhone_txt = $("#relationPhone").text();
        var assignAddress_txt = $("#assignAddress_lee").text();
		var mealCurrency_txt = $("#mealCurrency").text();

        $("#login_name").text(loginCode_lee);		
        $("#real_name").val(relationMan_txt);
        $("#telephone").val(relationPhone_txt);	
        $("#assignAddress").val(assignAddress_txt);	
		$("#mealCurrency").val(mealCurrency_txt);	
		
		$("#btn_login").hide();
		$("#btn_reg").hide();
		$("#btn_loginOut").show();	
				
    </script>
</s:iterator>
</body>
</html>

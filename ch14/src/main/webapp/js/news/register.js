function check(){
	//用户名
	if(register.userName.value == ""){
		alert("请输入用户名");
		return false;
	}
	//密码
	if(register.passWd.value == ""){
		alert("请输入密码");
		return false;
	}
	//重复密码
	if(register.pwdAgain.value == ""){
		alert("请输入第二次的密码");
		return false;
	}
	//判断密码是否相等
	if(register.passWd.value != register.pwdAgain.value){
		alert("两次输入的密码不一样");
		return false;
	}
	//问题不为空
	if(register.question.value == ""){
		alert("密码提示问题不为空");
		return false;
	}
	//答案不为空
	if(register.answer.value == ""){
		alert("答案不为空");
		return false;
	}
	//判断邮箱
	if(register.emailAddr.value != ""){
		if(register.emailAddr.value.indexOf("@") == -1){
			alert("没有\"@\"符号，不是个有效的邮件地址");
			return false;
		}
		else if(register.emailAddr.value.indexOf(".") == -1){
			alert("没有\".\"符号，不是个有效的邮件地址");
			return false;
		}
		return true;
	}
}
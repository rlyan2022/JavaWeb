function check(){
	//密码
	if(edituser.passWd.value == ""){
		alert("请输入密码");
		return false;
	}
	//重复密码
	if(edituser.pwdAgain.value == ""){
		alert("请输入第二次的密码");
		return false;
	}
	//判断密码是否相等
	if(edituser.passWd.value != edituser.pwdAgain.value){
		alert("两次输入的密码不一样");
		return false;
	}
	//问题不为空
	if(edituser.question.value == ""){
		alert("密码提示问题不为空");
		return false;
	}
	//答案不为空
	if(edituser.answer.value == ""){
		alert("答案不为空");
		return false;
	}
	//判断邮箱
	if(edituser.emailAddr.value != ""){
		if(edituser.emailAddr.value.indexOf("@") == -1){
			alert("没有\"@\"符号，不是个有效的邮件地址");
			return false;
		}
		else if(edituser.emailAddr.value.indexOf(".") == -1){
			alert("没有\".\"符号，不是个有效的邮件地址");
			return false;
		}
	}
	return true;
}
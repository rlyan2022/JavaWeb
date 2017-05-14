function check(){
	//栏目为空判断
	if(kind.content.value == ""){
		alert("栏目名不为空");
		kind.content.focus();
		return false;
	}
	//栏目长度判断
	if(kind.content.value.length>255){
		alert("栏目不能超过255个字符");
    	kind.content.focus();
        return false;
	}
}
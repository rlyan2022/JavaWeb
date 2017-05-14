function check(){
	//判断类别的值是否为空
	if(editkind.content.value == ""){
		alert("栏目名不为空");
		editkind.content.focus();
		return false;
	}
	//栏目长度判断
	if(editkind.content.value.length>20){
		alert("栏目不能超过20个字符");
    	editkind.content.focus();
        return false;
	}
}
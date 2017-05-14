//更新内容判断
function check(){
	if(editcolumn.content_edit.value == ""){
		alert("栏目名不为空");
		editcolumn.content_edit.focus();
		return false;
	}
	//栏目长度判断
	if(editcolumn.content_edit.value.length > 20){
		alert("栏目不能超过20个字符");
    	editcolumn.content_edit.focus();
        return false;
	}
}
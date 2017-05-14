//PUBLIC
//是否为数字，返回真假
function IsDigit(cCheck){
	return (('0'<=cCheck) && (cCheck<='9'));
}
//是否为英文字母
function IsAlpha(cCheck){
	return ((('a'<=cCheck) && (cCheck<='z')) || (('A'<=cCheck) && (cCheck<='Z')))
}
//判断删除
function charge(content) {
return(confirm('\n\n注意！\n您现在的操作会删除 [ ' + content + ' ] 及其下面的所有分类\n请确认\n'));
}

//主函数

function check(){
	//判断栏目ID
	if(column.classId.value == ""){
		alert("请输入栏目ID");
		column.classId.focus();
		return false;
	}
	//判断是否为数字
    number=column.classId.value;
    for(nIndex=0;nIndex<number.length;nIndex++){
		cCheck=number.charAt(nIndex);
		if(!(IsDigit(cCheck))){
			alert("对不起！栏目ID只能使用数字");
			column.classId.focus();
			return false;
		}
	}
	//栏目为空判断
	if(column.content.value == ""){
		alert("栏目名不为空");
		column.content.focus();
		return false;
	}
	//栏目长度判断
	if(column.content.value.length>20){
		alert("栏目不能超过20个字符");
    	column.content.focus();
        return false;
	}
}
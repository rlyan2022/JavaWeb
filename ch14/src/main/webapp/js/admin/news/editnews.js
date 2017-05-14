function check(){
	//标题
	if(addarticle.title.value == ""){
		alert("请输入标题");
		addarticle.title.focus();
		return false;
	}
	if(addarticle.title.value.length > 255){
		alert("标题不能多于255个字符！");
		addarticle.title.focus();
		return false;
	}
	//文章内容
	if(addarticle.content.value == ""){
		alert("内容不能为空");
		addarticle.content.focus();
		return false;
	}
	//作者
	if(addarticle.author.value == ""){
		alert("作者不能为空!");
		addarticle.author.focus();
		return false;
	}
}
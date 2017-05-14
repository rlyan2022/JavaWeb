function check(){
	//标题
	if(news.headTitle.value == ""){
		alert("请输入标题");
		news.headTitle.focus();
		return false;
	}
	if(news.headTitle.value.length > 50){
		alert("标题不能多于50个字符！");
		news.headTitle.focus();
		return false;
	}
	//文章内容
	if(news.content.value == ""){
		alert("内容不能为空");
		news.content.focus();
		return false;
	}
	//作者
	if(news.author.value == ""){
		alert("作者不能为空!");
		news.author.focus();
		return false;
	}
}
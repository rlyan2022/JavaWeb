function wait(width,height,message,imagesrc)
{
	var _width = width||100;
	var _height = height||50;
	var _message = message||"请稍候,正在等待服务器响应";
	var _container = null;
	var _imagesrc = imagesrc;
	this.show = function(){
		if(_container===null){
			var w = (window.innerWidth) ? window.innerWidth : (document.documentElement && document.documentElement.clientWidth) ? document.documentElement.clientWidth : document.body.offsetWidth;
			var h = (window.innerHeight) ? window.innerHeight : (document.documentElement && document.documentElement.clientHeight) ? document.documentElement.clientHeight : document.body.offsetHeight;

			var x = w/2-_width/2;      //浏览器宽
    		var y = h/2-_height/2;      //浏览器高
			this._container = $(document.createElement("div"));
			this._container.addClass("wait")
			.append("<span style='margin-top:10%'>")
			.append(_message)
			.append("</span><br />")
			.append("<img src='"+_imagesrc+"' border=0 />")
			.Draggable(
	        {
	            zIndex:1000                   //拖拽过程中的Z坐标值
	            //ghosting:true
	        })
    		//设置left位置
    		.left(x+"px")
    		//设置top位置
    		.top(y+"px")
    		.width(_width)
    		.height(_height)
			.appendTo("body");
		}
	}
	this.hidden = function(){
		this._container.remove();
	}
}
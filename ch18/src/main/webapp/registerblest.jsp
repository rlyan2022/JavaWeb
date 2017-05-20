<%@page pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<style type="text/css">
<!--.link {
color : #000000;
text-decoration : none;
}A.link:hover {
color : red;
}A.link:active {
color : #000000;
text-decoration : none;
}//-->
</style>
<style type="text/css">
<!--
#slidemenubar2{position:absolute;left:-110pt;width:120pt;top:100pt;border:1.5pt solid black;
background-color:#ffffff;layer-background-color:#ffffff;font: 9pt/20pt "宋体";}
body { font-size: 9pt; margin: 0pt}
#slidemenubar { position:absolute;
left:-110pt;width:120pt;top:100pt;border:1.5pt black solid;background-color:#F3F3F3;layer-background-color:lightyellow;
font: 9pt/20pt "宋体"; }
//-->
</style>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>无标题文档</title>
<style type="text/css">
<!--
body {
	background-color: #FFFFFF;
	background-image: url(inmage/12d.JPG);
}
.style1 {
	font-size: 24pt;
	color: #FF3333;
	font-weight: bold;
}
.style4 {font-size: 24px}
.style5 {font-size: 9px}
-->
</style></head>
<script language="JavaScript1.2">
if (document.all)
document.write('<div id="slidemenubar2" style="left:-100" onMouseover="pull()" onMouseout="draw()">')
</script> <layer id="slidemenubar" onMouseover="pull()" onMouseout="draw()"> <script language="JavaScript1.2">
var sitems=new Array()
var sitemlinks=new Array登录
//以下是菜单内容，自由设置；
sitems[0]="本站首页"
sitems[1]="会员登录"
sitems[2]="管理员通道"
sitems[3]="关于...."

//菜单项目连接
sitemlinks[0]="index.html"
sitemlinks[1]="denglu.jsp"
sitemlinks[2]="guanliyuan.jsp"
sitemlinks[3]="../index.htm"

for (i=0;i<=sitems.length-1;i++)
document.write('<a href='+sitemlinks[i]+'>'+sitems[i]+'</a><br>')
</script> </layer>
<script language="JavaScript1.2">
function regenerate(){
window.location.reload()
}function regenerate2(){
if (document.layers)
setTimeout("window.onresize=regenerate",400)
}window.onload=regenerate2
if (document.all){
document.write('</div>')
themenu=document.all.slidemenubar2.style
rightboundary=0
leftboundary=-150
}else{
themenu=document.layers.slidemenubar
rightboundary=150
leftboundary=10
}function pull(){
if (window.drawit)
clearInterval(drawit)
pullit=setInterval("pullengine()",50)
}function draw(){
clearInterval(pullit)
drawit=setInterval("drawengine()",50)
}function pullengine(){
if (document.all&&themenu.pixelLeft<rightboundary)
themenu.pixelLeft+=5
else if(document.layers&&themenu.left<rightboundary)
themenu.left+=5
else if (window.pullit)
clearInterval(pullit)
}function drawengine(){
if (document.all&&themenu.pixelLeft>leftboundary)
themenu.pixelLeft-=5
else if(document.layers&&themenu.left>leftboundary)
themenu.left-=5
else if (window.drawit)
clearInterval(drawit)
}

</script>
<body>

<p>&nbsp;</p>
<p align="center" class="style1">欢迎您注册会员</p>
<hr>
  <form name="form1" method="post" action="">
    <p align="center"><span class="style4">注册成功!&nbsp;<登录pan></p>
    <p align="center">&nbsp;</p>
    <p align="center"><span class="style4">请单击此<a href="login.jsp">登录</a> </span></p>
</form>
<p>&nbsp;</p>

</body>
</html>

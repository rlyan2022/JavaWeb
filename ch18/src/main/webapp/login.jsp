<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>无标题文档</title>
<script language="javascript">
	function od()
	{
		var f=document.form1;
		if(f.Username.value=="")
		{
			alert("用户名未输入");
			f.Username.focus();
			f.Username.select();
			return;
		}
		if(f.Password.value=="")
		{
			alert("密码未输入");
			f.Password.focus();
			f.Password.select();
			return;
		}
	}
</script>
<style type="text/css">
<!--
body {
	background-image: url(inmage/12d.JPG);
}
.style3 {
	font-size: 24pt;
	color: #FF0000;
	font-weight: bold;
}
-->
</style></head>
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
//.style2 {font-size: 18pt}
-->
</style>
<script language="JavaScript1.2">
if (document.all)
document.write('<div id="slidemenubar2" style="left:-100" onMouseover="pull()" onMouseout="draw()">')
</script> <layer id="slidemenubar" onMouseover="pull()" onMouseout="draw()"> <script language="JavaScript1.2">
var sitems=new Array()
var sitemlinks=new Array()
//以下是菜单内容，自由设置；
sitems[0]="本站首页"
sitems[1]="会员注册"
sitems[2]="管理员通道"
sitems[3]="其它栏目"

//菜单项目连接
sitemlinks[0]="index.html"
sitemlinks[1]="register.jsp"
sitemlinks[2]="Adminlogin.jsp"
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
else登录f (window.drawit)
clearInterval(drawit)
}</script>
<body>
<p>&nbsp;</p>
<p align="center" class="style1 style3">欢迎会员登录系统</p>

<hr>
<p>&nbsp;</p>
<p>&nbsp;</p>
<p>&nbsp;</p>
<form name="form1" method="post" action="LoginServlet">
  <table width="230" border="1" align="center" cellpadding="0" cellspacing="0">
    <tr>
      <td width="86" height="46"><div align="center">用户名:</div></td>
      <td width="138"><div align="center">
        <input name="Username" type="text" id="Username" size="15">
      </div></td>
    </tr>
    <tr>
      <td height="48"><div align="center">密 &nbsp;码:</div></td>
      <td><div align="center">
        <input name="Password" type="Password" id="Password" size="15">
      </div></td>
    </tr>
  </table>
  <p align="center">
    <input name="Sub1" type="submit" id="Sub1" value="提交" onClick="od()">
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    <input name="Sub2" type="submit" id="Sub2" value="重置">
</p>
</form>
<p>&nbsp;</p>
</body>
</html>

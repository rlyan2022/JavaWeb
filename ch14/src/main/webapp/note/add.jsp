<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="java.sql.*" %>
<%@ page import="java.util.*" %>

<jsp:useBean id="noteG" scope="page" class="com.sanqing.persistence.NOTEGuest"/>
<jsp:setProperty name="noteG" property="*"/>
<jsp:useBean id="guest" scope="page" class="com.sanqing.news.note.Guest"/>
<jsp:useBean id="servlet" scope="page" class="com.sanqing.servlet.DOServlet"/>

<%
	String action=servlet.requestStr(request,"submit");
	if(action.equals("true")){
		guest.insNote(noteG.getUserName(),noteG.getSex(),noteG.getEmail(),noteG.getQq(),noteG.getUrl(),noteG.getHeadTitle(),noteG.getContent(),noteG.getImage());
		servlet.responseUrl(response,"index.jsp");
	}
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>新闻发布系统</title>
<link rel="stylesheet" href="text.css" type="text/css">
<script language="javascript">
function check(){
	if(text.userName.value==""){
		alert("请输入姓名");
		return false;
	}
	if(text.userName.value.length>20){
		alert("姓名不超过20位");
		return false;
	}
	if(text.email.value!=""){
		if(text.email.value.length>50){
			alert("邮件位数不超过20");
			return false;
		}
		if(text.email.value.indexOf("@") == -1){
			alert("没有\"@\"符号，不是个有效的邮件地址");
			return false;
		}
		else if(text.email.value.indexOf(".") == -1){
			alert("没有\".\"符号，不是个有效的邮件地址");
			return false;
		}
	}
	if(text.qq.value!=""){
	if(text.qq.value.length>9){
		alert("qq位数不超过9");
		return false;
		}
	}
	if(text.url.value!=""){
	if(text.url.value.length>50){
		alert("url位数不超过24");
		return false;
		}
	}
	if(text.headTitle.value==""){
		alert("请输入标题");
		return false;
	}
	if(text.headTitle.value.length>40){
		alert("标题不超过40位");
		return false;
	}
	if(text.content.value==""){
		alert("请输入内容");
		return false;
	}
	if(text.content.value.length>400){
		alert("内容不超过400位");
		return false;
	}
	return true;
}
</script>
</head>

<body leftMargin=0  topMargin=0 marginheight="0" marginwidth="0" bgcolor="#ffffff">
<br>
<div align="center">
  <center>
  <table align=center border=0 cellPadding=0 cellSpacing=0 width=500>
    <tr>
      <td width=134 height=37><IMG height=37 src="images/top_left.gif" width=134></td>
      <td width="100%" background=images/top_middle.gif height=37 valign="middle">
      <p align="center"><font color="#FFFFFF">欢迎来到新闻发布系统!!!</font></td>
      <td width=49 height=37><IMG height=37 src="images/top_right.gif" width=49></td>
    </tr>
  </table>
  </center>
</div>
<div align=center>
  <center>
    <table align=center border=0 cellPadding=0 cellSpacing=0 width=500 height=320>
      <tr>
        <td width=11 background="images/middle_left.gif" height=320>　</td>
        <td width=478 align="center" height=320>
          <form name="text" method="post" action="add.jsp?submit=true" onsubmit="return check();">
  			<table border="1" cellpadding="0" cellspacing="0" style="border-collapse: collapse" bordercolor="#009933" width="94%" id="AutoNumber3" height="126">
			<tr>
              <td width="100%" height="126" align="center" valign="top"> <table border="0" cellpadding="0" cellspacing="0" style="border-collapse: collapse" bordercolor="#009933" width="100%" id="AutoNumber4" height="28">
                  <tr>
                    <td width="100%" height="28" background="images/note_bg.gif">
                      <p align="center">欢迎留言！（“<font color="#FF0000">＊</font>”是必填写的字段）</td>
                  </tr>
                </table>
                  <table border="0" cellpadding="0" cellspacing="0" style="border-collapse: collapse" bordercolor="#111111" width="100%" id="AutoNumber5" height="261" bgcolor="#F7FEF6">
                    <tr>
                      <td width="100%" height="80" valign="top">
                        <table border="0" cellpadding="0" cellspacing="0" style="border-collapse: collapse" bordercolor="#111111" width="100%" id="AutoNumber6" height="80">
                          <tr>
                          <td width="15%" height="29"><font color="#FF0000">＊</font><font color="#99BB99">
                            姓名：</font></td>
                          <td width="37%" height="29">　
                            <input type="text" name="userName" size="16" class=yao id="userName"></td>
                          <td width="14%" height="29"> <p align="center"><font color="#99BB99">性别：</font></td>
                          <td width="34%" height="29">　
                            <input type="radio" value="0" name="sex" checked>
                            <font color="#99BB99">男　</font>　
                            <input type="radio" value="1" name="sex">
                            <font color="#99BB99">女</font></td>
                        </tr>
                        <tr>
                            <td width="15%" height="22">
                              <p align="center"><font color="#99BB99">E-mail</font></td>
                            <td width="37%" height="22">　
                            <input type="text" name="email" size="16" class="yao"></td>
                            <td width="14%" height="22">
                              <p align="center"><font color="#99BB99">QQ</font></td>
                            <td width="34%" height="22">　
                              <input type="text" name="qq" size=16 class="yao"></td>
                        </tr>
                        <tr>
                          <td width="14%" height="29"> 　　<font color="#99BB99">主页：</font></td>
                          <td width="86%" height="29" colspan="3"> 　
                            <input type="text" name="url" size="22" class="yao">
                            　　　　　<font color="#99BB99">注意</font><font color="#FF0000">＊</font><font color="#99BB99">为必填字段</font></td>
                        </tr>
                      </table></td>
                  </tr>
                  <tr>
                    <td width="100%" height="155" valign="top"> <table border="0" cellpadding="0" cellspacing="0" style="border-collapse: collapse" bordercolor="#111111" width="100%" id="AutoNumber7" height="167">
                        <tr>
                          <td width="29%" height="22"> <p align="right"><font color="#FF0000">＊</font><font color="#99BB99">留言标题：
                              </font></td>
                          <td width="71%" height="165" rowspan="3"> <table border="0" cellpadding="0" cellspacing="0" style="border-collapse: collapse" bordercolor="#111111" width="100%" id="AutoNumber8" height="175">
                              <tr>
                                <td width="100%" height="22"><font color="#99BB99">　
                                  <input name="headTitle" type="text" class=yao id="headTitle" size="28" maxlength="28">
                                  </font></td>
                              </tr>
                              <tr>
                                <td width="100%" height="126" valign="top">
                                  <!--webbot bot="Validation" s-data-type="String" i-maximum-length="30" s-validation-constraint="Greater than or equal to" s-validation-value="30" -->
                                  <textarea name="content" cols="40" rows="8" class="yao" id="content"></textarea></td>
                              </tr>
                              <tr>
                                <td width="100%" height="22" valign="top">&nbsp;&nbsp;&nbsp;<input type="submit" value="发表留言">&nbsp;&nbsp;<input type="reset" value="重置"></td>
                              </tr>
                            </table></tr>
                        <tr>
                          <td width="29%" height="22"> <p align="right"><font color="#FF0000">＊</font><font color="#99BB99">留言内容：</font></td>
                        </tr>
                        <tr>
                          <td width="29%" height="123" align="center" valign="top">
                            <script language=JavaScript>
                 function changeimage()
 					{
  						document.showimages.src=document.text.image.value;
 					}
                </script>
                              <img name=showimages src="images/image/01.gif" width="84" height="95">
                              <select style="position: absolute; left: 191px; top: 318px; width: 85px; height: 20px;  clip:rect(2 114 20 2)"  name="image" size="1" onChange="changeimage();" >
                                <option value="images/image/01.gif" selected>images
                                1</option><img src="">
                                <option value="images/image/02.gif">images 2</option>
                                <option value="images/image/03.gif">images 3</option>
                                <option value="images/image/04.gif">images 4</option>
                                <option value="images/image/05.gif">images 5</option>
                                <option value="images/image/06.gif">images 6</option>
                                <option value="images/image/07.gif">images 7</option>
                                <option value="images/image/08.gif">images 8</option>
                                <option value="images/image/09.gif">images 9</option>
                                <option value="images/image/10.gif">images 10</option>
                                <option value="images/image/11.gif">images 11</option>
                                <option value="images/image/12.gif">images 12</option>
                                <option value="images/image/13.gif">images 13</option>
                                <option value="images/image/14.gif">images 14</option>
                                <option value="images/image/15.gif">images 15</option>
                                <option value="images/image/16.gif">images 16</option>
                                <option value="images/image/17.gif">images 17</option>
                                <option value="images/image/18.gif">images 18</option>
                                <option value="images/image/19.gif">images 19</option>
                                <option value="images/image/20.gif">images 20</option>
                                <option value="images/image/21.gif">images 21</option>
                              </select> </td>
                      </table>
					 </table></td>
          </table>
           </form></td>
        <td width=11 background="images/middle_left.gif" height=320>　</td>
    </tr>
    </table>	  
  </center>
</div>
<div align=center>
  <center>
  <table border=0 cellpadding=0 cellspacing=0 width=500>
    <tr>
    <td width=133 height=72><IMG height=72 src="images/bottom_left.gif" width=133></td>
    <TD width="100%" background=images/bottom_middle.gif height=72>
    <font color="#FFFFFF">All Rights Reserved.Copyleft &copy; 2001-2003 
    <a href="http://www.sanqing.com"><font color="#FFFFFF">www.sanqing.com</font></a></font><a href="http://www.sanqing.com"><font color="#FFFFFF">
    </font></a> </TD>
    <td width=22 height=72><IMG height=72 src="images/bottom_right.gif" width=22></td>
    </tr>
  </table>
  </center>
</div>
</body>
</html>
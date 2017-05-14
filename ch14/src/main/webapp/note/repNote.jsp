<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="check.jsp"%>

<jsp:useBean id="manager" scope="page" class="com.sanqing.news.note.Manager"/>
<%
	int noteId=servlet.requestInt(request,"noteId");
	String action=servlet.requestStr(request,"submit");
	if(action.equals("true")){
		String content = servlet.requestStr(request,"content");
		manager.insReply(noteId,content);
		servlet.responseUrl(response,"default.jsp");
	}
%>
<html>
<head>
<title>新闻发布系统</title>
<link rel="stylesheet" href="text.css" type="text/css">
<SCRIPT Language=javascript>
<!--
function CheckData(){
	if(text.content.value == ""){
		alert("不能是空留言!");
		return false;
	}
	return true;
}
-->
</SCRIPT>
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
  <table align=center border=0 cellPadding=0 cellSpacing=0 width=500 height=113>
    <tr>
      <td width=11 background="images/middle_left.gif" height=113>　</td>
      <td width=478 align="center" height=1>

	<br>
  <table border="1" cellpadding="0" cellspacing="0" style="border-collapse: collapse" bordercolor="#009933" width="77%" id="AutoNumber1" height="154">
    <form name="text" method="post" action="repNote.jsp?submit=true" onSubmit="return checkData();">
    <tr>
      <td width="100%" height="154">
      <table border="0" cellpadding="0" cellspacing="0" style="border-collapse: collapse" bordercolor="#111111" width="100%" id="AutoNumber2" height="140">
        <tr>
          <td width="100%" height="26" background="images/note_bg.gif">
          <p align="center"><font color="#FF0000">回复主题</font></td>
        </tr>
        <tr>
          <td width="100%" height="149">
          <p align="center">
            	<textarea name="content" cols="41" rows="9" id="content"></textarea>
          </td>
        </tr>
        <tr>
          <td width="100%" height="25">
		  <input type="hidden" name="noteId" value="<%=servlet.requestInt(request,"noteId")%>">
          <p align="right">　<input type="submit" value="回复">&nbsp; <input type="reset" value="重写"></td>

        </tr>
      </table>
      </td>
    </tr>
    </form>
  </table>
   <br>

  </td>
      <td width=11 background="images/middle_left.gif" height=113>　</td>
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
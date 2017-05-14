<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="java.sql.*" %>
<%@ page import="java.util.*" %>
<%@ page import="com.sanqing.persistence.*" %>

<jsp:useBean id="linkNews" class="com.sanqing.news.LinkNews"/>

<%@ include file="../include/head.jsp"%>
<link rel="stylesheet" href="../css/text.css" type="text/css">
<script src="../js/news/register.js"></script>

<body topMargin=0>

<table width="760" height="378" border="0" align="center" cellpadding="0" cellspacing="0">
  <form action="pass.jsp" method="post" name="register" onSubmit = "return check();">
  <tr> 
    <td width="11" rowspan="2" valign="top" background="../images/middle_left.gif">&nbsp;</td>
    <td width="738" height="20"> <table align=center border=0 cellPadding=0 cellSpacing=0 width=738 height=20 background="../images/index.gif">
        <tr> 
          <td width=736 height=20 class=cdfont valign="bottom"> 　　　<a href="index.jsp"><font color="#333333">首页</font></a>　　　　
            <%Iterator rs_class = linkNews.showClass();while(rs_class.hasNext()){NEWSClass tableClass=(NEWSClass)rs_class.next();%><a href="news.jsp?classId=<%=tableClass.getClassId()%>"><font color="#333333"><%=tableClass.getContent()%></font></a>　　　<%}%>　　<a href="../note/index.jsp" target="_blank"><font color="#333333">
            留言本</font></a></td>
        </tr>
      </table></td>
    <td width="11" rowspan="2" background="../images/middle_right.gif">&nbsp;</td>
  </tr>
  <tr>
    <td height="23" valign="middle"> &nbsp;&nbsp;&nbsp;文章发布 &gt;&gt; 用户注册</td>
  </tr>
  <tr>
      <td height="335" valign="top" background="../images/middle_left.gif">&nbsp;</td>
    <td valign="top"><table width="99%" border="1" align="center" cellpadding="0" cellspacing="0">
          <tr background="../images/reg.gif">
            <td height="28" colspan="2" align="center">用户注册（<font color="#FF0000">&quot;*&quot;是必须填写</font>）</td>
          </tr>
          <tr align="center">
            <td height="31" align="right"><font color="#FF0000">*</font>&nbsp;用户名：</td>
            <td height="31"><input name="userName" type="text" class="text" id="userName"> &nbsp;</td>
          </tr>
          <tr align="center">
            <td height="28" align="right"><font color="#FF0000">*</font>&nbsp;密 码：</td>
            <td height="28"><input name="passWd" type="password" class="text" id="passWd"> &nbsp;</td>
          </tr>
          <tr align="center">
            <td width="26%" height="31" align="right"><font color="#FF0000">*&nbsp;</font>请再输一次密码：</td>
            <td width="74%" height="31"> <input name="pwdAgain" type="password" class="text" id="pwdAgain">
              &nbsp;</td>
          </tr>
          <tr align="center">
            <td height="35" align="right">性别：</td>
            <td height="35"> &nbsp; <select name="sex" id="sex">
                <option value="1" selected>男</option>
                <option value="2">女</option>
              </select></td>
          </tr>
          <tr align="center">
            <td height="33" align="right"><font color="#FF0000">*&nbsp;</font>密码提示问题：</td>
            <td height="33"><input type="text" name="question" class="text"> &nbsp;</td>
          </tr>
          <tr align="center">
            <td height="29" align="right"><font color="#FF0000">*&nbsp;</font>问题答案：</td>
            <td height="29"> <input type="text" name="answer" class="text"> &nbsp;</td>
          </tr>
          <tr align="center">
            <td height="30" align="right">E-mail：</td>
            <td height="30"> <input name="emailAddr" type="text" class="text" id="emailAddr"> &nbsp;</td>
          </tr>
          <tr align="center">
            <td height="27" align="right">QQ：</td>
            <td height="27" align="center"><input name="qq" type="text" class="text" id="qq"> &nbsp;</td>
          </tr>
          <tr align="center">
            <td height="29" align="right">个人主页：</td>
            <td height="29"><input name="http" type="text" class="text" size="30"> &nbsp;</td>
          </tr>
          <tr>
            <td height="30" colspan="2"><table width="100%" border="0" cellpadding="0" cellspacing="0">
                <tr>
                  <td width="34%">&nbsp;</td>
                  <td width="66%"><input type="submit" value="申请"> &nbsp;&nbsp;
                    <input type="reset" value="清除"></td>
                </tr>
              </table></td>
          </tr>
        </table>
        <br>
      </td>
    <td background="../images/middle_right.gif">&nbsp;</td>
  </tr>
  </form>
</table>

</body>
<%@ include file="../include/copyleft.jsp"%>
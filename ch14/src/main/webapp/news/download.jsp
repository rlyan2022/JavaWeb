<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="java.sql.*"%>
<%@ page import="java.util.*" %>
<%@ page import="com.sanqing.persistence.*" %>

<%@ include file="../include/head.jsp"%>
<jsp:useBean id="linkNews" class="com.sanqing.news.LinkNews"/>

<link rel="stylesheet" href="../css/text.css" type="text/css">
<body topMargin=0>

<table width="760" height="173" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr> 
      <td width="11" rowspan="2" valign="top" background="../images/middle_left.gif">　</td>
      <td width="738" height="20"> <table align=center border=0 cellPadding=0 cellSpacing=0 width=738 height=20 background="../images/index.gif">
        <tr>
          <td width=736 height=20 class=cdfont valign="bottom"> 　　　<a href="index.jsp"><font color="#333333">首页</font></a>　　　　
            <%Iterator rs_class = linkNews.showClass();while(rs_class.hasNext()){NEWSClass tableClass=(NEWSClass)rs_class.next();%><a href="news.jsp?classId=<%=tableClass.getClassId()%>"><font color="#333333"><%=tableClass.getContent()%></font></a>　　　<%}%>　　<a href="../note/index.jsp" target="_blank"><font color="#333333">
            留言本</font></a></td>
        </tr>
        </table></td>
      <td width="11" rowspan="2" background="../images/middle_right.gif">　</td>
    </tr>
    <tr>

    <td height="153" align="left" valign="top">
      <table width="100%" cellpadding="0" cellspacing="0">
          <tr>

          <td height="254">
            <table border="0" cellpadding="0" cellspacing="0" style="border-collapse: collapse" bordercolor="#111111" width="100%" id="AutoNumber1" height="218">
              <tr>
                <td width="33%" height="58"> <p align="right"> <img border="0" src="../images/11.bmp" width="32" height="32"></td>
                <td width="67%" height="58">&nbsp;&nbsp;&nbsp;<a href="../download/nt.exe">sanqing新闻发布v1.0测试版</a>（提供JavaBean）</td>
              </tr>
              <tr>
                <td height="50" align="right"><img border="0" src="../images/12.bmp" width="32" height="32"></td>
                <td width="67%" height="50">&nbsp;&nbsp;&nbsp;<a href="../download/mysql1.exe">sanqing新闻发布1.0稳定版（MySql版）</a></td>
              </tr>
              <tr>
                <td height="51" align="right"><img border="0" src="../images/13.bmp" width="32" height="32"></td>
                <td width="67%" height="51"><a href="../download/note.exe">&nbsp;&nbsp;&nbsp;本站的留言本</a></td>
              </tr>
              <tr>
                <td height="50" align="right"><img border="0" src="../images/14.bmp" width="32" height="32"></td>
                <td height="50"><a href="../download/mysql.exe">&nbsp;&nbsp;&nbsp;mysql管理控制台</a></td>
              </tr>
            </table>
            </td>
          </tr>
        </table></td>
    </tr>
</table>

</body>
<%@ include file="../include/copyleft.jsp"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.sql.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.sanqing.persistence.*" %>
<jsp:useBean id="isIn" class="com.sanqing.news.manage.ISLogin"/>
<jsp:useBean id="syslogin" scope="page" class="com.sanqing.persistence.NEWSAdmin"/>
<%@ include file="check.jsp"%>
<html>
<head>
<title>main</title>
<STYLE type=text/css>
BODY { BACKGROUND: #799ae1; FONT: 9pt 宋体; MARGIN: 0px }
TABLE { BORDER-BOTTOM: 0px; BORDER-LEFT: 0px; BORDER-RIGHT: 0px; BORDER-TOP: 0px }
TD { FONT: 12px 宋体 }
</style>
<base target="_self">
</head>

<body bgcolor="#F2F4F9">
<p>&nbsp;</p>
<table width="92%" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td width="0" height="55" align="left" valign="middle"> <p><font color="red"><%=adminName%>
        </font> ：欢迎您来到新闻发布系统！ </p>
      <%
			/**
			Iterator rs = isIn.timeIp(adminName);
			while(rs.hasNext()){
				NEWSAdmin tableAdmin = (NEWSAdmin)rs.next();
			*/
		%>
    </td>
  </tr>
  <tr>
    <td height="32" align="left" valign="middle">您上次的登录时间是:<font color="red"><%=isIn.strTime(adminName)%>
      <%//=tableAdmin.getLastLogin()%>
      </font></td>
  </tr>
  <tr>
    <td height="58" align="left" valign="middle">您上次的登录IP是:<font color="red"><%=isIn.strIp(adminName)%>
      <%//=tableAdmin.getLastLoginIp()%>
      </font></td>
  </tr>
</table>
<p><font color="#FFFFFF"></font> </p>
</body>

</html>
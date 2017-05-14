<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="java.sql.*"%>

<jsp:useBean id="userM" class="com.sanqing.news.UserManage"/>
<jsp:useBean id="newsU" class="com.sanqing.persistence.NEWSUsr">
<jsp:setProperty name="newsU" property="*"/>
</jsp:useBean>

<link rel="stylesheet" href="../css/text.css" type="text/css">

<body topMargin=0>
<br>
<table width="300" height="85" border="0" align="center" cellpadding="0" cellspacing="0">
  <%
	if(userM.isPassWd(newsU.getUserName(),newsU.getAnswer())){
	%>
  <tr> 
    <td align="center" valign="middle"> <p>您的密码是: <font color="red"><%=userM.getPassWd()%></font></p>
      <p>&nbsp; </p></td>
  </tr>
  <tr>
    <td height="19" align="center" valign="middle"><table width="90%" border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td height="19" align="right"><a href="javascript:window.close()">关闭窗口</a>&nbsp;</td>
        </tr>
      </table></td>
  </tr>
  <%
		}
		else{
	%>
  <tr>
    <td align="center" valign="middle">对不起您的密码答案不符合！</td>
  </tr>
  <%
		}
	%>
</table>
</body>

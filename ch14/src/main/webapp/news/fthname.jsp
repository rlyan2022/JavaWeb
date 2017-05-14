<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="java.sql.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.sanqing.persistence.*" %>

<jsp:useBean id="userM" class="com.sanqing.news.UserManage"/>
<jsp:useBean id="newsU" class="com.sanqing.persistence.NEWSUsr">
<jsp:setProperty name="newsU" property="*"/>
</jsp:useBean>

<link rel="stylesheet" href="../css/text.css" type="text/css">

<body topMargin=0>
<br>
<%
	if(userM.isUserName(newsU.getUserName())){
		userM.setUser(newsU.getUserName());
		Iterator rs = userM.listPersonal();
		while(rs.hasNext()){
			NEWSUsr tableUser = (NEWSUsr)rs.next();
%>
 <form action="fthpwd.jsp" method="post">
<table width="300" height="91" border="0" align="center" cellpadding="0" cellspacing="0">
 
    <tr> 
      <td height="24" align="center" valign="middle"> 
        <p>密码提示问题: <font color="red"><%=tableUser.getQuestion()%></font></p></td>
    </tr>
    <tr>
      <td height="25" align="center" valign="middle">请输入问题答案:
        <input name="answer" type="text" class="text" size="16">
      </td>
    </tr>
    <tr>
      <td height="22" align="center" valign="middle">
	  <input type="hidden" name="userName" value="<%=tableUser.getUserName()%>">
	  <input type="submit" value="下一步">&nbsp;</td>
    </tr>

  <%
  		}
  		}else{
  %>
   <tr>
      <td height="20" align="center" valign="middle"><font color="red">对不起，注册用户中没有此用户名用户!</font></td>
    </tr>
<%
		}
%>
</table>
</form>
</body>

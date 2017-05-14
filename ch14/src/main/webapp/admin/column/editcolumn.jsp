<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="java.sql.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.sanqing.persistence.*" %>
<%@ include file="check.jsp" %>

<jsp:useBean id="Column" scope="page" class="com.sanqing.news.manage.Column"/>

<%
	/**
	 * 栏目更新
	 */
 	String action=servlet.requestStr(request,"submit");
	int classId_up = servlet.requestInt(request,"classId_edit");
	String content_up = servlet.requestStr(request,"content_edit");
	out.print(content_up);
	if(action.equals("true")){
		Column.upColumn(classId_up,content_up);
		servlet.responseUrl(response,"column.jsp");
	}
%>
<html>
<head>
<title>修改栏目</title>
<link rel="stylesheet" href="../../css/admin.css" type="text/css">
<script language="JavaScript" src="../../js/admin/column/editcolumn.js"></script>
</head>

<body>
<p>&nbsp;</p>
<p>&nbsp;</p>

<%
	//列出指定栏目
	int classId_edit = servlet.requestInt(request,"classId");
	Iterator rs_edit = Column.editColumn(classId_edit);
	while(rs_edit.hasNext()){
		NEWSClass tableClass = (NEWSClass)rs_edit.next();
		int classId = tableClass.getClassId();
		String content = tableClass.getContent();
%>
<form action="editcolumn.jsp?submit=true" method="post" name="editcolumn" onSubmit = "return check();">
<table width="595" height="7%" border="1" align="center" cellpadding="0" cellspacing="0" bordercolor="#FFFFFF">
  <tr>
      <td height="39" align="center">请输入新的栏目 :&nbsp;
      <input type="text" name="content_edit" size="20" value="<%=String.valueOf(content)%>">&nbsp;&nbsp;
	  <input type="hidden" name="classId_edit" value="<%=String.valueOf(classId)%>">
      <input name="button" type="submit" id="button" value="更新"></td>
  </tr>
</table>
</form>
<%
	}
%>
<p>&nbsp;</p>
</body>
</html>

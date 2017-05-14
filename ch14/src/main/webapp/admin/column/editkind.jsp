<%@ page contentType="text/html; charset=UTF-8"%>

<%@ page import="java.sql.*" %>
<%@ page import="java.util.*" %>
<%@ page import="com.sanqing.servlet.*" %>
<%@ page import="com.sanqing.news.manage.*"%>
<%@ page import="com.sanqing.persistence.*"%>
<%@ include file="check.jsp" %>

<jsp:useBean id="Column" scope="page" class="com.sanqing.news.manage.Column"/>
<jsp:useBean id="Kind" scope="page" class="com.sanqing.news.manage.Kind"/>
<jsp:useBean id="newsKind" scope="page" class="com.sanqing.persistence.NEWSKind"/>
<jsp:setProperty name="newsKind" property="*"/>

<%
	/**
     * 更新类别
     */
   	//获取kindId的值
	int kindIdII = servlet.requestInt(request,"kindId");
	//取得action的值
	String action=servlet.requestStr(request,"submit");
	//判断插入
	if(action.equals("true")){
		//执行插入
		Kind.upKind(newsKind.getClassId(),newsKind.getContent(),kindIdII);
		servlet.responseUrl(response,"kind.jsp");
	}
%>
<html>
<head>
<title>修改类别</title>
<link rel="stylesheet" href="../../css/admin.css" type="text/css">
<script language="JavaScript" src="../../js/admin/column/editkind.js"></script>
</head>

<body>
<p>&nbsp;</p>
<p>&nbsp;</p>
<form action="editkind.jsp?submit=true" method="post" name="editkind" onSubmit = "return check();">
<table width="595" height="7%" border="1" align="center" cellpadding="0" cellspacing="0" bordercolor="#FFFFFF">
  <tr>
      <td height="39" align="center">请输入新类别的名称 :&nbsp;
	  <%
	  	//获取kindId的值
		int kindId = servlet.requestInt(request,"kindId");
		//类的具体的值
		Iterator kind = Kind.editKind(kindId);
		while(kind.hasNext()){
			NEWSKind tableKind = (NEWSKind)kind.next();
	  %>
	  <select name="classId">
	  <%
		//栏目的值
		Iterator column = Column.getColumn();
		while(column.hasNext()){
			NEWSClass tableClass = (NEWSClass)column.next();
      %>
		  <option value="<%=tableClass.getClassId()%>"<%if(tableClass.getClassId()==tableKind.getClassId()){out.print(" selected");}//取出具体修改的栏目%>><%=tableClass.getContent()%></option>
	 <%
		}
	 %>
        </select>
	   &nbsp;
        <input name="content" type="text" value="<%=tableKind.getContent()%>" size="20">
        &nbsp;&nbsp;
      <input type="hidden" name="kindId" value=<%=kindId%>>
	  <input name="button" type="submit" id="button" value="更新"></td>
	  <%}%>
  </tr>

</table>
</form>
<p>&nbsp;</p>
</body>
</html>

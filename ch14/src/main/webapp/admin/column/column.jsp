<%@ page contentType="text/html; charset=UTF-8"%>

<%@ page import="java.sql.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.sanqing.persistence.*" %>
<%@ page import="com.sanqing.news.manage.*" %>
<%@ include file="check.jsp" %>

<jsp:useBean id="Column" scope="page" class="com.sanqing.news.manage.Column"/>
<jsp:setProperty name="Column" property="*"/>
<jsp:useBean id="NEWSClass" scope="page" class="com.sanqing.persistence.NEWSClass"/>
<jsp:setProperty name="NEWSClass" property="*"/>

<%
    /**
     * 插入数据
     */
	//取得action的值
	String action=servlet.requestStr(request,"submit");
	//判断插入
	if(action.equals("true")){
		//判断是否有重名的编号
		if(!Column.isIns(NEWSClass.getClassId())){
			Column.InsColumn(NEWSClass.getClassId(),NEWSClass.getContent());
			servlet.responseUrl(response,"column.jsp");
		}
		else{
			out.println("<Script language=JavaScript>alert('此编号已经存在，请另选一个!');JavaScript:history.back();</Script>");
		}
	}
%>

<html>
<head>
<title>类别管理</title>
<link rel="stylesheet" href="../../css/admin.css" type="text/css">
<script language="JavaScript" src="../../js/admin/column/column.js"></script>
</head>

<body>

	<br>
<table width="595" height="2%" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td height="26" align="center">系 统 总 栏 管 理 </td>
  </tr>
</table>

<table width="595" border="1" align="center" cellpadding="0" cellspacing="0" bordercolor="#FFFFFF">
  <tr valign="middle" bgcolor="#799ae1">
    <td width="220" height="29" align="center"><font color="#FFFFFF">栏目ID</font></td>
    <td width="221" align="center"><font color="#FFFFFF">栏目名称</font></td>
    <td colspan="2" align="center"><font color="#FFFFFF">编辑、删除</font></td>
  </tr>
<%
		//判断是否有栏目存在
		if(Column.isNullColumn()){
			//实现Iterator接口，用它来访问Vector中的数据
			Iterator rs = Column.getColumn();
			while(rs.hasNext()){//判断数组值是否存在
				NEWSClass tableClass = (NEWSClass)rs.next();//循环取值
				int classId = tableClass.getClassId();//取得classid的值
				String content = tableClass.getContent();//内容的值
%>
  <tr>
    <td height="30" align="center">&nbsp;<%=String.valueOf(classId)%></td>
    <td align="center">&nbsp;<a><%=String.valueOf(content)%></a></td>
	<td width="60" align="center"><a href="editcolumn.jsp?classId=<%=String.valueOf(classId)%>"><img src="../../images/admin/edit.gif" alt="编辑栏目" height="18" border="0"></a></td>
    <td width="60" align="center"><a href="delcolumn.jsp?classId=<%=String.valueOf(classId)%>" onclick="return(charge('<%=String.valueOf(content)%>'))"><img src="../../images/admin/del.gif" alt="删除栏目" width="16" height="16" border="0"></a></td>
  </tr>

<%
			}
	}else{
%>
  <tr align="center">
    <td height="35" colspan="4" align="center" class="title">Sorry! 还没有任何记录？ 请先添加!</td>
  </tr>
  <%
	}
%>
</table>

<p>&nbsp;</p>
<form action="column.jsp?submit=true" method=post name="column" onSubmit = "return check();">
<table width="595" height="7%" border="1" align="center" cellpadding="0" cellspacing="0" bordercolor="#ffffff">
  <tr>
      <td width="899" height="32" align="center"> 添加栏目 ID：
        <input name="classId" type="text" id="classId" size="4">
        &nbsp;&nbsp;栏目名称：
        <input name="content" type="text" size="20">
      &nbsp;&nbsp;<input type="submit" name="Submit" value="提交"></td>
  </tr>
</table>
 </form>
<p>&nbsp; </p>
</body>
</html>
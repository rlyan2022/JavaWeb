<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="java.sql.*" %>
<%@ page import="java.util.*" %>
<%@ page import="com.sanqing.persistence.*" %>
<%@ include file="check.jsp"%>

<jsp:useBean id="newsT" scope="page" class="com.sanqing.persistence.NEWSTable"/>
<jsp:setProperty name="newsT" property="*"/>
<jsp:useBean id="kind" scope="page" class="com.sanqing.news.manage.Kind"/>
<jsp:useBean id="news" scope="page" class="com.sanqing.news.manage.News"/>
<jsp:useBean id="column" scope="page" class="com.sanqing.news.manage.Column"/>

<%
	/**
	 * 更新数据
	 */
	String action=servlet.requestStr(request,"submit");
	if(action.equals("true")){
	/**
	out.print(newsT.getClassId());
	out.print(newsT.getKindId());
	out.print(newsT.getMyOther());
	out.print(newsT.getHeadTitle());
	out.print(newsT.getContent());
	out.print(newsT.getConnect());
	out.print(newsT.getAuthor());
	out.print(newsT.getEditor());
	out.print(newsT.getNewsFrom());
	out.print(newsT.getTop());
	*/
		if(newsT.getClassId() != 0){
			//执行插入
			news.upUbbNews(newsT.getClassId(),newsT.getKindId(),newsT.getMyOther(),newsT.getHeadTitle(),newsT.getContent(),newsT.getConnect(),newsT.getAuthor(),newsT.getEditor(),newsT.getNewsFrom(),newsT.getTop(),newsT.getNewsId());
			servlet.responseUrl(response,"newslist.jsp");
		}
		else{
			out.println("<script language=javascript>alert('请您先选择栏目!');history.back();</script>");
		}
	}
%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改文章</title>
<link rel="stylesheet" href="../../css/admin.css" type="text/css">
<%
	//类别
	Iterator rs_kind = kind.allKind();
%>
<script language = "JavaScript">
	var onecount;//定义变量
	onecount=0;//赋值为o
	subcat = new Array();//定义个数组
        //--------jsp--------语句
		<%
	        int count = 0;
			while(rs_kind.hasNext()){
				NEWSKind tableKind = (NEWSKind)rs_kind.next();
        %>
				subcat[<%=count%>] = new Array("<%=tableKind.getContent()%>","<%=tableKind.getClassId()%>","<%=tableKind.getKindId()%>");
        <%
	     	   count = count + 1;//指针每循环一次,count值+1
			}
        %>
	onecount=<%=count%>;

function changelocation(classId){
    document.news.kindId.length = 0;//设置默认的时候为0
    var classId=classId;//将栏木的值给
    var i;
    for (i=0;i<onecount;i++){
        if (subcat[i][1] == classId){
             document.news.kindId.options[document.news.kindId.length] = new Option(subcat[i][0], subcat[i][2]);
        }
    }
}
</script>
</head>

<body>
<form action="editNews.jsp?submit=true" method="post" name="news">
<table width="98%" border="1" align="center" cellpadding="0" cellspacing="0" bordercolor="#FFFFFF">
    <tr align="center">
      <td height="26" colspan="2">
        <table width="100%" border="0" cellpadding="0" cellspacing="0">
          <tr>
            <td height="21" align="center"><font color="#FFFFFF"><strong> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
              &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;
              &nbsp;&nbsp;&nbsp; 修改文章</strong></font> </td>
          </tr>
        </table></td>
    </tr>
    <%
	//栏目列表
	Iterator rs = news.editNews(servlet.requestInt(request,"newsId"));
	while(rs.hasNext()){
		NEWSTable tableNews=(NEWSTable)rs.next();
	%>
    <tr align="center">
      <td width="16%" height="30">文章类型<br></td>
      <td width="84%" align="left">&nbsp;&nbsp;栏目：
        <select name="classId" onChange="changelocation(document.news.classId.options[document.news.classId.selectedIndex].value)">
          <option value="0">请选择栏目</option>
          <%
		//栏目的值
		Iterator rs_column = column.getColumn();
		while(rs_column.hasNext()){
			NEWSClass tableClass = (NEWSClass)rs_column.next();
      %>
          <option value="<%=tableClass.getClassId()%>"<%if(tableClass.getClassId()==tableNews.getClassId()){out.print(" selected");}//取出具体修改的栏目%>><%=tableClass.getContent()%></option>
          <%
				}
	   %>
        </select> &nbsp;&nbsp; 类别：
        <select name="kindId">
          <option value="0">请选择类别</option>
          <%
		//类别的值
		Iterator rskind = kind.allKind();
		while(rskind.hasNext()){
			NEWSKind tableKind = (NEWSKind)rskind.next();
      %>
          <option value="<%=tableKind.getKindId()%>"<%if(tableKind.getKindId()==tableNews.getKindId()){out.print(" selected");}//取出具体修改的栏目%>><%=tableKind.getContent()%></option>
          <%
		}
	  %>
        </select> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        <%
			//原创或转载
			if(tableNews.getMyOther() == 0){
		%>
        原创:
        <input type="radio" name="myOther" value="0" checked> &nbsp;转载：
        <input type="radio" name="myOther" value="1">
        <%
			}
			else{
		%>
        原创:
        <input type="radio" name="myOther" value="0"> &nbsp;转载：
        <input type="radio" name="myOther" value="1" checked>
        <%}%>
    </tr>
    <tr align="center">
      <td height="28">文章标题&nbsp; </td>
      <td height="28" align="left">&nbsp; <input name="headTitle" type="text" value="<%=tableNews.getHeadTitle()%>" size="30"></td>
    </tr>
    <tr align="center">
      <td height="221">文章内容</td>
      <td align="left">&nbsp; <textarea name="content" cols="60" rows="12" id="content"><%=tableNews.getContent()%></textarea>
      </td>
    </tr>
    <tr align="center">
      <td height="27">相关文章</td>
      <td align="left">&nbsp; <input name="connect" type="text" value="<%=tableNews.getConnect()%>" size="20"></td>
    </tr>
    <tr align="left">
      <td height="25" align="center">作者&nbsp; </td>
      <td height="25"> &nbsp; <input type="text" size="20" name="author" value="<%=tableNews.getAuthor()%>"></td>
    </tr>
    <tr align="center">
      <td height="26">编辑</td>
      <td height="26" align="left">&nbsp; <input type="text" size="20" name="editor" value="<%=tableNews.getEditor()%>"></td>
    </tr>
    <tr align="center">
      <td height="28">出处<br></td>
      <td align="left">&nbsp; <input name="newsFrom" type="text" id="newsFrom" size="25" value=<%=tableNews.getNewsFrom()%>>
      </td>
    </tr>
    <tr align="center">
      <td height="26" colspan="2"><table width="100%" height="26" border="0" cellpadding="0" cellspacing="0">
          <tr>
            <td width="25%" align="center">文章置顶
              <%
		  		if(tableNews.getTop() == 0){
			%>
              <input name="top" type="checkbox" id="top" value="1">
              <%
				}
				else{
			%>
              <input name="top" type="checkbox" id="top" value="1" checked>
              <%
				}
			%>
            </td>
            <td width="75%" align="center">&nbsp; <input type="hidden" name="newsId" value="<%=servlet.requestInt(request,"newsId")%>">
              <input type="submit" value="更新"> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
              <input type="reset" value="重置"> </td>
          </tr>
        </table></td>
    </tr>
    <%}%>
</table>
  </form>
</body>
</html>
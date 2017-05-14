<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="java.sql.*" %>
<%@ page import="java.util.*" %>
<%@ page import="com.sanqing.persistence.*" %>
<%@ include file="check.jsp" %>

<jsp:useBean id="Pagination" class="com.sanqing.common.Pagination"/>
<jsp:useBean id="news" scope="page" class="com.sanqing.news.manage.News"/>

<%
	int curPages = Pagination.curPages(Pagination.strPage(request,"page"));
	Pagination.setRows(20);//每页显示10条
	int totalPages = Pagination.getPages(news.sumSearchNews(servlet.requestStr(request,"search")));//取出总页数
%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改文章</title>
<link rel="stylesheet" href="../../css/admin.css" type="text/css">
</head>

<body>
<table width="96%" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td width="78%" height="25">&nbsp;&nbsp;&nbsp;共搜索到关键字为&nbsp;<font color="red"><%out.print(servlet.requestStr(request,"search"));%></font>&nbsp;的记录&nbsp;&nbsp;<font color="red"><%=news.sumSearchNews(servlet.requestStr(request,"search"))%></font>&nbsp;&nbsp;条</td>
    <td width="22%">&nbsp;</td>
  </tr>
</table>
<table width="96%" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td height="73"><table width="100%" height="12%" border="1" align="center" cellpadding="0" cellspacing="0" bordercolor="#FFFFFF">
        <tr align="center">
          <td width="71%" height="32">文章题目（加入时间）</td>
          <td width="10%">点击次数</td>
          <td width="10%">编辑</td>
          <td width="9%">删除</td>
        </tr>
<%
	if(news.sumSearchNews(servlet.requestStr(request,"search")) > 0){
	Iterator rs = Pagination.getPageSet(news.searchNews(servlet.requestStr(request,"search")),curPages);
	int i=1;//变量获得每页显示的条数
	while(rs.hasNext()){
		NEWSTable tableNews = (NEWSTable)rs.next();
%>
        <tr align="center">
          <td height="28"> <table width="100%" height="27" border="0" cellpadding="0" cellspacing="0">
              <tr>
                <td width="67%" height="27" align="center"><a href="../../news/listnews.jsp?newsId=<%=tableNews.getNewsId()%>"><%=tableNews.getHeadTitle()%></a></td>
                <td width="33%" align="center"><%=tableNews.getNewsTime()%></td>
              </tr>
            </table></td>
          <td height="28"> <%=tableNews.getHits()%> </td>
          <td height="28"><a href=editNews.jsp?NewsId=<%=tableNews.getNewsId()%>><img src="../../images/admin/edit.gif" alt="编辑栏目" height="18" border="0"></a></td>
          <td height="28"><a href="delNews.jsp?newsId=<%=tableNews.getNewsId()%>"><img src="../../images/admin/del.gif" alt="删除栏目" width="16" height="16" border="0"></a></td>
        </tr>
        <%
		i=i+1;
		if(i>20)
			break;
	}
%>
        <%}else{%>
        <tr align="center">
          <td height="27" colspan="4" class="title">对不起，还没有添加文章，请先添加！</td>
        </tr>
        <%}%>
      </table></td>
  </tr>
  <tr> 
    <td height="31"> 
      <table width="100%" cellpadding="0" cellspacing="0">
        <tr> 
          <td width="73%" height="29">&nbsp;</td>
          <td width="14%" align="center" valign="bottom"> 
            <%if(curPages>1){%>
            <a href="search.jsp?page=<%=curPages-1%>"><img src="../../images/prev.gif" width="36" height="11" border="0"></a> 
            <%}%>
          </td>
          <td width="13%" valign="bottom"> 
            <%if(curPages<totalPages){%>
            <a href="search.jsp?page=<%=curPages+1%>"><img src="../../images/next.gif" width="36" height="11" border="0"></a> 
            <%}%>
          </td>
        </tr>
      </table></td>
  </tr>
</table>
<p>&nbsp;</p>
</body>
</html>

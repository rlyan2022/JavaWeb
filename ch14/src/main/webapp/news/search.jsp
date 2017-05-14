<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="java.sql.*"%>
<%@ page import="java.util.*" %>
<%@ page import="com.sanqing.persistence.*" %>

<jsp:useBean id="personal" scope="page" class="com.sanqing.news.Personal"/>
<jsp:useBean id="SessionManager" class="com.sanqing.servlet.SessionManager"/>
<jsp:useBean id="linkNews" class="com.sanqing.news.LinkNews"/>
<jsp:useBean id="searchN" scope="page" class="com.sanqing.news.SearchNews">
<jsp:setProperty name="searchN" property="*"/>
</jsp:useBean>
<%
	if((searchN.getSearch()).length()==0){
		out.print("<Script language=JavaScript>alert('请填写上你的查询条件!');JavaScript:history.back();</Script>");
	}
%>
<jsp:useBean id="Pagination" class="com.sanqing.common.Pagination"/>

<%
	searchN.getSearch();
	int curPages = Pagination.curPages(Pagination.strPage(request,"page"));
	Pagination.setRows(20);//每页显示20条
	int totalPages = Pagination.getPages(searchN.sumNews());//取出总页数
%>
<%@ include file="../include/head.jsp"%>
<link rel="stylesheet" href="../css/text.css" type="text/css">
<body topMargin=0>

<table width="760" align="center" cellpadding="0" cellspacing="0">
  <tr>
      <td width="11" rowspan="2" valign="top" background="../images/middle_left.gif">　</td>
      <td width="738" height="22">
        <table align=center border=0 cellPadding=0 cellSpacing=0 width=738 height=20 background="../images/index.gif">
        <tr>
          <td width=736 height=20 class=cdfont valign="bottom"> 　　　<a href="index.jsp"><font color="#333333">首页</font></a>　　　　
            <%Iterator rs_class = linkNews.showClass();while(rs_class.hasNext()){NEWSClass tableClass=(NEWSClass)rs_class.next();%><a href="news.jsp?classId=<%=tableClass.getClassId()%>"><font color="#333333"><%=tableClass.getContent()%></font></a>　　　<%}%>　　<a href="../note/index.jsp" target="_blank"><font color="#333333">
            留言本</font></a></td>
        </tr>
        </table></td>
      <td width="11" rowspan="2" background="../images/middle_right.gif">　</td>
    </tr>
    <tr>

    <td height="292" align="center" valign="top">
      <table width="100%" cellpadding="0" cellspacing="0">
        <tr>
          <td height="19" colspan="2" valign="middle">&nbsp; </td>
        </tr>
        <tr>
          <td width="24%" valign="top"><table width="100%" cellpadding="0" cellspacing="0">
              <tr>
                <td height="269" valign="top">
                  <table width="100%" border="0" cellspacing="0" cellpadding="0">
                    <tr>
                      <td height="47" align="center" valign="top">
                      <%
					  	String user=SessionManager.getSession(session,"sessionuser");
					  	if(user==null){
					  %>
						<table width="100%" height="109" border="0" cellpadding="0" cellspacing="0">
                          <form method="post" action="checkuser.jsp">
                            <tr>
                              <td height="32" colspan="2" align="center"><img src="../images/big_load.gif" width="189" height="30"></td>
                            </tr>
                            <tr align="center">
                              <td width="37%" height="28">用户名：</td>
                              <td width="63%"><input name="userName" type="text" class="text" id="userName" size="16" mame="user"></td>
                            </tr>
                            <tr align="center">
                              <td height="25"> 密&nbsp; 码：</td>
                              <td><input name="passWd" type="password" class="text" id="passWd" size="16"></td>
                            </tr>
                            <tr align="center">
                              <td height="24" colspan="2"> <table width="100%" border="0" cellspacing="0" cellpadding="0">
                                  <tr align="center">
                                    <td width="48%" height="24"><input name="image" type="image" src="../images/button_login.gif"></td>
                                    <td width="52%" align="left"><a href="register.jsp"><img src="../images/button_reg.gif" width="44" height="18" border="0"></a></td>
                                  </tr>
                                </table></td>
                            </tr>
                          </form>
                        </table>
						<%
							}else{
						%>
					    <table width="100%" height="107" border="0" cellpadding="0" cellspacing="0">
                          <tr>
							 <td height="28" align="center"><font color="red"><u><b><%=user%></b></u></font>&nbsp;:&nbsp;您好！</td>
                            </tr>
                            <tr>
                              <td height="28">&nbsp;&nbsp;&nbsp;&nbsp;您的IP:<font color="red"><%=request.getRemoteAddr()%></font></td>
                            </tr>
                            <tr>
                              <td height="25">&nbsp;&nbsp;&nbsp;&nbsp;您的身份:<font color="red"><%=personal.pdmPersonal()%></font></td>
                            </tr>
                            <tr>
                              <td height="24">&nbsp;&nbsp;&nbsp;&nbsp;您发表的文章总数:<font color="red"><%=personal.sumPersonal()%></font></td>
                            </tr>
                        </table>
						<%
							}
						%>
                      </td>
                    </tr>
                    <tr>
                      <td height="44" align="center" valign="top">
                        <table width="100%" height="82" border="0" cellpadding="0" cellspacing="0">
                          <tr>
                            <td height="24"> <p><img src="../images/big_search.gif" width="189" height="30"></p></td>
                          </tr>
                       	  <form action="search.jsp" method="post">
						  <tr>
                            <td height="23" align="center"><input type="text" size="20" name="search" class="text"></td>
                          </tr>
                          <tr>
                            <td height="29"> <table width="100%" border="0" cellspacing="0" cellpadding="0">
								<tr>
                                  <td width="54%" height="28" align="right">
									<select name="select">
                                      <option value="0" selected>文章标题</option>
                                      <option value="1">文章内容</option>
                                    </select></td>
                                  <td width="46%" align="center"><input type="image" src="../images/search.gif"></td>
                                </tr>
                              </table></td>
                          </tr>
						  </form>
                        </table>
                      </td>
                    </tr>
                    <tr>
                      <td height="78" align="center" valign="top">
                        <table width="100%" border="0" cellspacing="0" cellpadding="0">
                          <tr>
                            <td height="31" align="center"><img src="../images/../images/linux_hot.gif" width="189" height="31"></td>
                          </tr>
                          <tr>
                            <td height="47" valign="top">
                              <table width="100%" border="0" cellspacing="0" cellpadding="0">
                	   <%
							Iterator rs_hot = searchN.hotNews();
							while(rs_hot.hasNext()){
								NEWSTable tableNews_hot = (NEWSTable)rs_hot.next();
						%>
                                <tr>
                                  <td width="10%" height="22" align="right"><img src="../images/dot.gif"></td>
                                  <td width="90%">&nbsp;<a href="listnews.jsp?newsId=<%=tableNews_hot.getNewsId()%>">
                                    <%=tableNews_hot.getHeadTitle()%>
                                    </a></td>
                                </tr>
                       <%
							}
						%>
                              </table><br></td>
                          </tr>
                        </table>
                      </td>
                    </tr>
                  </table></td>
              </tr>
            </table></td>
          <td width="76%" valign="top" height="271">
            <table width="98%" border="1" align="center" cellpadding="0" cellspacing="0" style="border-collapse: collapse" bordercolor="#90A0B0">
              <tr>
                <td>
                  <table width="100%" border="0" cellspacing="0" cellpadding="0">
                    <tr>
                      <td height="24" background="../images/body_left.gif">&nbsp;&nbsp;搜索结果：共搜索到关键字为“<font color="red"><%=searchN.getSearch()%></font>”的记录&nbsp;<font color="red">&nbsp;<%=searchN.sumNews()%>&nbsp;</font>条</td>
                    </tr>
                  </table></td>
              </tr>
              <tr>
                <td>
                  <table width="100%" border="0" cellspacing="0" cellpadding="0">
                    <%
						if(searchN.sumNews() > 0){
							Iterator rs = Pagination.getPageSet(searchN.listNews(),curPages);
							int i=1;
							while(rs.hasNext()){
								NEWSTable tableNews = (NEWSTable)rs.next();
					%>
                    <tr>
                      <td width="4%" height="20" align="center"><img src="../images/../images/new.gif" width="10" height="9"></td>
                      <td width="72%"><a href="listnews.jsp?newsId=<%=tableNews.getNewsId()%>"><%=tableNews.getHeadTitle()%></a>&nbsp;</td>
                      <td width="24%"><%=tableNews.getNewsTime()%></td>
                    </tr>
                    <%
							i++;
							if(i>20)
								break;
							}
						}else{
					%>
                    <tr>
                      <td width="4%" height="21" align="center">&nbsp;</td>
                      <td colspan="2">&nbsp;没有符合条件的记录</td>
                    </tr>
                    <%}%>
                  </table></td>
              </tr>
            </table>
            <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
              <tr> 
                <td width="85%" height="22" align="right" valign="bottom"> 
                  <%if(curPages>1){%>
                  <a href="search.jsp?select=<%=searchN.getSelect()%>&search=<%=searchN.getSearch()%>&page=<%=curPages-1%>"><img src="../images/prev.gif" width="36" height="11" border="0"></a> 
                  <%}%>
                </td>
                <td width="15%" align="center" valign="bottom"> 
                  <%if(curPages<totalPages){%>
                  <a href="search.jsp?select=<%=searchN.getSelect()%>&search=<%=searchN.getSearch()%>&page=<%=curPages+1%>"><img src="../images/next.gif" width="36" height="11" border="0"></a> 
                  <%}%>
                </td>
              </tr>
            </table></td>
        </tr>
      </table>
		  
    </td>
    </tr>
</table>
</body>
<%@ include file="../include/copyleft.jsp"%>
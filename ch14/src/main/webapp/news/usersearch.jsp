<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="java.sql.*" %>
<%@ page import="java.util.*" %>
<%@ page import="com.sanqing.persistence.*" %>
<%@ include file="check.jsp" %>

<jsp:useBean id="personal" class="com.sanqing.news.Personal"/>
<jsp:useBean id="Pagination" class="com.sanqing.common.Pagination"/>
<jsp:useBean id="searchN" scope="page" class="com.sanqing.news.SearchNews">
<jsp:useBean id="linkNews" class="com.sanqing.news.LinkNews"/>
<jsp:setProperty name="searchN"  property="*"/>
</jsp:useBean>
<%
	personal.setUser(user);
%>
<%
	searchN.getSearch();
	int curPages = Pagination.curPages(Pagination.strPage(request,"page"));
	Pagination.setRows(20);//每页显示20条
	int totalPages = Pagination.getPages(searchN.sumNews(user));//取出总页数
%>
<%@ include file="../include/head.jsp"%>
<link rel="stylesheet" href="../css/text.css" type="text/css">
<script src=../js/news/show.js></script>
<body topMargin=0>

<table width="760" align="center" cellpadding="0" cellspacing="0">
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

    <td height="449" align="center" valign="top">
      <table width="100%" height="449" border="1" cellpadding="0" cellspacing="0" bordercolor="#999999" style="border-collapse: collapse">
        <tr>
            <td height="18" colspan="2" valign="middle"><marquee scrollDelay=100 direction="left" onmouseover=this.stop() onmouseout=this.start()>
            由于本站刚刚建立，也许大家在使用的时候会遇到很多的bug，请大家发<a href="mailto:lovehere2@163.net">E-mail</a>给我！或是到<a href="http://note.sanqing.com">留言区</a>给我留言
            </marquee></td>
          </tr>
          <tr>

          <td width="23%" height="167" valign="top">
            <table width="98%" border="0" cellpadding="0" cellspacing="0">
              <tr>
                <td height="18" align="center"><img src="../images/yourstate.gif" width="168" height="30"></td>
              </tr>
              <tr>
                <td height="90" valign="top" bordercolor="#FF0000">
                    <p style="margin-top: 3; margin-bottom: 3">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font color="red"><u><b><%=user%></b></u></font>&nbsp;：&nbsp;您好！</p>
                  <p style="margin-top: 3; margin-bottom: 3">&nbsp;&nbsp;您的IP:<font color="red"><%=request.getRemoteAddr()%></font></p>
                    <p style="margin-top: 3; margin-bottom: 3">&nbsp;&nbsp;上次登录时间:<br>&nbsp;&nbsp;&nbsp;&nbsp;<font color="red"><%=personal.timePersonal()%></font></p>
                    <p style="margin-top: 3; margin-bottom: 3">&nbsp;&nbsp;发表的文章总数:<font color="red"><%=personal.sumPersonal()%></font></p>
                    <p style="margin-top: 3; margin-bottom: 3">&nbsp;&nbsp;您的身份:<font color="red"><%=personal.pdmPersonal()%></font></p>
                    <p style="margin-top: 3; margin-bottom: 3">&nbsp;&nbsp;<a href="javascript:show('notice.htm')">用户须知</a></p></td>
                </tr>
              </table></td>
            <td width="77%" rowspan="3" valign="top" height="340"><table width="98%" border="0" align="center" cellpadding="0" cellspacing="0" style="border-collapse: collapse" bordercolor="#90a0b0">
              <tr>
                <td height="25"> <table width="100%" border="0" cellspacing="0" cellpadding="0">
                    <form action="usersearch.jsp" method="post">
                      <tr>
                        <td height="27" align="center">您自己在本站发表的文章进行搜索:
                          <select name="select">
                            <option value="0" selected>按标题查询</option>
                            <option value="1">按文章内容查询</option>
                          </select> &nbsp; <input type="text" size="20" name="search">
                          &nbsp; <input type="submit" value="search">
                        </td>
                      </tr>
                    </form>
                  </table></td>
              </tr>
              <tr>
                <td height="48" valign="top">
                  <table width="100%" border="1" cellpadding="0" cellspacing="0" bordercolor="#CCCCCC"  style="border-collapse: collapse">
                    <tr>
                      <td height="26" background="../images/reg.gif">&nbsp;&nbsp;
                        搜索到您在本站发表的文章中关键字为“<font color="red"><%=searchN.getSearch()%></font>”的文章<font color="red">“<%=searchN.sumNews(user)%>”</font></td>
                    </tr>
                    <tr>
                      <td height="20">
                        <table width="100%" border="0" cellpadding="0" cellspacing="0">
                      <%
						if(searchN.sumNews(user) > 0){
							Iterator rs = Pagination.getPageSet(searchN.listNews(user),curPages);
							int i=1;
							while(rs.hasNext()){
								NEWSTable tableNews = (NEWSTable)rs.next();
					%>
						  <tr>
                            <td width="3%" height="18" align="center"><img src="../images/per_link1.gif" width="12" height="12"></td>
                            <td width="69%"><a href="listnews.jsp?newsId=<%=tableNews.getNewsId()%>"><%=tableNews.getHeadTitle()%></a></td>
                            <td width="28%"><%=tableNews.getNewsTime()%></td>
                          </tr>
					<%
							i++;
							if(i>20)
								break;
							}
						}else{
					%>
						  <tr>
                            <td width="3%" height="18" align="center">&nbsp;</td>
                            <td width="69%" align="center">没有符合条件的记录!</td>
                            <td width="28%">&nbsp;</td>
                          </tr>
					<%
						  }
				     %>
                        </table>
                      </td>
                    </tr>
                  </table> </td>
              </tr>
            </table>
			<table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
              <tr>
                <td width="85%" height="22" align="right" valign="bottom">
                  <%if(curPages>1){%>
                  <a href="usersearch.jsp?select=<%=searchN.getSelect()%>&search=<%=searchN.getSearch()%>&page=<%=curPages-1%>"><img src="../images/prev.gif" width="36" height="11" border="0"></a>
                  <%}%>
                </td>
                <td width="15%" align="center" valign="bottom">
                  <%if(curPages<totalPages){%>
                  <a href="usersearch.jsp?select=<%=searchN.getSelect()%>&search=<%=searchN.getSearch()%>&page=<%=curPages+1%>"><img src="../images/next.gif" width="36" height="11" border="0"></a>
                  <%}%>
                </td>
              </tr>
            </table> </td>
          </tr>
          <tr>

          <td valign="top" height="90">
            <table width="99%" height="99" border="0" cellpadding="0" cellspacing="0">
              <tr>

                <td height="21" align="center" valign="middle"><img src="../images/czl.gif" width="168" height="30"></td>
                </tr>
                <tr>

                <td height="69" valign="top">
                  <table width="100%" border="0" cellpadding="0" cellspacing="0" style="margin-bottom: 3">
                    <tr>
                      <td width="16%" align="right"><img src="../images/1.gif" width="20" height="21"></td>
                      <td width="84%"><a href="userNews.jsp">发表文章</a></td>
                    </tr>
                    <tr>
                      <td align="right"><img src="../images/2.gif" width="21" height="20"></td>
                      <td><a href="edituser.jsp">更改个人资料</a></td>
                    </tr>
                    <tr>
                      <td height="25" align="right"><img src="../images/3.gif" width="21" height="21"></td>
                      <td><a href="logout.jsp">退出登录</a></td>
                    </tr>
                  </table>

                </td>
                </tr>
              </table></td>
          </tr>
          <tr>

          <td height="161" valign="top">
            <table width="96%" border="0" align="right" cellpadding="0" cellspacing="0">
              <tr>

                <td height="25" align="center" valign="middle"> <p><img src="../images/zngg.gif" width="168" height="30"></p></td>
                </tr>
                <tr>

                <td valign="middle"> <table width="95%" height="95" align="center" cellpadding="0" cellspacing="0" style="border-collapse: collapse">
                    <tr>
                      <td height="95" valign="top">                  <marquee onmouseover=this.stop() onmouseout=this.start() scrollDelay=80 direction=up scrollAmount=1 height=110>
                    &nbsp;&nbsp; &nbsp; 请您注意，请在你发表文章后,如果您觉得您的文章有地方需要修改的话，请及时修改，我们会每两天就审核一次，如果您的文章已经通过审批，但您还想修改的话请发<a href="mailto:redhatserver@163.net">E-mail</a>给我！
                    <br>
                    <br>
                  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                  ——白鹰
                  </marquee></td>
                    </tr>
                  </table> </td>
                </tr>
              </table></td>
          </tr>
        </table>
    </td>
    </tr>
</table>

</body>
<%@ include file="../include/copyleft.jsp"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="java.sql.*" %>
<%@ page import="java.util.*" %>
<%@ page import="com.sanqing.persistence.*" %>

<jsp:useBean id="linkNews" class="com.sanqing.news.LinkNews"/>
<jsp:useBean id="counter" class="com.sanqing.common.Counter"/>
<jsp:useBean id="newsShow" class="com.sanqing.news.NewsShow"/>
<jsp:useBean id="column" class="com.sanqing.news.manage.Column"/>
<jsp:useBean id="personal" scope="page" class="com.sanqing.news.Personal"/>
<jsp:useBean id="SessionManager" class="com.sanqing.servlet.SessionManager"/>

<%@ include file="../include/head.jsp" %>
<link rel="stylesheet" href="../css/text.css" type="text/css">
<script src="../js/news/fthpwd.js"></script>

<body topMargin=0>
<table width="760" height="462" align="center" cellpadding="0" cellspacing="0">
  <tr> 
    <td width="11" rowspan="3" valign="top" background="../images/middle_left.gif">　</td>
    <td width="738" height="20"> <table align=center border=0 cellPadding=0 cellSpacing=0 width=738 height=20 background="../images/index.gif">
        <tr>
          <td width=736 height=20 class=cdfont valign="bottom"> 　　　<a href="index.jsp"><font color="#333333">首页</font></a>　　　　
            <%Iterator rs_class = linkNews.showClass();while(rs_class.hasNext()){NEWSClass tableClass=(NEWSClass)rs_class.next();%><a href="news.jsp?classId=<%=tableClass.getClassId()%>"><font color="#333333"><%=tableClass.getContent()%></font></a>　　　<%}%>　　<a href="../note/index.jsp" target="_blank"><font color="#333333">
            留言本</font></a></td>
        </tr>
      </table></td>
    <td width="11" rowspan="3" background="../images/middle_right.gif">　</td>
  </tr>
  <tr>
    <td height="421" valign="top"> <br> <table width="100%" height="408" border="0" cellpadding="0" cellspacing="0">
        <tr>
          <td width="527" height="408" align="center" valign="top"> <table width="98%" cellpadding="0" cellspacing="0">
              <tr>
                <td align="center" valign="top">
                  <table width="99%" border="1" cellpadding="0" cellspacing="0" style="border-collapse: collapse" bordercolor="#90A0B0">
                    <tr>
                      <td height="30"> <table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
                          <%
						  	if(newsShow.isTime()){
						  %>
						  <tr>
                            <td width="37%" height="20" background="../images/body_left.gif">
                              &nbsp;&nbsp;最新加入的文章</td>
                            <td width="38%" align="right" background="../images/body_left.gif" class="show">最新更新：&nbsp;<%=newsShow.lastTime()%>&nbsp;</td>
                            <td width="25%" height="20" align="center" background="../images/body_left.gif"><img src="../images/more.gif" width="88" height="24"></td>
                          </tr>
                          <%
							  }else{
						  %>
                          <tr>
                            <td width="37%" height="20" background="../images/body_left.gif">
                              &nbsp;&nbsp;最新加入的文章</td>
                            <td width="38%" height="20" align="right" background="../images/body_left.gif" class="show">还没有记录！&nbsp;</td>
                            <td width="25%" height="20" align="center" background="../images/body_left.gif"><img src="../images/more.gif" width="88" height="24"></td>
                          </tr>
                          <%
								}
						  %>
                        </table></td>
                    </tr>
                    <tr>
                      <td>
                      <table width="509" border="0" align="right" cellpadding="0" cellspacing="0">
                          <%
							if(newsShow.isNews()){//总记录判断
								if(newsShow.isTopNews()){
									Iterator rs_new_top = newsShow.topNews();
									while(rs_new_top.hasNext()){
										NEWSTable tableNews = (NEWSTable)rs_new_top.next();
						  %>
                          <tr>
                            <td width="20" height="20" align="center"><img src="../images/top.gif" width="10" height="9"></td>
                            <td width="335" align="left"><a href="listnews.jsp?newsId=<%=tableNews.getNewsId()%>"><font color="#FF0000"><%=tableNews.getHeadTitle()%></font></a>　</td>
                            <td width="154" height="20" align="right"><font color="#AFB9D5">
                              [
                              <%
								//栏目
								out.print(column.getColumn_newsShow(tableNews.getClassId()));
							 %>
                              ]</font>&nbsp;</td>
                          </tr>
    					  <%
									}
								}
						  %>
	                      <%
							//最新文章
							if(newsShow.isNewNews()){
								Iterator rs_new = newsShow.newNews();
								while(rs_new.hasNext()){
									NEWSTable tableNews = (NEWSTable)rs_new.next();
						  %>
                          <tr align="center">
                            <td height="24" align="center" width="20"><img src="../images/new.gif" width="10" height="9"></td>
                            <td height="24" align="left" width="335"><a href="listnews.jsp?newsId=<%=tableNews.getNewsId()%>"><%=tableNews.getHeadTitle()%></a>　</td>
                            <td height="24" align="right" width="154"><font color="#AFB9D5">
                              [
                              <%=column.getColumn_newsShow(tableNews.getClassId())%>
                              ]</font>&nbsp;</td>
                          </tr>
                          <%
						  		}
							}
						  %>
                          <%
							}
							else{
						  %>
                          <tr align="center">
                            <td height="24" colspan="3" width="509"><font color="#000000">对不</font>起！现在还没有任何显示，请先从后台添加记录！</td>
                          </tr>
                          <%
						 		}
						  %>
                        </table></td>
                    </tr>
                  </table>
                  <br> </td>
              </tr>
              <tr>
                <td height="82" valign="top"> <table width="99%" border="1" align="center" cellpadding="0" style="border-collapse: collapse" cellspacing="0" bordercolor="#90A0B0">
                    <tr>
                      <td height="30"> <table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
                          <tr>
                            <td width="74%" height="20" background="../images/body_left.gif">&nbsp;&nbsp;热门文章</td>
                            <td width="26%" align="center" background="../images/body_left.gif"><img src="../images/more.gif" width="88" height="24"></td>
                          </tr>
                        </table></td>
                    </tr>
                    <tr>
                      <td> <table width="100%" border="0" align="right" cellpadding="0" cellspacing="0">
                   <%
						if(newsShow.isNews()){
							Iterator rs_hot = newsShow.hotNews();
							while(rs_hot.hasNext()){
								NEWSTable tableNews = (NEWSTable)rs_hot.next();
					%>
                          <tr>
                            <td width="4%" height="24" align="center"><img src="../images/new.gif" width="10" height="9"></td>
                            <td width="48%" align="left"><a href="listnews.jsp?newsId=<%=tableNews.getNewsId()%>"><%=tableNews.getHeadTitle()%></a>　</td>
                            <td width="23%" height="24" align="center"><font color="#AFB9D5">
                              <%=tableNews.getHits()%>&nbsp; </font>&nbsp;</td>
                            <td width="25%" height="24" align="right"><font color="#AFB9D5"><%=tableNews.getNewsTime()%></font>&nbsp;&nbsp;</td>
                          </tr>
                     <%
							}
					%>
                    <%
						  		}else{
					%>
                          <tr align="center">
                            <td height="27" colspan="4">对不起！现在还没有任何显示，请先从后台添加记录！</td>
                          </tr>
                          <%
								}
						  %>
                        </table></td>
                    </tr>
                  </table></td>
              </tr>
            </table></td>
          <td width="209" align="left" valign="top"> <table width="97%" height="405" cellpadding="0" cellspacing="0" bgcolor="#ffffff" style="border-collapse: collapse">
              <tr>
                <td height="403" valign="top"> <div align="center">
                    <center>
                      <form action="search.jsp" method="post">
                      <table width="100%" height="398" border="0" cellpadding="0" cellspacing="0" style="border-collapse: collapse">
                        <tr>
                          <td height="86" valign="top"> <table width="100%" height="86" border="0" align="center" cellpadding="0" cellspacing="0">
                              <tr>
                                <td height="86" align="center" valign="top" bordercolor="#D8DEEB" >

                                  <table width="100%" height="85" border="0" cellpadding="0" cellspacing="0">
                                      <tr align="center" valign="top">
                                        <td height="30" colspan="2"><img src="../images/big_search.gif" width="189" height="30"></td>
                                      </tr>
                                      <tr valign="middle">
                                        <td height="26" colspan="2" align="center">
                                          <input name="search" type="text" class="text" id="search" size="20">
                                        </td>
                                      </tr>
                                      <tr>
                                        <td width="57%" height="29" align="center" valign="middle">
                                          <table width="91%" height="29" border="0" cellpadding="0" cellspacing="0">
                                            <tr>
                                              <td height="29" align="right"> <select name="select">
                                                  <option value="0">文章标题</option>
                                                  <option value="1">文章内容</option>
                                                </select> </td>
                                            </tr>
                                          </table></td>
                                        <td width="43%" align="center" valign="middle">
                                        <input type="image" src="../images/search.gif" width="56" height="22"></td>
                                      </tr>
                                  </table>
                                  </td>
                              </tr>
                            </table>
                             </form>
                            </td>
                        </tr>
                        <tr>
                          <td height="115" valign="top" >
					  <%
					  	String user=SessionManager.getSession(session,"sessionuser");
					  	if(user==null){
					  %>
					  		 <form method="post" action="checkuser.jsp">
						  <table width="100%" border="0" cellpadding="0" cellspacing="0">

                                <tr>
                                  <td height="28" colspan="2" align="center"><img src="../images/big_load.gif" width="189" height="30"></td>
                                </tr>
                                <tr align="center">
                                  <td width="41%" height="28"><strong>用户名:</strong></td>
                                  <td width="59%"><input name="userName" type="text" class="text" id="userName" size="16">
                                  </td>
                                </tr>
                                <tr align="center">
                                  <td height="27"><strong>密&nbsp;&nbsp;码:</strong></td>
                                  <td height="27"> <input name="passWd" type="password" class="text" id="passWd" size="16">
                                  </td>
                                </tr>
                                <tr align="center">
                                  <td height="30" colspan="2"> <table width="100%" cellpadding="0" cellspacing="0">
                                      <tr align="center">
                                        <td width="31%" height="20" align="right" valign="middle">
                                          <input type="image" src="../images/button_login.gif" width="46" height="18">
                                        </td>
                                        <td width="29%" align="center" valign="middle"><a href="register.jsp"><img src="../images/button_reg.gif" width="44" height="18" border="0"></a>
                                        </td>
                                        <td width="40%" align="left" valign="middle"><a href="javascript:show('fetchpwd.jsp')"><img src="../images/button_getps.gif" width="69" height="18" border="0"></a></td>
                                      </tr>
                                    </table></td>
                                </tr>

                            </table>
                            </form>
						<%
							}else{
						%>
					    <table width="100%" height="107" border="0" cellpadding="0" cellspacing="0">
                          <tr>
							 <td height="28" align="center"><font color="red"><u><b><%=user%></b></u></font>&nbsp;:&nbsp;您好！</td>
                            </tr>
                            <tr>
                              <td height="28">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;您的IP:<font color="red"><%=request.getRemoteAddr()%></font></td>
                            </tr>
                            <tr>
                              <td height="25">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;您的身份:<font color="red"><%=personal.pdmPersonal()%></font></td>
                            </tr>
                            <tr>
                              <td height="24">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;您发表的文章总数:<font color="red"><%=personal.sumPersonal()%></font></td>
                            </tr>
                        </table>
						<%
							}
						%>
							</td>
                        </tr>
                        <tr>
                          <td height="100" valign="top"> <table width="100%" border="0" cellspacing="0" cellpadding="0">
                              <tr>
                                <td height="39" colspan="2" align="center"><img src="../images/lltj.gif" width="189" height="30"></td>
                              </tr>
                              <tr>
                                <td width="21%" height="23" align="right"><img src="../images/1.gif" width="21" height="21"></td>
                                <td width="79%">&nbsp;&nbsp;文章总数:&nbsp;<font color="#ff0000"><%=newsShow.sumNews()%></font></td>
                              </tr>
                              <tr>
                                <td height="23" align="right"><img src="../images/2.gif" width="21" height="20"></td>
                                <td height="23">&nbsp;&nbsp;注册用户数:&nbsp;<font color="#ff0000"><%=newsShow.sumUser()%></font></td>
                              </tr>
                              <tr>
                                <td height="22" align="right"><img src="../images/3.gif" width="21" height="21"></td>
                                <td height="22">&nbsp;&nbsp;共计访问总数:&nbsp;<font color="red"><%
									if(counter.isIp(request.getRemoteAddr())){
										out.print(counter.counter());
									}
									else{
										counter.upCounter(request.getRemoteAddr());
										out.print(counter.counter());
									}
								%>
                                  </font> </td>
                              </tr>
                            </table></td>
                        </tr>
                      </table>
                    </center>
                  </div></td>
              </tr>
            </table></td>
        </tr>
      </table><br></td>
  </tr>
  <tr> 
    <td align="center" bgcolor="#FFFFFF"></td>
  </tr>
</table>
</body>
<%@ include file="../include/copyleft.jsp"%>
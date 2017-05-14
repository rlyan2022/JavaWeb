<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="java.sql.*" %>
<%@ page import="java.util.*" %>
<%@ page import="com.sanqing.persistence.*" %>

<jsp:useBean id="SessionManager" class="com.sanqing.servlet.SessionManager"/>
<jsp:useBean id="personal" scope="page" class="com.sanqing.news.Personal"/>
<jsp:useBean id="linkNews" scope="page" class="com.sanqing.news.LinkNews"/>
<jsp:useBean id="newsShow" scope="page" class="com.sanqing.news.NewsShow"/>
<jsp:useBean id="servlet" scope="page" class="com.sanqing.servlet.DOServlet"/>
<jsp:useBean id="reply" class="com.sanqing.news.Reply"/>
<jsp:useBean id="newsR" class="com.sanqing.persistence.NEWSReply">
<jsp:setProperty name="newsR" property="*"/>
</jsp:useBean>

<%@ include file="../include/head.jsp"%>
<link rel="stylesheet" href="../css/text.css" type="text/css">
<body topMargin=0>
<table width="760" border="0" align="center" cellpadding="0" cellspacing="0" height="360">
  <%
			//更新阅读次数
			newsShow.upReadTime(servlet.requestInt(request,"newsId"));
			//列出文章的内容
  			Iterator rs=newsShow.listNews(servlet.requestInt(request,"newsId"));
			while(rs.hasNext()){
				NEWSTable tableNews = (NEWSTable)rs.next();
  		%>
  <tr>
    <td width="11" rowspan="3" background="../images/middle_left.gif" height="364">　</td>
    <td width="738" height="20"> <table align=center border=0 cellPadding=0 cellSpacing=0 width=738 height=20 background="../images/index.gif">
        <tr>
          <td width=736 height=20 class=cdfont valign="bottom"> 　　　<a href="index.jsp"><font color="#333333">首页</font></a>　　　　
            <%Iterator rs_class = linkNews.showClass();while(rs_class.hasNext()){NEWSClass tableClass=(NEWSClass)rs_class.next();%><a href="news.jsp?classId=<%=tableClass.getClassId()%>"><font color="#333333"><%=tableClass.getContent()%></font></a>　　　<%}%>　　<a href="../note/index.jsp" target="_blank"><font color="#333333">
            留言本</font></a></td>
        </tr>
      </table></td>
    <td width="11" rowspan="3" background="../images/middle_right.gif" height="364">　</td>
  </tr>
  <tr>
    <td height="340" valign="top">
      <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td height="23"> &nbsp; &nbsp; 您现在的位置：<font color="#3366CC">&nbsp; </font><a href="news.jsp?classId=<%=tableNews.getClassId()%>"><%=newsShow.strClass(tableNews.getClassId())%></a>&nbsp;>>&nbsp;<a href="newsII.jsp?kindId=<%=tableNews.getKindId()%>"><%=newsShow.strKind(tableNews.getKindId())%></a><font color="#3366CC">&nbsp;
            </font></td>
        </tr>
      </table>
      <table width="100%" border="1" cellpadding="0" cellspacing="0" style="border-collapse: collapse" bordercolor="#CCCCCC">
        <tr>
          <td width="27%" height="315" valign="top">
            <table width="97%" align="right" cellpadding="0" cellspacing="0" style="border-collapse: collapse">
              <tr>
                <td>
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
                              <td width="48%" height="24">
                              <input name="image" type="image" src="../images/button_login.gif" width="46" height="18"></td>
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
					<%}%>
				  </td>
              </tr>
              <tr>
                <td height="84" valign="top"> <table width="100%" height="82" border="0" cellpadding="0" cellspacing="0">
                    <form action="search.jsp" method="post">
                      <tr>
                        <td height="24"> <p><img src="../images/big_search.gif" width="189" height="30"></p></td>
                      </tr>
                      <tr>
                        <td height="23" align="center"><input type="text" size="20" name="search" class="text"></td>
                      </tr>
                      <tr>
                        <td height="29"> <table width="100%" border="0" cellspacing="0" cellpadding="0">
                            <tr>
                              <td width="54%" height="26" align="right"><select name="select">
                                  <option value="0">文章标题</option>
                                  <option value="1">文章内容</option>
                                </select></td>
                              <td width="46%" align="center">
                              <input name="image2" type="image" src="../images/search.gif" width="56" height="22"></td>
                            </tr>
                          </table></td>
                      </tr>
                    </form>
                  </table></td>
              </tr>
              <tr>
                <td height="118" valign="top"><table width="100%" border="0" cellspacing="0" cellpadding="0">
                    <tr>
                    </tr>
                    <tr>
                      <td height="46" valign="top"> <table width="100%" border="0" cellspacing="0" cellpadding="0">
                    <%
						//热门文章
						Iterator rs_hot = newsShow.listHotNews(tableNews.getKindId());
						while(rs_hot.hasNext()){
							NEWSTable newsTable_hot = (NEWSTable)rs_hot.next();
					%>
                          <tr>
                            <td width="10%" height="22" align="right">
                            <img src="../images/dot.gif" width="6" height="9"></td>
                            <td width="90%">&nbsp;<a href="listnews.jsp?newsId=<%=newsTable_hot.getNewsId()%>"><%=newsTable_hot.getHeadTitle()%></a></td>
                          </tr>
                    <%
						}
					%>
                        </table></td>
                    </tr>
                  </table><br></td>
              </tr>
            </table>
            <br></td>
          <td width="73%" valign="top">
		  <table width="98%" align="center" cellpadding="0" cellspacing="0">
              <tr>
                <td colspan="2" valign="middle" background="../images/point.gif">
                  <table width="100%" height="49" border="0" cellpadding="0" cellspacing="0">
                    <tr>
                      <td width="25%" height="28">　</td>
                      <td width="50%" align="center" class="title"><b><%=tableNews.getHeadTitle()%></b></td>
                      <td width="25%" rowspan="2" valign="bottom"><table width="100%" cellspacing="0" cellpadding="0">
                          <tr>
                            <td>出处：<%=tableNews.getNewsFrom()%></td>
                          </tr>
                          <tr>
                            <td align="right"><font color="#ff0000">
                              <%=newsShow.valueMyOther(tableNews.getMyOther())%>
                              </font>　</td>
                          </tr>
                        </table></td>
                    </tr>
                    <tr>
                      <td height="21">[<%=tableNews.getNewsTime()%>] </td>
                      <td align="center">作者：<%=tableNews.getAuthor()%></td>
                    </tr>
                  </table></td>
              </tr>
              <tr>
                <td height="23" colspan="2" valign="top"> <table width="100%" border="0" cellpadding="0" cellspacing="0" style="word-break:break-all">
                    <tr>
                      <td height="21" valign="top" class=article>&nbsp;&nbsp;&nbsp;
                        <%=tableNews.getContent()%></td>
                    </tr>
                  </table></td>
              </tr>
              <tr>
                <td width="60%" height="22" valign="middle">编辑：<%=tableNews.getEditor()%></td>
                <td width="40%" valign="middle">阅读次数: <font color="red"><%=tableNews.getHits()%></font></td>
              </tr>
            </table>
           <br>
            <br>
            <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
              <tr>
                <td height="44" valign="top">
					<table width="100%" border="1" cellpadding="0" cellspacing="0" style="border-collapse: collapse" bordercolor="#CCCCCC">
                    <tr>
                      <td height="25" background="../images/listarticle.gif"> &nbsp;&nbsp;相关文章：</td>
                    </tr>
                    <tr>
                      <td height="22" align="left">
						<table width="100%" border="0" cellpadding="0" cellspacing="0">
                          <%
							if(linkNews.isConnectLink(tableNews.getConnect())){
								Iterator rs_connect = linkNews.connectLink(tableNews.getConnect());
								while(rs_connect.hasNext()){
									NEWSTable tableNews_connect = (NEWSTable)rs_connect.next();
						%>
                          <tr>
                            <td width="3%" height="21" align="right"><img src="../images/per_link1.gif" width="12" height="12"></td>
                            <td width="77%" align="left">&nbsp;<a href="listnews.jsp?newsId=<%=tableNews_connect.getNewsId()%>"><%=tableNews_connect.getHeadTitle()%></a></td>
                            <td width="20%" height="21">&nbsp;<%//=tableNews_connect.getNewsTime()%></td>
                          </tr>
						  <%
						  			}
						  		}
						  %>
                        </table></td>
                    </tr>
                  </table></td>
              </tr>
            </table><br>
			<table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
              <tr>
                <td height="12" align="right" valign="top"><a href="reply.jsp?newsId=<%=tableNews.getNewsId()%>">发表评论</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
              </tr>
              <tr>
                <td height="56">
                  <table width="100%" border="1" style="border-collapse: collapse" cellpadding="0" cellspacing="0" bordercolor="#CCCCCC">
                    <tr>
                      <td height="27" align="center" background="../images/listarticle.gif" bgcolor="#efefef"><table width="100%" border="0" cellpadding="0" cellspacing="0">
                          <tr>
                            <td width="16%" height="20" align="center"><font color="#FF0000">笔名</font></td>
                            <td width="63%">&nbsp;&nbsp;<font color="#FF0000">发表内容</font></td>
                            <td width="21%" align="center"><font color="#FF0000">时间</font></td>
                          </tr>
                        </table></td>
                    </tr>
                    <tr>
                      <td>
                        <table width="100%" border="1" cellpadding="0" cellspacing="0" bordercolor="#efefef">
                          <%
						  		//添加回复文章
								String action=servlet.requestStr(request,"submit");
								if(action.equals("true")){
									reply.insReply(newsR.getNewsId(),newsR.getUser(),newsR.getContent());
								}
						  %>
                          <%
							  //列出回复的内容
						 		if(reply.isReply(newsR.getNewsId())){
									Iterator rs_reply=reply.listReply(newsR.getNewsId());
									while(rs_reply.hasNext()){
										NEWSReply tableReply = (NEWSReply)rs_reply.next();
						 %>
                          <tr>
                            <td width="16%" height="22" align="center"><%=tableReply.getUser()%></td>
                            <td width="63%" height="22"><%=tableReply.getContent()%></td>
                            <td width="21%" align="center" height="22"><%=tableReply.getReplyTime()%></td>
                          </tr>
                          <%
						 			}
						 	}
								//else{
						%>
                          <!--
						  <tr>
                            <td height="21" colspan="3" align="center"><font color="#AFB9D5">还没有任何评论！</font></td>
                          </tr>
						  -->
                          <%
						  //		}
						  %>
                        </table></td>
                    </tr>
                  </table></td>
              </tr>
            </table>
			</td>
        </tr>
      </table>
    </td>
  </tr>
  <%}%>
</table>
</body>
<%@ include file="../include/copyleft.jsp"%>
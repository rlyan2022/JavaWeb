<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.sql.*"%>
<%@ include file="check.jsp"%>

<jsp:useBean id="usr" class="com.sanqing.news.manage.Usr"/>
<HTML>
<HEAD>
<TITLE>新闻发布系统--管理页面</TITLE>
<STYLE type=text/css>BODY {
	BACKGROUND: #799ae1; FONT: 9pt 宋体; MARGIN: 0px
}
TABLE {
	BORDER-BOTTOM: 0px; BORDER-LEFT: 0px; BORDER-RIGHT: 0px; BORDER-TOP: 0px
}
TD {
	FONT: 12px 宋体
}
IMG {
	BORDER-BOTTOM: 0px; BORDER-LEFT: 0px; BORDER-RIGHT: 0px; BORDER-TOP: 0px; VERTICAL-ALIGN: bottom
}
A {
	COLOR: #000000; FONT: 12px 宋体; TEXT-DECORATION: none
}
A:hover {
	COLOR: #428eff; TEXT-DECORATION: underline
}
.sec_menu {
	BACKGROUND: #d6dff7; BORDER-BOTTOM: white 1px solid; BORDER-LEFT: white 1px solid; BORDER-RIGHT: white 1px solid; OVERFLOW: hidden
}
.menu_title {

}
.menu_title SPAN {
	COLOR: #215dc6; FONT-WEIGHT: bold; LEFT: 8px; POSITION: relative; TOP: 2px
}
.menu_title2 {

}
.menu_title2 SPAN {
	COLOR: #428eff; FONT-WEIGHT: bold; LEFT: 8px; POSITION: relative; TOP: 2px
}
</STYLE>

<SCRIPT language=javascript1.2>
function showsubmenu(sid)
{
whichEl = eval("submenu" + sid);
if (whichEl.style.display == "none")
{
eval("submenu" + sid + ".style.display=\"\";");
}
else
{
eval("submenu" + sid + ".style.display=\"none\";");
}
}
</SCRIPT>

<META content="MSHTML 5.00.3700.6699" name=GENERATOR>
</HEAD>

<BODY leftMargin=0 topMargin=0 marginwidth="0" marginheight="0">
<TABLE align=left border=0 cellPadding=0 cellSpacing=0 width="100%">
  <TBODY>
  <TR>
    <TD vAlign=top>
      <TABLE align=center cellPadding=0 cellSpacing=0 width=158>
        <TBODY>
        <TR>
              <TD height=42 vAlign=bottom><IMG height=38 src="../images/admin/title1.gif" width=158></TD>
		</TR>
		</TBODY>
      </TABLE>
      <TABLE align=center cellPadding=0 cellSpacing=0 width=158>
        <TBODY>
        <TR>
              <TD background=../images/admin/title2.gif class=menu_title height=25 onmouseout="this.className='menu_title';"onmouseover="this.className='menu_title2';"><SPAN><A href="../news/index.jsp" target=_top><B>返回首页</B></A>
                | <A href="logout.jsp" target=_top><B>退出</B></A></SPAN> </TD>
         </TR>
		 </TBODY>
	  </TABLE>
        &nbsp;
	<%
	  		if(usr.adminPurview(adminName)==0){
	%>
      <TABLE align=center cellPadding=0 cellSpacing=0 width=158>
        <TBODY>
        <TR>
              <TD background=../images/admin/title3.gif class=menu_title height=25 id=menuTitle1 onclick=showsubmenu(1) onmouseout="this.className='menu_title';" onmouseover="this.className='menu_title2';"><SPAN>栏目管理</SPAN>
              </TD>
        </TR>
        <TR>
              <TD height="80" id=submenu1>
                <DIV class=sec_menu style="WIDTH: 158px">
                  <TABLE align=center cellPadding=0 cellSpacing=0 width=135>
                    <TBODY>
                      <TR>
                        <TD height=20><a href="column/column.jsp" target=main>总栏管理</a></TD>
                      </TR>
                      <TR>
                        <TD height=20><a href="column/kind.jsp" target=main>类别管理</a></TD>
                      </TR>
                    </TBODY>
                  </TABLE>
                </DIV>
            <DIV style="WIDTH: 158px">
            <TABLE align=center cellPadding=0 cellSpacing=0 width=135>
              <TBODY>
              <TR>
                <TD height=20></TD>
			  </TR>
			  </TBODY>
			</TABLE>
		    </DIV>
			</TD>
		</TR>
		</TBODY>
	  </TABLE>
	  <%}%>
	  <%
	  		if(usr.adminPurview(adminName)==0){
	  %>
      <TABLE align=center cellPadding=0 cellSpacing=0 width=158>
        <TBODY>
        <TR>
              <TD background=../images/admin/title4.gif class=menu_title height=25 id=menuTitle1 onclick=showsubmenu(2) onmouseout="this.className='menu_title';" onmouseover="this.className='menu_title2';"><SPAN>用户管理</SPAN>
              </TD>
        </TR>
        <TR>
              <TD height="80" id=submenu2>
                <DIV class=sec_menu style="WIDTH: 158px">
                  <TABLE align=center cellPadding=0 cellSpacing=0 width=135>
                    <TBODY>
                      <TR>
                        <TD height=20><A href="usr/user.jsp" target=main>用户信息管理</A></TD>
                      </TR>
                      <TR>
                        <TD height=20><A href="usr/manager.jsp" target=main>管理员信息管理</A></TD>
                      </TR>
                    </TBODY>
                  </TABLE>
                </DIV>
            <DIV style="WIDTH: 158px">
            <TABLE align=center cellPadding=0 cellSpacing=0 width=135>
              <TBODY>
              <TR>
                <TD height=20></TD>
			  </TR>
			  </TBODY>
			 </TABLE>
			 </DIV>
			 </TD>
		  </TR>
		  </TBODY>
		</TABLE>
		<%
			}
			else{
				if(usr.adminPurview(adminName)==1){
		%>
		<TABLE align=center cellPadding=0 cellSpacing=0 width=158>
        <TBODY>
        <TR>
              <TD background=../images/admin/title4.gif class=menu_title height=25 id=menuTitle1 onclick=showsubmenu(2) onmouseout="this.className='menu_title';" onmouseover="this.className='menu_title2';"><SPAN>用户管理</SPAN>
              </TD>
        </TR>
        <TR>
              <TD height="80" id=submenu2>
                <DIV class=sec_menu style="WIDTH: 158px">
                  <TABLE align=center cellPadding=0 cellSpacing=0 width=135>
                    <TBODY>
                      <TR>
                        <TD height=20><a href="usr/user.jsp" target=main>用户信息管理</a></TD>
                      </TR>
                      <TR>
                        <TD height=20><a href="usr/editadmin.jsp" target=main>修改个人资料</a></TD>
                      </TR>
                      <TR>
                        <TD height=20><A href="usr/backuser.jsp" target=main>后台用户管理</A></TD>
                      </TR>
                    </TBODY>
                  </TABLE>
                </DIV>
            <DIV style="WIDTH: 158px">
            <TABLE align=center cellPadding=0 cellSpacing=0 width=135>
              <TBODY>
              <TR>
                <TD height=20></TD>
			  </TR>
			  </TBODY>
			 </TABLE>
			 </DIV>
			 </TD>
		  </TR>
		  </TBODY>
		</TABLE>
	  <%}else{%>
	  <TABLE align=center cellPadding=0 cellSpacing=0 width=158>
        <TBODY>
        <TR>
              <TD background=../images/admin/title4.gif class=menu_title height=25 id=menuTitle1 onclick=showsubmenu(2) onmouseout="this.className='menu_title';" onmouseover="this.className='menu_title2';"><SPAN>用户管理</SPAN>
              </TD>
        </TR>
        <TR>
              <TD height="45" id=submenu2>
                <DIV class=sec_menu style="WIDTH: 158px">
                  <TABLE align=center cellPadding=0 cellSpacing=0 width=135>
                    <TBODY>
                      <TR>
                        <TD height=21><a href="usr/editadmin.jsp" target=main>修改个人资料</a></TD>
                      </TR>
                    </TBODY>
                  </TABLE>
                </DIV>
            <DIV style="WIDTH: 158px">
            <TABLE align=center cellPadding=0 cellSpacing=0 width=135>
              <TBODY>
              <TR>
                        <TD height=23></TD>
			  </TR>
			  </TBODY>
			 </TABLE>
			 </DIV>
			 </TD>
		  </TR>
		  </TBODY>
		</TABLE>
		<%
				}
			}
		%>
		<%if(usr.adminPurview(adminName)<2){%>
      <TABLE align=center cellPadding=0 cellSpacing=0 width=158>
        <TBODY>
        <TR>
              <TD background=../images/admin/title5.gif class=menu_title height=25 id=menuTitle1 onclick=showsubmenu(3) onmouseout="this.className='menu_title';" onmouseover="this.className='menu_title2';"><SPAN>文章管理</SPAN>
              </TD>
        </TR>
        <TR>
              <TD height="100" id=submenu3>
                <DIV class=sec_menu style="WIDTH: 158px">
                  <TABLE align=center cellPadding=0 cellSpacing=0 width=135>
                    <TBODY>
                      <TR>
                        <TD height=20><A href="news/news.jsp" target=main>新增文章
                          </a></TD>
                      </TR>
                      <TR>
                        <TD height=20><a href="news/shnews.jsp" target=main>文章审批</a></TD>
                      </TR>
                      <TR>
                        <TD height=23><a href="news/newslist.jsp" target=main>文章管理</a></TD>
                      </TR>
                      <TR>
                        <TD height=20><a href="news/newstalk.jsp" target=main>文章评论管理</a></TD>
                      </TR>
                    </TBODY>
                  </TABLE>
                </DIV>
            <DIV style="WIDTH: 158px">
            <TABLE align=center cellPadding=0 cellSpacing=0 width=135>
              <TBODY>
              <TR>
                <TD height=20></TD>
			  </TR>
			  </TBODY>
			</TABLE>
			</DIV>
			</TD>
		</TR>
		</TBODY>
	  </TABLE>
	  <%}else{%>
		<TABLE align=center cellPadding=0 cellSpacing=0 width=158>
        <TBODY>
        <TR>
              <TD background=../images/admin/title5.gif class=menu_title height=25 id=menuTitle1 onclick=showsubmenu(3) onmouseout="this.className='menu_title';" onmouseover="this.className='menu_title2';"><SPAN>文章管理</SPAN>
              </TD>
        </TR>
        <TR>
              <TD height="45" id=submenu3>
                <DIV class=sec_menu style="WIDTH: 158px">
                  <TABLE align=center cellPadding=0 cellSpacing=0 width=135>
                    <TBODY>
                      <TR>
                        <TD height=23><A href="news/news.jsp" target=main>添加文章
                          </a></TD>
                      </TR>
                    </TBODY>
                  </TABLE>
                </DIV>
            <DIV style="WIDTH: 158px">
            <TABLE align=center cellPadding=0 cellSpacing=0 width=135>
              <TBODY>
              <TR>
                        <TD height=23></TD>
			  </TR>
			  </TBODY>
			</TABLE>
			</DIV>
			</TD>
		</TR>
		</TBODY>
	  </TABLE>
	  <%}%>
	  <%
			if(usr.adminPurview(adminName)==0){
	  %>
      <TABLE align=center cellPadding=0 cellSpacing=0 width=158>
        <TBODY>
        <TR>
              <TD background=../images/admin/title6.gif class=menu_title height=25 id=menuTitle1 onclick=showsubmenu(4) onmouseout="this.className='menu_title';" onmouseover="this.className='menu_title2';"><SPAN>站务管理</SPAN>
              </TD>
        </TR>
        <TR>
              <TD height="100" id=submenu4>
                <DIV class=sec_menu style="WIDTH: 158px">
                  <TABLE align=center cellPadding=0 cellSpacing=0 width=135>
                    <TBODY>
                      <TR>
                        <TD height=20>版权管理</TD>
                      </TR>
                      <TR>
                        <TD height=20>广告管理</TD>
                      </TR>
                      <TR> 
                        <TD height=23>&nbsp;</TD>
                      </TR>
                      <TR> 
                        <TD height=20>&nbsp;</TD>
                      </TR>
                    </TBODY>
                  </TABLE>
                </DIV>
            <DIV style="WIDTH: 158px">
            <TABLE align=center cellPadding=0 cellSpacing=0 width=135>
              <TBODY>
              <TR>
                <TD height=20></TD>
			  </TR>
			  </TBODY>
			</TABLE>
			</DIV>
			</TD>
		</TR>
		</TBODY>
	  </TABLE>
	  <%}%>
     &nbsp; 
  </TR></TBODY></TABLE>
  </BODY>
</HTML>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/share/taglib.jsp" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="<%=basePath%>style/oa.css" rel="stylesheet" type="text/css">
<script language="javascript" src="<%=basePath%>script/public.js"></script>
<title>查询产品信息</title>
</head>
<body>
<center>
<form action="product.do" method="post">
<input type="hidden" name="query" value="true">
<center>
<TABLE width="778" border=0 cellPadding=0 cellSpacing=0 borderColor=#ffffff bgColor=#dee7ff style="FONT-SIZE: 10pt">
        <TBODY>
          <TR height=35>
            <TD align=middle width=20 background=<%=basePath%>images/title_left.gif
          bgColor=#dee7ff></TD>
            <TD align=middle width=120 background=<%=basePath%>images/title_left.gif
          bgColor=#dee7ff><FONT color=#f7f7f7>产品查询<font color="#FFFFFF">&nbsp;</font></FONT> </TD>
            <TD align=middle width=11 background=<%=basePath%>images/title_middle.gif
          bgColor=#dee7ff><FONT color=#f7f7f7>&nbsp;</FONT> </TD>
            <TD align=middle background=<%=basePath%>images/title_right.gif
          bgColor=#dee7ff><FONT color=#f7f7f7>&nbsp;</FONT> </TD>
          </TR>
        </TBODY>
      </TABLE>
</center>
<TABLE width="778" border=0 align=center cellPadding=0 cellSpacing=0 borderColor=#ffffff style="FONT-SIZE: 10pt">
        <TBODY>
          <TR>
            <TD width="82%" height=14 align=right vAlign=center noWrap>
            </TD>
            <TD width="18%" align=right vAlign=center noWrap>　</TD>
          </TR>
          <TR>
            <TD height=14 align=right vAlign=center noWrap>
            	<!-- 在这里插入查询表单 -->
            </TD>
          </TR>
          <TR>
            <TD height=28 colspan="2" align=right vAlign=center noWrap background="<%=basePath%>images/list_middle.jpg">&nbsp;&nbsp;
            <!-- 可以在这里插入分页导航条 -->
            </TD>
          </TR>
        </TBODY>
      </TABLE>
<center>
<TABLE class="tableEdit" border="0" cellspacing="1" cellpadding="0" style="width:580px;">
	<TBODY>
		<TR>
			<!-- 这里是添加、编辑界面的标题 -->
			<td align="center" class="tdEditTitle">查询产品信息</TD>
		</TR>
		<TR>
			<td>
			<!-- 主输入域开始 -->
<table class="tableEdit" style="width:778px;" cellspacing="0" border="0" cellpadding="0">
	<tr>
		<td class="tdEditLabel" >产品编号</td>
		<td class="tdEditContent"><input type="text" name="productNO" ></td>
	</tr>
		<tr>
		<td class="tdEditLabel" >产品类型</td>
		<td class="tdEditContent"><input type="text" name="producttypeName" ></td>
	</tr>
		<tr>
		<td class="tdEditLabel" >产品名称</td>
		<td class="tdEditContent"><input type="text" name="productName" ></td>
	</tr>
		<tr>
		<td class="tdEditLabel" >所在区域</td>
		<td class="tdEditContent"><input type="text" name="producingArea" ></td>
	</tr>
		<tr>
		<td class="tdEditLabel" >所有者</td>
		<td class="tdEditContent"><input type="text" name="productOwner" ></td>
	</tr>
		<tr>
		<td class="tdEditLabel" >单位</td>
		<td class="tdEditContent"><input type="text" name="unit" ></td>
	</tr>
		<tr>
		<td class="tdEditLabel" >价格</td>
		<td class="tdEditContent"><input type="text" name="price" ></td>
	</tr>
		<tr>
		<td class="tdEditLabel" >数量</td>
		<td class="tdEditContent"><input type="text" name="quantity" ></td>
	</tr>
	<tr>
		<td class="tdEditLabel" >其他信息</td>
		<td class="tdEditContent"><input type="text" name="otherInfo" ></td>
	</tr>
</table>
			<!-- 主输入域结束 -->
			</td>
		</TR>
	</TBODY>
</TABLE>

<TABLE>
		<TR align="center">
			<TD colspan="3" bgcolor="#EFF3F7">
			<input type="submit" name="saveButton"
				class="MyButton" value="查询产品信息">
			</TD>
		</TR>
</TABLE>
<TABLE width="778" border=0 align=center cellPadding=0 cellSpacing=0 borderColor=#ffffff style="FONT-SIZE: 10pt">
        <TBODY>
          <TR>
            <TD height=28 align=right vAlign=center noWrap background="<%=basePath%>images/list_middle.jpg">&nbsp;&nbsp;
    		</TD>
          </TR>
        </TBODY>
</TABLE>
</form>
</center>
</body>
</html>
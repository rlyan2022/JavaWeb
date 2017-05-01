<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/share/taglib.jsp" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="<%=basePath%>style/oa.css" rel="stylesheet" type="text/css">
<script language="javascript" src="<%=basePath%>script/public.js"></script>
<script type="text/javascript">
		//到指定的分页页面
	function topage(page){
		var form = document.forms[0];
		form.page.value=page;
		form.submit();
	}
</script>
<title>产品管理</title>
</head>
<BODY bgColor=#dee7ff leftMargin=0 background="" topMargin=0 marginheight="0" marginwidth="0">
<center>
      <TABLE width="778" border=0 cellPadding=0 cellSpacing=0 borderColor=#ffffff bgColor=#dee7ff style="FONT-SIZE: 10pt">
        <TBODY>
          <TR height=35>
            <TD align=middle width=20 background=<%=basePath%>images/title_left.gif
          bgColor=#dee7ff></TD>
            <TD align=middle width=120 background=<%=basePath%>images/title_left.gif
          bgColor=#dee7ff><FONT color=#f7f7f7> 产品管理<font color="#FFFFFF">&nbsp;</font></FONT> </TD>
            <TD align=middle width=11 background=<%=basePath%>images/title_middle.gif
          bgColor=#dee7ff><FONT color=#f7f7f7>&nbsp;</FONT> </TD>
            <TD align=middle background=<%=basePath%>images/title_right.gif
          bgColor=#dee7ff><FONT color=#f7f7f7>&nbsp;</FONT> </TD>
          </TR>
        </TBODY>
      </TABLE>
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
            <TD height=14 align="left" vAlign=center noWrap>
            <a href="#" onclick="openWin('productmanage_addUI.do','addperson',600,200);">新增产品信息</a>
            </TD>
          </TR>
          <TR>
            <TD height=28 colspan="2" align=right vAlign=center noWrap background="<%=basePath%>images/list_middle.jpg">&nbsp;&nbsp;
            <!-- 可以在这里插入分页导航条 -->
            </TD>
          </TR>
        </TBODY>
      </TABLE>
      <form action="product.do" method="post">
      <s:hidden name="query"/>
      <s:hidden name="page"/>
      <s:hidden name="productcode"/>
      <s:hidden name="producttypename"/>
      <s:hidden name="productname"/>
      <s:hidden name="producingarea"/>
      <s:hidden name="productowner"/>
      <s:hidden name="unit"/>
      <s:hidden name="price"/>
      <s:hidden name="quantity"/>
      <s:hidden name="otherInfo"/>
      <table width="778" border="0" cellPadding="0" cellSpacing="1" bgcolor="#6386d6">
          <!-- 列表标题栏 -->
	      <tr bgcolor="#EFF3F7" class="TableBody1">
		      <td width="5%" height="37" align="center"><b>编号</b></td>
		      <td width="10%" height="37" align="center"><B>类型</B></td>
		      <td width="10%" height="37" align="center"><B>名称</B></td>
		      <td width="10%" height="37" align="center"><B>所在区域</B></td>
		      <td width="10%" height="37" align="center"><B>所有者</B></td>
		      <td width="10%" height="37" align="center"><B>单位</B></td>
		      <td width="10%" height="37" align="center"><B>价格</B></td>
		      <td width="10%" height="37" align="center"><B>数量</B></td>
		      <td width="10%" height="37" align="center"><B>其他</B></td>
              <td width="10%" height="37" align="center"><b>操作</b></td>
          </tr>
          <!-- 列表数据栏 -->
         <s:if test="null != #request.pageView.records && !#request.pageView.records.isEmpty() ">
          <s:iterator value="#request.pageView.records" id="entity">
	      <tr bgcolor="#EFF3F7" class="TableBody1" onmouseover="this.bgColor = '#DEE7FF';" onmouseout="this.bgColor='#EFF3F7';">
		      <td align="center" vAlign="center">${entity.productNO }</td>
	          <td align="center" vAlign="center">${entity.productType.producttypeName }</td>
	          <td align="center" vAlign="center">${entity.productName }</td>
	          <td align="center" vAlign="center">${entity.producingArea }</td>
	          <td align="center" vAlign="center">${entity.productOwner }</td>
	          <td align="center" vAlign="center">${entity.unit }</td>
	          <td align="center" vAlign="center">${entity.price }</td>
	          <td align="center" vAlign="center">${entity.quantity }</td>
	          <td align="center" vAlign="center">${entity.otherInfo }</td>
	          <td align="center" vAlign="center"><a href="#" onclick="del('productmanage_del.do?productNO=${entity.productNO}');">删除</a>
	          <a href="#" onclick="openWin('productmanage_updateUI.do?productNO=${entity.productNO}','addperson',600,200);">修改</a>
	          </td>
        </tr>
        </s:iterator>
		</s:if>
        <!-- 在列表数据为空的时候，要显示的提示信息 -->
	    <s:else>
	    <tr>
	    	<td colspan="10" align="center" bgcolor="#EFF3F7" class="TableBody1" onmouseover="this.bgColor = '#DEE7FF';" onmouseout="this.bgColor='#EFF3F7';">
	    	没有找到相应的记录
	    	</td>
	    </tr>
	    </s:else>
      </table>
      <TABLE width="778" border=0 align=center cellPadding=0 cellSpacing=0 borderColor=#ffffff style="FONT-SIZE: 10pt">
        <TBODY>
          <TR>
            <TD height=28 align=right vAlign=center noWrap background="<%=basePath%>images/list_middle.jpg">&nbsp;&nbsp;
            <!-- 可以在这里插入分页导航条 -->
            <%@ include file="/share/fenye.jsp" %>
    		</TD>
          </TR>
        </TBODY>
      </TABLE>
      </form>
</center>

</body>

</html>

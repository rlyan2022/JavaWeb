<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags"  %>

<div id="footer" style="margin-top:5px;width: 600px;">
<s:if test="leavewordPage.totalCount>0">
&nbsp;&nbsp;&nbsp;有${leavewordPage.totalCount}条记录, 显示${(leavewordPage.pageNo-1)*leavewordPage.pageSize+1}到<s:if test="leavewordPage.totalCount-leavewordPage.pageNo*leavewordPage.pageSize>leavewordPage.pageSize">${leavewordPage.pageNo*leavewordPage.pageSize}</s:if><s:else>${leavewordPage.totalCount}</s:else>条记录,当前第${leavewordPage.pageNo}页, 共${leavewordPage.totalPages}页 
每页显示
<select name="pageSizeSelect" onchange="changePageSize(this.options[this.options.selectedIndex].value)">
	<option value="10" <s:if test="leavewordPage.pageSize==10"> selected </s:if>>10</option>
	<option value="20" <s:if test="leavewordPage.pageSize==20"> selected </s:if>>20</option>
	<option value="30" <s:if test="leavewordPage.pageSize==30"> selected </s:if>>30</option>
	<option value="40" <s:if test="leavewordPage.pageSize==40"> selected </s:if>>40</option>
	<option value="50" <s:if test="leavewordPage.pageSize==50"> selected </s:if>>50</option>
	<option value="100" <s:if test="leavewordPage.pageSize==100"> selected </s:if>>100</option>
	<option value="200" <s:if test="leavewordPage.pageSize==200"> selected </s:if>>200</option>
</select>条<br>
<s:if test="leavewordPage.totalPages>1">		
	<s:if test="leavewordPage.hasPre">[<a href="javascript:jumpPage(1)">首页</a>/<a href="javascript:jumpPage(${leavewordPage.prePage})">前一页</a>]</s:if>
	<s:elseif test="1">[<b>首页</b>/前一页]</s:elseif>		
	<s:if test="leavewordPage.pageNo-3>0">...&nbsp;</s:if>
	<s:if test="leavewordPage.pageNo-2>0"><a href="javascript:jumpPage(${leavewordPage.pageNo-2})">${leavewordPage.pageNo-2}</a></s:if>
	<s:if test="leavewordPage.pageNo-1>0"><a href="javascript:jumpPage(${leavewordPage.pageNo-1})">${leavewordPage.pageNo-1}</a></s:if>
	<s:if test="leavewordPage.pageNo>0"><b>${leavewordPage.pageNo}</b></s:if>
	<s:if test="leavewordPage.totalPages>=leavewordPage.pageNo+1"><a href="javascript:jumpPage(${leavewordPage.pageNo+1})">${leavewordPage.pageNo+1}</a></s:if>
	<s:if test="leavewordPage.totalPages>=leavewordPage.pageNo+2"><a href="javascript:jumpPage(${leavewordPage.pageNo+2})">${leavewordPage.pageNo+2}</a></s:if>
	<s:if test="leavewordPage.totalPages>=leavewordPage.pageNo+3"><a href="javascript:jumpPage(${leavewordPage.pageNo+3})">${leavewordPage.pageNo+3}</a></s:if>
	<s:if test="leavewordPage.totalPages>=leavewordPage.pageNo+4"><a href="javascript:jumpPage(${leavewordPage.pageNo+4})">${leavewordPage.pageNo+4}</a></s:if>
	<s:if test="leavewordPage.totalPages>=leavewordPage.pageNo+5">...&nbsp;</s:if>	
	<s:if test="leavewordPage.hasNext">[<a href="javascript:jumpPage(${leavewordPage.nextPage})">后一页</a>/<a href="javascript:jumpPage(${leavewordPage.totalPages})">尾页</a>]</s:if>
	<s:elseif test="leavewordPage.hasNext-1">[后一页/<b>尾页</b>]</s:elseif>	  
</s:if>
</s:if>
</div>
<br/>
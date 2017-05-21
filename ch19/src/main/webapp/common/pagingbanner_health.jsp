<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags"  %>
<div style="WIDTH: 210px;  font-size:12px; color:#000000;">
<s:if test="healthPage.totalCount>0">
有${healthPage.totalCount}条记录, 显示${(healthPage.pageNo-1)*healthPage.pageSize+1}到<s:if test="healthPage.totalCount-healthPage.pageNo*healthPage.pageSize>healthPage.pageSize">${healthPage.pageNo*healthPage.pageSize}</s:if><s:else>${healthPage.totalCount}</s:else>条记录<br/>
第${healthPage.pageNo}页, 共${healthPage.totalPages}页 
每页显示
<select name="pageSizeSelect" onchange="changePageSize(this.options[this.options.selectedIndex].value)">
	<option value="10" <s:if test="healthPage.pageSize==10"> selected </s:if>>10</option>
	<option value="20" <s:if test="healthPage.pageSize==20"> selected </s:if>>20</option>
	<option value="30" <s:if test="healthPage.pageSize==30"> selected </s:if>>30</option>
</select>条<br>
<s:if test="healthPage.totalPages>1">	
	<br/>	
	<s:if test="healthPage.hasPre">[<a href="javascript:jumpPage(1)">首页</a>/<a href="javascript:jumpPage(${healthPage.prePage})">前一页</a>]</s:if>
	<s:elseif test="1">[<b>首页</b>/前一页]</s:elseif>		
	<s:if test="healthPage.pageNo-3>0">...&nbsp;</s:if>
	<s:if test="healthPage.pageNo-2>0"><a href="javascript:jumpPage(${healthPage.pageNo-2})">${healthPage.pageNo-2}</a></s:if>
	<s:if test="healthPage.pageNo-1>0"><a href="javascript:jumpPage(${healthPage.pageNo-1})">${healthPage.pageNo-1}</a></s:if>
	<s:if test="healthPage.pageNo>0"><b>${healthPage.pageNo}</b></s:if>
	<s:if test="healthPage.totalPages>=healthPage.pageNo+1"><a href="javascript:jumpPage(${healthPage.pageNo+1})">${healthPage.pageNo+1}</a></s:if>
	<s:if test="healthPage.totalPages>=healthPage.pageNo+2"><a href="javascript:jumpPage(${healthPage.pageNo+2})">${healthPage.pageNo+2}</a></s:if>
	<s:if test="healthPage.totalPages>=healthPage.pageNo+3"><a href="javascript:jumpPage(${healthPage.pageNo+3})">${healthPage.pageNo+3}</a></s:if>
	<s:if test="healthPage.totalPages>=healthPage.pageNo+4"><a href="javascript:jumpPage(${healthPage.pageNo+4})">${healthPage.pageNo+4}</a></s:if>
	<s:if test="healthPage.totalPages>=healthPage.pageNo+5">...&nbsp;</s:if>	
	<s:if test="healthPage.hasNext">[<a href="javascript:jumpPage(${healthPage.nextPage})">后一页</a>/<a href="javascript:jumpPage(${healthPage.totalPages})">尾页</a>]</s:if>
	<s:elseif test="healthPage.hasNext-1">[后一页/<b>尾页</b>]</s:elseif>	  
</s:if>
</s:if>
</div>
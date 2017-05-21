<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://displaytag.sf.net/el" prefix="display" %>
<display:setProperty name="paging.banner.all_items_found">
	<br><span class='pagebanner'>有{0}条{1}, 显示所有{2}.
	每页显示
	<select name="pageSizeSelect" onchange="changePageSize(this.options[this.options.selectedIndex].value)">		
		<option value="10" <s:if test="pageSize==10">selected</s:if>>10</option>
		<option value="20" <s:if test="pageSize==20">selected</s:if>>20</option>
		<option value="30" <s:if test="pageSize==30">selected</s:if>>30</option>
		<option value="40" <s:if test="pageSize==40">selected</s:if>>40</option>
		<option value="50" <s:if test="pageSize==50">selected</s:if>>50</option>
		<option value="100" <s:if test="pageSize==100">selected</s:if>>100</option>
		<option value="200" <s:if test="pageSize==200">selected</s:if>>200</option>
	</select>条
	</span>
	</display:setProperty>		
	<display:setProperty name="paging.banner.some_items_found">
	<br><span class='pagebanner'>有{0}条{1}, 显示{2}到{3}.    	
	每页显示
	<select name="pageSizeSelect" onchange="changePageSize(this.options[this.options.selectedIndex].value)">		
		<option value="10" <s:if test="pageSize==10">selected</s:if>>10</option>
		<option value="20" <s:if test="pageSize==20">selected</s:if>>20</option>
		<option value="30" <s:if test="pageSize==30">selected</s:if>>30</option>
		<option value="40" <s:if test="pageSize==40">selected</s:if>>40</option>
		<option value="50" <s:if test="pageSize==50">selected</s:if>>50</option>
		<option value="100" <s:if test="pageSize==100">selected</s:if>>100</option>
		<option value="200" <s:if test="pageSize==200">selected</s:if>>200</option>
	</select>条
    </span>
</display:setProperty>
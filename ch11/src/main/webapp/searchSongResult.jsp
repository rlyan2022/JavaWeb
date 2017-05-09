<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<html>
  <head>
    <title>华语歌曲</title>
    <link href="css.css" media="screen" rel="Stylesheet" type="text/css" />
    <script type="text/javascript">
    </script>
  </head>
  <body>
   <form action="playSong.action" method="post">
 	<table  width="800px" align="center">
 	   <s:iterator value="searchSongReult" status="st">
 	     <s:if test="${st.index % 8 == 0}">
 	       <s:if test="${st.index == 0}">
 	          <tr>
 	            <td>
 	              <input type="checkbox" name="songIds"  id="<s:property value='id'/>" value="<s:property value='id'/>"><label for="<s:property value='id'/>" class="checkboxLabel"><s:property value="name"/>  
 	            </td>
 	       </s:if>
 	       <s:else>
 	           </tr>
 	           <tr>
 	              <td>
 	                  <input type="checkbox" name="songIds"  id="<s:property value='id'/>" value="<s:property value='id'/>"><label for="<s:property value='id'/>" class="checkboxLabel"><s:property value="name"/>
 	               </td>
 	       </s:else>
 	     </s:if>
 	     <s:else>
 	         <td>
 	             <input type="checkbox" name="songIds"  id="<s:property value='id'/>" value="<s:property value='id'/>"><label for="<s:property value='id'/>" class="checkboxLabel"><s:property value="name"/>
 	         </td>
 	     </s:else>
 	  </s:iterator>
 	  </tr>
 	</table>
 	 <p align="center"><input type="submit" value="选种播放歌曲"></p>
 	</form>
  </body>
</html>
    
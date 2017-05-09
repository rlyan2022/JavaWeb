<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<html>
  <head>
    <title></title>
    <link href="css.css" media="screen" rel="Stylesheet" type="text/css" />
    <script type="text/javascript">
    	function selectAll(index){
    	  alert(index);
    	  var checkboxes = document.forms[index].elements;
    	  alert(checkboxes.length);
    	  for(var i = 0; i < checkboxes.length; i++){
    	     checkboxes[i].checked = true;
    	  }
    	}
    	function selectReverse(index){
    	  var checkboxes = document.forms[index].elements;
    	  for(var i = 0; i < checkboxes.length; i++){
    	    if(checkboxes[i].checked)
    	      checkboxes[i].checked = false;
    	     else
    	      checkboxes[i].checked = true;
    	  }
    	}
    </script>
  </head>
  <body>
  <div id="main">
   <div id="content">
    <div class="leftContainer">
     <div class="title">最新歌曲排行</div>
     <form action="playSong.action" method="post">
      <ul>
          <s:iterator value="songSingerMap" status="st">
    	  	<li><input type="checkbox" name="songIds"  id="<s:property value='key.id'/>" value="<s:property value='key.id'/>"><label for="<s:property value='key.id'/>" class="checkboxLabel"><s:property value="key.name"/>
       	     （<s:iterator value="value">
          	   <s:property value="name"/>
             </s:iterator>）
              </label></li>
       	  </s:iterator>
      </ul>
       <p align="center">
         <!--  <input value="全选" type="button" onclick="selectAll(0)">
         <input value="反选" type="button" onclick="selectReverse(0)"><br>-->
         <input value="播放选中歌曲" type="submit"/>
       </p>
      </form>
     </div>
     
    <div class="rightWrapper"> 
     <div class="centerContainer">
       	<div class="title">华语歌曲</div>
     	<form action="playSong.action" method="post">
      	<ul>
       	  <s:iterator value="songSingerChinaMap" status="st">
    	  	<li><input type="checkbox" name="songIds"  id="<s:property value='key.id'/>" value="<s:property value='key.id'/>"><label for="<s:property value='key.id'/>" class="checkboxLabel"><s:property value="key.name"/>
       	     （<s:iterator value="value">
          	   <s:property value="name"/>
             </s:iterator>）
              </label></li>
       	  </s:iterator>
        </ul>
        <p align="center">
           <!--<input value="全选" type="button" onclick="selectAll(1)">
           <input value="反选" type="button" onclick="selectReverse(1)"><br>-->
           <input value="播放选中歌曲" type="submit"/>
        </p>
        </form>
    </div>
    
    <div class="rightContainer">
       	<div class="title">韩国歌曲</div>
     	<form action="playSong.action" method="post">
      	<ul>
       	  <s:iterator value="songSingerKoreaMap" status="st">
    	  	<li><input type="checkbox" name="songIds"  id="<s:property value='key.id'/>" value="<s:property value='key.id'/>"><label for="<s:property value='key.id'/>" class="checkboxLabel"><s:property value="key.name"/>
       	     （<s:iterator value="value">
          	   <s:property value="name"/>
             </s:iterator>）
              </label></li>
       	  </s:iterator>
        </ul>
        <p align="center">
            <!--<input value="全选" type="button" onclick="selectAll(2)">
            <input value="反选" type="button" onclick="selectReverse(2)"><br>-->
            <input value="播放选中歌曲" type="submit"/>
        </p>
        </form>
    </div>
    </div>
    <div class="seperator">
    </div>
   <div class="rightWrapper">
    <div class="centerContainer">
       <div class="title">日本歌曲</div>
     	<form action="playSong.action" method="post">
      	<ul>
       	  <s:iterator value="songSingerJapanMap" status="st">
    	  	<li><input type="checkbox" name="songIds"  id="<s:property value='key.id'/>" value="<s:property value='key.id'/>"><label for="<s:property value='key.id'/>" class="checkboxLabel"><s:property value="key.name"/>
       	     （<s:iterator value="value">
          	   <s:property value="name"/>
             </s:iterator>）
              </label></li>
       	  </s:iterator>
        </ul>
        <p align="center">
            <!--<input value="全选" type="button" onclick="selectAll(2)">
            <input value="反选" type="button" onclick="selectReverse(2)"><br>-->
            <input value="播放选种歌曲" type="submit"/>
        </p>
        </form>
    </div>
    <div class="rightContainer">
      <div class="title">欧美歌曲</div>
     	<form action="playSong.action" method="post">
      	<ul>
       	  <s:iterator value="songSingerOccidentMap" status="st">
    	  	<li><input type="checkbox" name="songIds"  id="<s:property value='key.id'/>" value="<s:property value='key.id'/>"><label for="<s:property value='key.id'/>" class="checkboxLabel"><s:property value="key.name"/>
       	     （<s:iterator value="value">
          	   <s:property value="name"/>
             </s:iterator>）
              </label></li>
       	  </s:iterator>
        </ul>
        <p align="center">
           <!--<input value="全选" type="button" onclick="selectAll(1)">
           <input value="反选" type="button" onclick="selectReverse(1)"><br>-->
           <input value="播放选种歌曲" type="submit"/>
        </p>
       </form>
    </div>
    </div>
   </div>
   </div>
  </body>
</html>

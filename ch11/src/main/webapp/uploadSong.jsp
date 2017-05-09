<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<html>
  <head>
    <title>上传歌曲</title>
    <script type="text/javascript">
       var totalSinger = 1;
       function selectSingerType(s){
         switch(s.options[s.selectedIndex].value){
          case "1":
             document.getElementById("bandNameDiv").style.display="none";
             document.getElementById("addButton").disabled = true;
             break;
         case "2":
             document.getElementById("bandNameDiv").style.display="none";
              document.getElementById("addButton").disabled = false;
             break;
         case "3":
             document.getElementById("bandNameDiv").style.display="";
              document.getElementById("addButton").disabled = false;
             break;
         }
          var innerHtml = "";
           innerHtml += '歌手姓名:<input type="text" name="singerDtos[0].name"><br>';
           innerHtml += '歌手国籍：<select name="singerDtos[0].region">';
           innerHtml += '<option value="china" selected>华人歌手</option>';
           innerHtml += '<option value="korea">韩国歌手</option>';
           innerHtml += '<option value="japan">日本歌手</option>';
           innerHtml += '<option value="occident">欧美歌手</option>';
           innerHtml += '</select><br>';
           innerHtml += '歌手性别：<select name="singerDtos[0].sex">';
           innerHtml += '<option value="m">男</option>';
           innerHtml += '<option value="w">女</option>';
           innerHtml += '</select><br>';
           document.getElementById("singers").innerHTML = innerHtml;
       }
       
       function add(){
           var innerHtml = document.getElementById("Singers").innerHTML;
           innerHtml += '歌手姓名:<input type="text" name="singerDtos[' + totalSinger + '].name"><br>';
           innerHtml += '歌手国籍：<select name="singerDtos['+ totalSinger +'].region">';
           innerHtml += '<option value="china" selected>华人歌手</option>';
           innerHtml += '<option value="korea">韩国歌手</option>';
           innerHtml += '<option value="japan">日本歌手</option>';
           innerHtml += '<option value="occident">欧美歌手</option>';
           innerHtml += '</select><br>';
           innerHtml += '歌手性别：<select name="singerDtos[' + totalSinger + '].sex">';
           innerHtml += '<option value="m">男</option>';
           innerHtml += '<option value="w">女</option>';
           innerHtml += '</select><br>';
           totalSinger++;
           document.getElementById("singers").innerHTML = innerHtml;
       }
    </script>
  </head>
  <body>
  <div align="center">
   <s:form action="uploadSong.action" enctype="multipart/form-data">
       <h3 align="center">上传歌曲</h3>
        歌曲名称：<input type="text" name="songName"><br>
        选择上传的歌曲：<input type="file" name="upload"><br> 
      	歌手类型： <select name="type" onchange="selectSingerType(this)">
          <option value="1" selected>单人歌手</option>
          <option value="2">组合歌手</option>
          <option value="3">乐队</option> 
       </select>
       <div id="bandNameDiv" style="display:none">
          乐队名称:<input type="text" name="bandName">
       </div>
       <div id="singers">
           歌手姓名：<input type="text" name="singerDtos[0].name"><br>
           歌手国籍： <select name="singerDtos[0].region">
               <option value="china" selected>华人歌手</option>
               <option value="korea">韩国歌手</option>
               <option value="japan">日本歌手</option>
               <option value="occident">欧美歌手</option>
          </select><br>
         歌手性别：<select name="singerDtos[0].sex">
               <option value="m">男</option>
               <option value="w">女</option>
          </select><br>
       </div>
       <input type="submit" value="上传">
    </s:form>
    <div id="addSinger" align="center">
       <input type="button" value="添加歌手" id="addButton" onclick="add()" disabled>
    </div>
   </div>
  </body>
</html>

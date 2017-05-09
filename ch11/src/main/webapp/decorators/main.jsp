<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<html>
  <head>
     <title><decorator:title/></title>
     <link href="main.css" media="screen" rel="Stylesheet" type="text/css"/>
     <decorator:head/>
  </head>
  <body>
    <div id="top">
       <div id="head">
          <s:if test="#session.user != null">
               欢迎${sessionScope.user.userName}光临!
          </s:if>
          <s:else>
           <form action="login.action" method="post">
           <div align="right">
               用户登录:
              帐号<input type="text" name="user.userName">
              密码:<input type="password" name="user.password"><input type="submit" value="登录"> |
            <a href="register.jsp">注册</a>
           </div>
           </form>
          </s:else>
       </div>
       <div id="title">
         <form action="searchSong.action" method="post">
             <div align="center">
                  精彩生活从今声开始&nbsp;&nbsp;&nbsp;<b>搜索:</b>
             	<input type="text" name="songName">
             	<input type="submit" value="音乐搜索">
             </div>
         </form>
       </div>
       <div id="mainMenu">
       </div>
       <div id="menu">
       	 <p align="center">
       	 	   <a href="">音乐首页 </a>|
       	 	   <a href="listSongByRegion.action?region=china">华人歌手 </a>|
       	 	   <a href="listSongByRegion.action?region=korea">韩国歌手 </a>|
       	 	   <a href="listSongByRegion.action?region=japan">日本歌手 </a>|
       	 	   <a href="listSongByRegion.action?region=occident">欧美歌手 </a>|
       	 	   <a href="uploadSong.jsp">上传歌曲</a>|
       	 </p>
       </div>
    </div>
    <decorator:body/>
     <p class="info">所有音乐搜索自互联网，出处与本站无关，档案仅作低品质试听请购买正版CD听高品质音乐，以支持你们的偶像</p>
  </body>
</html>
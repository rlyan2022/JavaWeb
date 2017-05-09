<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<html>
    <head><title>播放音乐</title>
    <script type="text/javascript">
        function p(){
            Exobud.FileName = "<s:property value="localAddr"/>" + "/upload/" + "<s:property value="songListFileName"/>";
            Exobud.AutoStart = -1;
            Exobud.play();
        }
        function play(s){
            Exobud.FileName = "<s:property value="localAddr"/>" + s.value;
            Exobud.AutoStart = -1;
            Exobud.play();
        }
    </script>
    </head>
	<body>
		<div id="player" align="center">
		  <object id="Exobud" codebase="http://activex.microsoft.com/activex/controls/mplayer/en/nsmp2inf.cab#Version=6,4,5,715" type="application/x-oleobject" height="66" 
				standby="Loading Microsoft Windows Media Player components..." width="300" classid="CLSID:22d6f312-b0f6-11d0-94ab-0080c74c7e95">
          <param name="AutoRewind" value="1">
          <param name="FileName" value="">
          <param name="ShowControls" value="1">
          <param name="loop" value="0">
          <param name="ShowPositionControls" value="0">
          <param name="ShowAudioControls" value="1">
          <param name="ShowTracker" value="1">
          <param name="ShowDisplay" value="0">
          <param name="ShowStatusBar" value="1">
          <param name="ShowGotoBar" value="0">
          <param name="ShowCaptioning" value="0">
          <param name="AutoStart" value="-1">
          <param name="Volume" value="-1">
          <param name="AnimationAtStart" value="0">
          <param name="TransparentAtStart" value="0">
          <param name="AllowChangeDisplaySize" value="0">
          <param name="AllowScan" value="0">
          <param name="EnableContextMenu" value="0">
          <param name="ClickToPlay" value="0">
       </object>
     </div>
     
     <table height="30px" width="100%">
       <tr><td></td></tr>
     </table>
    
     <div id="playList" align="center">
        <select id="songList" size="10" onchange="play(this)">
           <s:iterator value="songSingerMap" status="st">
    	  	<option value="<s:property value="key.location"/>"><s:property value="key.name"/>
       	     （<s:iterator value="value">
          	   <s:property value="name"/>
             </s:iterator>）
             </option>
       	  </s:iterator>
       </select>
     </div>
    <br>
     <p align="center"><a href="listSong.action">返回歌曲列表</a></p>
     <script type="text/javascript">
	       p();
	 </script>
	</body>
</html>
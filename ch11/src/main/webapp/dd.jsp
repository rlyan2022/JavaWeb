<%@ page language="java" contentType="text/html;charset=UTF-8"%>

<html>
    <head><title>播放音乐</title>
    </head>
<body>
<object id="Exobud" codebase="http://activex.microsoft.com/activex/controls/mplayer/en/nsmp2inf.cab#Version=6,4,5,715" type="application/x-oleobject" height="66" 
				standby="Loading Microsoft Windows Media Player components..." width="300" classid="CLSID:22d6f312-b0f6-11d0-94ab-0080c74c7e95">
          <param name="AutoRewind" value="1">
          <param name="FileName" value="http://127.0.0.1:8080/Music/upload/33_32_34_.m3u">
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
</body>
</html>
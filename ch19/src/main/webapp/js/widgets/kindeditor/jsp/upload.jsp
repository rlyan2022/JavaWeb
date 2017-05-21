<%@page import="java.net.URLDecoder"%> 
<%@ page language="java" contentType="text/html;charset=UTF-8"%> 
<%@ page import="java.util.*"%> 
<%@ page import="org.apache.commons.fileupload.*"%> 
<% 
//程序:brick 
//时间:2009-3-20 
//说明:KindEditor图片上传处理， 
//注意，此程序需要commons-fileupload.jar，请自行下载放到WEB-INF/lib目录下。 
//获取站点根路径
String ctx = request.getContextPath();
//保存图片的物理路径
String separator = java.io.File.separator;
String savePath = request.getSession().getServletContext().getRealPath(separator)+"upload"+separator+"kindeditor"+separator; 
java.io.File myFilePath = new java.io.File(savePath.toString());
if (!myFilePath.exists()) {
  myFilePath.mkdir();
}

//图片站点路径
String saveUrl = ctx+"/upload/kindeditor/";
//扩展名
String[] extArr = new String[]{".gif",".jpg",".png",".bmp"};
//最大能上传的文件大小100k
int maxSize = 102400; 

String fileId = null;
String fileWidth = null; 
String fileHeight = null; 
String fileBorder = null; 
String fileTitle = null; 

Date currentDate = new Date(); 
Random random = new Random(); 
//用时间和随机数自动生成文件名
String fileNameAuto = String.format("%X_%X",new Object[]{new Integer((int)(currentDate.getTime())),new Integer(random.nextInt())}); 
String filePath = null; 
String fileUrl = null; 
try{
	DiskFileUpload uploadFile = new DiskFileUpload(); 
	uploadFile.setSizeMax(maxSize);
	uploadFile.setSizeThreshold(4096); 
	uploadFile.setRepositoryPath("c:/"); 

	List fileItems = uploadFile.parseRequest(request); 
	Iterator iter = fileItems.iterator(); 
	while (iter.hasNext()) { 
	    FileItem item = (FileItem) iter.next(); 
	    String fieldName = item.getFieldName(); 
	    if (!item.isFormField()) { 
	        String name = item.getName(); 
	        long size = item.getSize(); 
	        if((name==null||name.equals("")) && size==0) 
	        continue; 
	        if(size>maxSize) { 
	        	out.println( "<script type=\"text/javascript\">alert(\"上传文件大小超过限制100k。\");history.back();</script>");
	            return;
	        } 
	        int pos = name.lastIndexOf("."); 
	        String ext = name.substring(pos); 
	        boolean b=false; 
	        for(int m=0;m<extArr.length; m++){ 
	            if(extArr[m].equalsIgnoreCase(ext)){ 
	                b=true; 
	                break; 
	            } 
	        } 
	        if (b==false){ 
	        	out.println( "<script type=\"text/javascript\">alert(\"上传文件的扩展名不被允许。\");history.back();</script>");
	    		return;
	        } 
	        filePath = savePath + fileNameAuto + ext; 
	        fileUrl = saveUrl + fileNameAuto + ext; 
	        java.io.File f= new java.io.File(filePath); 
	        item.write(f); 
	    } 
	    else{ 
	        String fieldValue = item.getString(); 
			if("id".equals(fieldName)){ 
	            fileId = fieldValue; 
	        } 
	        else if("imgWicurrentDateh".equals(fieldName)){ 
	            fileWidth = fieldValue; 
	        } 
	        else if("imgHeight".equals(fieldName)){ 
	            fileHeight = fieldValue; 
	        } 
	        else if("imgBorder".equals(fieldName)){ 
	            fileBorder = fieldValue; 
	        } 
	        else if("imgTitle".equals(fieldName)){ 
	            fileTitle = URLDecoder.decode(fieldValue,"UTF-8");
	        }
	    } 
	}

    out.println( "<html>"); 
    out.println( "<head>"); 
    out.println( "<title>Insert Image</title>"); 
    out.println( "<meta http-equiv=\"content-type\" content=\"text/html; charset=utf-8\">"); 
    out.println( "</head>"); 
    out.println( "<body>"); 
    out.println( "<script type=\"text/javascript\">parent.KE.plugin[\"image\"].insert(\"" + fileId + "\",\"" + fileUrl + "\",\"" + fileTitle + "\",\"" + fileWidth + "\",\"" + fileHeight + "\",\"" + fileBorder + "\");</script>");
    out.println( "</body>"); 
    out.println( "</html>"); 		
}catch(Exception e){
	out.println( "<script type=\"text/javascript\">alert(\"文件上传失败。上传文件大小不能大于100k\");history.back();</script>");
}finally{
	
}
%> 
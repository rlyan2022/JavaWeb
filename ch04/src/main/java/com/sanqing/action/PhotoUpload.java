package com.sanqing.action;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.commons.io.FilenameUtils;
import org.apache.struts2.ServletActionContext;

import java.io.*;
import java.util.Map;
import java.util.UUID;

public class PhotoUpload extends ActionSupport {
    private File myFile;
    private String myFileContentType;
    private String myFileFileName;

    public File getMyFile() {
        return myFile;
    }

    public void setMyFile(File myFile) {
        this.myFile = myFile;
    }

    public String getMyFileContentType() {
        return myFileContentType;
    }

    public void setMyFileContentType(String myFileContentType) {
        this.myFileContentType = myFileContentType;
    }

    public String getMyFileFileName() {
        return myFileFileName;
    }

    public void setMyFileFileName(String myFileFileName) {
        this.myFileFileName = myFileFileName;
    }

    public String execute() throws Exception {
        //获得username
        Map session = ServletActionContext.getContext().getSession();
        String username = (String) session.get("username");

        //创建一个输入流
        InputStream is = new FileInputStream(myFile);
        //设置文件保存目录
        String photoPath =
                ServletActionContext.getServletContext().getRealPath("/user/photo/" + username);
        File filePhotoPath = new File(photoPath);
        if (!filePhotoPath.isDirectory()) {
            filePhotoPath.mkdir();
        }

        //解决中文文件名问题
        String extension = FilenameUtils.getExtension(this.getMyFileFileName());
        String filename = UUID.randomUUID().toString() + "." + extension;

        //设置目标文件
        File tofile = new File(photoPath, filename);
        //使用输出流来包装目标文件
        OutputStream os = new FileOutputStream(tofile);
        byte[] buffer = new byte[1024];
        int length = 0;
        while ((length = is.read(buffer)) > 0) {
            os.write(buffer, 0, length);
        }
        //关闭输入流
        is.close();
        //关闭输出流
        os.close();

        return this.SUCCESS;
    }


}

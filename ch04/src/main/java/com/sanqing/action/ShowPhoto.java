package com.sanqing.action;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.Map;

public class ShowPhoto extends ActionSupport {

    public String execute() throws Exception {
        //获得username
        Map session = ServletActionContext.getContext().getSession();
        String username = (String) session.get("username");

        //设置文件目录
        String photoPath =
                ServletActionContext.getServletContext().getRealPath("/user/photo/" + username);
        //使用File封装
        File fphotoPath = new File(photoPath);
        //获得相册目录的所有图片文件
        String[] photoList = fphotoPath.list();
        //将图片列表设置到request范围
        HttpServletRequest request = ServletActionContext.getRequest();
        request.setAttribute("photoList", photoList);
        return super.execute();
    }

}

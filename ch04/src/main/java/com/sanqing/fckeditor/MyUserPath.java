package com.sanqing.fckeditor;

import net.fckeditor.requestcycle.UserPathBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class MyUserPath implements UserPathBuilder {

    public String getUserFilesPath(HttpServletRequest request) {
        //返回一个路径，这个路径就是用户的目录
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");
        return "/userfiles/" + username;
    }

}

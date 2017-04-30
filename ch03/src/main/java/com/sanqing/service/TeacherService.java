package com.sanqing.service;

public interface TeacherService {
    //判断是否为合法老师，从而决定是否允许登录
    public boolean allowLogin(String teacherID, String password);
}

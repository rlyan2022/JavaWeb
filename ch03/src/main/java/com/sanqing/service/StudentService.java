package com.sanqing.service;

import com.sanqing.po.Student;

import java.util.List;

public interface StudentService {
    //判断是否为合法学生，从而决定是否允许登录
    public boolean allowLogin(String studentID, String password);

    //获得学生信息
    public Student getStudentInfo(String studentID);

    //设置学生成绩
    public void setStudentResult(String studentID, int result);

    //根据学生姓名查找学生
    public List<Student> getStudentByName(String studentName);

    //根据班级查找学生
    public List<Student> getStudentByClass(String sclass);
}

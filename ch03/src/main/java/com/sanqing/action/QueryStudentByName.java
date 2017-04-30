package com.sanqing.action;

import com.opensymphony.xwork2.ActionSupport;
import com.sanqing.po.Student;
import com.sanqing.service.StudentService;
import com.sanqing.service.StudentServiceImpl;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class QueryStudentByName extends ActionSupport {
    private String studentName;
    private StudentService studentService = new StudentServiceImpl();

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String execute() throws Exception {
        HttpServletRequest request = ServletActionContext.getRequest();
        List<Student> students = studentService.getStudentByName(studentName);
        request.setAttribute("students", students);
        return this.SUCCESS;
    }

}

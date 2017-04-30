package com.sanqing.action;

import com.opensymphony.xwork2.ActionSupport;
import com.sanqing.po.Student;
import com.sanqing.service.StudentService;
import com.sanqing.service.StudentServiceImpl;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class QueryStudentByClass extends ActionSupport {
    private String sclass;
    private StudentService studentService = new StudentServiceImpl();

    public String getSclass() {
        return sclass;
    }

    public void setSclass(String sclass) {
        this.sclass = sclass;
    }

    public String execute() throws Exception {
        HttpServletRequest request = ServletActionContext.getRequest();
        List<Student> students = studentService.getStudentByClass(sclass);
        request.setAttribute("students", students);
        return this.SUCCESS;
    }

}

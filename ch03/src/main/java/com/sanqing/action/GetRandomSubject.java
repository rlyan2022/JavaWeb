package com.sanqing.action;

import com.opensymphony.xwork2.ActionSupport;
import com.sanqing.po.Subject;
import com.sanqing.service.SubjectService;
import com.sanqing.service.SubjectServiceImpl;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/*
 * 获得随机试题
 */
public class GetRandomSubject extends ActionSupport {
    private SubjectService subjectService = new SubjectServiceImpl();

    public String execute() throws Exception {
        List<Subject> subjects = subjectService.randomFindSubject(20);//获得试题记录
        HttpServletRequest request = ServletActionContext.getRequest();
        request.setAttribute("subjects", subjects);
        return SUCCESS;
    }
}

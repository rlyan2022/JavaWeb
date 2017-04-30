package com.sanqing.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.sanqing.po.Subject;
import com.sanqing.service.SubjectService;
import com.sanqing.service.SubjectServiceImpl;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/*
 * 显示试题答案
 */
public class ShowSubjectAnswer extends ActionSupport {
    private SubjectService subjectService = new SubjectServiceImpl();

    public String execute() throws Exception {
        List<Subject> subjects = new ArrayList<Subject>();//保存学生考过的题目
        HttpServletRequest request = ServletActionContext.getRequest();
        Map session = ActionContext.getContext().getSession();
        List<Integer> subjectIDs = (List<Integer>) session.get("subjectIDs");
        for (Integer subjectID : subjectIDs) {
            Subject subject = subjectService.showSubjectParticular(subjectID);//通过试题编号查找试题
            subjects.add(subject);
        }
        request.setAttribute("subjects", subjects);
        return SUCCESS;
    }
}

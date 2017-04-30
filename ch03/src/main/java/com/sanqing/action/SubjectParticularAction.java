package com.sanqing.action;

import com.opensymphony.xwork2.ActionSupport;
import com.sanqing.po.Subject;
import com.sanqing.service.SubjectService;
import com.sanqing.service.SubjectServiceImpl;
import org.apache.struts2.ServletActionContext;

/*
 * 查看试题详细信息
 */
public class SubjectParticularAction extends ActionSupport {
    private int subjectID;
    private SubjectService subjectService = new SubjectServiceImpl();

    public int getSubjectID() {
        return subjectID;
    }

    public void setSubjectID(int subjectID) {
        this.subjectID = subjectID;
    }

    public String execute() throws Exception {
        Subject subject = subjectService.showSubjectParticular(subjectID);
        ServletActionContext.getRequest().setAttribute("subject", subject);
        return SUCCESS;
    }
}

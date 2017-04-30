package com.sanqing.action;

import com.opensymphony.xwork2.ActionSupport;
import com.sanqing.po.Subject;
import com.sanqing.service.SubjectService;
import com.sanqing.service.SubjectServiceImpl;
import com.sanqing.util.Page;
import com.sanqing.util.PageResult;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class QuerySubjectAction extends ActionSupport {
    private int currentPage;        //当前页
    private SubjectService subjectService = new SubjectServiceImpl();

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public String execute() throws Exception {
        Page page = new Page();
        page.setEveryPage(10);//每页显示10条记录
        page.setCurrentPage(currentPage);//设置当前页
        PageResult pageResult = subjectService.querySubjectByPage(page);
        List<Subject> subjects = pageResult.getList();//获得试题记录
        page = pageResult.getPage();//获得分页信息
        HttpServletRequest request = ServletActionContext.getRequest();
        request.setAttribute("subjects", subjects);
        request.setAttribute("page", page);
        return SUCCESS;
    }
}

package com.action;

import com.actionForm.BookCaseForm;
import com.dao.BookCaseDAO;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BookCase extends Action {
    private BookCaseDAO bookCaseDAO = null;

    public BookCase() {
        this.bookCaseDAO = new BookCaseDAO();
    }

    public ActionForward execute(ActionMapping mapping, ActionForm form,
                                 HttpServletRequest request,
                                 HttpServletResponse response) {
        String action = request.getParameter("action");
        System.out.println("\nbookCase*********************action=" + action);
        if (action == null || "".equals(action)) {
            return mapping.findForward("error");
        } else if ("bookCaseAdd".equals(action)) {
            return bookCaseAdd(mapping, form, request, response);
        } else if ("bookCaseQuery".equals(action)) {
            return bookCaseQuery(mapping, form, request, response);
        } else if ("bookCaseModifyQuery".equals(action)) {
            return bookCaseModifyQuery(mapping, form, request, response);
        } else if ("bookCaseModify".equals(action)) {
            return bookCaseModify(mapping, form, request, response);
        } else if ("bookCaseDel".equals(action)) {
            return bookCaseDel(mapping, form, request, response);
        }
        request.setAttribute("error", "操作失败！");
        return mapping.findForward("error");
    }

    /***********************添加书架信息**************************/
    private ActionForward bookCaseAdd(ActionMapping mapping, ActionForm form,
                                      HttpServletRequest request,
                                      HttpServletResponse response) {
        BookCaseForm bookCaseForm = (BookCaseForm) form;
        System.out.println("servlet:" + request.getParameter("name"));
        bookCaseForm.setName(bookCaseForm.getName());
        int a = bookCaseDAO.insert(bookCaseForm);
        if (a == 0) {
            request.setAttribute("error", "书架信息添加失败！");
            return mapping.findForward("error");
        } else if (a == 2) {
            request.setAttribute("error", "该书架信息已经添加！");
            return mapping.findForward("error");
        } else {
            return mapping.findForward("bookcaseAdd");
        }
    }

    /***********************查询全部书架信息**************************/
    private ActionForward bookCaseQuery(ActionMapping mapping, ActionForm form,
                                        HttpServletRequest request,
                                        HttpServletResponse response) {
        String str = null;
        request.setAttribute("bookcase", bookCaseDAO.query(str));
        return mapping.findForward("bookcaseQuery");
    }

    /***********************查询修改书架信息**************************/
    private ActionForward bookCaseModifyQuery(ActionMapping mapping, ActionForm form,
                                              HttpServletRequest request,
                                              HttpServletResponse response) {
        BookCaseForm bookCaseForm = (BookCaseForm) form;
        bookCaseForm.setId(Integer.valueOf(request.getParameter("ID")));
        request.setAttribute("bookCaseQueryif", bookCaseDAO.queryM(bookCaseForm));
        return mapping.findForward("bookCaseQueryModify");
    }

    /***********************修改书架信息**************************/
    private ActionForward bookCaseModify(ActionMapping mapping, ActionForm form,
                                         HttpServletRequest request,
                                         HttpServletResponse response) {
        BookCaseForm bookCaseForm = (BookCaseForm) form;
        bookCaseForm.setName(request.getParameter("name"));
        int ret = bookCaseDAO.update(bookCaseForm);
        if (ret == 0) {
            request.setAttribute("error", "修改书架信息失败！");
            return mapping.findForward("error");
        } else {
            return mapping.findForward("bookCaseModify");
        }
    }

    /***********************删除书架信息**************************/
    private ActionForward bookCaseDel(ActionMapping mapping, ActionForm form,
                                      HttpServletRequest request,
                                      HttpServletResponse response
    ) {
        BookCaseForm bookCaseForm = (BookCaseForm) form;
        bookCaseForm.setId(Integer.valueOf(request.getParameter("ID")));
        int ret = bookCaseDAO.delete(bookCaseForm);
        if (ret == 0) {
            request.setAttribute("error", "删除书架信息失败！");
            return mapping.findForward("error");
        } else {
            return mapping.findForward("bookCaseDel");
        }
    }
}

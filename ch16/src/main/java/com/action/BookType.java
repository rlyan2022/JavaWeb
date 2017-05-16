package com.action;

import com.actionForm.BookTypeForm;
import com.dao.BookTypeDAO;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*图书类别信息处理的控制层action*/
public class BookType extends Action {
    private BookTypeDAO bookTypeDAO = null;

    public BookType() {
        this.bookTypeDAO = new BookTypeDAO();
    }

    public ActionForward execute(ActionMapping mapping, ActionForm form,
                                 HttpServletRequest request,
                                 HttpServletResponse response) {

        String action = request.getParameter("action");/*取得图书类别操作的类型*/
        System.out.println("\nbookType*********************action=" + action);
        if (action == null || "".equals(action)) {
            request.setAttribute("error", "您的操作有误！");
            return mapping.findForward("error");
        } else if ("bookTypeAdd".equals(action)) { /*图书类别添加*/
            return bookTypeAdd(mapping, form, request, response);
        } else if ("bookTypeQuery".equals(action)) {
            return bookTypeQuery(mapping, form, request, response);
        } else if ("bookTypeModifyQuery".equals(action)) {
            return bookTypeModifyQuery(mapping, form, request, response);
        } else if ("bookTypeModify".equals(action)) {
            return bookTypeModify(mapping, form, request, response);
        } else if ("bookTypeDel".equals(action)) {
            return bookTypeDel(mapping, form, request, response);
        }
        request.setAttribute("error", "操作失败！");
        return mapping.findForward("error");
    }

    /***********************添加图书类型信息**************************/
    private ActionForward bookTypeAdd(ActionMapping mapping, ActionForm form,
                                      HttpServletRequest request,
                                      HttpServletResponse response) {
        BookTypeForm bookTypeForm = (BookTypeForm) form;
        System.out.println("servlet:" + bookTypeForm.getTypeName());
        bookTypeForm.setTypeName(bookTypeForm.getTypeName());
        int a = bookTypeDAO.insert(bookTypeForm);
        if (a == 0) {
            request.setAttribute("error", "图书类型信息添加失败！");
            return mapping.findForward("error");
        } else if (a == 2) {
            request.setAttribute("error", "该图书类型信息已经添加！");
            return mapping.findForward("error");
        } else {
            return mapping.findForward("bookTypeAdd");
        }
    }

    /***********************查询全部图书类型信息**************************/
    private ActionForward bookTypeQuery(ActionMapping mapping, ActionForm form,
                                        HttpServletRequest request,
                                        HttpServletResponse response) {
        String str = null;
        request.setAttribute("bookType", bookTypeDAO.query(str));
        return mapping.findForward("bookTypeQuery");
    }

    /***********************查询修改图书类型信息**************************/
    private ActionForward bookTypeModifyQuery(ActionMapping mapping, ActionForm form,
                                              HttpServletRequest request,
                                              HttpServletResponse response) {
        BookTypeForm bookTypeForm = (BookTypeForm) form;
        bookTypeForm.setId(Integer.valueOf(request.getParameter("ID")));
        request.setAttribute("bookTypeQueryif", bookTypeDAO.queryM(bookTypeForm));
        return mapping.findForward("bookTypeQueryModify");
    }

    /***********************修改图书类型信息**************************/
    private ActionForward bookTypeModify(ActionMapping mapping, ActionForm form,
                                         HttpServletRequest request,
                                         HttpServletResponse response) {
        BookTypeForm bookTypeForm = (BookTypeForm) form;
        bookTypeForm.setTypeName(bookTypeForm.getTypeName());
        bookTypeForm.setDays(bookTypeForm.getDays());
        int ret = bookTypeDAO.update(bookTypeForm);
        if (ret == 0) {
            request.setAttribute("error", "修改图书类型信息失败！");
            return mapping.findForward("error");
        } else {
            return mapping.findForward("bookTypeModify");
        }
    }

    /***********************删除图书类型信息**************************/
    private ActionForward bookTypeDel(ActionMapping mapping, ActionForm form,
                                      HttpServletRequest request,
                                      HttpServletResponse response) {
        BookTypeForm bookTypeForm = (BookTypeForm) form;
        bookTypeForm.setId(Integer.valueOf(request.getParameter("ID")));
        int ret = bookTypeDAO.delete(bookTypeForm);
        if (ret == 0) {
            request.setAttribute("error", "删除图书类型信息失败！");
            return mapping.findForward("error");
        } else {
            return mapping.findForward("bookTypeDel");
        }
    }
}

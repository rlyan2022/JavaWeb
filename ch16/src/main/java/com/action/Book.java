package com.action;

import com.actionForm.BookForm;
import com.dao.BookDAO;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

public class Book extends Action {
    private BookDAO bookDAO = null;

    public Book() {
        this.bookDAO = new BookDAO();
    }

    public ActionForward execute(ActionMapping mapping, ActionForm form,
                                 HttpServletRequest request,
                                 HttpServletResponse response) {
        String action = request.getParameter("action");
        System.out.println("\nbook*********************action=" + action);
        if (action == null || "".equals(action)) {
            request.setAttribute("error", "您的操作有误！");
            return mapping.findForward("error");
        } else if ("bookAdd".equals(action)) {
            return bookAdd(mapping, form, request, response);
        } else if ("bookQuery".equals(action)) {
            return bookQuery(mapping, form, request, response);
        } else if ("bookModifyQuery".equals(action)) {
            return bookModifyQuery(mapping, form, request, response);
        } else if ("bookModify".equals(action)) {
            return bookModify(mapping, form, request, response);
        } else if ("bookDel".equals(action)) {
            return bookDel(mapping, form, request, response);
        } else if ("bookDetail".equals(action)) {
            return bookDetail(mapping, form, request, response);
        } else if ("bookifQuery".equals(action)) {
            return bookifQuery(mapping, form, request, response);
        }
        request.setAttribute("error", "操作失败！");
        return mapping.findForward("error");
    }

    /***********************添加图书信息**************************/
    private ActionForward bookAdd(ActionMapping mapping, ActionForm form,
                                  HttpServletRequest request,
                                  HttpServletResponse response) {
        BookForm bookForm = (BookForm) form;
        bookForm.setBarcode(bookForm.getBarcode());
        bookForm.setBookName(bookForm.getBookName());
        bookForm.setTypeId(bookForm.getTypeId());
        bookForm.setAuthor(bookForm.getAuthor());
        bookForm.setTranslator(bookForm.getTranslator());
        bookForm.setIsbn(bookForm.getIsbn());
        bookForm.setPrice(bookForm.getPrice());
        bookForm.setPage(bookForm.getPage());
        bookForm.setBookcaseid(bookForm.getBookcaseid());
        //获取系统日期
        Date date1 = new Date();
        java.sql.Date date = new java.sql.Date(date1.getTime());
        bookForm.setInTime(date.toString());
        bookForm.setOperator(bookForm.getOperator());
        int a = bookDAO.insert(bookForm);
        if (a == 1) {
            return mapping.findForward("bookAdd");
        } else if (a == 2) {
            request.setAttribute("error", "该图书信息已经添加！");
            return mapping.findForward("error");
        } else {
            request.setAttribute("error", "图书信息添加失败！");
            return mapping.findForward("error");
        }
    }

    /***********************查询全部图书信息**************************/
    private ActionForward bookQuery(ActionMapping mapping, ActionForm form,
                                    HttpServletRequest request,
                                    HttpServletResponse response) {
        String str = null;
        request.setAttribute("book", bookDAO.query(str));    //将查询结果保存到book中
        return mapping.findForward("bookQuery");        //转到显示图书信息列表页面
    }

    /***********************条件查询图书信息**************************/
    private ActionForward bookifQuery(ActionMapping mapping, ActionForm form,
                                      HttpServletRequest request,
                                      HttpServletResponse response) {
        String str = null;
        if (request.getParameter("f") != null) {
            str = request.getParameter("f") + " like '%" +
                    request.getParameter("key") + "%";
        }
        request.setAttribute("ifbook", bookDAO.query(str));
        System.out.print("条件查询图书信息时的str:" + str);
        return mapping.findForward("bookifQuery");
    }

    /***********************查询修改图书信息**************************/
    private ActionForward bookModifyQuery(ActionMapping mapping, ActionForm form,
                                          HttpServletRequest request,
                                          HttpServletResponse response) {
        BookForm bookForm = (BookForm) form;
        System.out.println("查询修改图书信息：" + request.getParameter("ID"));
        bookForm.setId(Integer.valueOf(request.getParameter("ID")));
        request.setAttribute("bookQueryif", bookDAO.queryM(bookForm));
        return mapping.findForward("bookQueryModify");
    }

    /***********************查询图书详细信息**************************/
    private ActionForward bookDetail(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request,
                                     HttpServletResponse response) {
        BookForm bookForm = (BookForm) form;
        bookForm.setId(Integer.valueOf(request.getParameter("ID")));
        request.setAttribute("bookDetail", bookDAO.queryM(bookForm));
        return mapping.findForward("bookDeatil");
    }

    /***********************修改图书信息**************************/
    private ActionForward bookModify(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request,
                                     HttpServletResponse response) {
        BookForm bookForm = (BookForm) form;        //实例化BookForm类
        bookForm.setBarcode(bookForm.getBarcode());    //获取并设置条形码属性
        bookForm.setBookName(bookForm.getBookName());
        bookForm.setTypeId(bookForm.getTypeId());
        bookForm.setAuthor(bookForm.getAuthor());
        bookForm.setTranslator(bookForm.getTranslator());
        bookForm.setIsbn(bookForm.getIsbn());
        bookForm.setPrice(bookForm.getPrice());
        bookForm.setPage(bookForm.getPage());
        bookForm.setBookcaseid(bookForm.getBookcaseid());
        bookForm.setInTime(bookForm.getInTime());
        bookForm.setOperator(bookForm.getOperator());
        int ret = bookDAO.update(bookForm);            //调用修改图书信息的方法update()
        if (ret == 0) {
            request.setAttribute("error", "修改图书信息失败！");
            return mapping.findForward("error");        //转到错误提示页面
        } else {
            return mapping.findForward("bookModify");    //转到修改成功页面
        }
    }

    /***********************删除图书信息**************************/
    private ActionForward bookDel(ActionMapping mapping, ActionForm form,
                                  HttpServletRequest request,
                                  HttpServletResponse response) {
        BookForm bookForm = (BookForm) form;
        bookForm.setId(Integer.valueOf(request.getParameter("ID")));
        int ret = bookDAO.delete(bookForm);
        if (ret == 0) {
            request.setAttribute("error", "删除图书信息失败！");
            return mapping.findForward("error");
        } else {
            return mapping.findForward("bookDel");
        }
    }
}

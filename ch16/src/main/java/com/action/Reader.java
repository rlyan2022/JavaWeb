package com.action;

import com.actionForm.ReaderForm;
import com.dao.ReaderDAO;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

public class Reader extends Action {
    private ReaderDAO readerDAO = null;

    public Reader() {
        this.readerDAO = new ReaderDAO();
    }

    public ActionForward execute(ActionMapping mapping, ActionForm form,
                                 HttpServletRequest request,
                                 HttpServletResponse response) {
        String action = request.getParameter("action");
        System.out.println("\nreader*********************action=" + action);
        if (action == null || "".equals(action)) {
            request.setAttribute("error", "您的操作有误！");
            return mapping.findForward("error");
        } else if ("readerAdd".equals(action)) {
            return readerAdd(mapping, form, request, response);
        } else if ("readerQuery".equals(action)) {
            return readerQuery(mapping, form, request, response);
        } else if ("readerModifyQuery".equals(action)) {
            return readerModifyQuery(mapping, form, request, response);
        } else if ("readerModify".equals(action)) {
            return readerModify(mapping, form, request, response);
        } else if ("readerDel".equals(action)) {
            return readerDel(mapping, form, request, response);
        } else if ("readerDetail".equals(action)) {
            return readerDetail(mapping, form, request, response);
        }
        request.setAttribute("error", "操作失败！");
        return mapping.findForward("error");
    }

    /***********************添加读者信息**************************/
    private ActionForward readerAdd(ActionMapping mapping, ActionForm form,
                                    HttpServletRequest request,
                                    HttpServletResponse response) {
        ReaderForm readerForm = (ReaderForm) form;
        readerForm.setName(readerForm.getName());
        readerForm.setSex(readerForm.getSex());
        readerForm.setBarcode(readerForm.getBarcode());
        readerForm.setVocation(readerForm.getVocation());
        readerForm.setBirthday(readerForm.getBirthday());
        readerForm.setPaperType(readerForm.getPaperType());
        readerForm.setPaperNO(readerForm.getPaperNO());
        readerForm.setTel(readerForm.getTel());
        readerForm.setEmail(readerForm.getEmail());
        //获取系统日期
        Date date1 = new Date();
        java.sql.Date date = new java.sql.Date(date1.getTime());
        readerForm.setCreateDate(date.toString());
        readerForm.setOperator(readerForm.getOperator());
        readerForm.setRemark(readerForm.getRemark());
        readerForm.setTypeid(readerForm.getTypeid());
        int a = readerDAO.insert(readerForm);
        if (a == 0) {
            request.setAttribute("error", "读者信息添加失败！");
            return mapping.findForward("error");
        } else if (a == 2) {
            request.setAttribute("error", "该读者信息已经添加！");
            return mapping.findForward("error");
        } else {
            return mapping.findForward("readerAdd");
        }
    }

    /***********************查询全部读者信息**************************/
    private ActionForward readerQuery(ActionMapping mapping, ActionForm form,
                                      HttpServletRequest request,
                                      HttpServletResponse response) {
        String str = null;
        request.setAttribute("reader", readerDAO.query(str));
        return mapping.findForward("readerQuery");
    }

    /***********************查询修改读者信息**************************/
    private ActionForward readerModifyQuery(ActionMapping mapping, ActionForm form,
                                            HttpServletRequest request,
                                            HttpServletResponse response) {
        ReaderForm readerForm = (ReaderForm) form;
        System.out.println("查询修改读者信息：" + request.getParameter("ID"));
        readerForm.setId(Integer.valueOf(request.getParameter("ID")));
        request.setAttribute("readerQueryif", readerDAO.queryM(readerForm));
        return mapping.findForward("readerQueryModify");
    }

    /***********************查询读者详细信息**************************/
    private ActionForward readerDetail(ActionMapping mapping, ActionForm form,
                                       HttpServletRequest request,
                                       HttpServletResponse response) {
        ReaderForm readerForm = (ReaderForm) form;
        readerForm.setId(Integer.valueOf(request.getParameter("ID")));
        request.setAttribute("readerDetail", readerDAO.queryM(readerForm));
        return mapping.findForward("readerDeatil");
    }

    /***********************修改读者信息**************************/
    private ActionForward readerModify(ActionMapping mapping, ActionForm form,
                                       HttpServletRequest request,
                                       HttpServletResponse response) {
        ReaderForm readerForm = (ReaderForm) form;
        readerForm.setName(readerForm.getName());
        readerForm.setSex(readerForm.getSex());
        readerForm.setBarcode(readerForm.getBarcode());
        readerForm.setVocation(readerForm.getVocation());
        readerForm.setBirthday(readerForm.getBirthday());
        readerForm.setPaperType(readerForm.getPaperType());
        readerForm.setPaperNO(readerForm.getPaperNO());
        readerForm.setTel(readerForm.getTel());
        readerForm.setEmail(readerForm.getEmail());
        readerForm.setOperator(readerForm.getOperator());
        readerForm.setRemark(readerForm.getRemark());
        readerForm.setTypeid(readerForm.getTypeid());
        int ret = readerDAO.update(readerForm);
        if (ret == 0) {
            request.setAttribute("error", "修改读者信息失败！");
            return mapping.findForward("error");
        } else {
            return mapping.findForward("readerModify");
        }
    }

    /***********************删除读者信息**************************/
    private ActionForward readerDel(ActionMapping mapping, ActionForm form,
                                    HttpServletRequest request,
                                    HttpServletResponse response) {
        ReaderForm readerForm = (ReaderForm) form;
        readerForm.setId(Integer.valueOf(request.getParameter("ID")));
        int ret = readerDAO.delete(readerForm);
        if (ret == 0) {
            request.setAttribute("error", "删除读者信息失败！");
            return mapping.findForward("error");
        } else {
            return mapping.findForward("readerDel");
        }
    }
}

package com.action;

import com.actionForm.ReaderTypeForm;
import com.dao.ReaderTypeDAO;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ReaderType extends Action {
    private ReaderTypeDAO readerTypeDAO = null;

    public ReaderType() {
        this.readerTypeDAO = new ReaderTypeDAO();
    }

    public ActionForward execute(ActionMapping mapping, ActionForm form,
                                 HttpServletRequest request,
                                 HttpServletResponse response) {
        String action = request.getParameter("action");
        System.out.println("\nreaderType*********************action=" + action);
        if (action == null || "".equals(action)) {
            request.setAttribute("error", "您的操作有误！");
            return mapping.findForward("error");
        } else if ("readerTypeAdd".equals(action)) {
            return readerTypeAdd(mapping, form, request, response);
        } else if ("readerTypeQuery".equals(action)) {
            return readerTypeQuery(mapping, form, request, response);
        } else if ("readerTypeModifyQuery".equals(action)) {
            return readerTypeModifyQuery(mapping, form, request, response);
        } else if ("readerTypeModify".equals(action)) {
            return readerTypeModify(mapping, form, request, response);
        } else if ("readerTypeDel".equals(action)) {
            return readerTypeDel(mapping, form, request, response);
        }
        request.setAttribute("error", "操作失败！");
        return mapping.findForward("error");
    }

    /***********************添加读者类型信息**************************/
    private ActionForward readerTypeAdd(ActionMapping mapping, ActionForm form,
                                        HttpServletRequest request,
                                        HttpServletResponse response) {
        ReaderTypeForm readerTypeForm = (ReaderTypeForm) form;
        System.out.println("servlet:" + request.getParameter("name"));
        readerTypeForm.setName(readerTypeForm.getName());
        int a = readerTypeDAO.insert(readerTypeForm);
        if (a == 0) {
            request.setAttribute("error", "读者类型信息添加失败！");
            return mapping.findForward("error");
        } else if (a == 2) {
            request.setAttribute("error", "该读者类型信息已经添加！");
            return mapping.findForward("error");
        } else {
            return mapping.findForward("readerTypeAdd");
        }
    }

    /***********************查询全部读者类型信息**************************/
    private ActionForward readerTypeQuery(ActionMapping mapping, ActionForm form,
                                          HttpServletRequest request,
                                          HttpServletResponse response) {
        String str = null;
        request.setAttribute("readerType", readerTypeDAO.query(str));
        return mapping.findForward("readerTypeQuery");
    }

    /***********************查询修改读者类型信息**************************/
    private ActionForward readerTypeModifyQuery(ActionMapping mapping, ActionForm form,
                                                HttpServletRequest request,
                                                HttpServletResponse response) {
        ReaderTypeForm readerTypeForm = (ReaderTypeForm) form;
        readerTypeForm.setId(Integer.valueOf(request.getParameter("ID")));
        request.setAttribute("readerTypeQueryif", readerTypeDAO.queryM(readerTypeForm));
        return mapping.findForward("readerTypeQueryModify");
    }

    /***********************修改读者类型信息**************************/
    private ActionForward readerTypeModify(ActionMapping mapping, ActionForm form,
                                           HttpServletRequest request,
                                           HttpServletResponse response) {
        ReaderTypeForm readerTypeForm = (ReaderTypeForm) form;
        readerTypeForm.setName(readerTypeForm.getName());
        readerTypeForm.setNumber(readerTypeForm.getNumber());
        int ret = readerTypeDAO.update(readerTypeForm);
        if (ret == 0) {
            request.setAttribute("error", "修改读者类型信息失败！");
            return mapping.findForward("error");
        } else {
            return mapping.findForward("readerTypeModify");
        }
    }

    /***********************删除读者类型信息**************************/
    private ActionForward readerTypeDel(ActionMapping mapping, ActionForm form,
                                        HttpServletRequest request,
                                        HttpServletResponse response) {
        ReaderTypeForm readerTypeForm = (ReaderTypeForm) form;
        readerTypeForm.setId(Integer.valueOf(request.getParameter("ID")));
        int ret = readerTypeDAO.delete(readerTypeForm);
        if (ret == 0) {
            request.setAttribute("error", "删除读者类型信息失败！");
            return mapping.findForward("error");
        } else {
            return mapping.findForward("readerTypeDel");
        }
    }
}

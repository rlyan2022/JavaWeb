package com.action;

import com.actionForm.ParameterForm;
import com.dao.ParameterDAO;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Parameter extends Action {
    ParameterDAO parameterDAO = null;

    public Parameter() {
        parameterDAO = new ParameterDAO();
    }

    public ActionForward execute(ActionMapping mapping, ActionForm form,
                                 HttpServletRequest request,
                                 HttpServletResponse response) {
        ParameterForm parameterForm = (ParameterForm) form;
        String str = request.getParameter("action");
        if ("parameterQuery".equals(str)) {
            return parameterModifyQuery(mapping, form, request, response);
        } else if ("parameterModify".equals(str)) {
            return parameterModify(mapping, form, request, response);
        }
        request.setAttribute("error", "您的操作有误！");
        return mapping.findForward("error");
    }

    private ActionForward parameterModify(ActionMapping mapping, ActionForm form,
                                          HttpServletRequest request,
                                          HttpServletResponse response
    ) {
        ParameterForm parameterForm = (ParameterForm) form;
        parameterForm.setId(parameterForm.getId());
        parameterForm.setCost(parameterForm.getCost());
        parameterForm.setValidity(parameterForm.getValidity());
        int ret = parameterDAO.update(parameterForm);
        if (ret == 0) {
            request.setAttribute("error", "参数设置信息修改失败！");
            return mapping.findForward("error");
        } else {
            return mapping.findForward("parametermodify");
        }

    }

    private ActionForward parameterModifyQuery(ActionMapping mapping, ActionForm form,
                                               HttpServletRequest request,
                                               HttpServletResponse response
    ) {
        request.setAttribute("parameterModifyif", parameterDAO.query());
        return mapping.findForward("parametermodifyQuery");
    }
}

package com.demo.struts.actions;

import com.demo.hibernate.beans.Sms;
import com.demo.hibernate.dao.SmsDAO;
import com.demo.struts.forms.PageForm;
import com.demo.struts.forms.SmsForm;
import com.demo.struts.util.Constants;
import com.demo.struts.util.Pager;
import org.apache.log4j.Logger;
import org.apache.struts.action.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SmsAction extends BaseAction {

    protected SmsDAO smsDAO;

    Logger log = Logger.getLogger(this.getClass());

    public SmsDAO getSmsDAO() {
        return smsDAO;
    }

    public void setSmsDAO(SmsDAO smsDAO) {
        this.smsDAO = smsDAO;
    }

    public ActionForward list(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String username = getUsername(request);

        PageForm pageForm = (PageForm) form;

        // get pager
        Pager pager = smsDAO.findPagerByUsername(username, pageForm
                .getPageSize(), pageForm.getPageNo());

        // set smsList
        request.setAttribute("smsList", pager.getResultList());

        // set pager
        request.setAttribute("pager", pager);

        // save session
        setSession(request, Constants.PAGER_ADDRESS, pageForm);

        ActionForward forward = mapping.findForward(Constants.LIST_KEY);
        return (forward);
    }

    public ActionForward back(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String username = getUsername(request);

        PageForm pageForm = (PageForm) getSession(request, Constants.PAGER_ADDRESS);

        // get pager
        Pager pager = smsDAO.findPagerByUsername(username, pageForm
                .getPageSize(), pageForm.getPageNo());

        // set smsList
        request.setAttribute("smsList", pager.getResultList());

        // set pager
        request.setAttribute("pager", pager);

        // save session
        setSession(request, Constants.PAGER_ADDRESS, pageForm);

        ActionForward forward = mapping.findForward(Constants.LIST_KEY);
        return (forward);
    }

    public ActionForward add(ActionMapping mapping, ActionForm form,
                             HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        ActionForward forward = mapping.findForward(Constants.ADD_KEY);
        return (forward);
    }

    public ActionForward insert(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        ActionMessages messages = new ActionMessages();
        SmsForm smsForm = (SmsForm) form;
        String username = getUsername(request);

        // insert object
        Sms sms = new Sms();
        sms.setUsername(smsForm.getUsername());
        sms.setSender(username);
        sms.setMessage(smsForm.getMessage());
        sms.setSendtime(smsForm.getSendtime());
        sms.setIsread("0");
        smsDAO.insert(sms);

        // save messages
        messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
                "sms.message.add.success"));
        saveErrors(request, messages);

        // get pageform from session
        PageForm pageForm = (PageForm) getSession(request, Constants.PAGER_ADDRESS);

        // get pager form list page
        Pager pager = smsDAO.findPagerByUsername(username, pageForm
                .getPageSize(), pageForm.getPageNo());
        request.setAttribute("smsList", pager.getResultList());
        request.setAttribute("pager", pager);

        return mapping.findForward(Constants.LIST_KEY);
    }

    public ActionForward read(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        ActionMessages messages = new ActionMessages();
        String id = request.getParameter("id");

        if (id == null) {
            // id not exist, save messages
            messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
                    "sms.message.edit.notexist"));
            saveErrors(request, messages);
            return mapping.findForward(Constants.LIST_KEY);
        } else {
            // get object
            Sms sms = smsDAO.findById(id);
            if (sms == null) {
                messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
                        "sms.message.edit.notexist"));
                saveErrors(request, messages);
                return mapping.findForward(Constants.LIST_KEY);
            }

            // set isread
            sms.setIsread("1");
            smsDAO.update(sms);

            String username = getUsername(request);

            // get pageForm from session
            PageForm pageForm = (PageForm) getSession(request, Constants.PAGER_ADDRESS);

            // get pager form list page
            Pager pager = smsDAO.findPagerByUsername(username, pageForm
                    .getPageSize(), pageForm.getPageNo());
            request.setAttribute("smsList", pager.getResultList());
            request.setAttribute("pager", pager);
        }
        return mapping.findForward(Constants.LIST_KEY);
    }

    public ActionForward delete(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        ActionMessages messages = new ActionMessages();

        String id = request.getParameter("id");
        if (id == null) {
            // if id not exist
            messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
                    "sms.message.edit.notexist"));
        } else {
            // delete object
            smsDAO.delete(id);

            // save messages
            messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
                    "sms.message.delete.success"));

            String username = getUsername(request);

            // get pageForm from session
            PageForm pageForm = (PageForm) getSession(request, Constants.PAGER_ADDRESS);

            // get pager form list page
            Pager pager = smsDAO.findPagerByUsername(username, pageForm
                    .getPageSize(), pageForm.getPageNo());
            request.setAttribute("smsList", pager.getResultList());
            request.setAttribute("pager", pager);
        }
        saveErrors(request, messages);
        return mapping.findForward(Constants.LIST_KEY);
    }
}

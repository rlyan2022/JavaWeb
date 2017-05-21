package com.demo.struts.actions;

import com.demo.hibernate.beans.Notice;
import com.demo.hibernate.dao.NoticeDAO;
import com.demo.struts.forms.NoticeForm;
import com.demo.struts.forms.PageForm;
import com.demo.struts.util.Constants;
import com.demo.struts.util.Pager;
import org.apache.log4j.Logger;
import org.apache.struts.action.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class NoticeAction extends BaseAction {

    protected NoticeDAO noticeDAO;

    Logger log = Logger.getLogger(this.getClass());

    public NoticeDAO getNoticeDAO() {
        return noticeDAO;
    }

    public void setNoticeDAO(NoticeDAO noticeDAO) {
        this.noticeDAO = noticeDAO;
    }

    public ActionForward list(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        PageForm pageForm = (PageForm) form;

        // get pager
        Pager pager = noticeDAO.findPager(pageForm.getPageSize(), pageForm
                .getPageNo());

        // set noticeList
        request.setAttribute("noticeList", pager.getResultList());

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
        PageForm pageForm = (PageForm) getSession(request,
                Constants.PAGER_ADDRESS);

        // get pager
        Pager pager = noticeDAO.findPager(pageForm.getPageSize(), pageForm
                .getPageNo());

        // set noticeList
        request.setAttribute("noticeList", pager.getResultList());

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
        NoticeForm noticeForm = (NoticeForm) form;
        String username = getUsername(request);

        // insert object
        Notice notice = new Notice();
        notice.setSender(username);
        notice.setTitle(noticeForm.getTitle());
        notice.setContent(noticeForm.getContent());
        notice.setSendtime(noticeForm.getSendtime());
        noticeDAO.insert(notice);

        // save messages
        messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
                "notice.message.add.success"));
        saveErrors(request, messages);

        // get pageform from session
        PageForm pageForm = (PageForm) getSession(request,
                Constants.PAGER_ADDRESS);

        // get pager form list page
        Pager pager = noticeDAO.findPager(pageForm.getPageSize(), pageForm
                .getPageNo());
        request.setAttribute("noticeList", pager.getResultList());
        request.setAttribute("pager", pager);

        return mapping.findForward(Constants.LIST_KEY);
    }

    public ActionForward edit(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        ActionMessages messages = new ActionMessages();
        String id = request.getParameter("id");

        if (id == null) {
            // id not exist, save messages
            messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
                    "notice.message.edit.notexist"));
            saveErrors(request, messages);
            return mapping.findForward(Constants.LIST_KEY);
        } else {
            // get object
            Notice notice = noticeDAO.findById(id);
            if (notice == null) {
                messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
                        "notice.message.edit.notexist"));
                saveErrors(request, messages);
                return mapping.findForward(Constants.LIST_KEY);
            }

            // save form object
            NoticeForm noticeForm = new NoticeForm();
            noticeForm.setId(notice.getId().toString());
            noticeForm.setSender(notice.getSender());
            noticeForm.setTitle(notice.getTitle());
            noticeForm.setContent(notice.getContent());
            noticeForm.setSendtime(notice.getSendtime());

            // save in request
            request.setAttribute("noticeFormBean", noticeForm);

            return mapping.findForward(Constants.EDIT_KEY);
        }
    }

    public ActionForward update(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        ActionMessages messages = new ActionMessages();
        NoticeForm noticeForm = (NoticeForm) form;
        Notice notice = new Notice();

        // update object
        notice.setId(new Integer(noticeForm.getId()));
        notice.setSender(noticeForm.getSender());
        notice.setTitle(noticeForm.getTitle());
        notice.setContent(noticeForm.getContent());
        notice.setSendtime(noticeForm.getSendtime());
        noticeDAO.update(notice);

        // save messages
        messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
                "notice.message.edit.success"));
        saveErrors(request, messages);

        // get pageForm from session
        PageForm pageForm = (PageForm) getSession(request,
                Constants.PAGER_ADDRESS);

        // get pager form list page
        Pager pager = noticeDAO.findPager(pageForm.getPageSize(), pageForm
                .getPageNo());
        request.setAttribute("noticeList", pager.getResultList());
        request.setAttribute("pager", pager);

        ActionForward forward = mapping.findForward(Constants.LIST_KEY);
        return (forward);
    }

    public ActionForward delete(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        ActionMessages messages = new ActionMessages();

        String id = request.getParameter("id");
        if (id == null) {
            // if id not exist
            messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
                    "notice.message.edit.notexist"));
        } else {
            // delete object
            noticeDAO.delete(id);

            // save messages
            messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
                    "notice.message.delete.success"));

            // get pageForm from session
            PageForm pageForm = (PageForm) getSession(request,
                    Constants.PAGER_ADDRESS);

            // get pager form list page
            Pager pager = noticeDAO.findPager(pageForm.getPageSize(), pageForm
                    .getPageNo());
            request.setAttribute("noticeList", pager.getResultList());
            request.setAttribute("pager", pager);
        }
        saveErrors(request, messages);
        return mapping.findForward(Constants.LIST_KEY);
    }
}

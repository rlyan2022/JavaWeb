package com.demo.struts.forms;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import javax.servlet.http.HttpServletRequest;

public class MeetingForm extends ActionForm {

    private static final long serialVersionUID = -7819458651912123880L;

    protected String id = null;

    protected String sender = null;

    protected String starttime = null;

    protected String endtime = null;

    protected String address = null;

    protected String title = null;

    protected String content = null;

    public ActionErrors validate(ActionMapping arg0, HttpServletRequest arg1) {
        ActionErrors errors = new ActionErrors();
        String queryString = arg1.getQueryString();
        if (queryString.equalsIgnoreCase("method=insert")
                || queryString.equalsIgnoreCase("method=update")) {
            if (starttime == null || starttime.equals("")) {
                errors.add("starttime", new ActionMessage("meeting.error.starttime"));
            }

            if (endtime == null || endtime.equals("")) {
                errors.add("endtime", new ActionMessage("meeting.error.endtime"));
            }

            if (address == null || address.equals("")) {
                errors.add("address", new ActionMessage("meeting.error.address"));
            }

            if (title == null || title.equals("")) {
                errors.add("title", new ActionMessage("meeting.error.title"));
            }

            if (content == null || content.equals("")) {
                errors.add("content", new ActionMessage("meeting.error.content"));
            }
        }
        arg1.setAttribute("meetingFormBean", this);
        return errors;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getEndtime() {
        return endtime;
    }

    public void setEndtime(String endtime) {
        this.endtime = endtime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getStarttime() {
        return starttime;
    }

    public void setStarttime(String starttime) {
        this.starttime = starttime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


}

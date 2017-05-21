package com.demo.struts.forms;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SmsForm extends ActionForm {

    private static final long serialVersionUID = 4127444815071959178L;

    protected String id = null;

    protected String username = null;

    protected String sender = null;

    protected String message = null;

    protected String sendtime = null;

    protected String isread = null;

    public ActionErrors validate(ActionMapping arg0, HttpServletRequest arg1) {
        ActionErrors errors = new ActionErrors();
        String queryString = arg1.getQueryString();
        if (queryString.equalsIgnoreCase("method=insert")) {
            if (username == null || username.equals("")) {
                errors.add("username", new ActionMessage("sms.error.username"));
            }

            if (message == null || message.equals("")) {
                errors.add("message", new ActionMessage("sms.error.message"));
            }
        }
        arg1.setAttribute("smsFormBean", this);
        return errors;
    }

    public String getSendtime() {
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        sendtime = f.format(new Date());
        return sendtime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIsread() {
        return isread;
    }

    public void setIsread(String isread) {
        this.isread = isread;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public void setSendtime(String sendtime) {
        this.sendtime = sendtime;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}

package com.demo.struts.actions;

import com.demo.hibernate.dao.UserDAO;
import com.demo.struts.forms.LoginForm;
import com.demo.struts.util.Constants;
import org.apache.log4j.Logger;
import org.apache.struts.action.*;
import org.apache.struts.actions.DispatchAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginAction extends DispatchAction {

    protected UserDAO userDAO;

    Logger log = Logger.getLogger(this.getClass());

    public UserDAO getUserDAO() {
        return userDAO;
    }

    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public ActionForward execute(ActionMapping mapping, ActionForm form,
                                 HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        ActionMessages messages = new ActionMessages();
        ActionForward forward = new ActionForward();
        LoginForm loginForm = (LoginForm) form;

        try {
            // get parameters
            String username = loginForm.getUsername();

            // invalidate the original session if exists
            HttpSession session = request.getSession(false);
            if (session != null) {
                session.invalidate();
            }

            // create a new session for the user
            session = request.getSession(true);

            // login
            boolean isValid = valid(request, loginForm);
            if (isValid) {
                session.setAttribute(Constants.USERNAME_KEY, username);

                log.info("User " + username + " login.");
            } else {
                messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
                        "login.message.failed"));
            }

        } catch (Exception e) {
            messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
                    "login.message.failed"));

        }

        // If a message is required, save the specified key(s)
        // into the request for use by the <struts:errors> tag.

        if (!messages.isEmpty()) {
            saveErrors(request, messages);
            request.setAttribute("loginFormBean", loginForm);
            forward = mapping.findForward(Constants.FAILURE_KEY);
        } else {
            forward = mapping.findForward(Constants.SUCCESS_KEY);
        }

        // Finish with
        return (forward);
    }

    private boolean valid(HttpServletRequest request, LoginForm loginForm) {
        if (getUserDAO().isValid(loginForm.getUsername(),
                loginForm.getPassword())) {
            return true;
        } else {
            return false;
        }
    }
}

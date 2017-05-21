package com.demo.struts.forms;

import com.demo.struts.util.RegExpression;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import javax.servlet.http.HttpServletRequest;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AddressForm extends ActionForm {

    private static final long serialVersionUID = -9064004131486821122L;

    protected String id = null;

    protected String username = null;

    protected String name = null;

    protected String sex = null;

    protected String mobile = null;

    protected String email = null;

    protected String qq = null;

    protected String company = null;

    protected String address = null;

    protected String postcode = null;

    public ActionErrors validate(ActionMapping arg0, HttpServletRequest arg1) {
        ActionErrors errors = new ActionErrors();
        String queryString = arg1.getQueryString();
        if (queryString.equalsIgnoreCase("method=insert")
                || queryString.equalsIgnoreCase("method=update")) {
            //check name
            if (name == null || name.equals("")) {
                errors.add("name", new ActionMessage("address.error.name"));
            }

            //check mobile
            if (mobile != null && !mobile.equals("")) {
                Pattern p_mobile = Pattern.compile(RegExpression.REG_mobile);
                Matcher m_mobile = p_mobile.matcher(mobile);
                if (!m_mobile.find()) {
                    errors.add("mobile", new ActionMessage("address.error.mobile"));
                }
            }

            //check email
            if (email != null && !email.equals("")) {
                Pattern p_email = Pattern.compile(RegExpression.REG_email);
                Matcher m_email = p_email.matcher(email);
                if (!m_email.find()) {
                    errors.add("email", new ActionMessage("address.error.email"));
                }
            }

            //check postcode
            if (postcode != null && !postcode.equals("")) {
                Pattern p_postcode = Pattern.compile(RegExpression.REG_postcode);
                Matcher m_postcode = p_postcode.matcher(postcode);
                if (!m_postcode.find()) {
                    errors.add("postcode", new ActionMessage("address.error.postcode"));
                }
            }
        }
        arg1.setAttribute("addressFormBean", this);
        return errors;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}

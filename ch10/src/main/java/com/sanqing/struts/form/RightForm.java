package com.sanqing.struts.form;

import org.apache.struts.action.ActionForm;

public class RightForm extends ActionForm {
    private Long rightCode;
    private String rightText;
    private String rightUrl;

    public String getRightUrl() {
        return rightUrl;
    }

    public void setRightUrl(String rightUrl) {
        this.rightUrl = rightUrl;
    }

    public Long getRightCode() {
        return rightCode;
    }

    public void setRightCode(Long rightCode) {
        this.rightCode = rightCode;
    }

    public String getRightText() {
        return rightText;
    }

    public void setRightText(String rightText) {
        this.rightText = rightText;
    }
}

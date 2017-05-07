package com.sanqing.struts.form;

import org.apache.struts.action.ActionForm;

public class CustProfferForm extends ActionForm {

    private String odrCustomer;
    private Double adrTotal;

    public String getOdrCustomer() {
        return odrCustomer;
    }

    public void setOdrCustomer(String odrCustomer) {
        this.odrCustomer = odrCustomer;
    }

    public Double getAdrTotal() {
        return adrTotal;
    }

    public void setAdrTotal(Double adrTotal) {
        this.adrTotal = adrTotal;
    }

}
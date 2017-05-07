package com.sanqing.struts.form;


import org.apache.struts.action.ActionForm;

public class ChanceForm extends ActionForm {
    private Long chcId;
    private String chcSource;
    private String chcCustName;
    private String chcTitle;
    private Integer chcRate;
    private String chcLinkman;
    private String chcTel;
    private String chcDesc;
    private String chcCreateBy;
    private String chcCreateDate;
    private String chcDueTo;
    private String chcDueDate;
    private String chcStatus;


    public Long getChcId() {
        return chcId;
    }

    public void setChcId(Long chcId) {
        this.chcId = chcId;
    }

    public String getChcSource() {
        return chcSource;
    }

    public void setChcSource(String chcSource) {
        this.chcSource = chcSource;
    }

    public String getChcCustName() {
        return chcCustName;
    }

    public void setChcCustName(String chcCustName) {
        this.chcCustName = chcCustName;
    }

    public String getChcTitle() {
        return chcTitle;
    }

    public void setChcTitle(String chcTitle) {
        this.chcTitle = chcTitle;
    }

    public Integer getChcRate() {
        return chcRate;
    }

    public void setChcRate(Integer chcRate) {
        this.chcRate = chcRate;
    }

    public String getChcLinkman() {
        return chcLinkman;
    }

    public void setChcLinkman(String chcLinkman) {
        this.chcLinkman = chcLinkman;
    }

    public String getChcTel() {
        return chcTel;
    }

    public void setChcTel(String chcTel) {
        this.chcTel = chcTel;
    }

    public String getChcDesc() {
        return chcDesc;
    }

    public void setChcDesc(String chcDesc) {
        this.chcDesc = chcDesc;
    }

    public String getChcCreateBy() {
        return chcCreateBy;
    }

    public void setChcCreateBy(String chcCreateBy) {
        this.chcCreateBy = chcCreateBy;
    }

    public String getChcDueTo() {
        return chcDueTo;
    }

    public void setChcDueTo(String chcDueTo) {
        this.chcDueTo = chcDueTo;
    }

    public String getChcStatus() {
        return chcStatus;
    }

    public void setChcStatus(String chcStatus) {
        this.chcStatus = chcStatus;
    }

    public String getChcCreateDate() {
        return chcCreateDate;
    }

    public void setChcCreateDate(String chcCreateDate) {
        this.chcCreateDate = chcCreateDate;
    }

    public String getChcDueDate() {
        return chcDueDate;
    }

    public void setChcDueDate(String chcDueDate) {
        this.chcDueDate = chcDueDate;
    }
}


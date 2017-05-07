package com.sanqing.struts.form;

public class CstServiceReptForm {
    private String svrType;
    private Integer svrCount;
    private String svrDate;

    public String getSvrDate() {
        return svrDate;
    }

    public void setSvrDate(String svrDate) {
        this.svrDate = svrDate;
    }

    public String getSvrType() {
        return svrType;
    }

    public void setSvrType(String svrType) {
        this.svrType = svrType;
    }

    public Integer getSvrCount() {
        return svrCount;
    }

    public void setSvrCount(Integer svrCount) {
        this.svrCount = svrCount;
    }
}

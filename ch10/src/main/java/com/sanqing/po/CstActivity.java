package com.sanqing.po;

public class CstActivity implements java.io.Serializable {

    // Fields

    private Long atvId;
    private CstCustomer cstCustomer;
    private String atvDate;
    private String atvPlace;
    private String atvTitle;
    private String atvDesc;
    private String atvRemark;

    // Constructors

    /**
     * default constructor
     */
    public CstActivity() {
    }

    /**
     * minimal constructor
     */
    public CstActivity(String atvDate, String atvPlace, String atvTitle) {
        this.atvDate = atvDate;
        this.atvPlace = atvPlace;
        this.atvTitle = atvTitle;
    }

    /**
     * full constructor
     */
    public CstActivity(CstCustomer cstCustomer, String atvDate,
                       String atvPlace, String atvTitle, String atvDesc) {
        this.cstCustomer = cstCustomer;
        this.atvDate = atvDate;
        this.atvPlace = atvPlace;
        this.atvTitle = atvTitle;
        this.atvDesc = atvDesc;
    }

    // Property accessors

    public Long getAtvId() {
        return this.atvId;
    }

    public void setAtvId(Long atvId) {
        this.atvId = atvId;
    }

    public CstCustomer getCstCustomer() {
        return this.cstCustomer;
    }

    public void setCstCustomer(CstCustomer cstCustomer) {
        this.cstCustomer = cstCustomer;
    }

    public String getAtvDate() {
        return this.atvDate;
    }

    public void setAtvDate(String atvDate) {
        this.atvDate = atvDate;
    }

    public String getAtvPlace() {
        return this.atvPlace;
    }

    public void setAtvPlace(String atvPlace) {
        this.atvPlace = atvPlace;
    }

    public String getAtvTitle() {
        return this.atvTitle;
    }

    public void setAtvTitle(String atvTitle) {
        this.atvTitle = atvTitle;
    }

    public String getAtvDesc() {
        return this.atvDesc;
    }

    public void setAtvDesc(String atvDesc) {
        this.atvDesc = atvDesc;
    }

    public String getAtvRemark() {
        return atvRemark;
    }

    public void setAtvRemark(String atvRemark) {
        this.atvRemark = atvRemark;
    }

}
package com.sanqing.po;

public class CstService implements java.io.Serializable {

    // Fields

    private Long svrId;
    private String svrType;
    private String svrTitle;
    private CstCustomer cstCustomer;
    private String svrStatus;
    private String svrRequest;
    private String svrCreateBy;
    private String svrCreateDate;
    private String svrDueTo;
    private String svrDueDate;
    private String svrDeal;
    private String svrDealBy;
    private String svrDealDate;
    private String svrResult;
    private String svrSatisfy;

    // Constructors

    /**
     * default constructor
     */
    public CstService() {
    }

    /**
     * minimal constructor
     */
    public CstService(String svrType, String svrTitle, String svrStatus,
                      String svrRequest, String svrCreateBy, String svrCreateDate) {
        this.svrType = svrType;
        this.svrTitle = svrTitle;
        this.svrStatus = svrStatus;
        this.svrRequest = svrRequest;
        this.svrCreateBy = svrCreateBy;
        this.svrCreateDate = svrCreateDate;
    }

    /**
     * full constructor
     */
    public CstService(String svrType, String svrTitle, CstCustomer cstCustomer,
                      String svrStatus, String svrRequest, String svrCreateBy,
                      String svrCreateDate, String svrDueTo, String svrDueDate,
                      String svrDeal, String svrDealBy, String svrDealDate,
                      String svrResult, String svrSatisfy) {
        this.svrType = svrType;
        this.svrTitle = svrTitle;
        this.cstCustomer = cstCustomer;
        this.svrStatus = svrStatus;
        this.svrRequest = svrRequest;
        this.svrCreateBy = svrCreateBy;
        this.svrCreateDate = svrCreateDate;
        this.svrDueTo = svrDueTo;
        this.svrDueDate = svrDueDate;
        this.svrDeal = svrDeal;
        this.svrDealBy = svrDealBy;
        this.svrDealDate = svrDealDate;
        this.svrResult = svrResult;
        this.svrSatisfy = svrSatisfy;
    }

    public Long getSvrId() {
        return svrId;
    }

    public void setSvrId(Long svrId) {
        this.svrId = svrId;
    }

    public String getSvrType() {
        return svrType;
    }

    public void setSvrType(String svrType) {
        this.svrType = svrType;
    }

    public String getSvrTitle() {
        return svrTitle;
    }

    public void setSvrTitle(String svrTitle) {
        this.svrTitle = svrTitle;
    }

    public CstCustomer getCstCustomer() {
        return cstCustomer;
    }

    public void setCstCustomer(CstCustomer cstCustomer) {
        this.cstCustomer = cstCustomer;
    }

    public String getSvrStatus() {
        return svrStatus;
    }

    public void setSvrStatus(String svrStatus) {
        this.svrStatus = svrStatus;
    }

    public String getSvrRequest() {
        return svrRequest;
    }

    public void setSvrRequest(String svrRequest) {
        this.svrRequest = svrRequest;
    }

    public String getSvrCreateBy() {
        return svrCreateBy;
    }

    public void setSvrCreateBy(String svrCreateBy) {
        this.svrCreateBy = svrCreateBy;
    }

    public String getSvrCreateDate() {
        return svrCreateDate;
    }

    public void setSvrCreateDate(String svrCreateDate) {
        this.svrCreateDate = svrCreateDate;
    }

    public String getSvrDueTo() {
        return svrDueTo;
    }

    public void setSvrDueTo(String svrDueTo) {
        this.svrDueTo = svrDueTo;
    }

    public String getSvrDueDate() {
        return svrDueDate;
    }

    public void setSvrDueDate(String svrDueDate) {
        this.svrDueDate = svrDueDate;
    }

    public String getSvrDeal() {
        return svrDeal;
    }

    public void setSvrDeal(String svrDeal) {
        this.svrDeal = svrDeal;
    }

    public String getSvrDealBy() {
        return svrDealBy;
    }

    public void setSvrDealBy(String svrDealBy) {
        this.svrDealBy = svrDealBy;
    }

    public String getSvrDealDate() {
        return svrDealDate;
    }

    public void setSvrDealDate(String svrDealDate) {
        this.svrDealDate = svrDealDate;
    }

    public String getSvrResult() {
        return svrResult;
    }

    public void setSvrResult(String svrResult) {
        this.svrResult = svrResult;
    }

    public String getSvrSatisfy() {
        return svrSatisfy;
    }

    public void setSvrSatisfy(String svrSatisfy) {
        this.svrSatisfy = svrSatisfy;
    }

    // Property accessors
}
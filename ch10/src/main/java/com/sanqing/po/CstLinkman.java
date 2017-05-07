package com.sanqing.po;

/**
 * CstLinkman entity.
 *
 * @author MyEclipse Persistence Tools
 */

public class CstLinkman implements java.io.Serializable {
    private Long lkmId;                            //联系人编号
    private CstCustomer cstCustomer;            //客户信息
    private String lkmName;                        //联系人姓名
    private String lkmSex;                        //联系人性别
    private String lkmPostion;                    //联系人职位
    private String lkmTel;                        //办公电话
    private String lkmMobile;                    //手机号码
    private String lkmMemo;                        //备注信息

    // Constructors

    /**
     * default constructor
     */
    public CstLinkman() {
    }

    /**
     * minimal constructor
     */
    public CstLinkman(CstCustomer cstCustomer, String lkmName, String lkmTel) {
        this.cstCustomer = cstCustomer;
        this.lkmName = lkmName;
        this.lkmTel = lkmTel;
    }

    /**
     * full constructor
     */
    public CstLinkman(CstCustomer cstCustomer, String lkmName, String lkmSex,
                      String lkmPostion, String lkmTel, String lkmMobile, String lkmMemo) {
        this.cstCustomer = cstCustomer;
        this.lkmName = lkmName;
        this.lkmSex = lkmSex;
        this.lkmPostion = lkmPostion;
        this.lkmTel = lkmTel;
        this.lkmMobile = lkmMobile;
        this.lkmMemo = lkmMemo;
    }

    // Property accessors

    public Long getLkmId() {
        return this.lkmId;
    }

    public void setLkmId(Long lkmId) {
        this.lkmId = lkmId;
    }

    public CstCustomer getCstCustomer() {
        return this.cstCustomer;
    }

    public void setCstCustomer(CstCustomer cstCustomer) {
        this.cstCustomer = cstCustomer;
    }

    public String getLkmName() {
        return this.lkmName;
    }

    public void setLkmName(String lkmName) {
        this.lkmName = lkmName;
    }

    public String getLkmSex() {
        return this.lkmSex;
    }

    public void setLkmSex(String lkmSex) {
        this.lkmSex = lkmSex;
    }

    public String getLkmPostion() {
        return this.lkmPostion;
    }

    public void setLkmPostion(String lkmPostion) {
        this.lkmPostion = lkmPostion;
    }

    public String getLkmTel() {
        return this.lkmTel;
    }

    public void setLkmTel(String lkmTel) {
        this.lkmTel = lkmTel;
    }

    public String getLkmMobile() {
        return this.lkmMobile;
    }

    public void setLkmMobile(String lkmMobile) {
        this.lkmMobile = lkmMobile;
    }

    public String getLkmMemo() {
        return this.lkmMemo;
    }

    public void setLkmMemo(String lkmMemo) {
        this.lkmMemo = lkmMemo;
    }

}
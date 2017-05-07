package com.sanqing.po;

import java.util.HashSet;
import java.util.Set;

/**
 * CstCustomer entity.
 *
 * @author MyEclipse Persistence Tools
 */

public class CstCustomer implements java.io.Serializable {
    private String custNo;                        //客户编号
    private CstManager cstManager;                //客户经理
    private String custName;                    //客户名称
    private String custRegion;                    //所在地区
    private Integer custLevel;                    //客户等级
    private Integer custSatisfy;                //客户满意度
    private Integer custCredit;                    //客户信任度
    private String custAddr;                    //客户住址
    private String custZip;                        //邮政编码
    private String custTel;                        //电话号码
    private String custFax;                        //传真号码
    private String custWebsite;                    //网站地址
    private String custLicenceNo;                //营业执照号码
    private String custChieftain;                //法人代表
    private Long custBankroll;                    //注册资金
    private Long custTurnover;                    //年营业额
    private String custBank;                    //客户银行
    private String custBankAccount;                //银行号码
    private String custLocalTaxNo;                //地税登记号
    private String custNationalTaxNo;            //国税登记号
    private String custStatus;                    //客户状态
    private Set cstActivities = new HashSet(0);    //客户交往记录
    private Set cstLinkmans = new HashSet(0);    //客户联系人
    private Set cstLosts = new HashSet(0);        //客户流失记录

    // Constructors

    /**
     * default constructor
     */
    public CstCustomer() {
    }

    /**
     * minimal constructor
     */
    public CstCustomer(CstManager cstManager, String custName) {
        this.cstManager = cstManager;
        this.custName = custName;
    }

    /**
     * full constructor
     */
    public CstCustomer(CstManager cstManager, String custName,
                       String custRegion, Integer custLevel, Integer custSatisfy,
                       Integer custCredit, String custAddr, String custZip, String custTel,
                       String custFax, String custWebsite, String custLicenceNo,
                       String custChieftain, Long custBankroll, Long custTurnover,
                       String custBank, String custBankAccount, String custLocalTaxNo,
                       String custNationalTaxNo, String custStatus, Set cstActivities,
                       Set cstLinkmans, Set cstLosts) {
        this.cstManager = cstManager;
        this.custName = custName;
        this.custRegion = custRegion;
        this.custLevel = custLevel;
        this.custSatisfy = custSatisfy;
        this.custCredit = custCredit;
        this.custAddr = custAddr;
        this.custZip = custZip;
        this.custTel = custTel;
        this.custFax = custFax;
        this.custWebsite = custWebsite;
        this.custLicenceNo = custLicenceNo;
        this.custChieftain = custChieftain;
        this.custBankroll = custBankroll;
        this.custTurnover = custTurnover;
        this.custBank = custBank;
        this.custBankAccount = custBankAccount;
        this.custLocalTaxNo = custLocalTaxNo;
        this.custNationalTaxNo = custNationalTaxNo;
        this.custStatus = custStatus;
        this.cstActivities = cstActivities;
        this.cstLinkmans = cstLinkmans;
        this.cstLosts = cstLosts;
    }

    // Property accessors

    public String getCustNo() {
        return this.custNo;
    }

    public void setCustNo(String custNo) {
        this.custNo = custNo;
    }

    public CstManager getCstManager() {
        return this.cstManager;
    }

    public void setCstManager(CstManager cstManager) {
        this.cstManager = cstManager;
    }

    public String getCustName() {
        return this.custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public String getCustRegion() {
        return this.custRegion;
    }

    public void setCustRegion(String custRegion) {
        this.custRegion = custRegion;
    }

    public Integer getCustLevel() {
        return custLevel;
    }

    public void setCustLevel(Integer custLevel) {
        this.custLevel = custLevel;
    }

    public Integer getCustSatisfy() {
        return custSatisfy;
    }

    public void setCustSatisfy(Integer custSatisfy) {
        this.custSatisfy = custSatisfy;
    }

    public Integer getCustCredit() {
        return custCredit;
    }

    public void setCustCredit(Integer custCredit) {
        this.custCredit = custCredit;
    }

    public String getCustAddr() {
        return this.custAddr;
    }

    public void setCustAddr(String custAddr) {
        this.custAddr = custAddr;
    }

    public String getCustZip() {
        return this.custZip;
    }

    public void setCustZip(String custZip) {
        this.custZip = custZip;
    }

    public String getCustTel() {
        return this.custTel;
    }

    public void setCustTel(String custTel) {
        this.custTel = custTel;
    }

    public String getCustFax() {
        return this.custFax;
    }

    public void setCustFax(String custFax) {
        this.custFax = custFax;
    }

    public String getCustWebsite() {
        return this.custWebsite;
    }

    public void setCustWebsite(String custWebsite) {
        this.custWebsite = custWebsite;
    }

    public String getCustLicenceNo() {
        return this.custLicenceNo;
    }

    public void setCustLicenceNo(String custLicenceNo) {
        this.custLicenceNo = custLicenceNo;
    }

    public String getCustChieftain() {
        return this.custChieftain;
    }

    public void setCustChieftain(String custChieftain) {
        this.custChieftain = custChieftain;
    }

    public Long getCustBankroll() {
        return this.custBankroll;
    }

    public void setCustBankroll(Long custBankroll) {
        this.custBankroll = custBankroll;
    }

    public Long getCustTurnover() {
        return this.custTurnover;
    }

    public void setCustTurnover(Long custTurnover) {
        this.custTurnover = custTurnover;
    }

    public String getCustBank() {
        return this.custBank;
    }

    public void setCustBank(String custBank) {
        this.custBank = custBank;
    }

    public String getCustBankAccount() {
        return this.custBankAccount;
    }

    public void setCustBankAccount(String custBankAccount) {
        this.custBankAccount = custBankAccount;
    }

    public String getCustLocalTaxNo() {
        return this.custLocalTaxNo;
    }

    public void setCustLocalTaxNo(String custLocalTaxNo) {
        this.custLocalTaxNo = custLocalTaxNo;
    }

    public String getCustNationalTaxNo() {
        return this.custNationalTaxNo;
    }

    public void setCustNationalTaxNo(String custNationalTaxNo) {
        this.custNationalTaxNo = custNationalTaxNo;
    }

    public String getCustStatus() {
        return this.custStatus;
    }

    public void setCustStatus(String custStatus) {
        this.custStatus = custStatus;
    }

    public Set getCstActivities() {
        return this.cstActivities;
    }

    public void setCstActivities(Set cstActivities) {
        this.cstActivities = cstActivities;
    }

    public Set getCstLinkmans() {
        return this.cstLinkmans;
    }

    public void setCstLinkmans(Set cstLinkmans) {
        this.cstLinkmans = cstLinkmans;
    }

    public Set getCstLosts() {
        return this.cstLosts;
    }

    public void setCstLosts(Set cstLosts) {
        this.cstLosts = cstLosts;
    }

}
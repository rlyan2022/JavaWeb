package com.sanqing.po;

import java.util.HashSet;
import java.util.Set;


public class SalChance implements java.io.Serializable {
    private Long chcId;                                //机会编号
    private String chcSource;                        //机会来源
    private String chcCustName;                        //客户名称
    private String chcTitle;                        //概要信息
    private Integer chcRate;                        //成功机率
    private String chcLinkman;                        //联系人
    private String chcTel;                            //电话号码
    private String chcDesc;                            //机会描述
    private String chcCreateBy;                        //创建人
    private String chcCreateDate;                    //创建时间
    private String chcDueTo;                        //实施人
    private String chcDueDate;                        //实施时间
    private String chcStatus;                        //机会状态
    private Set salPlans = new HashSet(0);            //销售计划记录


    public SalChance() {
    }


    public SalChance(String chcCustName, String chcTitle, Integer chcRate,
                     String chcDesc, String chcCreateBy, String chcCreateDate,
                     String chcStatus) {
        this.chcCustName = chcCustName;
        this.chcTitle = chcTitle;
        this.chcRate = chcRate;
        this.chcDesc = chcDesc;
        this.chcCreateBy = chcCreateBy;
        this.chcCreateDate = chcCreateDate;
        this.chcStatus = chcStatus;
    }


    public SalChance(String chcSource, String chcCustName, String chcTitle,
                     Integer chcRate, String chcLinkman, String chcTel, String chcDesc,
                     String chcCreateBy, String chcCreateDate, String chcDueTo,
                     String chcDueDate, String chcStatus, Set salPlans) {
        this.chcSource = chcSource;
        this.chcCustName = chcCustName;
        this.chcTitle = chcTitle;
        this.chcRate = chcRate;
        this.chcLinkman = chcLinkman;
        this.chcTel = chcTel;
        this.chcDesc = chcDesc;
        this.chcCreateBy = chcCreateBy;
        this.chcCreateDate = chcCreateDate;
        this.chcDueTo = chcDueTo;
        this.chcDueDate = chcDueDate;
        this.chcStatus = chcStatus;
        this.salPlans = salPlans;
    }


    public Long getChcId() {
        return chcId;
    }


    public void setChcId(Long chcId) {
        this.chcId = chcId;
    }


    public String getChcSource() {
        return this.chcSource;
    }

    public void setChcSource(String chcSource) {
        this.chcSource = chcSource;
    }

    public String getChcCustName() {
        return this.chcCustName;
    }

    public void setChcCustName(String chcCustName) {
        this.chcCustName = chcCustName;
    }

    public String getChcTitle() {
        return this.chcTitle;
    }

    public void setChcTitle(String chcTitle) {
        this.chcTitle = chcTitle;
    }

    public Integer getChcRate() {
        return this.chcRate;
    }

    public void setChcRate(Integer chcRate) {
        this.chcRate = chcRate;
    }

    public String getChcLinkman() {
        return this.chcLinkman;
    }

    public void setChcLinkman(String chcLinkman) {
        this.chcLinkman = chcLinkman;
    }

    public String getChcTel() {
        return this.chcTel;
    }

    public void setChcTel(String chcTel) {
        this.chcTel = chcTel;
    }

    public String getChcDesc() {
        return this.chcDesc;
    }

    public void setChcDesc(String chcDesc) {
        this.chcDesc = chcDesc;
    }

    public String getChcCreateBy() {
        return this.chcCreateBy;
    }

    public void setChcCreateBy(String chcCreateBy) {
        this.chcCreateBy = chcCreateBy;
    }


    public String getChcDueTo() {
        return this.chcDueTo;
    }

    public void setChcDueTo(String chcDueTo) {
        this.chcDueTo = chcDueTo;
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


    public String getChcStatus() {
        return this.chcStatus;
    }

    public void setChcStatus(String chcStatus) {
        this.chcStatus = chcStatus;
    }

    public Set getSalPlans() {
        return this.salPlans;
    }

    public void setSalPlans(Set salPlans) {
        this.salPlans = salPlans;
    }

}
package com.sanqing.po;

import java.util.HashSet;
import java.util.Set;

/**
 * CstManager entity.
 *
 * @author MyEclipse Persistence Tools
 */

public class CstManager implements java.io.Serializable {
    private Long manId;                            //客户经理编号
    private String manName;                        //客户经理姓名
    private Set cstCustomers = new HashSet(0);    //客户信息

    // Constructors

    /**
     * default constructor
     */
    public CstManager() {
    }

    /**
     * minimal constructor
     */
    public CstManager(String manName) {
        this.manName = manName;
    }

    /**
     * full constructor
     */
    public CstManager(String manName, Set cstCustomers) {
        this.manName = manName;
        this.cstCustomers = cstCustomers;
    }

    // Property accessors

    public Long getManId() {
        return this.manId;
    }

    public void setManId(Long manId) {
        this.manId = manId;
    }

    public String getManName() {
        return this.manName;
    }

    public void setManName(String manName) {
        this.manName = manName;
    }

    public Set getCstCustomers() {
        return this.cstCustomers;
    }

    public void setCstCustomers(Set cstCustomers) {
        this.cstCustomers = cstCustomers;
    }

}
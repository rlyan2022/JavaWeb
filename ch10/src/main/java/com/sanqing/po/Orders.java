package com.sanqing.po;

import java.util.HashSet;
import java.util.Set;

public class Orders implements java.io.Serializable {

    // Fields
    private Long odrId;
    private String odrCustomer;
    private String odrDate;
    private String odrAddr;
    private String odrStatus;
    private Set ordersLines = new HashSet(0);

    // Constructors

    /**
     * default constructor
     */
    public Orders() {
    }

    /**
     * minimal constructor
     */
    public Orders(String odrCustomer, String odrDate, String odrStatus) {
        this.odrCustomer = odrCustomer;
        this.odrDate = odrDate;
        this.odrStatus = odrStatus;
    }

    /**
     * full constructor
     */
    public Orders(String odrCustomer, String odrDate, String odrAddr,
                  String odrStatus, Set ordersLines) {
        this.odrCustomer = odrCustomer;
        this.odrDate = odrDate;
        this.odrAddr = odrAddr;
        this.odrStatus = odrStatus;
        this.ordersLines = ordersLines;
    }

    // Property accessors

    public Long getOdrId() {
        return this.odrId;
    }

    public void setOdrId(Long odrId) {
        this.odrId = odrId;
    }

    public String getOdrCustomer() {
        return this.odrCustomer;
    }

    public void setOdrCustomer(String odrCustomer) {
        this.odrCustomer = odrCustomer;
    }

    public String getOdrDate() {
        return this.odrDate;
    }

    public void setOdrDate(String odrDate) {
        this.odrDate = odrDate;
    }

    public String getOdrAddr() {
        return this.odrAddr;
    }

    public void setOdrAddr(String odrAddr) {
        this.odrAddr = odrAddr;
    }

    public String getOdrStatus() {
        return this.odrStatus;
    }

    public void setOdrStatus(String odrStatus) {
        this.odrStatus = odrStatus;
    }

    public Set getOrdersLines() {
        return this.ordersLines;
    }

    public void setOrdersLines(Set ordersLines) {
        this.ordersLines = ordersLines;
    }

}
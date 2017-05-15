package com.sanqing.po;

import java.util.Date;


/**
 * PayOut entity. @author MyEclipse Persistence Tools
 */

public class PayOut implements java.io.Serializable {


    // Fields    

    private Integer id;
    private User user;
    private String payOutName;
    private Double payOutMoney;
    private Date payOutDate;


    // Constructors

    /**
     * default constructor
     */
    public PayOut() {
    }


    /**
     * full constructor
     */
    public PayOut(User user, String payOutName, Double payOutMoney, Date payOutDate) {
        this.user = user;
        this.payOutName = payOutName;
        this.payOutMoney = payOutMoney;
        this.payOutDate = payOutDate;
    }


    // Property accessors

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getPayOutName() {
        return this.payOutName;
    }

    public void setPayOutName(String payOutName) {
        this.payOutName = payOutName;
    }

    public Double getPayOutMoney() {
        return this.payOutMoney;
    }

    public void setPayOutMoney(Double payOutMoney) {
        this.payOutMoney = payOutMoney;
    }

    public Date getPayOutDate() {
        return this.payOutDate;
    }

    public void setPayOutDate(Date payOutDate) {
        this.payOutDate = payOutDate;
    }


}
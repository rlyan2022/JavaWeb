package com.sanqing.po;

import java.util.Date;

public class OrderForm implements java.io.Serializable {
    private Integer orderFormId;    //订单编号
    private User user;                //用户信息
    private String orderFormNo;        //订单序号
    private Date submitTime;        //提交订单时间
    private Date consignmentTime;    //发货时间
    private Double totalPrice;        //总金额
    private String remark;            //买家备注
    private String ipaddress;        //买家IP地址
    private Integer isPayoff;        //买家是否付款
    private Integer isConsignment;    //是否发货

    public OrderForm() {
    }

    public OrderForm(User user) {
        this.user = user;
    }

    public OrderForm(User user, String orderFormNo, Date submitTime,
                     Date consignmentTime, Double totalPrice, String remark,
                     String ipaddress, Integer isPayoff, Integer isConsignment) {
        this.user = user;
        this.orderFormNo = orderFormNo;
        this.submitTime = submitTime;
        this.consignmentTime = consignmentTime;
        this.totalPrice = totalPrice;
        this.remark = remark;
        this.ipaddress = ipaddress;
        this.isPayoff = isPayoff;
        this.isConsignment = isConsignment;
    }

    public Integer getOrderFormId() {
        return this.orderFormId;
    }

    public void setOrderFormId(Integer orderFormId) {
        this.orderFormId = orderFormId;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getOrderFormNo() {
        return this.orderFormNo;
    }

    public void setOrderFormNo(String orderFormNo) {
        this.orderFormNo = orderFormNo;
    }

    public Date getSubmitTime() {
        return this.submitTime;
    }

    public void setSubmitTime(Date submitTime) {
        this.submitTime = submitTime;
    }

    public Date getConsignmentTime() {
        return this.consignmentTime;
    }

    public void setConsignmentTime(Date consignmentTime) {
        this.consignmentTime = consignmentTime;
    }

    public Double getTotalPrice() {
        return this.totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getRemark() {
        return this.remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getIpaddress() {
        return this.ipaddress;
    }

    public void setIpaddress(String ipaddress) {
        this.ipaddress = ipaddress;
    }

    public Integer getIsPayoff() {
        return this.isPayoff;
    }

    public void setIsPayoff(Integer isPayoff) {
        this.isPayoff = isPayoff;
    }

    public Integer getIsConsignment() {
        return this.isConsignment;
    }

    public void setIsConsignment(Integer isConsignment) {
        this.isConsignment = isConsignment;
    }

}
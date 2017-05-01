package com.sanqing.po;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "tb_order")
public class Order {            //订单信息类
    @Id
    @Column(length = 10)
    private String orderNO;        //订单编码
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "customerNO")
    private Customer customer;    //客户
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "productNO")
    private Product product;    //产品
    @Column(length = 10)
    private int quantity;        //产品数量
    @Temporal(TemporalType.DATE)
    private Date orderTime;        //订单的时间
    @Column(length = 50)
    private String otherInfo;    //其他信息

    public String getOrderNO() {
        return orderNO;
    }

    public void setOrderNO(String orderNO) {
        this.orderNO = orderNO;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    public String getOtherInfo() {
        return otherInfo;
    }

    public void setOtherInfo(String otherInfo) {
        this.otherInfo = otherInfo;
    }
}

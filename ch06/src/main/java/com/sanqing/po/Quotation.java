package com.sanqing.po;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "tb_quotation")
public class Quotation {            //报价信息类
    @Id
    @Column(length = 15)
    private String quotationNO;        //报价编号
    @Column(length = 15)
    private String quotationMan;    //报价人
    @Temporal(TemporalType.DATE)
    private Date quotationTime;        //报价时间
    @Column(length = 50)
    private String otherInfo;            //其他信息
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "productNO")
    private Product product;        //产品
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "customerNO")
    private Customer customer;        //客户

    public String getQuotationNO() {
        return quotationNO;
    }

    public void setQuotationNO(String quotationNO) {
        this.quotationNO = quotationNO;
    }

    public String getQuotationMan() {
        return quotationMan;
    }

    public void setQuotationMan(String quotationMan) {
        this.quotationMan = quotationMan;
    }

    public Date getQuotationTime() {
        return quotationTime;
    }

    public void setQuotationTime(Date quotationTime) {
        this.quotationTime = quotationTime;
    }

    public String getOtherInfo() {
        return otherInfo;
    }

    public void setOtherInfo(String otherInfo) {
        this.otherInfo = otherInfo;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}

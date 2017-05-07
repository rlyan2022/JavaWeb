package com.sanqing.po;

import java.util.HashSet;
import java.util.Set;

/**
 * Product entity.
 *
 * @author MyEclipse Persistence Tools
 */

public class Product implements java.io.Serializable {
    private Long prodId;                            //产品编号
    private String prodName;                        //产品名称
    private String prodType;                        //产品型号
    private String prodBatch;                        //产品批次
    private String prodUnit;                        //产品单位
    private Double prodPrice;                        //产品单价
    private String prodMemo;                        //产品备注
    private Set storages = new HashSet(0);            //仓库记录
    private Set ordersLines = new HashSet(0);        //订单详细记录

    // Constructors

    /**
     * default constructor
     */
    public Product() {
    }

    /**
     * minimal constructor
     */
    public Product(String prodName, String prodType) {
        this.prodName = prodName;
        this.prodType = prodType;
    }

    /**
     * full constructor
     */
    public Product(String prodName, String prodType, String prodBatch,
                   String prodUnit, Double prodPrice, String prodMemo, Set storages,
                   Set ordersLines) {
        this.prodName = prodName;
        this.prodType = prodType;
        this.prodBatch = prodBatch;
        this.prodUnit = prodUnit;
        this.prodPrice = prodPrice;
        this.prodMemo = prodMemo;
        this.storages = storages;
        this.ordersLines = ordersLines;
    }

    // Property accessors

    public Long getProdId() {
        return this.prodId;
    }

    public void setProdId(Long prodId) {
        this.prodId = prodId;
    }

    public String getProdName() {
        return this.prodName;
    }

    public void setProdName(String prodName) {
        this.prodName = prodName;
    }

    public String getProdType() {
        return this.prodType;
    }

    public void setProdType(String prodType) {
        this.prodType = prodType;
    }

    public String getProdBatch() {
        return this.prodBatch;
    }

    public void setProdBatch(String prodBatch) {
        this.prodBatch = prodBatch;
    }

    public String getProdUnit() {
        return this.prodUnit;
    }

    public void setProdUnit(String prodUnit) {
        this.prodUnit = prodUnit;
    }

    public Double getProdPrice() {
        return this.prodPrice;
    }

    public void setProdPrice(Double prodPrice) {
        this.prodPrice = prodPrice;
    }

    public String getProdMemo() {
        return this.prodMemo;
    }

    public void setProdMemo(String prodMemo) {
        this.prodMemo = prodMemo;
    }

    public Set getStorages() {
        return this.storages;
    }

    public void setStorages(Set storages) {
        this.storages = storages;
    }

    public Set getOrdersLines() {
        return this.ordersLines;
    }

    public void setOrdersLines(Set ordersLines) {
        this.ordersLines = ordersLines;
    }

}
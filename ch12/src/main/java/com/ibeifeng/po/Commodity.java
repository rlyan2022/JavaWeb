package com.ibeifeng.po;

import java.util.HashSet;
import java.util.Set;

/**
 * Commodity entity.
 *
 * @author MyEclipse Persistence Tools
 */

public class Commodity implements java.io.Serializable {

    // Fields

    private Integer commodityId;
    private String commodityName;
    private Double price;
    private Double agio;
    private Set consumes = new HashSet(0);

    // Constructors

    /**
     * default constructor
     */
    public Commodity() {
    }

    /**
     * full constructor
     */
    public Commodity(String commodityName, Double price, Double agio,
                     Set consumes) {
        this.commodityName = commodityName;
        this.price = price;
        this.agio = agio;
        this.consumes = consumes;
    }

    // Property accessors

    public Integer getCommodityId() {
        return this.commodityId;
    }

    public void setCommodityId(Integer commodityId) {
        this.commodityId = commodityId;
    }

    public String getCommodityName() {
        return this.commodityName;
    }

    public void setCommodityName(String commodityName) {
        this.commodityName = commodityName;
    }

    public Double getPrice() {
        return this.price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getAgio() {
        return this.agio;
    }

    public void setAgio(Double agio) {
        this.agio = agio;
    }

    public Set getConsumes() {
        return this.consumes;
    }

    public void setConsumes(Set consumes) {
        this.consumes = consumes;
    }

}
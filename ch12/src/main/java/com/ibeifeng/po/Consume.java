package com.ibeifeng.po;

/**
 * Consume entity.
 *
 * @author MyEclipse Persistence Tools
 */

public class Consume implements java.io.Serializable {

    // Fields

    private Integer consumeId;
    private Vip vip;
    private Commodity commodity;
    private String name;
    private String commodityName;
    private Double price;
    private Double practicePrice;

    // Constructors

    /**
     * default constructor
     */
    public Consume() {
    }

    /**
     * full constructor
     */
    public Consume(Vip vip, Commodity commodity, String name,
                   String commodityName, Double price, Double practicePrice) {
        this.vip = vip;
        this.commodity = commodity;
        this.name = name;
        this.commodityName = commodityName;
        this.price = price;
        this.practicePrice = practicePrice;
    }

    // Property accessors

    public Integer getConsumeId() {
        return this.consumeId;
    }

    public void setConsumeId(Integer consumeId) {
        this.consumeId = consumeId;
    }

    public Vip getVip() {
        return this.vip;
    }

    public void setVip(Vip vip) {
        this.vip = vip;
    }

    public Commodity getCommodity() {
        return this.commodity;
    }

    public void setCommodity(Commodity commodity) {
        this.commodity = commodity;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Double getPracticePrice() {
        return this.practicePrice;
    }

    public void setPracticePrice(Double practicePrice) {
        this.practicePrice = practicePrice;
    }

}
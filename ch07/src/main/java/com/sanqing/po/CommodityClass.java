package com.sanqing.po;

public class CommodityClass implements java.io.Serializable {
    private Integer commodityClassId;        //商品种类编号
    private String commodityClassName;        //商品种类名称

    public Integer getCommodityClassId() {
        return this.commodityClassId;
    }

    public void setCommodityClassId(Integer commodityClassId) {
        this.commodityClassId = commodityClassId;
    }

    public String getCommodityClassName() {
        return this.commodityClassName;
    }

    public void setCommodityClassName(String commodityClassName) {
        this.commodityClassName = commodityClassName;
    }

    public CommodityClass(Integer commodityClassId) {
        super();
        this.commodityClassId = commodityClassId;
    }

    public CommodityClass() {
        super();
    }
}
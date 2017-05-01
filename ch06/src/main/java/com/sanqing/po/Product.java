package com.sanqing.po;

import javax.persistence.*;

@Entity
@Table(name = "tb_product")
public class Product {                //产品信息类
    @Id
    @Column(length = 15)
    private String productNO;        //产品编号
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "producttypeNO")
    private ProductType productType;//产品类型
    @Column(length = 20)
    private String productName;        //产品名称
    @Column(length = 20)
    private String producingArea;    //产品所在区域
    @Column(length = 20)
    private String productOwner;    //产品所有者
    @Column(length = 20)
    private String unit;            //产品单位
    @Column
    private double price;            //产品价格
    @Column
    private int quantity;            //产品数量
    @Column(length = 50)
    private String otherInfo;        //其他信息

    public String getProductNO() {
        return productNO;
    }

    public void setProductNO(String productNO) {
        this.productNO = productNO;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProducingArea() {
        return producingArea;
    }

    public void setProducingArea(String producingArea) {
        this.producingArea = producingArea;
    }

    public String getProductOwner() {
        return productOwner;
    }

    public void setProductOwner(String productOwner) {
        this.productOwner = productOwner;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getOtherInfo() {
        return otherInfo;
    }

    public void setOtherInfo(String otherInfo) {
        this.otherInfo = otherInfo;
    }

    public ProductType getProductType() {
        return productType;
    }

    public void setProductType(ProductType productType) {
        this.productType = productType;
    }
}

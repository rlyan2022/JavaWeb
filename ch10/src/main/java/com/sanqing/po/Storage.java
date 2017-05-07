package com.sanqing.po;

/**
 * Storage entity.
 *
 * @author MyEclipse Persistence Tools
 */

public class Storage implements java.io.Serializable {
    private Long stkId;                                //库存编号
    private Product product;                        //产品信息
    private String stkWarehouse;                    //库存名称
    private String stkWare;                            //货物位置
    private Integer stkCount;                        //库存数量
    private String stkMemo;                            //备注信息

    // Constructors

    /**
     * default constructor
     */
    public Storage() {
    }

    /**
     * minimal constructor
     */
    public Storage(Product product, String stkWarehouse, String stkWare,
                   Integer stkCount) {
        this.product = product;
        this.stkWarehouse = stkWarehouse;
        this.stkWare = stkWare;
        this.stkCount = stkCount;
    }

    /**
     * full constructor
     */
    public Storage(Product product, String stkWarehouse, String stkWare,
                   Integer stkCount, String stkMemo) {
        this.product = product;
        this.stkWarehouse = stkWarehouse;
        this.stkWare = stkWare;
        this.stkCount = stkCount;
        this.stkMemo = stkMemo;
    }

    // Property accessors

    public Long getStkId() {
        return this.stkId;
    }

    public void setStkId(Long stkId) {
        this.stkId = stkId;
    }

    public Product getProduct() {
        return this.product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getStkWarehouse() {
        return this.stkWarehouse;
    }

    public void setStkWarehouse(String stkWarehouse) {
        this.stkWarehouse = stkWarehouse;
    }

    public String getStkWare() {
        return this.stkWare;
    }

    public void setStkWare(String stkWare) {
        this.stkWare = stkWare;
    }

    public Integer getStkCount() {
        return this.stkCount;
    }

    public void setStkCount(Integer stkCount) {
        this.stkCount = stkCount;
    }

    public String getStkMemo() {
        return this.stkMemo;
    }

    public void setStkMemo(String stkMemo) {
        this.stkMemo = stkMemo;
    }

}
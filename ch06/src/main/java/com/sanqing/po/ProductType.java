package com.sanqing.po;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tb_producttype")
public class ProductType {            //产品类别信息
    private String producttypeNO;    //产品类别编号
    private String producttypeName;    //产品类别名称

    public ProductType() {
    }            //默认构造方法

    public ProductType(String producttypeNO) {//自定义构造方法
        this.producttypeNO = producttypeNO;
    }

    @Id
    @Column(length = 15)
    public String getProducttypeNO() {//获得产品类别编号
        return producttypeNO;
    }

    public void setProducttypeNO(String producttypeNO) {//设置产品类别编号
        this.producttypeNO = producttypeNO;
    }

    @Column(length = 20)
    public String getProducttypeName() {//获得产品类别名称
        return producttypeName;
    }

    public void setProducttypeName(String producttypeName) {//设置产品类别名称
        this.producttypeName = producttypeName;
    }
}

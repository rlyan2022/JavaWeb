package com.sanqing.action;

import com.sanqing.po.Product;
import com.sanqing.po.ProductType;
import com.sanqing.service.ProductService;
import com.sanqing.service.ProductTypeService;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 产品管理
 */
@Controller("productManageAction")
@Scope("prototype")
public class ProductManageAction extends BaseAction {

    @Resource
    private ProductService productService;
    @Resource
    private ProductTypeService productTypeService;

    /* 产品编号 */
    private String productNO;
    // 产品类型编号
    private String producttypeNO;
    /* 产品名称 */
    private String productName;
    /* 产品所在区域 */
    private String producingArea;
    /* 产品所有者 */
    private String productOwner;
    /* 产品单位 */
    private String unit;
    /* 产品价格 */
    private double price;
    /* 产品数量*/
    private int quantity;
    /* 其他信息 */
    private String otherInfo;

    /**
     * 添加产品输入界面
     *
     * @return
     */
    public String addUI() {
        HttpServletRequest request = ServletActionContext.getRequest();
        List<ProductType> producttypes = productTypeService.getScrollData().getResultlist();
        request.setAttribute("producttypes", producttypes);
        return "add";
    }

    /**
     * 添加产品
     *
     * @return
     */
    public String add() {
        Product product = new Product();
        product.setProductNO(productNO);
        product.setProductType(new ProductType(producttypeNO));
        product.setOtherInfo(otherInfo);
        product.setPrice(price);
        product.setProducingArea(producingArea);
        product.setProductName(productName);
        product.setProductOwner(productOwner);
        product.setQuantity(quantity);
        product.setUnit(unit);
        productService.save(product);
        return "pub_add_success";
    }

    /**
     * 修改产品输入界面
     *
     * @return
     */
    public String updateUI() {
        Product product = productService.find(productNO);
        HttpServletRequest request = ServletActionContext.getRequest();
        request.setAttribute("product", product);
        List<ProductType> producttypes = productTypeService.getScrollData().getResultlist();
        request.setAttribute("producttypes", producttypes);
        return "update";
    }

    /**
     * 修改产品
     *
     * @return
     */
    public String update() {
        Product product = new Product();
        product.setProductNO(productNO);
        product.setProductType(new ProductType(producttypeNO));
        product.setOtherInfo(otherInfo);
        product.setPrice(price);
        product.setProducingArea(producingArea);
        product.setProductName(productName);
        product.setProductOwner(productOwner);
        product.setQuantity(quantity);
        product.setUnit(unit);
        productService.update(product);
        return "pub_update_success";
    }

    /**
     * 删除产品
     *
     * @return
     */
    public String del() {
        productService.delete(productNO);
        return "pub_del_success";
    }

    /**
     * 产品查询
     *
     * @return
     */
    public String query() {
        return "query";
    }

    public String getProductNO() {
        return productNO;
    }

    public void setProductNO(String productNO) {
        this.productNO = productNO;
    }

    public String getProducttypeNO() {
        return producttypeNO;
    }

    public void setProducttypeNO(String producttypeNO) {
        this.producttypeNO = producttypeNO;
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

}

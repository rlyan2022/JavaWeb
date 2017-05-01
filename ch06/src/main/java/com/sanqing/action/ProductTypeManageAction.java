package com.sanqing.action;

import com.sanqing.po.ProductType;
import com.sanqing.service.ProductTypeService;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Controller("productTypeManageAction")
@Scope("prototype")
public class ProductTypeManageAction extends BaseAction {

    @Resource
    private ProductTypeService productTypeService;
    // 产品类型编号
    private String producttypeNO;
    // 产品类型名称
    private String producttypeName;

    /**
     * 添加产品类别输入界面
     *
     * @return
     */
    public String addUI() {
        return "add";
    }

    /**
     * 添加产品类别
     *
     * @return
     */
    public String add() {
        ProductType productType = new ProductType();
        productType.setProducttypeNO(producttypeNO);
        productType.setProducttypeName(producttypeName);
        productTypeService.save(productType);
        return "pub_add_success";
    }

    /**
     * 修改产品类别输入界面
     *
     * @return
     */
    public String updateUI() {
        ProductType productType = productTypeService.find(producttypeNO);
        HttpServletRequest request = ServletActionContext.getRequest();
        request.setAttribute("productType", productType);
        return "update";
    }

    /**
     * 修改产品类别
     *
     * @return
     */
    public String update() {
        ProductType productType = new ProductType();
        productType.setProducttypeNO(producttypeNO);
        productType.setProducttypeName(producttypeName);
        productTypeService.update(productType);
        return "pub_update_success";
    }

    /**
     * 删除产品类别
     *
     * @return
     */
    public String del() {
        productTypeService.delete(producttypeNO);
        return "pub_del_success";
    }

    /**
     * 产品类别查询
     *
     * @return
     */
    public String query() {
        return "query";
    }

    public String getProducttypeNO() {
        return producttypeNO;
    }

    public void setProducttypeNO(String producttypeNO) {
        this.producttypeNO = producttypeNO;
    }

    public String getProducttypeName() {
        return producttypeName;
    }

    public void setProducttypeName(String producttypeName) {
        this.producttypeName = producttypeName;
    }
}

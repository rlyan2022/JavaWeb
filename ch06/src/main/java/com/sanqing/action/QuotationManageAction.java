package com.sanqing.action;

import com.sanqing.po.Customer;
import com.sanqing.po.Product;
import com.sanqing.po.Quotation;
import com.sanqing.service.CustomerService;
import com.sanqing.service.ProductService;
import com.sanqing.service.QuotationService;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Controller("quotationManageAction")
@Scope("prototype")
public class QuotationManageAction extends BaseAction {
    @Resource
    private QuotationService quotationService;    //报价业务逻辑组件
    @Resource
    private ProductService productService;        //产品业务逻辑组件
    @Resource
    private CustomerService customerService;    //客户业务逻辑组件
    private String quotationNO;                    // 报价编号
    private String quotationMan;                // 报价人
    private String otherInfo;                    // 其他信息
    private Product product;                    //产品信息
    private Customer customer;                    //客户信息

    public String addUI() {                        //跳转到报价信息输入页面
        HttpServletRequest request =
                ServletActionContext.getRequest();//获得request对象
        request.setAttribute("products",
                productService.getScrollData().getResultlist());//保存产品信息列表
        request.setAttribute("customers",
                customerService.getScrollData().getResultlist());//保存客户信息列表
        return "add";
    }

    public String add() {                    //完成报价信息的录入
        Quotation quotation = new Quotation();//实例化一个Quotation对象
        quotation.setCustomer(customer);//设置客户信息
        quotation.setOtherInfo(otherInfo);//设置其他信息
        quotation.setProduct(product);//设置产品信息
        quotation.setQuotationNO(quotationNO);//设置报价编号
        quotation.setQuotationMan(quotationMan);//设置报价人
        quotationService.save(quotation);//保存报价信息
        return "pub_add_success";//跳转到录入成功页面
    }

    public String updateUI() {        //跳转到报价信息修改页面
        HttpServletRequest
                request = ServletActionContext.getRequest();//获得request对象
        request.setAttribute("products",
                productService.getScrollData().getResultlist());//获得产品信息并保存
        request.setAttribute("customers",
                customerService.getScrollData().getResultlist());//获得客户信息并保存
        request.setAttribute("quotation",
                quotationService.find(quotationNO));//获得报价信息并保存
        return "update";
    }

    public String update() {
        Quotation quotation = new Quotation();//实例化一个Quotation对象
        quotation.setCustomer(customer);//设置客户信息
        quotation.setOtherInfo(otherInfo);//设置其他信息
        quotation.setProduct(product);//设置产品信息
        quotation.setQuotationNO(quotationNO);//设置报价编号
        quotation.setQuotationMan(quotationMan);//设置报价人
        quotationService.update(quotation);//更新报价信息
        return "pub_update_success";//跳转到更新成功页面
    }

    public String del() {//删除报价信息
        quotationService.delete(quotationNO);//调用业务逻辑组件完成删除
        return "pub_del_success";//跳转到删除成功页面
    }

    /**
     * 查询报价
     *
     * @return
     */
    public String query() {
        return "query";
    }

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

package com.sanqing.action;

import com.sanqing.po.Customer;
import com.sanqing.po.Product;
import com.sanqing.po.Quotation;
import com.sanqing.service.QuotationService;
import com.sanqing.util.PageView;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller("quotationAction")
@Scope("prototype")
public class QuotationAction extends BaseAction {
    @Resource
    private QuotationService quotationService;    //报价业务逻辑组件
    // 报价编号
    private String quotationNO;
    // 报价人
    private String quotationMan;
    // 报价时间
    private Date quotationtime;
    // 其他
    private String otherInfo;
    //产品
    private Product product;
    private String productName;
    //客户
    private Customer customer;
    private String customerName;


    @Override
    public String execute() throws Exception {
        PageView<Quotation> pageView =
                new PageView<Quotation>(5, getPage());//设置分页信息
        StringBuffer jpql = new StringBuffer("");//初始化条件查询语句
        List<Object> params = new ArrayList<Object>();//初始化查询参数
        if ("true".equals(getQuery())) {    //按条件查询
            if (quotationNO != null && !"".equals(quotationNO)) {//根据报价编号进行查询
                if (params.size() > 0) jpql.append(" and ");
                jpql.append(" o.quotationNO = ?").append(params.size() + 1);
                params.add(quotationNO);
            }
            if (quotationMan != null && !"".equals(quotationMan)) {//根据报价人进行查询
                if (params.size() > 0) jpql.append(" and ");
                jpql.append(" o.quotationMan like ?").append(params.size() + 1);
                params.add(quotationMan);
            }
            if (otherInfo != null && !"".equals(otherInfo)) {//根据其他信息进行查询
                if (params.size() > 0) jpql.append(" and ");
                jpql.append(" o.otherInfo like ?").append(params.size() + 1);
                params.add(otherInfo);
            }
            if (productName != null && !"".equals(productName)) {//根据产品名称进行查询
                if (params.size() > 0) jpql.append(" and ");
                jpql.append(" o.product.productName like ?").append(params.size() + 1);
                params.add(productName);
            }
            if (customerName != null && !"".equals(customerName)) {//根据客户名称进行查询
                if (params.size() > 0) jpql.append(" and ");
                jpql.append(" o.customer.customerName = ?").append(params.size() + 1);
                params.add(customerName);
            }
            pageView.setQueryResult(quotationService.getScrollData(pageView.getFirstResult(),
                    pageView.getMaxresult(), jpql.toString(), params.toArray()));//按条件进行查询
        } else {
            pageView.setQueryResult(quotationService.getScrollData(
                    pageView.getFirstResult(), pageView.getMaxresult()));//查询所有记录
        }
        HttpServletRequest request = ServletActionContext.getRequest();//获得request对象
        request.setAttribute("pageView", pageView);//保存到request范围
        return this.SUCCESS;
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

    public Date getQuotationtime() {
        return quotationtime;
    }

    public void setQuotationtime(Date quotationtime) {
        this.quotationtime = quotationtime;
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

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
}

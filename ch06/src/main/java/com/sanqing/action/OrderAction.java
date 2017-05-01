package com.sanqing.action;

import com.sanqing.po.Customer;
import com.sanqing.po.Order;
import com.sanqing.po.Product;
import com.sanqing.service.OrderService;
import com.sanqing.util.PageView;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller("orderAction")
@Scope("prototype")
public class OrderAction extends BaseAction {

    @Resource
    private OrderService orderService;
    /* 订单编码 */
    private String orderNO;
    /* 客户名称 */
    private Customer customer;
    private String customerName;
    /* 产品名称 */
    private Product product;
    private String productname;
    /* 产品数量 */
    private int quantity;
    /* 订单的时间 */
    private Date ordetTime;
    /* 其他信息*/
    private String otherInfo;

    @Override
    public String execute() throws Exception {
        HttpServletRequest request = ServletActionContext.getRequest();
        PageView<Order> pageView = new PageView<Order>(5, getPage());
        StringBuffer jpql = new StringBuffer("");
        List<Object> params = new ArrayList<Object>();
        if ("true".equals(getQuery())) {
            if (orderNO != null && !"".equals(orderNO)) {
                if (params.size() > 0) jpql.append(" and ");
                jpql.append(" o.orderNO = ?").append(params.size() + 1);
                params.add(orderNO);
            }
            if (customerName != null && !"".equals(customerName)) {
                if (params.size() > 0) jpql.append(" and ");
                jpql.append(" o.customer.customerName like ?").append(params.size() + 1);
                params.add("%" + customerName + "%");
            }
            if (productname != null && !"".equals(productname)) {
                if (params.size() > 0) jpql.append(" and ");
                jpql.append(" o.product.productname like ?").append(params.size() + 1);
                params.add("%" + productname + "%");
            }
            if (quantity > 0) {
                if (params.size() > 0) jpql.append(" and ");
                jpql.append(" o.quantity = ?").append(params.size() + 1);
                params.add(quantity);
            }
            if (otherInfo != null && !"".equals(otherInfo)) {
                if (params.size() > 0) jpql.append(" and ");
                jpql.append(" o.otherInfo like ?").append(params.size() + 1);
                params.add("%" + otherInfo + "%");
            }
            pageView.setQueryResult(orderService.getScrollData(pageView.getFirstResult(), pageView.getMaxresult(), jpql.toString(), params.toArray()));
        } else {
            pageView.setQueryResult(orderService.getScrollData(pageView.getFirstResult(), pageView.getMaxresult()));
        }
        request.setAttribute("pageView", pageView);
        return this.SUCCESS;
    }

    public String getOrderNO() {
        return orderNO;
    }

    public void setOrderNO(String orderNO) {
        this.orderNO = orderNO;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Date getOrdetTime() {
        return ordetTime;
    }

    public void setOrdetTime(Date ordetTime) {
        this.ordetTime = ordetTime;
    }

    public String getOtherInfo() {
        return otherInfo;
    }

    public void setOtherInfo(String otherInfo) {
        this.otherInfo = otherInfo;
    }

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }


}

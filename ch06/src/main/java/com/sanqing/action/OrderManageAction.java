package com.sanqing.action;

import com.sanqing.po.Customer;
import com.sanqing.po.Order;
import com.sanqing.po.Product;
import com.sanqing.service.CustomerService;
import com.sanqing.service.OrderService;
import com.sanqing.service.ProductService;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

@Controller("orderManageAction")
@Scope("prototype")
public class OrderManageAction extends BaseAction {

    @Resource
    private OrderService orderService;
    @Resource
    private CustomerService customerService;
    @Resource
    private ProductService productService;

    /* 订单编码 */
    private String orderNO;
    /* 客户名称 */
    private Customer customer;
    /* 产品名称 */
    private Product product;
    /* 产品数量 */
    private int quantity;
    /* 订单的时间 */
    private Date orderTime;
    /* 其他信息*/
    private String otherInfo;

    /**
     * 添加订单输入界面
     *
     * @return
     */
    public String addUI() {
        HttpServletRequest request = ServletActionContext.getRequest();
        List<Customer> customers = customerService.getScrollData().getResultlist();
        List<Product> products = productService.getScrollData().getResultlist();
        request.setAttribute("customers", customers);
        request.setAttribute("products", products);
        return "add";
    }

    /**
     * 添加订单
     *
     * @return
     */
    public String add() {
        Order order = new Order();
        order.setCustomer(customer);
        order.setOrderNO(orderNO);
        order.setOrderTime(orderTime);
        order.setOtherInfo(otherInfo);
        order.setProduct(product);
        order.setQuantity(quantity);
        orderService.save(order);
        return "pub_add_success";
    }

    /**
     * 修改订单输入界面
     *
     * @return
     */
    public String updateUI() {
        HttpServletRequest request = ServletActionContext.getRequest();
        List<Customer> customers = customerService.getScrollData().getResultlist();
        List<Product> products = productService.getScrollData().getResultlist();
        Order order = orderService.find(orderNO);
        request.setAttribute("customers", customers);
        request.setAttribute("products", products);
        request.setAttribute("order", order);
        return "update";
    }

    /**
     * 修改订单
     *
     * @return
     */
    public String update() {
        Order order = new Order();
        order.setCustomer(customer);
        order.setOrderNO(orderNO);
        order.setOrderTime(orderTime);
        order.setOtherInfo(otherInfo);
        order.setProduct(product);
        order.setQuantity(quantity);
        orderService.update(order);
        return "pub_update_success";
    }

    /**
     * 删除订单
     *
     * @return
     */
    public String del() {
        orderService.delete(orderNO);
        return "pub_del_success";
    }

    /**
     * 查询订单
     *
     * @return
     */
    public String query() {
        HttpServletRequest request = ServletActionContext.getRequest();
        List<Customer> customers = customerService.getScrollData().getResultlist();
        List<Product> products = productService.getScrollData().getResultlist();
        request.setAttribute("customers", customers);
        request.setAttribute("products", products);
        return "query";
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

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    public String getOtherInfo() {
        return otherInfo;
    }

    public void setOtherInfo(String otherInfo) {
        this.otherInfo = otherInfo;
    }
}

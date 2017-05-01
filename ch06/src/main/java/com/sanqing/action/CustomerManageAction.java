package com.sanqing.action;

import com.sanqing.po.Customer;
import com.sanqing.service.CustomerService;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 客户管理action
 */
@Controller("customerManageAction")
@Scope("prototype")
public class CustomerManageAction extends BaseAction {
    @Resource
    private CustomerService customerService;//注入客户业务逻辑组件
    private String customerNO;                //客户编号
    private String customerName;            //客户名称
    private String phone;                    //客户电话
    private String address;                    //客户地址
    private String relationman;                //客户联系人
    private String otherInfo;                //其他信息

    public String addUI() {                    //新增客户输入界面
        return "add";
    }

    public String add() {                //新增客户方法
        Customer customer = new Customer();
        customer.setAddress(address);
        customer.setCustomerNO(customerNO);
        customer.setCustomerName(customerName);
        customer.setOtherInfo(otherInfo);
        customer.setPhone(phone);
        customer.setRelationman(relationman);
        customerService.save(customer);
        return "pub_add_success";
    }

    public String updateUI() {    //该方法用来跳转到客户信息更新表单
        Customer customer = customerService.find(customerNO);//查询该客户编号对应的客户
        HttpServletRequest request = ServletActionContext.getRequest();//获得request对象
        request.setAttribute("customer", customer);//将客户信息保存在request范围
        return "update";//跳转到客户信息更新表单页
    }

    public String update() {
        Customer customer = new Customer();
        customer.setAddress(address);
        customer.setCustomerNO(customerNO);
        customer.setCustomerName(customerName);
        customer.setOtherInfo(otherInfo);
        customer.setPhone(phone);
        customer.setRelationman(relationman);
        customerService.update(customer);//更新客户信息
        return "pub_update_success";//跳转到更新成功页面
    }

    /**
     * 删除客户
     *
     * @return
     */
    public String del() {        //删除客户信息
        customerService.delete(customerNO);//根据客户编号删除客户
        return "pub_del_success";//跳转到删除成功页
    }

    /**
     * 查询客户信息
     *
     * @return
     */
    public String query() {
        return "query";
    }

    public String getCustomerNO() {
        return customerNO;
    }

    public void setCustomerNO(String customerNO) {
        this.customerNO = customerNO;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRelationman() {
        return relationman;
    }

    public void setRelationman(String relationman) {
        this.relationman = relationman;
    }

    public String getOtherInfo() {
        return otherInfo;
    }

    public void setOtherInfo(String otherInfo) {
        this.otherInfo = otherInfo;
    }


}

package com.sanqing.action;

import com.sanqing.po.Customer;
import com.sanqing.service.CustomerService;
import com.sanqing.util.PageView;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * 客户显示action
 */
@Controller("customerAction")
@Scope("prototype")
public class CustomerAction extends BaseAction {
    @Resource
    private CustomerService customerService;//注入客户业务组件
    private String customerNO;                //客户编号
    private String customerName;            //客户名称
    private String phone;                    //客户电话
    private String address;                    //客户地址
    private String relationman;                //客户联系人
    private String otherInfo;                //其他

    @Override
    public String execute() throws Exception {
        PageView<Customer> pageView =
                new PageView<Customer>(5, getPage());//设置分页信息，每页显示5条记录
        StringBuffer jpql = new StringBuffer("");            //初始化条件查询语句
        List<Object> params = new ArrayList<Object>();        //初始化查询参数列表
        if ("true".equals(getQuery())) {                        //是否为条件查询
            if (customerNO != null && !"".equals(customerNO.trim())) {//根据客户编号进行查询
                if (params.size() > 0) jpql.append(" and ");
                jpql.append(" o.customerNO like ?").append(params.size() + 1);
                params.add("%" + customerNO + "%");
            }
            if (customerName != null && !"".equals(customerName.trim())) {//根据客户名称查询
                if (params.size() > 0) jpql.append(" and ");
                jpql.append(" o.customerName like ?").append(params.size() + 1);
                params.add("%" + customerName + "%");
            }
            if (phone != null && !"".equals(phone.trim())) {//根据电话号码查询
                if (params.size() > 0) jpql.append(" and ");
                jpql.append(" o.phone like ?").append(params.size() + 1);
                params.add("%" + phone + "%");
            }
            if (address != null && !"".equals(address.trim())) {//根据地址查询
                if (params.size() > 0) jpql.append(" and ");
                jpql.append(" o.address like ?").append(params.size() + 1);
                params.add("%" + address + "%");
            }
            if (relationman != null && !"".equals(relationman.trim())) {//根据联系人查询
                if (params.size() > 0) jpql.append(" and ");
                jpql.append(" o.relationman like ?").append(params.size() + 1);
                params.add("%" + relationman + "%");
            }
            if (otherInfo != null && !"".equals(otherInfo.trim())) {//根据其他信息查询
                if (params.size() > 0) jpql.append(" and ");
                jpql.append(" o.otherInfo like ?").append(params.size() + 1);
                params.add("%" + otherInfo + "%");
            }
            pageView.setQueryResult(customerService.getScrollData(
                    pageView.getFirstResult(), pageView.getMaxresult(),
                    jpql.toString(), params.toArray()));//按条件查询
        } else {
            pageView.setQueryResult(customerService.getScrollData
                    (pageView.getFirstResult(), pageView.getMaxresult()));//查询所有记录
        }
        HttpServletRequest request = ServletActionContext.getRequest();//获得request对象
        request.setAttribute("pageView", pageView);//保存到request范围
        return this.SUCCESS;
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

package com.sanqing.service;

import com.sanqing.dao.IOrdersDAO;
import com.sanqing.dao.IOrdersLineDAO;
import com.sanqing.util.PageResult;

import java.util.Map;

public class OrdersService {
    private IOrdersDAO ordersService = null;
    private IOrdersLineDAO ordersLineService = null;

    public IOrdersLineDAO getOrdersLineService() {
        return ordersLineService;
    }

    private IOrdersDAO orders = null;

    public void setOrdersLineService(IOrdersLineDAO ordersLineService) {
        this.ordersLineService = ordersLineService;
    }

    public IOrdersDAO getOrdersService() {
        return ordersService;
    }

    public void setOrdersService(IOrdersDAO ordersService) {
        this.ordersService = ordersService;
    }

    // 查询客户订单
    public PageResult findAllOrders(Map paramMap) {
        return ordersService.findAll(paramMap);
    }

    // 查看订单明细
    public PageResult findOrdersLine(Map paramMap) {
        return ordersLineService.findAll(paramMap);
    }

    public IOrdersDAO getOrders() {
        return orders;
    }

    public void setOrders(IOrdersDAO orders) {
        this.orders = orders;
    }

}

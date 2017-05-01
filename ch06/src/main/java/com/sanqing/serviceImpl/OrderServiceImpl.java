package com.sanqing.serviceImpl;

import com.sanqing.dao.DaoSupport;
import com.sanqing.po.Order;
import com.sanqing.service.OrderService;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class OrderServiceImpl extends DaoSupport<Order> implements OrderService {

    @Override
    public void save(Object entity) {
        Order order = (Order) entity;
        order.setOrderTime(new Date());
        super.save(order);
    }

    @Override
    public void update(Object entity) {
        Order order = (Order) entity;
        order.setOrderTime(new Date());
        super.update(entity);
    }
}

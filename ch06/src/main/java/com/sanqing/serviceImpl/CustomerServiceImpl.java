package com.sanqing.serviceImpl;

import com.sanqing.dao.DaoSupport;
import com.sanqing.po.Customer;
import com.sanqing.service.CustomerService;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl extends DaoSupport<Customer> implements
        CustomerService {

}

package com.sanqing.serviceImpl;

import com.sanqing.dao.DaoSupport;
import com.sanqing.po.Product;
import com.sanqing.service.ProductService;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl extends DaoSupport<Product> implements ProductService {

}

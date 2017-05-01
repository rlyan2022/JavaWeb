package com.sanqing.serviceImpl;

import com.sanqing.dao.DaoSupport;
import com.sanqing.po.ProductType;
import com.sanqing.service.ProductTypeService;
import org.springframework.stereotype.Service;

@Service
public class ProductTypeServiceImpl extends DaoSupport<ProductType> implements
        ProductTypeService {
}

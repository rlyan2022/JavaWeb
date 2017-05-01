package com.sanqing.serviceImpl;

import com.sanqing.dao.DaoSupport;
import com.sanqing.po.Quotation;
import com.sanqing.service.QuotationService;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class QuotationServiceImpl extends DaoSupport<Quotation> implements
        QuotationService {

    @Override
    public void save(Object entity) {
        Quotation quotation = (Quotation) entity;
        quotation.setQuotationTime(new Date());
        super.save(entity);
    }

    @Override
    public void update(Object entity) {
        Quotation quotation = (Quotation) entity;
        quotation.setQuotationTime(new Date());
        super.update(entity);
    }

}

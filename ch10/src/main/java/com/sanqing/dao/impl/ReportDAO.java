package com.sanqing.dao.impl;

import com.sanqing.hb.HibernateSessionFactory;
import com.sanqing.util.PageResult;
import org.hibernate.Query;
import org.hibernate.Session;

public class ReportDAO {
    //查询客户构成
    public PageResult finCustStructure(String type) {
        PageResult pgr = new PageResult();
        Session session = HibernateSessionFactory.getSession();
        String hql = null;
        if (type == "cstLevel") {
            hql = "select (select b.dictItem from BasDict b where dictType='客户等级' and b.dictId=c.basDictByCustLevel.dictId) as 等级,count(*) from CstCustomer c group by c.basDictByCustLevel.dictId";
        } else if (type == "cstSatisfy") {
            hql = "select c.custSatisfy,count(*) from CstCustomer c group by c.custSatisfy";
        } else if (type == "cstCredit") {
            hql = "select c.custCredit,count(*) from CstCustomer c group by c.custCredit";
        }
        Query query = session.createQuery(hql);
        pgr.setData(query.list());
        pgr.setRowCount(query.list().size());
        return pgr;
    }
}

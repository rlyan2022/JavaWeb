package com.sanqing.dao.impl;

import com.sanqing.dao.CommodityClassDAO;
import com.sanqing.page.Page;
import com.sanqing.po.CommodityClass;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import java.util.List;

public class CommodityClassDAOImpl extends HibernateDaoSupport implements CommodityClassDAO {
    public List<CommodityClass> findAll(Page page) {
        Session session = getSession();        //获得session对象
        Query query = session.createQuery("from CommodityClass");//执行查询
        query.setFirstResult(page.getBeginIndex());    //设置查询起点位置
        query.setMaxResults(page.getEveryPage());    //设置查询最大值
        return query.list();        //返回查询结果
    }

    public void save(CommodityClass commodityclass) {
        getHibernateTemplate().save(commodityclass);//保存实体
    }

    public int findAllCount() {
        List<CommodityClass> commodityClasses =
                getHibernateTemplate().find("from CommodityClass");//查询所有记录
        return commodityClasses.size();//返回记录数
    }

    public List<CommodityClass> findAll() {
        return getHibernateTemplate().find("from CommodityClass");
    }

    public CommodityClass findByID(int commodityClassID) {
        return (CommodityClass) getHibernateTemplate().load(CommodityClass.class, commodityClassID);
    }
}

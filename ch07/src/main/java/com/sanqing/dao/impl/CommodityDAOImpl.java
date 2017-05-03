package com.sanqing.dao.impl;

import com.sanqing.dao.CommodityDAO;
import com.sanqing.page.Page;
import com.sanqing.po.Commodity;
import com.sanqing.po.CommodityClass;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import java.util.List;

public class CommodityDAOImpl extends HibernateDaoSupport implements CommodityDAO {
    public List<Commodity> findAll(Page page) {
        Session session = getSession();        //获得session对象
        Query query = session.createQuery("from Commodity");//执行查询
        query.setFirstResult(page.getBeginIndex());    //设置查询起点位置
        query.setMaxResults(page.getEveryPage());    //设置查询最大值
        return query.list();        //返回查询结果
    }

    public void save(Commodity commodity) {
        getHibernateTemplate().save(commodity);//保存实体
    }

    public int findAllCount() {
        List<CommodityClass> commodities =
                getHibernateTemplate().find("from Commodity");//查询所有记录
        return commodities.size();//返回记录数
    }

    public void delete(int commodityID) {    //删除商品信息
        Commodity commodity = (Commodity) getHibernateTemplate().
                load(Commodity.class, commodityID);//加载对象
        getHibernateTemplate().delete(commodity);//删除对象
    }

    public List<Commodity> findByTime(int num) {
        Session session = getSession();        //获得session对象
        Query query = session.createQuery
                ("from Commodity order by regTime desc");//执行查询
        query.setFirstResult(0);    //设置查询起点位置
        query.setMaxResults(num);    //设置查询最大值
        return query.list();        //返回查询结果
    }

    public Commodity findByID(int commodityID) {
        return (Commodity) getHibernateTemplate().load(Commodity.class, commodityID);
    }

    public List<Commodity> findByClass(CommodityClass commodityClass, Page page) {
        Session session = getSession();        //获得session对象
        Query query = session.createQuery("from Commodity where commodityClass =:commodityClass");//执行查询
        query.setEntity("commodityClass", commodityClass);
        query.setFirstResult(page.getBeginIndex());    //设置查询起点位置
        query.setMaxResults(page.getEveryPage());    //设置查询最大值
        return query.list();        //返回查询结果
    }

    public int findAllCount(CommodityClass commodityClass) {
        Session session = getSession();        //获得session对象
        Query query = session.createQuery("from Commodity where commodityClass =:commodityClass");//执行查询
        query.setEntity("commodityClass", commodityClass);
        return query.list().size();//返回记录数
    }
}

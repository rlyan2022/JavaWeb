package com.demo.hibernate.dao;

import com.demo.hibernate.beans.Notice;
import com.demo.struts.util.Pager;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import java.util.List;

public class NoticeDAO extends HibernateDaoSupport implements INoticeDAO {

    /**
     * when have pageSize and pageNo parameters
     */
    public Pager findPager(final int pageSize,
                           final int pageNo) {
        Session session = getHibernateTemplate().getSessionFactory().openSession();

        // set query condition
        Criteria criteria = session.createCriteria(Notice.class);

        // get total count
        int rowCount = ((Integer) criteria.setProjection(
                Projections.rowCount()).uniqueResult()).intValue();
        criteria.setProjection(null);

        // get current page list
        int startIndex = pageSize * (pageNo - 1);
        criteria.addOrder(Order.desc("sendtime"));
        criteria.setFirstResult(startIndex);
        criteria.setMaxResults(pageSize);
        List result = criteria.list();

        session.close();

        return new Pager(pageSize, pageNo, rowCount, result);
    }

    public Notice findById(String id) {
        return (Notice) getHibernateTemplate().get(Notice.class,
                new Integer(id));
    }

    public void insert(Notice notice) {
        getHibernateTemplate().save(notice);
    }

    public void update(Notice notice) {
        getHibernateTemplate().update(notice);
    }

    public void delete(String id) {
        Object p = getHibernateTemplate().load(Notice.class, new Integer(id));
        getHibernateTemplate().delete(p);
    }

}

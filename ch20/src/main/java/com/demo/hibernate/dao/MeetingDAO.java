package com.demo.hibernate.dao;

import com.demo.hibernate.beans.Meeting;
import com.demo.struts.util.Pager;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import java.util.List;

public class MeetingDAO extends HibernateDaoSupport implements IMeetingDAO {

    /**
     * when have pageSize and pageNo parameters
     */
    public Pager findPager(final int pageSize,
                           final int pageNo) {
        Session session = getHibernateTemplate().getSessionFactory().openSession();

        // set query condition
        Criteria criteria = session.createCriteria(Meeting.class);

        // get total count
        int rowCount = ((Integer) criteria.setProjection(
                Projections.rowCount()).uniqueResult()).intValue();
        criteria.setProjection(null);

        // get current page list
        int startIndex = pageSize * (pageNo - 1);
        criteria.addOrder(Order.desc("starttime"));
        criteria.addOrder(Order.desc("endtime"));
        criteria.setFirstResult(startIndex);
        criteria.setMaxResults(pageSize);
        List result = criteria.list();

        session.close();

        return new Pager(pageSize, pageNo, rowCount, result);
    }

    public Meeting findById(String id) {
        return (Meeting) getHibernateTemplate().get(Meeting.class,
                new Integer(id));
    }

    public void insert(Meeting meeting) {
        getHibernateTemplate().save(meeting);
    }

    public void update(Meeting meeting) {
        getHibernateTemplate().update(meeting);
    }

    public void delete(String id) {
        Object p = getHibernateTemplate().load(Meeting.class, new Integer(id));
        getHibernateTemplate().delete(p);
    }

}

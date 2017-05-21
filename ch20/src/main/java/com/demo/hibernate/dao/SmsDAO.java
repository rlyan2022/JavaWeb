package com.demo.hibernate.dao;

import com.demo.hibernate.beans.Sms;
import com.demo.struts.util.Pager;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import java.util.List;

public class SmsDAO extends HibernateDaoSupport implements ISmsDAO {

    /**
     * when have pageSize and pageNo parameters
     */
    public Pager findPagerByUsername(final String username, final int pageSize,
                                     final int pageNo) {
        Session session = getHibernateTemplate().getSessionFactory().openSession();

        // set query condition
        Criteria criteria = session.createCriteria(Sms.class);
        criteria.add(Restrictions.eq("username", username));

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

    public Sms findById(String id) {
        return (Sms) getHibernateTemplate().get(Sms.class,
                new Integer(id));
    }

    public void insert(Sms sms) {
        getHibernateTemplate().save(sms);
    }

    public void update(Sms sms) {
        getHibernateTemplate().update(sms);
    }

    public void delete(String id) {
        Object p = getHibernateTemplate().load(Sms.class, new Integer(id));
        getHibernateTemplate().delete(p);
    }

}

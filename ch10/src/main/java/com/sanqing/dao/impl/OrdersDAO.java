package com.sanqing.dao.impl;

import com.sanqing.dao.IOrdersDAO;
import com.sanqing.po.Orders;
import com.sanqing.struts.form.OrdersForm;
import com.sanqing.util.PageResult;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.LockMode;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.impl.CriteriaImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class OrdersDAO extends HibernateDaoSupport implements IOrdersDAO {
    private static final Log log = LogFactory.getLog(OrdersDAO.class);

    protected void initDao() {
        // do nothing
    }

    public PageResult findAll(Map paramMap) {
        PageResult pgr = new PageResult();
        // 参数
        String start = (String) paramMap.get("start");
        String limit = (String) paramMap.get("limit");
        String odrCustomer = (String) paramMap.get("odrCustomer");
        try {
            Criteria c = getSession().createCriteria(Orders.class);
            // 条件
            c.add(Expression.eq("odrCustomer", odrCustomer));
            // 总记录条数
            Projection entityProjection = ((CriteriaImpl) c).getProjection();
            c.setProjection(Projections.rowCount()).uniqueResult();
            int rowCount = ((Number) c.uniqueResult()).intValue();
            pgr.setRowCount(rowCount);
            c.setProjection(entityProjection);
            // 分页
            if (start != null) {
                c.setFirstResult(Integer.parseInt(start));
            }
            if (limit != null) {
                c.setMaxResults(Integer.parseInt(limit));
            }
            // 把数据放到一个Form中
            List<Orders> list = c.list();
            List<OrdersForm> fList = new ArrayList<OrdersForm>();
            OrdersForm ordersForm = null;
            for (Orders orders : list) {
                ordersForm = new OrdersForm();
                ordersForm.setOdrId(orders.getOdrId());
                ordersForm.setOdrCustomer(orders.getOdrCustomer());
                ordersForm.setOdrDate(orders.getOdrDate());
                ordersForm.setOdrAddr(orders.getOdrAddr());
                ordersForm.setOdrStatus(orders.getOdrStatus());
                fList.add(ordersForm);
            }
            pgr.setData(fList);
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
        return pgr;
    }

    public void save(Orders transientInstance) {
        log.debug("saving Orders instance");
        try {
            getHibernateTemplate().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }

    /*
     * (non-Javadoc)
     *
     */
    public void delete(Orders persistentInstance) {
        log.debug("deleting Orders instance");
        try {
            getHibernateTemplate().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }

    /*
     * (non-Javadoc)
     *
     * @see com.sanqing.daoIOrdersDAO#findById(java.lang.Long)
     */
    public Orders findById(Long id) {
        log.debug("getting Orders instance with id: " + id);
        try {
            Orders instance = (Orders) getHibernateTemplate().get(
                    "com.sanqing.po.Orders", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }

    /*
     * (non-Javadoc)
     *
     */
    public List findByExample(Orders instance) {
        log.debug("finding Orders instance by example");
        try {
            List results = getHibernateTemplate().findByExample(instance);
            log.debug("find by example successful, result size: "
                    + results.size());
            return results;
        } catch (RuntimeException re) {
            log.error("find by example failed", re);
            throw re;
        }
    }

    /*
     * (non-Javadoc)
     *
     * @see com.sanqing.daoIOrdersDAO#findByProperty(java.lang.String,
     *      java.lang.Object)
     */
    public List findByProperty(String propertyName, Object value) {
        log.debug("finding Orders instance with property: " + propertyName
                + ", value: " + value);
        try {
            String queryString = "from Orders as model where model."
                    + propertyName + "= ?";
            return getHibernateTemplate().find(queryString, value);
        } catch (RuntimeException re) {
            log.error("find by property name failed", re);
            throw re;
        }
    }

	/*
     * (non-Javadoc)
	 * 
	 * @see com.sanqing.daoIOrdersDAO#findByOdrCustomer(java.lang.Object)
	 */


    public List findByOdrCustomer(Object odrCustomer, Object odrDate) {

        Criteria c = getSession().createCriteria(Orders.class);
        c.add(Expression.like("odrCustomer", "%" + odrCustomer + "%"));
        c.add(Expression.like("odrDate", "%" + odrDate + "%"));
        return c.list();

    }

    /*
     * (non-Javadoc)
     *
     * @see com.sanqing.daoIOrdersDAO#findByOdrAddr(java.lang.Object)
     */
    public List findByOdrAddr(Object odrAddr) {
        return findByProperty(ODR_ADDR, odrAddr);
    }

    /*
     * (non-Javadoc)
     *
     * @see com.sanqing.daoIOrdersDAO#findByOdrStatus(java.lang.Object)
     */
    public List findByOdrStatus(Object odrStatus) {
        return findByProperty(ODR_STATUS, odrStatus);
    }

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sanqing.daoIOrdersDAO#findAll()
	 */

    /*
     * (non-Javadoc)
     *
     * @see com.sanqing.daoIOrdersDAO#merge(com.sanqing.po.Orders)
     */
    public Orders merge(Orders detachedInstance) {
        log.debug("merging Orders instance");
        try {
            Orders result = (Orders) getHibernateTemplate().merge(
                    detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    /*
     * (non-Javadoc)
     *
     */
    public void attachDirty(Orders instance) {
        log.debug("attaching dirty Orders instance");
        try {
            getHibernateTemplate().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

    /*
     * (non-Javadoc)
     *
     * @see com.sanqing.daoIOrdersDAO#attachClean(com.sanqing.po.Orders)
     */
    public void attachClean(Orders instance) {
        log.debug("attaching clean Orders instance");
        try {
            getHibernateTemplate().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

    public static IOrdersDAO getFromApplicationContext(ApplicationContext ctx) {
        return (IOrdersDAO) ctx.getBean("OrdersDAO");
    }

    public List findAll() {
        // TODO Auto-generated method stub
        return null;
    }
}
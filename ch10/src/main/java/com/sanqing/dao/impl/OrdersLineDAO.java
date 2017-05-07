package com.sanqing.dao.impl;

import com.sanqing.dao.ICstCustomerDAO;
import com.sanqing.dao.IOrdersDAO;
import com.sanqing.dao.IOrdersLineDAO;
import com.sanqing.hb.HibernateSessionFactory;
import com.sanqing.po.OrdersLine;
import com.sanqing.struts.form.CustProfferForm;
import com.sanqing.struts.form.OrdersLineForm;
import com.sanqing.util.PageResult;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.impl.CriteriaImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class OrdersLineDAO extends HibernateDaoSupport implements
        IOrdersLineDAO {
    private static final Log log = LogFactory.getLog(OrdersLineDAO.class);
    IOrdersDAO ordersDao = null;
    ICstCustomerDAO customerDao = null;

    public ICstCustomerDAO getCustomerDao() {
        return customerDao;
    }

    public void setCustomerDao(ICstCustomerDAO customerDao) {
        this.customerDao = customerDao;
    }

    public IOrdersDAO getIOrdersDao() {
        return ordersDao;
    }

    public void setOrdersDao(IOrdersDAO ordersDao) {
        this.ordersDao = ordersDao;
    }

    protected void initDao() {
        // do nothing
    }

    public PageResult findAll(Map paramMap) {
        PageResult pgr = new PageResult();
        // 参数
        String start = (String) paramMap.get("start");
        String limit = (String) paramMap.get("limit");
        String oddOrderId = (String) paramMap.get("oddOrderId");
        try {
            Criteria c = getSession().createCriteria(OrdersLine.class);
            // 条件
            c.add(Expression.eq("orders.odrId", Long.parseLong(oddOrderId)));
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
            List<OrdersLine> list = c.list();
            List<OrdersLineForm> fList = new ArrayList<OrdersLineForm>();
            OrdersLineForm ordersLineForm = null;
            for (OrdersLine ordersLine : list) {
                ordersLineForm = new OrdersLineForm();
                ordersLineForm.setProdName(ordersLine.getProduct()
                        .getProdName());
                ordersLineForm.setOddCount(ordersLine.getOddCount());
                ordersLineForm.setOddUnit(ordersLine.getOddUnit());
                ordersLineForm.setOddPrice(ordersLine.getOddPrice());
                Double total = ordersLine.getOddPrice()
                        * ordersLine.getOddCount();
                ordersLineForm.setProdTotal(total);
                fList.add(ordersLineForm);
            }
            pgr.setData(fList);
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
        return pgr;
    }

    public PageResult findCustProffer(Map paramMap) {
        PageResult pgr = new PageResult();
        Session session = HibernateSessionFactory.getSession();
        String start = (String) paramMap.get("start");
        String limit = (String) paramMap.get("limit");
        String odrCustomer = (String) paramMap.get("odrCustomer");
        String odrDate = (String) paramMap.get("odrDate");
        // List<CstCustomer> list = customerDao.findAllCustomer();
        // String[] str = new String[list.size()];
        // for (int i = 0; i < str.length; i++) {
        // str[i] = list.get(i).getCustName();
        // System.out.println(str[i]);
        // }
        String hql = "select count(ol) from OrdersLine ol where 1=1 ";
        String hql2 = "select ol.orders.odrCustomer,"
                + " sum(ol.oddPrice) from OrdersLine ol where 1=1 ";

        String str = "";
        boolean flag = false;
        if (odrCustomer != null && !odrCustomer.equals("")) {
            str += "and ol.orders.odrCustomer like '%" + odrCustomer + "%' ";
            flag = true;
        }
        if (odrDate != null && !odrDate.equals("")) {
            str += "and ol.orders.odrDate like'%" + odrDate + "%' ";
        }

        hql += str + "group by ol.orders.odrCustomer";
        hql2 += str + "group by ol.orders.odrCustomer";
        Query query = session.createQuery(hql);
        // 查询记录数
        pgr.setRowCount(query.list().size());
        // 查询记录
        Session session2 = getSession();
        Query query2 = session2.createQuery(hql2);
        if (StringUtils.isNotEmpty(start)) {
            query2.setFirstResult(Integer.parseInt(start));
        }
        if (StringUtils.isNotEmpty(limit)) {
            query2.setMaxResults(Integer.parseInt(limit));
        }

        List<Object[]> list = query2.list();
        List<CustProfferForm> fList = new ArrayList<CustProfferForm>();
        CustProfferForm custProfferForm = null;
        for (Object[] obj : list) {
            custProfferForm = new CustProfferForm();
            custProfferForm.setOdrCustomer(obj[0].toString());
            custProfferForm.setAdrTotal(Double.parseDouble(obj[1].toString()));
            fList.add(custProfferForm);
        }
        pgr.setData(fList);
        return pgr;
    }

    /*
     * (non-Javadoc)
     *
     */
    public void save(OrdersLine transientInstance) {
        log.debug("saving OrdersLine instance");
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
    public void delete(OrdersLine persistentInstance) {
        log.debug("deleting OrdersLine instance");
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
     * @see com.sanqing.daoIOrdersLineDAO#findById(java.lang.Long)
     */
    public OrdersLine findById(Long id) {
        log.debug("getting OrdersLine instance with id: " + id);
        try {
            OrdersLine instance = (OrdersLine) getHibernateTemplate().get(
                    "com.sanqing.po.OrdersLine", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }

    /*
     * (non-Javadoc)
     *
     * @see com.sanqing.daoIOrdersLineDAO#findByExample(com.sanqing.po.OrdersLine)
     */
    public List findByExample(OrdersLine instance) {
        log.debug("finding OrdersLine instance by example");
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
     * @see com.sanqing.daoIOrdersLineDAO#findByProperty(java.lang.String,
     *      java.lang.Object)
     */
    public List findByProperty(String propertyName, Object value) {
        log.debug("finding OrdersLine instance with property: " + propertyName
                + ", value: " + value);
        try {
            String queryString = "from OrdersLine as model where model."
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
     * @see com.sanqing.daoIOrdersLineDAO#findByOddCount(java.lang.Object)
     */
    public List findByOddCount(Object oddCount) {
        return findByProperty(ODD_COUNT, oddCount);
    }

    /*
     * (non-Javadoc)
     *
     * @see com.sanqing.daoIOrdersLineDAO#findByOddUnit(java.lang.Object)
     */
    public List findByOddUnit(Object oddUnit) {
        return findByProperty(ODD_UNIT, oddUnit);
    }

    /*
     * (non-Javadoc)
     *
     * @see com.sanqing.daoIOrdersLineDAO#findByOddPrice(java.lang.Object)
     */
    public List findByOddPrice(Object oddPrice) {
        return findByProperty(ODD_PRICE, oddPrice);
    }

    /*
     * (non-Javadoc)
     *
     * @see com.sanqing.daoIOrdersLineDAO#findAll()
     */
    public List findAll() {
        log.debug("finding all OrdersLine instances");
        try {
            String queryString = "from OrdersLine";
            return getHibernateTemplate().find(queryString);
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
    }

    /*
     * (non-Javadoc)
     *
     * @see com.sanqing.daoIOrdersLineDAO#merge(com.sanqing.po.OrdersLine)
     */
    public OrdersLine merge(OrdersLine detachedInstance) {
        log.debug("merging OrdersLine instance");
        try {
            OrdersLine result = (OrdersLine) getHibernateTemplate().merge(
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
    public void attachDirty(OrdersLine instance) {
        log.debug("attaching dirty OrdersLine instance");
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
     * @see com.sanqing.daoIOrdersLineDAO#attachClean(com.sanqing.po.OrdersLine)
     */
    public void attachClean(OrdersLine instance) {
        log.debug("attaching clean OrdersLine instance");
        try {
            getHibernateTemplate().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

    public static IOrdersLineDAO getFromApplicationContext(
            ApplicationContext ctx) {
        return (IOrdersLineDAO) ctx.getBean("OrdersLineDAO");
    }

}
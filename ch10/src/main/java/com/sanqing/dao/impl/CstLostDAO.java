package com.sanqing.dao.impl;

import com.sanqing.dao.ICstCustomerDAO;
import com.sanqing.dao.ICstLostDAO;
import com.sanqing.hb.HibernateSessionFactory;
import com.sanqing.po.CstCustomer;
import com.sanqing.po.CstLost;
import com.sanqing.service.CstLostService;
import com.sanqing.struts.form.CstLostForm;
import com.sanqing.struts.form.CstLostReptForm;
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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CstLostDAO extends HibernateDaoSupport implements ICstLostDAO {
    private static final Log log = LogFactory.getLog(CstLostDAO.class);
    private ICstCustomerDAO cstDao = null;
    private CstLostService cstLostService = null;

    public ICstCustomerDAO getCstDao() {
        return cstDao;
    }

    public void setCstDao(ICstCustomerDAO cstDao) {
        this.cstDao = cstDao;
    }

    public CstLostService getCstLostService() {
        return cstLostService;
    }

    public void setCstLostService(CstLostService cstLostService) {
        this.cstLostService = cstLostService;
    }

    protected void initDao() {
        // do nothing
    }

    // 查询出即将流失的客户
    public void lostCustomer() {
        try {
            // 首先从订单表中查询出最后一次下单距今有六个月的客户
            Session session = HibernateSessionFactory.getSession();
            String sql = "select * from (select odr_customer,(select top 1 odr_date from jb_sale.dbo.orders as o2 where o2.odr_customer=o.odr_customer order by odr_date desc) as date1 from jb_sale.dbo.orders as o group by odr_customer) as otable where ((getdate()-otable.date1)>'1900-06-01 00:00:00')";
            Query qu = session.createSQLQuery(sql);
            List list = qu.list();
            for (int i = 0; i < list.size(); i++) {
                Object[] lost = (Object[]) list.get(i);
                System.out.println("要流失的客户为:" + lost[0]);
                // 然后根据查询出来的客户名称再到客户表中查询出客户信息
                List<CstCustomer> cstCustomer = cstDao.findByCustName(lost[0]);
                // 最后把即将流失的客户信息放入到客户流失表中
                CstLost cstLost = null;
                for (CstCustomer cst : cstCustomer) {
                    cstLost = new CstLost();
                    CstCustomer customer = new CstCustomer();
                    customer.setCustNo(cst.getCustNo());
                    cstLost.setCstCustomer(customer);
                    cstLost.setLstCustManagerName(cst.getCstManager()
                            .getManName());
                    SimpleDateFormat sf = new SimpleDateFormat("yyyy-mm-dd");
                    try {
                        cstLost.setLstLastOrderDate(sf
                                .parse(lost[1].toString()));
                    } catch (ParseException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    System.out.println("流失客户的日期：" + lost[1].toString());
                    cstLost.setLstStatus("1");
                    // 如果该客户的信息已添加到流失表则没有必要再次添加
                    if (findByProperty("cstCustomer.custNo", cst.getCustNo())
                            .size() == 0) {
                        cstLostService.add(cstLost);
                    }
                }
            }
        } catch (RuntimeException re) {
            throw re;
        }
    }

    // 查询流失客户的信息
    public PageResult findAll(Map paramMap) {
        lostCustomer();
        PageResult pgr = new PageResult();
        // 获得参数
        String start = (String) paramMap.get("start");
        String limit = (String) paramMap.get("limit");
        String lstCstCustomer = (String) paramMap.get("lstCstCustomer");
        String lstCustManagerName = (String) paramMap.get("lstCustManagerName");
        String lstStatus = (String) paramMap.get("lstStatus");

        try {
            Criteria c = getSession().createCriteria(CstLost.class);
            // 条件
            // c.add(Expression.eq("cstCustomer.custNo", lkmCustNo));
            // 总记录条数
            Projection entityProjection = ((CriteriaImpl) c).getProjection();
            c.setProjection(Projections.rowCount()).uniqueResult();
            int rowCount = ((Number) c.uniqueResult()).intValue();
            pgr.setRowCount(rowCount);
            c.setProjection(entityProjection);
            // 分页
            if (start != null) {
                c.setFirstResult(new Integer(start));
            }
            if (limit != null) {
                c.setMaxResults(new Integer(limit));
            }
            // 把查询到的数据放到List<CstLostForm>里面,方便在CstLostAction中转换成json数据
            List<CstLost> list = c.list();
            List<CstLostForm> fList = new ArrayList<CstLostForm>();
            CstLostForm cstLostForm = null;
            for (CstLost cstLost : list) {
                cstLostForm = new CstLostForm();
                cstLostForm.setLstId(cstLost.getLstId());
                cstLostForm.setLstCustName(cstLost.getCstCustomer()
                        .getCustName());
                cstLostForm.setLstCustManagerName(cstLost.getCstCustomer()
                        .getCstManager().getManName());
                cstLostForm.setLstLastOrderDate(cstLost.getLstLastOrderDate());
                cstLostForm.setLstLostDate(cstLost.getLstLostDate());
                cstLostForm.setLstDelay(cstLost.getLstDelay());
                cstLostForm.setLstReason(cstLost.getLstReason());
                cstLostForm.setLstStatus(cstLost.getLstStatus());
                fList.add(cstLostForm);
            }
            pgr.setData(fList);
        } catch (RuntimeException re) {
            throw re;
        }
        return pgr;
    }

    public PageResult findCstLostRept(Map paramMap) {
        PageResult PageResult = new PageResult();
        // 参数
        String start = (String) paramMap.get("start");
        String limit = (String) paramMap.get("limit");
        String custName = (String) paramMap.get("custName");
        // 按客户名称查找出客户编号，再通过主外键关系查出相应数据
        List<CstCustomer> lists = cstDao.findByCustName(custName);
        String[] str = new String[lists.size()];
        for (int i = 0; i < str.length; i++) {
            str[i] = lists.get(i).getCustNo();
            System.out.println(str[i]);
        }

        // 按客户经理查出相应的数据
        String lstCustManagerName = (String) paramMap.get("lstCustManagerName");
        System.out.println("第一条" + start);
        System.out.println("最后一条" + limit);
        try {
            Criteria c = getSession().createCriteria(CstLost.class);
            // 条件

            if (str.length > 0) {
                c.add(Expression.in("cstCustomer.custNo", str));
            }
            if (StringUtils.isNotEmpty(lstCustManagerName)) {
                c.add(Expression.like("lstCustManagerName", "%"
                        + lstCustManagerName + "%"));
            }
            List<CstLost> list = c.list();
            // 总条数
            Projection entityProjection = ((CriteriaImpl) c).getProjection();
            c.setProjection(Projections.rowCount()).uniqueResult();
            int rowCount = ((Number) c.uniqueResult()).intValue();
            PageResult.setRowCount(rowCount);
            c.setProjection(entityProjection);

            // 分页
            if (start != null) {
                c.setFirstResult(Integer.parseInt(start));
            }
            if (limit != null) {
                c.setMaxResults(Integer.parseInt(limit));
            }
            List<CstLostReptForm> fList = new ArrayList<CstLostReptForm>();
            CstLostReptForm cstLostReptForm = null;
            for (CstLost lose : list) {
                cstLostReptForm = new CstLostReptForm();
                cstLostReptForm.setLstId(lose.getLstId());
                cstLostReptForm
                        .setCustName(lose.getCstCustomer().getCustName());
                cstLostReptForm.setLstCustManagerName(lose
                        .getLstCustManagerName());
                cstLostReptForm.setLstLostDate(lose.getLstLastOrderDate());
                cstLostReptForm.setLstReason(lose.getLstReason());
                fList.add(cstLostReptForm);
            }
            PageResult.setData(fList);

        } catch (RuntimeException re) {
            log.error("find all failed", re);
            re.printStackTrace();
            throw re;
        }
        return PageResult;
    }

    /*
     * (non-Javadoc)
     *
     */
    public void save(CstLost transientInstance) {
        log.debug("saving CstLost instance");
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
    public void delete(CstLost persistentInstance) {
        log.debug("deleting CstLost instance");
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
     */
    public CstLost findById(Long id) {
        log.debug("getting CstLost instance with id: " + id);
        try {
            CstLost instance = (CstLost) getHibernateTemplate().get(
                    "com.sanqing.po.CstLost", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }

    public List findByProperty(String propertyName, Object value) {
        log.debug("finding CstLost instance with property: " + propertyName
                + ", value: " + value);
        try {
            String queryString = "from CstLost as model where model."
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
     */
    public List findByExample(CstLost instance) {
        log.debug("finding CstLost instance by example");
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
     */
    public List findByLstCustManagerName(Object lstCustManagerName) {
        return findByProperty(LST_CUST_MANAGER_NAME, lstCustManagerName);
    }

    /*
     * (non-Javadoc)
     *
     */
    public List findByLstDelay(Object lstDelay) {
        return findByProperty(LST_DELAY, lstDelay);
    }

    /*
     * (non-Javadoc)
     *
     */
    public List findByLstReason(Object lstReason) {
        return findByProperty(LST_REASON, lstReason);
    }

    /*
     * (non-Javadoc)
     *
     */
    public List findByLstStatus(Object lstStatus) {
        return findByProperty(LST_STATUS, lstStatus);
    }

    /*
     * (non-Javadoc)
     *
     */
    public CstLost merge(CstLost detachedInstance) {
        log.debug("merging CstLost instance");
        try {
            CstLost result = (CstLost) getHibernateTemplate().merge(
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
    public void attachDirty(CstLost instance) {
        log.debug("attaching dirty CstLost instance");
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
     */
    public void attachClean(CstLost instance) {
        log.debug("attaching clean CstLost instance");
        try {
            getHibernateTemplate().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

    public static ICstLostDAO getFromApplicationContext(ApplicationContext ctx) {
        return (ICstLostDAO) ctx.getBean("CstLostDAO");
    }

}
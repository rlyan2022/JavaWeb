package com.sanqing.dao.impl;

import com.sanqing.dao.ICstServiceDAO;
import com.sanqing.po.CstCustomer;
import com.sanqing.po.CstService;
import com.sanqing.struts.form.CstServiceForm;
import com.sanqing.struts.form.CstServiceReptForm;
import com.sanqing.util.PageResult;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.LockMode;
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

public class CstServiceDAO extends HibernateDaoSupport implements
        ICstServiceDAO {
    private static final Log log = LogFactory.getLog(CstServiceDAO.class);
    CstCustomerDAO cstcustomer = null;

    public CstCustomerDAO getCstcustomer() {
        return cstcustomer;
    }

    public void setCstcustomer(CstCustomerDAO cstcustomer) {
        this.cstcustomer = cstcustomer;
    }

    protected void initDao() {
        // do nothing
    }

    public PageResult findAll(Map paramMap) {
        PageResult pgr = new PageResult();
        // 参数
        String start = (String) paramMap.get("start");
        String limit = (String) paramMap.get("limit");
        String custName = (String) paramMap.get("custName");
        List<CstCustomer> customer = cstcustomer.findByCustName(custName);

        // 根据客户名称查询出客户服务信息
        String[] custNo = new String[customer.size()];
        for (int i = 0; i < custNo.length; i++) {
            custNo[i] = customer.get(i).getCustNo();
            System.out.println("客户编号为：" + custNo[i]);
        }

        String svrType = (String) paramMap.get("svrType");
        String svrTitle = (String) paramMap.get("svrTitle");
        String svrCreateDate = (String) paramMap.get("svrCreateDate");
        String svrStatus = (String) paramMap.get("svrStatus");

        try {
            Criteria c = getSession().createCriteria(CstService.class);
            c.add(Expression.eq("svrStatus", svrStatus));
            if (custNo.length > 0) {
                c.add(Expression.in("cstCustomer.custNo", custNo));// 客户查询
            }
            if (StringUtils.isNotEmpty(svrTitle)) {
                c.add(Expression.like("svrTitle", "%" + svrTitle + "%"));// 概要
            }
            if (StringUtils.isNotEmpty(svrType)) {
                c.add(Expression.eq("svrType", svrType));
            }
            if (StringUtils.isNotEmpty(svrCreateDate)) {
                c.add(Expression.like("svrCreateDate", "%" + svrCreateDate
                        + "%"));
            }
            System.out.println("状态:" + svrStatus);
            System.out.println("服务类型：" + svrType);
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
            // 把查询到的数据放到List<cstServiceForm>里面,方便在CustomerAction中转换成json数据
            List<CstService> list = c.list();

            List<CstServiceForm> fList = new ArrayList<CstServiceForm>();
            CstServiceForm serviceForm = null;

            for (CstService cstService : list) {
                serviceForm = new CstServiceForm();
                serviceForm.setSvrId(cstService.getSvrId());
                System.out.println("+customer is +"
                        + cstService.getCstCustomer());
                if (null != cstService.getCstCustomer()) {
                    serviceForm.setSvrCustNo(cstService.getCstCustomer()
                            .getCustNo());
                    serviceForm.setSvrCustName(cstService.getCstCustomer()
                            .getCustName());
                }
                serviceForm.setSvrType(cstService.getSvrType());
                serviceForm.setSvrTitle(cstService.getSvrTitle());
                serviceForm.setSvrStatus(cstService.getSvrStatus());
                serviceForm.setSvrRequest(cstService.getSvrRequest());
                serviceForm.setSvrCreateBy(cstService.getSvrCreateBy());
                serviceForm.setSvrCreateDate(cstService.getSvrCreateDate());
                serviceForm.setSvrDueTo(cstService.getSvrDueTo());
                serviceForm.setSvrDueDate(cstService.getSvrDealDate());
                serviceForm.setSvrDeal(cstService.getSvrDeal());
                serviceForm.setSvrDealBy(cstService.getSvrDealBy());
                serviceForm.setSvrDealDate(cstService.getSvrDealDate());
                serviceForm.setSvrResult(cstService.getSvrResult());
                serviceForm.setSvrSatisfy(cstService.getSvrSatisfy());
                fList.add(serviceForm);
            }
            pgr.setData(fList);

        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
        return pgr;
    }

    // 查询出客户服务处理的年份
    public PageResult findSvrDate(Map paramMap) {
        PageResult pgr = new PageResult();
        String start = (String) paramMap.get("start");
        String limit = (String) paramMap.get("limit");
        Session session = getSession();
        String hql = "select s.svrCreateDate,count(*) from CstService s group by s.svrCreateDate";
        List<Object[]> list = session.createQuery(hql).setFirstResult(
                Integer.parseInt(start)).setMaxResults(Integer.parseInt(limit))
                .list();
        List<CstServiceReptForm> fList = new ArrayList<CstServiceReptForm>();
        for (int i = 0; i < list.size(); i++) {
            Object[] obj = (Object[]) list.get(i);
            CstServiceReptForm cstSvrReptForm = new CstServiceReptForm();
            cstSvrReptForm.setSvrDate(obj[0].toString());
            fList.add(cstSvrReptForm);
        }
        pgr.setData(fList);
        return pgr;
    }

    // 查询客户分析
    public PageResult findServiceRept(String svrDate) {
        System.out.println("年份:" + svrDate);
        PageResult pgr = new PageResult();
        Session session = getSession();
        String hql = "select s.svrType,count(*) from CstService s group by s.svrType";
        if (svrDate != "" && svrDate != null) {
            hql = hql + " where s.svrCreateDate LIKE'%" + svrDate + "%'";
        }
        System.out.println(hql);
        List<Object[]> list = session.createQuery(hql).list();
        List<CstServiceReptForm> fList = new ArrayList<CstServiceReptForm>();
        for (int i = 0; i < list.size(); i++) {
            Object[] obj = (Object[]) list.get(i);
            CstServiceReptForm cstSvrReptForm = new CstServiceReptForm();
            cstSvrReptForm.setSvrType(obj[0].toString());
            cstSvrReptForm.setSvrCount(Integer.parseInt(obj[1].toString()));
            fList.add(cstSvrReptForm);
        }
        pgr.setData(fList);
        return pgr;
    }

    public List findBySvrType(Object svrType) {
        return findByProperty(SVR_TYPE, svrType);
    }

    public void save(CstService transientInstance) {
        log.debug("saving CstService instance");
        try {
            getHibernateTemplate().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }

    public void delete(CstService persistentInstance) {
        log.debug("deleting CstService instance");
        try {
            getHibernateTemplate().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }

    public CstService findById(Long id) {
        log.debug("getting CstService instance with id: " + id);
        try {
            CstService instance = (CstService) getHibernateTemplate().get(
                    "com.sanqing.po.CstService", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }

    public List findByExample(CstService instance) {
        log.debug("finding CstService instance by example");
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
    public List findByProperty(String propertyName, Object value) {
        log.debug("finding CstService instance with property: " + propertyName
                + ", value: " + value);
        try {
            String queryString = "from CstService as model where model."
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

    /*
     * (non-Javadoc)
     *
     */
    public List findBySvrTitle(Object svrTitle) {
        return findByProperty(SVR_TITLE, svrTitle);
    }

    /*
     * (non-Javadoc)
     *
     */
    public List findBySvrCustNo(Object svrCustNo) {
        return findByProperty(SVR_CUST_NO, svrCustNo);
    }

    /*
     * (non-Javadoc)
     *
     */
    public List findBySvrStatus(Object svrStatus) {
        return findByProperty(SVR_STATUS, svrStatus);
    }

    /*
     * (non-Javadoc)
     *
     */
    public List findBySvrRequest(Object svrRequest) {
        return findByProperty(SVR_REQUEST, svrRequest);
    }

    /*
     * (non-Javadoc)
     *
     */
    public List findBySvrCreateBy(Object svrCreateBy) {
        return findByProperty(SVR_CREATE_BY, svrCreateBy);
    }

    /*
     * (non-Javadoc)
     *
     */
    public List findBySvrDueTo(Object svrDueTo) {
        return findByProperty(SVR_DUE_TO, svrDueTo);
    }

    /*
     * (non-Javadoc)
     *
     */
    public List findBySvrDeal(Object svrDeal) {
        return findByProperty(SVR_DEAL, svrDeal);
    }

    /*
     * (non-Javadoc)
     *
     */
    public List findBySvrDealBy(Object svrDealBy) {
        return findByProperty(SVR_DEAL_BY, svrDealBy);
    }

    /*
     * (non-Javadoc)
     *
     */
    public List<Object> findBySvrResult(Object svrResult) {
        return findByProperty(SVR_RESULT, svrResult);
    }

    /*
     * (non-Javadoc)
     *
     */
    public List findBySvrSatisfy(Object svrSatisfy) {
        return findByProperty(SVR_SATISFY, svrSatisfy);
    }

	/*
	 * (non-Javadoc)
	 * 
	 */

    /*
     * (non-Javadoc)
     *
     */
    public CstService merge(CstService detachedInstance) {
        log.debug("merging CstService instance");
        try {
            CstService result = (CstService) getHibernateTemplate().merge(
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
    public void attachDirty(CstService instance) {
        log.debug("attaching dirty CstService instance");
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
    public void attachClean(CstService instance) {
        log.debug("attaching clean CstService instance");
        try {
            getHibernateTemplate().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

    public static ICstServiceDAO getFromApplicationContext(
            ApplicationContext ctx) {
        return (ICstServiceDAO) ctx.getBean("CstServiceDAO");
    }
}
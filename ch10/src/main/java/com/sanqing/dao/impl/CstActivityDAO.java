package com.sanqing.dao.impl;

import com.sanqing.dao.ICstActivityDAO;
import com.sanqing.po.CstActivity;
import com.sanqing.struts.form.CstActivityForm;
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

public class CstActivityDAO extends HibernateDaoSupport implements
        ICstActivityDAO {
    private static final Log log = LogFactory.getLog(CstActivityDAO.class);

    protected void initDao() {
        // do nothing
    }

    // 查询客户交往记录
    public PageResult findAll(Map paramMap) {
        PageResult pgr = new PageResult();
        // 参数
        String start = (String) paramMap.get("start");
        String limit = (String) paramMap.get("limit");
        String atvCustNo = (String) paramMap.get("atvCustNo");
        try {
            Criteria c = getSession().createCriteria(CstActivity.class);
            // 条件
            c.add(Expression.eq("cstCustomer.custNo", atvCustNo));
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
            List<CstActivity> list = c.list();
            List<CstActivityForm> fList = new ArrayList<CstActivityForm>();
            CstActivityForm activityForm = null;
            for (CstActivity activity : list) {
                activityForm = new CstActivityForm();
                activityForm.setAtvId(activity.getAtvId());
                activityForm
                        .setAtvCustNo(activity.getCstCustomer().getCustNo());
                activityForm.setAtvDate(activity.getAtvDate());
                activityForm.setAtvPlace(activity.getAtvPlace());
                activityForm.setAtvTitle(activity.getAtvTitle());
                activityForm.setAtvDesc(activity.getAtvDesc());
                activityForm.setAtvRemark(activity.getAtvRemark());
                fList.add(activityForm);
            }
            pgr.setData(fList);
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
        return pgr;
    }

    public void save(CstActivity transientInstance) {
        log.debug("saving CstActivity instance");
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
    public void delete(CstActivity persistentInstance) {
        log.debug("deleting CstActivity instance");
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
    public CstActivity findById(Long id) {
        log.debug("getting CstActivity instance with id: " + id);
        try {
            CstActivity instance = (CstActivity) getHibernateTemplate().get(
                    "com.sanqing.po.CstActivity", id);
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
    public List findByExample(CstActivity instance) {
        log.debug("finding CstActivity instance by example");
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
        log.debug("finding CstActivity instance with property: " + propertyName
                + ", value: " + value);
        try {
            String queryString = "from CstActivity as model where model."
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
    public List findByAtvPlace(Object atvPlace) {
        return findByProperty(ATV_PLACE, atvPlace);
    }

    /*
     * (non-Javadoc)
     *
     */
    public List findByAtvTitle(Object atvTitle) {
        return findByProperty(ATV_TITLE, atvTitle);
    }

    /*
     * (non-Javadoc)
     *
     */
    public List findByAtvDesc(Object atvDesc) {
        return findByProperty(ATV_DESC, atvDesc);
    }

	/*
     * (non-Javadoc)
	 * 
	 */

    /*
     * (non-Javadoc)
     *
     */
    public CstActivity merge(CstActivity detachedInstance) {
        log.debug("merging CstActivity instance");
        try {
            CstActivity result = (CstActivity) getHibernateTemplate().merge(
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
    public void attachDirty(CstActivity instance) {
        log.debug("attaching dirty CstActivity instance");
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
    public void attachClean(CstActivity instance) {
        log.debug("attaching clean CstActivity instance");
        try {
            getHibernateTemplate().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

    public static ICstActivityDAO getFromApplicationContext(
            ApplicationContext ctx) {
        return (ICstActivityDAO) ctx.getBean("CstActivityDAO");
    }
}
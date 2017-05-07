package com.sanqing.dao.impl;

import com.sanqing.dao.ICstLinkmanDAO;
import com.sanqing.po.CstLinkman;
import com.sanqing.struts.form.CstLinkmanForm;
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

public class CstLinkmanDAO extends HibernateDaoSupport implements
        ICstLinkmanDAO {
    private static final Log log = LogFactory.getLog(CstLinkmanDAO.class);

    protected void initDao() {
        // do nothing
    }

    // 查询客户联系人信息
    public PageResult findAll(Map paramMap) {
        PageResult pgr = new PageResult();
        // 获得参数
        String start = (String) paramMap.get("start");
        String limit = (String) paramMap.get("limit");
        String lkmCustNo = (String) paramMap.get("lkmCustNo");
        System.out.println("要查询" + lkmCustNo + "的联系人");
        try {
            Criteria c = getSession().createCriteria(CstLinkman.class);
            // 条件
            c.add(Expression.eq("cstCustomer.custNo", lkmCustNo));
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
            //把查询到的数据放到List<CstLinkmanForm>里面,方便在CustomerAction中转换成json数据
            List<CstLinkman> list = c.list();
            List<CstLinkmanForm> fLinkman = new ArrayList<CstLinkmanForm>();
            CstLinkmanForm linkmanForm = null;
            for (CstLinkman linkman : list) {
                linkmanForm = new CstLinkmanForm();
                linkmanForm.setLkmId(linkman.getLkmId());
                linkmanForm.setLkmName(linkman.getLkmName());
                linkmanForm.setLkmSex(linkman.getLkmSex());
                linkmanForm.setLkmPostion(linkman.getLkmPostion());
                linkmanForm.setLkmTel(linkman.getLkmTel());
                linkmanForm.setLkmMobile(linkman.getLkmMobile());
                linkmanForm.setLkmMemo(linkman.getLkmMemo());
                fLinkman.add(linkmanForm);
            }
            pgr.setData(fLinkman);
        } catch (RuntimeException re) {
            throw re;
        }
        return pgr;
    }

    /*
     * (non-Javadoc)
     *
     */
    public void save(CstLinkman transientInstance) {
        log.debug("saving CstLinkman instance");
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
    public void delete(CstLinkman persistentInstance) {
        log.debug("deleting CstLinkman instance");
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
    public CstLinkman findById(Long id) {
        log.debug("getting CstLinkman instance with id: " + id);
        try {
            CstLinkman instance = (CstLinkman) getHibernateTemplate().get(
                    "com.sanqing.po.CstLinkman", id);
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
    public List findByExample(CstLinkman instance) {
        log.debug("finding CstLinkman instance by example");
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
        log.debug("finding CstLinkman instance with property: " + propertyName
                + ", value: " + value);
        try {
            String queryString = "from CstLinkman as model where model."
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
    public List findByLkmName(Object lkmName) {
        return findByProperty(LKM_NAME, lkmName);
    }

    /*
     * (non-Javadoc)
     *
     */
    public List findByLkmSex(Object lkmSex) {
        return findByProperty(LKM_SEX, lkmSex);
    }

    /*
     * (non-Javadoc)
     *
     */
    public List findByLkmPostion(Object lkmPostion) {
        return findByProperty(LKM_POSTION, lkmPostion);
    }

    /*
     * (non-Javadoc)
     *
     */
    public List findByLkmTel(Object lkmTel) {
        return findByProperty(LKM_TEL, lkmTel);
    }

    /*
     * (non-Javadoc)
     *
     */
    public List findByLkmMobile(Object lkmMobile) {
        return findByProperty(LKM_MOBILE, lkmMobile);
    }

    /*
     * (non-Javadoc)
     *
     */
    public List findByLkmMemo(Object lkmMemo) {
        return findByProperty(LKM_MEMO, lkmMemo);
    }

	/*
     * (non-Javadoc)
	 * 
	 */

    /*
     * (non-Javadoc)
     *
     */
    public CstLinkman merge(CstLinkman detachedInstance) {
        log.debug("merging CstLinkman instance");
        try {
            CstLinkman result = (CstLinkman) getHibernateTemplate().merge(
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
    public void attachDirty(CstLinkman instance) {
        log.debug("attaching dirty CstLinkman instance");
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
    public void attachClean(CstLinkman instance) {
        log.debug("attaching clean CstLinkman instance");
        try {
            getHibernateTemplate().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

    public static ICstLinkmanDAO getFromApplicationContext(
            ApplicationContext ctx) {
        return (ICstLinkmanDAO) ctx.getBean("CstLinkmanDAO");
    }

    public List findAll() {
        // TODO Auto-generated method stub
        return null;
    }
}
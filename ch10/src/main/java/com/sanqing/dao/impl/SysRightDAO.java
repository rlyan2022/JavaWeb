package com.sanqing.dao.impl;

import com.sanqing.dao.ISysRightDAO;
import com.sanqing.po.SysRight;
import com.sanqing.struts.form.RightForm;
import com.sanqing.util.PageResult;
import com.sanqing.util.RightList;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.LockMode;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.impl.CriteriaImpl;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SysRightDAO extends HibernateDaoSupport implements ISysRightDAO {
    private static final Log log = LogFactory.getLog(SysRightDAO.class);

    protected void initDao() {
    }

    public PageResult findRight(Map paramMap) {
        PageResult pgr = new PageResult();
        // 参数
        String start = (String) paramMap.get("start");
        String limit = (String) paramMap.get("limit");
        try {
            Criteria c = getSession().createCriteria(SysRight.class);
            // 总条数
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
            List<SysRight> list = c.list();
            List<RightForm> fList = new ArrayList<RightForm>();
            RightForm rightForm = null;
            for (SysRight right : list) {
                rightForm = new RightForm();
                rightForm.setRightCode(right.getRightCode());
                rightForm.setRightText(right.getRightText());
                rightForm.setRightUrl(right.getRightUrl());
                fList.add(rightForm);
            }
            pgr.setData(fList);
        } catch (RuntimeException re) {
            throw re;
        }
        return pgr;
    }

    public List<SysRight> findAll() {
        List<SysRight> rights = null;
        try {
            Criteria c = getSession().createCriteria(SysRight.class);
            rights = c.list();
        } catch (RuntimeException re) {
            throw re;
        }
        return rights;
    }

    //找出所有的权限
    public RightList findAllRight() {
        RightList rl = new RightList();
        try {
            Criteria c = getSession().createCriteria(SysRight.class);
            List<SysRight> list = c.list();
            List<RightForm> fList = new ArrayList<RightForm>();
            RightForm rightForm = null;
            for (SysRight right : list) {
                rightForm = new RightForm();
                rightForm.setRightCode(right.getRightCode());
                rightForm.setRightText(right.getRightText());
                fList.add(rightForm);
            }
            rl.setAllRight(fList);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return rl;
    }

    public void save(SysRight transientInstance) {
        log.debug("saving SysRight instance");
        try {
            getHibernateTemplate().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }

    public void delete(SysRight persistentInstance) {
        log.debug("deleting SysRight instance");
        try {
            getHibernateTemplate().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }

    public List<SysRight> findByrightText(String name) {
        Criteria ctr = getSession().createCriteria(SysRight.class);
        ctr.add(Expression.like("rightText", "%" + name + "%"));
        return ctr.list();
        // 根据用户名
    }

    public SysRight findById(Long id) {
        log.debug("getting SysRight instance with id: " + id);
        try {
            SysRight instance = (SysRight) getHibernateTemplate().get(
                    "com.sanqing.po.SysRight", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }

    public List findByExample(SysRight instance) {
        log.debug("finding SysRight instance by example");
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

    public List findByProperty(String propertyName, Object value) {
        log.debug("finding SysRight instance with property: " + propertyName
                + ", value: " + value);
        try {
            String queryString = "from SysRight as model where model."
                    + propertyName + "= ?";
            return getHibernateTemplate().find(queryString, value);
        } catch (RuntimeException re) {
            log.error("find by property name failed", re);
            throw re;
        }
    }

    public List findByRightType(Object rightType) {
        return findByProperty(RIGHT_TYPE, rightType);
    }

    public List findByRightText(Object rightText) {
        return findByProperty(RIGHT_TEXT, rightText);
    }

    public List findByRightUrl(Object rightUrl) {
        return findByProperty(RIGHT_URL, rightUrl);
    }

    public List findByRightTip(Object rightTip) {
        return findByProperty(RIGHT_TIP, rightTip);
    }

    public SysRight merge(SysRight detachedInstance) {
        log.debug("merging SysRight instance");
        try {
            SysRight result = (SysRight) getHibernateTemplate().merge(
                    detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(SysRight instance) {
        log.debug("attaching dirty SysRight instance");
        try {
            getHibernateTemplate().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

    public void attachClean(SysRight instance) {
        log.debug("attaching clean SysRight instance");
        try {
            getHibernateTemplate().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
}
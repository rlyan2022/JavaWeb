package com.sanqing.dao.impl;

import com.sanqing.dao.ISalPlanDAO;
import com.sanqing.po.SalChance;
import com.sanqing.po.SalPlan;
import com.sanqing.struts.form.ChanceForm;
import com.sanqing.struts.form.PlanForm;
import com.sanqing.util.PageResult;
import org.apache.commons.lang.StringUtils;
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

public class SalPlanDAO extends HibernateDaoSupport implements ISalPlanDAO {
    private static final Log log = LogFactory.getLog(SalPlanDAO.class);

    protected void initDao() {
        // do nothing
    }

    public PageResult findAll(Map paramMap) {
        PageResult pgr = new PageResult();
        // 接收参数。。。。
        String start = (String) paramMap.get("start");// 第一条数据。。。
        String limit = (String) paramMap.get("limit");// 最后一条数据。。。。
        String custName = (String) paramMap.get("chcCustName");
        String custTitle = (String) paramMap.get("chcTitle");
        String custLinkman = (String) paramMap.get("chcLinkman");
        // System.out.println("第一条数据:" + start);
        // System.out.println("最后一条数据:" + limit);
        try {
            Criteria c = getSession().createCriteria(SalChance.class);
            // 查询条件。。。。
            c.add(Expression.gt("chcStatus", "1"));
            if (StringUtils.isNotEmpty(custName)) {
                c.add(Expression.like("chcCustName", "%" + custName + "%"));
            }
            if (StringUtils.isNotEmpty(custTitle)) {
                c.add(Expression.like("chcTitle", "%" + custTitle + "%"));
            }
            if (StringUtils.isNotEmpty(custLinkman)) {
                c.add(Expression.like("chcLinkman", "%" + custLinkman + "%"));
            }
            // 数据总条数。。。。
            Projection entityProjection = ((CriteriaImpl) c).getProjection();
            c.setProjection(Projections.rowCount()).uniqueResult();
            int rowCount = ((Number) c.uniqueResult()).intValue();
            pgr.setRowCount(rowCount);
            c.setProjection(entityProjection);
            // 分页处理。。。
            if (start != null) {
                c.setFirstResult(Integer.parseInt(start));
            }
            if (limit != null) {
                c.setMaxResults(Integer.parseInt(limit));
            }
            List<SalChance> list = c.list();
            List<ChanceForm> fList = new ArrayList<ChanceForm>();
            ChanceForm chanceForm = null;
            for (SalChance chance : list) {
                chanceForm = new ChanceForm();
                chanceForm.setChcId(chance.getChcId());
                chanceForm.setChcSource(chance.getChcSource());
                chanceForm.setChcCustName(chance.getChcCustName());
                chanceForm.setChcTitle(chance.getChcTitle());
                chanceForm.setChcRate(chance.getChcRate());
                chanceForm.setChcLinkman(chance.getChcLinkman());
                chanceForm.setChcTel(chance.getChcTel());
                chanceForm.setChcDesc(chance.getChcDesc());
                chanceForm.setChcCreateBy(chance.getChcCreateBy());
                chanceForm.setChcCreateDate(chance.getChcCreateDate());
                chanceForm.setChcDueTo(chance.getChcDueTo());
                chanceForm.setChcDueDate(chance.getChcDueDate());
                chanceForm.setChcStatus(chance.getChcStatus());
                fList.add(chanceForm);
            }
            pgr.setData(fList);

        } catch (RuntimeException e) {
            log.error("aaaaaaaa", e);
            throw e;
        }
        return pgr;
    }

    public PageResult findPlanTodo(Map paramMap) {
        PageResult pgr = new PageResult();
        // 接收参数。。。。
        String start = (String) paramMap.get("start");// 第一条数据。。。
        String limit = (String) paramMap.get("limit");// 最后一条数据。。。。
        String chcId = (String) paramMap.get("chcId");
        System.out.println("销售机会编号：" + chcId);
        try {
            Criteria c = getSession().createCriteria(SalPlan.class);
            // 查询条件。。。。
            c.add(Expression.like("salChance.chcId", Long.parseLong(chcId)));
            // 数据总条数。。。。
            Projection entityProjection = ((CriteriaImpl) c).getProjection();
            c.setProjection(Projections.rowCount()).uniqueResult();
            int rowCount = ((Number) c.uniqueResult()).intValue();
            pgr.setRowCount(rowCount);
            c.setProjection(entityProjection);
            // 分页处理。。。
            if (start != null) {
                c.setFirstResult(Integer.parseInt(start));
            }
            if (limit != null) {
                c.setMaxResults(Integer.parseInt(limit));
            }
            // 转JSON数据
            List<SalPlan> list = c.list();
            List<PlanForm> fList = new ArrayList<PlanForm>();
            PlanForm planForm = null;
            for (SalPlan salPlan : list) {
                planForm = new PlanForm();
                planForm.setPlaId(salPlan.getPlaId());
                planForm.setPlaDate(salPlan.getPlaDate());
                planForm.setPlaTodo(salPlan.getPlaTodo());
                planForm.setPlaResult(salPlan.getPlaResult());
                fList.add(planForm);
            }
            pgr.setData(fList);
        } catch (RuntimeException e) {
            log.error("aaaaaaaa", e);
            throw e;
        }
        return pgr;
    }

    /*
     * (non-Javadoc)
     *
     */
    public void save(SalPlan transientInstance) {
        log.debug("saving SalPlan instance");
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
    public void delete(SalPlan persistentInstance) {
        log.debug("deleting SalPlan instance");
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
    public SalPlan findById(Long id) {
        log.debug("getting SalPlan instance with id: " + id);
        try {
            SalPlan instance = (SalPlan) getHibernateTemplate().get(
                    "com.sanqing.po.SalPlan", id);
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
    public List findByExample(SalPlan instance) {
        log.debug("finding SalPlan instance by example");
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
        log.debug("finding SalPlan instance with property: " + propertyName
                + ", value: " + value);
        try {
            String queryString = "from SalPlan as model where model."
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
    public List findByPlaTodo(Object plaTodo) {
        return findByProperty(PLA_TODO, plaTodo);
    }

    /*
     * (non-Javadoc)
     *
     */
    public List findByPlaResult(Object plaResult) {
        return findByProperty(PLA_RESULT, plaResult);
    }

    public SalPlan merge(SalPlan detachedInstance) {
        log.debug("merging SalPlan instance");
        try {
            SalPlan result = (SalPlan) getHibernateTemplate().merge(
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
    public void attachDirty(SalPlan instance) {
        log.debug("attaching dirty SalPlan instance");
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
    public void attachClean(SalPlan instance) {
        log.debug("attaching clean SalPlan instance");
        try {
            getHibernateTemplate().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

}
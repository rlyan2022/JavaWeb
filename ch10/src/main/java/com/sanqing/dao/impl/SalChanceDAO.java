package com.sanqing.dao.impl;

import com.sanqing.dao.ISalChanceDAO;
import com.sanqing.po.SalChance;
import com.sanqing.struts.form.ChanceForm;
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
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SalChanceDAO extends HibernateDaoSupport implements ISalChanceDAO {
    private static final Log log = LogFactory.getLog(SalChanceDAO.class);

    protected void initDao() {

    }

    public PageResult findAll1() {
        return null;
    }

    public PageResult findAll(Map paramMap) {
        PageResult PageResult = new PageResult();
        // 参数
        String start = (String) paramMap.get("start");
        String limit = (String) paramMap.get("limit");
        String chcCustName = (String) paramMap.get("chcCustName");
        String custTitle = (String) paramMap.get("chcTitle");
        String custLinkman = (String) paramMap.get("chcLinkman");
        System.out.println("第一条" + start);
        System.out.println("最后一条" + limit);
        try {
            Criteria c = getSession().createCriteria(SalChance.class);
            // 条件
            if (StringUtils.isNotEmpty(chcCustName)) {
                c.add(Expression.like("chcCustName", "%" + chcCustName + "%"));
            }

            if (StringUtils.isNotEmpty(custTitle)) {
                c.add(Expression.like("chcTitle", "%" + custTitle + "%"));
            }
            if (StringUtils.isNotEmpty(custLinkman)) {
                c.add(Expression.like("chcLinkman", "%" + custLinkman + "%"));
            }
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
            PageResult.setData(fList);

        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
        return PageResult;
    }

    public void save(SalChance transientInstance) {
        log.debug("saving SalChance instance");
        try {
            getHibernateTemplate().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }

    public void delete(SalChance salChance) {
        log.debug("deleting SalChance instance");
        try {
            getHibernateTemplate().delete(salChance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }

    public SalChance findById(Long chcId) {
        log.debug("getting SalChance instance with id: " + chcId);
        try {
            SalChance instance = (SalChance) getHibernateTemplate().get(
                    "com.sanqing.po.SalChance", chcId);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }

    public List findByExample(SalChance instance) {
        log.debug("finding SalChance instance by example");
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
        log.debug("finding SalChance instance with property: " + propertyName
                + ", value: " + value);
        try {
            String queryString = "from SalChance as model where model."
                    + propertyName + "= ?";
            return getHibernateTemplate().find(queryString, value);
        } catch (RuntimeException re) {
            log.error("find by property name failed", re);
            throw re;
        }
    }

    public List findByChcSource(Object chcSource) {
        return findByProperty(CHC_SOURCE, chcSource);
    }

    public List findByChcCustName(Object chcCustName) {
        return findByProperty(CHC_CUST_NAME, chcCustName);
    }

    public List findByChcTitle(Object chcTitle) {
        return findByProperty(CHC_TITLE, chcTitle);
    }

    public List findByChcRate(Object chcRate) {
        return findByProperty(CHC_RATE, chcRate);
    }

    public List findByChcLinkman(Object chcLinkman) {
        return findByProperty(CHC_LINKMAN, chcLinkman);
    }

    public List findByChcTel(Object chcTel) {
        return findByProperty(CHC_TEL, chcTel);
    }

    public List findByChcDesc(Object chcDesc) {
        return findByProperty(CHC_DESC, chcDesc);
    }

    public List findByChcCreateBy(Object chcCreateBy) {
        return findByProperty(CHC_CREATE_BY, chcCreateBy);
    }

    public List findByChcDueTo(Object chcDueTo) {
        return findByProperty(CHC_DUE_TO, chcDueTo);
    }

    public List findByChcStatus(Object chcStatus) {
        return findByProperty(CHC_STATUS, chcStatus);
    }

    public List findAll() {
        log.debug("finding all SalChance instances");
        try {
            String queryString = "from SalChance";
            return getHibernateTemplate().find(queryString);
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
    }

    public SalChance merge(SalChance detachedInstance) {
        log.debug("merging SalChance instance");
        try {
            SalChance result = (SalChance) getHibernateTemplate().merge(
                    detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(SalChance instance) {
        log.debug("attaching dirty SalChance instance");
        try {
            getHibernateTemplate().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

    public void attachClean(SalChance instance) {
        log.debug("attaching clean SalChance instance");
        try {
            getHibernateTemplate().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

    public static ISalChanceDAO getFromApplicationContext(ApplicationContext ctx) {
        return (ISalChanceDAO) ctx.getBean("SalChanceDAO");
    }

}
package com.sanqing.dao.impl;

import com.sanqing.dao.IBasDictDAO;
import com.sanqing.po.BasDict;
import com.sanqing.struts.form.BasDictForm;
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

public class BasDictDAO extends HibernateDaoSupport implements IBasDictDAO {
    private static final Log log = LogFactory.getLog(BasDictDAO.class);

    protected void initDao() {
        // do nothing
    }

    public void save(BasDict transientInstance) {
        log.debug("saving BasDict instance");
        try {
            getHibernateTemplate().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }

    public PageResult findAll(Map paramMap) {
        PageResult pgr = new PageResult();
        String start = (String) paramMap.get("start");
        String limit = (String) paramMap.get("limit");


        String dicttype = (String) paramMap.get("dict_type");
        String dictitem = (String) paramMap.get("dict_item");
        String dictvalue = (String) paramMap.get("dict_value");
        try {
            Criteria c = getSession().createCriteria(BasDict.class);
            if (StringUtils.isNotEmpty(dicttype)) {
                c.add(Expression.like("dictType", "%" + dicttype + "%"));
            }
            if (StringUtils.isNotEmpty(dictitem)) {
                c.add(Expression.like("dictItem", "%" + dictitem + "%"));
            }
            if (StringUtils.isNotEmpty(dictvalue)) {
                c.add(Expression.like("dictValue", "%" + dictvalue + "%"));
            }

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
            List<BasDict> list = c.list();
            List<BasDictForm> fList = new ArrayList<BasDictForm>();
            BasDictForm dictForm = null;
            for (BasDict basdict : list) {
                dictForm = new BasDictForm();
                dictForm.setDictId(basdict.getDictId());
                dictForm.setDictType(basdict.getDictType());
                dictForm.setDictItem(basdict.getDictItem());
                dictForm.setDictValue(basdict.getDictValue());
                dictForm.setDictIsEditable(basdict.getDictIsEditable());
                fList.add(dictForm);
            }
            pgr.setData(fList);

        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
        return pgr;

    }

    /*
     * (non-Javadoc)
     *
     */
    public void delete(BasDict persistentInstance) {
        log.debug("deleting BasDict instance");
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
    public BasDict findById(Long id) {
        log.debug("getting BasDict instance with id: " + id);
        try {
            BasDict instance = (BasDict) getHibernateTemplate().get(
                    "com.sanqing.po.BasDict", id);
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
    public List findByExample(BasDict instance) {
        log.debug("finding BasDict instance by example");
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
        log.debug("finding BasDict instance with property: " + propertyName
                + ", value: " + value);
        try {
            String queryString = "from BasDict as model where model."
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
    public List findByDictType(Object dictType) {
        return findByProperty(DICT_TYPE, dictType);
    }

    /*
     * (non-Javadoc)
     *
     */
    public List findByDictItem(Object dictItem) {
        return findByProperty(DICT_ITEM, dictItem);
    }

    /*
     * (non-Javadoc)
     *
     */
    public List findByDictValue(Object dictValue) {
        return findByProperty(DICT_VALUE, dictValue);
    }

    /*
     * (non-Javadoc)
     *
     */
    public List findByDictIsEditable(Object dictIsEditable) {
        return findByProperty(DICT_IS_EDITABLE, dictIsEditable);
    }

    /*
     * (non-Javadoc)
     *
     */
    public List findAll() {
        log.debug("finding all BasDict instances");
        try {
            String queryString = "from BasDict";
            return getHibernateTemplate().find(queryString);
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
    }

    /*
     * (non-Javadoc)
     *
     */
    public BasDict merge(BasDict detachedInstance) {
        log.debug("merging BasDict instance");
        try {
            BasDict result = (BasDict) getHibernateTemplate().merge(
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
    public void attachDirty(BasDict instance) {
        log.debug("attaching dirty BasDict instance");
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
    public void attachClean(BasDict instance) {
        log.debug("attaching clean BasDict instance");
        try {
            getHibernateTemplate().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

    public static IBasDictDAO getFromApplicationContext(ApplicationContext ctx) {
        return (IBasDictDAO) ctx.getBean("BasDictDAO");
    }

}
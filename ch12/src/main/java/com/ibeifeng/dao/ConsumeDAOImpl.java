package com.ibeifeng.dao;

import com.ibeifeng.po.Consume;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import java.util.List;

/**
 * A data access object (DAO) providing persistence and search support for
 * Consume entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 *
 * @author MyEclipse Persistence Tools
 * @see com.ibeifeng.po.Consume
 */

public class ConsumeDAOImpl extends HibernateDaoSupport implements ConsumeDAO {
    private static final Log log = LogFactory.getLog(ConsumeDAOImpl.class);
    // property constants
    public static final String NAME = "name";
    public static final String COMMODITY_NAME = "commodityName";
    public static final String PRICE = "price";
    public static final String PRACTICE_PRICE = "practicePrice";

    /* (non-Javadoc)
     * @see com.ibeifeng.dao.ConsumeDAO#save(com.ibeifeng.po.Consume)
     */
    /* (non-Javadoc)
	 * @see com.ibeifeng.dao.ConsumeDAO#save(com.ibeifeng.po.Consume)
	 */
    public void save(Consume transientInstance) {
        log.debug("saving Consume instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }

    /* (non-Javadoc)
     * @see com.ibeifeng.dao.ConsumeDAO#delete(com.ibeifeng.po.Consume)
     */
	/* (non-Javadoc)
	 * @see com.ibeifeng.dao.ConsumeDAO#delete(com.ibeifeng.po.Consume)
	 */
    public void delete(Consume persistentInstance) {
        log.debug("deleting Consume instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }

    /* (non-Javadoc)
     * @see com.ibeifeng.dao.ConsumeDAO#findById(java.lang.Integer)
     */
	/* (non-Javadoc)
	 * @see com.ibeifeng.dao.ConsumeDAO#findById(java.lang.Integer)
	 */
    public Consume findById(java.lang.Integer id) {
        log.debug("getting Consume instance with id: " + id);
        try {
            Consume instance = (Consume) getSession().get(
                    "com.ibeifeng.po.Consume", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }

    /* (non-Javadoc)
     * @see com.ibeifeng.dao.ConsumeDAO#findByExample(com.ibeifeng.po.Consume)
     */
	/* (non-Javadoc)
	 * @see com.ibeifeng.dao.ConsumeDAO#findByExample(com.ibeifeng.po.Consume)
	 */
    public List findByExample(Consume instance) {
        log.debug("finding Consume instance by example");
        try {
            List results = getSession().createCriteria(
                    "com.ibeifeng.po.Consume").add(Example.create(instance))
                    .list();
            log.debug("find by example successful, result size: "
                    + results.size());
            return results;
        } catch (RuntimeException re) {
            log.error("find by example failed", re);
            throw re;
        }
    }

    /* (non-Javadoc)
     * @see com.ibeifeng.dao.ConsumeDAO#findByProperty(java.lang.String, java.lang.Object)
     */
	/* (non-Javadoc)
	 * @see com.ibeifeng.dao.ConsumeDAO#findByProperty(java.lang.String, java.lang.Object)
	 */
    public List findByProperty(String propertyName, Object value) {
        log.debug("finding Consume instance with property: " + propertyName
                + ", value: " + value);
        try {
            String queryString = "from Consume as model where model."
                    + propertyName + "= ?";
            Query queryObject = getSession().createQuery(queryString);
            queryObject.setParameter(0, value);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find by property name failed", re);
            throw re;
        }
    }

    /* (non-Javadoc)
     * @see com.ibeifeng.dao.ConsumeDAO#findByName(java.lang.Object)
     */
	/* (non-Javadoc)
	 * @see com.ibeifeng.dao.ConsumeDAO#findByName(java.lang.Object)
	 */
    public List findByName(Object name) {
        return findByProperty(NAME, name);
    }

    /* (non-Javadoc)
     * @see com.ibeifeng.dao.ConsumeDAO#findByCommodityName(java.lang.Object)
     */
	/* (non-Javadoc)
	 * @see com.ibeifeng.dao.ConsumeDAO#findByCommodityName(java.lang.Object)
	 */
    public List findByCommodityName(Object commodityName) {
        return findByProperty(COMMODITY_NAME, commodityName);
    }

    /* (non-Javadoc)
     * @see com.ibeifeng.dao.ConsumeDAO#findByPrice(java.lang.Object)
     */
	/* (non-Javadoc)
	 * @see com.ibeifeng.dao.ConsumeDAO#findByPrice(java.lang.Object)
	 */
    public List findByPrice(Object price) {
        return findByProperty(PRICE, price);
    }

    /* (non-Javadoc)
     * @see com.ibeifeng.dao.ConsumeDAO#findByPracticePrice(java.lang.Object)
     */
	/* (non-Javadoc)
	 * @see com.ibeifeng.dao.ConsumeDAO#findByPracticePrice(java.lang.Object)
	 */
    public List findByPracticePrice(Object practicePrice) {
        return findByProperty(PRACTICE_PRICE, practicePrice);
    }

    /* (non-Javadoc)
     * @see com.ibeifeng.dao.ConsumeDAO#findAll()
     */
	/* (non-Javadoc)
	 * @see com.ibeifeng.dao.ConsumeDAO#findAll()
	 */
    public List findAll() {
        log.debug("finding all Consume instances");
        try {
            String queryString = "from Consume";
            Query queryObject = getSession().createQuery(queryString);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
    }

    /* (non-Javadoc)
     * @see com.ibeifeng.dao.ConsumeDAO#findAll(int, int)
     */
    public List findAll(int start, int limit) {
        log.debug("finding all Consume instances");
        try {
            String queryString = "from Consume";
            Query queryObject = getSession().createQuery(queryString);
            queryObject.setFirstResult(start);
            queryObject.setMaxResults(limit);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
    }

    /* (non-Javadoc)
     * @see com.ibeifeng.dao.ConsumeDAO#merge(com.ibeifeng.po.Consume)
     */
	/* (non-Javadoc)
	 * @see com.ibeifeng.dao.ConsumeDAO#merge(com.ibeifeng.po.Consume)
	 */
    public Consume merge(Consume detachedInstance) {
        log.debug("merging Consume instance");
        try {
            Consume result = (Consume) getSession().merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    /* (non-Javadoc)
     * @see com.ibeifeng.dao.ConsumeDAO#attachDirty(com.ibeifeng.po.Consume)
     */
	/* (non-Javadoc)
	 * @see com.ibeifeng.dao.ConsumeDAO#attachDirty(com.ibeifeng.po.Consume)
	 */
    public void attachDirty(Consume instance) {
        log.debug("attaching dirty Consume instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

    /* (non-Javadoc)
     * @see com.ibeifeng.dao.ConsumeDAO#attachClean(com.ibeifeng.po.Consume)
     */
	/* (non-Javadoc)
	 * @see com.ibeifeng.dao.ConsumeDAO#attachClean(com.ibeifeng.po.Consume)
	 */
    public void attachClean(Consume instance) {
        log.debug("attaching clean Consume instance");
        try {
            getSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
}
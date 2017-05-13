package com.ibeifeng.dao;

import com.ibeifeng.po.Commodity;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import java.util.List;

/**
 * A data access object (DAO) providing persistence and search support for
 * Commodity entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 *
 * @author MyEclipse Persistence Tools
 * @see com.ibeifeng.po.Commodity
 */

public class CommodityDAOImpl extends HibernateDaoSupport implements CommodityDAO {
    private static final Log log = LogFactory.getLog(CommodityDAOImpl.class);
    // property constants
    public static final String COMMODITY_NAME = "commodityName";
    public static final String PRICE = "price";
    public static final String AGIO = "agio";

    /* (non-Javadoc)
     * @see com.ibeifeng.dao.CommodityDAO#save(com.ibeifeng.po.Commodity)
     */
    /* (non-Javadoc)
	 * @see com.ibeifeng.dao.CommodityDAO#save(com.ibeifeng.po.Commodity)
	 */
    public void save(Commodity transientInstance) {
        log.debug("saving Commodity instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }

    /* (non-Javadoc)
     * @see com.ibeifeng.dao.CommodityDAO#delete(com.ibeifeng.po.Commodity)
     */
	/* (non-Javadoc)
	 * @see com.ibeifeng.dao.CommodityDAO#delete(com.ibeifeng.po.Commodity)
	 */
    public void delete(Commodity persistentInstance) {
        log.debug("deleting Commodity instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }

    /* (non-Javadoc)
     * @see com.ibeifeng.dao.CommodityDAO#findById(java.lang.Integer)
     */
	/* (non-Javadoc)
	 * @see com.ibeifeng.dao.CommodityDAO#findById(java.lang.Integer)
	 */
    public Commodity findById(java.lang.Integer id) {
        log.debug("getting Commodity instance with id: " + id);
        try {
            Commodity instance = (Commodity) getSession().get(
                    "com.ibeifeng.po.Commodity", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }

    /* (non-Javadoc)
     * @see com.ibeifeng.dao.CommodityDAO#findByExample(com.ibeifeng.po.Commodity)
     */
	/* (non-Javadoc)
	 * @see com.ibeifeng.dao.CommodityDAO#findByExample(com.ibeifeng.po.Commodity)
	 */
    public List findByExample(Commodity instance) {
        log.debug("finding Commodity instance by example");
        try {
            List results = getSession().createCriteria(
                    "com.ibeifeng.po.Commodity").add(Example.create(instance))
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
     * @see com.ibeifeng.dao.CommodityDAO#findByProperty(java.lang.String, java.lang.Object)
     */
	/* (non-Javadoc)
	 * @see com.ibeifeng.dao.CommodityDAO#findByProperty(java.lang.String, java.lang.Object)
	 */
    public List findByProperty(String propertyName, Object value) {
        log.debug("finding Commodity instance with property: " + propertyName
                + ", value: " + value);
        try {
            String queryString = "from Commodity as model where model."
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
     * @see com.ibeifeng.dao.CommodityDAO#findByCommodityName(java.lang.Object)
     */
	/* (non-Javadoc)
	 * @see com.ibeifeng.dao.CommodityDAO#findByCommodityName(java.lang.Object)
	 */
    public List findByCommodityName(Object commodityName) {
        return findByProperty(COMMODITY_NAME, commodityName);
    }

    /* (non-Javadoc)
     * @see com.ibeifeng.dao.CommodityDAO#findByPrice(java.lang.Object)
     */
	/* (non-Javadoc)
	 * @see com.ibeifeng.dao.CommodityDAO#findByPrice(java.lang.Object)
	 */
    public List findByPrice(Object price) {
        return findByProperty(PRICE, price);
    }

    /* (non-Javadoc)
     * @see com.ibeifeng.dao.CommodityDAO#findByAgio(java.lang.Object)
     */
	/* (non-Javadoc)
	 * @see com.ibeifeng.dao.CommodityDAO#findByAgio(java.lang.Object)
	 */
    public List findByAgio(Object agio) {
        return findByProperty(AGIO, agio);
    }

    /* (non-Javadoc)
     * @see com.ibeifeng.dao.CommodityDAO#findAll()
     */
	/* (non-Javadoc)
	 * @see com.ibeifeng.dao.CommodityDAO#findAll()
	 */
    public List findAll() {
        log.debug("finding all Commodity instances");
        try {
            String queryString = "from Commodity";
            Query queryObject = getSession().createQuery(queryString);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
    }

    /* (non-Javadoc)
     * @see com.ibeifeng.dao.CommodityDAO#findAll(int, int)
     */
    public List findAll(int start, int limit) {
        log.debug("finding all Commodity instances");
        try {
            String queryString = "from Commodity";
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
     * @see com.ibeifeng.dao.CommodityDAO#merge(com.ibeifeng.po.Commodity)
     */
	/* (non-Javadoc)
	 * @see com.ibeifeng.dao.CommodityDAO#merge(com.ibeifeng.po.Commodity)
	 */
    public Commodity merge(Commodity detachedInstance) {
        log.debug("merging Commodity instance");
        try {
            Commodity result = (Commodity) getSession().merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    /* (non-Javadoc)
     * @see com.ibeifeng.dao.CommodityDAO#attachDirty(com.ibeifeng.po.Commodity)
     */
	/* (non-Javadoc)
	 * @see com.ibeifeng.dao.CommodityDAO#attachDirty(com.ibeifeng.po.Commodity)
	 */
    public void attachDirty(Commodity instance) {
        log.debug("attaching dirty Commodity instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

    /* (non-Javadoc)
     * @see com.ibeifeng.dao.CommodityDAO#attachClean(com.ibeifeng.po.Commodity)
     */
	/* (non-Javadoc)
	 * @see com.ibeifeng.dao.CommodityDAO#attachClean(com.ibeifeng.po.Commodity)
	 */
    public void attachClean(Commodity instance) {
        log.debug("attaching clean Commodity instance");
        try {
            getSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
}
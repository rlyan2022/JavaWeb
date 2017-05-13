package com.ibeifeng.dao;

import com.ibeifeng.po.Vip;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import java.util.List;

/**
 * A data access object (DAO) providing persistence and search support for Vip
 * entities. Transaction control of the save(), update() and delete() operations
 * can directly support Spring container-managed transactions or they can be
 * augmented to handle user-managed Spring transactions. Each of these methods
 * provides additional information for how to configure it for the desired type
 * of transaction control.
 *
 * @author MyEclipse Persistence Tools
 * @see com.ibeifeng.po.Vip
 */

public class VipDAOImpl extends HibernateDaoSupport implements VipDAO {
    private static final Log log = LogFactory.getLog(VipDAOImpl.class);
    // property constants
    public static final String NAME = "name";
    public static final String AGE = "age";
    public static final String PROFESSION = "profession";

    /* (non-Javadoc)
     * @see com.ibeifeng.dao.VipDAO#save(com.ibeifeng.po.Vip)
     */
    /* (non-Javadoc)
	 * @see com.ibeifeng.dao.VipDAO#save(com.ibeifeng.po.Vip)
	 */
    public void save(Vip transientInstance) {
        log.debug("saving Vip instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }

    /* (non-Javadoc)
     * @see com.ibeifeng.dao.VipDAO#delete(com.ibeifeng.po.Vip)
     */
	/* (non-Javadoc)
	 * @see com.ibeifeng.dao.VipDAO#delete(com.ibeifeng.po.Vip)
	 */
    public void delete(Vip persistentInstance) {
        log.debug("deleting Vip instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }

    /* (non-Javadoc)
     * @see com.ibeifeng.dao.VipDAO#findById(java.lang.Integer)
     */
	/* (non-Javadoc)
	 * @see com.ibeifeng.dao.VipDAO#findById(java.lang.Integer)
	 */
    public Vip findById(java.lang.Integer id) {
        log.debug("getting Vip instance with id: " + id);
        try {
            Vip instance = (Vip) getSession().get("com.ibeifeng.po.Vip", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }

    /* (non-Javadoc)
     * @see com.ibeifeng.dao.VipDAO#findByExample(com.ibeifeng.po.Vip)
     */
	/* (non-Javadoc)
	 * @see com.ibeifeng.dao.VipDAO#findByExample(com.ibeifeng.po.Vip)
	 */
    public List findByExample(Vip instance) {
        log.debug("finding Vip instance by example");
        try {
            List results = getSession().createCriteria("com.ibeifeng.po.Vip")
                    .add(Example.create(instance)).list();
            log.debug("find by example successful, result size: "
                    + results.size());
            return results;
        } catch (RuntimeException re) {
            log.error("find by example failed", re);
            throw re;
        }
    }

    /* (non-Javadoc)
     * @see com.ibeifeng.dao.VipDAO#findByProperty(java.lang.String, java.lang.Object)
     */
	/* (non-Javadoc)
	 * @see com.ibeifeng.dao.VipDAO#findByProperty(java.lang.String, java.lang.Object)
	 */
    public List findByProperty(String propertyName, Object value) {
        log.debug("finding Vip instance with property: " + propertyName
                + ", value: " + value);
        try {
            String queryString = "from Vip as model where model."
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
     * @see com.ibeifeng.dao.VipDAO#findByName(java.lang.Object)
     */
	/* (non-Javadoc)
	 * @see com.ibeifeng.dao.VipDAO#findByName(java.lang.Object)
	 */
    public List findByName(Object name) {
        return findByProperty(NAME, name);
    }

    /* (non-Javadoc)
     * @see com.ibeifeng.dao.VipDAO#findByAge(java.lang.Object)
     */
	/* (non-Javadoc)
	 * @see com.ibeifeng.dao.VipDAO#findByAge(java.lang.Object)
	 */
    public List findByAge(Object age) {
        return findByProperty(AGE, age);
    }

    /* (non-Javadoc)
     * @see com.ibeifeng.dao.VipDAO#findByProfession(java.lang.Object)
     */
	/* (non-Javadoc)
	 * @see com.ibeifeng.dao.VipDAO#findByProfession(java.lang.Object)
	 */
    public List findByProfession(Object profession) {
        return findByProperty(PROFESSION, profession);
    }

    /* (non-Javadoc)
     * @see com.ibeifeng.dao.VipDAO#findAll()
     */
	/* (non-Javadoc)
	 * @see com.ibeifeng.dao.VipDAO#findAll()
	 */
    public List findAll() {
        log.debug("finding all Vip instances");
        try {
            String queryString = "from Vip";
            Query queryObject = getSession().createQuery(queryString);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
    }

    /* (non-Javadoc)
     * @see com.ibeifeng.dao.VipDAO#findAll(int, int)
     */
    public List findAll(int start, int limit) {
        log.debug("finding all Vip instances");
        try {
            String queryString = "from Vip";
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
     * @see com.ibeifeng.dao.VipDAO#merge(com.ibeifeng.po.Vip)
     */
	/* (non-Javadoc)
	 * @see com.ibeifeng.dao.VipDAO#merge(com.ibeifeng.po.Vip)
	 */
    public Vip merge(Vip detachedInstance) {
        log.debug("merging Vip instance");
        try {
            Vip result = (Vip) getSession().merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    /* (non-Javadoc)
     * @see com.ibeifeng.dao.VipDAO#attachDirty(com.ibeifeng.po.Vip)
     */
	/* (non-Javadoc)
	 * @see com.ibeifeng.dao.VipDAO#attachDirty(com.ibeifeng.po.Vip)
	 */
    public void attachDirty(Vip instance) {
        log.debug("attaching dirty Vip instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

    /* (non-Javadoc)
     * @see com.ibeifeng.dao.VipDAO#attachClean(com.ibeifeng.po.Vip)
     */
	/* (non-Javadoc)
	 * @see com.ibeifeng.dao.VipDAO#attachClean(com.ibeifeng.po.Vip)
	 */
    public void attachClean(Vip instance) {
        log.debug("attaching clean Vip instance");
        try {
            getSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
}
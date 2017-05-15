package com.sanqing.dao;

import com.sanqing.po.PayOut;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import java.util.List;

/**
 * A data access object (DAO) providing persistence and search support for PayOut entities.
 * Transaction control of the save(), update() and delete() operations
 * can directly support Spring container-managed transactions or they can be augmented	to handle user-managed Spring transactions.
 * Each of these methods provides additional information for how to configure it for the desired type of transaction control.
 *
 * @author MyEclipse Persistence Tools
 * @see com.sanqing.po.PayOut
 */

public class PayOutDAOImpl extends HibernateDaoSupport implements PayOutDAO {
    private static final Log log = LogFactory.getLog(PayOutDAOImpl.class);
    //property constants
    public static final String PAY_OUT_NAME = "payOutName";
    public static final String PAY_OUT_MONEY = "payOutMoney";


    /* (non-Javadoc)
     * @see com.sanqing.dao.PayOutDAO#save(com.sanqing.po.PayOut)
	 */
    /* (non-Javadoc)
	 * @see com.sanqing.dao.PayOutDAO#save(com.sanqing.po.PayOut)
	 */
    /* (non-Javadoc)
	 * @see com.sanqing.dao.PayOutDAO#save(com.sanqing.po.PayOut)
	 */
    public void save(PayOut transientInstance) {
        log.debug("saving PayOut instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }

    /* (non-Javadoc)
     * @see com.sanqing.dao.PayOutDAO#delete(com.sanqing.po.PayOut)
     */
	/* (non-Javadoc)
	 * @see com.sanqing.dao.PayOutDAO#delete(com.sanqing.po.PayOut)
	 */
	/* (non-Javadoc)
	 * @see com.sanqing.dao.PayOutDAO#delete(com.sanqing.po.PayOut)
	 */
    public void delete(PayOut persistentInstance) {
        log.debug("deleting PayOut instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }

    /* (non-Javadoc)
	 * @see com.sanqing.dao.PayOutDAO#findById(java.lang.Integer)
	 */
    /* (non-Javadoc)
	 * @see com.sanqing.dao.PayOutDAO#findById(java.lang.Integer)
	 */
    /* (non-Javadoc)
	 * @see com.sanqing.dao.PayOutDAO#findById(java.lang.Integer)
	 */
    public PayOut findById(java.lang.Integer id) {
        log.debug("getting PayOut instance with id: " + id);
        try {
            PayOut instance = (PayOut) getSession()
                    .get("com.sanqing.po.PayOut", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }


    /* (non-Javadoc)
	 * @see com.sanqing.dao.PayOutDAO#findByExample(com.sanqing.po.PayOut)
	 */
    /* (non-Javadoc)
	 * @see com.sanqing.dao.PayOutDAO#findByExample(com.sanqing.po.PayOut)
	 */
    /* (non-Javadoc)
	 * @see com.sanqing.dao.PayOutDAO#findByExample(com.sanqing.po.PayOut)
	 */
    public List findByExample(PayOut instance) {
        log.debug("finding PayOut instance by example");
        try {
            List results = getSession()
                    .createCriteria("com.sanqing.po.PayOut")
                    .add(Example.create(instance))
                    .list();
            log.debug("find by example successful, result size: " + results.size());
            return results;
        } catch (RuntimeException re) {
            log.error("find by example failed", re);
            throw re;
        }
    }

    /* (non-Javadoc)
	 * @see com.sanqing.dao.PayOutDAO#findByProperty(java.lang.String, java.lang.Object)
	 */
    /* (non-Javadoc)
	 * @see com.sanqing.dao.PayOutDAO#findByProperty(java.lang.String, java.lang.Object)
	 */
    /* (non-Javadoc)
	 * @see com.sanqing.dao.PayOutDAO#findByProperty(java.lang.String, java.lang.Object)
	 */
    public List findByProperty(String propertyName, Object value) {
        log.debug("finding PayOut instance with property: " + propertyName
                + ", value: " + value);
        try {
            String queryString = "from PayOut as model where model."
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
	 * @see com.sanqing.dao.PayOutDAO#findByProperty(java.lang.String, java.lang.Object, int, int)
	 */
    /* (non-Javadoc)
	 * @see com.sanqing.dao.PayOutDAO#findByProperty(java.lang.String, java.lang.Object, int, int)
	 */
    public List findByProperty(String propertyName, Object value, int start, int limit) {
        log.debug("finding PayOut instance with property: " + propertyName
                + ", value: " + value);
        try {
            String queryString = "from PayOut as model where model."
                    + propertyName + "= ?";
            Query queryObject = getSession().createQuery(queryString);
            queryObject.setParameter(0, value);
            queryObject.setFirstResult(start);
            queryObject.setMaxResults(limit);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find by property name failed", re);
            throw re;
        }
    }

    /* (non-Javadoc)
	 * @see com.sanqing.dao.PayOutDAO#findByProperties(java.lang.String, java.lang.Object, java.lang.String, java.lang.Object, int, int)
	 */
    public List findByProperties(String propertyName, Object value, String propertyName2, Object value2, int start, int limit) {
        log.debug("finding PayOut instance with property: " + propertyName
                + ", value: " + value);
        try {
            String queryString = "from PayOut as model where model."
                    + propertyName + "= ?" + "and model." + propertyName2 + "= ?";
            Query queryObject = getSession().createQuery(queryString);
            queryObject.setParameter(0, value);
            queryObject.setParameter(1, value2);
            queryObject.setFirstResult(start);
            queryObject.setMaxResults(limit);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find by property name failed", re);
            throw re;
        }
    }

    /* (non-Javadoc)
     * @see com.sanqing.dao.PayOutDAO#findByPayOutName(java.lang.Object)
     */
	/* (non-Javadoc)
	 * @see com.sanqing.dao.PayOutDAO#findByPayOutName(java.lang.Object)
	 */
	/* (non-Javadoc)
	 * @see com.sanqing.dao.PayOutDAO#findByPayOutName(java.lang.Object)
	 */
    public List findByPayOutName(Object payOutName
    ) {
        return findByProperty(PAY_OUT_NAME, payOutName
        );
    }

    /* (non-Javadoc)
     * @see com.sanqing.dao.PayOutDAO#findByPayOutMoney(java.lang.Object)
     */
	/* (non-Javadoc)
	 * @see com.sanqing.dao.PayOutDAO#findByPayOutMoney(java.lang.Object)
	 */
	/* (non-Javadoc)
	 * @see com.sanqing.dao.PayOutDAO#findByPayOutMoney(java.lang.Object)
	 */
    public List findByPayOutMoney(Object payOutMoney
    ) {
        return findByProperty(PAY_OUT_MONEY, payOutMoney
        );
    }


    /* (non-Javadoc)
     * @see com.sanqing.dao.PayOutDAO#findAll()
     */
	/* (non-Javadoc)
	 * @see com.sanqing.dao.PayOutDAO#findAll()
	 */
	/* (non-Javadoc)
	 * @see com.sanqing.dao.PayOutDAO#findAll()
	 */
    public List findAll() {
        log.debug("finding all PayOut instances");
        try {
            String queryString = "from PayOut";
            Query queryObject = getSession().createQuery(queryString);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
    }

    /* (non-Javadoc)
	 * @see com.sanqing.dao.PayOutDAO#merge(com.sanqing.po.PayOut)
	 */
    /* (non-Javadoc)
	 * @see com.sanqing.dao.PayOutDAO#merge(com.sanqing.po.PayOut)
	 */
    /* (non-Javadoc)
	 * @see com.sanqing.dao.PayOutDAO#merge(com.sanqing.po.PayOut)
	 */
    public PayOut merge(PayOut detachedInstance) {
        log.debug("merging PayOut instance");
        try {
            PayOut result = (PayOut) getSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    /* (non-Javadoc)
	 * @see com.sanqing.dao.PayOutDAO#attachDirty(com.sanqing.po.PayOut)
	 */
    /* (non-Javadoc)
	 * @see com.sanqing.dao.PayOutDAO#attachDirty(com.sanqing.po.PayOut)
	 */
    /* (non-Javadoc)
	 * @see com.sanqing.dao.PayOutDAO#attachDirty(com.sanqing.po.PayOut)
	 */
    public void attachDirty(PayOut instance) {
        log.debug("attaching dirty PayOut instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

    /* (non-Javadoc)
	 * @see com.sanqing.dao.PayOutDAO#attachClean(com.sanqing.po.PayOut)
	 */
    /* (non-Javadoc)
	 * @see com.sanqing.dao.PayOutDAO#attachClean(com.sanqing.po.PayOut)
	 */
    /* (non-Javadoc)
	 * @see com.sanqing.dao.PayOutDAO#attachClean(com.sanqing.po.PayOut)
	 */
    public void attachClean(PayOut instance) {
        log.debug("attaching clean PayOut instance");
        try {
            getSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
}
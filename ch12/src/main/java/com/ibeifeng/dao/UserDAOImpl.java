package com.ibeifeng.dao;

import com.ibeifeng.po.User;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import java.util.List;

/**
 * A data access object (DAO) providing persistence and search support for User
 * entities. Transaction control of the save(), update() and delete() operations
 * can directly support Spring container-managed transactions or they can be
 * augmented to handle user-managed Spring transactions. Each of these methods
 * provides additional information for how to configure it for the desired type
 * of transaction control.
 *
 * @author MyEclipse Persistence Tools
 * @see com.ibeifeng.po.User
 */

public class UserDAOImpl extends HibernateDaoSupport implements UserDAO {
    private static final Log log = LogFactory.getLog(UserDAOImpl.class);
    // property constants
    public static final String PASSWORD = "password";
    public static final String QUANXIAN = "quanxian";

    /* (non-Javadoc)
     * @see com.ibeifeng.dao.UserDAO#save(com.ibeifeng.po.User)
     */
    public void save(User transientInstance) {
        log.debug("saving User instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }

    /* (non-Javadoc)
     * @see com.ibeifeng.dao.UserDAO#delete(com.ibeifeng.po.User)
     */
    public void delete(User persistentInstance) {
        log.debug("deleting User instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }

    /* (non-Javadoc)
     * @see com.ibeifeng.dao.UserDAO#findById(java.lang.String)
     */
    public User findById(java.lang.String id) {
        log.debug("getting User instance with id: " + id);
        try {
            User instance = (User) getSession().get("com.ibeifeng.po.User", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }

    /* (non-Javadoc)
     * @see com.ibeifeng.dao.UserDAO#findByExample(com.ibeifeng.po.User)
     */
    public List findByExample(User instance) {
        log.debug("finding User instance by example");
        try {
            List results = getSession().createCriteria("com.ibeifeng.po.User")
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
     * @see com.ibeifeng.dao.UserDAO#findByProperty(java.lang.String, java.lang.Object)
     */
    public List findByProperty(String propertyName, Object value) {
        log.debug("finding User instance with property: " + propertyName
                + ", value: " + value);
        try {
            String queryString = "from User as model where model."
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
     * @see com.ibeifeng.dao.UserDAO#findByPassword(java.lang.Object)
     */
    public List findByPassword(Object password) {
        return findByProperty(PASSWORD, password);
    }

    /* (non-Javadoc)
     * @see com.ibeifeng.dao.UserDAO#findByQuanxian(java.lang.Object)
     */
    public List findByQuanxian(Object quanxian) {
        return findByProperty(QUANXIAN, quanxian);
    }

    /* (non-Javadoc)
     * @see com.ibeifeng.dao.UserDAO#findAll()
     */
    public List findAll() {
        log.debug("finding all User instances");
        try {
            String queryString = "from User";
            Query queryObject = getSession().createQuery(queryString);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
    }

    /* (non-Javadoc)
     * @see com.ibeifeng.dao.UserDAO#merge(com.ibeifeng.po.User)
     */
    public User merge(User detachedInstance) {
        log.debug("merging User instance");
        try {
            User result = (User) getSession().merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    /* (non-Javadoc)
     * @see com.ibeifeng.dao.UserDAO#attachDirty(com.ibeifeng.po.User)
     */
    public void attachDirty(User instance) {
        log.debug("attaching dirty User instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

    /* (non-Javadoc)
     * @see com.ibeifeng.dao.UserDAO#attachClean(com.ibeifeng.po.User)
     */
    public void attachClean(User instance) {
        log.debug("attaching clean User instance");
        try {
            getSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
}
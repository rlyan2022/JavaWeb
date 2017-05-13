package HibernateDao;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

import java.util.List;

/**
 * Data access object (DAO) for domain model class Rolemaster.
 *
 * @author MyEclipse Persistence Tools
 * @see Rolemaster
 */

public class RolemasterDAO extends BaseHibernateDAO {
    private static final Log log = LogFactory.getLog(RolemasterDAO.class);

    // property constants
    public static final String ROLE_NAME = "roleName";

    public void save(Rolemaster transientInstance) {
        log.debug("saving Rolemaster instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }

    public void delete(Rolemaster persistentInstance) {
        log.debug("deleting Rolemaster instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }

    public Rolemaster findById(String id) {
        log.debug("getting Rolemaster instance with id: " + id);
        try {
            Rolemaster instance = (Rolemaster) getSession().get(
                    "HibernateDao.Rolemaster", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }

    public List findByExample(Rolemaster instance) {
        log.debug("finding Rolemaster instance by example");
        try {
            List results = getSession().createCriteria(
                    "HibernateDao.Rolemaster").add(Example.create(instance))
                    .list();
            log.debug("find by example successful, result size: "
                    + results.size());
            return results;
        } catch (RuntimeException re) {
            log.error("find by example failed", re);
            throw re;
        }
    }

    public List findByProperty(String propertyName, Object value) {
        log.debug("finding Rolemaster instance with property: " + propertyName
                + ", value: " + value);
        try {
            String queryString = "from Rolemaster as model where model."
                    + propertyName + "= ?";
            Query queryObject = getSession().createQuery(queryString);
            queryObject.setParameter(0, value);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find by property name failed", re);
            throw re;
        }
    }

    public List findByRoleName(Object roleName) {
        return findByProperty(ROLE_NAME, roleName);
    }

    public List findAll() {
        log.debug("finding all Rolemaster instances");
        try {
            String queryString = "from Rolemaster";
            Query queryObject = getSession().createQuery(queryString);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
    }

    public Rolemaster merge(Rolemaster detachedInstance) {
        log.debug("merging Rolemaster instance");
        try {
            Rolemaster result = (Rolemaster) getSession().merge(
                    detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(Rolemaster instance) {
        log.debug("attaching dirty Rolemaster instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

    public void attachClean(Rolemaster instance) {
        log.debug("attaching clean Rolemaster instance");
        try {
            getSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
}
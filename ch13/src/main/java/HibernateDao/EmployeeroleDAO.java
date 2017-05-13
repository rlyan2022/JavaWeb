package HibernateDao;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

import java.util.List;

/**
 * Data access object (DAO) for domain model class Employeerole.
 *
 * @author MyEclipse Persistence Tools
 * @see Employeerole
 */

public class EmployeeroleDAO extends BaseHibernateDAO {
    private static final Log log = LogFactory.getLog(EmployeeroleDAO.class);

    // property constants

    public void save(Employeerole transientInstance) {
        log.debug("saving Employeerole instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }

    public void delete(Employeerole persistentInstance) {
        log.debug("deleting Employeerole instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }

    public Employeerole findById(Integer id) {
        log.debug("getting Employeerole instance with id: " + id);
        try {
            Employeerole instance = (Employeerole) getSession().get(
                    "HibernateDao.Employeerole", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }

    public List findByExample(Employeerole instance) {
        log.debug("finding Employeerole instance by example");
        try {
            List results = getSession().createCriteria(
                    "HibernateDao.Employeerole").add(Example.create(instance))
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
        log.debug("finding Employeerole instance with property: "
                + propertyName + ", value: " + value);
        try {
            String queryString = "from Employeerole as model where model."
                    + propertyName + "= ?";
            Query queryObject = getSession().createQuery(queryString);
            queryObject.setParameter(0, value);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find by property name failed", re);
            throw re;
        }
    }

    public List findAll() {
        log.debug("finding all Employeerole instances");
        try {
            String queryString = "from Employeerole";
            Query queryObject = getSession().createQuery(queryString);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
    }

    public Employeerole merge(Employeerole detachedInstance) {
        log.debug("merging Employeerole instance");
        try {
            Employeerole result = (Employeerole) getSession().merge(
                    detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(Employeerole instance) {
        log.debug("attaching dirty Employeerole instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

    public void attachClean(Employeerole instance) {
        log.debug("attaching clean Employeerole instance");
        try {
            getSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
}
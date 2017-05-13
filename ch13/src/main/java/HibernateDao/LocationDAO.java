package HibernateDao;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

import java.util.List;

/**
 * Data access object (DAO) for domain model class Location.
 *
 * @author MyEclipse Persistence Tools
 * @see Location
 */

public class LocationDAO extends BaseHibernateDAO {
    private static final Log log = LogFactory.getLog(LocationDAO.class);

    // property constants
    public static final String LOCA_DESC = "locaDesc";

    public void save(Location transientInstance) {
        log.debug("saving Location instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }

    public void delete(Location persistentInstance) {
        log.debug("deleting Location instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }

    public Location findById(String id) {
        log.debug("getting Location instance with id: " + id);
        try {
            Location instance = (Location) getSession().get(
                    "HibernateDao.Location", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }

    public List findByExample(Location instance) {
        log.debug("finding Location instance by example");
        try {
            List results = getSession().createCriteria("HibernateDao.Location")
                    .add(Example.create(instance)).list();
            log.debug("find by example successful, result size: "
                    + results.size());
            return results;
        } catch (RuntimeException re) {
            log.error("find by example failed", re);
            throw re;
        }
    }

    public List findByProperty(String propertyName, Object value) {
        log.debug("finding Location instance with property: " + propertyName
                + ", value: " + value);
        try {
            String queryString = "from Location as model where model."
                    + propertyName + "= ?";
            Query queryObject = getSession().createQuery(queryString);
            queryObject.setParameter(0, value);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find by property name failed", re);
            throw re;
        }
    }

    public List findByLocaDesc(Object locaDesc) {
        return findByProperty(LOCA_DESC, locaDesc);
    }

    public List findAll() {
        log.debug("finding all Location instances");
        try {
            String queryString = "from Location";
            Query queryObject = getSession().createQuery(queryString);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
    }

    public Location merge(Location detachedInstance) {
        log.debug("merging Location instance");
        try {
            Location result = (Location) getSession().merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(Location instance) {
        log.debug("attaching dirty Location instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

    public void attachClean(Location instance) {
        log.debug("attaching clean Location instance");
        try {
            getSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
}
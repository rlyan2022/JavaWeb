package HibernateDao;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

import java.util.List;

/**
 * Data access object (DAO) for domain model class Inventorytag.
 *
 * @author MyEclipse Persistence Tools
 * @see Inventorytag
 */

public class InventorytagDAO extends BaseHibernateDAO {
    private static final Log log = LogFactory.getLog(InventorytagDAO.class);

    // property constants
    public static final String INC_ZERO = "incZero";

    public static final String INC_NEGA = "incNega";

    public void save(Inventorytag transientInstance) {
        log.debug("saving Inventorytag instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }

    public void delete(Inventorytag persistentInstance) {
        log.debug("deleting Inventorytag instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }

    public Inventorytag findById(Integer id) {
        log.debug("getting Inventorytag instance with id: " + id);
        try {
            Inventorytag instance = (Inventorytag) getSession().get(
                    "HibernateDao.Inventorytag", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }

    public List findByExample(Inventorytag instance) {
        log.debug("finding Inventorytag instance by example");
        try {
            List results = getSession().createCriteria(
                    "HibernateDao.Inventorytag").add(Example.create(instance))
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
        log.debug("finding Inventorytag instance with property: "
                + propertyName + ", value: " + value);
        try {
            String queryString = "from Inventorytag as model where model."
                    + propertyName + "= ?";
            Query queryObject = getSession().createQuery(queryString);
            queryObject.setParameter(0, value);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find by property name failed", re);
            throw re;
        }
    }

    public List findByIncZero(Object incZero) {
        return findByProperty(INC_ZERO, incZero);
    }

    public List findByIncNega(Object incNega) {
        return findByProperty(INC_NEGA, incNega);
    }

    public List findAll() {
        log.debug("finding all Inventorytag instances");
        try {
            String queryString = "from Inventorytag";
            Query queryObject = getSession().createQuery(queryString);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
    }

    public Inventorytag merge(Inventorytag detachedInstance) {
        log.debug("merging Inventorytag instance");
        try {
            Inventorytag result = (Inventorytag) getSession().merge(
                    detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(Inventorytag instance) {
        log.debug("attaching dirty Inventorytag instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

    public void attachClean(Inventorytag instance) {
        log.debug("attaching clean Inventorytag instance");
        try {
            getSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
}
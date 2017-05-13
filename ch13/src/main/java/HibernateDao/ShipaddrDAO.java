package HibernateDao;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

import java.util.List;

/**
 * Data access object (DAO) for domain model class Shipaddr.
 *
 * @author MyEclipse Persistence Tools
 * @see Shipaddr
 */

public class ShipaddrDAO extends BaseHibernateDAO {
    private static final Log log = LogFactory.getLog(ShipaddrDAO.class);

    // property constants
    public static final String SHIP_DEC = "shipDec";

    public static final String SHIP_CITY = "shipCity";

    public static final String STATE = "state";

    public void save(Shipaddr transientInstance) {
        log.debug("saving Shipaddr instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }

    public void delete(Shipaddr persistentInstance) {
        log.debug("deleting Shipaddr instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }

    public Shipaddr findById(String id) {
        log.debug("getting Shipaddr instance with id: " + id);
        try {
            Shipaddr instance = (Shipaddr) getSession().get(
                    "HibernateDao.Shipaddr", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }

    public List findByExample(Shipaddr instance) {
        log.debug("finding Shipaddr instance by example");
        try {
            List results = getSession().createCriteria("HibernateDao.Shipaddr")
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
        log.debug("finding Shipaddr instance with property: " + propertyName
                + ", value: " + value);
        try {
            String queryString = "from Shipaddr as model where model."
                    + propertyName + "= ?";
            Query queryObject = getSession().createQuery(queryString);
            queryObject.setParameter(0, value);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find by property name failed", re);
            throw re;
        }
    }

    public List findByShipDec(Object shipDec) {
        return findByProperty(SHIP_DEC, shipDec);
    }

    public List findByShipCity(Object shipCity) {
        return findByProperty(SHIP_CITY, shipCity);
    }

    public List findByState(Object state) {
        return findByProperty(STATE, state);
    }

    public List findAll() {
        log.debug("finding all Shipaddr instances");
        try {
            String queryString = "from Shipaddr";
            Query queryObject = getSession().createQuery(queryString);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
    }

    public Shipaddr merge(Shipaddr detachedInstance) {
        log.debug("merging Shipaddr instance");
        try {
            Shipaddr result = (Shipaddr) getSession().merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(Shipaddr instance) {
        log.debug("attaching dirty Shipaddr instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

    public void attachClean(Shipaddr instance) {
        log.debug("attaching clean Shipaddr instance");
        try {
            getSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
}
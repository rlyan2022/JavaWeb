package HibernateDao;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

import java.util.List;

/**
 * Data access object (DAO) for domain model class Shipvin.
 *
 * @author MyEclipse Persistence Tools
 * @see Shipvin
 */

public class ShipvinDAO extends BaseHibernateDAO {
    private static final Log log = LogFactory.getLog(ShipvinDAO.class);

    // property constants
    public static final String SHIPVIA_DESC = "shipviaDesc";

    public void save(Shipvin transientInstance) {
        log.debug("saving Shipvin instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }

    public void delete(Shipvin persistentInstance) {
        log.debug("deleting Shipvin instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }

    public Shipvin findById(String id) {
        log.debug("getting Shipvin instance with id: " + id);
        try {
            Shipvin instance = (Shipvin) getSession().get(
                    "HibernateDao.Shipvin", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }

    public List findByExample(Shipvin instance) {
        log.debug("finding Shipvin instance by example");
        try {
            List results = getSession().createCriteria("HibernateDao.Shipvin")
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
        log.debug("finding Shipvin instance with property: " + propertyName
                + ", value: " + value);
        try {
            String queryString = "from Shipvin as model where model."
                    + propertyName + "= ?";
            Query queryObject = getSession().createQuery(queryString);
            queryObject.setParameter(0, value);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find by property name failed", re);
            throw re;
        }
    }

    public List findByShipviaDesc(Object shipviaDesc) {
        return findByProperty(SHIPVIA_DESC, shipviaDesc);
    }

    public List findAll() {
        log.debug("finding all Shipvin instances");
        try {
            String queryString = "from Shipvin";
            Query queryObject = getSession().createQuery(queryString);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
    }

    public Shipvin merge(Shipvin detachedInstance) {
        log.debug("merging Shipvin instance");
        try {
            Shipvin result = (Shipvin) getSession().merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(Shipvin instance) {
        log.debug("attaching dirty Shipvin instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

    public void attachClean(Shipvin instance) {
        log.debug("attaching clean Shipvin instance");
        try {
            getSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
}
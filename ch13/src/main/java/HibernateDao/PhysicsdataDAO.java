package HibernateDao;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

import java.util.List;

/**
 * Data access object (DAO) for domain model class Physicsdata.
 *
 * @author MyEclipse Persistence Tools
 * @see Physicsdata
 */

public class PhysicsdataDAO extends BaseHibernateDAO {
    private static final Log log = LogFactory.getLog(PhysicsdataDAO.class);

    // property constants
    public static final String STATUE = "statue";

    public static final String CUTOFF = "cutoff";

    public static final String ACT_QTY = "actQty";

    public void save(Physicsdata transientInstance) {
        log.debug("saving Physicsdata instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }

    public void delete(Physicsdata persistentInstance) {
        log.debug("deleting Physicsdata instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }

    public Physicsdata findById(Integer id) {
        log.debug("getting Physicsdata instance with id: " + id);
        try {
            Physicsdata instance = (Physicsdata) getSession().get(
                    "HibernateDao.Physicsdata", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }

    public List findByExample(Physicsdata instance) {
        log.debug("finding Physicsdata instance by example");
        try {
            List results = getSession().createCriteria(
                    "HibernateDao.Physicsdata").add(Example.create(instance))
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
        log.debug("finding Physicsdata instance with property: " + propertyName
                + ", value: " + value);
        try {
            String queryString = "from Physicsdata as model where model."
                    + propertyName + "= ?";
            Query queryObject = getSession().createQuery(queryString);
            queryObject.setParameter(0, value);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find by property name failed", re);
            throw re;
        }
    }

    public List findByStatue(Object statue) {
        return findByProperty(STATUE, statue);
    }

    public List findByCutoff(Object cutoff) {
        return findByProperty(CUTOFF, cutoff);
    }

    public List findByActQty(Object actQty) {
        return findByProperty(ACT_QTY, actQty);
    }

    public List findAll() {
        log.debug("finding all Physicsdata instances");
        try {
            String queryString = "from Physicsdata";
            Query queryObject = getSession().createQuery(queryString);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
    }

    public Physicsdata merge(Physicsdata detachedInstance) {
        log.debug("merging Physicsdata instance");
        try {
            Physicsdata result = (Physicsdata) getSession().merge(
                    detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(Physicsdata instance) {
        log.debug("attaching dirty Physicsdata instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

    public void attachClean(Physicsdata instance) {
        log.debug("attaching clean Physicsdata instance");
        try {
            getSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
}
package HibernateDao;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

import java.util.List;

/**
 * Data access object (DAO) for domain model class Itemlocation.
 *
 * @author MyEclipse Persistence Tools
 * @see Itemlocation
 */

public class ItemlocationDAO extends BaseHibernateDAO {
    private static final Log log = LogFactory.getLog(ItemlocationDAO.class);

    // property constants

    public void save(Itemlocation transientInstance) {
        log.debug("saving Itemlocation instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }

    public void delete(Itemlocation persistentInstance) {
        log.debug("deleting Itemlocation instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }

    public Itemlocation findById(ItemlocationId id) {
        log.debug("getting Itemlocation instance with id: " + id);
        try {
            Itemlocation instance = (Itemlocation) getSession().get(
                    "HibernateDao.Itemlocation", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }

    public List findByExample(Itemlocation instance) {
        log.debug("finding Itemlocation instance by example");
        try {
            List results = getSession().createCriteria(
                    "HibernateDao.Itemlocation").add(Example.create(instance))
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
        log.debug("finding Itemlocation instance with property: "
                + propertyName + ", value: " + value);
        try {
            String queryString = "from Itemlocation as model where model."
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
        log.debug("finding all Itemlocation instances");
        try {
            String queryString = "from Itemlocation";
            Query queryObject = getSession().createQuery(queryString);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
    }

    public Itemlocation merge(Itemlocation detachedInstance) {
        log.debug("merging Itemlocation instance");
        try {
            Itemlocation result = (Itemlocation) getSession().merge(
                    detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(Itemlocation instance) {
        log.debug("attaching dirty Itemlocation instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

    public void attachClean(Itemlocation instance) {
        log.debug("attaching clean Itemlocation instance");
        try {
            getSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
}
package HibernateDao;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

import java.util.List;

/**
 * Data access object (DAO) for domain model class WarehouseItem.
 *
 * @author MyEclipse Persistence Tools
 * @see WarehouseItem
 */

public class WarehouseItemDAO extends BaseHibernateDAO {
    private static final Log log = LogFactory.getLog(WarehouseItemDAO.class);

    // property constants

    public void save(WarehouseItem transientInstance) {
        log.debug("saving WarehouseItem instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }

    public void delete(WarehouseItem persistentInstance) {
        log.debug("deleting WarehouseItem instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }

    public WarehouseItem findById(WarehouseItemId id) {
        log.debug("getting WarehouseItem instance with id: " + id);
        try {
            WarehouseItem instance = (WarehouseItem) getSession().get(
                    "HibernateDao.WarehouseItem", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }

    public List findByExample(WarehouseItem instance) {
        log.debug("finding WarehouseItem instance by example");
        try {
            List results = getSession().createCriteria(
                    "HibernateDao.WarehouseItem").add(Example.create(instance))
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
        log.debug("finding WarehouseItem instance with property: "
                + propertyName + ", value: " + value);
        try {
            String queryString = "from WarehouseItem as model where model."
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
        log.debug("finding all WarehouseItem instances");
        try {
            String queryString = "from WarehouseItem";
            Query queryObject = getSession().createQuery(queryString);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
    }

    public WarehouseItem merge(WarehouseItem detachedInstance) {
        log.debug("merging WarehouseItem instance");
        try {
            WarehouseItem result = (WarehouseItem) getSession().merge(
                    detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(WarehouseItem instance) {
        log.debug("attaching dirty WarehouseItem instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

    public void attachClean(WarehouseItem instance) {
        log.debug("attaching clean WarehouseItem instance");
        try {
            getSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
}
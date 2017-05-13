package HibernateDao;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

import java.util.List;

/**
 * Data access object (DAO) for domain model class Warehouse.
 *
 * @author MyEclipse Persistence Tools
 * @see Warehouse
 */

public class WarehouseDAO extends BaseHibernateDAO {
    private static final Log log = LogFactory.getLog(WarehouseDAO.class);

    // property constants
    public static final String WARE_DESC = "wareDesc";

    public static final String WARE_ADRR = "wareAdrr";

    public static final String VAL_EMP = "valEmp";

    public static final String FAX = "fax";

    public static final String PHONE = "phone";

    public void save(Warehouse transientInstance) {
        log.debug("saving Warehouse instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }

    public void delete(Warehouse persistentInstance) {
        log.debug("deleting Warehouse instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }

    public Warehouse findById(String id) {
        log.debug("getting Warehouse instance with id: " + id);
        try {
            Warehouse instance = (Warehouse) getSession().get(
                    "HibernateDao.Warehouse", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }

    public List findByExample(Warehouse instance) {
        log.debug("finding Warehouse instance by example");
        try {
            List results = getSession()
                    .createCriteria("HibernateDao.Warehouse").add(
                            Example.create(instance)).list();
            log.debug("find by example successful, result size: "
                    + results.size());
            return results;
        } catch (RuntimeException re) {
            log.error("find by example failed", re);
            throw re;
        }
    }

    public List findByProperty(String propertyName, Object value) {
        log.debug("finding Warehouse instance with property: " + propertyName
                + ", value: " + value);
        try {
            String queryString = "from Warehouse as model where model."
                    + propertyName + "= ?";
            Query queryObject = getSession().createQuery(queryString);
            queryObject.setParameter(0, value);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find by property name failed", re);
            throw re;
        }
    }

    public List findByWareDesc(Object wareDesc) {
        return findByProperty(WARE_DESC, wareDesc);
    }

    public List findByWareAdrr(Object wareAdrr) {
        return findByProperty(WARE_ADRR, wareAdrr);
    }

    public List findByValEmp(Object valEmp) {
        return findByProperty(VAL_EMP, valEmp);
    }

    public List findByFax(Object fax) {
        return findByProperty(FAX, fax);
    }

    public List findByPhone(Object phone) {
        return findByProperty(PHONE, phone);
    }

    public List findAll() {
        log.debug("finding all Warehouse instances");
        try {
            String queryString = "from Warehouse";
            Query queryObject = getSession().createQuery(queryString);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
    }

    public Warehouse merge(Warehouse detachedInstance) {
        log.debug("merging Warehouse instance");
        try {
            Warehouse result = (Warehouse) getSession().merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(Warehouse instance) {
        log.debug("attaching dirty Warehouse instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

    public void attachClean(Warehouse instance) {
        log.debug("attaching clean Warehouse instance");
        try {
            getSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
}
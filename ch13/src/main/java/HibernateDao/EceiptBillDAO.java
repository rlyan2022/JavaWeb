package HibernateDao;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

import java.util.List;

/**
 * Data access object (DAO) for domain model class EceiptBill.
 *
 * @author MyEclipse Persistence Tools
 * @see EceiptBill
 */

public class EceiptBillDAO extends BaseHibernateDAO {
    private static final Log log = LogFactory.getLog(EceiptBillDAO.class);

    // property constants
    public static final String BILL_DESC = "billDesc";

    public static final String SOURCE_COMPANY = "sourceCompany";

    public static final String CERT_NO = "certNo";

    public static final String STATUS = "status";

    public void save(EceiptBill transientInstance) {
        log.debug("saving EceiptBill instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }

    public void delete(EceiptBill persistentInstance) {
        log.debug("deleting EceiptBill instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }

    public EceiptBill findById(String id) {
        log.debug("getting EceiptBill instance with id: " + id);
        try {
            EceiptBill instance = (EceiptBill) getSession().get(
                    "HibernateDao.EceiptBill", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }

    public List findByExample(EceiptBill instance) {
        log.debug("finding EceiptBill instance by example");
        try {
            List results = getSession().createCriteria(
                    "HibernateDao.EceiptBill").add(Example.create(instance))
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
        log.debug("finding EceiptBill instance with property: " + propertyName
                + ", value: " + value);
        try {
            String queryString = "from EceiptBill as model where model."
                    + propertyName + "= ?";
            Query queryObject = getSession().createQuery(queryString);
            queryObject.setParameter(0, value);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find by property name failed", re);
            throw re;
        }
    }

    public List findByBillDesc(Object billDesc) {
        return findByProperty(BILL_DESC, billDesc);
    }

    public List findBySourceCompany(Object sourceCompany) {
        return findByProperty(SOURCE_COMPANY, sourceCompany);
    }

    public List findByCertNo(Object certNo) {
        return findByProperty(CERT_NO, certNo);
    }

    public List findByStatus(Object status) {
        return findByProperty(STATUS, status);
    }

    public List findAll() {
        log.debug("finding all EceiptBill instances");
        try {
            String queryString = "from EceiptBill";
            Query queryObject = getSession().createQuery(queryString);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
    }

    public EceiptBill merge(EceiptBill detachedInstance) {
        log.debug("merging EceiptBill instance");
        try {
            EceiptBill result = (EceiptBill) getSession().merge(
                    detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(EceiptBill instance) {
        log.debug("attaching dirty EceiptBill instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

    public void attachClean(EceiptBill instance) {
        log.debug("attaching clean EceiptBill instance");
        try {
            getSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
}
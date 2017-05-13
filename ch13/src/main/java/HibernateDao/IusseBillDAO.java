package HibernateDao;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

import java.util.List;

/**
 * Data access object (DAO) for domain model class IusseBill.
 *
 * @author MyEclipse Persistence Tools
 * @see IusseBill
 */

public class IusseBillDAO extends BaseHibernateDAO {
    private static final Log log = LogFactory.getLog(IusseBillDAO.class);

    // property constants
    public static final String BILL_DESC = "billDesc";

    public static final String DEST_COMPANY = "destCompany";

    public static final String CERT_NO = "certNo";

    public static final String STATUS = "status";

    public void save(IusseBill transientInstance) {
        log.debug("saving IusseBill instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }

    public void delete(IusseBill persistentInstance) {
        log.debug("deleting IusseBill instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }

    public IusseBill findById(String id) {
        log.debug("getting IusseBill instance with id: " + id);
        try {
            IusseBill instance = (IusseBill) getSession().get(
                    "HibernateDao.IusseBill", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }

    public List findByExample(IusseBill instance) {
        log.debug("finding IusseBill instance by example");
        try {
            List results = getSession()
                    .createCriteria("HibernateDao.IusseBill").add(
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
        log.debug("finding IusseBill instance with property: " + propertyName
                + ", value: " + value);
        try {
            String queryString = "from IusseBill as model where model."
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

    public List findByDestCompany(Object destCompany) {
        return findByProperty(DEST_COMPANY, destCompany);
    }

    public List findByCertNo(Object certNo) {
        return findByProperty(CERT_NO, certNo);
    }

    public List findByStatus(Object status) {
        return findByProperty(STATUS, status);
    }

    public List findAll() {
        log.debug("finding all IusseBill instances");
        try {
            String queryString = "from IusseBill";
            Query queryObject = getSession().createQuery(queryString);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
    }

    public IusseBill merge(IusseBill detachedInstance) {
        log.debug("merging IusseBill instance");
        try {
            IusseBill result = (IusseBill) getSession().merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(IusseBill instance) {
        log.debug("attaching dirty IusseBill instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

    public void attachClean(IusseBill instance) {
        log.debug("attaching clean IusseBill instance");
        try {
            getSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
}
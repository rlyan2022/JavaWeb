package HibernateDao;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

import java.util.List;

/**
 * Data access object (DAO) for domain model class Eceipt.
 *
 * @author MyEclipse Persistence Tools
 * @see Eceipt
 */

public class EceiptDAO extends BaseHibernateDAO {
    private static final Log log = LogFactory.getLog(EceiptDAO.class);

    // property constants
    public static final String RE_TYPE = "reType";

    public static final String QTY = "qty";

    public static final String BILL_NO = "billNo";

    public static final String ACTUAL_QTY = "actualQty";

    public static final String AD_JU_AMT = "adJuAmt";

    public void save(Eceipt transientInstance) {
        log.debug("saving Eceipt instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }

    public void delete(Eceipt persistentInstance) {
        log.debug("deleting Eceipt instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }

    public Eceipt findById(Integer id) {
        log.debug("getting Eceipt instance with id: " + id);
        try {
            Eceipt instance = (Eceipt) getSession().get("HibernateDao.Eceipt",
                    id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }

    public List findByExample(Eceipt instance) {
        log.debug("finding Eceipt instance by example");
        try {
            List results = getSession().createCriteria("HibernateDao.Eceipt")
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
        log.debug("finding Eceipt instance with property: " + propertyName
                + ", value: " + value);
        try {
            String queryString = "from Eceipt as model where model."
                    + propertyName + "= ?";
            Query queryObject = getSession().createQuery(queryString);
            queryObject.setParameter(0, value);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find by property name failed", re);
            throw re;
        }
    }

    public List findByReType(Object reType) {
        return findByProperty(RE_TYPE, reType);
    }

    public List findByQty(Object qty) {
        return findByProperty(QTY, qty);
    }

    public List findByBillNo(Object billNo) {
        return findByProperty(BILL_NO, billNo);
    }

    public List findByActualQty(Object actualQty) {
        return findByProperty(ACTUAL_QTY, actualQty);
    }

    public List findByAdJuAmt(Object adJuAmt) {
        return findByProperty(AD_JU_AMT, adJuAmt);
    }

    public List findAll() {
        log.debug("finding all Eceipt instances");
        try {
            String queryString = "from Eceipt";
            Query queryObject = getSession().createQuery(queryString);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
    }

    public Eceipt merge(Eceipt detachedInstance) {
        log.debug("merging Eceipt instance");
        try {
            Eceipt result = (Eceipt) getSession().merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(Eceipt instance) {
        log.debug("attaching dirty Eceipt instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

    public void attachClean(Eceipt instance) {
        log.debug("attaching clean Eceipt instance");
        try {
            getSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
}
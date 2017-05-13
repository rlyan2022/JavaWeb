package HibernateDao;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

import java.util.List;

/**
 * Data access object (DAO) for domain model class Iusse.
 *
 * @author MyEclipse Persistence Tools
 * @see Iusse
 */

public class IusseDAO extends BaseHibernateDAO {
    private static final Log log = LogFactory.getLog(IusseDAO.class);

    // property constants
    public static final String IS_TYPE = "isType";

    public static final String QTY = "qty";

    public static final String ACTUAL_QTY = "actualQty";

    public static final String AD_JU_AMT = "adJuAmt";

    public static final String BILL_NO = "billNo";

    public void save(Iusse transientInstance) {
        log.debug("saving Iusse instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }

    public void delete(Iusse persistentInstance) {
        log.debug("deleting Iusse instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }

    public Iusse findById(Integer id) {
        log.debug("getting Iusse instance with id: " + id);
        try {
            Iusse instance = (Iusse) getSession().get("HibernateDao.Iusse", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }

    public List findByExample(Iusse instance) {
        log.debug("finding Iusse instance by example");
        try {
            List results = getSession().createCriteria("HibernateDao.Iusse")
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
        log.debug("finding Iusse instance with property: " + propertyName
                + ", value: " + value);
        try {
            String queryString = "from Iusse as model where model."
                    + propertyName + "= ?";
            Query queryObject = getSession().createQuery(queryString);
            queryObject.setParameter(0, value);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find by property name failed", re);
            throw re;
        }
    }

    public List findByIsType(Object isType) {
        return findByProperty(IS_TYPE, isType);
    }

    public List findByQty(Object qty) {
        return findByProperty(QTY, qty);
    }

    public List findByActualQty(Object actualQty) {
        return findByProperty(ACTUAL_QTY, actualQty);
    }

    public List findByAdJuAmt(Object adJuAmt) {
        return findByProperty(AD_JU_AMT, adJuAmt);
    }

    public List findByBillNo(Object billNo) {
        return findByProperty(BILL_NO, billNo);
    }

    public List findAll() {
        log.debug("finding all Iusse instances");
        try {
            String queryString = "from Iusse";
            Query queryObject = getSession().createQuery(queryString);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
    }

    public Iusse merge(Iusse detachedInstance) {
        log.debug("merging Iusse instance");
        try {
            Iusse result = (Iusse) getSession().merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(Iusse instance) {
        log.debug("attaching dirty Iusse instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

    public void attachClean(Iusse instance) {
        log.debug("attaching clean Iusse instance");
        try {
            getSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
}
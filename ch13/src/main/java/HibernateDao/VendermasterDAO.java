package HibernateDao;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

import java.util.List;

/**
 * Data access object (DAO) for domain model class Vendermaster.
 *
 * @author MyEclipse Persistence Tools
 * @see Vendermaster
 */

public class VendermasterDAO extends BaseHibernateDAO {
    private static final Log log = LogFactory.getLog(VendermasterDAO.class);

    // property constants
    public static final String VEND_DESC = "vendDesc";

    public static final String VEND_ADDR = "vendAddr";

    public static final String VEND_CITY = "vendCity";

    public static final String VEND_NATI = "vendNati";

    public static final String VEND_PHONE = "vendPhone";

    public static final String VEND_POST = "vendPost";

    public static final String VEND_EMAIL = "vendEmail";

    public static final String TRADE_AMOUNT = "tradeAmount";

    public static final String CONT_MAN = "contMan";

    public void save(Vendermaster transientInstance) {
        log.debug("saving Vendermaster instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }

    public void delete(Vendermaster persistentInstance) {
        log.debug("deleting Vendermaster instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }

    public Vendermaster findById(String id) {
        log.debug("getting Vendermaster instance with id: " + id);
        try {
            Vendermaster instance = (Vendermaster) getSession().get(
                    "HibernateDao.Vendermaster", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }

    public List findByExample(Vendermaster instance) {
        log.debug("finding Vendermaster instance by example");
        try {
            List results = getSession().createCriteria(
                    "HibernateDao.Vendermaster").add(Example.create(instance))
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
        log.debug("finding Vendermaster instance with property: "
                + propertyName + ", value: " + value);
        try {
            String queryString = "from Vendermaster as model where model."
                    + propertyName + "= ?";
            Query queryObject = getSession().createQuery(queryString);
            queryObject.setParameter(0, value);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find by property name failed", re);
            throw re;
        }
    }

    public List findByVendDesc(Object vendDesc) {
        return findByProperty(VEND_DESC, vendDesc);
    }

    public List findByVendAddr(Object vendAddr) {
        return findByProperty(VEND_ADDR, vendAddr);
    }

    public List findByVendCity(Object vendCity) {
        return findByProperty(VEND_CITY, vendCity);
    }

    public List findByVendNati(Object vendNati) {
        return findByProperty(VEND_NATI, vendNati);
    }

    public List findByVendPhone(Object vendPhone) {
        return findByProperty(VEND_PHONE, vendPhone);
    }

    public List findByVendPost(Object vendPost) {
        return findByProperty(VEND_POST, vendPost);
    }

    public List findByVendEmail(Object vendEmail) {
        return findByProperty(VEND_EMAIL, vendEmail);
    }

    public List findByTradeAmount(Object tradeAmount) {
        return findByProperty(TRADE_AMOUNT, tradeAmount);
    }

    public List findByContMan(Object contMan) {
        return findByProperty(CONT_MAN, contMan);
    }

    public List findAll() {
        log.debug("finding all Vendermaster instances");
        try {
            String queryString = "from Vendermaster";
            Query queryObject = getSession().createQuery(queryString);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
    }

    public Vendermaster merge(Vendermaster detachedInstance) {
        log.debug("merging Vendermaster instance");
        try {
            Vendermaster result = (Vendermaster) getSession().merge(
                    detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(Vendermaster instance) {
        log.debug("attaching dirty Vendermaster instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

    public void attachClean(Vendermaster instance) {
        log.debug("attaching clean Vendermaster instance");
        try {
            getSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
}
package HibernateDao;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

import java.util.List;

/**
 * Data access object (DAO) for domain model class Actionmaster.
 *
 * @author MyEclipse Persistence Tools
 * @see Actionmaster
 */

public class ActionmasterDAO extends BaseHibernateDAO {
    private static final Log log = LogFactory.getLog(ActionmasterDAO.class);

    // property constants
    public static final String ACTION_DESC = "actionDesc";

    public static final String ACTION_GROUP = "actionGroup";

    public void save(Actionmaster transientInstance) {
        log.debug("saving Actionmaster instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }

    public void delete(Actionmaster persistentInstance) {
        log.debug("deleting Actionmaster instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }

    public Actionmaster findById(String id) {
        log.debug("getting Actionmaster instance with id: " + id);
        try {
            Actionmaster instance = (Actionmaster) getSession().get(
                    "HibernateDao.Actionmaster", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }

    public List findByExample(Actionmaster instance) {
        log.debug("finding Actionmaster instance by example");
        try {
            List results = getSession().createCriteria(
                    "HibernateDao.Actionmaster").add(Example.create(instance))
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
        log.debug("finding Actionmaster instance with property: "
                + propertyName + ", value: " + value);
        try {
            String queryString = "from Actionmaster as model where model."
                    + propertyName + "= ?";
            Query queryObject = getSession().createQuery(queryString);
            queryObject.setParameter(0, value);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find by property name failed", re);
            throw re;
        }
    }

    public List findByActionDesc(Object actionDesc) {
        return findByProperty(ACTION_DESC, actionDesc);
    }

    public List findByActionGroup(Object actionGroup) {
        return findByProperty(ACTION_GROUP, actionGroup);
    }

    public List findAll() {
        log.debug("finding all Actionmaster instances");
        try {
            String queryString = "from Actionmaster as model order by model.actionGroup";
            Query queryObject = getSession().createQuery(queryString);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
    }

    public List findGroup() {
        log.debug("finding all Actionmaster instances");
        try {
            String queryString = "select distinct model.actionGroup from Actionmaster as model";
            Query queryObject = getSession().createQuery(queryString);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find action_group failed", re);
            throw re;
        }
    }

    public Actionmaster merge(Actionmaster detachedInstance) {
        log.debug("merging Actionmaster instance");
        try {
            Actionmaster result = (Actionmaster) getSession().merge(
                    detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(Actionmaster instance) {
        log.debug("attaching dirty Actionmaster instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

    public void attachClean(Actionmaster instance) {
        log.debug("attaching clean Actionmaster instance");
        try {
            getSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
}
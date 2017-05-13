package HibernateDao;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

import java.util.List;

/**
 * Data access object (DAO) for domain model class Employee.
 *
 * @author MyEclipse Persistence Tools
 * @see Employee
 */

public class EmployeeDAO extends BaseHibernateDAO {
    private static final Log log = LogFactory.getLog(EmployeeDAO.class);

    // property constants
    public static final String EMP_DESC = "empDesc";

    public static final String PASSWORD = "password";

    public static final String FOLK = "folk";

    public static final String MARRI = "marri";

    public static final String FAMILY = "family";

    public static final String COLLAGE = "collage";

    public static final String SPECI = "speci";

    public static final String WAGE = "wage";

    public void save(Employee transientInstance) {
        log.debug("saving Employee instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }

    public void delete(Employee persistentInstance) {
        log.debug("deleting Employee instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }

    public Employee findById(String id) {
        log.debug("getting Employee instance with id: " + id);
        try {
            Employee instance = (Employee) getSession().get(
                    "HibernateDao.Employee", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }

    public List findByExample(Employee instance) {
        log.debug("finding Employee instance by example");
        try {
            List results = getSession().createCriteria("HibernateDao.Employee")
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
        log.debug("finding Employee instance with property: " + propertyName
                + ", value: " + value);
        try {
            String queryString = "from Employee as model where model."
                    + propertyName + "= ?";
            Query queryObject = getSession().createQuery(queryString);
            queryObject.setParameter(0, value);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find by property name failed", re);
            throw re;
        }
    }

    public List findByEmpDesc(Object empDesc) {
        return findByProperty(EMP_DESC, empDesc);
    }

    public List findByPassword(Object password) {
        return findByProperty(PASSWORD, password);
    }

    public List findByFolk(Object folk) {
        return findByProperty(FOLK, folk);
    }

    public List findByMarri(Object marri) {
        return findByProperty(MARRI, marri);
    }

    public List findByFamily(Object family) {
        return findByProperty(FAMILY, family);
    }

    public List findByCollage(Object collage) {
        return findByProperty(COLLAGE, collage);
    }

    public List findBySpeci(Object speci) {
        return findByProperty(SPECI, speci);
    }

    public List findByWage(Object wage) {
        return findByProperty(WAGE, wage);
    }

    public List findAll() {
        log.debug("finding all Employee instances");
        try {
            String queryString = "from Employee";
            Query queryObject = getSession().createQuery(queryString);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
    }

    public Employee merge(Employee detachedInstance) {
        log.debug("merging Employee instance");
        try {
            Employee result = (Employee) getSession().merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(Employee instance) {
        log.debug("attaching dirty Employee instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

    public void attachClean(Employee instance) {
        log.debug("attaching clean Employee instance");
        try {
            getSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
}
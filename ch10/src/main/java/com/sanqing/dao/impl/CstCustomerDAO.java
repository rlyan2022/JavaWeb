package com.sanqing.dao.impl;

import com.sanqing.dao.ICstCustomerDAO;
import com.sanqing.dao.ICstManagerDAO;
import com.sanqing.hb.HibernateSessionFactory;
import com.sanqing.po.CstCustomer;
import com.sanqing.po.CstManager;
import com.sanqing.struts.form.CustomerForm;
import com.sanqing.struts.form.ReportForm;
import com.sanqing.util.PageResult;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.impl.CriteriaImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CstCustomerDAO extends HibernateDaoSupport implements
        ICstCustomerDAO {
    private static final Log log = LogFactory.getLog(CstCustomerDAO.class);
    private ICstManagerDAO cstManDao = null;

    public ICstManagerDAO getCstManDao() {
        return cstManDao;
    }

    public void setCstManDao(ICstManagerDAO cstManDao) {
        this.cstManDao = cstManDao;
    }

    protected void initDao() {
        // do nothing
    }

    public List<CstCustomer> findAllCustomer() {
        try {
            String queryString = "from CstCustomer";
            return getHibernateTemplate().find(queryString);
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
    }

    public PageResult findAll(Map paramMap) {
        PageResult pgr = new PageResult();
        // 参数
        String start = (String) paramMap.get("start");
        String limit = (String) paramMap.get("limit");
        String no = (String) paramMap.get("custNo");
        String name = (String) paramMap.get("custName");
        String manName = (String) paramMap.get("custManName");
        String level = (String) paramMap.get("custLevel");
        String region = (String) paramMap.get("custRegion");
        System.out.println("第一条" + start);
        System.out.println("最后一条" + limit);
        // 按客户名称查找出客户编号，再通过主外键关系查出相应数据
        List<CstManager> lists = cstManDao.findByManName(manName);
        Long[] manId = new Long[lists.size()];
        for (int i = 0; i < manId.length; i++) {
            manId[i] = lists.get(i).getManId();
            System.out.println("aaaaaaaaaaaaa" + manId[i]);
        }
        try {
            Criteria c = getSession().createCriteria(CstCustomer.class);
            // 条件查询
            c.add(Expression.eq("custStatus", "1"));// 查询状态为1的客户

            if (StringUtils.isNotEmpty(no)) {
                c.add(Expression.like("custNo", "%" + no + "%"));// 编号
            }

            if (StringUtils.isNotEmpty(name)) {
                c.add(Expression.like("custName", "%" + name + "%"));// 客户名称
            }
            if (manId.length > 0) {
                c.add(Expression.in("cstManager.manId", manId));
            }
            if (StringUtils.isNotEmpty(level)) {
                c.add(Expression.eq("custLevel", level));// 客户等级
            }
            if (StringUtils.isNotEmpty(region)) {
                c.add(Expression.like("custRegion", "%" + region + "%"));// 地区
            }
            // 总记录条数
            Projection entityProjection = ((CriteriaImpl) c).getProjection();
            c.setProjection(Projections.rowCount()).uniqueResult();
            int rowCount = ((Number) c.uniqueResult()).intValue();
            pgr.setRowCount(rowCount);
            c.setProjection(entityProjection);

            // 分页
            if (start != null) {
                c.setFirstResult(Integer.parseInt(start));
            }
            if (limit != null) {
                c.setMaxResults(Integer.parseInt(limit));
            }
            // 把查询到的数据放到List<customerForm>里面,方便在CustomerAction中转换成json数据
            List<CstCustomer> list = c.list();
            List<CustomerForm> fList = new ArrayList<CustomerForm>();
            CustomerForm custForm = null;
            for (CstCustomer customer : list) {
                custForm = new CustomerForm();
                custForm.setCustNo(customer.getCustNo());
                custForm.setCustName(customer.getCustName());
                custForm.setCustRegion(customer.getCustRegion());
                custForm.setCustManagerName(customer.getCstManager().getManName());
                // 对客户等级进行判断
                Integer cstL = customer.getCustLevel();
                String cstLevel;
                if (cstL == 1) {
                    cstLevel = "普通客户";
                } else if (cstL == 2) {
                    cstLevel = "重点开发客户";
                } else if (cstL == 3) {
                    cstLevel = "大客户";
                } else if (cstL == 4) {
                    cstLevel = "合作伙伴";
                } else {
                    cstLevel = "战略合作伙伴";
                }
                custForm.setCustLevel(cstLevel);

                // 对客户满意度进行判断
                Integer cstS = customer.getCustSatisfy();
                String cstSatisfy;
                if (cstS == 1) {
                    cstSatisfy = "☆";
                } else if (cstS == 2) {
                    cstSatisfy = "☆☆";
                } else if (cstS == 3) {
                    cstSatisfy = "☆☆☆";
                } else if (cstS == 4) {
                    cstSatisfy = "☆☆☆☆";
                } else {
                    cstSatisfy = "☆☆☆☆";
                }
                custForm.setCustSatisfy(cstSatisfy);
                // 对客户信用度进行判断
                Integer cstC = customer.getCustSatisfy();
                String cstCredit;
                if (cstC == 1) {
                    cstCredit = "☆";
                } else if (cstC == 2) {
                    cstCredit = "☆☆";
                } else if (cstC == 3) {
                    cstCredit = "☆☆☆";
                } else if (cstC == 4) {
                    cstCredit = "☆☆☆☆";
                } else {
                    cstCredit = "☆☆☆☆";
                }
                custForm.setCustCredit(cstCredit);
                custForm.setCustAddr(customer.getCustAddr());
                custForm.setCustZip(customer.getCustZip());
                custForm.setCustTel(customer.getCustTel());
                custForm.setCustFax(customer.getCustFax());
                custForm.setCustWebsite(customer.getCustWebsite());
                custForm.setCustLicenceNo(customer.getCustLicenceNo());
                custForm.setCustChieftain(customer.getCustChieftain());
                custForm.setCustBankroll(customer.getCustBankroll());
                custForm.setCustTurnover(customer.getCustTurnover());
                custForm.setCustBank(customer.getCustBank());
                custForm.setCustBankAccount(customer.getCustBankAccount());
                custForm.setCustLocalTaxNo(customer.getCustLocalTaxNo());
                custForm.setCustNationalTaxNo(customer.getCustNationalTaxNo());
                custForm.setCustStatus(customer.getCustStatus());
                fList.add(custForm);
            }
            pgr.setData(fList);

        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
        return pgr;
    }

    // 查询客户构成
    public PageResult finCustStructure(String type) {
        PageResult pgr = new PageResult();
        Session session = HibernateSessionFactory.getSession();
        String hql = "";
        // 根据报表方式进行查询
        if ("cstLevel".equals(type)) {
            System.out.println("按客户等级查询");
            hql = "select (select b.dictItem from BasDict b where dictType='客户等级' "
                    + "and b.dictValue=c.custLevel) as "
                    + "等级,count(*) from CstCustomer c group by c.custLevel ";
        } else if ("cstSatisfy".equals(type)) {
            System.out.println("按客户满意度查询");
            hql = "select c.custSatisfy,count(*) from CstCustomer c group by c.custSatisfy";
        } else if ("cstCredit".equals(type)) {
            System.out.println("按客户信用度查询");
            hql = "select c.custCredit,count(*) from CstCustomer c group by c.custCredit";
        }
        Query query = session.createQuery(hql);
        List<Object[]> list = query.list();
        List<ReportForm> fList = new ArrayList<ReportForm>();
        ReportForm reptForm = null;
        for (Object[] obj : list) {
            reptForm = new ReportForm();
            if (obj[0] != null) {
                if (type.equals("cstLevel")) {
                    reptForm.setCustLevel(obj[0].toString());
                } else if (type.equals("cstSatisfy")) {
                    reptForm
                            .setCustSatisfy(Integer.parseInt(obj[0].toString()));
                } else if (type.equals("cstCredit")) {
                    reptForm.setCustCredit(Integer.parseInt(obj[0].toString()));
                }
            }
            reptForm.setNumber(Integer.parseInt(obj[1].toString()));
            fList.add(reptForm);
        }
        pgr.setData(fList);
        pgr.setRowCount(query.list().size());
        return pgr;
    }

    public void save(CstCustomer customer) {
        log.debug("saving CstCustomer instance");
        try {
            getHibernateTemplate().save(customer);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }

    /*
     * (non-Javadoc)
     *
     */
    public void delete(CstCustomer persistentInstance) {
        log.debug("deleting CstCustomer instance");
        try {
            getHibernateTemplate().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }

    // 根据客户编号查询客户信息
    public CstCustomer findById(String custNo) {
        log.debug("getting CstCustomer instance with id: " + custNo);
        try {
            CstCustomer instance = (CstCustomer) getHibernateTemplate().get(
                    "com.sanqing.po.CstCustomer", custNo);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }

    // 根据客户名称查询客户信息
    public List<CstCustomer> findByCustName(Object custName) {
        Criteria ctr = getSession().createCriteria(CstCustomer.class);
        ctr.add(Expression.like("custName", "%" + custName + "%"));
        return ctr.list();
    }

    public List findByExample(CstCustomer instance) {
        log.debug("finding CstCustomer instance by example");
        try {
            List results = getHibernateTemplate().findByExample(instance);
            log.debug("find by example successful, result size: "
                    + results.size());
            return results;
        } catch (RuntimeException re) {
            log.error("find by example failed", re);
            throw re;
        }
    }

    /*
     * (non-Javadoc)
     *
     */
    public List findByProperty(String propertyName, Object value) {
        log.debug("finding CstCustomer instance with property: " + propertyName
                + ", value: " + value);
        try {
            String queryString = "from CstCustomer as model where model."
                    + propertyName + "= ?";
            return getHibernateTemplate().find(queryString, value);
        } catch (RuntimeException re) {
            log.error("find by property name failed", re);
            throw re;
        }
    }

	/*
     * (non-Javadoc)
	 * 
	 */

    /*
     * (non-Javadoc)
     *
     */
    public List findByCustRegion(Object custRegion) {
        return findByProperty(CUST_REGION, custRegion);
    }

    /*
     * (non-Javadoc)
     *
     */
    public List findByCustManagerName(Object custManagerName) {
        return findByProperty(CUST_MANAGER_NAME, custManagerName);
    }

    /*
     * (non-Javadoc)
     *
     */
    public List findByCustLevel(Object custLevel) {
        return findByProperty(CUST_LEVEL, custLevel);
    }

    /*
     * (non-Javadoc)
     *
     */
    public List findByCustSatisfy(Object custSatisfy) {
        return findByProperty(CUST_SATISFY, custSatisfy);
    }

    /*
     * (non-Javadoc)
     *
     */
    public List findByCustCredit(Object custCredit) {
        return findByProperty(CUST_CREDIT, custCredit);
    }

    /*
     * (non-Javadoc)
     *
     */
    public List findByCustAddr(Object custAddr) {
        return findByProperty(CUST_ADDR, custAddr);
    }

    /*
     * (non-Javadoc)
     *
     */
    public List findByCustZip(Object custZip) {
        return findByProperty(CUST_ZIP, custZip);
    }

    /*
     * (non-Javadoc)
     *
     */
    public List findByCustTel(Object custTel) {
        return findByProperty(CUST_TEL, custTel);
    }

    /*
     * (non-Javadoc)
     *
     */
    public List findByCustFax(Object custFax) {
        return findByProperty(CUST_FAX, custFax);
    }

    /*
     * (non-Javadoc)
     *
     */
    public List findByCustWebsite(Object custWebsite) {
        return findByProperty(CUST_WEBSITE, custWebsite);
    }

    /*
     * (non-Javadoc)
     *
     */
    public List findByCustLicenceNo(Object custLicenceNo) {
        return findByProperty(CUST_LICENCE_NO, custLicenceNo);
    }

    /*
     * (non-Javadoc)
     *
     */
    public List findByCustChieftain(Object custChieftain) {
        return findByProperty(CUST_CHIEFTAIN, custChieftain);
    }

    /*
     * (non-Javadoc)
     *
     */
    public List findByCustBankroll(Object custBankroll) {
        return findByProperty(CUST_BANKROLL, custBankroll);
    }

    /*
     * (non-Javadoc)
     *
     */
    public List findByCustTurnover(Object custTurnover) {
        return findByProperty(CUST_TURNOVER, custTurnover);
    }

    /*
     * (non-Javadoc)
     *
     */
    public List findByCustBank(Object custBank) {
        return findByProperty(CUST_BANK, custBank);
    }

    /*
     * (non-Javadoc)
     *
     */
    public List findByCustBankAccount(Object custBankAccount) {
        return findByProperty(CUST_BANK_ACCOUNT, custBankAccount);
    }

    /*
     * (non-Javadoc)
     *
     */
    public List findByCustLocalTaxNo(Object custLocalTaxNo) {
        return findByProperty(CUST_LOCAL_TAX_NO, custLocalTaxNo);
    }

    /*
     * (non-Javadoc)
     *
     */
    public List findByCustNationalTaxNo(Object custNationalTaxNo) {
        return findByProperty(CUST_NATIONAL_TAX_NO, custNationalTaxNo);
    }

    /*
     * (non-Javadoc)
     *
     */
    public List findByCustStatus(Object custStatus) {
        return findByProperty(CUST_STATUS, custStatus);
    }

    public CstCustomer merge(CstCustomer detachedInstance) {
        log.debug("merging CstCustomer instance");
        try {
            CstCustomer result = (CstCustomer) getHibernateTemplate().merge(
                    detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    /*
     * (non-Javadoc)
     *
     */
    public void attachDirty(CstCustomer instance) {
        log.debug("attaching dirty CstCustomer instance");
        try {
            getHibernateTemplate().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

    /*
     * (non-Javadoc)
     *
     */
    public void attachClean(CstCustomer instance) {
        log.debug("attaching clean CstCustomer instance");
        try {
            getHibernateTemplate().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

    public static ICstCustomerDAO getFromApplicationContext(
            ApplicationContext ctx) {
        return (ICstCustomerDAO) ctx.getBean("CstCustomerDAO");
    }

    public PageResult findAll() {
        // TODO Auto-generated method stub
        return null;
    }
}
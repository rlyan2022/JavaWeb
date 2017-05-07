package com.sanqing.dao.impl;

import com.sanqing.dao.ISysRoleDAO;
import com.sanqing.dao.ISysRoleRightDAO;
import com.sanqing.dao.ISysUserDAO;
import com.sanqing.po.SysRoleRight;
import com.sanqing.po.SysUser;
import com.sanqing.struts.form.UserinfoForm;
import com.sanqing.util.PageResult;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.LockMode;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.impl.CriteriaImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SysUserDAO extends HibernateDaoSupport implements ISysUserDAO {
    private static final Log log = LogFactory.getLog(SysUserDAO.class);

    private ISysRoleDAO sysRoleDao = null;
    private ISysRoleRightDAO roleRDao = null;

    public ISysRoleDAO getSysRoleDao() {
        return sysRoleDao;
    }

    public void setSysRoleDao(ISysRoleDAO sysRoleDao) {
        this.sysRoleDao = sysRoleDao;
    }

    // ISysRoleRightDAO的GET和SET方法
    public ISysRoleRightDAO getRoleRDao() {
        return roleRDao;
    }

    public void setRoleRDao(ISysRoleRightDAO roleRDao) {
        this.roleRDao = roleRDao;
    }

    // SysRoleDAO的GET和SET方法
    public static Log getLog() {
        return log;
    }

    protected void initDao() {
    }

    public PageResult findAll(Map paramMap) {
        PageResult PageResult = new PageResult();
        // 参数
        String start = (String) paramMap.get("start");
        String limit = (String) paramMap.get("limit");
        String usrName = (String) paramMap.get("usrName");
        System.out.println("用户名称： " + usrName);

        String roleId = (String) paramMap.get("roleId");
        System.out.println("角色名称： " + roleId);
        // 根据权限查询用户信息
//		String rightCode = (String) paramMap.get("rightCode");
//		System.out.println("权限编号： " + rightCode);
//			List<SysRoleRight> srr = roleRDao.findByProperty(
//					"sysRight.rightCode", rightCode);
//			System.out.println("大小：" + srr.size());
//			Long[] rolesId = new Long[srr.size()];
//			for (int i = 0; i < srr.size(); i++) {
//				rolesId[i] = srr.get(i).getSysRole().getRoleId();
//			}

        System.out.println("第一条" + start);
        System.out.println("最后一条" + limit);
        try {
            Criteria c = getSession().createCriteria(SysUser.class);
            // 条件
            if (StringUtils.isNotEmpty(usrName)) {
                c.add(Expression.like("usrName", "%" + usrName + "%"));
            }
            if (null != roleId && !"".equals(roleId) && !roleId.equals("-1")) {
                c.add(Expression.eq("sysRole.roleId", Long.parseLong(roleId)));
            }

//			if (rolesId.length>0) {
//				c.add(Expression.in("sysRole.roleId", rolesId));
//			}

            // 总条数
            Projection entityProjection = ((CriteriaImpl) c).getProjection();
            c.setProjection(Projections.rowCount()).uniqueResult();
            int rowCount = ((Number) c.uniqueResult()).intValue();
            PageResult.setRowCount(rowCount);
            c.setProjection(entityProjection);
            System.out.println("过去吧");
            // 分页
            if (start != null) {
                c.setFirstResult(Integer.parseInt(start));
            }
            if (limit != null) {
                c.setMaxResults(Integer.parseInt(limit));
            }
            List<SysUser> list = c.list();
            List<UserinfoForm> uList = new ArrayList<UserinfoForm>();
            UserinfoForm userinfoForm = null;
            for (SysUser user : list) {
                userinfoForm = new UserinfoForm();
                userinfoForm.setUsrId(user.getUsrId());
                userinfoForm.setUsrName(user.getUsrName());
                userinfoForm.setRoleName(user.getSysRole().getRoleName());
                List<SysRoleRight> right = roleRDao.findByProperty(
                        "sysRole.roleId", user.getSysRole().getRoleId());
                for (SysRoleRight sRr : right) {
                    userinfoForm.setRightText(sRr.getSysRight().getRightText());
                }
                userinfoForm.setUsrPassword(user.getUsrPassword());
                userinfoForm.setUsrFlag(user.getUsrFlag());
                uList.add(userinfoForm);
            }
            PageResult.setData(uList);

        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
        return PageResult;
    }

    public void save(SysUser transientInstance) {
        log.debug("saving SysUser instance");
        try {
            getHibernateTemplate().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }

    public void delete(SysUser persistentInstance) {
        log.debug("deleting SysUser instance");
        try {
            getHibernateTemplate().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }

    public SysUser findById(Long id) {
        log.debug("getting SysUser instance with id: " + id);
        try {
            SysUser instance = (SysUser) getHibernateTemplate().get(
                    "com.sanqing.po.SysUser", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }

    public List findByExample(SysUser instance) {
        log.debug("finding SysUser instance by example");
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

    public List findByProperty(String propertyName, Object value) {
        log.debug("finding SysUser instance with property: " + propertyName
                + ", value: " + value);
        try {
            String queryString = "from SysUser as model where model."
                    + propertyName + "= ?";
            return getHibernateTemplate().find(queryString, value);
        } catch (RuntimeException re) {
            log.error("find by property name failed", re);
            throw re;
        }
    }

    public List findByUsrName(Object usrName) {
        return findByProperty(USR_NAME, usrName);
    }

    public List findByUsrPassword(Object usrPassword) {
        return findByProperty(USR_PASSWORD, usrPassword);
    }

    public List findByUsrFlag(Object usrFlag) {
        return findByProperty(USR_FLAG, usrFlag);
    }

    public List findAll() {
        log.debug("finding all SysUser instances");
        try {
            String queryString = "from SysUser";
            return getHibernateTemplate().find(queryString);
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
    }

    public SysUser merge(SysUser detachedInstance) {
        log.debug("merging SysUser instance");
        try {
            SysUser result = (SysUser) getHibernateTemplate().merge(
                    detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(SysUser instance) {
        log.debug("attaching dirty SysUser instance");
        try {
            getHibernateTemplate().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

    public void attachClean(SysUser instance) {
        log.debug("attaching clean SysUser instance");
        try {
            getHibernateTemplate().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

    public static ISysUserDAO getFromApplicationContext(ApplicationContext ctx) {
        return (ISysUserDAO) ctx.getBean("SysUserDAO");
    }
}
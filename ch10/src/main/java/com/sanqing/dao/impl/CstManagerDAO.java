package com.sanqing.dao.impl;

import com.sanqing.dao.ICstManagerDAO;
import com.sanqing.po.CstManager;
import com.sanqing.po.CstService;
import com.sanqing.struts.form.CstManagerForm;
import com.sanqing.util.PageResult;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.impl.CriteriaImpl;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CstManagerDAO extends HibernateDaoSupport implements
        ICstManagerDAO {
    private static final Log log = LogFactory.getLog(CstManagerDAO.class);

    protected void initDao() {
    }

    /*
     * (non-Javadoc)
     *
     */
    public PageResult findAll(Map paramMap) {
        PageResult pgr = new PageResult();
        // 获得参数
        String start = (String) paramMap.get("start");
        String limit = (String) paramMap.get("limit");

        try {
            Criteria c = getSession().createCriteria(CstManager.class);
            // 总记录条数
            Projection entityProjection = ((CriteriaImpl) c).getProjection();
            c.setProjection(Projections.rowCount()).uniqueResult();
            int rowCount = ((Number) c.uniqueResult()).intValue();
            pgr.setRowCount(rowCount);
            c.setProjection(entityProjection);
            // 分页
            if (start != null) {
                c.setFirstResult(new Integer(start));
            }
            if (limit != null) {
                c.setMaxResults(new Integer(limit));
            }

            List<CstManager> list = c.list();
            List<CstManagerForm> fList = new ArrayList<CstManagerForm>();
            CstManagerForm cstManagerForm = null;
            for (CstManager cstManager : list) {
                cstManagerForm = new CstManagerForm();
                cstManagerForm.setCustManId(cstManager.getManId());
                cstManagerForm.setManName(cstManager.getManName());
                fList.add(cstManagerForm);
            }
            pgr.setData(fList);
        } catch (RuntimeException re) {
            throw re;
        }
        return pgr;
    }

    public String findForeignExists(String custNo) {
        String result = "删除失败该客户有";
        List<CstService> cstService = getSession().createQuery("select * from CstService c where c.cstCustomer.custNo='" + custNo + "'").list();
        if (cstService != null) {
            result += "交往记录";
        }
//			else if(cstLinkman=)
        return result;
    }

    public List<CstManager> findByManName(String manName) {
        Criteria c = getSession().createCriteria(CstManager.class);
        c.add(Expression.like("manName", "%" + manName + "%"));
        return c.list();
    }

    public List<CstManager> findManId(String manName) {
        Criteria c = getSession().createCriteria(CstManager.class);
        c.add(Expression.eq("manName", manName));
        return c.list();
    }
}
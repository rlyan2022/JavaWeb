package com.sanqing.action;

import com.sanqing.po.ProductType;
import com.sanqing.service.ProductTypeService;
import com.sanqing.util.PageView;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * 产品类别显示
 */
@Controller("productTypeAction")
@Scope("prototype")
public class ProductTypeAction extends BaseAction {

    @Resource
    private ProductTypeService productTypeService;

    // 产品类型编号
    private String producttypeNO;
    // 产品类型名称
    private String producttypeName;

    @Override
    public String execute() throws Exception {
        PageView<ProductType> pageView = new PageView<ProductType>(5, getPage());
        StringBuffer jpql = new StringBuffer("");
        List<Object> params = new ArrayList<Object>();
        if ("true".equals(getQuery())) {
            if (producttypeName != null && !"".equals(producttypeName.trim())) {
                if (params.size() > 0) jpql.append(" and ");
                jpql.append(" o.producttypeName like ?").append(params.size() + 1);
                params.add("%" + producttypeName + "%");
            }
            if (producttypeNO != null && !"".equals(producttypeNO.trim())) {
                if (params.size() > 0) jpql.append(" and ");
                jpql.append(" o.producttypeNO like ?").append(params.size() + 1);
                params.add("%" + producttypeNO + "%");
            }
            pageView.setQueryResult(productTypeService.getScrollData(pageView.getFirstResult(), pageView.getMaxresult(), jpql.toString(), params.toArray()));
        } else {
            pageView.setQueryResult(productTypeService.getScrollData(pageView.getFirstResult(), pageView.getMaxresult()));
        }
        HttpServletRequest request = ServletActionContext.getRequest();
        request.setAttribute("pageView", pageView);
        return this.SUCCESS;
    }

    public String getProducttypeNO() {
        return producttypeNO;
    }

    public void setProducttypeNO(String producttypeNO) {
        this.producttypeNO = producttypeNO;
    }

    public String getProducttypeName() {
        return producttypeName;
    }

    public void setProducttypeName(String producttypeName) {
        this.producttypeName = producttypeName;
    }
}

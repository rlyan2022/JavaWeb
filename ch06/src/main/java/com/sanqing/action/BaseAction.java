package com.sanqing.action;

import com.opensymphony.xwork2.ActionSupport;

/**
 * 基本的action
 */
public class BaseAction extends ActionSupport {
    public Integer page;    //当前页信息
    public String query;    //是否为条件查询

    public Integer getPage() {//获得当前页信息
        return page = (page == null || page < 1) ? 1 : page;
    }

    public void setPage(Integer page) {//设置当前页信息
        this.page = page;
    }

    public String getQuery() {//获得query信息
        return query;
    }

    public void setQuery(String query) {//设置query信息
        this.query = query;
    }
}

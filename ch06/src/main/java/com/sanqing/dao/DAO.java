package com.sanqing.dao;

import com.sanqing.util.QueryResult;

import java.io.Serializable;
import java.util.LinkedHashMap;


public interface DAO<T> {
    public long getCount();//获得记录总数

    public void clear();//清除一级缓存的数据

    public void save(Object entity);//保存记录

    public void update(Object entity);//更新记录

    public void delete(Serializable... entityids);//删除记录

    public T find(Serializable entityId);//通过主键获得记录

    public QueryResult<T> getScrollData(int firstindex, int maxresult, String wherejpql,
                                        Object[] queryParams, LinkedHashMap<String, String> orderby);//获得分页记录

    public QueryResult<T> getScrollData(int firstindex, int maxresult,
                                        String wherejpql, Object[] queryParams);//获得分页记录

    public QueryResult<T> getScrollData(int firstindex, int maxresult,
                                        LinkedHashMap<String, String> orderby);//获得分页记录

    public QueryResult<T> getScrollData(int firstindex, int maxresult);        //获得分页记录

    public QueryResult<T> getScrollData();//获得分页记录
}

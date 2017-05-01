package com.sanqing.dao;

import com.sanqing.util.GenericsUtils;
import com.sanqing.util.QueryResult;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.LinkedHashMap;

@SuppressWarnings("unchecked")
@Transactional
public abstract class DaoSupport<T> implements DAO<T> {
    protected Class<T> entityClass = GenericsUtils.getSuperClassGenricType(this.getClass());//获得该类的父类的泛型参数的实际类型
    @PersistenceContext
    protected EntityManager em;

    public void clear() {//清除一级缓存的数据
        em.clear();
    }

    public void delete(Serializable... entityids) {//删除记录
        for (Object id : entityids) {
            em.remove(em.getReference(this.entityClass, id));
        }
    }

    @Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
    public T find(Serializable entityId) {//通过主键获得记录
        if (entityId == null) throw new RuntimeException(this.entityClass.getName() + ":传入的实体id不能为空");
        return em.find(this.entityClass, entityId);
    }

    public void save(Object entity) {//保存记录
        em.persist(entity);
    }

    @Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
    public long getCount() {    //获得记录总数
        return (Long) em.createQuery("select count(" + getCountField(this.entityClass) + ") from " + getEntityName(this.entityClass) + " o").getSingleResult();
    }

    protected static <E> String getCountField(Class<E> clazz) {
        String out = "o";
        try {
            PropertyDescriptor[] propertyDescriptors = Introspector.getBeanInfo(clazz).getPropertyDescriptors();
            for (PropertyDescriptor propertydesc : propertyDescriptors) {
                Method method = propertydesc.getReadMethod();
                if (method != null && method.isAnnotationPresent(EmbeddedId.class)) {
                    PropertyDescriptor[] ps = Introspector.getBeanInfo(propertydesc.getPropertyType()).getPropertyDescriptors();
                    out = "o." + propertydesc.getName() + "." + (!ps[1].getName().equals("class") ? ps[1].getName() : ps[0].getName());
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return out;
    }

    protected static void setQueryParams(Query query, Object[] queryParams) {
        if (queryParams != null && queryParams.length > 0) {
            for (int i = 0; i < queryParams.length; i++) {
                query.setParameter(i + 1, queryParams[i]);
            }
        }
    }

    protected static String buildOrderby(LinkedHashMap<String, String> orderby) {//组装order by语句
        StringBuffer orderbyql = new StringBuffer("");
        if (orderby != null && orderby.size() > 0) {
            orderbyql.append(" order by ");
            for (String key : orderby.keySet()) {
                orderbyql.append("o.").append(key).append(" ").append(orderby.get(key)).append(",");
            }
            orderbyql.deleteCharAt(orderbyql.length() - 1);
        }
        return orderbyql.toString();
    }

    protected static <E> String getEntityName(Class<E> clazz) {//获取实体的名称
        String entityname = clazz.getSimpleName();
        Entity entity = clazz.getAnnotation(Entity.class);
        if (entity.name() != null && !"".equals(entity.name())) {
            entityname = entity.name();
        }
        return entityname;
    }

    public void update(Object entity) {//更新记录
        em.merge(entity);
    }

    @Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
    public QueryResult<T> getScrollData(int firstindex,
                                        int maxresult, LinkedHashMap<String, String> orderby) {//获得分页记录
        return getScrollData(firstindex, maxresult, null, null, orderby);
    }

    @Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
    public QueryResult<T> getScrollData(int firstindex,
                                        int maxresult, String wherejpql, Object[] queryParams) {//获得分页记录
        return getScrollData(firstindex, maxresult, wherejpql, queryParams, null);
    }

    @Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
    public QueryResult<T> getScrollData(int firstindex, int maxresult) {//获得分页记录
        return getScrollData(firstindex, maxresult, null, null, null);
    }

    @Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
    public QueryResult<T> getScrollData() {
        return getScrollData(-1, -1);
    }

    @Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
    public QueryResult<T> getScrollData(int firstindex, int maxresult, String wherejpql,
                                        Object[] queryParams, LinkedHashMap<String, String> orderby) {//获得分页记录
        QueryResult qr = new QueryResult<T>();//查询记录结果
        String entityname = getEntityName(this.entityClass);//获得实体名称
        Query query = em.createQuery("select o from " +
                entityname + " o " + (wherejpql == null
                || "".equals(wherejpql.trim()) ? "" :
                "where " + wherejpql) + buildOrderby(orderby));//执行查询
        setQueryParams(query, queryParams);//设置查询参数
        if (firstindex != -1 && maxresult != -1) //两个参数都不是-1的时候才分页
            query.setFirstResult(firstindex).
                    setMaxResults(maxresult);//设置查询记录的起始位置和查询最大数
        qr.setResultlist(query.getResultList());//设置查询的记录
        query = em.createQuery("select count(" +
                getCountField(this.entityClass) + ") " +
                "from " + entityname + " o " + (wherejpql == null ||
                "".equals(wherejpql.trim()) ? "" : "where " + wherejpql));
        setQueryParams(query, queryParams);//设置查询参数
        qr.setTotalrecord((Long) query.getSingleResult());//设置查询记录总数
        return qr;//返回查询记录结果
    }


}

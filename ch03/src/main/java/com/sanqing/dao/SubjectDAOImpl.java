package com.sanqing.dao;

import com.sanqing.hibernate.HibernateSessionFactory;
import com.sanqing.po.Subject;
import com.sanqing.util.Page;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class SubjectDAOImpl implements SubjectDAO {
    public void addSubject(Subject subject) {
        Session session = HibernateSessionFactory.getSession();//获得Session对象
        Transaction transaction = null;//声明一个事务对象
        try {
            transaction = session.beginTransaction();//开启事务
            session.save(subject);//保存试题信息
            transaction.commit();//提交事务
        } catch (Exception ex) {
            ex.printStackTrace();
            transaction.rollback();//事务回滚
        }
        HibernateSessionFactory.closeSession();//关闭Session对象
    }

    public Subject findSubjectByTitle(String subjectTitle) {
        Session session = HibernateSessionFactory.getSession();//获得Session对象
        Query query = session.createQuery("from Subject as sub where sub.subjectTitle = ?");
        query.setString(0, subjectTitle);
        List list = query.list();                    //查询结果保存到list中
        HibernateSessionFactory.closeSession();        //关闭Session对象
        if (list.size() == 0) {
            return null;                            //返回null
        } else {
            return (Subject) list.get(0);            //返回第一个试题
        }
    }

    public List<Subject> findSubjectByPage(Page page) {
        Session session = HibernateSessionFactory.getSession();//获得Session对象
        Query query = session.createQuery("from Subject");
        query.setMaxResults(page.getEveryPage());//设置查询记录数
        query.setFirstResult(page.getBeginIndex());//设置查询记录起始位置
        List list = query.list();                    //查询结果保存到list中
        HibernateSessionFactory.closeSession();//关闭Session对象
        return list;
    }

    public int findSubjectCount() {
        Session session = HibernateSessionFactory.getSession();//获得Session对象
        Query query = session.createQuery("from Subject");
        List list = query.list();                    //查询结果保存到list中
        int count = list.size();
        HibernateSessionFactory.closeSession();//关闭Session对象
        return count;
    }

    public Subject findSubjectByID(int subjectID) {
        Session session = HibernateSessionFactory.getSession();//获得Session对象
        Subject subject = (Subject) session.get(Subject.class, subjectID);
        HibernateSessionFactory.closeSession();        //关闭Session对象
        return subject;
    }

    public void updateSubject(Subject subject) {
        Session session = HibernateSessionFactory.getSession();//获得Session对象
        Transaction transaction = null;//声明一个事务对象
        try {
            transaction = session.beginTransaction();//开启事务
            session.update(subject);//更新试题信息
            transaction.commit();//提交事务
        } catch (Exception ex) {
            ex.printStackTrace();
            transaction.rollback();//事务回滚
        }
        HibernateSessionFactory.closeSession();//关闭Session对象
    }

    public void deleteSubject(int subjectID) {
        Session session = HibernateSessionFactory.getSession();//获得Session对象
        Subject subject = (Subject) session.get(Subject.class, subjectID);
        Transaction transaction = null;//声明一个事务对象
        try {
            transaction = session.beginTransaction();//开启事务
            session.delete(subject);
            transaction.commit();//提交事务
        } catch (Exception ex) {
            ex.printStackTrace();
            transaction.rollback();//事务回滚
        }
        HibernateSessionFactory.closeSession();//关闭Session对象
    }

    public List<Subject> likeQueryByTitle(String subjectTitle, Page page) {
        Session session = HibernateSessionFactory.getSession();//获得Session对象
        Query query = session.createQuery("from Subject as sub where sub.subjectTitle like :title ");
        query.setString("title", "%" + subjectTitle + "%");
        query.setMaxResults(page.getEveryPage());//设置查询记录数
        query.setFirstResult(page.getBeginIndex());//设置查询记录起始位置
        List list = query.list();                    //查询结果保存到list中
        HibernateSessionFactory.closeSession();//关闭Session对象
        return list;
    }

    public int findLinkQueryCount(String subjectTitle) {
        Session session = HibernateSessionFactory.getSession();//获得Session对象
        Query query = session.createQuery("from Subject as sub where sub.subjectTitle like :title ");
        query.setString("title", "%" + subjectTitle + "%");
        List list = query.list();                    //查询结果保存到list中
        int count = list.size();
        HibernateSessionFactory.closeSession();//关闭Session对象
        return count;
    }

    public List<Subject> randomFindSubject(int number) {
        Session session = HibernateSessionFactory.getSession();//获得Session对象
        Query query = session.createQuery("from Subject as sub order by rand()");
        query.setMaxResults(number);//设置查询记录数
        List list = query.list();                    //查询结果保存到list中
        HibernateSessionFactory.closeSession();//关闭Session对象
        return list;
    }
}

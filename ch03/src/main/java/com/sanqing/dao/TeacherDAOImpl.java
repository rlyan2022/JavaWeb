package com.sanqing.dao;

import com.sanqing.hibernate.HibernateSessionFactory;
import com.sanqing.po.Teacher;
import org.hibernate.Session;

public class TeacherDAOImpl implements TeacherDAO {
    public Teacher findByTeacherID(String teacherID) {
        Session session = HibernateSessionFactory.getSession();//获得Session对象
        Teacher teacher = (Teacher) session.get(Teacher.class, teacherID);
        HibernateSessionFactory.closeSession();//关闭Session对象
        return teacher;
    }
}

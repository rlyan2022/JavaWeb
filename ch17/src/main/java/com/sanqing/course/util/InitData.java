package com.sanqing.course.util;

import org.hibernate.Session;

import com.sanqing.course.model.Course;
import com.sanqing.course.model.Student;
import com.sanqing.course.model.Teacher;
import com.sanqing.course.model.Team;
import com.sanqing.course.model.User;





public class InitData {

	public static void main(String[] args) {

		InitData.saveUser();
		InitData.saveTeam();
		InitData.saveCourse();
		InitData.saveTeacher();
		InitData.saveStudent();
	}

	public static void saveUser() {
		Session session = null;
		try {
			session = HibernateUtils.getSession();
			session.beginTransaction();

			User u1 = new User();
			u1.setName("Tom");
			u1.setPassword("Tom");
			session.save(u1);

			User u2 = new User();
			u2.setName("Jerry");
			u2.setPassword("Jerry");
			session.save(u2);

			User u3 = new User();
			u3.setName("admin");
			u3.setPassword("admin");
			session.save(u3);

			User u4 = new User();
			u4.setName("jack");
			u4.setPassword("jack");
			session.save(u4);

			session.getTransaction().commit();
		}catch(Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}finally {
			HibernateUtils.closeSession(session);
		}
	}

	public static void saveTeam() {
		Session session = null;
		try {
			session = HibernateUtils.getSession();
			session.beginTransaction();

			Team t1 = new Team();
			t1.setName("计算机1班");
			session.save(t1);

			Team t2 = new Team();
			t2.setName("计算机2班");
			session.save(t2);

			Team t3 = new Team();
			t3.setName("计算机3班");
			session.save(t3);

			Team t4 = new Team();
			t4.setName("计算机4班");
			session.save(t4);

			session.getTransaction().commit();
		}catch(Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}finally {
			HibernateUtils.closeSession(session);
		}
	}

	public static void saveCourse() {
		Session session = null;
		try {
			session = HibernateUtils.getSession();
			session.beginTransaction();

			Course c1 = new Course();
			c1.setName("软件工程");
			session.save(c1);

			Course c2 = new Course();
			c2.setName("数据库系统概论");
			session.save(c2);

			Course c3 = new Course();
			c3.setName("数据结构");
			session.save(c3);

			Course c4 = new Course();
			c4.setName("计算机组成原理");
			session.save(c4);

			session.getTransaction().commit();
		}catch(Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}finally {
			HibernateUtils.closeSession(session);
		}
	}

	public static void saveTeacher() {
		Session session = null;
		try {
			session = HibernateUtils.getSession();
			session.beginTransaction();

			Teacher t1 = new Teacher();
			t1.setName("张三风");
			session.save(t1);

			Teacher t2 = new Teacher();
			t2.setName("周杰伦");
			session.save(t2);

			Teacher t3 = new Teacher();
			t3.setName("刘德华");
			session.save(t3);

			Teacher t4 = new Teacher();
			t4.setName("胡锦涛");
			session.save(t4);

			session.getTransaction().commit();
		}catch(Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}finally {
			HibernateUtils.closeSession(session);
		}
	}

	public static void saveStudent() {
		Session session = null;
		try {
			session = HibernateUtils.getSession();
			session.beginTransaction();

			Student s1 = new Student();
			s1.setCode("200507001");
			s1.setName("李倩");
			s1.setEnrollDate("2005");
			s1.setBirthday("1987-06-13");
			s1.setSex("男");
			session.save(s1);

			Student s2 = new Student();
			s2.setCode("200507002");
			s2.setName("赵高");
			s2.setEnrollDate("2005");
			s2.setBirthday("1987-05-13");
			s2.setSex("男");
			session.save(s2);

			Student s3 = new Student();
			s3.setCode("200507003");
			s3.setName("王猛");
			s3.setEnrollDate("2005");
			s3.setBirthday("1985-06-13");
			s3.setSex("男");
			session.save(s3);

			Student s4 = new Student();
			s4.setCode("200507004");
			s4.setName("孙睿");
			s4.setEnrollDate("2005");
			s4.setBirthday("1987-06-13");
			s4.setSex("女");
			session.save(s4);


			session.getTransaction().commit();
		}catch(Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}finally {
			HibernateUtils.closeSession(session);
		}
	}
}

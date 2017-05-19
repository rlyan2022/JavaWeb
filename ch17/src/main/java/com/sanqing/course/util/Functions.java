package com.sanqing.course.util;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.sanqing.course.model.Course;
import com.sanqing.course.model.CourseSchedule;
import com.sanqing.course.model.Mark;
import com.sanqing.course.model.Teacher;
import com.sanqing.course.model.Team;

/**
 * DRP函数库
 * 注意开发函数库方法必须是静态的
 * @author Administrator
 *
 */
public class Functions {

	private static SessionFactory sessionFactory;

	/**
	 * 返回班级对象的集合
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<Team> getTeamList() {

		List<Team> teamList = null;
		try {
			HibernateTemplate ht = new HibernateTemplate(sessionFactory);
			return ht.find("from Team t order by t.id");
		}catch(Exception e) {
			//记录日志,log4j等......
			e.printStackTrace();
		}
		return teamList;
	}

	/**
	 * 返回课程对象的集合
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<Course> getCourseList() {

		List<Course> courseList = null;
		try {
			HibernateTemplate ht = new HibernateTemplate(sessionFactory);
			return ht.find("from Course c order by c.id");
		}catch(Exception e) {
			//记录日志,log4j等......
			e.printStackTrace();
		}
		return courseList;
	}

	/**
	 * 返回教师对象的集合
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<Teacher> getTeacherList() {

		List<Teacher> teacherList = null;
		try {
			HibernateTemplate ht = new HibernateTemplate(sessionFactory);
			return ht.find("from Teacher t order by t.id");
		}catch(Exception e) {
			//记录日志,log4j等......
			e.printStackTrace();
		}
		return teacherList;
	}

	/**
	 * 返回课程对象
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static Course getCourseById(String id) {

		Course course = null;
		try {
			HibernateTemplate ht = new HibernateTemplate(sessionFactory);

			course = (Course)ht.get(Course.class, id);

			return course;
		}catch(Exception e) {
			//记录日志,log4j等......
			e.printStackTrace();
		}
		return course;
	}

	/**
	 * 返回班级对象
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static Team getTeamById(String id) {

		Team team = null;
		try {
			HibernateTemplate ht = new HibernateTemplate(sessionFactory);

			team = (Team)ht.get(Team.class, id);

			return team;
		}catch(Exception e) {
			//记录日志,log4j等......
			e.printStackTrace();
		}
		return team;
	}

	/**
	 * 返回学生成绩对象
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static Mark getMarkById(String id) {

		Mark mark = null;
		try {
			HibernateTemplate ht = new HibernateTemplate(sessionFactory);

			mark = (Mark)ht.get(Mark.class, id);

			return mark;
		}catch(Exception e) {
			//记录日志,log4j等......
			e.printStackTrace();
		}
		return mark;
	}


	/**
	 * 修改学生成绩对象
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static void updateMark(Mark mark) {

		try {
			HibernateTemplate ht = new HibernateTemplate(sessionFactory);

			ht.update(mark);

		}catch(Exception e) {
			//记录日志,log4j等......
			e.printStackTrace();
		}

	}

	/**
	 * 返回教师对象的集合
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<CourseSchedule> getCourseByTeam(final Team team) {

		List<CourseSchedule> list = new ArrayList<CourseSchedule>();

		try {
			HibernateTemplate ht = new HibernateTemplate(sessionFactory);

			list = ht.executeFind(new HibernateCallback() {

				public Object doInHibernate(Session session)
						throws HibernateException, SQLException {
					return session.createQuery("from CourseSchedule cs where cs.team=:team")
							.setParameter("team", team)
							.list();
				}
			});

			return list;

		}catch(Exception e) {
			//记录日志,log4j等......
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * 返回课程对象
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<Course> getCourseByTeamAndTeacher(final Team team, final Teacher teacher) {

		List<Course> list = new ArrayList<Course>();

		try {
			HibernateTemplate ht = new HibernateTemplate(sessionFactory);

			list = ht.executeFind(new HibernateCallback() {

				public Object doInHibernate(Session session)
						throws HibernateException, SQLException {
					return session.createQuery("select distinct cs.course from CourseSchedule cs where cs.team=:team and cs.teacher=:teacher " +
							"and cs.course not in(select m.course from Mark m)")
							.setParameter("team", team)
							.setParameter("teacher", teacher)
							.list();
				}
			});

			return list;

		}catch(Exception e) {
			//记录日志,log4j等......
			e.printStackTrace();
		}
		return list;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		Functions.sessionFactory = sessionFactory;
	}
}

package com.sanqing.course.daoImpl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.MatchMode;


import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.sanqing.course.dao.CourseDAO;
import com.sanqing.course.model.Course;
import com.sanqing.course.util.PageModel;



/**
 *
 * @author Administrator
 *
 */
public class CourseDAOImpl extends HibernateDaoSupport implements CourseDAO {

	/**
	 * 添加课程
	 * @param
	 */
	public void addCourse(Course course) throws Exception{

		this.getHibernateTemplate().save(course);

	}

	/**
	 * 修改课程
	 * @param
	 */
	public void modifyCourse(Course course) throws Exception {

		this.getHibernateTemplate().update(course);

	}

	/**
	 * 删除课程
	 * @param
	 */
	public void deleteCourse(String[] courseIdList) throws Exception {

		for (int i=0; i<courseIdList.length; i++) {
			Course course = (Course)this.getHibernateTemplate().load(Course.class, courseIdList[i]);
			this.getHibernateTemplate().delete(course);
		}
	}

	/**
	 * 查询全部课程
	 */
	@SuppressWarnings("unchecked")
	public List<Course> findAllCourses() {

		List<Course> courseList = new ArrayList<Course>();

		Session session = this.getSession();
		Query query = session.createQuery("from Course c");
		courseList = query.list();

		return courseList;

	}


	/**
	 * 根据Id查询课程
	 * @param
	 */
	public Course findCourseById(String id) {

		Course course = (Course)this.getHibernateTemplate().get(Course.class, id);

		return course;
	}

	/**
	 * 根据条件查询课程信息
	 */
	@SuppressWarnings("unchecked")
	public PageModel listCourse(int pageNo, Course course) {

		PageModel pageModel = null;
		List courseList = new ArrayList();

		Example exampleCourse = Example.create(course)
				.enableLike(MatchMode.ANYWHERE)
				.ignoreCase();


		courseList = this.getSession().createCriteria(Course.class)
				.add(exampleCourse)
				.setFirstResult((pageNo - 1) * PageModel.pageSize)
				.setMaxResults(PageModel.pageSize)
				.list();

		pageModel = new PageModel();
		pageModel.setPageNo(pageNo);
		pageModel.setList(courseList);
		pageModel.setTotalRecords(getTotalRecords(course));

		return pageModel;
	}

	/**
	 * 查询记录数
	 * @param
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private int getTotalRecords(Course course) {
		List courseList = new ArrayList();

		Example exampleCourse = Example.create(course)
				.enableLike(MatchMode.ANYWHERE)
				.ignoreCase();


		courseList = this.getSession().createCriteria(Course.class)
				.add(exampleCourse)
				.list();
		return courseList.size();
	}

}

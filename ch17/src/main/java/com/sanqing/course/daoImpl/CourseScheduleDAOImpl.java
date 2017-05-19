package com.sanqing.course.daoImpl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.MatchMode;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.sanqing.course.dao.CourseScheduleDAO;
import com.sanqing.course.model.Course;
import com.sanqing.course.model.CourseSchedule;
import com.sanqing.course.model.Teacher;
import com.sanqing.course.model.Team;
import com.sanqing.course.util.PageModel;



/**
 *
 * @author Administrator
 *
 */
public class CourseScheduleDAOImpl extends HibernateDaoSupport implements CourseScheduleDAO {

	/**
	 * 添加课程表
	 * @param
	 */
	public void addCourseSchedule(CourseSchedule courseSchedule) throws Exception{

		this.getHibernateTemplate().save(courseSchedule);

	}

	/**
	 * 修改课程表
	 * @param
	 */
	public void modifyCourseSchedule(CourseSchedule courseSchedule) throws Exception {

		this.getHibernateTemplate().update(courseSchedule);

	}

	/**
	 * 删除课程表
	 * @param
	 */
	public void deleteCourseSchedule(String[] courseScheduleIdList) throws Exception {

		for (int i=0; i<courseScheduleIdList.length; i++) {
			CourseSchedule courseSchedule = (CourseSchedule)this.getHibernateTemplate().load(CourseSchedule.class, courseScheduleIdList[i]);
			this.getHibernateTemplate().delete(courseSchedule);
		}
	}

	/**
	 * 查询全部课程表
	 */
	@SuppressWarnings("unchecked")
	public List<CourseSchedule> findAllCourseSchedules() throws Exception {

		List<CourseSchedule> courseScheduleList = new ArrayList<CourseSchedule>();

		Session session = this.getSession();
		Query query = session.createQuery("from CourseSchedule cs");
		courseScheduleList = query.list();

		return courseScheduleList;

	}

	/**
	 * 根据Id查询课程表
	 * @param
	 */
	@SuppressWarnings("unchecked")
	public CourseSchedule findCourseScheduleById(String id) throws Exception {

		CourseSchedule courseSchedule = (CourseSchedule)this.getHibernateTemplate().load(CourseSchedule.class, id);

		return courseSchedule;

	}


	/**
	 * 根据课程查询课表
	 * @param
	 */
	@SuppressWarnings("unchecked")
	public List<CourseSchedule> findCourseScheduleByCourse(Course course) throws Exception {

		List<CourseSchedule> list = new ArrayList<CourseSchedule>();

		list = this.getSession().createQuery("from CourseSchedule cs where cs.course=:course")
				.setParameter("course", course)
				.list();

		return list;
	}


	/**
	 * 根据班级查询课成
	 * @param
	 */
	@SuppressWarnings("unchecked")
	public List<Course> findCourseByTeam(Team team) throws Exception {

		List<Course> list = new ArrayList<Course>();

		list = this.getSession().createQuery("select cs.course from CourseSchedule cs where cs.team=:team")
				.setParameter("team", team)
				.list();

		return list;
	}

	/**
	 * 根据班级查询课程表
	 * @param
	 */
	@SuppressWarnings("unchecked")
	public List<CourseSchedule> findCourseScheduleByTeam(Team team) throws Exception {

		List<CourseSchedule> list = new ArrayList<CourseSchedule>();

		list = this.getSession().createQuery("from CourseSchedule cs where cs.team=:team")
				.setParameter("team", team)
				.list();

		return list;
	}


	/**
	 * 根据教师检索其所教的班级
	 * @param teacher
	 * @param team
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<Team> findTeamByTeacher(Teacher teacher) throws Exception {
		List<Team> list = new ArrayList<Team>();

		list = this.getSession().createQuery("select distinct cs.team from CourseSchedule cs where cs.teacher=:teacher")
				.setParameter("teacher", teacher)
				.list();

		return list;

	}

	/**
	 * 根据教师查询课程表
	 * @param
	 */
	@SuppressWarnings("unchecked")
	public List<CourseSchedule> findCourseScheduleByTeacher(Teacher teacher) throws Exception {

		List<CourseSchedule> list = new ArrayList<CourseSchedule>();

		list = this.getSession().createQuery("from CourseSchedule cs where cs.teacher=:teacher")
				.setParameter("teacher", teacher)
				.list();

		return list;

	}

	/**
	 * 根据教师和班级查询课程表
	 * @param
	 */
	@SuppressWarnings("unchecked")
	public List<CourseSchedule> findCourseScheduleByTeacherAndTeam(Teacher teacher, Team team) throws Exception {

		List<CourseSchedule> list = new ArrayList<CourseSchedule>();

		list = this.getSession().createQuery("from CourseSchedule cs where cs.teacher=:teacher and cs.team=:team")
				.setParameter("teacher", teacher)
				.setParameter("team", team)
				.list();

		return list;
	}

	/**
	 * 根据教师和班级查询课程表
	 * @param
	 */
	@SuppressWarnings("unchecked")
	public CourseSchedule findCourseSchedule(CourseSchedule courseSchedule) throws Exception {

		List<CourseSchedule> list = new ArrayList<CourseSchedule>();

		list = this.getSession().createQuery("from CourseSchedule cs where cs.teacher=:teacher and cs.team=:team and cs.course=:course")
				.setParameter("teacher", courseSchedule.getTeacher())
				.setParameter("team", courseSchedule.getTeam())
				.setParameter("course", courseSchedule.getCourse())
				.list();
		if(list.size() <= 0) {
			return null;
		}

		return list.get(0);

	}


	/**
	 * 根据条件查询课程表信息
	 */
	@SuppressWarnings("unchecked")
	public PageModel listCourseSchedule(int pageNo, CourseSchedule courseSchedule) throws Exception {

		PageModel pageModel = null;
		List courseScheduleList = new ArrayList();

		Example example = Example.create(courseSchedule)
				.enableLike(MatchMode.EXACT)
				.ignoreCase();


		courseScheduleList = this.getSession().createCriteria(CourseSchedule.class)
				.add(example)
				.list();

		pageModel = new PageModel();
		pageModel.setPageNo(pageNo);
		pageModel.setList(courseScheduleList);
		pageModel.setTotalRecords(getTotalRecords(courseSchedule));

		return pageModel;

	}

	/**
	 * 查询记录数
	 * @param
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private int getTotalRecords(CourseSchedule courseSchedule) {
		List courseScheduleList = new ArrayList();

		Example example = Example.create(courseSchedule)
				.enableLike(MatchMode.EXACT)
				.ignoreCase();


		courseScheduleList = this.getSession().createCriteria(CourseSchedule.class)
				.add(example)
				.list();
		return courseScheduleList.size();

	}

}

package com.sanqing.course.daoImpl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.sanqing.course.dao.StudentDAO;
import com.sanqing.course.model.Course;
import com.sanqing.course.model.Student;
import com.sanqing.course.model.Teacher;
import com.sanqing.course.model.Team;
import com.sanqing.course.util.PageModel;



/**
 *
 * @author Administrator
 *
 */
public class StudentDAOImpl extends HibernateDaoSupport implements StudentDAO {

	/**
	 * 添加学生
	 * @param
	 */
	public void addStudent(Student student) throws Exception{

		this.getHibernateTemplate().save(student);

	}

	/**
	 * 修改学生
	 * @param
	 */
	public void modifyStudent(Student student) throws Exception {

		this.getHibernateTemplate().update(student);

	}

	/**
	 * 删除学生
	 * @param
	 */
	public void deleteStudent(String[] studentIdList) throws Exception {

		for (int i=0; i<studentIdList.length; i++) {
			Student student = (Student)this.getHibernateTemplate().load(Student.class, studentIdList[i]);
			this.getHibernateTemplate().delete(student);
		}
	}

	/**
	 * 查询全部学生
	 */
	@SuppressWarnings("unchecked")
	public List<Student> findAllStudents() {

		List<Student> studentList = new ArrayList<Student>();

		Session session = this.getSession();
		Query query = session.createQuery("from Student s");
		studentList = query.list();

		return studentList;

	}


	/**
	 * 根据Id查询学生
	 * @param
	 */
	public Student findStudentById(String id) {

		Student student = (Student)this.getHibernateTemplate().load(Student.class, id);

		return student;
	}

	/**
	 * 根据条件查询学生信息
	 */
	@SuppressWarnings("unchecked")
	public PageModel listStudent(int pageNo, Student student) {

		PageModel pageModel = null;
		List studentList = new ArrayList();

		Example exampleStudent = Example.create(student)
				.enableLike(MatchMode.ANYWHERE)
				.ignoreCase();


		studentList = this.getSession().createCriteria(Student.class)
				.add(exampleStudent)
				.setFirstResult((pageNo - 1) * PageModel.pageSize)
				.setMaxResults(PageModel.pageSize)
				.addOrder(Order.asc("code"))
				.list();

		pageModel = new PageModel();
		pageModel.setPageNo(pageNo);
		pageModel.setList(studentList);
		pageModel.setTotalRecords(getTotalRecords(student));

		return pageModel;

	}

	/**
	 * 查询记录数
	 * @param
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private int getTotalRecords(Student student) {
		List studentList = new ArrayList();

		Example exampleStudent = Example.create(student)
				.enableLike(MatchMode.ANYWHERE)
				.ignoreCase();


		studentList = this.getSession().createCriteria(Student.class)
				.add(exampleStudent)
				.list();
		return studentList.size();
	}


	/**
	 * 根据条件统计学生
	 */
	@SuppressWarnings("unchecked")
	public PageModel countStudent(int pageNo, Team team, Course course, Teacher teacher) throws Exception {

		StringBuffer queryStr = new StringBuffer();

		boolean conditionFound = false;

		Query query = null;

		if(team != null) {
			queryStr.append("from Student s where s.team=:team order by s.code");

			query = this.getSession().createQuery(queryStr.toString());
			query.setParameter("team", team);
		} else {
			if(course != null) {
				if(conditionFound) {
					queryStr.append(" and");
				}
				queryStr.append(" s.team in(select cs.team from CourseSchedule cs where cs.course=:course)");
				conditionFound = true;
			}
			if(teacher != null) {
				if(conditionFound) {
					queryStr.append(" and");
				}
				queryStr.append(" s.team in(select cs.team from CourseSchedule cs where cs.teacher=:teacher)");
				conditionFound = true;
			}

			queryStr.append(" order by s.code");

			String fromClause = conditionFound ? "from Student s where " : "from Student s";
			queryStr.insert(0, fromClause);
			query = this.getSession().createQuery(queryStr.toString());

			if(course != null) {
				query.setParameter("course", course);
			}

			if(teacher != null) {
				query.setParameter("teacher", teacher);
			}
		}

		PageModel pageModel = null;
		List studentList = query.setFirstResult((pageNo - 1) * PageModel.pageSize)
				.setMaxResults(PageModel.pageSize)
				.list();


		pageModel = new PageModel();
		pageModel.setPageNo(pageNo);
		pageModel.setList(studentList);
		pageModel.setTotalRecords(getTotalCountRecords(team, course, teacher));

		return pageModel;

	}

	/**
	 * 查询记录数
	 * @param
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private int getTotalCountRecords(Team team, Course course, Teacher teacher) {

		StringBuffer queryStr = new StringBuffer();

		boolean conditionFound = false;

		Query query = null;

		if(team != null) {
			queryStr.append("select count(*) from Student s where s.team=:team order by s.code");

			query = this.getSession().createQuery(queryStr.toString());
			query.setParameter("team", team);
		} else {
			if(course != null) {
				if(conditionFound) {
					queryStr.append(" and");
				}
				queryStr.append(" s.team in(select cs.team from CourseSchedule cs where cs.course=:course)");
				conditionFound = true;
			}
			if(teacher != null) {
				if(conditionFound) {
					queryStr.append(" and");
				}
				queryStr.append(" s.team in(select cs.team from CourseSchedule cs where cs.teacher=:teacher)");
				conditionFound = true;
			}

			queryStr.append(" order by s.code");

			String fromClause = conditionFound ? "select count(*) from Student s where " : "select count(*) from Student s";
			queryStr.insert(0, fromClause);
			query = this.getSession().createQuery(queryStr.toString());

			if(course != null) {
				query.setParameter("course", course);
			}

			if(teacher != null) {
				query.setParameter("teacher", teacher);
			}
		}

		Long num = (Long)query.uniqueResult();

		return num.intValue();
	}

}

package com.sanqing.course.daoImpl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.sanqing.course.dao.MarkDAO;
import com.sanqing.course.model.Course;
import com.sanqing.course.model.Mark;
import com.sanqing.course.model.Student;
import com.sanqing.course.model.Team;
import com.sanqing.course.util.PageModel;



/**
 *
 * @author Administrator
 *
 */
public class MarkDAOImpl extends HibernateDaoSupport implements MarkDAO {

	/**
	 * 添加成绩
	 * @param
	 */
	public void addMark(Mark mark) throws Exception{

		this.getHibernateTemplate().save(mark);

	}

	/**
	 * 修改成绩
	 * @param
	 */
	public void modifyMark(Mark mark) throws Exception {

		this.getHibernateTemplate().update(mark);

	}

	/**
	 * 删除成绩
	 * @param
	 */
	public void deleteMark(String[] markIdList) throws Exception {

		for (int i=0; i<markIdList.length; i++) {
			Mark mark = (Mark)this.getHibernateTemplate().load(Mark.class, markIdList[i]);
			this.getHibernateTemplate().delete(mark);
		}
	}

	/**
	 * 查询全部成绩
	 */
	@SuppressWarnings("unchecked")
	public List<Mark> findAllMarks() {

		List<Mark> markList = new ArrayList<Mark>();

		Session session = this.getSession();
		Query query = session.createQuery("from Mark m");
		markList = query.list();

		return markList;

	}


	/**
	 * 根据Id查询成绩
	 * @param
	 */
	public Mark findMarkById(String id) {

		Mark mark = (Mark)this.getHibernateTemplate().load(Mark.class, id);

		return mark;
	}

	/**
	 * 根据条件查询成绩信息
	 */
	@SuppressWarnings("unchecked")
	public PageModel listMark(int pageNo, Mark mark, Team team) {


		StringBuffer queryStr = new StringBuffer();
		boolean conditionFound = false;
		if(mark.getCourse() != null) {
			queryStr.append(" m.course=:course");
			conditionFound = true;
		}
		if(mark.getStudent() != null) {
			if(conditionFound) {
				queryStr.append(" and");
			}
			queryStr.append(" m.student=:student");
			conditionFound = true;
		}
		if(team != null) {
			if(conditionFound) {
				queryStr.append(" and");
			}
			//queryStr.append("m.student in(select t.students from Team where )");
			queryStr.append(" m.student.team=:team");
			conditionFound = true;
		}

		String fromClause = conditionFound ? "from Mark m where " : "from Mark m";
		queryStr.insert(0, fromClause);

		Query query = this.getSession().createQuery(queryStr.toString());

		if(mark.getCourse() != null) {
			query.setParameter("course", mark.getCourse());
		}

		if(mark.getStudent() != null) {
			query.setParameter("student", mark.getStudent());
		}

		if(team != null) {
			query.setParameter("team", team);
		}


		PageModel pageModel = null;
		List markList = query.setFirstResult((pageNo - 1) * PageModel.pageSize)
				.setMaxResults(PageModel.pageSize)
				.list();


		pageModel = new PageModel();
		pageModel.setPageNo(pageNo);
		pageModel.setList(markList);
		pageModel.setTotalRecords(getTotalRecords(mark, team));

		return pageModel;

	}

	/**
	 * 查询记录数
	 * @param
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private int getTotalRecords(Mark mark, Team team) {
		List markList = new ArrayList();

		StringBuffer queryStr = new StringBuffer();
		boolean conditionFound = false;
		if(mark.getCourse() != null) {
			queryStr.append(" m.course=:course ");
			conditionFound = true;
		}
		if(mark.getStudent() != null) {
			if(conditionFound) {
				queryStr.append(" and");
			}
			queryStr.append(" m.student=:student ");
			conditionFound = true;
		}
		if(team != null) {
			if(conditionFound) {
				queryStr.append(" and ");
			}
			//queryStr.append("m.student in(select t.students from Team where )");
			queryStr.append(" m.student.team=:team ");
			conditionFound = true;
		}

		String fromClause = conditionFound ? "from Mark m where " : "from Mark m ";
		queryStr.insert(0, fromClause);

		Query query = this.getSession().createQuery(queryStr.toString());

		if(mark.getCourse() != null) {
			query.setParameter("course", mark.getCourse());
		}

		if(mark.getStudent() != null) {
			query.setParameter("student", mark.getStudent());
		}

		if(team != null) {
			query.setParameter("team", team);
		}

		markList = query.list();

		return markList.size();
	}


	/**
	 * 根据学生查询成绩
	 * @param
	 */
	@SuppressWarnings("unchecked")
	public List<Mark> findMarkByStudent(Student student) {

		String hql = "select m from Mark m where m.student=:student";

		List<Mark> marks = this.getSession().createQuery(hql)
				.setParameter("student", student)
				.list();

		return marks;
	}

	/**
	 * 根据班级和课程及分数段查询成绩
	 * @param
	 */
	@SuppressWarnings("unchecked")
	public List<Mark> findMarkByScore(Team team, Course course, Float min, Float max) {

		String hql = "select m from Mark m where m.course=:course " +
				" and m.student in(select s from Student s where s.team=:team)" +
				" and m.score>:min and m.score<:max";

		List<Mark> marks = this.getSession().createQuery(hql)
				.setParameter("course", course)
				.setParameter("team", team)
				.setParameter("min", min)
				.setParameter("max", max)
				.list();

		return marks;
	}

	/**
	 * 根据班级和课程查询成绩
	 * @param
	 */
	@SuppressWarnings("unchecked")
	public List<Mark> findMarkByCourse(Team team, Course course) {

		String hql = "select m from Mark m where m.course=:course " +
				" and m.student in(select s from Student s where s.team=:team) order by m.score desc";

		List<Mark> marks = this.getSession().createQuery(hql)
				.setParameter("course", course)
				.setParameter("team", team)
				.list();

		return marks;
	}

}

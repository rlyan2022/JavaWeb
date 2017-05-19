package com.sanqing.course.dao;

import java.util.List;

import com.sanqing.course.model.Course;
import com.sanqing.course.model.Mark;
import com.sanqing.course.model.Student;
import com.sanqing.course.model.Team;
import com.sanqing.course.util.PageModel;


public interface MarkDAO {

	/**
	 * 添加成绩
	 * @param
	 */
	public void addMark(Mark mark) throws Exception;

	/**
	 * 修改成绩
	 * @param
	 */
	public void modifyMark(Mark mark) throws Exception;

	/**
	 * 删除成绩
	 * @param
	 */
	public void deleteMark(String[] markIdList) throws Exception;


	/**
	 * 根据Id查询成绩
	 * @param
	 */
	public Mark findMarkById(String id) throws Exception;

	/**
	 * 查询全部成绩
	 */
	public List<Mark> findAllMarks() throws Exception;

	/**
	 * 根据条件查询成绩
	 */
	public PageModel listMark(int pageNo, Mark mark, Team team) throws Exception;

	/**
	 * 根据学生查找成绩
	 * @param team
	 * @return
	 * @throws Exception
	 */
	public List<Mark> findMarkByStudent(Student student) throws Exception;

	/**
	 * 根据班级和课程及分数段查询成绩
	 * @param
	 */
	@SuppressWarnings("unchecked")
	public List<Mark> findMarkByScore(Team team, Course course, Float min, Float max) throws Exception;

	/**
	 * 根据班级和课程查询成绩
	 * @param
	 */
	@SuppressWarnings("unchecked")
	public List<Mark> findMarkByCourse(Team team, Course course) throws Exception;
}

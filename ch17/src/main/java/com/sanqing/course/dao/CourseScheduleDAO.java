package com.sanqing.course.dao;

import java.util.List;

import com.sanqing.course.model.Course;
import com.sanqing.course.model.CourseSchedule;
import com.sanqing.course.model.Teacher;
import com.sanqing.course.model.Team;
import com.sanqing.course.util.PageModel;


public interface CourseScheduleDAO {

	/**
	 * 添加课表
	 * @param
	 */
	public void addCourseSchedule(CourseSchedule courseSchedule) throws Exception;

	/**
	 * 修改课表
	 * @param
	 */
	public void modifyCourseSchedule(CourseSchedule courseSchedule) throws Exception;

	/**
	 * 删除课表
	 * @param
	 */
	public void deleteCourseSchedule(String[] courseScheduleIdList) throws Exception;


	/**
	 * 根据Id查询课表
	 * @param
	 */
	public CourseSchedule findCourseScheduleById(String id) throws Exception;


	/**
	 * 查询某个老师在某个班级所教的某门课的课表对象
	 * @param teacher
	 * @param team
	 * @param course
	 * @return
	 * @throws Exception
	 */
	public CourseSchedule findCourseSchedule(CourseSchedule courseSchedule) throws Exception;


	/**
	 * 根据班级查询课表
	 * @param
	 */
	public List<CourseSchedule> findCourseScheduleByTeam(Team team) throws Exception;

	/**
	 * 根据班级查询课成
	 * @param
	 */
	public List<Course> findCourseByTeam(Team team) throws Exception;


	/**
	 * 根据课程查询课表
	 * @param
	 */
	public List<CourseSchedule> findCourseScheduleByCourse(Course course) throws Exception;


	/**
	 * 根据教师检索其所教的班级
	 * @param teacher
	 * @param team
	 * @return
	 * @throws Exception
	 */
	public List<Team> findTeamByTeacher(Teacher teacher) throws Exception;


	/**
	 * 根据教师查询课表
	 * @param
	 */
	public List<CourseSchedule> findCourseScheduleByTeacher(Teacher teacher) throws Exception;

	/**
	 * 根据教师和班级检索课表
	 * @param teacher
	 * @param team
	 * @return
	 * @throws Exception
	 */
	public List<CourseSchedule> findCourseScheduleByTeacherAndTeam(Teacher teacher, Team team) throws Exception;


	/**
	 * 查询全部课表
	 */
	public List<CourseSchedule> findAllCourseSchedules() throws Exception;


	/**
	 * 根据条件查询课表
	 */
	public PageModel listCourseSchedule(int pageNo, CourseSchedule courseSchedule) throws Exception;

}

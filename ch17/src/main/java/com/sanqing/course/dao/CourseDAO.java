package com.sanqing.course.dao;

import java.util.List;

import com.sanqing.course.model.Course;
import com.sanqing.course.util.PageModel;


public interface CourseDAO {

	/**
	 * 添加课程
	 * @param
	 */
	public void addCourse(Course course) throws Exception;

	/**
	 * 修改课程
	 * @param
	 */
	public void modifyCourse(Course course) throws Exception;

	/**
	 * 删除课程
	 * @param
	 */
	public void deleteCourse(String[] courseIdList) throws Exception;


	/**
	 * 根据Id查询课程
	 * @param
	 */
	public Course findCourseById(String id) throws Exception;

	/**
	 * 查询全部课程
	 */
	public List<Course> findAllCourses() throws Exception;

	/**
	 * 根据条件查询课程
	 */
	public PageModel listCourse(int pageNo, Course course) throws Exception;

}

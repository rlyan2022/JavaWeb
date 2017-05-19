package com.sanqing.course.dao;

import java.util.List;

import com.sanqing.course.model.Teacher;
import com.sanqing.course.util.PageModel;


public interface TeacherDAO {

	/**
	 * 添加教师
	 * @param
	 */
	public void addTeacher(Teacher teacher) throws Exception;

	/**
	 * 修改教师
	 * @param
	 */
	public void modifyTeacher(Teacher teacher) throws Exception;

	/**
	 * 删除教师
	 * @param
	 */
	public void deleteTeacher(String[] teacherIdList) throws Exception;


	/**
	 * 根据Id查询教师
	 * @param
	 */
	public Teacher findTeacherById(String id) throws Exception;

	/**
	 * 查询全部教师
	 */
	public List<Teacher> findAllTeachers() throws Exception;

	/**
	 * 根据条件查询教师
	 */
	public PageModel listTeacher(int pageNo, Teacher teacher) throws Exception;

}

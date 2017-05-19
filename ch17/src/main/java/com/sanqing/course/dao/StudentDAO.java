package com.sanqing.course.dao;

import java.util.List;

import com.sanqing.course.model.Course;
import com.sanqing.course.model.Student;
import com.sanqing.course.model.Teacher;
import com.sanqing.course.model.Team;
import com.sanqing.course.util.PageModel;


public interface StudentDAO {

	/**
	 * 添加学生
	 * @param
	 */
	public void addStudent(Student student) throws Exception;

	/**
	 * 修改学生
	 * @param
	 */
	public void modifyStudent(Student student) throws Exception;

	/**
	 * 删除学生
	 * @param
	 */
	public void deleteStudent(String[] studentIdList) throws Exception;


	/**
	 * 根据Id查询学生
	 * @param
	 */
	public Student findStudentById(String id) throws Exception;

	/**
	 * 查询全部学生
	 */
	public List<Student> findAllStudents() throws Exception;

	/**
	 * 根据条件查询学生
	 */
	public PageModel listStudent(int pageNo, Student student) throws Exception;


	/**
	 * 根据条件统计学生
	 */
	public PageModel countStudent(int pageNo, Team team, Course course, Teacher teacher) throws Exception;


}

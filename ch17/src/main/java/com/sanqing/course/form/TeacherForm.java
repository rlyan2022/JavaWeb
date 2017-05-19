package com.sanqing.course.form;

import org.apache.struts.action.ActionForm;


@SuppressWarnings("serial")
public class TeacherForm extends ActionForm {

	//主键
	private String id;

	//教师名称
	private String name;


	//收集Id，主要教师批量删除
	private String[] selectFlag;


	//课程编号
	private String courseId;

	//第几页
	private int pageNo;


	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}



	public String[] getSelectFlag() {
		return selectFlag;
	}

	public void setSelectFlag(String[] selectFlag) {
		this.selectFlag = selectFlag;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}


	/**
	 * @return the courseId
	 */
	public String getCourseId() {
		return courseId;
	}


	/**
	 * @param courseId the courseId to set
	 */
	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}

}

package com.sanqing.course.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.sanqing.course.dao.CourseDAO;
import com.sanqing.course.dao.CourseScheduleDAO;
import com.sanqing.course.dao.TeacherDAO;
import com.sanqing.course.dao.TeamDAO;
import com.sanqing.course.form.TeacherForm;
import com.sanqing.course.model.Course;
import com.sanqing.course.model.CourseSchedule;
import com.sanqing.course.model.Teacher;
import com.sanqing.course.model.Team;
import com.sanqing.course.util.PageModel;


public class TeacherAction extends BaseDispatchAction {

	private TeacherDAO teacherDao;
	private CourseDAO courseDao;
	private TeamDAO teamDao;
	private CourseScheduleDAO courseScheduleDao;

	/**
	 * @param courseScheduleDao the courseScheduleDao to set
	 */
	public void setCourseScheduleDao(CourseScheduleDAO courseScheduleDao) {
		this.courseScheduleDao = courseScheduleDao;
	}

	/**
	 * 动态检索教师
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward list(ActionMapping mapping, ActionForm form,
							  HttpServletRequest request, HttpServletResponse response) throws Exception {

		//获取从页面表单中提交过来的值
		TeacherForm tf = (TeacherForm)form;
		Teacher teacher = new Teacher();

		String flag = request.getParameter("flag");

		if("true".equals(flag)) {
			BeanUtils.copyProperties(teacher, form);
			request.getSession().setAttribute("teacherTea", teacher);
		} else {
			teacher = (Teacher) request.getSession().getAttribute("teacherTea");
		}

		//调用业务逻辑操作
		PageModel pageModel = teacherDao.listTeacher(tf.getPageNo(), teacher);

		//将查询结果放到request中
		request.setAttribute("pageModel", pageModel);

		return mapping.findForward("list");
	}

	/**
	 * 检索所有的教师
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward findAll(ActionMapping mapping, ActionForm form,
								 HttpServletRequest request, HttpServletResponse response) throws Exception {

		List<Teacher> list = teacherDao.findAllTeachers();

		request.setAttribute("teachers", list);

		return mapping.findForward("list");
	}

	/**
	 * 删除教师
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward delete(ActionMapping mapping, ActionForm form,
								HttpServletRequest request, HttpServletResponse response) throws Exception {

		//获取从页面表单中提交过来的值
		TeacherForm tf = (TeacherForm)form;

		try {
			//调用业务逻辑操作
			teacherDao.deleteTeacher(tf.getSelectFlag());
			saveMessage(request, "teacherForm.deleted");
		} catch (Exception e) {
			saveMessage(request, "teacherForm.deleted.error");
		}

		ActionForward af = new ActionForward("teacher.do?p=list&pageNo=1", true);

		return af;
	}


	/**
	 * 编辑教师:进入新建页面,进入更新页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward edit(ActionMapping mapping, ActionForm form,
							  HttpServletRequest request, HttpServletResponse response) throws Exception {

		String id = request.getParameter("id");

		if(id != null && id.trim().length() > 0) {

			Teacher teacher = teacherDao.findTeacherById(id);

			//将Teacher的值复制给form
			if(teacher != null) {
				request.setAttribute("teacher", teacher);
			}
		}

		return mapping.findForward("edit");
	}

	/**
	 * 保存教师:更新,创建
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("deprecation")
	public ActionForward save(ActionMapping mapping, ActionForm form,
							  HttpServletRequest request, HttpServletResponse response) throws Exception {

		TeacherForm tf = (TeacherForm) form;
		Teacher teacher = new Teacher();
		BeanUtils.copyProperties(teacher, tf);

		if(tf.getId() == null || tf.getId().trim().length() == 0) {
			teacherDao.addTeacher(teacher);
			saveMessage(request, "teacherForm.added", teacher.getName());
		} else {
			teacherDao.modifyTeacher(teacher);
			saveMessage(request, "teacherForm.updated", teacher.getName());
		}

		ActionForward af = new ActionForward("teacher.do?p=list&pageNo=1", true);
		return af;
	}

	/**
	 * 查看教师详细信息
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward detail(ActionMapping mapping, ActionForm form,
								HttpServletRequest request, HttpServletResponse response) throws Exception {

		String id = request.getParameter("id");

		String teamId = request.getParameter("teamId");

		Teacher teacher = teacherDao.findTeacherById(id);

		List<CourseSchedule> list = new ArrayList<CourseSchedule>();

		//为TeamList下拉列表准备
//		List<CourseSchedule> listQuery = new ArrayList<CourseSchedule>();
//		listQuery = courseScheduleDao.findCourseScheduleByTeacher(teacher);
//		request.setAttribute("queryList", listQuery);

		if(teamId == null || teamId.trim().length() < 0) {
			list = courseScheduleDao.findCourseScheduleByTeacher(teacher);
		} else {
			Team team = teamDao.findTeamById(teamId);
			list = courseScheduleDao.findCourseScheduleByTeacherAndTeam(teacher, team);
		}

		request.setAttribute("teacher", teacher);

		request.setAttribute("courseSchedules", list);



		return mapping.findForward("detail");
	}

	/**
	 * 编辑课程信息
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward editCourse(ActionMapping mapping, ActionForm form,
									HttpServletRequest request, HttpServletResponse response) throws Exception {

		String id = request.getParameter("id");

		if(id != null && id.trim().length() > 0) {

			Teacher teacher = teacherDao.findTeacherById(id);

			//将Teacher的值复制给form
			if(teacher != null) {
				request.setAttribute("teacher", teacher);
			}
		}

		return mapping.findForward("editCourse");
	}

	/**
	 * 增加课程信息
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward saveCourse(ActionMapping mapping, ActionForm form,
									HttpServletRequest request, HttpServletResponse response) throws Exception {

		TeacherForm tf = (TeacherForm) form;

		String id = tf.getId();

		Teacher teacher = teacherDao.findTeacherById(id);
		Course course = courseDao.findCourseById(tf.getCourseId());
		teacher.getCourses().add(course);
		teacherDao.modifyTeacher(teacher);

		saveMessage(request, "teacherForm.course.added");

		ActionForward af = new ActionForward("teacher.do?p=detail&id=" + id, true);

		return af;
	}

	/**
	 * 删除课程信息
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward deleteCourse(ActionMapping mapping, ActionForm form,
									  HttpServletRequest request, HttpServletResponse response) throws Exception {

		//获取从页面表单中提交过来的值
		TeacherForm tf = (TeacherForm)form;

		String id = request.getParameter("id");
		Teacher teacher = teacherDao.findTeacherById(id);
		//调用业务逻辑操作
		String[] selectFlags = tf.getSelectFlag();

		for (int i=0; i<selectFlags.length; i++) {
			Course course = courseDao.findCourseById(selectFlags[i]);
			teacher.getCourses().remove(course);
		}

		teacherDao.modifyTeacher(teacher);

		saveMessage(request, "teacherForm.course.deleted");

		ActionForward af = new ActionForward("teacher.do?p=detail&id=" + id, true);

		return af;
	}

	/**
	 * @param teacherDao the teacherDao to set
	 */
	public void setTeacherDao(TeacherDAO teacherDao) {
		this.teacherDao = teacherDao;
	}

	/**
	 * @param courseDao the courseDao to set
	 */
	public void setCourseDao(CourseDAO courseDao) {
		this.courseDao = courseDao;
	}

	/**
	 * @param teamDao the teamDao to set
	 */
	public void setTeamDao(TeamDAO teamDao) {
		this.teamDao = teamDao;
	}

}

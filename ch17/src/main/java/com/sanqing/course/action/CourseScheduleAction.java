package com.sanqing.course.action;

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
import com.sanqing.course.form.CourseScheduleForm;
import com.sanqing.course.model.Course;
import com.sanqing.course.model.CourseSchedule;
import com.sanqing.course.model.Teacher;
import com.sanqing.course.model.Team;


public class CourseScheduleAction extends BaseDispatchAction {

	private CourseScheduleDAO courseScheduleDao;
	private TeamDAO teamDao;
	private TeacherDAO teacherDao;
	private CourseDAO courseDao;


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
	 * @param courseScheduleDao the courseScheduleDao to set
	 */
	public void setCourseScheduleDao(CourseScheduleDAO courseScheduleDao) {
		this.courseScheduleDao = courseScheduleDao;
	}

	/**
	 * @param teamDao the teamDao to set
	 */
	public void setTeamDao(TeamDAO teamDao) {
		this.teamDao = teamDao;
	}


	/**
	 * 检索所有的课程表
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward findAll(ActionMapping mapping, ActionForm form,
								 HttpServletRequest request, HttpServletResponse response) throws Exception {

		List<CourseSchedule> list = courseScheduleDao.findAllCourseSchedules();

		request.setAttribute("courseSchedules", list);

		return mapping.findForward("list");
	}

	/**
	 * 删除课程表
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
		CourseScheduleForm csf = (CourseScheduleForm)form;
		String teamId = request.getParameter("teamId");

		try {
			//调用业务逻辑操作
			courseScheduleDao.deleteCourseSchedule(csf.getSelectFlag());
			saveMessage(request, "courseScheduleForm.deleted");
		} catch(Exception e) {
			saveMessage(request, "courseScheduleForm.deleted.error");
		}

		ActionForward af = new ActionForward("team.do?p=detail&id=" + teamId, true);

		return af;
	}


	/**
	 * 编辑课程表:进入新建页面,进入更新页面
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
		String teamId = request.getParameter("teamId");

		Team team = teamDao.findTeamById(teamId);
		request.setAttribute("team", team);

		if(id != null && id.trim().length() > 0) {

			CourseSchedule courseSchedule = courseScheduleDao.findCourseScheduleById(id);

			//将course的值复制给form
			if(courseSchedule != null) {
				request.setAttribute("courseSchedule", courseSchedule);
			}
		}

		return mapping.findForward("edit");
	}

	/**
	 * 保存课程表:更新,创建
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

		String teamId = request.getParameter("teamId");

		CourseScheduleForm csf = (CourseScheduleForm) form;
		CourseSchedule courseSchedule = new CourseSchedule();
		BeanUtils.copyProperties(courseSchedule, csf);

		Course course = courseDao.findCourseById(csf.getCourseId());
		Teacher teacher = teacherDao.findTeacherById(csf.getTeacherId());
		Team team = teamDao.findTeamById(csf.getTeamId());
		courseSchedule.setCourse(course);
		courseSchedule.setTeacher(teacher);
		courseSchedule.setTeam(team);

		if(csf.getId() == null || csf.getId().trim().length() == 0) {
			courseScheduleDao.addCourseSchedule(courseSchedule);
			saveMessage(request, "courseScheduleForm.added");
		} else {
			courseScheduleDao.modifyCourseSchedule(courseSchedule);
			saveMessage(request, "courseScheduleForm.updated");
		}

		ActionForward af = new ActionForward("team.do?p=detail&id=" + teamId, true);
		return af;
	}


	/**
	 * 动态检索成绩
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public ActionForward input(ActionMapping mapping, ActionForm form,
							   HttpServletRequest request, HttpServletResponse response) throws Exception {

		CourseScheduleForm csf = (CourseScheduleForm)form;

		CourseSchedule courseSchedule = new CourseSchedule();
		CourseSchedule courseScheduleQuery = new CourseSchedule();

		Integer num = null;

		String flag = request.getParameter("flag");

		Teacher teacher = (Teacher)request.getSession().getAttribute("teacher");

		List<Team> teams = courseScheduleDao.findTeamByTeacher(teacher);
		request.setAttribute("teams", teams);

		courseSchedule.setTeacher(teacher);

		if(csf != null) {

			if(csf.getCourseId() != null && csf.getCourseId().trim().length() > 0) {
				Course course = courseDao.findCourseById(csf.getCourseId());
				courseSchedule.setCourse(course);
			}

			if(csf.getTeamId() != null && csf.getTeamId().trim().length() > 0) {
				Team team = teamDao.findTeamById(csf.getTeamId());
				courseSchedule.setTeam(team);
			}
			//调用业务逻辑操作
			courseScheduleQuery = courseScheduleDao.findCourseSchedule(courseSchedule);
			request.getSession().setAttribute("courseScheduleQuery", courseScheduleQuery);
		} else if("added".equals(flag)){
			//调用业务逻辑操作
			courseScheduleQuery = (CourseSchedule)request.getSession().getAttribute("courseScheduleQuery");
		}


		if(courseScheduleQuery != null) {
			num = courseScheduleQuery.getTeam().getStudents().size();
			if(num > 0) {
				request.setAttribute("exist", num);
			}
		}


		//将查询结果放到request中
		request.setAttribute("courseSchedule", courseScheduleQuery);

		request.setAttribute("num", num);

		return mapping.findForward("input");
	}


}

package com.sanqing.course.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.sanqing.course.dao.CourseScheduleDAO;
import com.sanqing.course.dao.TeamDAO;
import com.sanqing.course.form.TeamForm;
import com.sanqing.course.model.CourseSchedule;
import com.sanqing.course.model.Team;
import com.sanqing.course.util.PageModel;


public class TeamAction extends BaseDispatchAction {

	private TeamDAO teamDao;
	private CourseScheduleDAO courseScheduleDao;


	/**
	 * @param teamDao the teamDao to set
	 */
	public void setTeamDao(TeamDAO teamDao) {
		this.teamDao = teamDao;
	}

	/**
	 * @param courseScheduleDAO the courseScheduleDAO to set
	 */
	public void setCourseScheduleDao(CourseScheduleDAO courseScheduleDao) {
		this.courseScheduleDao = courseScheduleDao;
	}

	/**
	 * 动态检索班级
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
		TeamForm tf = (TeamForm)form;
		Team team = new Team();
		String flag = request.getParameter("flag");

		if("true".equals(flag)) {
			BeanUtils.copyProperties(team, form);
			request.getSession().setAttribute("teamTem", team);
		} else {
			team = (Team) request.getSession().getAttribute("teamTem");
		}

		//调用业务逻辑操作
		PageModel pageModel = teamDao.listTeam(tf.getPageNo(), team);

		//将查询结果放到request中
		request.setAttribute("pageModel", pageModel);

		return mapping.findForward("list");
	}

	/**
	 * 检索所有的班级
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward findAll(ActionMapping mapping, ActionForm form,
								 HttpServletRequest request, HttpServletResponse response) throws Exception {

		List<Team> list = teamDao.findAllTeams();

		request.setAttribute("teams", list);

		return mapping.findForward("list");
	}

	/**
	 * 删除班级
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
		TeamForm tf = (TeamForm)form;

		try {
			//调用业务逻辑操作
			teamDao.deleteTeam(tf.getSelectFlag());
			saveMessage(request, "teamForm.deleted");
		} catch(Exception e) {
			e.printStackTrace();
			saveMessage(request, "teamForm.deleted.error");
		}


		ActionForward af = new ActionForward("team.do?p=list&pageNo=1", true);

		return af;
	}


	/**
	 * 编辑班级:进入新建页面,进入更新页面
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

			Team team = teamDao.findTeamById(id);

			//将course的值复制给form
			if(team != null) {
				request.setAttribute("team", team);
			}
		}

		return mapping.findForward("edit");
	}

	/**
	 * 保存班级:更新,创建
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


		TeamForm tf = (TeamForm) form;
		Team team = new Team();
		BeanUtils.copyProperties(team, tf);

		if(tf.getId() == null || tf.getId().trim().length() == 0) {
			teamDao.addTeam(team);
			saveMessage(request, "teamForm.added", team.getName());
		} else {
			teamDao.modifyTeam(team);
			saveMessage(request, "teamForm.updated", team.getName());
		}

		ActionForward af = new ActionForward("team.do?p=list&pageNo=1", true);
		return af;
	}

	/**
	 * 查看班级详细信息
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

		Team team = teamDao.findTeamById(id);

		List<CourseSchedule> courseSchedules = courseScheduleDao.findCourseScheduleByTeam(team);

		request.setAttribute("courseSchedules", courseSchedules);
		request.setAttribute("team", team);

		return mapping.findForward("detail");
	}


}

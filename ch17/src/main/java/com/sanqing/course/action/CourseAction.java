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
import com.sanqing.course.form.CourseForm;
import com.sanqing.course.model.Course;
import com.sanqing.course.model.CourseSchedule;
import com.sanqing.course.util.PageModel;


public class CourseAction extends BaseDispatchAction {

	private CourseDAO courseDao;
	private CourseScheduleDAO courseScheduleDao;


	/**
	 * 动态检索课程
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
		CourseForm cf = (CourseForm)form;
		String flag = request.getParameter("flag");
		Course course = new Course();

		if("true".equals(flag)) {
			BeanUtils.copyProperties(course, form);
			request.getSession().setAttribute("courseCou", course);
		} else {
			course = (Course) request.getSession().getAttribute("courseCou");
		}

		//调用业务逻辑操作
		PageModel pageModel = courseDao.listCourse(cf.getPageNo(), course);

		//将查询结果放到request中
		request.setAttribute("pageModel", pageModel);

		return mapping.findForward("list");
	}

	/**
	 * 检索所有的课程
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward findAll(ActionMapping mapping, ActionForm form,
								 HttpServletRequest request, HttpServletResponse response) throws Exception {

		List<Course> list = courseDao.findAllCourses();

		request.setAttribute("courses", list);

		return mapping.findForward("list");
	}

	/**
	 * 删除课程
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
		CourseForm cf = (CourseForm)form;

		try {
			//调用业务逻辑操作
			courseDao.deleteCourse(cf.getSelectFlag());
			saveMessage(request, "courseForm.deleted");
		} catch(Exception e) {
			saveMessage(request, "courseForm.deleted.error");
		}

		ActionForward af = new ActionForward("course.do?p=list&pageNo=1", true);

		return af;
	}


	/**
	 * 编辑课程:进入新建页面,进入更新页面
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

			Course course = courseDao.findCourseById(id);

			//将course的值复制给form
			if(course != null) {
				request.setAttribute("course", course);
			}
		}

		return mapping.findForward("edit");
	}

	/**
	 * 保存课程:更新,创建
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


		CourseForm cf = (CourseForm) form;
		Course course = new Course();
		BeanUtils.copyProperties(course, cf);

		if(cf.getId() == null || cf.getId().trim().length() == 0) {
			courseDao.addCourse(course);
			saveMessage(request, "courseForm.added", course.getName());
		} else {
			courseDao.modifyCourse(course);
			saveMessage(request, "courseForm.updated", course.getName());
		}

		ActionForward af = new ActionForward("course.do?p=list&pageNo=1", true);
		return af;
	}

	/**
	 * 查看课程详细信息
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

		Course course = courseDao.findCourseById(id);

		List<CourseSchedule> courseSchedules = courseScheduleDao.findCourseScheduleByCourse(course);

		request.setAttribute("courseSchedules", courseSchedules);
		request.setAttribute("course", course);

		return mapping.findForward("detail");
	}

	/**
	 * @param courseDao the courseDao to set
	 */
	public void setCourseDao(CourseDAO courseDao) {
		this.courseDao = courseDao;
	}

	public void setCourseScheduleDao(CourseScheduleDAO courseScheduleDao) {
		this.courseScheduleDao = courseScheduleDao;
	}

}

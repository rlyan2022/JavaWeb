package com.sanqing.course.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import com.sanqing.course.dao.TeacherDAO;
import com.sanqing.course.dao.UserDAO;
import com.sanqing.course.form.UserForm;
import com.sanqing.course.model.Teacher;
import com.sanqing.course.model.User;
import com.sanqing.course.util.PageModel;


public class UserAction extends BaseDispatchAction {

	private UserDAO userDao;
	private TeacherDAO teacherDao;

	/**
	 * 动态检索用户
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward list(ActionMapping mapping, ActionForm form,
							  HttpServletRequest request, HttpServletResponse response) throws Exception {


		User user = new User();
		UserForm uf = (UserForm)form;
		String flag = request.getParameter("flag");
		PageModel pageModel = new PageModel();

		if("true".equals(flag)) {
			BeanUtils.copyProperties(user, form);
			request.getSession().setAttribute("userUse", user);
		} else {
			user = (User) request.getSession().getAttribute("userUse");
		}
		pageModel = userDao.listUser(uf.getPageNo(), user);

		//将查询结果放到request中
		request.setAttribute("pageModel", pageModel);

		return mapping.findForward("list");
	}

	/**
	 * 检索所有的用户
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward findAll(ActionMapping mapping, ActionForm form,
								 HttpServletRequest request, HttpServletResponse response) throws Exception {

		List<User> list = userDao.findAllUsers();

		request.setAttribute("users", list);

		return mapping.findForward("list");
	}

	/**
	 * 删除用户
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
		UserForm uf = (UserForm)form;

		//调用业务逻辑操作
		userDao.deleteUser(uf.getSelectFlag());
		saveMessage(request, "userForm.deleted");

		ActionForward af = new ActionForward("user.do?p=list&pageNo=1", true);

		return af;
	}


	/**
	 * 编辑用户:进入新建页面,进入更新页面
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

			User user = userDao.findUserById(id);

			//将user的值复制给form
			if(user != null) {
				request.setAttribute("user", user);
			}
		}

		return mapping.findForward("edit");
	}

	/**
	 * 保存用户:更新,创建
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

		UserForm uf = (UserForm) form;
		User user = new User();
		BeanUtils.copyProperties(user, uf);

		if(uf.getTeacherId() != null && uf.getTeacherId().trim().length()>0) {
			Teacher teacher = teacherDao.findTeacherById(uf.getTeacherId());
			user.setTeacher(teacher);
		}

		if(uf.getId() == null || uf.getId().trim().length() == 0) {
			userDao.addUser(user);
			saveMessage(request, "userForm.added", user.getName());
		} else {
			userDao.modifyUser(user);
			saveMessage(request, "userForm.updated", user.getName());
		}

		ActionForward af = new ActionForward("user.do?p=list&pageNo=1", true);
		return af;
	}


	/**
	 * 用户登录
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward logon(ActionMapping mapping, ActionForm form,
							   HttpServletRequest request, HttpServletResponse response) {

		UserForm userForm = (UserForm) form;
		String ccode = (String) request.getSession().getAttribute("ccode");
		String checkcode = userForm.getCheckcode();
		if (!(checkcode.equals(ccode))) {
			ActionMessages errors = new ActionMessages();
			errors.add("checkcode", new ActionMessage("checkcode.error"));
			super.saveErrors(request, errors);
			return mapping.findForward("logonFailure");
		}
		User user = new User();
		User userNew = new User();
//		MD5Code md5 = new MD5Code();
		user.setName(userForm.getName());
		user.setPassword(userForm.getPassword());
		//user.setPassword(md5.getMD5ofStr(userForm.getPassword()));

		try {
			userNew = userDao.logon(user);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (userNew != null) {
			// 登录成功
			// 向session之中设置内容
			request.getSession().setAttribute("logon", userNew);

			if(userNew.getTeacher() != null) {
				request.getSession().setAttribute("teacher", userNew.getTeacher());
			} else {
				request.getSession().setAttribute("admin", userNew);
			}
			return mapping.findForward("logonSuccess");

		} else {
			return mapping.findForward("logonFailure");
		}
	}


	/**
	 * 退出系统
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward logoff(ActionMapping mapping, ActionForm form,
								HttpServletRequest request, HttpServletResponse response) {

		HttpSession session = request.getSession();
		session.invalidate();
		//返回业务处理结果:注册成功
		return mapping.findForward("logoffSuccess");
	}

	/**
	 * @param userDao the userDao to set
	 */
	public void setUserDao(UserDAO userDao) {
		this.userDao = userDao;
	}

	/**
	 * @param teacherDao the teacherDao to set
	 */
	public void setTeacherDao(TeacherDAO teacherDao) {
		this.teacherDao = teacherDao;
	}

}

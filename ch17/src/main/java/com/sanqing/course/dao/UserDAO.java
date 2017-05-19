package com.sanqing.course.dao;

import java.util.List;

import com.sanqing.course.model.User;
import com.sanqing.course.util.PageModel;


public interface UserDAO {

	/**
	 * 添加用户
	 * @param
	 */
	public void addUser(User user) throws Exception;

	/**
	 * 用户登录
	 * @param
	 */
	public User logon(User user) throws Exception;

	/**
	 * 修改用户
	 * @param
	 */
	public void modifyUser(User user) throws Exception;

	/**
	 * 删除用户
	 * @param
	 */
	public void deleteUser(String[] userIdList) throws Exception;


	/**
	 * 根据Id查询用户
	 * @param
	 */
	public User findUserById(String id) throws Exception;

	/**
	 * 查询全部用户
	 */
	public List<User> findAllUsers() throws Exception;

	/**
	 * 根据条件查询用户
	 */
	public PageModel listUser(int pageNo, User user) throws Exception;

}

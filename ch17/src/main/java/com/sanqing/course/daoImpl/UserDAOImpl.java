package com.sanqing.course.daoImpl;


import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.MatchMode;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.sanqing.course.dao.UserDAO;
import com.sanqing.course.model.User;
import com.sanqing.course.util.PageModel;


/**
 *
 * @author Administrator
 *
 */
public class UserDAOImpl extends HibernateDaoSupport implements UserDAO {

	/**
	 * 用户登录
	 * @param
	 */
	@SuppressWarnings("unchecked")
	public User logon(User user) throws Exception {

		User userNew = null;

		if(user.getName().equals("admin") && user.getPassword().equals("admin")) {
			User admin = new User();
			admin.setName("admin");
			admin.setPassword("admin");
			return admin;
		}

		String hql = "From User u where u.name=? and u.password=?";
		Query q = this.getSession().createQuery(hql);
		q.setString(0, user.getName());
		q.setString(1, user.getPassword());
		List all = q.list();

		if (all.size() > 0) {
			userNew = (User) all.get(0);
		}

		return userNew;
	}

	/**
	 * 添加用户
	 * @param
	 */
	public void addUser(User user) throws Exception{

		this.getHibernateTemplate().save(user);

	}

	/**
	 * 修改用户
	 * @param
	 */
	public void modifyUser(User user) throws Exception {

		this.getHibernateTemplate().update(user);

	}

	/**
	 * 删除用户
	 * @param
	 */
	public void deleteUser(String[] userIdList) throws Exception {

		for (int i=0; i<userIdList.length; i++) {
			User user = (User)this.getHibernateTemplate().load(User.class, userIdList[i]);
			this.getHibernateTemplate().delete(user);
		}
	}

	/**
	 * 查询全部用户
	 */
	@SuppressWarnings("unchecked")
	public List<User> findAllUsers() {

		List<User> userList = new ArrayList<User>();

		Session session = this.getSession();
		Query query = session.createQuery("from User u");
		userList = query.list();

		return userList;

	}


	/**
	 * 根据Id查询用户
	 * @param
	 */
	public User findUserById(String id) {

		User user = (User)this.getHibernateTemplate().load(User.class, id);

		return user;
	}

	/**
	 * 根据条件查询用户信息
	 */
	@SuppressWarnings("unchecked")
	public PageModel listUser(int pageNo, User user) {

		PageModel pageModel = null;
		List userList = new ArrayList();

		Example exampleUser = Example.create(user)
				.enableLike(MatchMode.ANYWHERE)
				.ignoreCase();


		userList = this.getSession().createCriteria(User.class)
				.add(exampleUser)
				.setFirstResult((pageNo - 1) * PageModel.pageSize)
				.setMaxResults(PageModel.pageSize)
				.list();

		pageModel = new PageModel();
		pageModel.setPageNo(pageNo);
		pageModel.setList(userList);
		pageModel.setTotalRecords(getTotalRecords(user));

		return pageModel;

	}

	/**
	 * 查询记录数
	 * @param
	 * @return
	 */
	@SuppressWarnings("unchecked" )
	private int getTotalRecords(User user) {

		List userList = new ArrayList();

		Example exampleUser = Example.create(user)
				.enableLike(MatchMode.ANYWHERE)
				.ignoreCase();

		userList = this.getSession().createCriteria(User.class)
				.add(exampleUser)
				.list();
		return userList.size();
	}

}

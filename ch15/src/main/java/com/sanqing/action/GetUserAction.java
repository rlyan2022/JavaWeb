package com.sanqing.action;

import com.opensymphony.xwork2.ActionSupport;
import com.sanqing.dao.UserDAO;
import com.sanqing.po.User;

import java.util.List;

public class GetUserAction extends ActionSupport {
    private UserDAO userDAO;
    private List<User> allUser;
    private int start;
    private int limit;
    private int recordSize;

    public int getRecordSize() {
        return recordSize;
    }

    public void setRecordSize(int recordSize) {
        this.recordSize = recordSize;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public List<User> getAllUser() {
        return allUser;
    }

    public void setAllUser(List<User> allUser) {
        this.allUser = allUser;
    }

    public UserDAO getUserDAO() {
        return userDAO;
    }

    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public String execute() throws Exception {
        //1.假分页
//		allUser = userDAO.findAll();
//		//记录数
//		setRecordSize(allUser.size());
//		//判断是否越界
//		int end = start + limit;
//		if(end >= allUser.size()) {
//			end = allUser.size();
//		}
//		//取得子List
//		allUser = allUser.subList(start, end);

        //2.真分页
        allUser = userDAO.findAll(start, limit);
        //记录数
        setRecordSize(userDAO.findAll().size());

        return this.SUCCESS;
    }
}

package com.sanqing.action;

import com.opensymphony.xwork2.ActionSupport;
import com.sanqing.dao.UserDAO;
import com.sanqing.po.User;

import java.util.ArrayList;
import java.util.List;

public class QueryUserAction extends ActionSupport {
    private UserDAO userDAO;
    private List<User> allUser;
    private int start;
    private int limit;
    private int recordSize;
    private String queryCondition;
    private String queryValue;


    public String getQueryCondition() {
        return queryCondition;
    }

    public void setQueryCondition(String queryCondition) {
        this.queryCondition = queryCondition;
    }

    public String getQueryValue() {
        return queryValue;
    }

    public void setQueryValue(String queryValue) {
        this.queryValue = queryValue;
    }

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
        //首先判断查询条件是否输入
        if (queryCondition == null || "".equals(queryCondition)) {
            //取出所有数据
            allUser = userDAO.findAll(start, limit);
            recordSize = userDAO.findAll().size();
        } else {
            //判断查询值是否输入
            if (queryValue == null || "".equals(queryValue)) {
                //取出所有数据
                allUser = userDAO.findAll(start, limit);
                recordSize = userDAO.findAll().size();
            } else {
                //判断是属于哪种查询条件
                if ("id".equals(queryCondition)) {
                    allUser = new ArrayList<User>();
                    allUser.add(userDAO.findById(Integer.parseInt(queryValue)));
                } else if ("username".equals(queryCondition)) {
                    allUser = userDAO.findByUsername(queryValue);
                } else if ("password".equals(queryCondition)) {
                    allUser = userDAO.findByPassword(queryValue);
                }
                recordSize = allUser.size();
            }
        }

        return this.SUCCESS;
    }
}

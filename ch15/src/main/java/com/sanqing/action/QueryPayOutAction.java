package com.sanqing.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.sanqing.dao.PayOutDAO;
import com.sanqing.dao.UserDAO;
import com.sanqing.po.PayOut;
import com.sanqing.po.User;

import java.util.List;
import java.util.Map;

public class QueryPayOutAction extends ActionSupport {
    private int start;
    private int limit;

    private String queryCondition;
    private String queryValue;

    private List<PayOut> allPayOut;
    private int recordSize;

    private UserDAO userDAO;
    private PayOutDAO payOutDAO;

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


    public String execute() throws Exception {
        //获得当前用户信息
        Map session = ActionContext.getContext().getSession();
        String username = (String) session.get("username");
        User user = (User) userDAO.findByUsername(username).get(0);

        if (queryCondition == null || "".equals(queryCondition)) {
            //取出所有支出信息，这里要注意，只是该用户的，不能是所有用户的
            allPayOut = payOutDAO.findByProperty("user", user, start, limit);
            recordSize = allPayOut.size();
        } else {
            if (queryValue == null || "".equals(queryValue)) {
                //取出所有支出信息，这里要注意，只是该用户的，不能是所有用户的
                allPayOut = payOutDAO.findByProperty("user", user, start, limit);
                recordSize = allPayOut.size();
            } else {
                if ("payOutName".equals(queryCondition)) {
                    allPayOut = payOutDAO.findByProperties("payOutName", queryValue, "user", user, start, limit);
                    recordSize = allPayOut.size();
                } else if ("payOutMoney".equals(queryCondition)) {
                    allPayOut = payOutDAO.findByProperties("payOutMoney", Double.parseDouble(queryValue), "user", user, start, limit);
                    recordSize = allPayOut.size();
                }
            }
        }

        return this.SUCCESS;
    }

    public List<PayOut> getAllPayOut() {
        return allPayOut;
    }

    public void setAllPayOut(List<PayOut> allPayOut) {
        this.allPayOut = allPayOut;
    }

    public int getRecordSize() {
        return recordSize;
    }

    public void setRecordSize(int recordSize) {
        this.recordSize = recordSize;
    }

    public UserDAO getUserDAO() {
        return userDAO;
    }

    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public PayOutDAO getPayOutDAO() {
        return payOutDAO;
    }

    public void setPayOutDAO(PayOutDAO payOutDAO) {
        this.payOutDAO = payOutDAO;
    }


}

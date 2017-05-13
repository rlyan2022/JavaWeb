package com.ibeifeng.action;

import com.ibeifeng.dao.ConsumeDAO;
import com.ibeifeng.po.Consume;
import com.opensymphony.xwork2.ActionSupport;

import java.util.List;

public class ConsumeQueryAction extends ActionSupport {
    private List<Consume> allConsume;
    private int recordSize;
    private int start;
    private int limit;

    private ConsumeDAO consumeDAO;

    public List<Consume> getAllConsume() {
        return allConsume;
    }

    public void setAllConsume(List<Consume> allConsume) {
        this.allConsume = allConsume;
    }

    public int getRecordSize() {
        return recordSize;
    }

    public void setRecordSize(int recordSize) {
        this.recordSize = recordSize;
    }

    public ConsumeDAO getConsumeDAO() {
        return consumeDAO;
    }

    public void setConsumeDAO(ConsumeDAO consumeDAO) {
        this.consumeDAO = consumeDAO;
    }

    public String execute() throws Exception {
        allConsume = consumeDAO.findAll(start, limit);
        recordSize = consumeDAO.findAll().size();
        return this.SUCCESS;
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

}

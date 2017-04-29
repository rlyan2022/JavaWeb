package com.sanqing.factory;

import com.sanqing.dao.ReplyDAO;
import com.sanqing.daoImpl.ReplyDAOImpl;

public class ReplyDAOFactory {
    public static ReplyDAO getReplyDAOInstance() {    //工厂方法，用来返回DAO实现类实例
        return new ReplyDAOImpl();                        //返回DAO实现类实例
    }
}

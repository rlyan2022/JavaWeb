package com.sanqing.daoFactory;

import com.sanqing.dao.VoteDAO;
import com.sanqing.daoImpl.VoteDAOImpl;

public class VoteDAOFactory {
    public static VoteDAO getVoteDAOInstance() {    //工厂方法，用来返回DAO实现类实例
        return new VoteDAOImpl();                        //返回DAO实现类实例
    }
}

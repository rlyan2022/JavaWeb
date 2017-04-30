package com.sanqing.daoFactory;

import com.sanqing.dao.VoteOptionDAO;
import com.sanqing.daoImpl.VoteOptionDAOImpl;

public class VoteOptionDAOFactory {
    public static VoteOptionDAO getVoteOptionDAOInstance() {    //工厂方法，用来返回DAO实现类实例
        return new VoteOptionDAOImpl();                        //返回DAO实现类实例
    }
}
